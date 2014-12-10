package th.ac.kmitl.it.faceinfo.faceplusplus;

import th.ac.kmitl.it.faceinfo.main.Data;
import android.util.Log;


import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class DeleteUser extends Thread {
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;

	public DeleteUser() {
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;
	}

	public void run() {
		Log.d("DeleteUser", "StartThread");
		try {
			facePP.setWaitSync();

			facePP.RESULT = httpRequests.groupDelete(new PostParameters()
					.setGroupName(data.USER_KEY));
		} catch (FaceppParseException e) {
			e.printStackTrace();
		}
		Log.d("DeleteUser", "EndThread");
	}
}
