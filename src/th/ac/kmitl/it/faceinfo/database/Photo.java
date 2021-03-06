package th.ac.kmitl.it.faceinfo.database;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Photo {
	private String con_id;
	private String photo_id;
	private String photo_detail;
	private String photo_date_add;
	private String photo_path;
	private boolean isNewPhoto;	
	
	public boolean isNewPhoto() {
		return isNewPhoto;
	}
	public void setNewPhoto(boolean isNewPhoto) {
		this.isNewPhoto = isNewPhoto;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getCon_id() {
		return con_id;
	}
	public void setCon_id(String con_id) {
		this.con_id = con_id;
	}
	public String getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}
	public String getPhoto_detail() {
		return photo_detail;
	}
	public void setPhoto_detail(String photo_detail) {
		this.photo_detail = photo_detail;
	}
	public String getPhoto_date_add() {
		return photo_date_add;
	}
	public void setPhoto_date_add(String photo_date_add) {
		this.photo_date_add = photo_date_add;
	}
	
	public Bitmap getBitmap(){
		File imgFile = new  File(photo_path);

		if(imgFile.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		    return myBitmap;
		}
		return null;
	
	}
	
	
	

}
