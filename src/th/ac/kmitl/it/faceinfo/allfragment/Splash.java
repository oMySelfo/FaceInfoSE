package th.ac.kmitl.it.faceinfo.allfragment;

import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.facebook.FacebookManager;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.MainActivity;
import th.ac.kmitl.it.faceinfo.main.R;
import th.ac.kmitl.it.faceinfo.main.SplashActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.app.Fragment;
import android.content.Intent;

public class Splash extends Fragment {
	public Splash() {}
	
	private ImageButton btn_loginfacebook;
	FacebookManager fm;
	DatabaseManager dbm;
	Data data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.splash, container, false);
		btn_loginfacebook = (ImageButton) rootView.findViewById(R.id.btn_loginfacebook);
		btn_loginfacebook.setVisibility(View.INVISIBLE);
		
		data = Data.getData();
		dbm = data.getDmb();
		fm = data.getFacebookManager();
		System.out.println(dbm.checkUserKey()+"--------------------");
		if(!dbm.checkUserKey()){
			btn_loginfacebook.setVisibility(View.VISIBLE);
			btn_loginfacebook.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					fm.login();
					Data.getData().getMainActivity().displayView(9);
				}
			});
		}else{
			Handler handler = new Handler();
			Runnable delayRunnable = new Runnable() {

			     @Override
			     public void run() {
			    	 Data.getData().getMainActivity().displayView(9);
			    }
			};      
			handler.postDelayed(delayRunnable, 2000);
		}
		return rootView;
	}
}
