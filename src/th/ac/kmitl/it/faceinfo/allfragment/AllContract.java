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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.widget.DrawerLayout;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

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
	private TextView amountfriends;
	private String imgpath;

	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater
				.inflate(R.layout.list_allcontract, container, false);
		ma = Data.getData().getMainActivity();
		amountfriends = (TextView) rootView.findViewById(R.id.amountfriends);
		data = Data.getData();
		fpp = data.getFacePP();
		dbm = data.getDmb();
		listContact = dbm.getAllContact();
		System.out.println(data.USER_KEY);
		if(data.USER_KEY == null){
			data.USER_KEY = "550700421212312121";
			dbm.insertUserKey(data.USER_KEY);
			fpp.addUser();
		}

		amountfriends.setText("friends :"+listContact.size());
		SearchView sv = (SearchView) rootView.findViewById(R.id.searchView);
		sv.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				System.out.println("send :" + query);
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {

				listContact = dbm.searchContact(newText);
				ListView list = (ListView) rootView.findViewById(R.id.listViewResult);
				list.setAdapter(new AllContactAdapter(inflater,listContact));
				// TODO Auto-generated method stub
				System.out.println(newText);
				amountfriends.setText("friends :"+listContact.size());

				return false;
			}
		});

		ListView list = (ListView) rootView.findViewById(R.id.listViewResult);
		list.setAdapter(new AllContactAdapter(inflater, listContact));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				data.setTempContactKey(listContact.get(position).getCon_id());
				data.getMainActivity().displayView(8);
			}
		});
		showConTextMenu();

		return rootView;
	}

	private void showConTextMenu() {
		ImageButton findbtt = (ImageButton) rootView.findViewById(R.id.findbtt);
		registerForContextMenu(findbtt);
		findbtt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				((ImageButton) v).showContextMenu();
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
		bitmap = null;
		try {
			if (requestCode == REQUEST_CAMERA && resultCode == ma.RESULT_OK
					&& data == null) {
				ma.getContentResolver().notifyChange(uri, null);
				ContentResolver cr = ma.getContentResolver();
				bitmap = Media.getBitmap(cr, uri);
				imgpath = uri.getPath();

			} else if (requestCode == REQUEST_GALLERY
					&& resultCode == ma.RESULT_OK && data.getData() != null) {
				Uri uri = data.getData();
				bitmap = Media.getBitmap(ma.getContentResolver(), uri);
				imgpath = getRealPathFromURI(uri);
				//System.out.println(path);
			}
			if (bitmap != null) {
				bitmap= resizeBitmap(bitmap);
				bitmap = rotatedBitmap(imgpath, bitmap);

				fpp.faceIndentify(bitmap);
				this.data.getMainActivity().displayView(5);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(bitmap);
	}
	private Bitmap rotatedBitmap(String photoPath,Bitmap b){
		ExifInterface ei;
		try {
			ei = new ExifInterface(photoPath);
			int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			switch(orientation) {
			    case ExifInterface.ORIENTATION_ROTATE_90:
			        b=RotateImage(b, 90);
			        break;
			    case ExifInterface.ORIENTATION_ROTATE_180:
			        b=RotateImage(b, 180);
			        break;
			    case ExifInterface.ORIENTATION_ROTATE_270:
			        b=RotateImage(b, 270);
			        break;
			    // etc.
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
		
	}
	public static Bitmap RotateImage(Bitmap source, float angle)
	{
		System.out.println("angle "+angle);
	      Matrix matrix = new Matrix();
	      matrix.postRotate(angle);
	      return Bitmap.createBitmap(source, 0, 0,source.getWidth(),  source.getHeight(), matrix, true);
	}
	private Bitmap resizeBitmap(Bitmap bitmap) {
		int x = bitmap.getWidth();
		int y = bitmap.getHeight();
		while (x > 500 && y > 500) {
			x = x / 2;
			y = y / 2;
		}
		
		System.out.println(x+" : "+y);
		bitmap = Bitmap.createScaledBitmap(bitmap, x, y, true);
		return bitmap;
	}
	private String getRealPathFromURI(Uri contentURI) {
		String result;
		Cursor cursor = ma.getContentResolver().query(contentURI, null, null,
				null, null);
		if (cursor == null) {
			result = contentURI.getPath();
		} else {
			cursor.moveToFirst();
			int idx = cursor
					.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
			result = cursor.getString(idx);
			cursor.close();
		}
		return result;
	}


}
