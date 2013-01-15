package com.didjital.csocare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;
import com.didjital.csocare.R;

public class EventSingle  extends Activity {
	
	// XML node keys
	static final String KEY_ITEM = "event"; // parent node
	//static final String KEY_ID = "id_bengkel";
	static final String KEY_NAME = "namaevent";
	static final String KEY_KONTAK = "kontak";
	static final String KEY_LOKASI = "lokasi";
	static final String KEY_EXTRA = "extra";
	static final String KEY_DESC = "desc";
	

	
	@TargetApi(9)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.enableDefaults();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.event_single_view);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String namaevent = in.getStringExtra(KEY_NAME);
        String loc = in.getStringExtra(KEY_LOKASI);
        String ext = in.getStringExtra(KEY_EXTRA);
        String contact = in.getStringExtra(KEY_KONTAK);
        String desc = in.getStringExtra(KEY_DESC);
        
        
        // Displaying all values on the screen
        TextView lblEvent = (TextView) findViewById(R.id.event_ev);
        TextView lblLokasi = (TextView) findViewById(R.id.lokasi_ev);
        TextView lblContact = (TextView) findViewById(R.id.contact_ev);
        TextView lblExtra = (TextView) findViewById(R.id.extra_ev);
        TextView lblDesc = (TextView) findViewById(R.id.desc_ev);
        
        lblEvent.setText(namaevent);
        lblLokasi.setText(loc);
        lblContact.setText(ext);
        lblExtra.setText(contact);
        lblDesc.setText(Html.fromHtml(desc));
    }
}
