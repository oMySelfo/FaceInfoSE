package th.ac.kmitl.it.faceinfo.allfragment;



import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.adapter.CoverFlowAdapter;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;


public class AddContacts extends Fragment {
	public AddContacts() {}
	private FancyCoverFlow fancyCoverFlow;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.addcontacts, container,false);
		List<Integer> images = new ArrayList<Integer>();
		images.add(R.drawable.add_image);
		
		CoverFlowAdapter adapter = new CoverFlowAdapter();
		adapter.setImages(images);
		
		
		fancyCoverFlow = (FancyCoverFlow) rootView
				.findViewById(R.id.fancyCoverFlow);

		fancyCoverFlow.setAdapter(adapter);
		fancyCoverFlow.setUnselectedAlpha(1.0f);
		fancyCoverFlow.setUnselectedSaturation(-1.0f);
		fancyCoverFlow.setUnselectedScale(0.5f);
		fancyCoverFlow.setSpacing(10);
		fancyCoverFlow.setMaxRotation(0);
		fancyCoverFlow.setScaleDownGravity(0.2f);
		fancyCoverFlow.setScaleX(1.8f);
		fancyCoverFlow.setScaleY(1.8f);
		fancyCoverFlow
				.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
		
		fancyCoverFlow
		.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> view,
					View container, int position, long id) {
				alertDiaLog();
				System.out.println("FlowCurPos " + position);
				return true;
			}
		});
		
	
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().addToBackStack(null)
					.replace(R.id.addcontact_frame, new expandableList()).commit();
		return rootView;
	}
	
	
	


	private void alertDiaLog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Data.getData().getMainActivity());
		builder.setTitle("Delete")
				.setIcon(getResources().getDrawable(R.drawable.ic_launcher))
				.setMessage("Do you want to delete this picture?")
				.setPositiveButton("Not Now",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						})
				.setNegativeButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								//delete pic
							}
						});

		AlertDialog alert = builder.create();
		alert.show();
	}
}
