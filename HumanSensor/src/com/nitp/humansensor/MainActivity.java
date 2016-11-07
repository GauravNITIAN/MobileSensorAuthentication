package com.nitp.humansensor;





import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText text;
	TextView textview;
	static String name = null;
	
	
	//Testing and Training file varibles 
		String test="TestData.txt";
		String Train="TrainData.txt";
		int featues = 9;   // no of features
		int treeCount = 12; // no of tree
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text=(EditText) findViewById(R.id.editText1);
		textview=(TextView) findViewById(R.id.textView1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	// call for training if training button is clicked
	public void train(View v)
	{
		if(text.getText().length() > 0)
		{
			name = text.getText().toString();
			Intent i = new Intent(this,DataCollect.class);
			startActivity(i);
		}
	
	}
	
	
	// cal for testing if testing button is clicked
	public void test(View v)
	{
		Intent i ;
		if(text.getText().length() > 0)
		{
			name = text.getText().toString();
			i = new Intent(this,TestDataCollect.class);
		    startActivity(i);
	}
	
	}
	
	
	public void Predict(View v) throws IOException
	{
	
		Train();
		
	}
	

	
	void Train() throws IOException
	{
		
		
		 	 
	    	 // BuildRandomForest for test data
	    	 
	    	
	    	
	    		 Toast.makeText(getApplicationContext(), "Aaaaaaaaaaaaa ", Toast.LENGTH_SHORT).show();		
	    // it the accuracy of test data		 
		double ans = AlgorithmML.BuildRandomForest(Train, test, featues, treeCount);
			
//		Toast.makeText(getApplicationContext(), "ABBBB ", Toast.LENGTH_SHORT).show();		
		// display on to the screen
		 textview.setText("Accuracy : "+ans);
		
		
				
			
				// TODO Auto-generated catch block
				
		
				
		
	    	 
	    
	    	 
		
	}

}
