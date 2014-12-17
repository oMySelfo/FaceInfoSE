package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.List;

import th.ac.kmitl.it.faceinfo.adapter.AllContactAdapter;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.os.Bundle;
import android.renderscript.AllocationAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PeopleOfGroup extends Fragment{
	public PeopleOfGroup() {}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_allcontract, container,false);
		ImageButton findbtt = (ImageButton) rootView.findViewById(R.id.findbtt);
		findbtt.setImageResource(R.drawable.add_peoplemini);
		//AllContactAdapter adapter = new AllContactAdapter(inflater, listContact);
		ListView list = (ListView) rootView.findViewById(R.id.listViewResult);
		//list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
		return rootView;
	}

}
