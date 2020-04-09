package com.example.b17it097;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Chronometer c;
    int i=0;
    int duration=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c=(Chronometer)findViewById(R.id.cnm);
        c.start();
        c.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer arg0) {
                i++;
                if (i >= duration)
                {
                    Toast.makeText(getApplicationContext(),"Message"+(i/10),   Toast.LENGTH_LONG).show();
                    duration=duration+10;
                }

            }
        });
    }
}