package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<SweetList> arrayList1;


    ArrayList<MyMenu> arrayList;
    ListView leftMenu;
    Context base;
    DrawerLayout drawerLayout;//перемещение меню слева
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        base = this;
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftMenu = (ListView) findViewById(R.id.ListLeftMenu);

        arrayList = new ArrayList<MyMenu>();
        arrayList.add(new MyMenu("Закуски"));
        arrayList.add(new MyMenu("Горячие Блюда"));
        arrayList.add(new MyMenu("Выпечка"));
        arrayList.add(new MyMenu("Фото блюд"));




        ListLeftAdapter listLeftAdapter = new ListLeftAdapter(base, arrayList);

        leftMenu.setAdapter(listLeftAdapter);
        leftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position+1) {
                    case 1:
                        setTitle("Закуски");
                        LightEatFragment lightEatFragment = new LightEatFragment();
                        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main_fraim, lightEatFragment).commit();
                        break;
                    case 2:
                        setTitle("Горячие Блюда");
                        HotEatFragment hotEatFragment = new HotEatFragment();
                        android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                        fragmentManager1.beginTransaction().replace(R.id.main_fraim, hotEatFragment).commit();
                        break;
                    case 3:
                        setTitle("Выпечка");
                        SweetFragment waterFragment = new SweetFragment();
                        android.support.v4.app.FragmentManager fragmentManager3 = getSupportFragmentManager();
                        fragmentManager3.beginTransaction().replace(R.id.main_fraim, waterFragment).commit();
                        break;
                    case 4:
                        setTitle("Фото блюд");
                        PhotoFrag photoFragment = new PhotoFrag();
                        //sweetEatFragment.arrayList1 = arrayList1;
                        android.support.v4.app.FragmentManager fragmentManager2 = getSupportFragmentManager();
                        fragmentManager2.beginTransaction().replace(R.id.main_fraim, photoFragment).commit();
                        break;
                }
                drawerLayout.closeDrawers();
            }
        });
    }


    @Override
    protected void onResume() {
        sp = PreferenceManager.getDefaultSharedPreferences(base);

        boolean theme = sp.getBoolean("myThemeDark", false);

        if(theme == true)
        {
            drawerLayout.setBackgroundResource(R.drawable.fon1);
        }
        else drawerLayout.setBackgroundResource(R.drawable.fon2);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(base, Preferences.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
