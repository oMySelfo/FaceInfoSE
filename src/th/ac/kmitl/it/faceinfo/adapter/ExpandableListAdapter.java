package th.ac.kmitl.it.faceinfo.adapter;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.main.R;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	List<String> Name ;
	List<String> Social;
	List<String> Etc;
	List<String> Field;
	private LayoutInflater mInflater;
	private String[] hint;
	private String[] inputData;

	

	public String[] getInputData() {
		return inputData;
	}


	public ExpandableListAdapter(LayoutInflater mInflater) {
		super();
		this.mInflater = mInflater;
		hint = new String[]{"NickName","Full Name","Address","Phone","FaceBook","BirthDay","E-mail","Detail"};
		inputData=new String[8];
		Name = new ArrayList<String>();
		Social = new ArrayList<String>();
		Etc = new ArrayList<String>();
		Field = new ArrayList<String>();
		Field.add("Profile");
		Field.add("Social");
		Field.add("Etc");
		Name.add("NickName");
		Name.add("Full Name");
		Name.add("Address");
		Social.add("Phone");
		Social.add("FaceBook");	
		//Etc.add("Address");
		Etc.add("BirthDay");
		Etc.add("Detail");
		Etc.add("E-mail");
	}

	@Override
	public String getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		if(groupPosition == 0){
			return Name.get(childPosition);
		}else if(groupPosition == 1){
			return Social.get(childPosition);
		}else{
			return Etc.get(childPosition);
		}
		
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		String childtext = (String) getChild(groupPosition, childPosition);
		convertView = mInflater.inflate(R.layout.child_expandablelistview, null);
		final EditText txt = (EditText) convertView.findViewById(R.id.edit_exp);
		txt.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					for(int i=0;i<hint.length;i++){
						if(hint[i].equals(txt.getHint())){
							System.out.println(hint[i]);
							inputData[i]=txt.getText().toString();
						}
					}
					
				}
				
			}
		});
		txt.setHint(childtext);
		
		
			return convertView;
		// TODO Auto-generated method stub
		
		
	
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		if(groupPosition == 0){
			return Name.size();
		}else if(groupPosition == 1){
			return Social.size();
		}else{
			return Etc.size();
		}

	}

	@Override
	public String getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return Field.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return Field.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView =mInflater.inflate(R.layout.header_expandablelistview, null);
		String headerTitle = (String) getGroup(groupPosition);
		TextView header = (TextView) convertView.findViewById(R.id.exp_header);
		header.setTypeface(null, Typeface.BOLD);
		header.setText(headerTitle);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
