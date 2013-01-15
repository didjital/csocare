package com.didjital.csocare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class BengkelPilih extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bengkel_pilih);
        

        Button beres = (Button) findViewById(R.id.beres);
        Button beruj = (Button) findViewById(R.id.beruj);
        Button parts = (Button) findViewById(R.id.parts);
        
        beres.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), BengkelActivity.class);
                startActivity(i);
            }
        });
        beruj.setOnClickListener(new View.OnClickListener() {
       	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), BengkelRujukan.class);
                startActivity(i);
            }
        });
        parts.setOnClickListener(new View.OnClickListener() {
       	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), TokoRujukan.class);
                startActivity(i);
            }
        });

}
}
