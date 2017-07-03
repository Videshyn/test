package com.example.user.firstapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(linearLayout, layoutParams);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(this);
        textView.setText("some text");
        textView.setLayoutParams(params);
        linearLayout.addView(textView);

        Button btn = new Button(this);
        btn.setText("Click");
        //btn.setLayoutParams(params);
        linearLayout.addView(btn, params);
        LinearLayout.LayoutParams marginParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        marginParam.topMargin = 50;
        marginParam.gravity = Gravity.CENTER;

        Button btn2 = new Button(this);
        btn2.setText("Button2");
        linearLayout.addView(btn2, marginParam);

        Button btn3 = new Button(this);
        btn3.setText("Button3");
        LinearLayout.LayoutParams rightMargin = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rightMargin.gravity = Gravity.RIGHT;
        linearLayout.addView(btn3, rightMargin);

    }
}
