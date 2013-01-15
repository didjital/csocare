package com.didjital.csocare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View; 
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class LoginAct extends Activity {
EditText un,pw;
TextView error, ver;
Button ok, register;
private CheckBox saveLoginCheckBox;
private SharedPreferences loginPreferences;
private SharedPreferences.Editor loginPrefsEditor;
private Boolean saveLogin;

//GPS Location
GPSTracker gps;
	
// Alert Dialog Manager
AlertDialogManager alert = new AlertDialogManager();
     
//flag for Internet connection status
Boolean isInternetPresent = false;
   	
// Connection detector class
ConnectionDetector cd;
private static final String TAG = "Software version";
private static String url ="http://csocare.csoc.or.id/download/version.txt";
private String getSoftwareVersion() {
	    try {
	            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
	            return packageInfo.versionName;
	    } catch (PackageManager.NameNotFoundException e) {
	            Log.e(TAG, "Package name not found", e);
	    };
		return null;
	 }


/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	StrictMode.enableDefaults();
	setContentView(R.layout.login_activity);
	
	ver = (TextView)findViewById(R.id.version);
	un=(EditText)findViewById(R.id.userInput);
	pw=(EditText)findViewById(R.id.passInput);
	ok=(Button)findViewById(R.id.loginBtn);
	register=(Button)findViewById(R.id.registerBtn);
	error=(TextView)findViewById(R.id.resultView);
	saveLoginCheckBox = (CheckBox)findViewById(R.id.checkIngat);
    loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
    loginPrefsEditor = loginPreferences.edit();
    
    ver.setText(getSoftwareVersion());
    
    saveLogin = loginPreferences.getBoolean("saveLogin", false);
    if (saveLogin == true) {
        un.setText(loginPreferences.getString("username", ""));
        pw.setText(loginPreferences.getString("password", ""));
        saveLoginCheckBox.setChecked(true);
    }
	
	ok.setOnClickListener(new View.OnClickListener() {
		@SuppressLint("WorldReadableFiles")
		@Override
		public void onClick(View v) {
		// TODO Auto-generated method stub
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("username", un.getText().toString()));
		postParameters.add(new BasicNameValuePair("password", pw.getText().toString()));

		String username = un.getText().toString();
        String password = pw.getText().toString();
        
		if (saveLoginCheckBox.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("username", username);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }
		//String valid = "1";
		String response = null;
			try {
				response = CustomHttpClient.executeHttpPost("http://csoc.or.id/script/auth.php", postParameters);  //Enetr Your remote PHP,ASP, Servlet file link
				String res=response.toString();
				// res = res.trim();
				res= res.replaceAll("\\s+","");
				//error.setText(res);
				if(res.equals("sukses")){
					error.setText("Selamat datang di CSOCare");
					Intent i = new Intent(LoginAct.this, splash.class);
					startActivity(i);
					LoginAct.this.finish();
				}
				else if(res.equals("tidakada")){
					alert.showAlertDialog(LoginAct.this, "Login Error",
		 					"Maaf username tidak ditemukan. Silahkan klik Register jika belum memiliki akun CSOC",
		 					false);
				}
				else{
					alert.showAlertDialog(LoginAct.this, "Login Error",
		 					"Maaf username atau password salah. Silahkan klik Register jika belum memiliki akun CSOC",
		 					false);
					//error.setText("Maaf, username atau password salah");
				}
		
		} catch (Exception e) {
		
		un.setText(e.toString());
		}
	
			
	}



});
	// Listening Tombol Info ketika di klik
    register.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            // Launching News Feed Screen
            Intent i = new Intent(getApplicationContext(), RegisterAct.class);
            startActivity(i);
        }
    });
	cd = new ConnectionDetector(getApplicationContext());

	// Check if Internet present
	isInternetPresent = cd.isConnectingToInternet();
	if (!isInternetPresent) {
		// Internet Connection is not present
		alert.showAlertDialog(LoginAct.this, "Koneksi Internet Error",
				"Silahkan gunakan koneksi internet yang benar-benar bekerja, pastikan dan Tidak dalam Flight Mode.", false);
		// stop executing code by return
		return;
	}
	
	// creating GPS Class object
 	gps = new GPSTracker(this);

 		// check if GPS location can get
 		if (gps.canGetLocation()) {
 			Log.d("Lokasi Anda", "latitude:" + gps.getLatitude() + ", longitude: " + gps.getLongitude());
 		} else {
 			// Can't get user's current location
 			alert.showAlertDialog(LoginAct.this, "GPS Status",
 					"Tidak dapat memperoleh informasi lokasi. Nyalakan GPS Anda",
 					false);
 			// stop executing code by return
 			return;
 		}
 		
 		HttpClient client = new DefaultHttpClient();
 		HttpGet request = new HttpGet(url);
 		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 		String latestVersion = "";
 		InputStream in = null;
		try {
			in = response.getEntity().getContent();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
 		StringBuilder str = new StringBuilder();
 		String line = null;
 		try {
			while((line = reader.readLine()) != null)
			{
			    str.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		latestVersion = str.toString();
 		
 		
}
}
