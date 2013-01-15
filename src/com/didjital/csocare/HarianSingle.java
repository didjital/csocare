package com.didjital.csocare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.widget.TextView;
import com.didjital.csocare.R;

public class HarianSingle  extends Activity {
	
	// XML node keys
	static final String KEY_ITEM = "pengurus"; // parent node
	static final String KEY_NAME = "nama";
	static final String KEY_JABATAN = "jabatan";
	static final String KEY_NOPUNG = "nopung";
	static final String KEY_PROFILE = "profil";
	@TargetApi(9)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.enableDefaults();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.harian_single);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String nama = in.getStringExtra(KEY_NAME);
        String jabatan = in.getStringExtra(KEY_JABATAN);
        String nopung = in.getStringExtra(KEY_NOPUNG);
        String profil = in.getStringExtra(KEY_PROFILE);
        
        // Displaying all values on the screen
        TextView lblNama = (TextView) findViewById(R.id.namaPengurus);
        TextView lblJabatan = (TextView) findViewById(R.id.jabatanPengurus);
        TextView lblNopung = (TextView) findViewById(R.id.nopungPengurus);
        TextView lblProfil = (TextView) findViewById(R.id.profilPengurus);
        
        lblNama.setText(nama);
        lblJabatan.setText(jabatan);
        lblNopung.setText(nopung);
        lblProfil.setText(profil);
    }
}
