package com.didjital.csocare;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
 
public class ChatRoom extends Activity {
 
	private WebView webView;

 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat_activity);
 
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://csoc.or.id/chat.php");
		

	}
 
}