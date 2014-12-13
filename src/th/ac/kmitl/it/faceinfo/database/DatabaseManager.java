package th.ac.kmitl.it.faceinfo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {
	private static String SCHEMA = "faceinfo";
	private String sql;
	
	public DatabaseManager(Context context){
		super(context,SCHEMA,null,1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createContact(db);
		createPhoto(db);
		createGroup(db);
		createUser(db);
		createContactGroup(db);
		createContactPhoto(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	
	
	private void createContact(SQLiteDatabase db){
		sql="";
		
		db.execSQL(sql);
		Log.d("Database", "Create Contact");
	}
	public void createUser(SQLiteDatabase db){
		sql="";
		
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
