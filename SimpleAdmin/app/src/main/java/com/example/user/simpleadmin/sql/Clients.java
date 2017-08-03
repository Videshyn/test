package com.example.user.simpleadmin.sql;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.simpleadmin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24.07.2017.
 */

public class Clients extends Activity implements View.OnClickListener{

    private DBHelper dbHelper;
    private Button btnSeeAllClients, btnRewriteClients, btnAddClients;
    private ListView clientsListView;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients);

        initUI();

        btnAddClients.setOnClickListener(this);
        btnSeeAllClients.setOnClickListener(this);
        btnRewriteClients.setOnClickListener(this);
    }

    private void initUI() {
        dbHelper = new DBHelper(this);
        clientsListView = (ListView) findViewById(R.id.clients_list_view) ;
        btnAddClients = (Button) findViewById(R.id.btn_clients_add);
        btnSeeAllClients = (Button) findViewById(R.id.btn_see_all_clients);
        btnRewriteClients = (Button) findViewById(R.id.btn_clients_rewrite);
    }

    @Override
    public void onClick(View view) {
        Cursor cursor = null;
        Intent intent;
        db = dbHelper.getWritableDatabase();
        switch (view.getId()){
            case R.id.btn_clients_add:
                intent = new Intent(Clients.this, ClientsAdd.class);
                startActivity(intent);
                break;
            case R.id.btn_clients_rewrite:
                intent = new Intent(Clients.this, ClientsRewrite.class);
                startActivity(intent);
                break;
            case R.id.btn_see_all_clients:
                cursor = db.query("CLIENTS", null, null, null, null, null, null);
                break;
        }

        List<String> clientsList = new ArrayList<>();
        if (cursor != null){
            if (cursor.moveToFirst()){
                int idColumnIndex = cursor.getColumnIndex("id_clients");
                int nameColumnIndex = cursor.getColumnIndex("clients_name");
                int phoneColumnIndex = cursor.getColumnIndex("phone");

                do {
                    clientsList.add("id = " + cursor.getInt(idColumnIndex)
                                    + " name = " + cursor.getString(nameColumnIndex)
                                    + " phone = " + cursor.getString(phoneColumnIndex));
                }while (cursor.moveToNext());
            }
            cursor.close();
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientsList);
        clientsListView.setAdapter(adapter);

        dbHelper.close();
    }
}
