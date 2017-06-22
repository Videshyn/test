package com.example.logstoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView text;
    Button btnOk, btnCan;
    final String TAG = "myLogs:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "search our views");

        btnOk = (Button) findViewById(R.id.btn1);
        btnCan = (Button) findViewById(R.id.btn2);
        text = (TextView) findViewById(R.id.text);

        Log.d(TAG, "get our views listener's");
        btnOk.setOnClickListener(this);
        btnCan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "find button");
        switch (view.getId()){
            case R.id.btn1:
                text.setText("Button Ok clicked");
                Log.d(TAG, "click Ok");
                Toast.makeText(this,"ok was clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn2:
                text.setText("Button Cancel clicked");
                Log.d(TAG, "click Cancel");
                Toast.makeText(this,"cancel was clicked", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
