package th.ac.kmitl.it.faceinfo.allfragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import th.ac.kmitl.it.faceinfo.adapter.AllContactAdapter;

import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.database.Photo;
import th.ac.kmitl.it.faceinfo.faceplusplus.FacePlusPlus;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.MainActivity;
import th.ac.kmitl.it.faceinfo.main.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.widget.DrawerLayout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AllContract extends Fragment {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private List<Contact> listContact;
	private Data data;
	private DatabaseManager dbm;
	private View rootView;
	private Bitmap bitmap;
	private Uri uri;
	private String path;
	private int REQUEST_GALLERY = 1;
	private int REQUEST_CAMERA = 2;
	private MainActivity ma;
	private FacePlusPlus fpp;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.list_allcontract, container,false);
		ma = Data.getData().getMainActivity();
		
		data = Data.getData();
		fpp = data.getFacePP();
		dbm = data.getDmb();
		listContact = dbm.getAllContact();
		
		
		ListView list = (ListView) rootView.findViewById(R.id.listViewResult);
		list.setAdapter(new AllContactAdapter(inflater,listContact ));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				data.setTempKey(listContact.get(position).getCon_id());
				data.getMainActivity().displayView(8);
			}
		});
		showConTextMenu();
		
		
		return rootView;
	}
	
	private void showConTextMenu(){
		ImageButton findbtt = (ImageButton) rootView.findViewById(R.id.findbtt);
		registerForContextMenu(findbtt);
		findbtt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 ((ImageButton)v).showContextMenu();
			}
		});
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, v.getId(), 0, "TakePicture");
		menu.add(0, v.getId(), 0, "Gallery");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "TakePicture") {
			System.out.println("TakePicture");
			Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
			String imageFileName = "IMG_" + timeStamp + ".jpg";
			File f = new File(Environment.getExternalStorageDirectory(),
					"DCIM/Camera/" + imageFileName);
			uri = Uri.fromFile(f);
			path = uri.getPath();
			it.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			startActivityForResult(Intent.createChooser(it, "Take a picture"),
					REQUEST_CAMERA);

		} else if (item.getTitle() == "Gallery") {
			System.out.println("Gallery");
			Intent itGallery = new Intent(Intent.ACTION_GET_CONTENT);
			itGallery.setType("image/*");
			startActivityForResult(
					Intent.createChooser(itGallery, "Select Picture"),
					REQUEST_GALLERY);
		} else {
			return false;
		}
		return true;

	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (requestCode == REQUEST_CAMERA && resultCode == ma.RESULT_OK) {
				ma.getContentResolver().notifyChange(uri, null);
				ContentResolver cr = ma.getContentResolver();
				bitmap = Media.getBitmap(cr, uri);

			} else if (requestCode == REQUEST_GALLERY
					&& resultCode == ma.RESULT_OK) {
				Uri uri = data.getData();
				bitmap = Media.getBitmap(ma.getContentResolver(), uri);
			}
			
			fpp.faceIndentify(bitmap);
			this.data.getMainActivity().displayView(5);
			
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(bitmap);

	}
	
	


	
	

}
