package th.ac.kmitl.it.faceinfo.main;

import org.json.JSONObject;

import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.faceplusplus.FacePlusPlus;

import android.content.Context;

import com.facepp.http.HttpRequests;



public class Data {
	private static Data data;
	private FacePlusPlus facePP;
	private DatabaseManager dbm;

	
	
	public String USER_KEY = "0000000000"; // / Stups UserKey;
	public String TEMP_KEY;
	public HttpRequests HTTPREQESTS;

	private Data() {
	}

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
