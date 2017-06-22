package com.example.contextmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textColor, textSize;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textColor = (TextView) findViewById(R.id.text1);
        textSize = (TextView) findViewById(R.id.text2);

        registerForContextMenu(textColor);
        registerForContextMenu(textSize);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()){
            case R.id.text1:
                menu.add(0, MENU_COLOR_RED, 0, "Red");
                menu.add(0, MENU_COLOR_GREEN, 0, "Green");
                menu.add(0, MENU_COLOR_BLUE, 0, "Blue");
                break;
            case R.id.text2:
                menu.add(0, MENU_SIZE_22, 0, "22");
                menu.add(0, MENU_SIZE_26, 0, "26");
                menu.add(0, MENU_SIZE_30, 0, "30");
                break;
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_COLOR_RED:
                textColor.setTextColor(Color.RED);
                textColor.setText("color = red");
                break;
            case MENU_COLOR_GREEN:
                textColor.setTextColor(Color.GREEN);
                textColor.setText("color = green");
                break;
            case MENU_COLOR_BLUE:
                textColor.setTextColor(Color.BLUE);
                textColor.setText("color = blue");
                break;
            case MENU_SIZE_22:
                textSize.setTextSize(22);
                textSize.setText("text size = 22");
                break;
            case MENU_SIZE_26:
                textSize.setTextSize(26);
                textSize.setText("text size = 26");
                break;
            case MENU_SIZE_30:
                textSize.setTextSize(30);
                textSize.setText("text size = 30");
                break;
        }
        return super.onContextItemSelected(item);
    }
}
