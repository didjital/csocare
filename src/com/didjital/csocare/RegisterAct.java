package com.didjital.csocare;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
 
public class RegisterAct extends Activity {
 
	private WebView webView;
 
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_activity);
 
		webView = (WebView) findViewById(R.id.register);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://csoc.or.id/index.php/member/formulir-pendaftaran");
 
	}
 
}