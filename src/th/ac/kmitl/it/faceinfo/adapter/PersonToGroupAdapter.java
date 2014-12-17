package th.ac.kmitl.it.faceinfo.adapter;

import java.util.ArrayList;
import java.util.List;


import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonToGroupAdapter extends BaseAdapter {
	private static List<Contact> listContact;


	private LayoutInflater mInflater;
	private List<Boolean> inGroup;
	
	public PersonToGroupAdapter (LayoutInflater Inflater,List<Contact> listContact) {
		super();
		this.listContact = listContact;
		inGroup = new ArrayList<Boolean>();
		mInflater = Inflater;
		for(Contact contact:listContact){
			inGroup.add(contact.isInGroup(Data.getData().getTempGroupKey()));
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listContact.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listContact.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = mInflater.inflate(R.layout.addpersontogroup, parent, false);
		ImageView img = (ImageView) convertView.findViewById(R.id.addpersontogroup_pic);
		TextView txt = (TextView) convertView.findViewById(R.id.addpersontogroup_name);
		CheckBox cb = (CheckBox) convertView.findViewById(R.id.addpersontogroup_cb);
		//Bitmap bmImg = BitmapFactory.decodeFile(listContact.get(position).getPath());
		//img.setImageBitmap(bmImg);	
		cb.setChecked(inGroup.get(position));
		//if(listContact.get(position).get)
		//cb.
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				inGroup.set(position, isChecked);
				
			}
		});
		txt.setText(listContact.get(position).getCon_name());
		
		return convertView;
	}
	public List<Boolean> getInGroup() {
		return inGroup;
	}


}
