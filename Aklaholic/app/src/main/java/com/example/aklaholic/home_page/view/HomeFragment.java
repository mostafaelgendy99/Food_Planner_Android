package com.example.aklaholic.home_page.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aklaholic.R;
import com.example.aklaholic.search_page.view.SearchFragment;
import com.example.aklaholic.home_page.presenter.IPresenter;
import com.example.aklaholic.home_page.presenter.Presenter;
import com.example.aklaholic.main.view.MainComm;
import com.example.aklaholic.model.pojo.Meal;
import com.google.android.material.search.SearchBar;

import java.util.List;


public class HomeFragment extends Fragment implements IView {


    private IPresenter presenter;
    private ImageView imgRandomMeal;
    private TextView txtRandomMeal;
    private String randomMealId;
    private MainComm mainComm;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainComm = (MainComm) getActivity();

        recyclerView = view.findViewById(R.id.recycler_users_choice);
        imgRandomMeal = view.findViewById(R.id.image_random_meal);
        txtRandomMeal = view.findViewById(R.id.text_random_meal);

        presenter = new Presenter(this);
        presenter.getRandomMeal();
        presenter.getRandomMeals();

        SearchBar searchBar = view.findViewById(R.id.searchView);
        searchBar.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, new SearchFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public void showRandomMeal(Meal meal) {
        txtRandomMeal.setText(meal.getStrMeal());
        randomMealId = meal.getIdMeal();
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .into(imgRandomMeal);

        imgRandomMeal.setOnClickListener(v -> {
            mainComm.showMealFromHome(meal.getIdMeal());
        });
    }

    @Override
    public void showRandomMeals(List<Meal> meals) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RandomMealsAdapter randomMealsAdapter = new RandomMealsAdapter(meals, new OnMealClickHome() {
            @Override
            public void onMealClick(String mealId) {
                mainComm.showMealFromHome(mealId);
            }
        });

        recyclerView.setAdapter(randomMealsAdapter);
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(requireContext(), "Error: " + errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
    }
}