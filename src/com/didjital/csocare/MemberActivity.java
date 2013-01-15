package com.didjital.csocare;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MemberActivity extends Activity {
	// All static variables
	static final String URL = "http://csocare.csoc.or.id/memberfile/tampil_xml_joomla1.php";
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
	
	ListView list;
    LazyAdapter adapter;
    ImageLoader imageLoader;

    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		StrictMode.enableDefaults();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
				
		ArrayList<HashMap<String, String>> membersList = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL, null, null); // getting XML from URL
		Document doc = parser.getDomElement(xml); // getting DOM element
		
		NodeList nl = doc.getElementsByTagName(KEY_PARENT);
		// looping through all member nodes <member>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			//map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_NAMA, parser.getValue(e, KEY_NAMA));
			map.put(KEY_NOPUNG, parser.getValue(e, KEY_NOPUNG));
			map.put(KEY_LOKASI, parser.getValue(e, KEY_LOKASI));
			map.put(KEY_PHONE, parser.getValue(e, KEY_PHONE));
			map.put(KEY_TELPRUMAH, parser.getValue(e, KEY_TELPRUMAH));
			map.put(KEY_TELPKANTOR, parser.getValue(e, KEY_TELPKANTOR));
			map.put(KEY_EMAIL, parser.getValue(e, KEY_EMAIL));
			map.put(KEY_KEND, parser.getValue(e, KEY_KEND));
			map.put(KEY_NOPOL, parser.getValue(e, KEY_NOPOL));
			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

			// adding HashList to ArrayList
			membersList.add(map);
		}
		

		list=(ListView)findViewById(R.id.list);
		
		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, membersList);
        list.setTextFilterEnabled(true);
        list.setAdapter(adapter);
        
        //filterText.addTextChangedListener(filterTextWatcher);
        
        

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
        	

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
							
				// getting values from selected ListItem
				String nama = ((TextView) view.findViewById(R.id.nama)).getText().toString();
				String nopung = ((TextView) view.findViewById(R.id.nopung)).getText().toString();
				String lokasi = ((TextView) view.findViewById(R.id.lokasi)).getText().toString();
				String phone = ((TextView) view.findViewById(R.id.mobilePhone)).getText().toString();
				String rumah = ((TextView) view.findViewById(R.id.telpRumah)).getText().toString();
				String kantor = ((TextView) view.findViewById(R.id.telpKantor)).getText().toString();
				String email = ((TextView) view.findViewById(R.id.emailRow)).getText().toString();
				String mobil = ((TextView) view.findViewById(R.id.kendRow)).getText().toString();
				String nopol = ((TextView) view.findViewById(R.id.nopolRow)).getText().toString();
				String urlimage = ((TextView) view.findViewById(R.id.urlRow)).getText().toString();
				//ImageView avatar = ((ImageView) view.findViewById(R.id.list_image));
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), MemberSingle.class);
				in.putExtra(KEY_NAMA, nama);
				in.putExtra(KEY_NOPUNG, nopung);
				in.putExtra(KEY_LOKASI, lokasi);
				in.putExtra(KEY_PHONE, phone);
				in.putExtra(KEY_TELPRUMAH, rumah);
				in.putExtra(KEY_TELPKANTOR, kantor);
				in.putExtra(KEY_EMAIL, email);
				in.putExtra(KEY_KEND, mobil);
				in.putExtra(KEY_NOPOL, nopol);
				in.putExtra(KEY_THUMB_URL, urlimage);
				//in.putExtra(KEY_THUMB_URL, R.drawable.no_photo);

				startActivity(in);		

			}
		});		
        
	}	
}