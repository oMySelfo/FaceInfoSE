package th.ac.kmitl.it.faceinfo.adapter;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.main.R;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

public class CoverFlowAdapter extends FancyCoverFlowAdapter{
	
	private List<Bitmap> images = new ArrayList<Bitmap>(); 
	

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
		imageView.setImageBitmap((Bitmap) this.getItem(i));
		//imageView.setImageResource((Integer) this.getItem(i));
		
		
		return imageView;
	}

	public List<Bitmap> getImages() {
		return images;
	}

	public void setImages(List<Bitmap> images) {
		this.images = images;
	}
	

}
