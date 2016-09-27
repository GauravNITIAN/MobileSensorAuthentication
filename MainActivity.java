package com.example.mobilesensorprotection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;





import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//Date Variable
	double cdate1 = 0;
    double cdate2 = 0;
    
    // Sensor Variable
    private static SensorManager sensorService;
    private Sensor sensor;

	private long  timer;
	TextView text;
	TextView text2;
	Button stop;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		text=(TextView) findViewById(R.id.textView1);
		stop=(Button) findViewById(R.id.button1);
		text2=(TextView) findViewById(R.id.textView3);
		
		// Sensor Service get
		sensorService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		
		if (sensor != null) {
            sensorService.registerListener(mySensorEventListener, sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "aass", Toast.LENGTH_SHORT);
            
		}
		else
			Toast.makeText(this, "ss", Toast.LENGTH_SHORT);
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		}
	
	    private SensorEventListener mySensorEventListener = new SensorEventListener() {

	    	
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            	Toast.makeText(getApplicationContext(), "ss", Toast.LENGTH_SHORT);
            	 text2.setText("Orientation changed");
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
            	Toast.makeText(getApplicationContext(), "ss", Toast.LENGTH_SHORT);
            	 if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float accl = getAccelerometer(event);
            text2.setText(String.valueOf(accl));
            
             }
            	 
            	 if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                     float accl = getAccelerometer(event);
                     text2.setText("Orientation changed");
                     
                      }
            }

            protected void onDestroy() {
                    
                    if (sensor != null) {
                            sensorService.unregisterListener(mySensorEventListener);
                    }
            }
    };	
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	     //   Log.v("I am ","Keyup");
	      // text.setText("up");
			return false;
	  }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//text.setText("down");
		// Log.v("I am ","KeyDown");
		return false;
	}
	
	
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
	 Toast.makeText(this, "Pressed for a long time =) ", Toast.LENGTH_SHORT).show();
	 return true;
	}
	
	
	@Override
	public void onBackPressed() {
	 Toast.makeText(this, "Back key pressed =)", Toast.LENGTH_SHORT).show();
	 super.onBackPressed();
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent KEvent) 
	{
	    int keyaction = KEvent.getAction();
	    
	    double diff = 0;
	    if(keyaction == KeyEvent.ACTION_DOWN)
	    {
	    	cdate1 = System.currentTimeMillis();   
	    	text.setText("down");
	        
	    }
	    
	    if(keyaction == KeyEvent.ACTION_UP)
	    {
	    	cdate2 = System.currentTimeMillis(); 
	    	//diff = cdate2.getTime() - cdate1.getTime();
	    	text.setText("up"+(cdate2-cdate1) );
	    	//Log.v("I am ","KeyDown");
	        
	    }
	    
	    try {
			Save(diff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return super.dispatchKeyEvent(KEvent);
	}

	
	
	 private void Save(double data) throws IOException {
		 
		 File file = new File("/sdcard0/train.txt");
		
		 file.createNewFile();
		 String FILENAME = "storage/sdcard0/train.txt";
		/* File fn =new File(FILENAME);
		 if(!fn.exists())
		 {
			 fn.createNewFile();
		 }*/
		 FileOutputStream outputStream;

		 try {
			  outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			  outputStream.write(String.valueOf(data).getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
	         
	    }
	
	  private float getAccelerometer(SensorEvent event) {
          float[] values = event.values;
          // Movement
          float x = values[0];
          float y = values[1];
          float z = values[2];

          float accelationSquareRoot = (x * x + y * y + z * z)
                          / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
          
          return accelationSquareRoot;
	  }
}
