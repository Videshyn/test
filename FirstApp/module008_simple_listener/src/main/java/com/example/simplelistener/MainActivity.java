package com.example.simplelistener;

import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button change;
    EditText edit;
    TextView text;
    final String s = "***";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        change = (Button) findViewById(R.id.btn);
        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.text);

        onClickStart(change);

    }

    public void onClickStart(View v){
        String str = String.valueOf(edit.getText());
        Pattern p = Pattern.compile("(ху[й,я,ю,и,е,ё]|п[и,е,ё]зд|^[ё,е]б|пид[^ж]|чмыр|залуп)");
        StringBuilder res = replace(p, str);
        res = replace(p, res.toString());
        text.setText(res);
    }

    public StringBuilder replace(Pattern p, String str){


        String[] arr = str.split(" ");
        StringBuilder stb = new StringBuilder("");
        for (int i = 0; i < arr.length; i ++){
            Matcher m = p.matcher(arr[i]);
            if (m.find()){
                arr[i] = s;
            }
            stb.append(arr[i] + " ");
        }
        return stb;
    }
}
