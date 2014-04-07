package com.example.polling;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class Create extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(Polling.EXTRA_MESSAGE);

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(textView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}

}
