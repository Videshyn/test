package com.example.user.simpleadmin.sql;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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

public class ClientsRewrite extends Activity{

    private DBHelper dbHelper;
    private Button btnOkRewriteClient;
    private EditText etRewClientsId, etRewClientsName, etRewClientsPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients_rewrite);

        initUI();

        btnOkRewriteClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();

                String idClient = etRewClientsId.getText().toString();
                cv.put("clients_name", etRewClientsName.getText().toString());
                cv.put("phone", Double.parseDouble(etRewClientsPhone.getText().toString()));
                database.update("CLIENTS", cv, "id_clients = ?", new String[]{idClient});

                Toast.makeText(ClientsRewrite.this, "client with id = " + idClient + " updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        dbHelper = new DBHelper(this);
        btnOkRewriteClient = (Button) findViewById(R.id.btnOkRewriteClient);
        etRewClientsId = (EditText) findViewById(R.id.etRewClientsId);
        etRewClientsName = (EditText) findViewById(R.id.etRewClientsName);
        etRewClientsPhone = (EditText) findViewById(R.id.etRewClientsPhone);
    }
}
