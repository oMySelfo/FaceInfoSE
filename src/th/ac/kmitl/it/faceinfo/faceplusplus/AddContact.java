package th.ac.kmitl.it.faceinfo.faceplusplus;

import th.ac.kmitl.it.faceinfo.main.Data;

import android.util.Log;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class AddContact extends Thread {

	
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;

	
	 public AddContact() {
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;

	}
	 
	 
	 public void run(){
		 Log.d("AddContact", "StartThread");
		 
		 try {
				facePP.setWaitSync();

				facePP.RESULT = httpRequests.personCreate(new PostParameters()
						.setGroupName(data.USER_KEY));
				
				
			} catch (FaceppParseException e) {
				e.printStackTrace();
			}
		 
		 Log.d("AddContact", "EndThread");
		 
	 }
}
