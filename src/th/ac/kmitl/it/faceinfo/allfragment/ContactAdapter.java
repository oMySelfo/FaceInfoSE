package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.main.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

	private static List<testdata> listContact;

	private LayoutInflater mInflater;
	
	public ContactAdapter(LayoutInflater Inflater,List<testdata> list) {
		super();
		listContact = list;
		mInflater = Inflater;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listContact.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listContact.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = mInflater.inflate(R.layout.showperson, parent, false);
		TextView txt = (TextView) convertView.findViewById(R.id.person_txt);
		ImageView img = (ImageView) convertView.findViewById(R.id.person_img);
		txt.setText(listContact.get(position).getName());
		//Bitmap bmImg = BitmapFactory.decodeFile(listContact.get(position).getPath());
		//img.setImageBitmap(bmImg);	
		return convertView;
	}

}
