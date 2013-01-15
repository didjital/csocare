package com.didjital.csocare;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BengkelRujukan extends ListActivity {

	// All static variables
	static final String URL = "http://csoc.or.id/script/rujukan_xml.php";
	// XML node keys
	static final String KEY_ITEM = "bengkel";
	static final String KEY_CP = "cp";// parent node
	static final String KEY_NAME = "dealer";
	static final String KEY_ALAMAT = "alamat";
	static final String KEY_AREA = "region";
	static final String KEY_DESC = "desc";
	static final String KEY_NOTELP = "notelp";
	static final String KEY_NOTELP2 = "notelp2";
	static final String KEY_EMAIL = "email";
	static final String KEY_FB = "fb";
	static final String KEY_TW = "twitter";
	static final String KEY_YM = "ym";
	static final String KEY_KOORD = "koordinat";
	

	@TargetApi(9)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		StrictMode.enableDefaults();
		setContentView(R.layout.bengkel_nonresmi);

		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL, null, null); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_ITEM);
		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			//map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
			map.put(KEY_CP, parser.getValue(e, KEY_CP));
			map.put(KEY_ALAMAT, parser.getValue(e, KEY_ALAMAT));
			map.put(KEY_AREA, parser.getValue(e, KEY_AREA));
			map.put(KEY_NOTELP, parser.getValue(e, KEY_NOTELP));
			map.put(KEY_NOTELP2, parser.getValue(e, KEY_NOTELP2));
			map.put(KEY_EMAIL, parser.getValue(e, KEY_EMAIL));
			map.put(KEY_FB, parser.getValue(e, KEY_FB));
			map.put(KEY_TW, parser.getValue(e, KEY_TW));
			map.put(KEY_YM, parser.getValue(e, KEY_YM));
			map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
			map.put(KEY_KOORD, parser.getValue(e, KEY_KOORD));
			


			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems,
				R.layout.bengkel_listing,
				new String[] { KEY_NAME, KEY_CP, KEY_ALAMAT, KEY_AREA, KEY_NOTELP, KEY_NOTELP2, KEY_DESC, KEY_EMAIL, KEY_FB, KEY_TW, KEY_YM, KEY_KOORD }, new int[] {
						R.id.bengkel_list2, R.id.cp_list2, R.id.alamat_list2, R.id.region_list2, R.id.telp_list2, R.id.fax_list2, R.id.desc_list2, R.id.email_list2, R.id.facebook_list2,  R.id.twitter_list2, R.id.ym_list2, R.id.koord_list2});

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String namabengkel = ((TextView) view.findViewById(R.id.bengkel_list2)).getText().toString();
				String contactperson = ((TextView) view.findViewById(R.id.cp_list2)).getText().toString();
				String alamat = ((TextView) view.findViewById(R.id.alamat_list2)).getText().toString();
				String area = ((TextView) view.findViewById(R.id.region_list2)).getText().toString();
				String notelp = ((TextView) view.findViewById(R.id.telp_list2)).getText().toString();
				String notelp2 = ((TextView) view.findViewById(R.id.fax_list2)).getText().toString();
				String desc = ((TextView) view.findViewById(R.id.desc_list2)).getText().toString();
				String email = ((TextView) view.findViewById(R.id.email_list2)).getText().toString();
				String fb = ((TextView) view.findViewById(R.id.facebook_list2)).getText().toString();
				String twitter = ((TextView) view.findViewById(R.id.twitter_list2)).getText().toString();
				String ym = ((TextView) view.findViewById(R.id.ym_list2)).getText().toString();
				String koordinat = ((TextView) view.findViewById(R.id.koord_list2)).getText().toString();
				
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), RujukanSingle.class);
				in.putExtra(KEY_NAME, namabengkel);
				in.putExtra(KEY_CP, contactperson);
				in.putExtra(KEY_ALAMAT, alamat);
				in.putExtra(KEY_AREA, area);
				in.putExtra(KEY_NOTELP, notelp);
				in.putExtra(KEY_NOTELP2, notelp2);
				in.putExtra(KEY_DESC, desc);
				in.putExtra(KEY_EMAIL, email);
				in.putExtra(KEY_FB, fb);
				in.putExtra(KEY_TW, twitter);
				in.putExtra(KEY_YM, ym);
				in.putExtra(KEY_KOORD, koordinat);
				startActivity(in);

			}
		});
	}
}
