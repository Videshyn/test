package com.example.vedia.filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonChange;
    private EditText edit;
    public final String FORMER = "***";
    public final String CENZURE = "(?i)(х[у,е][р,й,я,ю,и,е,ё]|п[и,е,ё]зд|^[ё,е]б|пид[^ж]|чмыр|залуп|бляд|[вы,у,по]" +
            "[ё,е]б|за[ё,е]б|конч)";
    public final String SPACE = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonChange = (Button) findViewById(R.id.btnFilter);
        edit = (EditText) findViewById(R.id.edit);

        buttonChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String editString = edit.getText().toString();
        Pattern pattern = Pattern.compile(CENZURE);
        String resultString = replace(pattern, editString);
        edit.setText(resultString);
    }

    public String replace(Pattern pattern, String editStr){
        String resultString = "";
        String[] arr = editStr.split(SPACE);
        for (int i = 0, n = arr.length; i < n; i ++){
            Matcher matcher = pattern.matcher(arr[i]);
            if (matcher.find()){
                arr[i] = FORMER;
            }
            resultString = arr[i] + SPACE;
        }
        return resultString;
    }
}
