package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by user on 29.12.2015.
 */
public class PhotoAdapter extends BaseAdapter {
    ArrayList<Bitmap> arrayList;
    Context context;
    LayoutInflater inflater;

    public PhotoAdapter(ArrayList<Bitmap> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View v = inflater.inflate(R.layout.item_photo, parent, false);
        Bitmap bitmap = (Bitmap)getItem(position);
        ((ImageView) v.findViewById(R.id.imageViewPhoto)).setImageBitmap(bitmap);
        return v;
    }
}
