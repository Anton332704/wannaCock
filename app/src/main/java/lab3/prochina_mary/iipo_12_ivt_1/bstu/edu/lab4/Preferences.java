package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.os.Bundle;
import android.preference.PreferenceActivity;


/**
 * Created by арт on 09.11.2015.
 */
public class Preferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
