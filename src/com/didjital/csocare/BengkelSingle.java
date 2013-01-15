package com.didjital.csocare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.widget.TextView;
import com.didjital.csocare.R;

public class BengkelSingle  extends Activity {
	
	// XML node keys
	static final String KEY_ITEM = "bengkel"; // parent node
	//static final String KEY_ID = "id_bengkel";
	static final String KEY_NAME = "dealer";
	static final String KEY_ALAMAT = "alamat";
	static final String KEY_AREA = "region";
	static final String KEY_NOTELP = "notelp";
	static final String KEY_NOTELP2 = "fax";
	static final String KEY_SPECIAL = "type";
		
	@TargetApi(9)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.enableDefaults();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bengkel_singgle_view);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String namabengkel = in.getStringExtra(KEY_NAME);
        String alamat = in.getStringExtra(KEY_ALAMAT);
        String area = in.getStringExtra(KEY_AREA);
        String notelp = in.getStringExtra(KEY_NOTELP);
        String notelp2 = in.getStringExtra(KEY_NOTELP2);
        String spesialis = in.getStringExtra(KEY_SPECIAL);
        
        
        // Displaying all values on the screen
        TextView lblBengkel = (TextView) findViewById(R.id.bengkel_single);
        TextView lblAlamat = (TextView) findViewById(R.id.alamat_single);
        TextView lblNotelp = (TextView) findViewById(R.id.telf_single);
        TextView lblNotelp2 = (TextView) findViewById(R.id.fax_single);
        TextView lblArea = (TextView) findViewById(R.id.region_single);
        TextView lblSpesialis = (TextView) findViewById(R.id.desc_single);
        
        lblNotelp.setTextColor(Color.parseColor("#000000"));
        lblNotelp2.setTextColor(Color.parseColor("#000000"));
        
        lblBengkel.setText(namabengkel);
        lblAlamat.setText(alamat);
        lblNotelp.setText(notelp);
        lblNotelp2.setText(notelp2);
        lblArea.setText(area);
        lblSpesialis.setText(spesialis);
        
    }
}
