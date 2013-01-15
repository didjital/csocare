package com.didjital.csocare;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;
import com.didjital.csocare.R;

public class SejarahAct extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sejarah_activity);
        
        TextView txt_satu = (TextView)findViewById(R.id.partSatu);
        txt_satu.setText(Html.fromHtml(getString(R.string.part_satu)));
        
        TextView txt_satusamb = (TextView)findViewById(R.id.partSatuSamb);
        txt_satusamb.setText(Html.fromHtml(getString(R.string.part_dua)));
        
        TextView txt_dua = (TextView)findViewById(R.id.partDua);
        txt_dua.setText(Html.fromHtml(getString(R.string.part_tiga)));
        
        TextView txt_tiga = (TextView)findViewById(R.id.partTiga);
        txt_tiga.setText(Html.fromHtml(getString(R.string.part_empat)));
        
        TextView txt_empat = (TextView)findViewById(R.id.partEmpat);
        txt_empat.setText(Html.fromHtml(getString(R.string.part_lima)));
        
        TextView txt_lima = (TextView)findViewById(R.id.partLima);
        txt_lima.setText(Html.fromHtml(getString(R.string.part_enam)));
        
        TextView txt_enam = (TextView)findViewById(R.id.partEnam);
        txt_enam.setText(Html.fromHtml(getString(R.string.part_tujuh)));

}
}