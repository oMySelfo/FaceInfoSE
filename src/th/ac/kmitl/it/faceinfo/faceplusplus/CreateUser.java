package th.ac.kmitl.it.faceinfo.faceplusplus;

import th.ac.kmitl.it.faceinfo.main.Data;
import android.util.Log;


import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class CreateUser extends Thread{
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;



	public CreateUser(){
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;
	}
	
	public void run(){
		try {
			Log.d("CreateUser", "StartThread");
			facePP.setWaitSync();
			facePP.RESULT = httpRequests.groupCreate(new PostParameters()
					.setGroupName(data.USER_KEY));
			Log.d("CreateUser", "EndThread");
		} catch (FaceppParseException e) {
			e.printStackTrace();
		} 
	}

}
