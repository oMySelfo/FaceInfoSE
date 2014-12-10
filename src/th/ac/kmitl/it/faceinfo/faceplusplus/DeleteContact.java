package th.ac.kmitl.it.faceinfo.faceplusplus;

import th.ac.kmitl.it.faceinfo.main.Data;

import android.util.Log;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class DeleteContact extends Thread {

	
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;
	private String id;
	
	 public DeleteContact(String id) {
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;
		this.id = id;
	}
	 
	 
	 public void run(){
		 Log.d("DeleteContact", "StartThread");
		 
		 try {
				facePP.setWaitSync();

				facePP.RESULT = httpRequests.personCreate(new PostParameters()
						.setPersonId(id));
				
				
			} catch (FaceppParseException e) {
				e.printStackTrace();
			}
		 
		 Log.d("DeleteContact", "EndThread");
		 
	 }
}
