package th.ac.kmitl.it.faceinfo.main;

import org.json.JSONObject;

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
	
	
	public String USER_KEY = "";
	public String TEMP_KEY;
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
	
	public String keyConcat(String name){
		return USER_KEY.concat("_").concat(name);
	}


}
