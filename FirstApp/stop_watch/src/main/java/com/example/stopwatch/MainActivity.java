package com.example.stopwatch;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bStop, bStart, bReset;

    private boolean running = false;
    private int seconds = 0;
    private boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            running = savedInstanceState.getBoolean("running");
            seconds = savedInstanceState.getInt("seconds");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        bReset = (Button) findViewById(R.id.btnReset);
        bStart = (Button) findViewById(R.id.btnStart);
        bStop = (Button) findViewById(R.id.btnStop);

        onRun();

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;

            }
        });

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;

            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                seconds = 0;

            }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("running", running);
        outState.putInt("seconds", seconds);
        outState.putBoolean("wasRunning", wasRunning);
    }

    public void onRun(){
        final  TextView textView = (TextView) findViewById(R.id.text);
        final Handler handler = new Handler();
        // код в методе run() будет выполнен мгновенно, поскольку помещен в метод post(),
        // поскольку postDelayed() вызывается через 1000мс, и находится в методе run(),
        // то код будет вызываться снова и снова
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 360;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                textView.setText(time);
                if (running){
                    seconds ++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning)
            running = true;
    }
}
