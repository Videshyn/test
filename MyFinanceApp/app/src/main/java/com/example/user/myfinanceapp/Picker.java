package com.example.user.myfinanceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Created by User on 05.08.2017.
 */

public class Picker extends Activity {

    private DatePicker datePicker;
    private Button btnPickerOk;
    private static final int INTENT_CONST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picker);

        initUI();

        btnPickerOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int month = datePicker.getMonth() + 1;
                String monthStr = "";
                if (month < 10){
                    monthStr = "0" + month;
                }else monthStr = month + "";
                int day = datePicker.getDayOfMonth();
                String dayStr = "";
                if (day < 10){
                    dayStr = "0" + day;
                }else dayStr = day + "";
                String date = monthStr + ":" + dayStr + ":" + datePicker.getYear() ;
                Intent intent = new Intent();
                intent.putExtra("date", date);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initUI() {
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        btnPickerOk = (Button) findViewById(R.id.btn_picker_ok);
    }
}
