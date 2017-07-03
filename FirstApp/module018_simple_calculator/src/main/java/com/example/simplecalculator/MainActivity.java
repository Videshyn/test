package com.example.simplecalculator;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    EditText editText1;
    EditText editText2;
    Button sub, plus, mul, div;
    TextView textView;
    String oper = "";

    final int ITEM1 = 1;
    final int ITEM2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editLeft);
        editText2 = (EditText) findViewById(R.id.editRight);

        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mult);
        div = (Button) findViewById(R.id.div);
        plus = (Button) findViewById(R.id.plus);

        textView = (TextView) findViewById(R.id.text);

        sub.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        plus.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        float num1 = 0;
        float num2 = 0;
        float res = 0;
        //Проверяем поля на пустоту
        if (TextUtils.isEmpty(editText1.getText().toString()) ||
                TextUtils.isEmpty(editText2.getText().toString())){
            return;
        }

        num1 = Float.parseFloat(editText1.getText().toString());
        num2 = Float.parseFloat(editText2.getText().toString());

        switch (view.getId()){
            case R.id.sub:
                oper = "-";
                res = num1 - num2;
                printRes(num1, num2, res, oper);
                break;
            case R.id.plus:
                oper = "+";
                res = num1 + num2;
                printRes(num1, num2, res, oper);
                break;
            case R.id.div:
                oper = "/";
                if (num2 == 0){
                    Toast.makeText(this, "Делить на 0 нельзя", Toast.LENGTH_SHORT).show();
                    editText1.setText("");
                    editText2.setText("");
                }else {
                    res = num1 / num2;
                    printRes(num1, num2, res, oper);
                }
                break;
            case R.id.mult:
                oper = "*";
                res = num1 * num2;
                printRes(num1, num2, res, oper);
                break;
        }




    }

    public void printRes(float num1, float num2, float res, String oper){
        textView.setText(num1 + " " + oper + " " + num2 + " = " + res);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ITEM1, 1, "Reset");
        menu.add(0, ITEM2, 2, "Exit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case ITEM1:
                editText1.setText("");
                editText2.setText("");
                textView.setText("");
                break;
            case ITEM2:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
