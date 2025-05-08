package com.example.aklaholic.model.pojo;
import androidx.room.Entity;
import androidx.annotation.NonNull;

@Entity(tableName = "favourite_meal")
public class FavouriteMeal extends Meal {
    public FavouriteMeal () {
        super();
    }

    public FavouriteMeal(@NonNull Meal meal) {
        super();

        setIdMeal(meal.getIdMeal());
        setStrMeal(meal.getStrMeal());
        setStrMealAlternate(meal.getStrMealAlternate());
        setStrCategory(meal.getStrCategory());
        setStrArea(meal.getStrArea());
        setStrInstructions(meal.getStrInstructions());
        setStrMealThumb(meal.getStrMealThumb());
        setStrTags(meal.getStrTags());
        setStrYoutube(meal.getStrYoutube());
        setStrSource(meal.getStrSource());
        setStrImageSource(meal.getStrImageSource());
        setStrCreativeCommonsConfirmed(meal.getStrCreativeCommonsConfirmed());
        setDateModified(meal.getDateModified());

        setStrIngredient1(meal.getStrIngredient1());
        setStrIngredient2(meal.getStrIngredient2());
        setStrIngredient3(meal.getStrIngredient3());
        setStrIngredient4(meal.getStrIngredient4());
        setStrIngredient5(meal.getStrIngredient5());
        setStrIngredient6(meal.getStrIngredient6());
        setStrIngredient7(meal.getStrIngredient7());
        setStrIngredient8(meal.getStrIngredient8());
        setStrIngredient9(meal.getStrIngredient9());
        setStrIngredient10(meal.getStrIngredient10());
        setStrIngredient11(meal.getStrIngredient11());
        setStrIngredient12(meal.getStrIngredient12());
        setStrIngredient13(meal.getStrIngredient13());
        setStrIngredient14(meal.getStrIngredient14());
        setStrIngredient15(meal.getStrIngredient15());
        setStrIngredient16(meal.getStrIngredient16());
        setStrIngredient17(meal.getStrIngredient17());
        setStrIngredient18(meal.getStrIngredient18());
        setStrIngredient19(meal.getStrIngredient19());
        setStrIngredient20(meal.getStrIngredient20());

        setStrMeasure1(meal.getStrMeasure1());
        setStrMeasure2(meal.getStrMeasure2());
        setStrMeasure3(meal.getStrMeasure3());
        setStrMeasure4(meal.getStrMeasure4());
        setStrMeasure5(meal.getStrMeasure5());
        setStrMeasure6(meal.getStrMeasure6());
        setStrMeasure7(meal.getStrMeasure7());
        setStrMeasure8(meal.getStrMeasure8());
        setStrMeasure9(meal.getStrMeasure9());
        setStrMeasure10(meal.getStrMeasure10());
        setStrMeasure11(meal.getStrMeasure11());
        setStrMeasure12(meal.getStrMeasure12());
        setStrMeasure13(meal.getStrMeasure13());
        setStrMeasure14(meal.getStrMeasure14());
        setStrMeasure15(meal.getStrMeasure15());
        setStrMeasure16(meal.getStrMeasure16());
        setStrMeasure17(meal.getStrMeasure17());
        setStrMeasure18(meal.getStrMeasure18());
        setStrMeasure19(meal.getStrMeasure19());
        setStrMeasure20(meal.getStrMeasure20());
    }
}

