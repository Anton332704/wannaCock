package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by арт on 27.10.2015.
 */
public class LightEatAdapter extends BaseAdapter {
    ArrayList<LightEatItem> arrayLightEat;
    Context context;
    LayoutInflater layoutInflater;

    public LightEatAdapter(ArrayList<LightEatItem> arrayLightEat, Context context) {
        this.arrayLightEat = arrayLightEat;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayLightEat.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayLightEat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_light_eat, parent, false);
        LightEatItem lightEatItem = (LightEatItem)getItem(position);
        final String desc = lightEatItem.Desc;
        final String nameStr = lightEatItem.Name;
        final String timeStr = lightEatItem.TimeCook;
        final int levelInt = lightEatItem.level;
        ((TextView) view.findViewById(R.id.textViewNameLight)).setText(nameStr);
        ((TextView) view.findViewById(R.id.textViewTimeCookLight)).setText(timeStr);
        ((TextView) view.findViewById(R.id.textViewLevelLight)).setText(levelInt + "");

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                arrayLightEat.remove(position);
                notifyDataSetChanged();
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoEat.class);
                intent.putExtra("desc", desc);
                intent.putExtra("name", nameStr);
                intent.putExtra("time", timeStr);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
