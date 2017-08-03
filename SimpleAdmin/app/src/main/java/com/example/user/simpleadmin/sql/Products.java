package com.example.user.simpleadmin.sql;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.simpleadmin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24.07.2017.
 */

public class Products extends Activity implements View.OnClickListener{

    private Button btnProductsSeeAll, btnProductsRewrite, btnProductsAdd;
    private ListView seeAllId;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

//    private String[] productNames = {"Nokia 3310", "Motorola c-115", "fdbbfb"};
//    private Double[] productPrices = {400.00, 330.00, 4546.00};
//
//    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);

        initUI();


//        db = dbHelper.getWritableDatabase();
//
//        Cursor cursor = db.query("PRODUCTS", null, null, null, null, null, null);
//        if(cursor == null){
//            ContentValues cv = new ContentValues();
//            for (int i = 0; i < 3; i ++){
//                cv.put("products_name", productNames[i]);
//                cv.put("price", productPrices[i]);
//                long row = db.insert("PRODUCTS", null, cv);
//                Log.d(LOG_TAG, "row inserted, ID = " + row);
//            }
//        }
//
//        cursor.close();
//        dbHelper.close();

        btnProductsAdd.setOnClickListener(this);
        btnProductsSeeAll.setOnClickListener(this);
        btnProductsRewrite.setOnClickListener(this);
    }

    private void initUI(){
        dbHelper = new DBHelper(this);
        btnProductsAdd = (Button) findViewById(R.id.btn_products_add);
        btnProductsSeeAll = (Button) findViewById(R.id.btn_products_see_all);
        btnProductsRewrite = (Button) findViewById(R.id.btn_products_rewrite);
        seeAllId = (ListView) findViewById(R.id.products_id_list_view);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (view.getId()){
            case R.id.btn_products_add:
                intent = new Intent(Products.this, ProductsAdd.class);
                startActivity(intent);
                break;
            case R.id.btn_products_rewrite:
                intent = new Intent(Products.this, ProductsRewrite.class);
                startActivity(intent);
                break;
            case R.id.btn_products_see_all:
                cursor = db.query("PRODUCTS", null, null, null, null, null, null);
                break;
        }
        List<String> productsList = new ArrayList<>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idColIndex = cursor.getColumnIndex("id_products");
                int nameColIndex = cursor.getColumnIndex("products_name");
                int priceColIndex = cursor.getColumnIndex("price");
                do {
                    productsList.add("id = " + cursor.getInt(idColIndex)
                            + " name = " + cursor.getString(nameColIndex)
                            + " price = " + cursor.getString(priceColIndex) + "$");
                } while (cursor.moveToNext());

            }
            cursor.close();
    }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productsList);
        seeAllId.setAdapter(adapter);
        dbHelper.close();
    }
}
