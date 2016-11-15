package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by арт on 12.10.2015.
 */
public class SweetAdapter extends BaseAdapter{

    Context context;
    ArrayList<SweetList> arrayList1;
    LayoutInflater layoutInflater;
    public SweetAdapter(Context context, ArrayList<SweetList> arrayList1) {
        this.context = context;
        this.arrayList1 = arrayList1;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View b = layoutInflater.inflate(R.layout.item2, parent, false);
        final SweetList sweetList = (SweetList) getItem(position);
//        TextView txt = (TextView)b.findViewById(R.id.textViewItem2);
        ((TextView)b.findViewById(R.id.textViewItem2)).setText(sweetList.getName().toString());
//        txt.setText(sweetList.getName());
        int y = 4;
        return b;
    }
}
