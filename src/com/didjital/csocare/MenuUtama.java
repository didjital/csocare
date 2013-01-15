package com.didjital.csocare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
//import androidhive.dashboard.R;
 
public class MenuUtama extends Activity {
	
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu_utama);
        
      		
        /**
         * Creating all buttons instances
         * */
        // Dashboard Info CSOC
        Button btn_info = (Button) findViewById(R.id.btn_info);
 
        // Dashboard tombol Event
        Button btn_event = (Button) findViewById(R.id.btn_event);
 
        // Dashboard Tombol Bengkel
        Button btn_bengkel = (Button) findViewById(R.id.btn_bengkel);
 
        // Dashboard Tombol Emergency
        Button btn_emergency = (Button) findViewById(R.id.btn_emergency);
        
        Button btn_chat = (Button) findViewById(R.id.btn_chat);
        
        Button btn_manual = (Button) findViewById(R.id.btn_manual);
 
        /**
         * Handling all button click events
         * */
 
        // Listening Tombol Info ketika di klik
        btn_info.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(i);
            }
        });
 
       // Listening Tombol Event ketika di Klik
        btn_event.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(i);
            }
        });
 
        // Listening Tombol Bengkel ketika di Click
        btn_bengkel.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), BengkelPilih.class);
                startActivity(i);
            }
        });
 
        // Listening to Tombol Emergency ketika di Klok
        btn_emergency.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), EmergencyActivity.class);
                startActivity(i);
            }
        });
        
        btn_chat.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(i);
            }
        });
        
        btn_manual.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), ManualActivity.class);
                startActivity(i);
            }
        });
        
        
    }

}

