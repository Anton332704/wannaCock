package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 23.12.2015.
 */
public class MyCursorAdapter extends ResourceCursorAdapter {

    public MyCursorAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
