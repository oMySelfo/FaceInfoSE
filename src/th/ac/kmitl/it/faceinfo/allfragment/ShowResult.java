package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.ArrayList;
import java.util.List;

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
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import th.ac.kmitl.it.faceinfo.adapter.CoverFlowAdapter;
import th.ac.kmitl.it.faceinfo.adapter.ObjectResult;
import th.ac.kmitl.it.faceinfo.adapter.ResultAdapter;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.showresult, container, false);
		ma = Data.getData().getMainActivity();
		setImagesFirst();
		setImagesNdRd();
		setFancyCoverFlow();
		
		//event First
		fancyCoverFlow.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> view, View container,
					int position, long id) {
				System.out.println("Okkkkkkkkkkkkkkkk");
			}
		});
		return rootView;
	}

	private void setImagesFirst() {
		images = new ArrayList<Bitmap>();
		images.add(BitmapFactory.decodeResource(rootView.getResources(),
				R.drawable.tae));
		images.add(BitmapFactory.decodeResource(rootView.getResources(),
				R.drawable.tae));
		adapter = new CoverFlowAdapter();
		adapter.setImages(images);

	}

	private void setImagesNdRd() {
		or = new ArrayList<ObjectResult>();
		// setIMages second
		or.add(new ObjectResult("Thanakorn" + "\n" + "45%", BitmapFactory
				.decodeResource(rootView.getResources(), R.drawable.tae)));
		// setIMages third
		or.add(new ObjectResult("TaeGoodBoy" + "\n" + "2%", BitmapFactory
				.decodeResource(rootView.getResources(), R.drawable.tae)));

		ArrayAdapter<ObjectResult> modeAdapter = new ResultAdapter(ma, or);
		ListView listViewResult = (ListView) rootView
				.findViewById(R.id.listViewResult);
		listViewResult.setAdapter(modeAdapter);

		// event ลำดับ 2,3
		listViewResult.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				ObjectResult ra = (ObjectResult) parent
						.getItemAtPosition(position);
				System.out.println(ra.getPicResult());
			}
		});
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
