package com.example.weunion;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Polling extends Activity {

	Button hold,view;
	TextView debugbox;	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_polling);
		
		hold = (Button) findViewById(R.id.b_logout);
		view = (Button) findViewById(R.id.button2);
		
		hold.setOnClickListener (new OnClickListener() {
			
		public void onClick (View v){
			Intent intent=new Intent (v.getContext(),Select_Event.class);
			startActivityForResult(intent,0);
			finish();
			
		}
		
		});
		
		view.setOnClickListener (new OnClickListener() {
			
			public void onClick (View v){
				Intent intent=new Intent (v.getContext(),Vote.class);
				startActivityForResult(intent,0);
				finish();

				
		}
			
		});
		
			
	}
	
	

}
