package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by арт on 27.10.2015.
 */
public class ActivityAddLight extends AppCompatActivity {

    Context cont;
    EditText editTextName;
    EditText editTextTime;
    EditText editTextLevel;
    EditText editDesc;
    Spinner spinner;
    Spinner spinnerKit;
    DBhelper dBhelper;
    final Uri uriData = Uri.parse("content://com.mycontentprovider.eat");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_light_eat);
        cont = this;
        editTextName = (EditText)findViewById(R.id.editTextNameLight);
        editTextTime = (EditText)findViewById(R.id.editTextTimeLight);
        editTextLevel = (EditText)findViewById(R.id.editTextLevelLight);
        editDesc = (EditText) findViewById(R.id.editTextDescLight);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinnerKit = (Spinner)findViewById(R.id.spinnerKit);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(cont, "rere", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerKit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        dBhelper = new DBhelper(this);
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        ArrayList<String> arList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.query("category", null, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            int indexId = cursor.getColumnIndex("_id");
            int indexCol = cursor.getColumnIndex("name");
            do {
                int idStr = cursor.getInt(indexId);
                String dataStr = cursor.getString(indexCol);
                arList.add(dataStr);
            }while(cursor.moveToNext() == true);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arList);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(1);

        ArrayList<String> arListKit = new ArrayList<String>();
        Cursor cursorKit = sqLiteDatabase.query("kitchen", null, null, null, null, null, null);
        if(cursorKit!=null)
        {
            cursorKit.moveToFirst();
            int indexId = cursorKit.getColumnIndex("_id");
            int indexCol = cursorKit.getColumnIndex("name");
            do {
                int idStr = cursorKit.getInt(indexId);
                String dataStr = cursorKit.getString(indexCol);
                arListKit.add(dataStr);
            }while(cursorKit.moveToNext() == true);
        }
        ArrayAdapter<String> dataAdapterKit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arListKit);
        spinnerKit.setAdapter(dataAdapterKit);
        spinnerKit.setSelection(1);
        spinnerKit.setPrompt("Кухни мира");
        sqLiteDatabase.close();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        editTextName.setText(sp.getString("lightName", ""));
        editTextTime.setText(sp.getString("lightTime", ""));
        editTextLevel.setText(sp.getString("lightLevel", ""));
    }

    public void click(View v)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", editTextName.getText().toString());
        contentValues.put("time", editTextTime.getText().toString());
        contentValues.put("level", Integer.parseInt(editTextLevel.getText().toString()));
        contentValues.put("description", editDesc.getText().toString());
        int cat = spinner.getSelectedItemPosition();
        int kit = spinnerKit.getSelectedItemPosition();
        contentValues.put("category", cat);
        contentValues.put("kitchen", kit);

        Uri uri = getContentResolver().insert(uriData, contentValues);
        Toast.makeText(this, "вставлено с uri: "+ uri.toString(), Toast.LENGTH_LONG).show();

//        SQLiteDatabase database = dBhelper.getWritableDatabase();
//        database.insert("dish", null, contentValues);
//        database.close();
//        String name = editTextName.getText().toString();
//        String time = editTextTime.getText().toString();
//        String level = editTextLevel.getText().toString();
//
//        Intent intent =  new Intent();
//        intent.putExtra("name", name);
//        intent.putExtra("time", time);
//        intent.putExtra("level", level);
//        setResult(RESULT_OK, intent);

        Toast.makeText(this, "Запись добавлена. Перейдите к окну закуски", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("lightName", editTextName.getText().toString());
        editor.putString("lightTime", editTextTime.getText().toString());
        editor.putString("lightLevel", editTextLevel.getText().toString());
        editor.commit();
    }
}
