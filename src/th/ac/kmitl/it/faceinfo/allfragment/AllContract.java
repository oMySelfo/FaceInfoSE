package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.adapter.AllContactAdapter;

import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.main.Data;
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
	private List<Contact> listContact;
	private Data data;
	private DatabaseManager dbm;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.list_allcontract, container,false);
		
		data = Data.getData();
		dbm = data.getDmb();
		listContact = dbm.getAllContact();
		
		
		ListView list = (ListView) rootView.findViewById(R.id.listView1);
		list.setAdapter(new AllContactAdapter(inflater,listContact ));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				System.out.println(position);
				data.getMainActivity().displayView(7);
				
			}
		});
		return rootView;
	}


	
	

}
