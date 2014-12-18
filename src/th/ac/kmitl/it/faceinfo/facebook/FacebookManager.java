package th.ac.kmitl.it.faceinfo.facebook;

import java.util.List;

import org.json.JSONException;

import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.faceplusplus.FacePlusPlus;
import th.ac.kmitl.it.faceinfo.main.Data;

import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.Permission.Type;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.entities.Profile.Properties;
import com.sromku.simple.fb.listeners.OnFriendsListener;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnLogoutListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import android.app.Activity;
import android.util.Log;

public class FacebookManager {
	private SimpleFacebook sf;
	private DatabaseManager dbm;
	private FacePlusPlus fpp;
	private Properties properties;

	public FacebookManager(Activity activity) {
		sf = SimpleFacebook.getInstance(activity);
		dbm = Data.getData().getDmb();
		fpp = Data.getData().getFacePP();
	}

	public void login() {
		sf.login(new OnLoginListener() {

			
			// Set UserKey and Sync
			@Override
			public void onLogin() {
				Log.d("Facebook", "login");
				 properties = new Properties.Builder().add(
						Profile.Properties.ID).build();
				sf.getProfile(properties, new OnProfileListener() {
					
					@Override
					public void onException(Throwable throwable) {
						super.onException(throwable);
					}
					@Override
					public void onFail(String reason) {
						System.out.println("Reason" + reason);
						super.onFail(reason);
					}

					@Override
					public void onComplete(Profile response) {
						super.onComplete(response);
						dbm.insertUserKey(response.getId());
						fpp.addUser();
						System.out.println("---------------------------" + dbm.checkUserKey());
						
					}
				});
				
				//Set Friend Facebook and Sync
				 properties = new Properties.Builder()
				.add(Profile.Properties.ID)
				.add(Profile.Properties.BIRTHDAY)
				.add(Profile.Properties.EMAIL)
				.add(Profile.Properties.GENDER)
				.add(Profile.Properties.NAME)
				.add(Profile.Properties.LINK)
				.build();
				 sf.getFriends(properties, new OnFriendsListener() {
					 @Override
						public void onComplete(List<Profile> response) {
							for(Profile p:response){
								fpp.createContact();
								try {			
									String con_id = fpp.RESULT.getString("person_id");
									String con_name = p.getName();
									String con_facebook = p.getLink();
									dbm.insertContactFacebook(con_id, con_name, con_facebook);
								} catch (JSONException e) {
									e.printStackTrace();
								}
								
							}
						}
				});
				 Data.getData().getMainActivity().displayView(0);
				
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
				fpp.deleteAllContact(dbm.getAllContact());		
				fpp.deleteUser();
				
				dbm.deleteDatabase();
			}
		});

	}
	

}
