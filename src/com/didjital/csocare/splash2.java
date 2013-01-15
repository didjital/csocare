package com.didjital.csocare;

import android.os.Bundle;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;

public class splash2 extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_dua);	
		Thread timer = new Thread() {
			public void run() {
				try {
					//berapalama splashscreen akan ditampilkan dalam milisecond
					sleep(1500);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					finish();
					//activity yang akan dijalankan setelah splashscreen selesai
					Intent i = new Intent(splash2.this, MenuUtama.class);
					startActivity(i);
				}
			}
		};
		timer.start();
	}

}