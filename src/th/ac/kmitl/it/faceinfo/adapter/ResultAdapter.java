package th.ac.kmitl.it.faceinfo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import th.ac.kmitl.it.faceinfo.main.R;

public class ResultAdapter extends ArrayAdapter<ObjectResult>{

	public ResultAdapter(Context context,	List<ObjectResult> objects) {
		super(context, 0, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ObjectResult user = getItem(position);    
	    if (convertView == null) {
	    	convertView = LayoutInflater.from(getContext()).inflate(R.layout.showresult_row, parent, false);
	    	}
	    TextView NameResult = (TextView) convertView.findViewById(R.id.NameResult);
	    ImageView ImageResult = (ImageView) convertView.findViewById(R.id.picResult);
	    
	    NameResult.setText(user.getNameResult());
	    ImageResult.setImageBitmap(user.getPicResult());
	    
	    return convertView;
	}
}
