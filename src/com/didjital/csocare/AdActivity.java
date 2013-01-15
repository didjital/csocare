package com.didjital.csocare;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;
import com.didjital.csocare.R;

public class AdActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ad_activity);
        
        TextView txt_ad = (TextView)findViewById(R.id.isiAd);
        txt_ad.setText(Html.fromHtml(getString(R.string.isi_ad)));

	}
}
