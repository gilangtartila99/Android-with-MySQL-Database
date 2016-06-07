package com.example.gilang.androidmysql;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
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

/**
 * Created by GILANG on 04/06/2016.
 */
public class MainActivity3 extends Activity implements View.OnClickListener {
    Check check = new Check();
    TableLayout tabelCheck;

    Button bTambahCheck;
    ArrayList<Button> bEdit = new ArrayList<Button>();
    ArrayList<Button> bDelete = new ArrayList<Button>();

    JSONArray arrayCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_check);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tabelCheck = (TableLayout) findViewById(R.id.table_check);
        bTambahCheck = (Button) findViewById(R.id.button_tambah_check);
        bTambahCheck.setOnClickListener(this);

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderEmployeename = new TextView(this);
        TextView viewHeaderItemname = new TextView(this);
        TextView viewHeaderStatus = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderEmployeename.setText("Employee Name");
        viewHeaderItemname.setText("Item Name");
        viewHeaderStatus.setText("Status");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderEmployeename.setPadding(5, 1, 5, 1);
        viewHeaderItemname.setPadding(5, 1, 5, 1);
        viewHeaderStatus.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderEmployeename);
        barisTabel.addView(viewHeaderItemname);
        barisTabel.addView(viewHeaderStatus);
        barisTabel.addView(viewHeaderAction);

        tabelCheck.addView(barisTabel, new TableLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));

        try {

            arrayCheck = new JSONArray(check.tampilCheck());

            for (int i = 0; i < arrayCheck.length(); i++) {
                JSONObject jsonChildNode = arrayCheck.getJSONObject(i);
                String employee_name = jsonChildNode.optString("employee_name");
                String item_name = jsonChildNode.optString("item_name");
                String status = jsonChildNode.optString("status");
                String id = jsonChildNode.optString("id");

                System.out.println("Employee Name :" + employee_name);
                System.out.println("Item Name :" + item_name);
                System.out.println("Status :" + status);
                System.out.println("ID :" + id);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewId = new TextView(this);
                viewId.setText(id);
                viewId.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewId);

                TextView viewEmployeename = new TextView(this);
                viewEmployeename.setText(employee_name);
                viewEmployeename.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewEmployeename);

                TextView viewItemname = new TextView(this);
                viewItemname.setText(item_name);
                viewItemname.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewItemname);

                TextView viewStatus = new TextView(this);
                viewStatus.setText(status);
                viewStatus.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewStatus);

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

                tabelCheck.addView(barisTabel, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {

        if (view.getId() == R.id.button_tambah_check) {
            // Toast.makeText(MainActivity.this, "Button Tambah Data",
            // Toast.LENGTH_SHORT).show();

            tambahCheck();

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
                    deleteCheck(id);

                }
            }
        }
    }

    public void deleteCheck(int id) {
        check.deleteCheck(id);

  /* restart acrtivity */
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        startActivity(getIntent());

    }

    public void getDataByID(int id) {

        String employee_nameEdit = null, item_nameEdit = null, statusEdit = null;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(check.getCheckById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                employee_nameEdit = jsonChildNode.optString("employee_name");
                item_nameEdit = jsonChildNode.optString("item_name");
                statusEdit = jsonChildNode.optString("status");
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

        final EditText editEmployeename = new EditText(this);
        editEmployeename.setText(employee_nameEdit);
        layoutInput.addView(editEmployeename);

        final EditText editItemname = new EditText(this);
        editItemname.setText(item_nameEdit);
        layoutInput.addView(editItemname);

        final EditText editStatus = new EditText(this);
        editStatus.setText(statusEdit);
        layoutInput.addView(editStatus);

        AlertDialog.Builder builderEditCheck = new AlertDialog.Builder(this);
        builderEditCheck.setIcon(R.drawable.batagrams);
        builderEditCheck.setTitle("Update Check");
        builderEditCheck.setView(layoutInput);
        builderEditCheck.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String employee_name = editEmployeename.getText().toString();
                String item_name = editItemname.getText().toString();
                String status = editStatus.getText().toString();

                System.out.println("Employee Name : " + employee_name + " Item Name : " + item_name + " Status : " + status);

                String laporan = check.updateCheck(viewId.getText().toString(),
                        editEmployeename.getText().toString(),
                        editItemname.getText().toString(),
                        editStatus.getText().toString());

                Toast.makeText(MainActivity3.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderEditCheck.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditCheck.show();

    }

    public void tambahCheck() {
  /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editEmployeename = new EditText(this);
        editEmployeename.setHint("Employee Name");
        layoutInput.addView(editEmployeename);

        final EditText editItemname = new EditText(this);
        editItemname.setHint("Item Name");
        layoutInput.addView(editItemname);

        final EditText editStatus = new EditText(this);
        editStatus.setHint("Status");
        layoutInput.addView(editStatus);

        AlertDialog.Builder builderInsertCheck = new AlertDialog.Builder(this);
        builderInsertCheck.setIcon(R.drawable.batagrams);
        builderInsertCheck.setTitle("Insert Check");
        builderInsertCheck.setView(layoutInput);
        builderInsertCheck.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String employee_name = editEmployeename.getText().toString();
                String item_name = editItemname.getText().toString();
                String status = editStatus.getText().toString();

                System.out.println("Employee Name : " + employee_name + " Item Name : " + item_name + " Status : " + status );

                String laporan = check.insertCheck(employee_name, item_name, status);

                Toast.makeText(MainActivity3.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderInsertCheck.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertCheck.show();
    }
}
