package com.didjital.csocare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.didjital.csocare.R;

public class PengurusAct extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pengurus_activity);
        
        Button btn_harian = (Button) findViewById(R.id.btnHarian);
        Button btn_penasihat = (Button) findViewById(R.id.btnPenasihat);
        
        btn_harian.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), HarianAct.class);
                startActivity(i);
            }
        });
        
        btn_penasihat.setOnClickListener(new View.OnClickListener() {
       	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), PenasihatAct.class);
                startActivity(i);
            }
        });
        

}

}
