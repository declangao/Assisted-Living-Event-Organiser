                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package nz.ac.wintec.soit.af5;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.xerces.impl.xpath.XPath.Step;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.SubMenu;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import nz.ac.wintec.soit.af5.client.MyRequestFactory;
import nz.ac.wintec.soit.af5.server.Event;
import nz.ac.wintec.soit.af5.shared.Af5Request;
import nz.ac.wintec.soit.af5.shared.EventProxy;
import nz.ac.wintec.soit.af5.shared.SeniorProxy;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main activity - requests "Hello, World" messages from the server and provides
 * a menu item to invoke the accounts activity.
 */
public class Af5Activity extends SherlockActivity{
	
	//long tempID;
	//EventProxy tempEvent;
	
    ArrayList < EventProxy > myEventList = new ArrayList<EventProxy>();
    
    private SharedPreferences preference;
    
    TextView tvDesc;
    TextView tvDueDate;
    ImageButton btnPrevious;
    ImageButton btnNext;
    ProgressDialog progressDialog=null;
    
    /**
     * The current context.
     */
    private Context mContext = this;
    
    String gmail=null;
    String uri=null;
    int year;
	int month;
	int day;
	int hour;
	int minute;
    
	Intent intent;
	int index;
	
    /**
     * Begins the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        intent = new Intent();
    	intent.setAction("nz.ac.wintec.soit.af5.AFSERVICE");

        AccountManager account=(AccountManager)getSystemService(ACCOUNT_SERVICE);
        Account[] list=account.getAccounts();
        for(Account a:list) {
        	if(a.type.equalsIgnoreCase("com.google")) {
        		gmail=a.name.toLowerCase();
        		break;
        	}
        }
        //gmail="Rob.J@gmail.com".toLowerCase();//Remove this line when in production mode
        
    	tvDesc=(TextView)findViewById(R.id.tvDesc);
    	tvDueDate=(TextView)findViewById(R.id.tvDueDate);
    	
    	/*
    	Button btn=(Button)findViewById(R.id.button);
    	btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyRequestFactory requestFactory = Util.getRequestFactory(getBaseContext(), MyRequestFactory.class);
				System.out.println("Step 1");
				Long tempid=(long)48002;
				System.out.println("Step 2");
				requestFactory.af5Request().readEvent(tempid).fire(new Receiver<EventProxy>() {

					@Override
					public void onFailure(ServerFailure error) {
						// TODO Auto-generated method stub
						System.out.println("Main GetEvent failed");
					}

					@Override
					public void onSuccess(EventProxy arg0) {
						// TODO Auto-generated method stub
						System.out.println("step 3");
						//List<EventProxy> list=arg0;
						System.out.println("Step 4");
						tvDesc.setText(arg0.getDescription());
						System.out.println("Step 5");
					}
				});
			}
		});
		*/
    	
    	ImageButton btnRefresh=(ImageButton)findViewById(R.id.btnRefresh);
    	btnRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //download();
                	System.out.println("GetEvent Started");
                	
