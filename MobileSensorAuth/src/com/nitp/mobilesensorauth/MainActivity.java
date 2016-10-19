package com.nitp.mobilesensorauth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText text;
	static String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		text=(EditText) findViewById(R.id.editText1);
		
		
		
		
	}

	
	
	
	
	
	
	
	
public void save(View v)
{
	
	
	
	if(text.getText().length() > 0)
	{
		name = text.getText().toString();
		Intent i = new Intent(this,DataCollect.class);
		startActivity(i);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	

}
