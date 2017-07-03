package com.example.controlelementsfromjava;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    EditText editText;
    Button clear, create;
    RadioGroup rgroup;
    LinearLayout line2;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    final String LOG_TAG = "MY_DEBUG_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "findViewById");
        line2 = (LinearLayout) findViewById(R.id.line2);
        editText = (EditText) findViewById(R.id.editText);
        rgroup = (RadioGroup) findViewById(R.id.rgroup);
        clear = (Button) findViewById(R.id.clear);
        create = (Button) findViewById(R.id.create);

        Log.d(LOG_TAG, "Button Listener");
        clear.setOnClickListener(this);
        create.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Log.d(LOG_TAG, "In button listener");
        switch (view.getId()){
            case R.id.create:
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(wrapContent, wrapContent);
                int btnGravity = Gravity.LEFT;
                switch (rgroup.getCheckedRadioButtonId()){
                    case R.id.rbtn1:
                        btnGravity = Gravity.LEFT;
                        break;
                    case R.id.rbtn2:
                        btnGravity = Gravity.CENTER;
                        break;
                    case R.id.rbtn3:
                        btnGravity = Gravity.RIGHT;
                        break;
                }
                params.gravity = btnGravity;
                Button btnNew = new Button(this);
                btnNew.setText(editText.getText().toString());
                line2.addView(btnNew, params);
                break;
            case R.id.clear:
                line2.removeAllViews();
                Toast.makeText(this, "Clear all elements!", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
