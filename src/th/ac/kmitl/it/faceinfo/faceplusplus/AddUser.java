package th.ac.kmitl.it.faceinfo.faceplusplus;

import th.ac.kmitl.it.faceinfo.main.Data;
import android.util.Log;


import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class AddUser extends Thread{
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;



	public AddUser(){
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;
	}
	
	public void run(){
		Log.d("CreateUser", "StartThread");
		try {
			facePP.setWaitSync();
			facePP.RESULT = httpRequests.groupCreate(new PostParameters()
					.setGroupName(data.USER_KEY));
		} catch (FaceppParseException e) {
			e.printStackTrace();
		} 
		Log.d("CreateUser", "EndThread");
	}

}
