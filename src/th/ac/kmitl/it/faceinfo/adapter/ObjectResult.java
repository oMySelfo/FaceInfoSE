package th.ac.kmitl.it.faceinfo.adapter;

import android.graphics.Bitmap;

public class ObjectResult {
	private Bitmap picResult;
	private String nameResult;
	
	public ObjectResult(String nameResult, Bitmap picResult){
		this.nameResult = nameResult;
		this.picResult = picResult;
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
