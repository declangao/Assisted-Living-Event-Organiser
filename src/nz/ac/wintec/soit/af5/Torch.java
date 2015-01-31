package nz.ac.wintec.soit.af5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Torch extends Activity {

	boolean locked=false;
	
	PowerManager powerManager;
	PowerManager.WakeLock wakeLock;
	
	LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.torch);
		
		layout=(LinearLayout)findViewById(R.id.layout);
		layout.setBackgroundColor(Color.WHITE);
		
		//powerManager=(PowerManager)getSystemService(POWER_SERVICE);
		powerManager=(PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "backlight");
		
		WindowManager.LayoutParams lp=getWindow().getAttributes();
		lp.screenBrightness=1.0f;
		getWindow().setAttributes(lp);
	}

	@Override
	protected void onPause() {
		unlock();
		super.onPause();
	}

	@Override
	protected void onResume() {
		lock();
		super.onResume();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return super.onTouchEvent(event);
	}

	private void lock() {
		if(!locked) {
			locked=true;
			wakeLock.acquire();
		}
	}
	
	private void unlock() {
		if(locked) {
			locked=false;
			wakeLock.release();
		}
	}

}
