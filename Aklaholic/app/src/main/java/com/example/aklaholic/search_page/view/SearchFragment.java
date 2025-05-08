package com.example.aklaholic.search_page.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.aklaholic.R;
import com.example.aklaholic.main.view.MainComm;
import com.example.aklaholic.model.local.MealLocalDataSource;
import com.example.aklaholic.model.pojo.Category;
import com.example.aklaholic.model.pojo.Country;

import com.example.aklaholic.model.pojo.Ingredient;
import com.example.aklaholic.model.pojo.Meal;
import com.example.aklaholic.model.remote.MealRemoteDataSource;
import com.example.aklaholic.model.remote.MealRemoteDataSourceImpl;
import com.example.aklaholic.model.repo.MealRepository;
import com.example.aklaholic.search_page.presenter.SearchPresenter;
import com.google.android.material.search.SearchView;

import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment implements ISearchView, OnCountryClickListener {

    SearchView searchBar;
    Button categoryButton;
    Button ingredientButton;
    Button countryButton;
    RecyclerView recyclerView;

    SearchPresenter presenter;

    List<Country> countries;

    MainComm mainComm;

    public void setupCountries() {
        countries = Arrays.asList(
                new Country("Egyptian", R.drawable.egypt_large),
                new Country("American", R.drawable.america_large),
                new Country("British", R.drawable.british_large),
                new Country("Canadian", R.drawable.canada_large),
                new Country("Chinese", R.drawable.china_large),
                new Country("Croatian", R.drawable.croatia_large),
                new Country("Dutch", R.drawable.netherlands_large),
                new Country("Filipino", R.drawable.phillippines_large),
                new Country("French", R.drawable.france_large),
                new Country("Greek", R.drawable.greece_large),
                new Country("Indian", R.drawable.india_large),
                new Country("Irish", R.drawable.ireland_large),
                new Country("Italian", R.drawable.italy_large),
                new Country("Jamaican", R.drawable.jamaica_large),
                new Country("Japanese", R.drawable.japan_large),
                new Country("Kenyan", R.drawable.kenya_large),
                new Country("Malaysian", R.drawable.malaysia_large),
                new Country("Mexican", R.drawable.mexico_large),
                new Country("Moroccan", R.drawable.morocco_large),
                new Country("Polish", R.drawable.poland_large),
                new Country("Portuguese", R.drawable.portugal_large),
                new Country("Russian", R.drawable.russia_large),
                new Country("Spanish", R.drawable.spain_large),
                new Country("Thai", R.drawable.thailand_large),
                new Country("Tunisian", R.drawable.tunisia_large),
                new Country("Turkish", R.drawable.turkey_large),
                new Country("Ukrainian", R.drawable.ukraine_flag),
                new Country("Uruguayan", R.drawable.uruguay_large),
                new Country("Vietnamese", R.drawable.vietnam_large)
        );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //searchBar = view.findViewById(R.id.search_bar);
        categoryButton = view.findViewById(R.id.btn_categories);
        ingredientButton = view.findViewById(R.id.btn_ingredients);
        countryButton = view.findViewById(R.id.btn_countries);
        recyclerView = view.findViewById(R.id.rv_search);
        setupCountries();
        MealRepository mealRepository = MealRepository.getInstance(
                MealLocalDataSource.getInstance(this.getContext()),
                MealRemoteDataSourceImpl.getInstance()
        );
        mainComm = (MainComm) getActivity();
        presenter = new SearchPresenter(this, mealRepository);

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAllCategories();
            }
        });

        countryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCountries(countries);
            }
        });

        ingredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAllIngredients();
            }
        });
    }

    @Override
    public void onCountryClick(Country country) {

    }

    @Override
    public void showCategories(List<Category> categories) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                SearchFragment.this.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        recyclerView.setLayoutManager(linearLayoutManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                presenter.getMealsByCategory(category.getStrCategory());
            }
        });
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                SearchFragment.this.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        recyclerView.setLayoutManager(linearLayoutManager);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredients, new IngredientsAdapter.OnIngredientClickListener() {
            @Override
            public void onIngredientClick(Ingredient ingredient) {
                presenter.getMealsByIngredient(ingredient.getStrIngredient());
            }
        });
        recyclerView.setAdapter(ingredientsAdapter);
    }
    @Override
    public void showCountries(List<Country> countries) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                SearchFragment.this.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        recyclerView.setLayoutManager(linearLayoutManager);
        CountryAdapter countryAdapter = new CountryAdapter(countries, new OnCountryClickListener() {
            @Override
            public void onCountryClick(Country country) {
                presenter.getMealsByCountry(country.getCountryName());
            }
        });
        recyclerView.setAdapter(countryAdapter);
    }

    @Override
    public void showMeals(List<Meal> meals) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                SearchFragment.this.getContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
        );
        recyclerView.setLayoutManager(gridLayoutManager);
        SearchMealAdapter searchMealAdapter = new SearchMealAdapter(meals, new OnMealClick() {
            @Override
            public void onMealClick(String mealId) {
                mainComm.showMealFromSearch(mealId);
            }
        });
        recyclerView.setAdapter(searchMealAdapter);

    }
}