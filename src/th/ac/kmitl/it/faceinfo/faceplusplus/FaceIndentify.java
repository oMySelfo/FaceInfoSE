package th.ac.kmitl.it.faceinfo.faceplusplus;

import th.ac.kmitl.it.faceinfo.main.Data;

import android.util.Log;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class FaceIndentify extends Thread {

	
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;
	private String faceId;
	
	 public FaceIndentify(String faceId) {
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;
		this.faceId= faceId;
	}
	 
	 
	 public void run(){
		 Log.d("FaceIdentify", "StartThread");
		 
		 try {
			 	
				facePP.setWaitSync();

				facePP.RESULT = httpRequests.personAddFace(new PostParameters()
						.setFaceId(faceId));
				
				///// yong mai dy tum
			} catch (FaceppParseException e) {
				e.printStackTrace();
			}
		 
		 Log.d("FaceIdentify", "EndThread");
		 
	 }
}
