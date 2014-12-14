package th.ac.kmitl.it.faceinfo.database;

import th.ac.kmitl.it.faceinfo.main.Data;
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
	
	public boolean checkUser(){
		Log.d("Database","Start Check User");
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select user_key from user;";
		Cursor cursor =  db.rawQuery(sql, null);
		if(cursor != null){
			cursor.moveToNext();
			data.USER_KEY = cursor.getString(0);
			Log.d("Database","User Key = " + data.USER_KEY);
			return true;
		}
		Log.d("Database","User Key = " + data.USER_KEY);
		return false;
	}
	
	
	
	
	public void createContact(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create Contact");
	}
	public void createUser(SQLiteDatabase db){
		sql="CREATE TABLE `user` (`user_key` VARCHAR(45) NOT NULL,PRIMARY KEY (`user_key`));";
		
		db.execSQL(sql);
		Log.d("Database", "Create User");
	}
	public void createPhoto(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create Photo");
	}
	public void createGroup(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create Group");
	}
	public void createContactPhoto(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create ContactPhoto");
	}
	public void createContactGroup(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create ContactGroup");
	}
	

}
