package com.example.beer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view){
        TextView textView = (TextView) findViewById(R.id.textView);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        String beerType = String.valueOf(spinner.getSelectedItem());
        BeerExpert expert = new BeerExpert();
        ArrayList<String> list = expert.getBeers(beerType);
        StringBuilder sb = new StringBuilder("");
        for (String l : list){
            sb.append(l + "\n");
        }
        textView.setText(sb);
    }
}