                	new AsyncTask < Void, Void, Void > () {
    					@Override
    					protected Void doInBackground(Void... params) {
    						//getEvents();
    						download();
    						return null;
    					}
    				}
    				.execute();
                	//getEvents();
                    //Toast.makeText(mContext, "Events downloaded from the server", Toast.LENGTH_LONG).show();
                }
            });

        
        btnPrevious=(ImageButton)findViewById(R.id.btnPrevious);
        btnPrevious.setEnabled(false);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				index--;
				if(index<0) {
					index=myEventList.size()-1;
				}
				displayEventAtIndex(index);
				/*
				if(!isServiceAlive()) {
		        	startService(intent);
		        }
		        */
			}
		});
        
        btnNext=(ImageButton)findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				index++;
				if(index>=myEventList.size()) {
					index=0;
				}
				displayEventAtIndex(index);
				//stopService(intent);
			}
		});
        
        preference=getSharedPreferences("nz.ac.wintec.soit.af5_preferences", MODE_WORLD_READABLE);
        boolean autoDownload=preference.getBoolean("autoDownload", false);
        if(autoDownload) {
        	download();
        }else {
        	//Start service if it's not been started or has been killed
        	if(!isServiceAlive()) {
            	startService(intent);
            }
		}
        
        /*
        //Start service if it's not been started or has been killed
    	if(!isServiceAlive()) {
        	startService(intent);
        }
        */
    }
    
    /*
    @Override
	protected void onResume() {
    	super.onResume();
		SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(this);
		//etResult.append(pref.getString("ringtone", "Unset"));
	}
	*/

	@Override
 	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getSupportMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		
 		return super.onCreateOptionsMenu(menu);
 	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, SettingsPref.class));
			break;
		case R.id.startService:
			if(!isServiceAlive()) {
            	startService(intent);
            }
			Toast.makeText(mContext, R.string.service_started, Toast.LENGTH_LONG).show();
			break;
		case R.id.stopService:
			stopService(intent);
			Toast.makeText(mContext, R.string.service_stopped, Toast.LENGTH_LONG).show();
			break;
		case R.id.torch:
			startActivity(new Intent(mContext, Torch.class));
			break;
		case R.id.about:
			AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
			builder.setTitle(R.string.about_title);
			builder.setIcon(R.drawable.about);
			builder.setMessage(R.string.about_msg);
			builder.setPositiveButton(R.string.about_ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			builder.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
    
    private void download() {
    	System.out.println("Download started");
    	runOnUiThread(new Runnable() {
			public void run() {
				progressDialog=ProgressDialog.show(mContext, mContext.getString(R.string.progress_title), mContext.getString(R.string.progress_msg), true);
		    	new Thread() {

					@Override
					public void run() {
						getEvents();
						handler.sendEmptyMessage(0);
					}
					
				}.start();
			}
		});
    	
    }
    private Handler handler=new Handler() {

		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();
			Toast.makeText(mContext, R.string.toast_download_finished, Toast.LENGTH_LONG).show();
			
			//Start service if it's not been started or has been killed
			if(!isServiceAlive()) {
	        	startService(intent);
	        }
		}
    	
    };

    /*
	private void sendEvent() {
        MyRequestFactory requestFactory = Util.getRequestFactory(mContext, MyRequestFactory.class);
        final Af5Request request2 = requestFactory.af5Request();
        
        EventProxy event = request2.create(EventProxy.class);
        
        event.setDescription("Alarm button pressed");
        event.setDueDate(new Date());
        event.setEmailAddress("still to do");
        
        request2.updateEvent(event).fire();
    }
    */
    
	private void getEvents() {
		System.out.println("Start reading events in main activity");
		
		if(isOnline()) {
			MyRequestFactory requestFactory = Util.getRequestFactory(mContext, MyRequestFactory.class);
	        //requestFactory.af5Request().queryEvents().fire(new Receiver < List < EventProxy >> () {
			requestFactory.af5Request().queryEvents().fire(new Receiver < List < EventProxy >> () {
	                @Override
	                public void onSuccess(List < EventProxy > events) {
	                	myEventList.clear();
	                	
	                    if (events == null) {
	                        return;
	                    }
	                    for (EventProxy event : events) {
	                    	long timeDiffInMiliSec=event.getDueDate().getTime()-new Date().getTime();
	                    	int timeDiffInSec=(int)timeDiffInMiliSec/1000;
	                    	if (timeDiffInSec>0) {
	                    		if(gmail.equals(event.getEmailAddress().toLowerCase())) {
	                            	myEventList.add(event);
	                            }
							}   
	                    }
	                    
	                    //Toast.makeText(mContext, "Events retrieved", Toast.LENGTH_SHORT).show();
	                    System.out.println("Finished reading events in main activity");
	                    
	                    if(!myEventList.isEmpty()) {
	                    	index=0;
	                    	runOnUiThread(new Runnable() {
								public void run() {
									displayEventAtIndex(index);
									btnPrevious.setEnabled(true);
			                    	btnNext.setEnabled(true);
								}
							});
	                    }else {
	                    	runOnUiThread(new Runnable() {
								public void run() {
									btnPrevious.setEnabled(false);
			                    	btnNext.setEnabled(false);
			                    	tvDesc.setText(R.string.no_new_event);
			                    	tvDueDate.setText(R.string.no_new_event);
								}
							});
						}
	                    //createAlarm(eventList.get(1));  
	                }

					@Override
					public void onFailure(ServerFailure error) {
						// TODO Auto-generated method stub
						System.out.println("Main GetEvent failed");
						//super.onFailure(error);
					}
	            });
		}else {
			runOnUiThread(new Runnable() {
				public void run() {
					AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
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
    	
        System.out.println("GetEvents finished in main activity");
	}
	
	private void displayEventAtIndex(int n) {
		EventProxy event=myEventList.get(n);
		tvDesc.setText(event.getDescription());
		getDateDetails(event);
		//tvDueDate.setText(hour+":"+minute+" on "+day+"/"+(month+1)+"/"+year);
		tvDueDate.setText(hour+":"+minute+"  "+day+"/"+(month+1)+"/"+year);
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
	}
    
    private boolean isServiceAlive() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("nz.ac.wintec.soit.af5.AFService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
 