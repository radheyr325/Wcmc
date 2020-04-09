package com.example.b17it097;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fe,ce ;
    Button convert ;
    Spinner spinner ;
    String text ;
    Double fed,ced;
    Double fece,cefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fe = findViewById(R.id.fe);
        ce = findViewById(R.id.ce);
        convert = findViewById(R.id.convert);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = spinner.getSelectedItem().toString() ;
                if(text.equals("Fahrenheit")){
                    ce.setEnabled(false);
                    fe.setEnabled(true);
                }else if(text.equals("Celsius")){
                    fe.setEnabled(false);
                    ce.setEnabled(true);
                }else if(text.equals("None")){
                    fe.setEnabled(false);
                    ce.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.equals("Fahrenheit")) {
                    fed = Double.parseDouble(fe.getText().toString());
                    fece = (fed - 32) * 5 / 9;
                    ce.setText(fece.toString());
                }else if(text.equals("Celsius")) {
                    ced = Double.parseDouble(ce.getText().toString());
                    cefe = ((ced * 9) / 5) + 32;
                    fe.setText(cefe.toString());
                }else if(text.equals("None")){
                    Toast.makeText(MainActivity.this,"Enter Value",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}