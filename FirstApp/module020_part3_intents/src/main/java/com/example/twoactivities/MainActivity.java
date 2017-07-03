package com.example.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button send;
    EditText edit;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button) findViewById(R.id.btn);
        edit = (EditText) findViewById(R.id.edText);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ReceiveMessageActivity.class);
//                intent.putExtra(ReceiveMessageActivity.MESSAGE, edit.getText().toString());

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
                Intent chIntent = Intent.createChooser(intent, "message ....");
                startActivity(chIntent);

            }
        });


    }


}
