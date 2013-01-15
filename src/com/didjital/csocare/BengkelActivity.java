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
import com.didjital.csocare.R;

public class BengkelActivity extends ListActivity {

	// All static variables
	static final String URL = "http://csoc.or.id/script/beres_xml.php";
	// XML node keys
	static final String KEY_ITEM = "bengkel"; // parent node
	//static final String KEY_ID = "id_bengkel";
	static final String KEY_NAME = "dealer";
	static final String KEY_ALAMAT = "alamat";
	static final String KEY_AREA = "region";
	static final String KEY_NOTELP = "notelp";
	static final String KEY_NOTELP2 = "fax";
	static final String KEY_SPECIAL = "type";
	

	@TargetApi(9)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		StrictMode.enableDefaults();
		setContentView(R.layout.bengkel_activity);

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
			map.put(KEY_ALAMAT, parser.getValue(e, KEY_ALAMAT));
			map.put(KEY_AREA, parser.getValue(e, KEY_AREA));
			map.put(KEY_NOTELP, parser.getValue(e, KEY_NOTELP));
			map.put(KEY_NOTELP2, parser.getValue(e, KEY_NOTELP2));
			map.put(KEY_SPECIAL, parser.getValue(e, KEY_SPECIAL));


			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems,
				R.layout.bengkel_list,
				new String[] { KEY_NAME, KEY_ALAMAT, KEY_AREA, KEY_NOTELP, KEY_NOTELP2, KEY_SPECIAL }, new int[] {
						R.id.bengkel_list, R.id.cp_list, R.id.region_list, R.id.telp_list, R.id.fax_list, R.id.desc_list});

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String namabengkel = ((TextView) view.findViewById(R.id.bengkel_list)).getText().toString();
				String alamat = ((TextView) view.findViewById(R.id.cp_list)).getText().toString();
				String area = ((TextView) view.findViewById(R.id.region_list)).getText().toString();
				String notelp = ((TextView) view.findViewById(R.id.telp_list)).getText().toString();
				String notelp2 = ((TextView) view.findViewById(R.id.fax_list)).getText().toString();
				String spesialis = ((TextView) view.findViewById(R.id.desc_list)).getText().toString();
				
				
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), BengkelSingle.class);
				in.putExtra(KEY_NAME, namabengkel);
				in.putExtra(KEY_ALAMAT, alamat);
				in.putExtra(KEY_AREA, area);
				in.putExtra(KEY_NOTELP, notelp);
				in.putExtra(KEY_NOTELP2, notelp2);
				in.putExtra(KEY_SPECIAL, spesialis);
				startActivity(in);

			}
		});
	}
}
