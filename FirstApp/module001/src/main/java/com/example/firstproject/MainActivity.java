package com.example.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.myscreen);// устанавливает содержимое из лояут файла,
        // принимает в качесве параметра константу которая является ID файла
    }
}
