package th.ac.kmitl.it.faceinfo.main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import th.ac.kmitl.it.faceinfo.allfragment.*;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.faceplusplus.AddContact;



import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private String[] navMenuTitles;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;
	private CharSequence mTitle;
	private List<Fragment> listFragment;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Model.setActivity(this);
		listFragment = new ArrayList<Fragment>();
		setContentView(R.layout.activity_main);
		DatabaseManager dbm = new DatabaseManager(this);
		Data.getData().setDatabaseManager(dbm);
		mTitle = getTitle();
		createfragment();
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.main_frame);
		mDrawerList = (ListView) findViewById(R.id.slidermenu);
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		displayView(0);
		getActionBar().setHomeButtonEnabled(true);
		SetMenuSliding();

	}
	private void createfragment(){
		listFragment.add(new AllContract());
		listFragment.add(new AddContacts());
		listFragment.add(new ListGroup());
		listFragment.add(new Aboutus());
		listFragment.add(new test1());
		listFragment.add(new test2());
		listFragment.add(new CreateGroup());
		
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			displayView(position);
		}
	}
	
	public void displayView(int position) {
		Fragment fragment = listFragment.get(position);
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().addToBackStack(null)
					.replace(R.id.frame_container, fragment).commit();
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}
	
	
	private void SetMenuSliding() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		
		String[][] dataSliding = new String[][] {
				{ R.drawable.ic_contacts + "", "All Contacts" },
				{ R.drawable.bt_plus + "", "Add Contacts" },
				{ R.drawable.ic_group + "", "Group" }, 
				{ R.drawable.ic_aboutus + "", "About Us" },
				{R.drawable.bt_plus+"","Test"},
				{R.drawable.bt_plus+"","Test2"}};
		String[] from = { "icon", "title" };
		int[] to = { R.id.icon, R.id.title };
		List<HashMap<String, String>> listSliding = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < dataSliding.length; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("icon", dataSliding[i][0]);
			hm.put("title", dataSliding[i][1]);
			listSliding.add(hm);
		}
		SimpleAdapter adapterSliding = new SimpleAdapter(this, listSliding,
				R.layout.item_in_slidermenu, from, to);

		mDrawerList.setAdapter(adapterSliding);
	}
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
//		 case R.id.action_settings:
//			 return true
		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
