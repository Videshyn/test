package com.example.user.simpleadmin.sql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.user.simpleadmin.R;

/**
 * Created by User on 24.07.2017.
 */

public class Choose extends Activity implements View.OnClickListener{

    private Button btnChooseProducts;
    private Button btnChooseClients;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_table);
        initUI();
    }

    public void initUI(){
        btnChooseClients = (Button) findViewById(R.id.btnChooseClients);
        btnChooseProducts = (Button) findViewById(R.id.btnChooseProducts);

        btnChooseClients.setOnClickListener(this);
        btnChooseProducts.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btnChooseClients:
                intent = new Intent(Choose.this, Clients.class);
                startActivity(intent);
                break;
            case R.id.btnChooseProducts:
                intent = new Intent(Choose.this, Products.class);
                startActivity(intent);
                break;
        }
    }
}
