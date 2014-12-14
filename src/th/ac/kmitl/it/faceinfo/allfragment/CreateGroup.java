package th.ac.kmitl.it.faceinfo.allfragment;

import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateGroup extends Fragment{
public CreateGroup() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.creategroup, container,false);
		return rootView;
	}

}
