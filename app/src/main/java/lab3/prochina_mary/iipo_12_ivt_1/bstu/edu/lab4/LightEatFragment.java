package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by арт on 12.10.2015.
 */
public class LightEatFragment extends android.support.v4.app.Fragment {
    Context myContext;
    ListView listLightEat;
    ArrayList<LightEatItem> lightEatItems;
    String str;
    LightEatAdapter lightEatAdapter;
    DBhelper dBhelper;
    final Uri uriMy = Uri.parse("content://com.mycontentprovider.eat/dish");
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        myContext = context;
        dBhelper = new DBhelper(context);
        lightEatItems = new ArrayList<LightEatItem>();
    }


    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(myContext, ActivityAddLight.class);
            startActivityForResult(intent, 1);
        }
    };

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null)return;
        String strName = data.getStringExtra("name");
        String strTime = data.getStringExtra("time");
        String strLevel = data.getStringExtra("level");
        int level = Integer.parseInt(strLevel);
        lightEatItems.add(new LightEatItem(strName, strTime, level));
        lightEatAdapter.notifyDataSetChanged();
    }*/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.light_eat_frag, container, false);
        Button btn = (Button)v.findViewById(R.id.buttonClickAdd);
        listLightEat = (ListView)v.findViewById(R.id.listViewLightEat);
        btn.setOnClickListener(myClickListener);
       // SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        //String tables1 = "dish as DS inner join category as CT on DS.category = CT._id";
        //Cursor c = sqLiteDatabase.query(tables1, null, "CT._id = 0", null, null, null, null, null);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        lightEatItems.clear();
        //Cursor c = getActivity().getContentResolver().query(uriMy, null, null, null, null);
        Cursor c = getActivity().getContentResolver().query(uriMy, null, null, null, null);
        if (c != null)
        {
            c.moveToFirst();
            int indexId = c.getColumnIndex("_id");
            int indexName = c.getColumnIndex("name");
            int indexTime = c.getColumnIndex("time");
            int indexLevel = c.getColumnIndex("level");
            int indexDesc = c.getColumnIndex("description");
            String infoStr = "";
            do {
                String nameStr = c.getString(1);
                String timeStr = c.getString(indexTime);
                int levelInt = c.getInt(indexLevel);
                String descStr = c.getString(indexDesc);
                lightEatItems.add(new LightEatItem(nameStr, timeStr, levelInt, descStr));
            } while (c.moveToNext() == true);
        }
        lightEatAdapter = new LightEatAdapter(lightEatItems, myContext);
        listLightEat.setAdapter(lightEatAdapter);
    }
}