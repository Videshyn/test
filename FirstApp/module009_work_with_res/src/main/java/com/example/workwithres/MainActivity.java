package com.example.workwithres;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    TextView text;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn2);
        text = (TextView) findViewById(R.id.text2);
        ll = (LinearLayout) findViewById(R.id.llBottom);

        btn1.setText(R.string.btn2);
        text.setText(R.string.text2);
        ll.setBackgroundResource(R.color.llBottomColor);
    }
}
