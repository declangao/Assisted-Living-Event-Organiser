package nz.ac.wintec.soit.af5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LaunchReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent launchIntent=new Intent(context, AFService.class);
		context.startService(launchIntent);
	}
	
}
