package com.didjital.csocare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
//import androidhive.dashboard.R;
import com.didjital.csocare.R;
 
public class InfoActivity extends Activity {
	
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.info_activity);
        
        /**
         * Creating all buttons instances
         * */
        // Dashboard Info CSOC
        Button btn_sejarah = (Button) findViewById(R.id.btn_sejarah);
 
        // Dashboard tombol Event
        Button btn_adart = (Button) findViewById(R.id.btn_adart);
 
        // Dashboard Tombol Bengkel
        Button btn_pengurus = (Button) findViewById(R.id.btn_pengurus);
 
        // Dashboard Tombol Emergency
        Button btn_member = (Button) findViewById(R.id.btn_member);
 
        /**
         * Handling all button click events
         * */
 
        // Listening Tombol Info ketika di klik
        btn_sejarah.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), SejarahAct.class);
                startActivity(i);
            }
        });
 
       // Listening Tombol Event ketika di Klik
        btn_adart.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), AdartAct.class);
                startActivity(i);
            }
        });
 
        // Listening Tombol Bengkel ketika di Click
        btn_pengurus.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), PengurusAct.class);
                startActivity(i);
            }
        });
 
        // Listening to Tombol Emergency ketika di Klok
        btn_member.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), MemberActivity.class);
                startActivity(i);
            }
        });
 
    }
}

