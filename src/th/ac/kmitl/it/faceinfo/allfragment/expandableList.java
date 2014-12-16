package th.ac.kmitl.it.faceinfo.allfragment;

import th.ac.kmitl.it.faceinfo.adapter.ExpandableListAdapter;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;

public class expandableList extends Fragment {
	private int NAME=0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.expandablelistview, container,false);
		ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView1);
		expListView.setAdapter(new ExpandableListAdapter(inflater));
		expListView.expandGroup(NAME);
		
			
			
		return rootView;
	}
	

}
