package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by арт on 13.10.2015.
 */
public class PhotoFrag extends Fragment
{

    ArrayList<SweetList> arrayList1;
    ListView photoList;
    ArrayList<Bitmap> arrayList;
    SweetAdapter sweetAdapter;
    Context cont;
    ImageView imageView;
    //final Context newcon = new ContextThemeWrapper(this, R.style.MyTextViewStyle);

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        cont = context;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null)
        {
            if(requestCode == 1)
            {
                Bitmap btm = (Bitmap)data.getExtras().get("data");
                //arrayList.add(btm);
                imageView.setImageBitmap(btm);

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //arrayList.notify();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_eat_frag, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageViewPhoto);
        /*photoList = (ListView)view.findViewById(R.id.listViewPhoto);
        arrayList = new ArrayList<Bitmap>();
        Bitmap icon = BitmapFactory.decodeResource(cont.getResources(), R.drawable.sms);
        arrayList.add(icon);*/
        Button btnMake = (Button) view.findViewById(R.id.buttonMakePhoto);
        btnMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,1);
            }
        });

        //PhotoAdapter photoAdapter = new PhotoAdapter(arrayList, cont);

        //photoList.setAdapter(photoAdapter);

        return view;
}
}
