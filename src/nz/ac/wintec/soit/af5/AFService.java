package nz.ac.wintec.soit.af5;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import nz.ac.wintec.soit.af5.client.MyRequestFactory;
import nz.ac.wintec.soit.af5.shared.EventProxy;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class AFService extends Service {

	//ArrayList <EventProxy> eventList = new ArrayList<EventProxy>();
    ArrayList <EventProxy> myEventList = new ArrayList<EventProxy>();
    
    private Context mContext = this;
    private SharedPreferences preference;
    
    boolean quit=false;
    
    int interval;
    
    String gmail=null;
    
    String uri=null;
    int year;
	int month;
	int day;
	int hour;
	int minute;
	int duration;
	boolean repeat=false;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		AccountManager account=(AccountManager)getSystemService(ACCOUNT_SERVICE);
        Account[] list=account.getAccounts();
        for(Account a:list) {
        	if(a.type.equalsIgnoreCase("com.google")) {
        		gmail=a.name.toLowerCase();
        		break;
        	}
        }
        //gmail="Rob.J@gmail.com".toLowerCase();//Remove this line when in production mode
        
        preference=getSharedPreferences("nz.ac.wintec.soit.af5_preferences", MODE_WORLD_READABLE);
        
        System.out.println("---Service created---");
        
        System.out.println("gmail: "+gmail);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		System.out.println("---Service started---");
		
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				while (!quit) {
					getEvents();
					interval=Integer.parseInt(preference.getString("interval", "30"));
					
					//System.out.println("Thread is running!");
			        System.out.println("Interval is "+interval+" ");
			        
					try {
						Thread.sleep(interval*1000);//second
						//Thread.sleep(interval*1000*60);//minute
						//Thread.sleep(interval*1000*60*60);//hour
					} catch (Exception e) {
						
					}
				}
				Looper.loop();
			}
		}.start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		this.quit=true;
		System.out.println("---Service destroyed---");
	}

	private void getEvents() {
		System.out.println("Service starts reading events");
		
		if(isOnline()) {
			MyRequestFactory requestFactory = Util.getRequestFactory(mContext, MyRequestFactory.class);
	        requestFactory.af5Request().queryEvents().fire(new Receiver < List < EventProxy >> () {
	                @Override
	                public void onSuccess(List < EventProxy > events) {
	                	//eventList.clear();
	                	myEventList.clear();
	                	
	                    if (events == null) {
	                        return;
	                    }
	                    for (EventProxy event : events) {
	                    	long timeDiffInMiliSec=event.getDueDate().getTime()-new Date().getTime();
	                    	int timeDiffInSec=(int)timeDiffInMiliSec/1000;
	                    	if (timeDiffInSec>0) {
	                    		//eventList.add(event);
	                    		//if(gmail.toLowerCase().equals(event.getEmailAddress().toLowerCase())) {
	                    		if(gmail.equals(event.getEmailAddress().toLowerCase())) {
	                            	myEventList.add(event);
	                            }
							}else {
								System.out.println("Create alarm skipped for "+event.getDescription());
							}
	                    }
	                    
	                    if(!myEventList.isEmpty()) {
	                    	for(EventProxy event:myEventList) {
	                        	createAlarm(event);
	                        }
	                    }
	                }

					@Override
					public void onFailure(ServerFailure error) {
						// TODO Auto-generated method stub
						System.out.println("GetEvent failed");
						//super.onFailure(error);
					}
	            });
		}
		
		System.out.println("Service finished reading events");
	}
	
	private void createAlarm(EventProxy event) {
		Bundle bundle=new Bundle();
		
		int id=event.getId().intValue();
		
		String desc=event.getDescription();
		getDateDetails(event);
		
		bundle.putString("desc", desc);
		bundle.putInt("year", year);
		bundle.putInt("month", month);
		bundle.putInt("day", day);
		bundle.putInt("hour", hour);
		bundle.putInt("minute", minute);
		bundle.putInt("id", id);
		bundle.putString("ringtone", uri);
		bundle.putInt("duration", duration);
		
		//test
		bundle.putString("email", event.getEmailAddress());
		bundle.putLong("seniorID", event.getSeniorId());
		bundle.putInt("executedTimes", event.getExecutedTimes());
		
		Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, 0);
        long orgTime=cal.getTimeInMillis();
        bundle.putLong("orgTime", orgTime);
        
        //Create a new PendingIntent and add it to the AlarmManager
        Intent intent = new Intent(this, AlarmReceiverActivity.class);
        intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
            id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am =
            (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        if(event.getDuration()==1) {
        	am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                    pendingIntent);
        }else {
        	am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 
        			1000*60*60*24, pendingIntent);
		}
        
        System.out.println("Alarm created for event\n"+desc+
				"\nAt "+hour+":"+minute+" "+day+"/"+(month+1)+"/"+year);
        /*
        Toast.makeText(this, "Alarm created for event\n"+desc+
				"\nAt "+hour+":"+minute+" "+day+"/"+month+"/"+year, Toast.LENGTH_LONG).show();
        etResult.append("Alarm created for event\n"+desc+"\nAt "+hour+":"+minute+" "+day+"/"+month+"/"+year+"\n");
        etResult.append(cal.getTime().toString());
        */
    }
	
	private void getDateDetails(EventProxy event) {
    	Date date=event.getDueDate();
    	//Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.setTimeZone(TimeZone.getTimeZone("GMT+12:00"));
    	//String desc=event.getDescription();
    	
		year=calendar.get(Calendar.YEAR);
		month=calendar.get(Calendar.MONTH);
		day=calendar.get(Calendar.DAY_OF_MONTH);
		hour=calendar.get(Calendar.HOUR_OF_DAY);
		minute=calendar.get(Calendar.MINUTE);
		//etResult.append("\n"+year+" "+month+" "+day+" "+hour+" "+minute);
		
		duration=event.getDuration();
		
		if(event.getDuration()>1) {
			repeat=true;
		}
		else {
			repeat=false;
		}
	}
	
	private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
