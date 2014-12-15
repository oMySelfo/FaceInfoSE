package th.ac.kmitl.it.faceinfo.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.main.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {
	private static String SCHEMA = "faceinfo";
	private Data data;
	private String sql;

	public DatabaseManager(Context context) {
		super(context, SCHEMA, null, 1);
		data = Data.getData();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createUser(db);
		createContact(db);
		// createPhoto(db);
		// createGroup(db);
		// createContactGroup(db);
		// createContactPhoto(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public boolean checkUserKey() {
		Log.d("Database", "Start Check User");
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select user_key from user;";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			data.USER_KEY = cursor.getString(0);
			Log.d("Database", "User Key = " + data.USER_KEY);
			return true;
		}
		Log.d("Database", "No User Key");
		return false;
	}

	public void insertUserKey(String userKey) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("user_key", userKey);
		db.insert("user", null, values);
		data.USER_KEY = userKey;
		Log.d("Database", "Create USER_KEY : " + userKey);
	}


	
	public void insertContactFacebook(String con_id,String con_name,String con_facebook){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("con_id", con_id);
		values.put("con_name", con_name);
		values.put("con_facebook", con_facebook);
		db.insert("contact", null, values);
		
	}
	public List<Contact> selectAllContact(){
		List<Contact> listContact = new ArrayList<Contact>();
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select con_id,con_name,con_fullname,con_facebook,con_email,con_phone,con_address,con_birthday,con_date_add,photo_id,con_detail from contact;";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Contact contact = new Contact();
			contact.setCon_id(cursor.getString(0));
			contact.setCon_name(cursor.getString(1));
			contact.setCon_fullname(cursor.getString(2));
			contact.setCon_facebook(cursor.getString(3));
			contact.setCon_email(cursor.getString(4));
			contact.setCon_phone(cursor.getString(5));
			contact.setCon_address(cursor.getString(6));
			contact.setCon_birthday(cursor.getString(7));
			contact.setCon_date_add(cursor.getString(8));
			contact.setPhoto_id(cursor.getString(9));
			contact.setCon_detail(cursor.getString(10));
			listContact.add(contact);
		}
		
		return listContact;
	}
	public void deleteDatabase(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("contact", null, null);
		db.delete("user", null, null);
		data.USER_KEY = "";
		db.close();
		Log.d("Database", "Delete Database");

	}

	private void createContact(SQLiteDatabase db) {
		sql = "CREATE TABLE `contact` " +
				"(`con_id` VARCHAR(45) PRIMARY KEY NOT NULL," +
				"`con_name` VARCHAR(45) NOT NULL," +
				"`con_fullname` VARCHAR(45) NULL," +
				"`con_birthday` DATE NULL," +
				"`photo_id` VARCHAR(45) NULL," +
				"`con_email` VARCHAR(45) NULL," +
				" `con_facebook` VARCHAR(45) NULL," +
				"`con_phone` VARCHAR(45) NULL," +
				" `con_date_add` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," +
				"`con_detail` VARCHAR(100) NULL," +
				"`con_address` VARCHAR(45) NULL);";
		db.execSQL(sql);
		Log.d("Database", "Create Contact");
	}

	private void createUser(SQLiteDatabase db) {
		sql = "CREATE TABLE `user` (`user_key` VARCHAR(45) NOT NULL,PRIMARY KEY (`user_key`));";
		db.execSQL(sql);
		Log.d("Database", "Create User");
	}

	private void createPhoto(SQLiteDatabase db) {
		sql = "";

		db.execSQL(sql);
		Log.d("Database", "Create Photo");
	}

	private void createGroup(SQLiteDatabase db) {
		sql = "";

		db.execSQL(sql);
		Log.d("Database", "Create Group");
	}

	private void createContactPhoto(SQLiteDatabase db) {
		sql = "";

		db.execSQL(sql);
		Log.d("Database", "Create ContactPhoto");
	}

	private void createContactGroup(SQLiteDatabase db) {
		sql = "";

		db.execSQL(sql);
		Log.d("Database", "Create ContactGroup");
	}

}
