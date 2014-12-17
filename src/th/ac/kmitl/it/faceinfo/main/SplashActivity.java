package th.ac.kmitl.it.faceinfo.main;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.facebook.FacebookManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class SplashActivity extends Activity{
	
	private ImageButton btn_loginfacebook;
	FacebookManager fm;
	DatabaseManager dbm;
	Data data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		btn_loginfacebook = (ImageButton) findViewById(R.id.btn_loginfacebook);
		btn_loginfacebook.setVisibility(View.INVISIBLE);
		Data.getData().setDatabaseManager(this);
		Data.getData().setFacebookManager(this);
		//
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
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
					SplashActivity.this.finish();
				}
			});
		}else{
			startActivity(new Intent(SplashActivity.this, MainActivity.class));
			SplashActivity.this.finish();
		}
		
		
		
	}
}
