package th.ac.kmitl.it.faceinfo.faceplusplus;

import java.io.ByteArrayOutputStream;

import th.ac.kmitl.it.faceinfo.main.Data;
import android.graphics.Bitmap;
import android.util.Log;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;


public class FaceDetect extends Thread{
	
	
	
	private Data data;
	private FacePlusPlus facePP;
	private HttpRequests httpRequests;
	private Bitmap bmp;
	
	 public FaceDetect(Bitmap bmp) {
		data = Data.getData();
		facePP = data.getFacePP();
		httpRequests = data.HTTPREQESTS;
		this.bmp = bmp;
	}
	 
	 
	 public void run(){
		 Log.d("FaceDetect", "StartThread");
		 
		 try {
			 	
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG,
						100, stream);
				byte[] byteArray = stream.toByteArray();
			 
				facePP.setWaitSync();
				facePP.RESULT = httpRequests.detectionDetect(new PostParameters()
						.setMode("oneface").setImg(byteArray));
				
			} catch (FaceppParseException e) {
				e.printStackTrace();
			}
		 
		 Log.d("FaceDetect", "EndThread");
		 
	 }

}
