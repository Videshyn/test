package com.example.user.myfinanceapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 05.08.2017.
 */

public class AddOrUpdateBalance extends Activity implements View.OnClickListener{

    private Button btnAdd, btnUpdate;
    private DBHelper dbHelper;
    private EditText etAddBalance;
    private SQLiteDatabase database;
    //private double defaultBalance = 0.0;

    // при вызове активити в БД заносится значение 0.0 в поле баланс таблицы БАЛАНС, при нажатии
    // кнопки add выполняется проверка пкстая таблица или нет, если нет вытягиваются все начения суммируются с
    // введенным и переписывается первая строка таблицы. ТАК КАК ПЛАНИРУЕТСЯ ЧТО ЗАПИСЬ В ТАБЛИЦЕ ВСЕГДА БУДЕТ ОДНА.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_balance);

        initUI();
        // add 0.0 if DB is empty
        //addDefaultBalance();

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

    }

    private void initUI() {
        btnAdd = (Button) findViewById(R.id.btnAddBalance);
        btnUpdate = (Button) findViewById(R.id.btnUpdateBalance);
        etAddBalance = (EditText) findViewById(R.id.etAddBalance);
        dbHelper = new DBHelper(this);

    }

//    private void addDefaultBalance(){
//        database = dbHelper.getWritableDatabase();
//        Cursor cursor = database.query("BALANCE", null, null, null, null, null, null);
//        if (cursor.getCount() == 0){
//            ContentValues cv = new ContentValues();
//            cv.put("balance_count", defaultBalance);
//            database.insert("BALANCE", null, cv);
//        }
//        cursor.close();
//        dbHelper.close();
//    }

    @Override
    public void onClick(View view) {
        Intent intent;
        database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor = null;
        if (!"".equals(etAddBalance.getText().toString())) {


        //

            switch (view.getId()){
                case R.id.btnAddBalance:
                    cursor = database.query("BALANCE", null,  null, null, null, null, null);
                    if (cursor != null){
                        double balanceAll = 0.0;
                        cursor.moveToLast();
                        int balanceColumnIndex = cursor.getColumnIndex("balance_count");
                        balanceAll = cursor.getDouble(balanceColumnIndex);
                        balanceAll += Double.parseDouble(etAddBalance.getText().toString());
                        cv.put("balance_count", balanceAll);
                        database.update("BALANCE", cv, "id_balance = ?", new String[] {"1"});
                        intent = new Intent(this, MainActivity.class);
                        intent.putExtra("balanceAll", balanceAll);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    break;
                case R.id.btnUpdateBalance:
                    double balanceAll = Double.parseDouble(etAddBalance.getText().toString());
                    cv.put("balance_count", balanceAll);
                    database.update("BALANCE", cv, "id_balance = ?", new String[] {"1"});
                    intent = new Intent(this, MainActivity.class);
                    intent.putExtra("balanceAll", balanceAll);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
            }

        //

        }else {
            Toast.makeText(this, "Enter balance:", Toast.LENGTH_SHORT).show();
        }

    }
}
