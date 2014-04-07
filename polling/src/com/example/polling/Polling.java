package com.example.polling;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.content.Intent;


public class Polling extends Activity {
	
	protected static final String EXTRA_MESSAGE = null;
	private EditText title, cno;
	private Button ok;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_polling);
		// Show the Up button in the action bar.
		
		/*	title = (EditText)findViewById(R.id.textView1);
		
		ok = (Button)findViewById(R.id.buttonOK);
		
		ok.setOnClickListener (new OnClickListener() {
			
		public void onClick (View v){
			Intent intent=new Intent (v.getContext(),Create.class);
			EditText editText = (EditText) findViewById(R.id.editText1);
			String message = editText.getText().toString();
			intent.putExtra(EXTRA_MESSAGE, message);
			startActivityForResult(intent,0);
		}
		
		});
		
		*/
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.polling, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
