package com.example.user.simpleadmin.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 24.07.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final String PRODUCTS_CREATE = "create table PRODUCTS (id_products" +
            " integer primary key autoincrement, " +
            "products_name text, price number);";

    private final String CLIENTS_CREATE = "create table CLIENTS (id_clients" +
            " integer primary key autoincrement, " +
            "clients_name text, phone number);";


    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CLIENTS_CREATE);
        sqLiteDatabase.execSQL(PRODUCTS_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
