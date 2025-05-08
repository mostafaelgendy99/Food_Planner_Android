package com.example.aklaholic.model.pojo;

public class Country {
    private String countryName;
    private int imageResourceId;
    public Country(String countryName, int imageResourceId) {
        this.countryName = countryName;
        this.imageResourceId = imageResourceId;
    }
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
