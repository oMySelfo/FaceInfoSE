package th.ac.kmitl.it.faceinfo.faceplusplus;

import java.util.List;

import org.json.JSONObject;

import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.main.Data;

import android.graphics.Bitmap;
import android.util.Log;


import com.facepp.http.HttpRequests;


public class FacePlusPlus{

	private Data data;
	private String API_KEY = "a5a3b214d4689870f34c11fb4d89d50b";
	private String API_SECRET = "O42Yf4ExbRTyNrTDL4PKxWxsOMKLr3cz";
	
	
	//// Wait Sync
	private long TIME_COUNT = 20000;
	private long COUNT;
	private long SLEEP = 300;

	public boolean WAITSTATUS = false;
	public JSONObject RESULT = null;
	
	
	public FacePlusPlus(){
		data = Data.getData();
		data.HTTPREQESTS =  new HttpRequests(API_KEY,API_SECRET, false, true);
		setWaitSync();
	}
	
	
	public void addUser(){
		new AddUser().start();
		while(checkWaitSync()){
			System.out.print("");
		}
	}
	public void deleteUser(){
		new DeleteUser().start();
		while(checkWaitSync()){
			System.out.print("");
		}
	}
	public void createContact(){
		new AddContact().start();
		while(checkWaitSync()){
			System.out.print("");
		}
	}
	public void deleteContact(String id){
		new DeleteContact(id).start();
		while(checkWaitSync()){
			System.out.print("");
		}
	}
	public void addFace(String con_id,String photo_id){
		new AddFace(con_id, photo_id).start();
		while(checkWaitSync()){
			System.out.print("");
		}
	}
	
	public void deleteFace(String con_id,String photo_id){
		new DeleteFace(con_id, photo_id).start();
		while(checkWaitSync()){
			System.out.print("");
		}
	}
	
	public void deleteAllContact(List<Contact> listContact){
		Log.d("Delete All Contact", "Start");
		for(Contact contact : listContact){
			String con_id = contact.getCon_id();
			deleteContact(con_id);

		}
		Log.d("Delete All Contact", "End");
	}
	
	public void faceDetect(Bitmap bitmap){
		new FaceDetect(bitmap).start();
		while(checkWaitSync()){
			System.out.print("");
		}
	}
	public void faceIndentify(Bitmap bitmap){
		new FaceIndentify(bitmap).start();
		while(checkWaitSync()){
			System.out.print("");
		}
	}

	
	public void setWaitSync() {
		WAITSTATUS = true;
		RESULT = null;
		COUNT = TIME_COUNT;
	}
	public boolean checkWaitSync(){
		if(COUNT > 0) {
			try {
				Thread.sleep(SLEEP);
				COUNT -= SLEEP;
				Log.d("Wait Sync FacePP :", String.valueOf(COUNT));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			WAITSTATUS = false;
		}

		return (WAITSTATUS && RESULT == null);
	}

	public void waitSycn() {
		while(checkWaitSync()){
			System.out.println("");
		}
		
	}
	

}
