package com.example.polling;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	Button hold;
	TextView debugbox;	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		hold = (Button) findViewById(R.id.button1);
		
		hold.setOnClickListener (new OnClickListener() {
			
		public void onClick (View v){
			Intent intent=new Intent (v.getContext(),Polling.class);
			startActivityForResult(intent,0);
			
		}
		
		});
			
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
		
	
	
}
