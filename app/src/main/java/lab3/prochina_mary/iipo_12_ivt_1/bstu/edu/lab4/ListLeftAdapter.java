package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by арт on 12.10.2015.
 */
public class ListLeftAdapter extends BaseAdapter{

    Context context;
    ArrayList<MyMenu> arrayList;
    LayoutInflater layoutInflater;

    public ListLeftAdapter(Context context, ArrayList<MyMenu> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.item, parent, false);
        final MyMenu myMenu = (MyMenu) getItem(position);
        TextView txt = (TextView)v.findViewById(R.id.textViewItem);
        txt.setText(myMenu.getName());
        return v;
    }
}
