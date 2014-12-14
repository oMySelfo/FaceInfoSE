package th.ac.kmitl.it.faceinfo.facebook;

import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.faceplusplus.FacePlusPlus;
import th.ac.kmitl.it.faceinfo.main.Data;

import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.Permission.Type;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.entities.Profile.Properties;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnLogoutListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import android.app.Activity;
import android.util.Log;

public class FacebookManager {
	private SimpleFacebook sf;
	private DatabaseManager dbm;
	private FacePlusPlus fpp;

	public FacebookManager(Activity activity) {
		sf = SimpleFacebook.getInstance(activity);
		dbm = Data.getData().getDmb();
		fpp = Data.getData().getFacePP();
	}

	public void login() {
		sf.login(new OnLoginListener() {

			@Override
			public void onLogin() {
				Log.d("Facebook", "login");
				Properties properties = new Properties.Builder().add(
						Profile.Properties.ID).build();
				sf.getProfile(properties, new OnProfileListener() {

					@Override
					public void onComplete(Profile response) {
						super.onComplete(response);
						dbm.insertUserKey(response.getId());
						fpp.addUser();
					}
				});
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

	public void logout() {
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
				fpp.deleteUser();
				dbm.deleteUserKey();
			}
		});

	}

}
