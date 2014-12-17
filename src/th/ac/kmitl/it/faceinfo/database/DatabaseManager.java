package th.ac.kmitl.it.faceinfo.database;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.allfragment.ListGroup;
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
		createPhoto(db);
		createGroup(db);
		createContactGroup(db);

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
		db.close();
		data.USER_KEY = userKey;
		Log.d("Database", "Create USER_KEY : " + userKey);
	}

	public void insertPhoto(Photo photo) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("con_id", photo.getCon_id());
		values.put("photo_id", photo.getPhoto_id());
		values.put("photo_path", photo.getPhoto_path());
		db.insert("photo", null, values);
		db.close();
	}

	public void insertContactFacebook(String con_id, String con_name,
			String con_facebook) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("con_id", con_id);
		values.put("con_name", con_name);
		values.put("con_facebook", con_facebook);
		db.insert("contact", null, values);
		db.close();

	}

	public void insertContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("con_id", contact.getCon_id());
		values.put("con_name", contact.getCon_name());
		values.put("con_facebook", contact.getCon_facebook());
		values.put("con_fullname", contact.getCon_fullname());
		values.put("con_email", contact.getCon_email());
		values.put("con_birthday", contact.getCon_birthday());
		values.put("con_detail", contact.getCon_detail());
		values.put("con_phone", contact.getCon_phone());
		values.put("con_address", contact.getCon_address());
		values.put("photo_id", contact.getPhoto_id());
		db.insert("contact", null, values);
		db.close();

	}

	public List<Contact> getAllContact() {
		List<Contact> listContact = new ArrayList<Contact>();
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select con_id,con_name,con_fullname,con_facebook,"
				+ "con_email,con_phone,con_address,con_birthday,"
				+ "con_date_add,photo_id,con_detail from contact;";
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
		cursor.close();
		db.close();

		return listContact;
	}

	public Contact getContact(String con_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Contact contact = new Contact();
		sql = "Select con_id,con_name,con_fullname,con_facebook,"
				+ "con_email,con_phone,con_address,con_birthday,"
				+ "con_date_add,photo_id,con_detail from contact where con_id = '"
				+ con_id + "';";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
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
		}
		cursor.close();
		db.close();
		return contact;
	}

	public List<Photo> getPhotoList(String con_id) {
		List<Photo> listPhoto = new ArrayList<Photo>();

		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select con_id,photo_id,photo_path,photo_date_add from photo where con_id = '"
				+ con_id + "';";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Photo photo = new Photo();
			photo.setCon_id(cursor.getString(0));
			photo.setPhoto_id(cursor.getString(1));
			photo.setPhoto_path(cursor.getString(2));
			photo.setPhoto_date_add(cursor.getString(3));
			photo.setNewPhoto(false);
			listPhoto.add(photo);
		}
		cursor.close();
		db.close();

		return listPhoto;
	}

	public void updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("con_name", contact.getCon_name());
		values.put("con_phone", contact.getCon_phone());
		values.put("con_fullname", contact.getCon_fullname());
		values.put("con_birthday", contact.getCon_birthday());
		values.put("con_email", contact.getCon_email());
		values.put("con_facebook", contact.getCon_facebook());
		values.put("con_address", contact.getCon_address());
		values.put("con_detail", contact.getCon_detail());
		values.put("photo_id", contact.getPhoto_id());

		db.update("contact", values, "con_id = '" + contact.getCon_id() + "'",
				null);
		db.close();
	}

	public void deleteContactPhoto(String con_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("photo", "con_id='" + con_id + "'", null);
		db.close();
	}

	public void deleteContact(String con_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("contact", "con_id='" + con_id + "'", null);
		db.delete("con_group", "con_id = '" +con_id+"';", null);
		db.close();
	}

	public void deletePhoto(String photo_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("photo", "photo_id='" + photo_id + "'", null);
		db.close();
	}

	public void deleteDatabase() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("contact", null, null);
		db.delete("user", null, null);
		data.USER_KEY = "";
		db.close();
		Log.d("Database", "Delete Database");

	}

	public String getPhotoPath(String photo_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select photo_path from photo where photo_id = '" + photo_id
				+ "';";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {

			return cursor.getString(0);
		}

		return null;

	}

	private void createContact(SQLiteDatabase db) {
		sql = "CREATE TABLE `contact` "
				+ "(`con_id` VARCHAR(45) PRIMARY KEY NOT NULL,"
				+ "`con_name` VARCHAR(45) NOT NULL DEFAULT 'Unknown name',"
				+ "`con_fullname` VARCHAR(45) NULL,"
				+ "`con_birthday` DATE NULL,"
				+ "`photo_id` VARCHAR(45) NULL,"
				+ "`con_email` VARCHAR(45) NULL,"
				+ " `con_facebook` VARCHAR(45) NULL,"
				+ "`con_phone` VARCHAR(45) NULL,"
				+ " `con_date_add` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
				+ "`con_detail` VARCHAR(100) NULL,"
				+ "`con_address` VARCHAR(45) NULL);";
		db.execSQL(sql);
		Log.d("Database", "Create Contact");
	}

	private void createUser(SQLiteDatabase db) {
		sql = "CREATE TABLE `user` (`user_key` VARCHAR(45) NOT NULL,PRIMARY KEY (`user_key`));";
		db.execSQL(sql);
		Log.d("Database", "Create User");
	}

	private void createPhoto(SQLiteDatabase db) {
		sql = "CREATE TABLE `photo` "
				+ "(`photo_id` VARCHAR(45) PRIMARY KEY NOT NULL,"
				+ "`con_id` VARCHAR(45) NOT NULL,"
				+ "`photo_detail` VARCHAR(50) NULL,"
				+ " `photo_date_add` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
				+ "`photo_path` VARCHAR(50) NULL);";
		db.execSQL(sql);
		Log.d("Database", "Create Photo");
	}

	private void createGroup(SQLiteDatabase db) {
		sql = "CREATE TABLE `group` "
				+ "(`group_id` INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
				+ "`group_img` VARCHAR(45) NOT NULL,"
				+ "`group_name` VARCHAR(45) NULL,"
				+ " `group_date_add` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
				+ "`group_detail` VARCHAR(50) NULL);";

		db.execSQL(sql);
		Log.d("Database", "Create Group");
	}

	private void createContactGroup(SQLiteDatabase db) {
		sql = "CREATE TABLE `con_group` "
				+ "(`con_group_id` INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
				+ "`group_id` INTEGER  NOT NULL,"
				+ "`con_id` VARCHAR(50) NULL);";

		db.execSQL(sql);
		Log.d("Database", "Create ContactGroup");
	}

	public List<Contact> searchContact(String keyWord) {
		SQLiteDatabase db = this.getReadableDatabase();
		List<Contact> searchList = new ArrayList<Contact>();
		sql = unionContact(keyWord);

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
			searchList.add(contact);
		}
		cursor.close();
		db.close();

		return searchList;
	}

	private String unionContact(String keyword) {
		String[] arrayKeyword = keyword.split(" ");
		sql = "Select con_id,con_name,con_fullname,con_facebook,"
				+ "con_email,con_phone,con_address,con_birthday,"
				+ "con_date_add,photo_id,con_detail from contact where con_name Like '%"
				+ keyword + "%'";
		;
		for (String key : arrayKeyword) {
			String sql1 = "Select con_id,con_name,con_fullname,con_facebook,"
					+ "con_email,con_phone,con_address,con_birthday,"
					+ "con_date_add,photo_id,con_detail from contact where con_name Like '%"
					+ key + "%'";
			String sql2 = "Select con_id,con_name,con_fullname,con_facebook,"
					+ "con_email,con_phone,con_address,con_birthday,"
					+ "con_date_add,photo_id,con_detail from contact where con_fullname Like '%"
					+ key + "%'";
			String sql3 = "Select con_id,con_name,con_fullname,con_facebook,"
					+ "con_email,con_phone,con_address,con_birthday,"
					+ "con_date_add,photo_id,con_detail from contact where con_phone Like '%"
					+ key + "%'";
			String sql4 = "Select con_id,con_name,con_fullname,con_facebook,"
					+ "con_email,con_phone,con_address,con_birthday,"
					+ "con_date_add,photo_id,con_detail from contact where con_birthday Like '%"
					+ key + "%'";
			String sql5 = "Select con_id,con_name,con_fullname,con_facebook,"
					+ "con_email,con_phone,con_address,con_birthday,"
					+ "con_date_add,photo_id,con_detail from contact where con_facebook Like '%"
					+ key + "%'";
			String sql6 = "Select con_id,con_name,con_fullname,con_facebook,"
					+ "con_email,con_phone,con_address,con_birthday,"
					+ "con_date_add,photo_id,con_detail from contact where con_address Like '%"
					+ key + "%'";
			String sql7 = "Select con_id,con_name,con_fullname,con_facebook,"
					+ "con_email,con_phone,con_address,con_birthday,"
					+ "con_date_add,photo_id,con_detail from contact where con_detail Like '%"
					+ key + "%'";
			String sql8 = "Select con_id,con_name,con_fullname,con_facebook,"
					+ "con_email,con_phone,con_address,con_birthday,"
					+ "con_date_add,photo_id,con_detail from contact where con_email Like '%"
					+ key + "%'";

			sql = sql + " UNION " + sql1 + " UNION " + sql2 + " UNION " + sql3
					+ " UNION " + sql4 + " UNION " + sql5 + " UNION " + sql6
					+ " UNION " + sql7 + " UNION " + sql8;
		}

		return sql;

	}

	public void insertGroup(Group group) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("group_name", group.getGroup_name());
		values.put("group_img", group.getGroup_img());
		db.insert("'group'", null, values);
		db.close();
	}

	public List<Group> getAllGroup() {
		List<Group> listGroup = new ArrayList<Group>();
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select group_id,group_name,group_img,group_detail,group_date_add from 'group'";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Group group = new Group();
			group.setGroup_id(cursor.getString(0));
			group.setGroup_name(cursor.getString(1));
			group.setGroup_img(cursor.getString(2));
			group.setGroup_detail(cursor.getString(3));
			group.setGroup_date_add(cursor.getString(4));
			listGroup.add(group);
		}
		cursor.close();
		db.close();
		return listGroup;
	}

	public void deleteGroup(String group_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("'group'", "group_id='" + group_id + "'", null);
		db.delete("con_group", "group_id='" + group_id + "'", null);
		db.close();
	}

	public List<Contact> getContactGroup(String group_id) {
		List<Contact> listContact = new ArrayList<Contact>();
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select con_id from con_group where group_id ='" + group_id + "'";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Contact contact = new Contact();
			contact.setCon_id(cursor.getString(0));
			listContact.add(contact);
		}
		cursor.close();

		for (Contact contact : listContact) {
			sql = "Select con_name,photo_id from contact where con_id='"
					+ contact.getCon_id() + "'";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				contact.setCon_name(cursor.getString(0));
				contact.setPhoto_id(cursor.getString(1));
				
			}
			cursor.close();
		}

		return listContact;
	}

	public boolean isInGroup(String con_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		sql = "Select * from con_group where con_id='" + con_id + "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			return true;
		}
		return false;

	}

	public void insertContactToGroup(String group_id, String con_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("group_id", group_id);
		values.put("con_id", con_id);
		db.insert("con_group", "con_id='" + con_id + "'", values);
		db.close();

	}

	public void deleteGroupConGroup(String group_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("con_group", "group_id='" + group_id + "'", null);
	}

}
