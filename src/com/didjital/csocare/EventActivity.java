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

public class EventActivity extends ListActivity {

	// All static variables
	static final String URL = "http://csoc.or.id/script/event_xml.php";
	// XML node keys
	static final String KEY_ITEM = "event"; // parent node
	//static final String KEY_ID = "id_bengkel";
	static final String KEY_NAME = "namaevent";
	static final String KEY_KONTAK = "kontak";
	static final String KEY_LOKASI = "lokasi";
	static final String KEY_EXTRA = "extra";
	static final String KEY_DESC = "desc";

	@TargetApi(9)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		StrictMode.enableDefaults();
		setContentView(R.layout.event_activity);

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
			map.put(KEY_KONTAK, parser.getValue(e, KEY_KONTAK));
			map.put(KEY_LOKASI, parser.getValue(e, KEY_LOKASI));
			map.put(KEY_EXTRA, parser.getValue(e, KEY_EXTRA));
			map.put(KEY_DESC, parser.getValue(e, KEY_DESC));

			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems,
				R.layout.event_list,
				new String[] { KEY_NAME, KEY_LOKASI, KEY_EXTRA, KEY_KONTAK, KEY_DESC}, new int[] {
						R.id.namaEvent, R.id.lokasiEvent, R.id.extraEvent, R.id.kontakEvent, R.id.descEvent });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String namaevent = ((TextView) view.findViewById(R.id.namaEvent)).getText().toString();
				String loc = ((TextView) view.findViewById(R.id.lokasiEvent)).getText().toString();
				String ext = ((TextView) view.findViewById(R.id.extraEvent)).getText().toString();
				String contact = ((TextView) view.findViewById(R.id.kontakEvent)).getText().toString();
				String desc = ((TextView) view.findViewById(R.id.descEvent)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), EventSingle.class);
				in.putExtra(KEY_NAME, namaevent);
				in.putExtra(KEY_LOKASI, loc);
				in.putExtra(KEY_EXTRA, ext);
				in.putExtra(KEY_KONTAK, contact);
				in.putExtra(KEY_DESC, desc);
				startActivity(in);

			}
		});
	}
}
