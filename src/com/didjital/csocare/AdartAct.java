package com.didjital.csocare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.didjital.csocare.R;

public class AdartAct extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.adart_activity);
        
        Button btn_ad = (Button) findViewById(R.id.btnAD);
        Button btn_art = (Button) findViewById(R.id.btnART);
        
        btn_ad.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), AdActivity.class);
                startActivity(i);
            }
        });
        
        btn_art.setOnClickListener(new View.OnClickListener() {
       	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), ArtActivity.class);
                startActivity(i);
            }
        });
        

}
}
