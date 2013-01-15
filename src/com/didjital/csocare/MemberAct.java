package com.didjital.csocare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MemberAct extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;
	AlertDialogManager alert = new AlertDialogManager();

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();
	ImageLoader imageLoader = new ImageLoader(null);
	
	ArrayList<HashMap<String, String>> memberList;

	// url to get all products list
	private static String url = "http://csocare.csoc.or.id/memberfile/member.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MEMBER = "members";
	private static final String TAG_NAMA = "nama";
	private static final String TAG_NOPUNG = "nopung";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_TELPRUMAH = "telprumah";
	private static final String TAG_TELPKANTOR = "telpkantor";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_MOBIL = "type_mobil";
	private static final String TAG_NOPOL = "nopol";
	private static final String TAG_AVATAR = "avatar";

	// products JSONArray
	JSONArray members = null;
	EditText cari;
	Button tombolcari;
	ImageView thumb;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.enableDefaults();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.member_act);
		
		//deklarasi tombol dan text field
		tombolcari = (Button)findViewById(R.id.btnCari);
		cari = (EditText)findViewById(R.id.inputSearch);
		thumb = (ImageView)findViewById(R.id.list_image);
		cari.setTextColor(Color.BLACK);


		// Hashmap for ListView
		memberList = new ArrayList<HashMap<String, String>>();

		// Get listview
		final ListView lv = getListView();
		//lv.setAdapter(null);
		
		tombolcari.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching All products Activity
				lv.setAdapter(null);
				new LoadCariMember().execute();
							
			}
		});
		

}
/** Data disini sudah dihapus karena tidak terpakai.
 * Kalo perlu ya di ambil lagi dari file H:/Potongan.txt
 */
	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	
	class LoadCariMember extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MemberAct.this);
			pDialog.setMessage("Mencari daftar member. Silahkan tunggu...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			String pencarian = cari.getText().toString();
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("user", pencarian));
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url, "POST", params);
			//imageLoader.DisplayImage(MemberAct.TAG_AVATAR, thumb);
			
			// Check your log cat for JSON reponse
			Log.d("Pencarian member: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					members = json.getJSONArray(TAG_MEMBER);

					// looping through All Products
					for (int i = 0; i < members.length(); i++) {
						JSONObject c = members.getJSONObject(i);

						// Storing each json item in variable
						String nama = c.getString(TAG_NAMA);
						String nopung = c.getString(TAG_NOPUNG);
						String phone = c.getString(TAG_PHONE);
						String rumah = c.getString(TAG_TELPRUMAH);
						String kantor = c.getString(TAG_TELPKANTOR);
						String email = c.getString(TAG_EMAIL);
						String mobil = c.getString(TAG_MOBIL);
						String nopol = c.getString(TAG_NOPOL);
						String avatar = c.getString(TAG_AVATAR);

						

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_NAMA, nama);
						map.put(TAG_NOPUNG, nopung);
						map.put(TAG_PHONE, phone);
						map.put(TAG_TELPRUMAH, rumah);
						map.put(TAG_TELPKANTOR, kantor);
						map.put(TAG_EMAIL, email);
						map.put(TAG_MOBIL, mobil);
						map.put(TAG_NOPOL, nopol);
						map.put(TAG_AVATAR, avatar);


						// adding HashList to ArrayList
						memberList.add(map);
					}
				} else {
					// no member found
					// Launch
					Intent i = new Intent(getApplicationContext(),
							MemberAct.class);
					alert.showAlertDialog(MemberAct.this, "Member",
		 					"Maaf, member tidak ditemukan. Silahkan cari dengan kata kunci lain",
		 					false);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} 

			return pencarian;
		}
		
		
		

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							MemberAct.this, memberList,
							R.layout.member_list, new String[] { TAG_NAMA,
									TAG_NOPUNG, TAG_PHONE, TAG_TELPRUMAH, TAG_TELPKANTOR, TAG_EMAIL, TAG_MOBIL, TAG_NOPOL, TAG_AVATAR},
							new int[] { R.id.namaMember, R.id.nopungMember, R.id.kontakMember, R.id.rumahMember, R.id.kantorMember, R.id.emailMember, R.id.mobilMember, R.id.nopolMember, R.id.list_image });
					// updating listview
					setListAdapter(adapter);
					/**
				     * ((SimpleAdapter) adapter).setViewBinder(new SimpleAdapter.ViewBinder() {

					    @Override
					     
					    public boolean setViewValue(View view, Object data, String textRepresentation) {
					        if(view.getId() == R.id.list_image) {
					            ImageView imageView = (ImageView) view;
					            Drawable drawable = (Drawable) data;
					            imageView.setImageDrawable(drawable);
					            return true;
					        }
					        return false;
					    }
					    
					});
					*/
					
				}
			});

		// selecting single ListView item
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String nama = ((TextView) view.findViewById(R.id.namaMember)).getText().toString();
				String nopung = ((TextView) view.findViewById(R.id.nopungMember)).getText().toString();
				String kontak = ((TextView) view.findViewById(R.id.kontakMember)).getText().toString();
				String email = ((TextView) view.findViewById(R.id.emailMember)).getText().toString();
				String mobil = ((TextView) view.findViewById(R.id.mobilMember)).getText().toString();
				String nopol = ((TextView) view.findViewById(R.id.nopolMember)).getText().toString();
				//ImageView avatar = ((ImageView) view.findViewById(R.id.list_image));
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), MemberSingle.class);
				in.putExtra(TAG_NAMA, nama);
				in.putExtra(TAG_NOPUNG, nopung);
				in.putExtra(TAG_PHONE, kontak);
				in.putExtra(TAG_EMAIL, email);
				in.putExtra(TAG_MOBIL, mobil);
				in.putExtra(TAG_NOPOL, nopol);
				//in.putExtra(TAG_AVATAR, avatar);
				startActivity(in);

			}
					
		});
		}
	}
}

	
		
		
		



