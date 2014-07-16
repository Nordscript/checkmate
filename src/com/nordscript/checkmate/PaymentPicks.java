package com.nordscript.checkmate;

import android.app.ListActivity;
import android.os.Bundle;

public class PaymentPicks extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_picks);

        setTitle("Food selection");
        
    
    };
    
}
