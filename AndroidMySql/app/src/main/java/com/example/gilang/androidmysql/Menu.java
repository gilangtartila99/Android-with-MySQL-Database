package com.example.gilang.androidmysql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by GILANG on 04/06/2016.
 */
public class Menu extends Activity implements View.OnClickListener {

    private Button bEmployee;
    private Button bItem;
    private Button bCheck;
    private Button bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bEmployee = (Button) findViewById(R.id.button_masuk_employee);
        bEmployee.setOnClickListener(this);
        bItem = (Button) findViewById(R.id.button_masuk_item);
        bItem.setOnClickListener(this);
        bCheck = (Button) findViewById(R.id.button_masuk_check);
        bCheck.setOnClickListener(this);
        bLogout = (Button) findViewById(R.id.button_logout);
        bLogout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_masuk_employee :
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Employee Data", Toast.LENGTH_LONG).show();
                break;

            case R.id.button_masuk_item :
                Intent i2 = new Intent(this, MainActivity2.class);
                startActivity(i2);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Item Data", Toast.LENGTH_LONG).show();
                break;

            case R.id.button_masuk_check :
                Intent i3 = new Intent(this, MainActivity3.class);
                startActivity(i3);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Check Data", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_logout :
                Intent i4 = new Intent(this, Login.class);
                startActivity(i4);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Logout Success", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
