package com.example.b17it097;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {

    EditText name,phone_number,password,confirm_password ;
    Spinner spinner;
    Button register;
    String district ;

    RequestQueue requestQueue;

    String hname,hphone,hpassword;

    ProgressDialog progressDialog;

    String HttpUrl = "https://cleanindia412.000webhostapp.com/registration.php";

//    Connection myConn = null;
//    PreparedStatement myStmt = null;
//    ResultSet myRs = null;
//
//    String url = "https://remotemysql.com:3306" ;
//    String user = "MTuuKRZweE" ;
//    String pass = "9yNDPhG4Zk" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.name);
        phone_number = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                district = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        requestQueue = Volley.newRequestQueue(registration.this);
        progressDialog = new ProgressDialog(registration.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                //Toast.makeText(registration.this,hname+hpassword+hphone+district,Toast.LENGTH_SHORT).show();
            }
        });

    }

    public  void setData() {
        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();

        hname = name.getText().toString();
        hphone = phone_number.getText().toString();
        hpassword = password.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog
                        progressDialog.dismiss();

                        Toast.makeText(registration.this, ServerResponse, Toast.LENGTH_LONG).show();

                        if (ServerResponse.equals("Data inserted successfully")) {
                            Intent intent1 = new Intent(registration.this, MainActivity.class);
                            startActivity(intent1);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog
                        progressDialog.dismiss();

                        Toast.makeText(registration.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("name", hname);
                params.put("phone_number", hphone);
                params.put("password", hpassword);
                params.put("district", district);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(registration.this);

        requestQueue.add(stringRequest);
        //  Toast.makeText(registration.this,hname+hpassword+hphone+district,Toast.LENGTH_SHORT).show();
    }
}
