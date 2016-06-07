package com.example.gilang.androidmysql;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by GILANG on 04/06/2016.
 */
public class Login extends ActionBarActivity{
    Button bLogin, bExit;
    private Context context;
    private ProgressDialog pDialog;
    String[] Employee_Name = {"gilang", "entol", "suriadi","tentri","ulfah","rima"};

    String LOGIN_URL = "http://192.168.100.6/serverandroid/login.php";
    //Keys for email and password as defined in our $_POST['key'] in login.php
    String KEY_USERNAME = "username";
    String KEY_PASSWORD = "password";
    //If server response is equal to this that means login is successful
    String LOGIN_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = Login.this;

        pDialog = new ProgressDialog(context);
        final EditText edPassword = (EditText) findViewById(R.id.editText_password);
        bLogin = (Button) findViewById(R.id.button_login);
        bExit = (Button) findViewById(R.id.button_exit);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, Employee_Name);
        final AutoCompleteTextView edUsernamee = (AutoCompleteTextView) findViewById(R.id.editText_username);
        edUsernamee.setThreshold(1);//akan dibaca dari karakter pertama
        edUsernamee.setAdapter(adapter);
        edUsernamee.setTextColor(Color.BLACK);

        CheckBox c = (CheckBox) findViewById(R.id.checkBox_password);
        c.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edUsernamee.getText().toString().trim();
                final String password = edPassword.getText().toString().trim();
                pDialog.setMessage("Login Process...");
                showDialog();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals(LOGIN_SUCCESS)) {
                                    hideDialog();
                                    gotoLogin();
                                } else {
                                    hideDialog();
                                    Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                hideDialog();
                                Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
//Adding parameters to request
                        params.put(KEY_USERNAME, username);
                        params.put(KEY_PASSWORD, password);
//returning parameter
                        return params;
                    }
                };
//Adding the string request to the queue
                Volley.newRequestQueue(Login.this).add(stringRequest);
            }
            private void gotoLogin() {
                Intent i = new Intent(context, Menu.class);
                startActivity(i);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                finish();
            }
            private void showDialog() {
                if (!pDialog.isShowing())
                    pDialog.show();
            }

            private void hideDialog() {
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        });

        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                Toast.makeText(getApplicationContext(), "You Exit the Application", Toast.LENGTH_LONG).show();
            }
        });

    }
}
