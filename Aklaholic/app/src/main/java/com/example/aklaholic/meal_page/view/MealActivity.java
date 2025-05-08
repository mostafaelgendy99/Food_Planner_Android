package com.example.aklaholic.meal_page.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aklaholic.R;
import com.example.aklaholic.meal_page.presenter.mealPresenter;
import com.example.aklaholic.model.local.MealLocalDataSource;
import com.example.aklaholic.model.pojo.Meal;
import com.example.aklaholic.model.remote.MealRemoteDataSource;
import com.example.aklaholic.model.remote.MealRemoteDataSourceImpl;
import com.example.aklaholic.model.repo.MealRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealActivity extends AppCompatActivity implements Iview {

    private ShapeableImageView mealImage;
    private FloatingActionButton favoriteButton;
    private RecyclerView ingredientsRecyclerView;
    private MaterialTextView mealName;
    private MaterialTextView mealInstructions;
    private WebView webView;

    private mealPresenter presenter;


    private String mealId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal);

        mealImage = findViewById(R.id.meal_image);
        favoriteButton = findViewById(R.id.favorite_button);
        ingredientsRecyclerView = findViewById(R.id.ingredients_recycler);
        mealName = findViewById(R.id.meal_title);
        mealInstructions = findViewById(R.id.instructions_text);
        webView = findViewById(R.id.youtube_webview);

        MealRemoteDataSource mealRemoteDataSource = MealRemoteDataSourceImpl.getInstance();
        MealLocalDataSource mealLocalDataSource = MealLocalDataSource.getInstance(this);
        MealRepository mealRepository  = MealRepository.getInstance(mealLocalDataSource, mealRemoteDataSource);
        presenter = new mealPresenter(mealRepository, this);

        String senderID = getIntent().getStringExtra("senderID");

        if (senderID != null) {
            if(senderID.equals("home") || senderID.equals("search")){
                mealId = getIntent().getStringExtra("mealId");
                presenter.getMealDetails(mealId);
            }
            else if (senderID.equals("fav")){
                Log.i("favvv","fav");
                mealId = getIntent().getStringExtra("mealId");
                presenter.getFavMealDetails(mealId);
            }
        }

    }

    @Override
    public void showMeal(Meal meal) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ingredientsRecyclerView.setLayoutManager(linearLayoutManager);
        MealAdapter mealAdapter = new MealAdapter(meal.getIngredients(), meal.getMeasures());
        ingredientsRecyclerView.setAdapter(mealAdapter);
        mealName.setText(meal.getStrMeal());
        mealInstructions.setText(meal.getStrInstructions());
        Glide.with(this).load(meal.getStrMealThumb()).into(mealImage);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        String youtubeUrl = meal.getStrYoutube();
        if (youtubeUrl != null && !youtubeUrl.isEmpty()) {
            String videoId = extractYoutubeVideoId(youtubeUrl);
            if (videoId != null) {
                String html = "<html><body style='margin:0;padding:0;'><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
                webView.loadData(html, "text/html", "utf-8");
            } else {
                webView.setVisibility(View.GONE);
            }
        } else {
            webView.setVisibility(View.GONE);
        }

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveMealToFav(meal);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        saveImage(meal);
                    }
                }).start();
            }
        });
    }

    @Override
    public void showFavMeal(Meal meal,Bitmap bitmap){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("favvv","shoFavMeal");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MealActivity.this);
                ingredientsRecyclerView.setLayoutManager(linearLayoutManager);
                MealAdapter mealAdapter = new MealAdapter(meal.getIngredients(), meal.getMeasures());
                ingredientsRecyclerView.setAdapter(mealAdapter);
                mealName.setText(meal.getStrMeal());
                mealInstructions.setText(meal.getStrInstructions());
                Glide.with(MealActivity.this).asBitmap().load(bitmap).into(mealImage);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setUseWideViewPort(true);

                String youtubeUrl = meal.getStrYoutube();
                if (youtubeUrl != null && !youtubeUrl.isEmpty()) {
                    String videoId = extractYoutubeVideoId(youtubeUrl);
                    if (videoId != null) {
                        String html = "<html><body style='margin:0;padding:0;'><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
                        webView.loadData(html, "text/html", "utf-8");
                    } else {
                        webView.setVisibility(View.GONE);
                    }
                } else {
                    webView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private String extractYoutubeVideoId(String url) {
        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\\?video_id=|\\?v=|\\&v=|youtu.be\\/|watch\\?v=|\\/v\\/|e\\/|watch\\?v=|\\?v=|\\&v=)([^#\\&\\?\\n]*)";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }

    public void saveImage(Meal meal){
        String imageUrl = meal.getStrMealThumb();
        File images = new File(getFilesDir(), "images");
        if (!images.exists()) {
            images.mkdirs();
        }
        File imageFile = new File(images, meal.getStrMeal().replace(' ','_') + ".png");
        if (imageFile.exists()) {
            return;
        }
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            FileOutputStream out = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFilesDir() {
        return getApplicationContext().getFilesDir();
    }
}