package com.example.vedia.filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button change;
    EditText edit;
    final String FORMER = "***";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        change = (Button) findViewById(R.id.btnFilter);
        edit = (EditText) findViewById(R.id.edit);

        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String str = String.valueOf(edit.getText());
        Pattern pattern = Pattern.compile("(?i)(х[у,е][р,й,я,ю,и,е,ё]|п[и,е,ё]зд|^[ё,е]б|пид[^ж]|чмыр|залуп|бляд|[вы,у,по]" +
                "[ё,е]б|за[ё,е]б|конч)");
        StringBuilder res = replace(pattern, str);
        edit.setText(res);
    }

    public StringBuilder replace(Pattern pattern, String editStr){
        StringBuilder res = new StringBuilder("");
        String[] arr = editStr.split(" ");
        for (int i = 0, n = arr.length; i < n; i ++){
            Matcher matcher = pattern.matcher(editStr);
            if (matcher.find()){
                arr[i] = FORMER;
            }
            res.append(arr[i] + " ");
        }
        return res;
    }
}
