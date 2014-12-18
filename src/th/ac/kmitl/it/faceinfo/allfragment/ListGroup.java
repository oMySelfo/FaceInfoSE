package th.ac.kmitl.it.faceinfo.allfragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.database.Group;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.MainActivity;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ListGroup extends Fragment {
	public ListGroup() {}
	
	private Data data;
	private MainActivity ma;
	private DatabaseManager dbm;
	private TextView amountgroups;
	private List<Group> listGroup;
	String[][] dataGroup = new String[][] { { "Friends", R.drawable.friend_cl + "" },
			{ "Family", R.drawable.family_cl + "" },{"Work", R.drawable.work_cl + "" }};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.listgroup, container,false);
		ImageButton creategroup = (ImageButton) rootView.findViewById(R.id.btn_addgruop);
		
		data = Data.getData();
		dbm = data.getDmb();
		ma = data.getMainActivity();
		amountgroups = (TextView) rootView.findViewById(R.id.amountgroup);
		
		listGroup = dbm.getAllGroup();
		amountgroups.setText("Groups :"+listGroup.size());

		
		creategroup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ma.displayView(6);
			}
		});
		
		String[] from = { "picGroup", "nameGroup"};
		int[] to = { R.id.picGroup, R.id.txtNameGroup};
		List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		for(Group group :listGroup){
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("nameGroup", group.getGroup_name());
			hm.put("picGroup", group.getGroup_img() );
			aList.add(hm);
		}
		SimpleAdapter adapter = new SimpleAdapter(ma,aList,
				R.layout.group_row, from, to);
		ListView listViewGroup = (ListView) rootView.findViewById(R.id.listgroup);
		listViewGroup.setAdapter(adapter);
		
		listViewGroup.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {

            	
            	data.setTempGroupKey(listGroup.get(position).getGroup_id());
                ma.displayView(10);

            }
        });
		
		listViewGroup.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> view, View container,
					int position, long id) {
				alertDiaLog(position);
				return true;
			}
			
		});
		return rootView;
	}
	
	private void alertDiaLog(final int position){
		AlertDialog.Builder builder = new AlertDialog.Builder(ma);
    	builder.setTitle("Delete").setIcon(getResources().getDrawable(R.drawable.bin))
    	.setMessage("Are you sure you want to delete ?")
    	.setPositiveButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//delete group
				String group_id = listGroup.get(position).getGroup_id();
				dbm.deleteGroup(group_id);
				ma.displayView(2);
				
			}
		});
    	
    	AlertDialog alert = builder.create();
    	alert.show();
	}
}
