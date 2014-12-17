package th.ac.kmitl.it.faceinfo.database;

import th.ac.kmitl.it.faceinfo.main.Data;

public class Contact {

	private String con_id;
	private String con_name;
	private String con_facebook;
	private String con_fullname;
	private String con_phone;
	private String con_address;
	private String photo_id;
	private String con_email;
	private String con_detail;
	private String con_date_add;
	private String con_birthday;
	private String photo_path;

	public String getCon_id() {
		return con_id;
	}

	public void setCon_id(String con_id) {
		this.con_id = con_id;
	}

	public String getCon_name() {
		return con_name;
	}

	public void setCon_name(String con_name) {
		this.con_name = con_name;
	}

	public String getCon_facebook() {
		return con_facebook;
	}

	public void setCon_facebook(String con_facebook) {
		this.con_facebook = con_facebook;
	}

	public String getCon_fullname() {
		return con_fullname;
	}

	public void setCon_fullname(String con_fullname) {
		this.con_fullname = con_fullname;
	}

	public String getCon_phone() {
		return con_phone;
	}

	public void setCon_phone(String con_phone) {
		this.con_phone = con_phone;
	}

	public String getCon_address() {
		return con_address;
	}

	public void setCon_address(String con_address) {
		this.con_address = con_address;
	}

	public String getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}

	public String getCon_email() {
		return con_email;
	}

	public void setCon_email(String con_email) {
		this.con_email = con_email;
	}

	public String getCon_detail() {
		return con_detail;
	}

	public void setCon_detail(String con_detail) {
		this.con_detail = con_detail;
	}

	public String getCon_date_add() {
		return con_date_add;
	}

	public void setCon_date_add(String con_date_add) {
		this.con_date_add = con_date_add;
	}

	public String getCon_birthday() {
		return con_birthday;
	}

	public void setCon_birthday(String con_birthday) {
		this.con_birthday = con_birthday;
	}

	public String getPhoto_path() {
		DatabaseManager dbm = Data.getData().getDmb();
		photo_path = dbm.getPhotoPath(photo_id);
		return photo_path;
	}

	public String getContactProfile(int index) {
		/*
		 * According to order of field in Add Contacts
		 */
		switch (index) {
		case 0:return con_name;
		case 1:return con_phone;
		case 2:return con_fullname;
		case 3:return con_facebook;
		case 4:return con_address;
		case 5:return con_email;
		case 6:return con_birthday;
		case 7: return con_detail;
		default: return null;
		}
	}
	
	public void serContactProfile(int index,String value){
		/* 
		 * According to order of field in Add Contacts
		 */
		switch (index) {
		case 0:setCon_name(value);
		case 1:setCon_phone(value);
		case 2:setCon_fullname(value);
		case 3:setCon_facebook(value);
		case 4:setCon_address(value);
		case 5:setCon_email(value);
		case 6:setCon_birthday(value);
		case 7: setCon_detail(value);
		}
	}

}
