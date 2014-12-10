package th.ac.kmitl.it.faceinfo.main;

import org.json.JSONObject;

import th.ac.kmitl.it.faceinfo.faceplusplus.FacePlusPlus;

import com.facepp.http.HttpRequests;



public class Data {
	private static Data data;
	private FacePlusPlus facePP;

	
	
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
