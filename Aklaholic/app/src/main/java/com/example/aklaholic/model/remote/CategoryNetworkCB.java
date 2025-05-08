package com.example.aklaholic.model.remote;

import com.example.aklaholic.model.pojo.Category;

import java.util.List;

public interface CategoryNetworkCB {
    public void onCategorySuccess(List<Category> categories);
    public void onFailure(String errorMessage);
}