package com.example.viewbyld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView myView = (TextView) findViewById(R.id.text);
        myView.setText("some new text");
        Button btn = (Button) findViewById(R.id.btn);
        btn.setText("new text in button");
        btn.setEnabled(false);
        CheckBox chb = (CheckBox) findViewById(R.id.checkbox);
        chb.setChecked(true);
    }
}
