package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.List;

import th.ac.kmitl.it.faceinfo.adapter.AllContactAdapter;
import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.os.Bundle;
import android.renderscript.AllocationAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView;
import android.widget.TextView;

public class PeopleOfGroup extends Fragment{
	private Data data;
	private DatabaseManager dbm;
	private List<Contact> listContact;
	private TextView amountfriends;
	private SearchView search;
	public PeopleOfGroup() {}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_allcontract, container,false);
		ImageButton findbtt = (ImageButton) rootView.findViewById(R.id.findbtt);
		data = Data.getData();
		dbm = data.getDmb();
		search = (SearchView) rootView.findViewById(R.id.searchView);
		search.setVisibility(View.INVISIBLE);
		
		amountfriends = (TextView) rootView.findViewById(R.id.amountfriends);
		listContact = dbm.getContactGroup(data.getTempGroupKey());
		findbtt.setImageResource(R.drawable.add_peoplemini);
		amountfriends.setText("friends :"+listContact.size());
		AllContactAdapter adapter = new AllContactAdapter(inflater, listContact);
		ListView list = (ListView) rootView.findViewById(R.id.listViewResult);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				data.setTempContactKey(listContact.get(position).getCon_id());
				data.getMainActivity().displayView(8);
				
			}
		});
		
		findbtt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				data.getMainActivity().displayView(7);
				
			}
		});
		return rootView;
	}

}
