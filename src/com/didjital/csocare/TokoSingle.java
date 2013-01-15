package com.didjital.csocare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.widget.TextView;

public class TokoSingle  extends Activity {
	
	// XML node keys
	static final String KEY_ITEM = "toko"; // parent node
	static final String KEY_NAME = "nama";
	static final String KEY_CP = "cp";
	static final String KEY_ALAMAT = "alamat";
	static final String KEY_AREA = "region";
	static final String KEY_DESC = "desc";
	static final String KEY_NOTELP = "notelp";
	static final String KEY_NOTELP2 = "notelp2";
	static final String KEY_PINBB = "pin_bb";
	static final String KEY_EMAIL = "email";
	static final String KEY_FB = "fb";
	static final String KEY_TW = "twitter";
	static final String KEY_YM = "ym";
	static final String KEY_KOORD = "koordinat";
		
	@TargetApi(9)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.enableDefaults();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.toko_single);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String namabengkel = in.getStringExtra(KEY_NAME);
        String alamat = in.getStringExtra(KEY_ALAMAT);
        String contact = in.getStringExtra(KEY_CP);
        String area = in.getStringExtra(KEY_AREA);
        String notelp = in.getStringExtra(KEY_NOTELP);
        String notelp2 = in.getStringExtra(KEY_NOTELP2);
        String pinbb = in.getStringExtra(KEY_PINBB);
        String desc = in.getStringExtra(KEY_DESC);
        String email = in.getStringExtra(KEY_EMAIL);
        String fb = in.getStringExtra(KEY_FB);
        String twitter = in.getStringExtra(KEY_TW);
        String ym = in.getStringExtra(KEY_YM);
        String koordinat = in.getStringExtra(KEY_KOORD);
        
        // Displaying all values on the screen
        TextView lblBengkel = (TextView) findViewById(R.id.val_bengkel);
        TextView lblAlamat = (TextView) findViewById(R.id.val_alamat);
        TextView lblArea = (TextView) findViewById(R.id.region);
        TextView lblCp = (TextView) findViewById(R.id.contact_person);
        TextView lblNotelp = (TextView) findViewById(R.id.val_telp);
        TextView lblNotelp2 = (TextView) findViewById(R.id.val_fax);
        TextView lblBb = (TextView) findViewById(R.id.pin_bb);
        TextView lblEmail = (TextView) findViewById(R.id.val_email);
        TextView lblFb = (TextView) findViewById(R.id.val_fb);
        TextView lblTw = (TextView) findViewById(R.id.val_tw);
        TextView lblYm = (TextView) findViewById(R.id.val_ym);
        TextView lblSpesialis = (TextView) findViewById(R.id.val_desc);
        TextView lblKoordinat = (TextView) findViewById(R.id.val_map);
        
        lblBengkel.setText(namabengkel);
        lblAlamat.setText(alamat);
        lblCp.setText(contact);
        lblArea.setText(area);
        lblSpesialis.setText(desc);
        lblNotelp.setText(notelp);
        lblNotelp.setTextColor(Color.parseColor("#000000"));
        lblNotelp2.setText(notelp2);
        lblNotelp2.setTextColor(Color.parseColor("#000000"));
        lblBb.setText(pinbb);
        lblEmail.setText(email);
        lblEmail.setTextColor(Color.parseColor("#000000"));
        lblFb.setText(fb);
        lblFb.setTextColor(Color.parseColor("#000000"));
        lblTw.setText(twitter);
        lblTw.setTextColor(Color.parseColor("#000000"));
        lblYm.setText(ym);
        lblYm.setTextColor(Color.parseColor("#000000"));
        lblKoordinat.setText(koordinat);
        lblKoordinat.setTextColor(Color.parseColor("#000000"));
    }
}
