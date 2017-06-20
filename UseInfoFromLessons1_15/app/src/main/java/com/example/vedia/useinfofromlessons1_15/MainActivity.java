package com.example.vedia.useinfofromlessons1_15;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btn;
    EditText editText;
    TextView textView;

    final String MY_LOG = "MY DEBUG LOG";
    String copied;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(MY_LOG, "get id for views");
        btn = (Button) findViewById(R.id.btnClear);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);

        Log.d(MY_LOG, "listener for button");
        btn.setOnClickListener(this);

        Log.d(MY_LOG, "context menu");
        registerForContextMenu(textView);
        registerForContextMenu(editText);

    }

    @Override
    public void onClick(View view) {
        Log.d(MY_LOG, "in button listener");
        textView.setText("default");
        editText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(MY_LOG, "Create menu");
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(MY_LOG, "work with menu's items");
        switch (item.getItemId()){
            case R.id.add_menu:
                textView.setText(textView.getText() + " " + editText.getText());
                break;
            case R.id.edit_menu:
                textView.setText(editText.getText());
                break;
            case R.id.delete_menu:
                textView.setText("");
                break;
            case R.id.copy_menu:
                Toast.makeText(this, "Text copied", Toast.LENGTH_SHORT).show();
                copied = (String) textView.getText();
                break;
            case R.id.paste_menu:
                editText.setText(copied);
                Toast.makeText(this, "Text pasted", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_menu:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.d(MY_LOG, "Create context menu");
        switch (v.getId()){
            case R.id.editText:
                menu.add(0, MENU_COLOR_BLUE, 1, "BLUE");
                menu.add(0, MENU_COLOR_GREEN, 2, "GREEN");
                menu.add(0, MENU_COLOR_RED, 3, "RED");
                break;
            case R.id.textView:
                menu.add(0, MENU_SIZE_22, 1, "22");
                menu.add(0, MENU_SIZE_26, 2, "26");
                menu.add(0, MENU_SIZE_30, 3, "30");
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(MY_LOG, "Event handling context-sensitive");
        switch (item.getItemId()){
            case MENU_COLOR_BLUE:
                editText.setTextColor(Color.BLUE);
                break;
            case MENU_COLOR_GREEN:
                editText.setTextColor(Color.GREEN);
                break;
            case MENU_COLOR_RED:
                editText.setTextColor(Color.RED);
                break;
            case MENU_SIZE_22:
                textView.setTextSize(22);
                break;
            case MENU_SIZE_26:
                textView.setTextSize(26);
                break;
            case MENU_SIZE_30:
                textView.setTextSize(30);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
