package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.adapter.PersonToGroupAdapter;
import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.MainActivity;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PersonToGroup extends Fragment {
	private PersonToGroupAdapter adapter;
	private  Data data;
	private DatabaseManager dbm;
	private List<Contact> listContact;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.list_persontogroup, container,false);
		ListView list = (ListView) rootView.findViewById(R.id.persontogroup_list);
		data = Data.getData();
		dbm = data.getDmb();
		listContact = dbm.getAllContact();
		
		adapter = new PersonToGroupAdapter(inflater,listContact);
		list.setAdapter(adapter);
		Button btn = (Button) rootView.findViewById(R.id.persontogroup_button);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//update to DB
				List<Boolean> inGroup = adapter.getInGroup();
				for(int i=0;i<inGroup.size();i++ ){
					System.out.println(i +" : "+inGroup.get(i));
				}
				
				
			}
		});
		
		return rootView;
	}
	

}
