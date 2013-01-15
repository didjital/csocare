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

public class PenasihatAct extends ListActivity {

	// All static variables
	static final String URL = "http://csoc.or.id/icsoc/pengurus/tampil_penasihat.php";
	// XML node keys
	static final String KEY_ITEM = "pengurus"; // parent node
	static final String KEY_NAME = "nama";
	static final String KEY_JABATAN = "jabatan";
	static final String KEY_NOPUNG = "nopung";
	static final String KEY_PROFILE = "profil";

	@TargetApi(9)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		StrictMode.enableDefaults();
		setContentView(R.layout.penasihat_act);

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
			map.put(KEY_JABATAN, parser.getValue(e, KEY_JABATAN));
			map.put(KEY_NOPUNG, parser.getValue(e, KEY_NOPUNG));
			map.put(KEY_PROFILE, parser.getValue(e, KEY_PROFILE));

			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems,
				R.layout.penasihat_list,
				new String[] { KEY_NAME, KEY_JABATAN, KEY_NOPUNG, KEY_PROFILE }, new int[] {
						R.id.namaPengurus, R.id.jabatanPengurus, R.id.nopungPengurus, R.id.profilPengurus });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String nama = ((TextView) view.findViewById(R.id.namaPengurus)).getText().toString();
				String jabatan = ((TextView) view.findViewById(R.id.jabatanPengurus)).getText().toString();
				String nopung = ((TextView) view.findViewById(R.id.nopungPengurus)).getText().toString();
				String profil = ((TextView) view.findViewById(R.id.profilPengurus)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), PenasihatSingle.class);
				in.putExtra(KEY_NAME, nama);
				in.putExtra(KEY_JABATAN, jabatan);
				in.putExtra(KEY_NOPUNG, nopung);
				in.putExtra(KEY_PROFILE, profil);
				startActivity(in);

			}
		});
	}
}
