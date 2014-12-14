package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.ArrayList;

import th.ac.kmitl.it.faceinfo.adapter.PersonToGroupAdapter;
import th.ac.kmitl.it.faceinfo.main.Model;
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
	PersonToGroupAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.list_persontogroup, container,false);
		ListView list = (ListView) rootView.findViewById(R.id.persontogroup_list);
		adapter = new PersonToGroupAdapter(inflater,Model.getListperson());
		list.setAdapter(adapter);
		Button btn = (Button) rootView.findViewById(R.id.persontogroup_button);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//update to DB
				/*for(int i=0;i<Model.getListperson().size();i++){
					System.out.println("Model :"+Model.getListperson().get(i).isGroup());
				}
				Model.setListperson(adapter.getListContact());
				for(int i=0;i<Model.getListperson().size();i++){
					System.out.println("aaa :"+Model.getListperson().get(i).isGroup());
				}*/
				
			}
		});
		
		return rootView;
	}
	

}
