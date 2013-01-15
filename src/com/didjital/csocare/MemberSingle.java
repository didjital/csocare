package com.didjital.csocare;

import java.io.InputStream;
import java.net.URL;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.didjital.csocare.R;

public class MemberSingle  extends Activity {
	
	// All static variables
	//static final String URL = "http://csocare.csoc.or.id/memberfile/tampil_xml_joomla1.php";
	
	// XML node keys
	static final String KEY_PARENT = "member"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_NOPUNG = "nopung";
	static final String KEY_NAMA = "nama";
	static final String KEY_LOKASI = "lokasi";
	static final String KEY_PHONE = "phone";
	static final String KEY_TELPRUMAH = "telprumah";
	static final String KEY_TELPKANTOR = "telpkantor";
	static final String KEY_EMAIL = "email";
	static final String KEY_KEND = "type_mobil";
	static final String KEY_NOPOL = "nopol";
	static final String KEY_THUMB_URL = "avatar";
	//private TextView txtUrl;
	
	
	//public ImageLoader imageLoader;
	
	
	@TargetApi(9)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.enableDefaults();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.member_single);
        
        // getting intent data
        Intent in = getIntent();
        
        
        // Get XML values from previous intent
        String nama = in.getStringExtra(KEY_NAMA);
        String nopung = in.getStringExtra(KEY_NOPUNG);
        String lokasi = in.getStringExtra(KEY_LOKASI);
        String phone = in.getStringExtra(KEY_PHONE);
        String rumah = in.getStringExtra(KEY_TELPRUMAH);
        String kantor = in.getStringExtra(KEY_TELPKANTOR);
        String email = in.getStringExtra(KEY_EMAIL);
        String mobil = in.getStringExtra(KEY_KEND);
        String nopol = in.getStringExtra(KEY_NOPOL);
        String urlimage = in.getStringExtra(KEY_THUMB_URL);
        //int[] image_link = in.getIntExtra("KEY_THUMB_URL");
        
        
                
        // Displaying all values on the screen
        TextView lblNama = (TextView) findViewById(R.id.namaMember);
        TextView lblNopung = (TextView) findViewById(R.id.nopungMember);
        //TextView lblNopung2 = (TextView) findViewById(R.id.nopungMember2);
        TextView lblLokasi = (TextView) findViewById(R.id.lokasiMember);
        TextView lblKontak = (TextView) findViewById(R.id.ponsel);
        TextView lblRumah = (TextView) findViewById(R.id.telpRumah);
        TextView lblKantor = (TextView) findViewById(R.id.telpKantor);
        TextView lblEmail = (TextView) findViewById(R.id.email);
        TextView lblMobil = (TextView) findViewById(R.id.mobil);
        TextView lblNopol = (TextView) findViewById(R.id.nopol);
        TextView lblUrl = (TextView) findViewById(R.id.url);
        //mageView thumb_image=(ImageView)findViewById(R.id.imageView1);
        ImageView foto = (ImageView) findViewById(R.id.imageView1);
        
               
        lblKontak.setLinkTextColor(Color.BLACK);
        lblRumah.setLinkTextColor(Color.BLACK);
        lblKantor.setLinkTextColor(Color.BLACK);
        lblEmail.setLinkTextColor(Color.BLACK);
        
        lblNama.setText(nama);
        lblNopung.setText(nopung);
        //lblNopung2.setText(nopung);
        lblLokasi.setText(lokasi);
        lblKontak.setText(phone);
        lblRumah.setText(rumah);
        lblKantor.setText(kantor);
        lblEmail.setText(email);
        lblMobil.setText(mobil);
        lblNopol.setText(nopol);
        try {
        	foto.setImageDrawable(grabImageFromUrl(urlimage));
        	//Bitmap.createScaledBitmap(foto, 128, 160, true); 
        	} catch(Exception e) {
        	lblUrl.setText("Error: Exception");
        	}

    }
	private Drawable grabImageFromUrl(String url) throws Exception {
		return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
		}

}
