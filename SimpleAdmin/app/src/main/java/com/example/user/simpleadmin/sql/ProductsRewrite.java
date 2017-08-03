package com.example.user.simpleadmin.sql;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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

public class ProductsRewrite extends Activity {

    private Button btnOkRewrite;
    private EditText etRewProductsId, etRewProductsName, etRewProductsPrice;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_rewrite);

        initUI();

        dbHelper = new DBHelper(this);

        btnOkRewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                String idProduct = etRewProductsId.getText().toString();
                int id = 0;
                Cursor cursor = null;
                cursor = database.query("PRODUCTS", null, null, null, null, null, null);
                cursor.moveToLast();
                int idColIndex = cursor.getColumnIndex("id_products");
                id = cursor.getInt(idColIndex);

                int idProductInt = Integer.parseInt(idProduct);
                if (id >= idProductInt && idProductInt >= 0){
                    cv.put("products_name", etRewProductsName.getText().toString());
                    cv.put("price", Double.parseDouble(etRewProductsPrice.getText().toString()));

                    database.update("PRODUCTS", cv, "id_products = ?", new String[] {idProduct});
                    Toast.makeText(ProductsRewrite.this, "product with id = " + idProduct + " updated", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }else {
                    Toast.makeText(ProductsRewrite.this, "wrong id", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }
            }
        });
    }

    private void initUI(){
        btnOkRewrite = (Button) findViewById(R.id.btnOkRewrite);
        etRewProductsId = (EditText) findViewById(R.id.etRewProductsId);
        etRewProductsName = (EditText) findViewById(R.id.etRewProductsName);
        etRewProductsPrice = (EditText) findViewById(R.id.etRewProductsPrice);
    }
}
