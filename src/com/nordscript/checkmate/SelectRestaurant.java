package com.nordscript.checkmate;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SelectRestaurant extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_restaurant);
		
		
		Button btnLoad = (Button) findViewById(R.id.select);
		 
        OnClickListener listener = new OnClickListener() {
 
            @Override
            public void onClick(View v) {
        		ViewGroup layout = (ViewGroup) findViewById(R.id.selectTable);
        		layout.removeAllViews();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SelectTable hello = new SelectTable();
                fragmentTransaction.add(R.id.selectTable, hello, "HELLO");
                fragmentTransaction.commit();
           }
        };
 
        btnLoad.setOnClickListener(listener);
 
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_restaurant, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
    public void toTable(View view) {
    	Intent midIntent = new Intent(this, TableOverview.class);
    	startActivity(midIntent);
    }

}
