package com.example.b17it097;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity<m1> extends AppCompatActivity {

    EditText username, password;
    Button login;

    RequestQueue requestQueue;
    String hname, hpassword;
    ProgressDialog progressDialog;
    String HttpUrl_citizen = "https://cleanindia412.000webhostapp.com/citizen_login.php";
    String HttpUrl_worker = "https://cleanindia412.000webhostapp.com/citizen_login.php";

    String confirmation;

    Button btn_reg;

    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Bundle b2 = getIntent().getExtras();
        final int m1 = b2.getInt("m1");

        btn_reg = findViewById(R.id.registration);
        if (btn_reg.getVisibility() == View.VISIBLE && m1 == 1) {
            btn_reg.setVisibility(View.GONE);
        } else {
            btn_reg.setVisibility(View.VISIBLE);
        }


        //login

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        progressDialog = new ProgressDialog(MainActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m1 == 0) {

                    progressDialog.setMessage("Please Wait, We are Authenticate You.");
                    progressDialog.show();

                    hname = username.getText().toString();
                    hpassword = password.getText().toString();

                    if (hname.isEmpty() && hpassword.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Enter User Name or Password", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl_citizen,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String ServerResponse) {
                                        progressDialog.dismiss();
                                        confirmation = ServerResponse;
                                        Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                                        if (ServerResponse.equals("Login Successfully")) {
                                            Toast.makeText(MainActivity.this,"Successfully Login",Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Hi", Toast.LENGTH_LONG).show();
                                        }
                                        //openNewActivity();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                        progressDialog.dismiss();
                                        Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() {

                                Map<String, String> params = new HashMap<String, String>();

                                params.put("phone_number", hname);
                                params.put("password", hpassword);

                                return params;
                            }

                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        requestQueue.add(stringRequest);

                    }


                } else if (m1 == 1) {

                    progressDialog.setMessage("Please Wait, We are Authenticate You.");
                    progressDialog.show();

                    hname = username.getText().toString();
                    hpassword = password.getText().toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl_worker,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String ServerResponse) {
                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {

                            Map<String, String> params = new HashMap<String, String>();

                            params.put("phone_number", hname);
                            params.put("password", hpassword);

                            return params;
                        }

                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);
                }
            }
        });


        //registration

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move();
            }
        });


        //forgot password
        forgot = findViewById(R.id.forgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //methods

    //registration

    public void move() {
        Intent intent = new Intent(this, registration.class);
        startActivity(intent);
    }

    private void openNewActivity() {
        Toast.makeText(this,"Successfully Login",Toast.LENGTH_LONG).show();
    }

}
