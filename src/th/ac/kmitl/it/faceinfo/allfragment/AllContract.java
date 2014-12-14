package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.adapter.ContactAdapter;
import th.ac.kmitl.it.faceinfo.database.test_datacontract;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AllContract extends Fragment {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.list_allcontract, container,false);
		ListView list = (ListView) rootView.findViewById(R.id.listView1);
		list.setAdapter(new ContactAdapter(inflater, createlist()));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				System.out.println(position);
				
			}
		});
		return rootView;
	}
	private List<testdata> createlist(){
		List<testdata> data = new ArrayList<testdata>();
		String[] name= {"jays","222","111"};
		for(int i=0;i<name.length;i++){
			testdata test = new testdata("AAA",name[i]);
			data.add(test);
		}
		return  data;
		
	}
	

	
	

}
