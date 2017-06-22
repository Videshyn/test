package com.example.buttonlistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button btn1;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        View.OnClickListener onOk = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Button Ok clicked");
            }
        };

        btn1.setOnClickListener(onOk);

        View.OnClickListener onCancel = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Button Cancel clicked");
            }
        };
        btn2.setOnClickListener(onCancel);

    }
}
