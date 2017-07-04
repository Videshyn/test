package com.example.user.starbuzzcoffee.food;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.starbuzzcoffee.R;

/**
 * Created by User on 05.07.2017.
 */

public class FoodActivity extends Activity {
    public final static String EXTRA_FOODNO = "foodNO";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);

        int foodId = (Integer) getIntent().getExtras().get(EXTRA_FOODNO);
        Food food = Food.food[foodId];

        ImageView photo = (ImageView) findViewById(R.id.food_photo);
        photo.setImageResource(food.getFoodPhotoId());
        photo.setContentDescription(food.getFoodDescriptions());

        TextView foodName = (TextView) findViewById(R.id.food_name);
        foodName.setText(food.getFoodName());

        TextView foodDescription = (TextView) findViewById(R.id.food_description);
        foodDescription.setText(food.getFoodDescriptions());

    }
}
