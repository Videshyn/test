package com.example.optimizationlistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button ok, cancel;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ok = (Button) findViewById(R.id.ok);
        cancel = (Button) findViewById(R.id.cancel);
        text = (TextView) findViewById(R.id.text);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ok:
                        text.setText("Click button Ok");
                        break;
                    case R.id.cancel:
                        text.setText("Click button cancel");
                        break;
                }
            }
        };

        ok.setOnClickListener(onClick);
        cancel.setOnClickListener(onClick);
    }
}
