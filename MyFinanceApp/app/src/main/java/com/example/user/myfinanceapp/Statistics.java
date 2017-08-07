package com.example.user.myfinanceapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.08.2017.
 */

public class Statistics extends Activity {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ListView lwExpenses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        initUI();
        seeExpenses();
    }

    private void initUI(){
        lwExpenses = (ListView) findViewById(R.id.allExpenses);
        dbHelper = new DBHelper(this);
    }


    private void seeExpenses() {

        database = dbHelper.getWritableDatabase();
        List<String> expensesList = new ArrayList<>();
        Cursor cursor = database.query("EXPENSES", null, null, null, null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                int categoryColumnIndex = cursor.getColumnIndex("category");
                int costColumnIndex = cursor.getColumnIndex("cost");
                int dataColumnIndex = cursor.getColumnIndex("data");
                while (cursor.moveToNext()){
                    expensesList.add(cursor.getString(categoryColumnIndex) + " " +
                            cursor.getDouble(costColumnIndex) + " " +
                            cursor.getString(dataColumnIndex));
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, expensesList);
            lwExpenses.setAdapter(adapter);
            database.close();
        }

    }
}
