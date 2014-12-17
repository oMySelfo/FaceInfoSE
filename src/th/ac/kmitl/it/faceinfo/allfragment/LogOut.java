package th.ac.kmitl.it.faceinfo.allfragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.database.Photo;
import th.ac.kmitl.it.faceinfo.facebook.FacebookManager;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.R;
import th.ac.kmitl.it.faceinfo.main.SplashActivity;

public class LogOut extends Fragment {
	public LogOut() {}

	FacebookManager fm;
	DatabaseManager dbm;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_allcontract, container,
				false);
		dbm = Data.getData().getDmb();
		dbm.checkUserKey();
		fm = Data.getData().getFacebookManager();
		alertDiaLog();
		return rootView;
	}

	private void alertDiaLog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Data.getData()
				.getMainActivity());
		builder.setTitle("Logout")
				.setIcon(getResources().getDrawable(R.drawable.ic_launcher))
				.setMessage("Do you want to logout ?")
				.setPositiveButton("Not Now",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						})
				.setNegativeButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								System.out.println("---------Logout---------");
								fm.logout();
								startActivity(new Intent(getActivity(), SplashActivity.class));
								getActivity().finish();
							
							}
						});

		AlertDialog alert = builder.create();
		alert.show();
	}
}
