package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by арт on 13.10.2015.
 */
public class HotEatFragment extends Fragment {
    Context base;
    DBhelper dBhelper;
    ListView listLightEat;
    ArrayList<LightEatItem> lightEatItems;
    LightEatAdapter lightEatAdapter;
    final Uri uriMy = Uri.parse("content://com.mycontentprovider.eat/dishHot");
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        base = activity;
        dBhelper = new DBhelper(base);
        lightEatItems = new ArrayList<LightEatItem>();
    }

    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Button btn = (Button)v.findViewById(R.id.buttonClick);
//            btn.setText("НАЖАЛ");

            Intent intent = new Intent(base, ActivityAddLight.class);
            startActivityForResult(intent, 1);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.light_eat_frag, container, false);
        Button btn = (Button)v.findViewById(R.id.buttonClickAdd);
        listLightEat = (ListView)v.findViewById(R.id.listViewLightEat);
        btn.setOnClickListener(myClickListener);
        //SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        String tables1 = "dish as DS inner join category as CT on DS.category = CT._id";

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        lightEatItems.clear();
        Cursor c = getActivity().getContentResolver().query(uriMy, null, null, null, null);
        //Cursor c = sqLiteDatabase.query(tables1, null, "CT._id = 1", null, null, null, null, null);
        if (c != null)
        {
            c.moveToFirst();
            int indexId = c.getColumnIndex("DS._id");
            int indexName = c.getColumnIndex("DS.name");
            int indexTime = c.getColumnIndex("time");
            int indexLevel = c.getColumnIndex("level");
            int indexDesc = c.getColumnIndex("description");
            String infoStr = "";
            do {
                try {
                    String nameStr = c.getString(1);
                    String timeStr = c.getString(indexTime);
                    int levelInt = c.getInt(indexLevel);
                    String descStr = c.getString(indexDesc);
                    lightEatItems.add(new LightEatItem(nameStr, timeStr, levelInt, descStr));
                }catch (Exception e)
                {

                }

            } while (c.moveToNext() == true);
        }

        lightEatAdapter = new LightEatAdapter(lightEatItems, base);

        listLightEat.setAdapter(lightEatAdapter);

    }
}
