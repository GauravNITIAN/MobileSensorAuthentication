package com.example.sesorproject;

import java.util.List;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView textviewAzimuth, textviewPitch, textviewRoll;
	 private static SensorManager mySensorManager;
	 private boolean sersorrunning;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		    }
	 @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	 
	        // Checks the orientation of the screen
	        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	        }
		  textviewAzimuth = (TextView)findViewById(R.id.textazimuth);
	       textviewPitch = (TextView)findViewById(R.id.textpitch);
	       textviewRoll = (TextView)findViewById(R.id.textroll);

	       mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	       List<Sensor> mySensors = mySensorManager.getSensorList(Sensor.TYPE_ALL);
	  
	       if(mySensors.size() > 0){
	        mySensorManager.registerListener(mySensorEventListener, mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
	        sersorrunning = true;
	        Toast.makeText(this, "Start ORIENTATION Sensor", Toast.LENGTH_LONG).show();
	       }
	       else{
	        Toast.makeText(this, "No ORIENTATION Sensor", Toast.LENGTH_LONG).show();
	        sersorrunning = false;
	        finish();
	       }

	   }
	  
	   private SensorEventListener mySensorEventListener = new SensorEventListener() {
	  
	  @Override
	  public void onSensorChanged(SensorEvent event) {
	   // TODO Auto-generated method stub
	   
	           textviewAzimuth.setText("Azimuth: " + String.valueOf(event.values[0]));
	           textviewPitch.setText("Pitch: " + String.valueOf(event.values[1]));
	           textviewRoll.setText("Roll: " + String.valueOf(event.values[2]));

	  }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	};
	};
	 @Override
	 protected void onDestroy() {
	  // TODO Auto-generated method stub
	  super.onDestroy();
	  
	  if(sersorrunning){
	   mySensorManager.unregisterListener(mySensorEventListener);
	   Toast.makeText(this, "unregisterListener", Toast.LENGTH_SHORT).show();
	  }
}
}