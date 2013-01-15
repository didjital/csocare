package com.didjital.csocare;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.view.Window;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class PentingAct extends ListActivity {
	//private static final int DIALOG_ID = 100;
	private SQLiteDatabase database;
	private SimpleCursorAdapter dataSource;
	//private View entryView;
	private TextView instansi;
	private TextView telf1;
	private TextView telf2;
	private TextView telf3;
	private TextView telf4;
	
	
    private static final String fields[] = { "wilayah", "instansi", "telf1", "telf2", "telf3", "telf4",
        BaseColumns._ID };

	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		DatabaseHelper helper = new DatabaseHelper(this);
		database = helper.getWritableDatabase();
        Cursor data = database.query("instansi", fields, 
            null, null, null, null, null);
		
        dataSource = new SimpleCursorAdapter(this, 
            R.layout.penting_list, data, fields,	
            new int[] { R.id.instansiText, R.id.telf1Text, R.id.telf2Text, R.id.telf3Text, R.id.telf4Text });



		setListAdapter(dataSource);
	}
	public class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, "CursorDemo", null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE IF NOT EXISTS names ("
					+ BaseColumns._ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, first VARCHAR, last VARCHAR)");
			db.execSQL("INSERT INTO names (first, last) VALUES ('John', 'Doe')");
			db.execSQL("INSERT INTO names (first, last) VALUES ('James', 'Kirk')");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Steps to upgrade the database for the new version ...
		}
	}


}
