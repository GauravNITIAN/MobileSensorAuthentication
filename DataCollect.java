package com.nitp.mobilesensorauth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DataCollect extends Activity implements SensorEventListener{

	EditText texts;
	
	private static long textend,textstart,textDiff;
	private static int count=0;
	
	float azimuthal,pitch,roll,bAzi,bPit,bRol,aAzi,aPit,aRol;
	private float lastX =0, lastY=0, lastZ=0;

	private float deltaX = 0;
	private float deltaY = 0;
	private float deltaZ = 0;
	
	String name;
	//---------------------------------------------Sensor Variables-----------------------------------------------------------//
	 private static SensorManager mySensorManager,sensorManager;
	 private boolean sersorrunning;
	 private Sensor accelerometer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_collect);
		
		texts = (EditText) findViewById(R.id.editText1);
		name = MainActivity.name;
		
		 
		
		//---------------------------------------------------Acclerotmeter-----------------------------------------------------=//



				  sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


					if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
						// success! we have an accelerometer

						accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
						sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
						
						
					} else {
						
						Toast.makeText(getApplicationContext(),  accelerometer.toString(), Toast.LENGTH_SHORT).show();
					}
				//----------------------------------------------------------------------------------------------------------------	
		
		
		
		
		
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
		
	       
	       
		
		
		
           texts.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
              textend=new Date().getTime();
	    		
              
              
              textDiff = textend - textstart;
              
              
			
              
              aAzi = azimuthal;
              aPit = pitch;
              aRol = roll;
              
             /* float dAzi = aAzi - bAzi;
              float dPit = aPit - bPit;
              float drol = aRol - bRol;
              */
              // Store in file
              String data = "["+textDiff+","+aAzi+","+aPit+","+aRol+","+deltaX+","+deltaY+","+deltaZ+","+name+"]";
             // Toast.makeText(getApplicationContext(), ""+data,Toast.LENGTH_SHORT).show();
              
              
              Save(data);
              
              
              bAzi = aAzi;
              bPit = aPit;
              bRol = aRol;
				
              
              
              
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
				textstart=new Date().getTime();
	    	     
				  bAzi = azimuthal;
	              bPit = pitch;
	              bRol = roll;
				
				
		    	    
		    	   
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
        
		
		
	}

	
	
	
	// -------------------------------------------------------------------------------------------------------------------  //
	
	public void Save(String data)
    {
    	
    	String filename="KeyStrokeData.txt";
        
    	   
    	
        try {  
        	
        	
        	 File myFile = new File(Environment
     	            .getExternalStorageDirectory(), filename);
     	    if (!myFile.exists())
     	        myFile.createNewFile();
             
             FileOutputStream fOut = new FileOutputStream(myFile,true);  
             OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);  
             
			myOutWriter.append(data+"\n"); 
			
             myOutWriter.close();  
             fOut.close();  
          
    // Toast.makeText(getApplicationContext(),data + "saved",Toast.LENGTH_LONG).show();  
           
          
        } catch (FileNotFoundException e) {e.printStackTrace();}  
        catch (IOException e) {e.printStackTrace();}  
    	
    	
    }	
//----------------------------------------------------Listiner for Orintation-----------------------------------
	
	
	private SensorEventListener mySensorEventListener = new SensorEventListener() {
		  
		  @Override
		  public void onSensorChanged(SensorEvent event) {
		   // TODO Auto-generated method stub
		   
		        azimuthal = event.values[0];
		        pitch = event.values[1];
		        roll = event.values[2];
		        
		      //  Toast.makeText(getApplicationContext(), "Azi="+azimuthal, Toast.LENGTH_SHORT).show();

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
		 
		 
public void close(View v)
{
	finish();
}





@Override
public void onAccuracyChanged(Sensor arg0, int arg1) {
	// TODO Auto-generated method stub
}


@Override
public void onSensorChanged(SensorEvent event) {
	// get the change of the x,y,z values of the accelerometer
	
    deltaX = Math.abs(lastX - event.values[0]);

    deltaY = Math.abs(lastY - event.values[1]);

    deltaZ = Math.abs(lastZ - event.values[2]);


    
 // set the last know values of x,y,z
    
        lastX = event.values[0];
    
        lastY = event.values[1];
    
        lastZ = event.values[2];


       
        
	
}



@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	sensorManager.unregisterListener(this);
	
}
	}

