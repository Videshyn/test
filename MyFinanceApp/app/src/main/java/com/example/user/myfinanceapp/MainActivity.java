package com.example.user.myfinanceapp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    private Button btnPlus, btnMinus;
    private TextView tvBalance, tvExpenses, tvRemainder;
    private Spinner categoriesSpinner;


    private List<String> defaultCategories;

    private static final String YOUR_BALANCE = "balance 0.0";
    private static final String YOUR_EXPENSES = "expenses 0.0";
    private static final String YOUR_REMAINDER = "remainder 0.0";

    private final Context context = this;
    private double defaultBalance = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        fillSpinner();
        addDefaultBalance();
        setBalance();
        setExpenses();
        setRemainder();
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);

    }


    private void addDefaultBalance(){
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("BALANCE", null, null, null, null, null, null);
        if (cursor.getCount() == 0){
            ContentValues cv = new ContentValues();
            cv.put("balance_count", defaultBalance);
            database.insert("BALANCE", null, cv);
        }
        cursor.close();
        dbHelper.close();
    }

    @Override
    protected void onRestart() {
        setBalance();
        setExpenses();
        setRemainder();
        fillSpinner();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        setBalance();
        setExpenses();
        setRemainder();
        fillSpinner();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setBalance(){
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("BALANCE", null, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToLast();
            int balanceColumnIndex = cursor.getColumnIndex("balance_count");
            double balanceNow = cursor.getDouble(balanceColumnIndex);
            tvBalance.setText("balance= " + balanceNow);
        }else tvBalance.setText(YOUR_BALANCE);
        database.close();
        cursor.close();
    }

    private void setExpenses(){
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("EXPENSES", null, null, null, null, null, null);
        double expensesAll = 0.0;
        if (cursor != null){
            if (cursor.moveToFirst()){
                int costColumnIndex = cursor.getColumnIndex("cost");
                while (cursor.moveToNext()){
                    expensesAll = expensesAll + cursor.getDouble(costColumnIndex);
                }
            }
            tvExpenses.setText("expenses= " + expensesAll);
        }else tvExpenses.setText(YOUR_EXPENSES);
        database.close();
        cursor.close();
    }

    private void setRemainder(){
        String strBalance = tvBalance.getText().toString();
        String arrBalance[] = strBalance.split(" ");
        double balance = Double.parseDouble(arrBalance[1]);
        String strExpenses = tvExpenses.getText().toString();
        String arrExpenses[] = strExpenses.split(" ");
        double expenses = Double.parseDouble(arrExpenses[1]);
        double remainderAll = balance - expenses;
        if (remainderAll < 0){
            tvRemainder.setTextColor(Color.rgb(255, 0, 0));
        }else tvRemainder.setTextColor(Color.rgb(0, 255, 0));
        tvRemainder.setText("remainder= " + remainderAll);
    }

    private void initUI() {
        dbHelper = new DBHelper(this);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        tvBalance = (TextView) findViewById(R.id.tvBalance);
        tvExpenses = (TextView) findViewById(R.id.tvExpenses);
        tvRemainder = (TextView) findViewById(R.id.tvRemainder);
        categoriesSpinner = (Spinner) findViewById(R.id.spinner_categories);

    }

    private void addCategory(){

            LayoutInflater li = LayoutInflater.from(context);
            View dialogView = li.inflate(R.layout.category_dialog, null);
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
            mDialogBuilder.setView(dialogView);
            final EditText userInput = (EditText) dialogView.findViewById(R.id.input_text);
            mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    String input = userInput.getText().toString();
                                    database = dbHelper.getWritableDatabase();
                                    ContentValues cv = new ContentValues();
                                    cv.put("category_name", input);
                                    database.insert("CATEGORIES", null, cv);
                                    defaultCategories.add(input);
                                    database.close();
                                }
                            });
            AlertDialog alertDialog = mDialogBuilder.create();

            alertDialog.show();

    }





    private void fillSpinner(){
        defaultCategories = new ArrayList<>();
        defaultCategories.add("Food");
        defaultCategories.add("Transport");
        defaultCategories.add("Entertainment");
        defaultCategories.add("Clothing");
        defaultCategories.add("Medicine");
        defaultCategories.add("Habitation");
        defaultCategories.add("Technique");

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("CATEGORIES", null, null, null, null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                int categoryColumnIndex = cursor.getColumnIndex("category_name");
                while (cursor.moveToNext()){
                    defaultCategories.add(cursor.getString(categoryColumnIndex));
                }
            }
        }
        database.close();
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row, R.id.row_spinner, defaultCategories);
        categoriesSpinner.setAdapter(adapter);

    }

    private void delCategory() {
        database = dbHelper.getWritableDatabase();
        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.category_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
        mDialogBuilder.setView(dialogView);
        final EditText userInput = (EditText) dialogView.findViewById(R.id.input_text);
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String input = userInput.getText().toString();
                                String sqlQuery = "DELETE FROM CATEGORIES WHERE category_name = ?;";
                                database.delete("CATEGORIES", "category_name=?", new String[]{input});
                                database.close();
                                onRestart();
                            }
                        });
        AlertDialog alertDialog = mDialogBuilder.create();

        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 0, 0, "add category");
        menu.add(0, 1, 1, "del category");
        menu.add(0, 2, 2, "statistics");
        menu.add(0, 3, 3, "exit");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()){
            case 0:
                addCategory();
                break;
            case 1:
                delCategory();
                break;
            case 2:
                intent = new Intent(this, Statistics.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case 3:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btnPlus:
                intent = new Intent(this, AddOrUpdateBalance.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btnMinus:
                intent = new Intent(this, AddingExpenses.class);
                String spinnerItem = categoriesSpinner.getSelectedItem().toString();
                intent.putExtra("spinnerItem", spinnerItem);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
