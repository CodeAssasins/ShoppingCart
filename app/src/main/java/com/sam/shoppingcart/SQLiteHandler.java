package com.sam.shoppingcart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "android_api";

	// Profile Settings table name
	private static final String TABLE_SHOP = "shop";

	// Profile Settings information names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PRICE = "price";


	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_PROF_TABLE = "CREATE TABLE " + TABLE_SHOP + "("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_NAME+" TEXT,  "+KEY_PRICE+" TEXT" + ")";

		db.execSQL(CREATE_PROF_TABLE);

		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP);

		// Create tables again
		onCreate(db);
	}

	// Check before adding item if item already exist

	public boolean ifExists(Model model)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = null;
		String checkQuery = "SELECT "+ KEY_NAME + " FROM " + TABLE_SHOP + " WHERE " + KEY_NAME + "= '"+model.getName() + "'";
		cursor= db.rawQuery(checkQuery,null);
		boolean exists = (cursor.getCount() > 0);
		cursor.close();
		return exists;
	}


    // Add items to db
	public void addItems(String name, String mobile){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(KEY_NAME, name);
		values.put(KEY_PRICE, mobile);

		long id = db.insert(TABLE_SHOP, null, values); // insert to 1st row
		db.close(); // Closing database connection

		Log.d(TAG, "New products inserted into sqlite: " + id);

	}




	// Fetch all data
	public ArrayList<HashMap<String, String>> getProfDetails()
    {
        ArrayList<HashMap<String, String>> array_list = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_SHOP;
        Cursor res = db.rawQuery(selectQuery, null);
        res.moveToFirst();

        while (res.isAfterLast() == false)
        {
            HashMap<String, String> hashmap= new HashMap<String, String>();
            hashmap.put("name", res.getString(res.getColumnIndex(KEY_NAME)));
            hashmap.put("price", res.getString(res.getColumnIndex(KEY_PRICE)));

            array_list.add(hashmap);
            res.moveToNext();
        }
        return array_list;
    }

	// Getting Sum of "price"
	public int sumofPrice(){
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  SUM(price) FROM " + TABLE_SHOP;
		Cursor cur = db.rawQuery(selectQuery, null);
		int total=0;
		if(cur.moveToFirst())
		{
			total = cur.getInt(0);
		}
		return total;
	}

	// Delete single row
	public void deleteItem(Model model) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SHOP, KEY_NAME + " = ?",new String[] { String.valueOf(model.getName()) });
		db.close();
	}

	// Delete whole table
	public void deleteTable() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_SHOP, null, null);
		db.close();

		Log.d(TAG, "Deleted all info from sqlite");
	}

}
