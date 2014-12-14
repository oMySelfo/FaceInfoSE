package th.ac.kmitl.it.faceinfo.allfragment;


import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.Permission.Type;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnLogoutListener;

import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.R;
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
	SimpleFacebook sf;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_test, container,false);
		
		Data.getData().getDmb().checkUser();
		
		btn1 = (Button)rootView.findViewById(R.id.button1);
		btn2 = (Button)rootView.findViewById(R.id.button2);
		sf = SimpleFacebook.getInstance(this.getActivity());
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sf.login(new OnLoginListener() {					
					
					@Override
					public void onLogin() {
						Log.d("Facebook", "login");
						
					}

					@Override
					public void onThinking() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onException(Throwable throwable) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onFail(String reason) {
						
					}

					@Override
					public void onNotAcceptingPermissions(Type type) {
						
						
					}
				});
				
			}
		});
		
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sf.logout(new OnLogoutListener() {
					
					@Override
					public void onFail(String reason) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onException(Throwable throwable) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onThinking() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onLogout() {
						Log.d("Facebook", "logout");
					}
				});
				
			}
		});
		
		
		
		
		
		return rootView;
	}
	

}
