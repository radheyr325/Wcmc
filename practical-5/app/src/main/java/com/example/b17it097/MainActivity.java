package com.example.b17it097;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    TextView message;
    Button cancel,login;
    String user,pass;
    int count = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cancel = findViewById(R.id.cancel);
        login = findViewById(R.id.login);
        message = findViewById(R.id.message);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("rutvik") && pass.equals("rutvik")){
                    message.setText(user+" Login Success Fully.");
                    message.setBackgroundColor(Color.parseColor("#006600"));
                }else{
                    message.setText("User Name or Password Incorrect."+count);
                    message.setBackgroundColor(Color.parseColor("#990000"));
                    count = count + 1;
                    if (count==4){
                        login.setEnabled(false);
                        login.setBackgroundColor(Color.BLACK);

                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}