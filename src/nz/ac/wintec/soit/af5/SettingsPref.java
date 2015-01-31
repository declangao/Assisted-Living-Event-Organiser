package nz.ac.wintec.soit.af5;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsPref extends SherlockPreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.settingspref);
	}

}
