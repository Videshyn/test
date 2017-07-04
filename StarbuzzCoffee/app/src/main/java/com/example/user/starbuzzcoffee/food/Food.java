package com.example.user.starbuzzcoffee.food;

import com.example.user.starbuzzcoffee.R;

/**
 * Created by User on 05.07.2017.
 */

public class Food {
    private String foodName;
    private String foodDescriptions;
    private int foodPhotoId;

    public final static Food[] food = {
            new Food("Pasta", "Good pasta from Italy", R.drawable.pasta),
            new Food("Borshch", "The best food ever from Russia", R.drawable.borshch),
            new Food("Kebab", "Kebab - the most delicious dish which can be cooked from meat", R.drawable.kebab)
    };

    public Food(String foodName, String foodDescriptions, int foodPhotoId) {
        this.foodName = foodName;
        this.foodDescriptions = foodDescriptions;
        this.foodPhotoId = foodPhotoId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescriptions() {
        return foodDescriptions;
    }

    public void setFoodDescriptions(String foodDescriptions) {
        this.foodDescriptions = foodDescriptions;
    }

    public int getFoodPhotoId() {
        return foodPhotoId;
    }

    public void setFoodPhotoId(int foodPhotoId) {
        this.foodPhotoId = foodPhotoId;
    }

    @Override
    public String toString() {
        return this.foodName;
    }
}
