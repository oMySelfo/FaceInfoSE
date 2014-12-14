package th.ac.kmitl.it.faceinfo.database;

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
	
	public DatabaseManager(Context context){
		super(context,SCHEMA,null,1);
		data = Data.getData();
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createUser(db);
//		createContact(db);
//		createPhoto(db);
//		createGroup(db);
//		createContactGroup(db);
//		createContactPhoto(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	
	public boolean checkUserKey(){
		Log.d("Database","Start Check User");
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select user_key from user;";
		Cursor cursor =  db.rawQuery(sql, null);
		if(cursor.moveToNext()){
			data.USER_KEY = cursor.getString(0);
			Log.d("Database","User Key = " + data.USER_KEY);
			return true;
		}
		Log.d("Database","No User Key");
		return false;
	}
	public void insertUserKey(String userKey){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("user_key", userKey);
		db.insert("user", null, values);
		data.USER_KEY = userKey;
		Log.d("Database", "Create USER_KEY : " + userKey);
	}

	public void deleteUserKey(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("user", null, null);
		data.USER_KEY = "";
		Log.d("Database","Delete UserKey");
	}
	
	
	
	private void createContact(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create Contact");
	}
	private void createUser(SQLiteDatabase db){
		sql="CREATE TABLE `user` (`user_key` VARCHAR(45) NOT NULL,PRIMARY KEY (`user_key`));";
		
		db.execSQL(sql);
		Log.d("Database", "Create User");
	}
	private void createPhoto(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create Photo");
	}
	private void createGroup(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create Group");
	}
	private void createContactPhoto(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create ContactPhoto");
	}
	private void createContactGroup(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create ContactGroup");
	}
	

}
