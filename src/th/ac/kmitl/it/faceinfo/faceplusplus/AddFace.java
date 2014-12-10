package th.ac.kmitl.it.faceinfo.faceplusplus;

import th.ac.kmitl.it.faceinfo.main.Data;

import android.util.Log;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class AddFace extends Thread {

	
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;
	private String faceId;
	private String contactId;
	
	 public AddFace(String contactId,String faceId) {
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;
		this.contactId = contactId;
		this.faceId= faceId;
	}
	 
	 
	 public void run(){
		 Log.d("AddFace", "StartThread");
		 
		 try {
				facePP.setWaitSync();

				facePP.RESULT = httpRequests.personAddFace(new PostParameters()
						.setPersonId(contactId).setFaceId(faceId));
				
				
			} catch (FaceppParseException e) {
				e.printStackTrace();
			}
		 
		 Log.d("AddFace", "EndThread");
		 
	 }
}
