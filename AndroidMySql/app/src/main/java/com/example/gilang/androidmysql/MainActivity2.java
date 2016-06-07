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
public class MainActivity2 extends Activity implements View.OnClickListener {
    Item item = new Item();
    TableLayout tabelItem;

    Button bTambahItem;
    ArrayList<Button> bEdit = new ArrayList<Button>();
    ArrayList<Button> bDelete = new ArrayList<Button>();

    JSONArray arrayItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_item);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tabelItem = (TableLayout) findViewById(R.id.table_item);
        bTambahItem = (Button) findViewById(R.id.button_tambah_item);
        bTambahItem.setOnClickListener(this);

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderName = new TextView(this);
        TextView viewHeaderType = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderName.setText("Name");
        viewHeaderType.setText("Type");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderName.setPadding(5, 1, 5, 1);
        viewHeaderType.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderName);
        barisTabel.addView(viewHeaderType);
        barisTabel.addView(viewHeaderAction);

        tabelItem.addView(barisTabel, new TableLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));

        try {

            arrayItem = new JSONArray(item.tampilItem());

            for (int i = 0; i < arrayItem.length(); i++) {
                JSONObject jsonChildNode = arrayItem.getJSONObject(i);
                String name = jsonChildNode.optString("name");
                String type = jsonChildNode.optString("type");
                String id = jsonChildNode.optString("id");

                System.out.println("Name :" + name);
                System.out.println("Type :" + type);
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

                TextView viewType = new TextView(this);
                viewType.setText(type);
                viewType.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewType);

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

                tabelItem.addView(barisTabel, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {

        if (view.getId() == R.id.button_tambah_item) {
            // Toast.makeText(MainActivity.this, "Button Tambah Data",
            // Toast.LENGTH_SHORT).show();

            tambahItem();

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
                    deleteItem(id);

                }
            }
        }
    }

    public void deleteItem(int id) {
        item.deleteItem(id);

  /* restart acrtivity */
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        startActivity(getIntent());

    }

    public void getDataByID(int id) {

        String nameEdit = null, typeEdit = null;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(item.getItemById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                nameEdit = jsonChildNode.optString("name");
                typeEdit = jsonChildNode.optString("type");
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

        final EditText editType = new EditText(this);
        editType.setText(typeEdit);
        layoutInput.addView(editType);

        AlertDialog.Builder builderEditItem = new AlertDialog.Builder(this);
        builderEditItem.setIcon(R.drawable.batagrams);
        builderEditItem.setTitle("Update Item");
        builderEditItem.setView(layoutInput);
        builderEditItem.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editName.getText().toString();
                String type = editType.getText().toString();

                System.out.println("Name : " + name + " Type : " + type);

                String laporan = item.updateItem(viewId.getText().toString(),
                        editName.getText().toString(),
                        editType.getText().toString());

                Toast.makeText(MainActivity2.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderEditItem.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditItem.show();

    }

    public void tambahItem() {
  /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editName = new EditText(this);
        editName.setHint("Name");
        layoutInput.addView(editName);

        final EditText editType = new EditText(this);
        editType.setHint("Type");
        layoutInput.addView(editType);

        AlertDialog.Builder builderInsertItem = new AlertDialog.Builder(this);
        builderInsertItem.setIcon(R.drawable.batagrams);
        builderInsertItem.setTitle("Insert Item");
        builderInsertItem.setView(layoutInput);
        builderInsertItem.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editName.getText().toString();
                String type = editType.getText().toString();

                System.out.println("Name : " + name + " Type : " + type);

                String laporan = item.insertItem(name, type);

                Toast.makeText(MainActivity2.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderInsertItem.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertItem.show();
    }
}
