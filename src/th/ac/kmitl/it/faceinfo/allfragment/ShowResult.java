package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import th.ac.kmitl.it.faceinfo.adapter.CoverFlowAdapter;
import th.ac.kmitl.it.faceinfo.adapter.ObjectResult;
import th.ac.kmitl.it.faceinfo.adapter.ResultAdapter;
import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.database.Photo;
import th.ac.kmitl.it.faceinfo.faceplusplus.FacePlusPlus;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.MainActivity;
import th.ac.kmitl.it.faceinfo.main.R;

public class ShowResult extends Fragment {
	public ShowResult() {
	}

	private MainActivity ma;
	private FancyCoverFlow fancyCoverFlow;
	private CoverFlowAdapter adapter;
	private View rootView;
	private List<Bitmap> images;
	private List<ObjectResult> or;
	private Data data;
	private FacePlusPlus fpp;
	private DatabaseManager dbm;
	private JSONArray candidateResult;
	private String con_id;
	private int confidect;
	private TextView tv;
	private List<Photo> listPhoto;
	private Contact contact;
	private Photo photo;
	private Bitmap bitmap;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.showresult, container, false);
		data = Data.getData();
		ma = data.getMainActivity();
		fpp = data.getFacePP();
		dbm = data.getDmb();

		try {
			
			System.out.println(fpp.RESULT);
			candidateResult = fpp.RESULT.getJSONArray("face").getJSONObject(0).getJSONArray("candidate");
			
			setImagesFirst();
			
			 
			setImagesNdRd();
			
			
			setFancyCoverFlow();
			
			
			
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		//event First
		fancyCoverFlow.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> view, View container,
					int position, long id) {
				System.out.println("Okkkkkkkkkkkkkkkk");
				data.getMainActivity().displayView(8);
			}
		});
		return rootView;
	}

	private void setImagesFirst() throws JSONException {
		con_id = candidateResult.getJSONObject(0).getString("person_id");
		confidect = (int)candidateResult.getJSONObject(0).getDouble("confidence");
		images = new ArrayList<Bitmap>();
		listPhoto = dbm.getPhotoList(con_id);
		contact = dbm.getContact(con_id);
		for(Photo photo:listPhoto){
			images.add(photo.getBitmap());
		}
		adapter = new CoverFlowAdapter();
		adapter.setImages(images);
		data.setTempContactKey(con_id);
		tv = (TextView) rootView.findViewById(R.id.nameResultFirst);
		tv.setText(contact.getCon_name() + "\naccuracy : " + confidect + "%" );
		
	}

	private void setImagesNdRd() throws JSONException {
		System.out.println("------------------"+candidateResult.length());
		if(candidateResult.length()>=2 ){
		or = new ArrayList<ObjectResult>();
		con_id = candidateResult.getJSONObject(1).getString("person_id");
		confidect = (int)candidateResult.getJSONObject(1).getDouble("confidence");
		contact = dbm.getContact(con_id);
		bitmap = BitmapFactory.decodeFile(contact.getPhoto_path());
		or.add(new ObjectResult(contact.getCon_name() + "\naccuracy : " + confidect + "%" ,bitmap,con_id));
		}
		if(candidateResult.length()>=3 ){
		con_id = candidateResult.getJSONObject(2).getString("person_id");
		confidect = (int)candidateResult.getJSONObject(2).getDouble("confidence");
		contact = dbm.getContact(con_id);
		bitmap = BitmapFactory.decodeFile(contact.getPhoto_path());
		or.add(new ObjectResult(contact.getCon_name() + "\naccuracy : " + confidect + "%" , bitmap,con_id));
		}

		if(candidateResult.length()!=1){
		ArrayAdapter<ObjectResult> modeAdapter = new ResultAdapter(ma, or);
		ListView listViewResult = (ListView) rootView
				.findViewById(R.id.listViewResult);
		listViewResult.setAdapter(modeAdapter);
		listViewResult.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				ObjectResult ra = (ObjectResult) parent
						.getItemAtPosition(position);
				data.setTempContactKey(ra.getCon_id());
				data.getMainActivity().displayView(8);
				System.out.println(ra.getPicResult());
			}
		});
		}


		
	}

	private void setFancyCoverFlow() {
		fancyCoverFlow = (FancyCoverFlow) rootView
				.findViewById(R.id.fancyCoverFlow);
		fancyCoverFlow.setAdapter(adapter);
		fancyCoverFlow.setUnselectedAlpha(1.0f);
		fancyCoverFlow.setUnselectedSaturation(-1.0f);
		fancyCoverFlow.setUnselectedScale(0.5f);
		fancyCoverFlow.setSpacing(10);
		fancyCoverFlow.setMaxRotation(0);
		fancyCoverFlow.setScaleDownGravity(0.2f);
		fancyCoverFlow.setScaleX(1.6f);
		fancyCoverFlow.setScaleY(1.6f);
		fancyCoverFlow.setY(47);
		fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
	}

}
