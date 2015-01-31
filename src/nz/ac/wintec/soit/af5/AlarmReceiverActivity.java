package nz.ac.wintec.soit.af5;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import nz.ac.wintec.soit.af5.client.MyRequestFactory;
import nz.ac.wintec.soit.af5.shared.Af5Request;
import nz.ac.wintec.soit.af5.shared.EventProxy;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmReceiverActivity extends Activity {
	private MediaPlayer mMediaPlayer;
	private PowerManager.WakeLock wl;
	private Vibrator vibrator;
	private SharedPreferences preferences;
	
	long id;
	EventProxy event;
	int exeTime;
	
	TextView tvDesc;
	TextView tvTD;
	
	int year;
	int month;
	int day;
	int hour;
	int minute;
	int duration;
	String uri=null;
	String desc=null;
	
	String email=null;
	long seniorID;
	int executedTimes;
	long orgTime;
	int delayTime=0;
	
	long[] pattern = { 0, 1000, 1000 };

	
	ArrayList < EventProxy > eventList = new ArrayList<EventProxy>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Tag");
        wl.acquire();
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | 
        	    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | 
        	    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | 
        	    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
        	    WindowManager.LayoutParams.FLAG_FULLSCREEN | 
        	    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | 
        	    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | 
        	    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.alarmreceiver);
        
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        
        preferences=getSharedPreferences("nz.ac.wintec.soit.af5_preferences", MODE_WORLD_READABLE);
        uri=preferences.getString("ringtone", null);
        
        tvDesc=(TextView)findViewById(R.id.tvDesc);
        tvTD=(TextView)findViewById(R.id.tvTD);
        
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        /*
        Log.d("Bundle","contains title = " + bundle.containsKey("title"));
        Log.d("Bundle","is empty? = " + bundle.isEmpty());
        Log.d("Bundle","to string = " + bundle.toString());
        */
        
        /*
        if(bundle.containsKey("id")) {	
        	id=bundle.getLong("id");
        	MyRequestFactory requestFactory = Util.getRequestFactory(getBaseContext(), MyRequestFactory.class);
            requestFactory.af5Request().readEvent(id).fire(new Receiver<EventProxy>() {

				@Override
				public void onSuccess(EventProxy eventProxy) {
					event=eventProxy;
				}
			});
        }
        */
        
        if(bundle.containsKey("desc")) {
        	desc=bundle.getString("desc");
        	tvDesc.setText(desc);
        	//tvDesc.setText(desc+" with id: "+bundle.getInt("id"));
        }
        if(bundle.containsKey("id")) {
        	id=bundle.getInt("id");
        }
        if(bundle.containsKey("year")) {
        	year=bundle.getInt("year");
        }
        if(bundle.containsKey("month")) {
        	month=bundle.getInt("month");
        }
        if(bundle.containsKey("day")) {
        	day=bundle.getInt("day");
        }
        if(bundle.containsKey("hour")) {
        	hour=bundle.getInt("hour");
        }
        if(bundle.containsKey("minute")) {
        	minute=bundle.getInt("minute");
        }
        if(bundle.containsKey("ringtone") && bundle.getString("ringtone") != null) {
        	uri=bundle.getString("ringtone");
        }
        
        
        if(bundle.containsKey("email")) {
        	email=bundle.getString("email");
        }
        if(bundle.containsKey("seniorID")) {
        	seniorID=bundle.getLong("seniorID");
        }
        if(bundle.containsKey("executedTimes")) {
        	executedTimes=bundle.getInt("executedTimes");
        }
        if(bundle.containsKey("orgTime")) {
        	orgTime=bundle.getLong("orgTime");
        }
        if(bundle.containsKey("delayTime")) {
        	delayTime=bundle.getInt("delayTime");
        }
        
        String dt=hour+":"+minute+" "+day+"/"+(month+1)+"/"+year+" ";
        tvTD.setText("Time and date for the event is: \n"+dt);
 
        Button btnLater = (Button) findViewById(R.id.btnStop);
        btnLater.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
            	stopAlarm();
            	remindMeLater();
                finish();
                return false;
            }
        });
        
        Button btnAcknowledge=(Button)findViewById(R.id.btnAck);
        btnAcknowledge.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopAlarm();
				new AsyncTask < Void, Void, Void > () {
					@Override
					protected Void doInBackground(Void... params) {
						if(isOnline()) {
							//getEvents();
							getEventByID(id);
							sendACK(1);
						}else {
							runOnUiThread(new Runnable() {
								public void run() {
									AlertDialog.Builder builder=new AlertDialog.Builder(AlarmReceiverActivity.this);
									builder.setTitle(R.string.no_internet_title);
									builder.setIcon(R.drawable.error);
									builder.setMessage(R.string.no_internet_msg);
									builder.setPositiveButton(R.string.no_internet_ok, new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
										}
									});
									builder.show();
								}
							});
						}
						
						return null;
					}
				}
				.execute();
                finish();
			}
		});
        
        Button btnDeny=(Button)findViewById(R.id.btnDeny);
        btnDeny.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopAlarm();
				new AsyncTask < Void, Void, Void > () {
					@Override
					protected Void doInBackground(Void... params) {
						if(isOnline()) {
							//getEvents();
							getEventByID(id);
							sendACK(0);
						}else {
							runOnUiThread(new Runnable() {
								public void run() {
									AlertDialog.Builder builder=new AlertDialog.Builder(AlarmReceiverActivity.this);
									builder.setTitle(R.string.no_internet_title);
									builder.setIcon(R.drawable.error);
									builder.setMessage(R.string.no_internet_msg);
									builder.setPositiveButton(R.string.no_internet_ok, new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
										}
									});
									builder.show();
								}
							});
						}
						
						return null;
					}
				}
				.execute();
                finish();
			}
		});
        
        playSound(this, getAlarmUri());
        vibrator.vibrate(pattern, 0);
    }
    
    private void getEventByID(long eventID) {
    	MyRequestFactory requestFactory = Util.getRequestFactory(getBaseContext(), MyRequestFactory.class);
		requestFactory.af5Request().readEvent(eventID).fire(new Receiver<EventProxy>() {

			@Override
			public void onSuccess(EventProxy arg0) {
				event=arg0;
				System.out.println("Got event");
			}

			@Override
			public void onFailure(ServerFailure error) {
				System.out.println("Alarmreceiver GetEvent failed");
			}
			
		});
	}
    
    private void getEvents() {
    	MyRequestFactory requestFactory = Util.getRequestFactory(this, MyRequestFactory.class);
        requestFactory.af5Request().queryEvents().fire(new Receiver < List < EventProxy >> () {
                @Override
                public void onSuccess(List < EventProxy > events) {
                	eventList.clear();
                	
                    if (events == null) {
                        return;
                    }
                    for (EventProxy event : events) {
                        eventList.add(event);
                    }
                }
            });
        
	}
    
    private EventProxy getEvent(Long eid) {
    	if (eid != null) {
            for (EventProxy eventProxy: eventList) {
                if (eventProxy.getId().longValue() == eid.longValue()) {
                    return eventProxy;
                }
            }
        }
        return null;
    	/*
    	MyRequestFactory requestFactory = Util.getRequestFactory(this, MyRequestFactory.class);
        requestFactory.af5Request().readEvent(eid).fire(new Receiver<EventProxy>() {

			@Override
			public void onSuccess(EventProxy eventProxy) {
				event=eventProxy;
			}
		});
        
        return event;
        */
    }
    
    private void sendACK(Integer ack) {
    	boolean delete;
    	
    	MyRequestFactory requestFactory = Util.getRequestFactory(this, MyRequestFactory.class);
        final Af5Request request = requestFactory.af5Request();
        final Af5Request request2 = requestFactory.af5Request();
        
        System.out.println("Start sending ACK");
        //if(getEvent(id)==null) {
    	if(event==null) {
        	deleteAlarm();
        	return;
        }
        EventProxy temp=request2.edit(event);
        //EventProxy temp=request2.edit(getCurrentEvent());
        
        System.out.println("executedTimes is "+temp.getExecutedTimes());
        System.out.println("exeTime is "+exeTime);
        exeTime=temp.getExecutedTimes()+1;
        System.out.println("exeTime is now "+exeTime);
        
        if(exeTime>=temp.getDuration()) {
        	delete=true;
        }else {
			delete=false;
		}
        
        try {
        	//tempEvent.setACK(true);
        	temp.setACK(ack);
        	if(!delete) {
        		temp.setExecutedTimes(exeTime);
        	}
		} catch (Exception e) {
			Log.e("test", e.toString());
		}
        
        request2.updateEvent(temp).fire();
        
        if(delete) {
        	deleteAlarm();
        }
        
        System.out.println("ACK sent");
        
        runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				showMessage(getString(R.string.alarmreceiver_ack));
			}
		});
        
    	/*
    	if(getIntent().getExtras().containsKey("id")) {	
        	id=getIntent().getExtras().getLong("id");
        	MyRequestFactory requestFactory = Util.getRequestFactory(getBaseContext(), MyRequestFactory.class);
            requestFactory.af5Request().readEvent(id).fire(new Receiver<EventProxy>() {

				@Override
				public void onSuccess(EventProxy eventProxy) {
					event=eventProxy;
				}
			});
        }
        */
	}
    
    private void deleteAlarm() {
    	System.out.println("Start delete alarm");
    	Intent intent = new Intent(this, AlarmReceiverActivity.class);
    	System.out.println("Step 1 finished");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
            (int)id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        System.out.println("Step 2 finished");
        AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        System.out.println("Step 3 finished");
        am.cancel(pendingIntent);
        System.out.println("Step 4 finished");
        
        System.out.println("Alarm deleted");
	}
    
    private void remindMeLater() {
    	Bundle bundle=new Bundle();
    	
    	int intID=(int)id;
    	
    	bundle.putString("desc", desc);
		bundle.putInt("year", year);
		bundle.putInt("month", month);
		bundle.putInt("day", day);
		bundle.putInt("hour", hour);
		bundle.putInt("minute", minute);
		bundle.putInt("id", intID);
		bundle.putString("ringtone", uri);
		bundle.putInt("duration", duration);
		bundle.putLong("orgTime", orgTime);
		bundle.putInt("delayTime", delayTime+1);
		//System.out.println("Delay time is now "+delayTime);
		
		int delay=Integer.parseInt(preferences.getString("delay", "5"));
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MINUTE, delay);
		
		Calendar orgCal=Calendar.getInstance();
		orgCal.setTimeInMillis(orgTime);
		orgCal.add(Calendar.DAY_OF_MONTH, 1);
		
		
		Intent intent = new Intent(this, AlarmReceiverActivity.class);
        intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
            intID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        if(duration==1) {
        	am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
        	//TODO:Problem, it delays the time for future events as well...
        	//am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        	//am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis()-delayTime*delay*60*1000, 1000*60*60*24, pendingIntent);
        	am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 1000*60*60*24, pendingIntent);
        	//System.out.println("Delayed time "+delayTime*delay*60*1000);
        }
        //Toast.makeText(AlarmReceiverActivity.this, "Remind you again in "+delay, Toast.LENGTH_LONG);
	}
    
    private void showMessage(String msg){
    	Context context = getApplicationContext();
    	//CharSequence text = "Hello toast!";
    	int duration = Toast.LENGTH_LONG;

    	Toast toast = Toast.makeText(context, msg, duration);
    	toast.show();
    }
    
    protected void onStop() {
        super.onStop();
        if(wl.isHeld()) {
        	wl.release();
        }
    }
    
    private void stopAlarm() {
    	mMediaPlayer.stop();
    	vibrator.cancel();
	}
    
    private void playSound(Context context, Uri alert) {
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(context, alert);
            final AudioManager audioManager = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }
    }
    
    private Uri getAlarmUri() {
    	if(uri==null) {
    		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alert == null) {
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                if (alert == null) {
                    alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                }
            }
            return alert;
    	}
        else {
        	Uri alert=Uri.parse(uri);
			return alert;
		}
    }
    
    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
