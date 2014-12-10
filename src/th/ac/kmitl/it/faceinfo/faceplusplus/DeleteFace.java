package th.ac.kmitl.it.faceinfo.faceplusplus;

import th.ac.kmitl.it.faceinfo.main.Data;

import android.util.Log;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class DeleteFace extends Thread {

	
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;
	private String faceId;
	private String contactId;
	
	 public DeleteFace(String contactId,String faceId) {
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;
		this.contactId = contactId;
		this.faceId= faceId;
	}
	 
	 
	 public void run(){
		 Log.d("DeleteFace", "StartThread");
		 
		 try {
				facePP.setWaitSync();

				facePP.RESULT = httpRequests.personRemoveFace(new PostParameters()
						.setPersonId(contactId).setFaceId(faceId));
				
				
			} catch (FaceppParseException e) {
				e.printStackTrace();
			}
		 
		 Log.d("DeleteFace", "EndThread");
		 
	 }
}
