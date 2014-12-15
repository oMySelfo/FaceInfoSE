package th.ac.kmitl.it.faceinfo.adapter;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.main.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

public class CoverFlowAdapter extends FancyCoverFlowAdapter{
	
	private List<Integer> images = new ArrayList<Integer>(); 
	

	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public Object getItem(int position) {
		return images.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getCoverFlowItem(int i, View reusableView, ViewGroup viewGroup) {
		ImageView imageView = null;
		
		
		if (reusableView != null) {
			imageView = (ImageView) reusableView;
		} else {
			imageView = new ImageView(viewGroup.getContext());
			imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			imageView
					.setLayoutParams(new FancyCoverFlow.LayoutParams(300, 300));
		}
		imageView.setImageResource((Integer) this.getItem(i));
		
		
		return imageView;
	}

	public List<Integer> getImages() {
		return images;
	}

	public void setImages(List<Integer> images) {
		this.images = images;
	}
	

}
