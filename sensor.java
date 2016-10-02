package com.hp.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private Sensor accelerometer;
	private Sensor orientation;
	TextView tv,tv1;
	private float lastX =0, lastY=0, lastZ=0;

	private float deltaX = 0;
	private float deltaY = 0;
	private float deltaZ = 0;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv=(TextView) findViewById(R.id.textView1);
		tv1=(TextView) findViewById(R.id.textView2);
		
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
	//	-----------------------------------------------------------------------------------------------------------------
		if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			// success! we have an accelerometer

			accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
			
			
		} else {
			
			Toast.makeText(getApplicationContext(),  accelerometer.toString(), Toast.LENGTH_SHORT).show();
		}
	//----------------------------------------------------------------------------------------------------------------	
		
		/*if (sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null) {
			// success! we have an accelerometer

			orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
			sensorManager.registerListener(this, orientation, SensorManager.SENSOR_DELAY_NORMAL);
			
			
		} else {
			
			Toast.makeText(getApplicationContext(), orientation.toString(), Toast.LENGTH_SHORT).show();
		}
		*/
	//--------------------------------------------------------------------------------------------------------------	
		
		
	
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		
		// get the change of the x,y,z values of the accelerometer
	
		    deltaX = Math.abs(lastX - event.values[0]);
	
		    deltaY = Math.abs(lastY - event.values[1]);
		
		    deltaZ = Math.abs(lastZ - event.values[2]);

		
		    
		 // set the last know values of x,y,z
		    
		        lastX = event.values[0];
		    
		        lastY = event.values[1];
		    
		        lastZ = event.values[2];

		
		        tv.setText("Accl in X Axis"+String.valueOf(deltaX)+"\nAccl in Y Axis"
		        +String.valueOf(deltaY)+"\nAccl in Z Axis"+String.valueOf(deltaZ));
		        
		        
		        
		// Orientation code is here
		   /*     if (sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null) {  
		        float azimuthal=event.values[0];
				float angle=event.values[1];
				float roll=event.values[2];
				String str="Azimuthal :"+String.valueOf(azimuthal)+"\n"+"Angle :"+String.valueOf(angle)+"\n"+
				"Roll :"+String.valueOf(azimuthal);
				tv1.setText(str);  
		        }
		        */
	}

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sensorManager.unregisterListener(this);
		
	}
	
	
	
}