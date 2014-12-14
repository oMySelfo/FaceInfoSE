package th.ac.kmitl.it.faceinfo.allfragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;



import th.ac.kmitl.it.faceinfo.main.MainActivity;
import th.ac.kmitl.it.faceinfo.main.Model;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ListGroup extends Fragment {
	public ListGroup() {}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.listgroup, container,false);
		ImageButton creategroup = (ImageButton) rootView.findViewById(R.id.btn_addgruop);
		
		
		creategroup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Model.getActivity().displayView(6);
			}
		});
		return rootView;
	}

}
