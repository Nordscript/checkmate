package com.nordscript.checkmate;


import java.util.ArrayList;
import java.util.Map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class Orders extends ActionBarActivity {
	public final static String TOTAL_SUM = "com.nordscript.checkmate.SUM";

	// Declare the UI components
		private ListView foodList;

		// Declare an ArrayAdapter that we use to join the data set and the ListView
		// is the way of type safe, means you only can pass Strings to this array
		//Anyway ArrayAdapter supports only TextView
		private ArrayAdapter arrayAdapter;

		private Firebase ref;
		private Orders activ;
		private ArrayList<String> dishes;
		public int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	activ = this;
    	dishes = new ArrayList<String>(20);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        
     // Initialize the UI components
        foodList = (ListView)findViewById(R.id.listView1);

        // Create a reference to a Firebase location
        ref = new Firebase("https://uqtez5y2bki.firebaseio-demo.com/Restaurants/Restaurant 2/Tables/Table 2/Orders");

        // Read data and react to changes
        ref.addChildEventListener(new ChildEventListener() {
        	  @Override
        	  public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
        	    Map s = snapshot.getValue(Map.class);
        	    dishes.add(snapshot.getName() + "  " + ((Map)snapshot.getValue(Map.class)).get("Price"));
        	    Log.i("Test", dishes.toString());
        	    arrayAdapter = new ArrayAdapter(activ, android.R.layout.simple_list_item_1, dishes.toArray());

                // By using setAdapter method, you plugged the ListView with adapter
                foodList.setAdapter(arrayAdapter);
        	  }

        	  @Override public void onChildChanged(DataSnapshot snapshot, String previousChildName) { }

        	  @Override public void onChildRemoved(DataSnapshot snapshot) {
        		Map s = snapshot.getValue(Map.class);
          	    dishes.remove(snapshot.getName() + "  " + ((Map)snapshot.getValue(Map.class)).get("Price"));
          	    Log.i("Test", dishes.toString());
          	    arrayAdapter = new ArrayAdapter(activ, android.R.layout.simple_list_item_1, dishes.toArray());

                // By using setAdapter method, you plugged the ListView with adapter
                foodList.setAdapter(arrayAdapter);
        	  }

        	  @Override public void onChildMoved(DataSnapshot snapshot, String previousChildName) { }

        	  @Override
			  public void onCancelled(FirebaseError arg0) { }
        });

        foodList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	
                view.setBackgroundColor(Color.GREEN);
                String text = dishes.get(position);
                String str = text.substring(text.indexOf(" "));
                int num = Integer.parseInt(str.trim());
                total += num;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.orders, menu);
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
    
    public void toPayment(View view) {
    	String string = Integer.toString(total);
		Intent pay = new Intent(this, PaymentMethod.class);
		pay.putExtra(TOTAL_SUM, string);
		startActivity(pay);
	}
    

}
