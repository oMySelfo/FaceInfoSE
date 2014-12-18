package th.ac.kmitl.it.faceinfo.adapter;

import android.graphics.Bitmap;

public class ObjectResult {
	private Bitmap picResult;
	private String nameResult;
	private String con_id;
	
	public ObjectResult(String nameResult, Bitmap picResult,String con_id){
		this.nameResult = nameResult;
		this.picResult = picResult;
		this.con_id = con_id;
	}
	
	
	public String getCon_id() {
		return con_id;
	}


	public void setCon_id(String con_id) {
		this.con_id = con_id;
	}


	public Bitmap getPicResult() {
		return picResult;
	}
	public void setPicResult(Bitmap picResult) {
		this.picResult = picResult;
	}
	public String getNameResult() {
		return nameResult;
	}
	public void setNameResult(String nameResult) {
		this.nameResult = nameResult;
	}
	
	

}
