package th.ac.kmitl.it.faceinfo.main;

import org.json.JSONObject;

import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.facebook.FacebookManager;
import th.ac.kmitl.it.faceinfo.faceplusplus.FacePlusPlus;

import android.app.Activity;
import android.content.Context;

import com.facepp.http.HttpRequests;



public class Data {
	private static Data data;
	private FacePlusPlus facePP;
	private DatabaseManager dbm;
	private FacebookManager fm;
	private MainActivity ma;
	private String tempGroupKey;
	private String tempContactKey;
	



	public String USER_KEY = "";
	public HttpRequests HTTPREQESTS;

	private Data() {
	}
	//test

	public static Data getData() {
		if (data == null) {
			data = new Data();
		}
		return data;
	}
	
	public DatabaseManager getDmb(){
		return dbm;
	}
	
	public void setDatabaseManager(Context context){
		dbm = new DatabaseManager(context);

	}
	
	public void setFacebookManager(Activity activity){
		fm = new FacebookManager(activity);
	}
	
	public FacebookManager getFacebookManager(){
		return fm;
	}

	public FacePlusPlus getFacePP() {
		if (facePP == null) {
			facePP = new FacePlusPlus();
		}

		return this.facePP;
	}
	
	public void setMainActivity(MainActivity ma){
		this.ma = ma;
	}
	public MainActivity getMainActivity(){
		return ma;
	}

	public String getTempGroupKey() {
		return tempGroupKey;
	}

	public void setTempGroupKey(String tempGroupKey) {
		this.tempGroupKey = tempGroupKey;
	}

	public String getTempContactKey() {
		return tempContactKey;
	}

	public void setTempContactKey(String tempContactKey) {
		this.tempContactKey = tempContactKey;
	}


	




}
