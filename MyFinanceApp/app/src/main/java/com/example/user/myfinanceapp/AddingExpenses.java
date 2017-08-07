package com.example.user.myfinanceapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by User on 05.08.2017.
 */

public class AddingExpenses extends Activity {

    private Button btnOkAdd;
    private EditText etCost, etDate;
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private TextView category;

    private static final int INTENT_CONST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding);

        initUI();
        Intent intent = getIntent();
        String spinnerItem = intent.getStringExtra("spinnerItem");
        category.setText(spinnerItem);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddingExpenses.this, Picker.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, INTENT_CONST);
            }
        });

            btnOkAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!etDate.getText().equals("") && !etCost.getText().equals("")){

                        ContentValues cv = new ContentValues();
                        database = dbHelper.getWritableDatabase();
                        cv.put("cost", etCost.getText().toString());
                        cv.put("data", etDate.getText().toString());
                        cv.put("category", category.getText().toString());
                        database.insert("EXPENSES", null, cv);
                        Toast.makeText(AddingExpenses.this, "Adding!", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(AddingExpenses.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);
                    } else {
                        Toast.makeText(AddingExpenses.this, "fill in all the fields", Toast.LENGTH_SHORT).show();
                    }

                }
            });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        String date = data.getStringExtra("date");
        etDate.setText(date);
    }

    private void initUI() {
        dbHelper = new DBHelper(this);
        btnOkAdd = (Button) findViewById(R.id.btnOkAdd);
        etCost = (EditText) findViewById(R.id.etCost);
        etDate = (EditText) findViewById(R.id.etDate);
        category = (TextView) findViewById(R.id.categoryTV);
    }
}
