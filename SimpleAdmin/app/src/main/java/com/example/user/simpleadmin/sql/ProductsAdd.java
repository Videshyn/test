package com.example.user.simpleadmin.sql;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.simpleadmin.R;

/**
 * Created by User on 24.07.2017.
 */

public class ProductsAdd extends Activity {

    private Button btnAddProducts;
    private EditText editProductsName, editProductsPrice;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_add);

        initUI();
        dbHelper = new DBHelper(this);

        btnAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                cv.put("products_name", editProductsName.getText().toString());
                Double productPrice = Double.parseDouble(editProductsPrice.getText().toString());
                cv.put("price",productPrice);
                long index = database.insert("PRODUCTS", null, cv);
                Toast.makeText(ProductsAdd.this, index + " insert", Toast.LENGTH_SHORT).show();
                editProductsPrice.setText("");
                editProductsName.setText("");
            }
        });

    }

    private void initUI(){
        btnAddProducts = (Button) findViewById(R.id.btnAddProducts);
        editProductsName = (EditText) findViewById(R.id.etAddProductsName);
        editProductsPrice = (EditText) findViewById(R.id.etAddProductsPrice);
    }
}
