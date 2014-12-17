package th.ac.kmitl.it.faceinfo.allfragment;


import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.Permission.Type;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.entities.Profile.Properties;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnLogoutListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.facebook.FacebookManager;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.MainActivity;
import th.ac.kmitl.it.faceinfo.main.R;
import th.ac.kmitl.it.faceinfo.main.SplashActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class test1 extends Fragment{ 
	public test1() {}
	
	Button btn1;
	Button btn2;
	FacebookManager fm;
	DatabaseManager dbm;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_test, container,false);
		
		dbm = Data.getData().getDmb();
		dbm.checkUserKey();
		fm = Data.getData().getFacebookManager();
		
		btn1 = (Button)rootView.findViewById(R.id.button1);
		btn2 = (Button)rootView.findViewById(R.id.button2);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fm.login();
			}
		});
		
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fm.logout();
			}
		});
		
		return rootView;
	}
	

}
