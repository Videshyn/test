package com.example.calculator;

import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// предусмотреть
// ввод более
// двух слогаемых
// до нажатия
// клавиши равно

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnC, btnDel, btnDiv;
    Button btnMult, btnPlus, btnSub, btnRes, btnPoint;
    TextView textView;
    String str = "";
    double num1, num2, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnRes = (Button) findViewById(R.id.btnRes);
        btnC = (Button) findViewById(R.id.btnC);
        btnPoint = (Button) findViewById(R.id.btnPoint);

        textView = (TextView) findViewById(R.id.text);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnRes.setOnClickListener(this);
        btnPoint.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "1";
                }else
                str = str + 1;
                textView.setText(str);
                break;
            case R.id.btn2:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "2";
                }else
                str = str + 2;
                textView.setText(str);
                break;
            case R.id.btn3:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "3";
                }else
                str = str + 3;
                textView.setText(str);
                break;
            case R.id.btn4:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "4";
                }else
                str = str + 4;
                textView.setText(str);
                break;
            case R.id.btn5:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "5";
                }else
                str = str + 5;
                textView.setText(str);
                break;
            case R.id.btn6:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "6";
                }else
                str = str + 6;
                textView.setText(str);
                break;
            case R.id.btn7:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "7";
                }else
                str = str + 7;
                textView.setText(str);
                break;
            case R.id.btn8:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "8";
                }else
                str = str + 8;
                textView.setText(str);
                break;
            case R.id.btn9:
                str = textView.getText().toString();
                if (str.equals("0")){
                    str = "9";
                }else
                str = str + 9;
                textView.setText(str);
                break;
            case R.id.btn0:
                if (textView.getText().equals("0")){
                    str = textView.getText().toString();
                }else {
                    str = str + 0;
                    textView.setText(str);
                }
                break;
            case R.id.btnPoint:
                if (textView.getText().toString().contains(".")){
                    Toast.makeText(this, "point is already in use", Toast.LENGTH_SHORT).show();
                }else{
                    str = textView.getText().toString() + ".";
                    textView.setText(str);
                }
                if (str.length() == 0){
                    str = "0";
                }
                break;
            case R.id.btnC:
                str = "0";
                textView.setText(str);
                break;
            case R.id.btnDel:
                String tmp = textView.getText().toString();
                int size = tmp.length();
                if (size <= 1){
                    tmp = "0";
                    textView.setText(tmp);
                }else textView.setText(tmp.substring(0, size - 1));
                break;
            case R.id.btnSub:
                str = textView.getText().toString();
                //textView.setText(getRes(str));
                getRes(str, R.id.btnSub);
                break;
            case R.id.btnPlus:
                //str = textView.getText().toString() + "+";
                str = textView.getText().toString();
                getRes(str, R.id.btnPlus);
                //textView.setText(str);
                break;
            case R.id.btnDiv:
//                str = textView.getText().toString() + "÷";
//                textView.setText(str);
                str = textView.getText().toString();
                getRes(str, R.id.btnDiv);
                break;
            case R.id.btnMult:
//                str = textView.getText().toString() + "*";
//                textView.setText(str);
                str = textView.getText().toString();
                getRes(str, R.id.btnMult);
                break;
            case R.id.btnRes:
                str = textView.getText().toString();
                if (str.contains("-")){
                    String[] arr = str.split("-");
                    num1 = Double.parseDouble(arr[0]);
                    num2 = Double.parseDouble(arr[1]);
                    res = num1 - num2;
                }
                if (str.contains("+")){
                    String[] arr = str.split("\\+");
                    num1 = Double.parseDouble(arr[0]);
                    num2 = Double.parseDouble(arr[1]);
                    res = num1 + num2;
                }
                if (str.contains("÷")){
                    String[] arr = str.split("÷");
                    num1 = Double.parseDouble(arr[0]);
                    num2 = Double.parseDouble(arr[1]);
                    if (num2 == 0){
                        Toast.makeText(this, "on 0 you can not divide", Toast.LENGTH_SHORT).show();
                        res = 0;
                    }else res = num1 / num2;
                }
                if (str.contains("*")){
                    String[] arr = str.split("\\*");
                    num1 = Double.parseDouble(arr[0]);
                    num2 = Double.parseDouble(arr[1]);
                    res = num1 * num2;
                }
                textView.setText(res + "");
                break;
        }

    }

    public void getRes(String text, int view){
        String snum = "";
        Pattern p = Pattern.compile("[-,+,*,÷]");
        Matcher m = p.matcher(text);
        if (m.find()){
            if (text.contains("+")){
                String[] arr = text.split("\\+");
                double a = Double.parseDouble(arr[0]);
                double b = Double.parseDouble(arr[1]);
                double c = a + b;
                snum = c + "";
                textView.setText(snum);
            }
            if (text.contains("*")){
                String[] arr = text.split("\\*");
                double a = Double.parseDouble(arr[0]);
                double b = Double.parseDouble(arr[1]);
                double c = a * b;
                snum = c + "";
                textView.setText(snum);
            }
            if (text.contains("÷")){
                String[] arr = text.split("÷");
                double a = Double.parseDouble(arr[0]);
                double b = Double.parseDouble(arr[1]);
                double c = 0d;
                if (b == 0){
                    Toast.makeText(this, "on 0 you can not divide", Toast.LENGTH_SHORT).show();
                    snum = "0";
                    textView.setText(snum);
                }else {
                    snum = (a / b) + "";
                    textView.setText(snum);
                }
            }
            if (text.contains("-")){
                String[] arr = text.split("-");
                double a = Double.parseDouble(arr[0]);
                double b = Double.parseDouble(arr[1]);
                double c = a - b;
                snum = c + "";
                textView.setText(snum);
            }
        }else {
            if (view == R.id.btnPlus){
                snum = text + "+";
            }
            if (view == R.id.btnDiv){
                snum = text + "÷";
            }
            if (view == R.id.btnSub){
                snum = text + "-";
            }
            if (view == R.id.btnMult){
                snum = text + "*";
            }
            textView.setText(snum);
        }
        //return snum;
    }


}
