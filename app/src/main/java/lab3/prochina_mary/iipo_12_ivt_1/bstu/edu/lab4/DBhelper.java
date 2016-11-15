package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by арт on 26.11.2015.
 */
public class DBhelper extends SQLiteOpenHelper {
    String jsonEat;
    File file;
    String jsonString = "";
    Context cont;

    public DBhelper(Context context) {
        super(context, "my_eat_db", null, 1);
        cont = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE dish(_id integer primary key autoincrement,name text," +
                "time text,description text,level integer,category integer,kitchen integer);");

        db.execSQL("CREATE TABLE category(_id integer primary key,name text);");


        db.execSQL("CREATE TABLE kitchen(_id integer primary key,name text);");

        db.execSQL("CREATE TABLE photo(_id integer primary key,name text);");



        ContentValues contcateg = new ContentValues();
        contcateg.put("_id", 0);
        contcateg.put("name", "Закуски");
        db.insert("category", null, contcateg);
        contcateg.clear();
        contcateg.put("_id", 1);
        contcateg.put("name", "Горячие блюда");
        db.insert("category", null, contcateg);
        contcateg.clear();
        contcateg.put("_id", 2);
        contcateg.put("name", "Выпечка");
        db.insert("category", null, contcateg);
        contcateg.clear();

        ContentValues contv = new ContentValues();
        contcateg.put("_id", 0);
        contv.put("name", "Русская");
        db.insert("kitchen", null, contv);
        contv.clear();
        contcateg.put("_id", 1);
        contv.put("name", "Американская");
        db.insert("kitchen", null, contv);
        contv.clear();
        contcateg.put("_id", 2);
        contv.put("name", "Итальянская");
        db.insert("kitchen", null, contv);
        contv.clear();

        AssetManager am = cont.getAssets();
        InputStream is = null;
        try {
            is = am.open("json_lab.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader streamReader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String strLine = "";
        try {
            while ((strLine = bufferedReader.readLine()) != null) {
                jsonString += strLine;
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject trainsJson = (JSONObject) jsonObject.get("lighteat");
            int i = 0;
            JSONObject trainsJsonFirs;
            while ((trainsJsonFirs = (JSONObject) trainsJson.get(i + "")) != null) {
                String nameStr = trainsJsonFirs.getString("name");
                String timeStr = trainsJsonFirs.getString("time");
                String levelStr = trainsJsonFirs.getString("level");
                String desc = trainsJsonFirs.getString("description");
                int levelInt = Integer.parseInt(levelStr);

                ContentValues cv = new ContentValues();
                cv.put("name", nameStr);
                cv.put("time", timeStr);
                cv.put("level", levelInt);
                cv.put("description", desc);
                cv.put("category", 0);
                cv.put("kitchen", 1 - i);



                db.insert("dish", null, cv);
                cv.clear();
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
