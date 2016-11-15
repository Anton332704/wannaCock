package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 29.12.2015.
 */
public class InfoEat extends AppCompatActivity {
    Context context;
    NotificationManager manager;
    EditText editDesc, editTime;
    TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_eat);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        context = this;
        editDesc = (EditText) findViewById(R.id.editTextDesc);
        editTime = (EditText) findViewById(R.id.editTextTimeCook);
        textName = (TextView)findViewById(R.id.textViewNameCook);
        editDesc.setText(getIntent().getStringExtra("desc"));
        editTime.setText(getIntent().getStringExtra("time"));
        textName.setText(getIntent().getStringExtra("name"));
    }

    public void startCook(View v)
    {
        try {
            int timeCooking = Integer.parseInt(editTime.getText().toString());
            Timer timer = new Timer();
            MyTimerTask myTimerTask = new MyTimerTask();
            timer.schedule(myTimerTask, timeCooking * 1000);
        }catch (Exception e)
        {
            Toast.makeText(context, "не удалось :С", Toast.LENGTH_LONG).show();
        }

    }

    class MyTimerTask extends TimerTask
    {
        @Override
        public void run() {

            Context mcontext = getApplicationContext();
            Notification.Builder builder = new Notification.Builder(mcontext);
            Resources res = mcontext.getResources();
            Uri ringURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);

            builder.setSmallIcon(R.drawable.cook)
                    .setSound(ringURI)
                    .setVibrate(new long[] { 1000, 1000, 1000, 1000})
                    .setContentIntent(contentIntent)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.cook))
                    .setTicker("Напоминание о готовности!")
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentTitle("Напоминание")
                    .setContentText("Блюдо уже готово");

            Notification notification = builder.build();

            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);

        }
    }
}
