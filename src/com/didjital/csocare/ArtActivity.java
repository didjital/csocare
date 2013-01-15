package com.didjital.csocare;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;
import com.didjital.csocare.R;

public class ArtActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.art_activity);
        
        TextView txt_ad = (TextView)findViewById(R.id.isiArt);
        txt_ad.setText(Html.fromHtml(getString(R.string.isi_art)));
	}

}
