package com.example.user.starbuzzcoffee.drink;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.starbuzzcoffee.R;
import com.example.user.starbuzzcoffee.drink.Drink;

/**
 * Created by User on 05.07.2017.
 */

public class DrinkActivity extends Activity {
    public static final String EXTRA_DRINKNO = "drinkNO";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_activity);

        int drinkNO = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);
        Drink drink = Drink.drinks[drinkNO];

        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(drink.getResId());
        photo.setContentDescription(drink.getDescription());

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(drink.getName());

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(drink.getDescription());

    }
}
