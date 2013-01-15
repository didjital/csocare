package com.didjital.csocare;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter{
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView nama = (TextView)vi.findViewById(R.id.nama); // nama
        TextView nopung = (TextView)vi.findViewById(R.id.nopung); // nomor punggung
        TextView lokasi = (TextView)vi.findViewById(R.id.lokasi); // lokasi
        TextView phone = (TextView)vi.findViewById(R.id.mobilePhone);
        TextView rumah = (TextView)vi.findViewById(R.id.telpRumah);
        TextView kantor = (TextView)vi.findViewById(R.id.telpKantor);
        TextView email = (TextView)vi.findViewById(R.id.emailRow);
        TextView kend = (TextView)vi.findViewById(R.id.kendRow);
        TextView nopol = (TextView)vi.findViewById(R.id.nopolRow);
        TextView urlimage = (TextView)vi.findViewById(R.id.urlRow);
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        
        HashMap<String, String> member = new HashMap<String, String>();
        member = data.get(position);
        
        // Setting all values in listview
        nama.setText(member.get(MemberActivity.KEY_NAMA));
        nopung.setText(member.get(MemberActivity.KEY_NOPUNG));
        lokasi.setText(member.get(MemberActivity.KEY_LOKASI));
        phone.setText(member.get(MemberActivity.KEY_PHONE));
        rumah.setText(member.get(MemberActivity.KEY_TELPRUMAH));
        kantor.setText(member.get(MemberActivity.KEY_TELPKANTOR));
        email.setText(member.get(MemberActivity.KEY_EMAIL));
        kend.setText(member.get(MemberActivity.KEY_KEND));
        nopol.setText(member.get(MemberActivity.KEY_NOPOL));
        urlimage.setText(member.get(MemberActivity.KEY_THUMB_URL));
        imageLoader.DisplayImage(member.get(MemberActivity.KEY_THUMB_URL), thumb_image);
        return vi;
    }
    
}