package th.ac.kmitl.it.faceinfo.allfragment;
import th.ac.kmitl.it.faceinfo.main.Model;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class CreateGroup extends Fragment{
public CreateGroup() {}
	
	String arr_images[] = { R.drawable.family_cl + "",
		R.drawable.friend_cl + "", R.drawable.work_cl + "",
		R.drawable.etc1_cl + "", R.drawable.etc2_cl + "",
		R.drawable.etc3_cl + "", R.drawable.etc3_cl + "",
		R.drawable.etc4_cl + "", R.drawable.etc5_cl + "",
		R.drawable.etc6_cl + "", R.drawable.etc7_cl + "" };
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.creategroup, container,false);
		
		final Spinner spinnerPicGroup = (Spinner) rootView
				.findViewById(R.id.spinner_picgroup);
		
		spinnerPicGroup.setAdapter(new MyAdapter(Model.getActivity(),
				R.layout.row_creategroup, arr_images));
		Button bt_addgroup = (Button)rootView.findViewById(R.id.btn_addgroup);
		
		bt_addgroup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText et = (EditText)rootView.findViewById(R.id.txt_namegroup);
				Model.getActivity().displayView(2);
			}			
		});

		return rootView;
	}

	public class MyAdapter extends ArrayAdapter<String> {

		public MyAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {

			LayoutInflater inflater = Model.getActivity().getLayoutInflater();
			View row = inflater.inflate(R.layout.row_creategroup, parent,
					false);

			ImageView icon = (ImageView) row.findViewById(R.id.image);
			icon.setImageResource(Integer.parseInt(arr_images[position]));

			return row;
		}
	}
}
