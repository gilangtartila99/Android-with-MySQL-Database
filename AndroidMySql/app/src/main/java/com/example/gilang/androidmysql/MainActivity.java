package com.example.gilang.androidmysql;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    Employee employee = new Employee();
    TableLayout tabelEmployee;

    Button bTambahEmployee;
    ArrayList<Button> bEdit = new ArrayList<Button>();
    ArrayList<Button> bDelete = new ArrayList<Button>();

    JSONArray arrayEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tabelEmployee = (TableLayout) findViewById(R.id.table_employee);
        bTambahEmployee = (Button) findViewById(R.id.button_tambah_employee);
        bTambahEmployee.setOnClickListener(this);

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderName = new TextView(this);
        TextView viewHeaderSurname = new TextView(this);
        TextView viewHeaderAge = new TextView(this);
        TextView viewHeaderUsername = new TextView(this);
        TextView viewHeaderPassword = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderName.setText("Name");
        viewHeaderSurname.setText("Surname");
        viewHeaderAge.setText("Age");
        viewHeaderUsername.setText("Username");
        viewHeaderPassword.setText("Password");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderName.setPadding(5, 1, 5, 1);
        viewHeaderSurname.setPadding(5, 1, 5, 1);
        viewHeaderAge.setPadding(5, 1, 5, 1);
        viewHeaderUsername.setPadding(5, 1, 5, 1);
        viewHeaderPassword.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderName);
        barisTabel.addView(viewHeaderSurname);
        barisTabel.addView(viewHeaderAge);
        barisTabel.addView(viewHeaderUsername);
        barisTabel.addView(viewHeaderPassword);
        barisTabel.addView(viewHeaderAction);

        tabelEmployee.addView(barisTabel, new TableLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));

        try {

            arrayEmployee = new JSONArray(employee.tampilEmployee());

            for (int i = 0; i < arrayEmployee.length(); i++) {
                JSONObject jsonChildNode = arrayEmployee.getJSONObject(i);
                String name = jsonChildNode.optString("name");
                String surname = jsonChildNode.optString("surname");
                String age = jsonChildNode.optString("age");
                String username = jsonChildNode.optString("username");
                String password = jsonChildNode.optString("password");
                String id = jsonChildNode.optString("id");

                System.out.println("Name :" + name);
                System.out.println("Surname :" + surname);
                System.out.println("Age :" + name);
                System.out.println("Username :" + username);
                System.out.println("Password :" + password);
                System.out.println("ID :" + id);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewId = new TextView(this);
                viewId.setText(id);
                viewId.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewId);

                TextView viewName = new TextView(this);
                viewName.setText(name);
                viewName.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewName);

                TextView viewSurname = new TextView(this);
                viewSurname.setText(surname);
                viewSurname.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewSurname);

                TextView viewAge = new TextView(this);
                viewAge.setText(age);
                viewAge.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAge);

                TextView viewUsername = new TextView(this);
                viewUsername.setText(username);
                viewUsername.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewUsername);

                TextView viewPassword = new TextView(this);
                viewPassword.setText(password);
                viewPassword.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewPassword);

                bEdit.add(i, new Button(this));
                bEdit.get(i).setId(Integer.parseInt(id));
                bEdit.get(i).setTag("Edit");
                bEdit.get(i).setText("Edit");
                bEdit.get(i).setOnClickListener(this);
                barisTabel.addView(bEdit.get(i));

                bDelete.add(i, new Button(this));
                bDelete.get(i).setId(Integer.parseInt(id));
                bDelete.get(i).setTag("Delete");
                bDelete.get(i).setText("Delete");
                bDelete.get(i).setOnClickListener(this);
                barisTabel.addView(bDelete.get(i));

                tabelEmployee.addView(barisTabel, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {

        if (view.getId() == R.id.button_tambah_employee) {
            // Toast.makeText(MainActivity.this, "Button Tambah Data",
            // Toast.LENGTH_SHORT).show();

            tambahEmployee();

        } else {
   /*
    * Melakukan pengecekan pada data array, agar sesuai dengan index
    * masing-masing button
    */
            for (int i = 0; i < bEdit.size(); i++) {

    /* jika yang diklik adalah button edit */
                if (view.getId() == bEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                    // Toast.makeText(MainActivity.this, "Edit : " +
                    // buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = bEdit.get(i).getId();
                    getDataByID(id);

                } /* jika yang diklik adalah button delete */
                else if (view.getId() == bDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                    // Toast.makeText(MainActivity.this, "Delete : " +
                    // buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = bDelete.get(i).getId();
                    deleteEmployee(id);

                }
            }
        }
    }

    public void deleteEmployee(int id) {
        employee.deleteEmployee(id);

  /* restart acrtivity */
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        startActivity(getIntent());

    }

    public void getDataByID(int id) {

        String nameEdit = null, surnameEdit = null, ageEdit = null, usernameEdit = null, passwordEdit = null;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(employee.getEmployeeById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                nameEdit = jsonChildNode.optString("name");
                surnameEdit = jsonChildNode.optString("surname");
                ageEdit = jsonChildNode.optString("age");
                usernameEdit = jsonChildNode.optString("username");
                passwordEdit = jsonChildNode.optString("password");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // buat id tersembunyi di alertbuilder
        final TextView viewId = new TextView(this);
        viewId.setText(String.valueOf(id));
        viewId.setTextColor(Color.TRANSPARENT);
        layoutInput.addView(viewId);

        final EditText editName = new EditText(this);
        editName.setText(nameEdit);
        layoutInput.addView(editName);

        final EditText editSurname = new EditText(this);
        editSurname.setText(surnameEdit);
        layoutInput.addView(editSurname);

        final EditText editAge = new EditText(this);
        editAge.setText(ageEdit);
        layoutInput.addView(editAge);

        final EditText editUsername = new EditText(this);
        editUsername.setText(usernameEdit);
        layoutInput.addView(editUsername);

        final EditText editPassword = new EditText(this);
        editPassword.setText(passwordEdit);
        layoutInput.addView(editPassword);

        AlertDialog.Builder builderEditEmployee = new AlertDialog.Builder(this);
        builderEditEmployee.setIcon(R.drawable.batagrams);
        builderEditEmployee.setTitle("Update Employee");
        builderEditEmployee.setView(layoutInput);
        builderEditEmployee.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editName.getText().toString();
                String surname = editSurname.getText().toString();
                String age = editAge.getText().toString();
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                System.out.println("Name : " + name + " Surname : " + surname + " Age : " + age + " Username : " + username + " Password : " + password);

                String laporan = employee.updateEmployee(viewId.getText().toString(),
                        editName.getText().toString(),
                        editSurname.getText().toString(),
                        editAge.getText().toString(),
                        editUsername.getText().toString(),
                        editPassword.getText().toString());

                Toast.makeText(MainActivity.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderEditEmployee.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditEmployee.show();

    }

    public void tambahEmployee() {
  /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editName = new EditText(this);
        editName.setHint("Name");
        layoutInput.addView(editName);

        final EditText editSurname = new EditText(this);
        editSurname.setHint("Surname");
        layoutInput.addView(editSurname);

        final EditText editAge = new EditText(this);
        editAge.setHint("Age");
        layoutInput.addView(editAge);

        final EditText editUsername = new EditText(this);
        editUsername.setHint("Username");
        layoutInput.addView(editUsername);

        final EditText editPassword = new EditText(this);
        editPassword.setHint("Password");
        layoutInput.addView(editPassword);

        AlertDialog.Builder builderInsertEmployee = new AlertDialog.Builder(this);
        builderInsertEmployee.setIcon(R.drawable.batagrams);
        builderInsertEmployee.setTitle("Insert Employee");
        builderInsertEmployee.setView(layoutInput);
        builderInsertEmployee.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editName.getText().toString();
                String surname = editSurname.getText().toString();
                String age = editAge.getText().toString();
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                System.out.println("Name : " + name + " Surname : " + surname + " Age : " + age + " Username : " + username + " Password : " + password);

                String laporan = employee.insertEmployee(name, surname, age, username, password);

                Toast.makeText(MainActivity.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderInsertEmployee.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertEmployee.show();
    }
}
