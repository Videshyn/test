package com.example.user.simpleadmin.sql;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.simpleadmin.R;

/**
 * Created by User on 03.08.2017.
 */

public class ClientsAdd extends Activity{

    private DBHelper dbHelper;
    private Button btnClientsOk;
    private EditText etClientName, etClientPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients_add);

        initUI();

        btnClientsOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                cv.put("clients_name", etClientName.getText().toString());
                cv.put("phone", Double.parseDouble(etClientPhone.getText().toString()));
                long addId = database.insert("CLIENTS", null, cv);
                etClientPhone.setText("");
                etClientName.setText("");
                Toast.makeText(ClientsAdd.this, "new " + addId + " client's added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        dbHelper = new DBHelper(this);
        btnClientsOk = (Button) findViewById(R.id.btnAddClients);
        etClientName = (EditText) findViewById(R.id.etAddClientsName);
        etClientPhone = (EditText) findViewById(R.id.etClientsPhone);
    }
}
