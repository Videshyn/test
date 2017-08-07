package com.example.user.myfinanceapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 04.08.2017.
 */



public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FinDB";
    private static final int DB_VERSION = 4;

    private final String CREATE_EXPENSES = "create table EXPENSES (id" +
            " integer primary key autoincrement, " +
            "cost number, data text, category text);";
    private final String CREATE_BALANCE = "create table BALANCE (id_balance integer primary key autoincrement, " +
            "balance_count number);";
    private final String CREATE_CATEGORIES = "create table CATEGORIES (id_category integer primary key " +
            "autoincrement, category_name text);";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EXPENSES);
        sqLiteDatabase.execSQL(CREATE_BALANCE);
        sqLiteDatabase.execSQL(CREATE_CATEGORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS EXPENSES");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BALANCE");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CATEGORIES");
        onCreate(sqLiteDatabase);
    }
}
