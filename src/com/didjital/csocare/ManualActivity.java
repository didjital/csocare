package com.didjital.csocare;

import com.didjital.csocare.manual.PartsAct;
import com.didjital.csocare.manual.TipsAct;
import com.didjital.csocare.manual.TroubleAct;

import android.annotation.TargetApi;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressWarnings("deprecation")
public class ManualActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.enableDefaults();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.manual_activity);
 
        TabHost tabHost = getTabHost();
 
        // Tab for TroubleShoot
        TabSpec troubleshoot = tabHost.newTabSpec("Troubleshooting");
        // setting Title and Icon for the Tab
        troubleshoot.setIndicator("Troubleshooting", getResources().getDrawable(R.drawable.arrow1));
        Intent troubleShoot = new Intent(this, TroubleAct.class);
        troubleshoot.setContent(troubleShoot);
 
        // Tab for Songs
        TabSpec parts = tabHost.newTabSpec("Parts");
        parts.setIndicator("Parts Subtitusi", getResources().getDrawable(R.drawable.parts1));
        Intent partSubs = new Intent(this, PartsAct.class);
        parts.setContent(partSubs);
 
        // Tab for Videos
        TabSpec tips = tabHost.newTabSpec("Tips");
        tips.setIndicator("Tips'n Trick", getResources().getDrawable(R.drawable.tips1));
        Intent tipsTrick = new Intent(this, TipsAct.class);
        tips.setContent(tipsTrick);
 
        // Adding all TabSpec to TabHost
        tabHost.addTab(troubleshoot); // Adding photos tab
        tabHost.addTab(parts); // Adding songs tab
        tabHost.addTab(tips); // Adding videos tab
    }
}
