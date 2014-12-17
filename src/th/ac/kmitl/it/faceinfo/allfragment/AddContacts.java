package th.ac.kmitl.it.faceinfo.allfragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import th.ac.kmitl.it.faceinfo.adapter.CoverFlowAdapter;
import th.ac.kmitl.it.faceinfo.database.Contact;
import th.ac.kmitl.it.faceinfo.database.DatabaseManager;
import th.ac.kmitl.it.faceinfo.database.Photo;
import th.ac.kmitl.it.faceinfo.faceplusplus.FacePlusPlus;
import th.ac.kmitl.it.faceinfo.main.Data;
import th.ac.kmitl.it.faceinfo.main.MainActivity;

import th.ac.kmitl.it.faceinfo.main.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;

public class AddContacts extends Fragment {
	public AddContacts() {
	}

	private FancyCoverFlow fancyCoverFlow;
	private ImageButton editbutton;
	private ImageButton deletebutton;
	private int mode;
	private int PAGE_PROFILE = 2;
	private int PAGE_ADDCONTACT = 1;
	private Data data;
	private DatabaseManager dbm;
	private FacePlusPlus fpp;

	private List<Integer> EdittextId;
	private EditText edittext;
	private View rootView;

	private List<Bitmap> showBitmapList;
	private List<Photo> listPhoto;
	private List<Photo> listPhotosDeleted;
	private boolean isEditEnable;
	private CoverFlowAdapter adapter;
	private Contact contact;
	private Bitmap bitmap;
	private Uri uri;
	private String path;
	private int REQUEST_GALLERY = 1;
	private int REQUEST_CAMERA = 2;
	private MainActivity ma;
	private boolean isOnClick;
	private LayoutInflater layout;
	private int TEXT_BIRTHDAY=6;
	private EditText birth;

	public AddContacts(int mode) {
		super();
		this.mode = mode;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.addcontacts, container, false);
		layout= inflater;
		
		

		deletebutton = (ImageButton) rootView
				.findViewById(R.id.addcontact_deletebutton);
		editbutton = (ImageButton) rootView
				.findViewById(R.id.addcontact_editbutton);

		showBitmapList = new ArrayList<Bitmap>();
		showBitmapList.add(BitmapFactory.decodeResource(
				rootView.getResources(), R.drawable.add_image));

		data = Data.getData();
		ma = Data.getData().getMainActivity();
		isEditEnable = true;

		adapter = new CoverFlowAdapter();
		adapter.setImages(showBitmapList);
		setEditTextId();
		setFancyCoverFlow();
		
		eventfancyCoverFlowClick();
		birth = (EditText) rootView.findViewById(EdittextId.get(TEXT_BIRTHDAY));


		data = Data.getData();
		dbm = data.getDmb();
		fpp = data.getFacePP();
		contact = new Contact();
		listPhoto = new ArrayList<Photo>();
		listPhotosDeleted = new ArrayList<Photo>();

		if (mode == PAGE_ADDCONTACT) {
			editbutton.setImageResource(R.drawable.save);
		} else if (mode == PAGE_PROFILE) {
			isEditEnable = false;
			eventfancyCoverFlowClick();
			contact = dbm.getContact(data.getTempKey()); // //// dummy no data
															// now
			for (int i = 0; i < EdittextId.size(); i++) {
				edittext = (EditText) rootView.findViewById(EdittextId.get(i));
				edittext.setEnabled(isEditEnable);
				birth.setEnabled(isEditEnable);
				edittext.setText(contact.getContactProfile(i));
			}

			listPhoto = dbm.getPhotoList(contact.getCon_id());
			for (Photo photo : listPhoto) {
				addBitmapToShowList(photo.getBitmap());
			}

		}

		editbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mode == PAGE_ADDCONTACT) {
					for (int i = 0; i < EdittextId.size(); i++) {
						edittext = (EditText) rootView.findViewById(EdittextId
								.get(i));
						contact.serContactProfile(i, edittext.getText()
								.toString());
					}
					fpp.createContact();
					try {
						String con_id = fpp.RESULT.getString("person_id");
						contact.setCon_id(con_id);
						dbm.insertContact(contact);
						for (Photo photo : listPhoto) {
							fpp.addFace(contact.getCon_id(),
									photo.getPhoto_id());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					data.getMainActivity().displayView(0);
				} else if (mode == PAGE_PROFILE) {
					isEditEnable = !isEditEnable;
					eventfancyCoverFlowClick();
					for (int i = 0; i < EdittextId.size(); i++) {

						edittext = (EditText) rootView.findViewById(EdittextId.get(i));
						if(i!=TEXT_BIRTHDAY){

						edittext.setEnabled(isEditEnable);
						}
						ImageButton calendar_button =(ImageButton) rootView.findViewById(R.id.addcontact_birthday_button);
						calendar_button.setEnabled(isEditEnable);
						if (isEditEnable == false) {
							contact.serContactProfile(i, edittext.getText()
									.toString());
						}
					}
					for (Photo photo : listPhoto) {
						if (photo.isNewPhoto()) {
							fpp.addFace(contact.getCon_id(),
									photo.getPhoto_id());
							dbm.insertPhoto(photo);
						}
					}
					for (Photo photo : listPhotosDeleted) {
						fpp.deleteFace(contact.getCon_id(), photo.getPhoto_id());
						dbm.deletePhoto(photo.getPhoto_id());
					}
					dbm.updateContact(contact);
				}
			}
		});
		ImageButton calendarbutton =(ImageButton) rootView.findViewById(R.id.addcontact_birthday_button);
		calendarbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calendarDiaLog(layout);
				
			}
		});
		

		return rootView;
	}

	private void eventfancyCoverFlowClick() {
		if (isEditEnable) {
			// Short Click (Add Picture)
			registerForContextMenu(fancyCoverFlow);
			fancyCoverFlow.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> view, View container,
						int position, long id) {
					isOnClick = true;
					if (position == showBitmapList.size() - 1) {

						container.showContextMenu();
					}
					isOnClick = false;

				}
			});
			// Long Click (Delete Picture)
			fancyCoverFlow
					.setOnItemLongClickListener(new OnItemLongClickListener() {
						@Override
						public boolean onItemLongClick(AdapterView<?> view,
								View container, int position, long id) {
							if (isOnClick)
								return false;
							if (position != showBitmapList.size() - 1) {
								alertDiaLog(position);
								return true;
							}
							return false;
						}
					});
		}

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// menu.setHeaderTitle("Select The Action");
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
	
	

	private void calendarDiaLog(LayoutInflater inflater) {
		String birthday;
		AlertDialog.Builder builder = new AlertDialog.Builder(Data.getData()
				.getMainActivity());
		View v = inflater.inflate(R.layout.calendar_fragment,null,false);
		
		final DatePicker date = (DatePicker) v.findViewById(R.id.datePicker1);
		if (mode != PAGE_ADDCONTACT){
			date.updateDate(1994, 1, 11);
		}
		
		builder.setTitle("BirthDay")
				.setIcon(getResources().getDrawable(R.drawable.ic_launcher))
				.setView(v)
				.setPositiveButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						})
				.setNegativeButton("OK",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								birth = (EditText) rootView.findViewById(EdittextId.get(TEXT_BIRTHDAY));
								birth.setText(date.getYear()+"-"+date.getMonth()+"-"+date.getDayOfMonth());
							}
						});

		AlertDialog alert = builder.create();
		alert.show();
	}


	private void alertDiaLog(final int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Data.getData()
				.getMainActivity());
		builder.setTitle("Delete")
				.setIcon(getResources().getDrawable(R.drawable.ic_launcher))
				.setMessage("Do you want to delete this picture?")
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
								Photo photo = listPhoto.get(position);
								listPhoto.remove(position);
								showBitmapList.remove(position);
								adapter.setImages(showBitmapList);
								fancyCoverFlow.setAdapter(adapter);
								if (!photo.isNewPhoto()) {
									listPhotosDeleted.add(photo);
								}

							}
						});

		AlertDialog alert = builder.create();
		alert.show();
	}

	private void setEditTextId() {
		EdittextId = new ArrayList<Integer>();
		EdittextId.add(R.id.addcontact_name);
		EdittextId.add(R.id.addcontact_phone);
		EdittextId.add(R.id.addcontact_fullname);
		EdittextId.add(R.id.addcontact_facebook);
		EdittextId.add(R.id.addcontact_address);
		EdittextId.add(R.id.addcontact_mail);
		EdittextId.add(R.id.addcontact_birthday);
		EdittextId.add(R.id.addcontact_detail);
	}

	private void setFancyCoverFlow() {
		fancyCoverFlow = (FancyCoverFlow) rootView
				.findViewById(R.id.fancyCoverFlow);
		fancyCoverFlow.setAdapter(adapter);
		fancyCoverFlow.setUnselectedAlpha(1.0f);
		fancyCoverFlow.setUnselectedSaturation(-1.0f);
		fancyCoverFlow.setUnselectedScale(0.5f);
		fancyCoverFlow.setSpacing(10);
		fancyCoverFlow.setMaxRotation(0);
		fancyCoverFlow.setScaleDownGravity(0.2f);
		fancyCoverFlow.setScaleX(1.6f);
		fancyCoverFlow.setScaleY(1.6f);
		fancyCoverFlow.setBackgroundResource(R.drawable.tab_show);
		fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (requestCode == REQUEST_CAMERA && resultCode == ma.RESULT_OK) {
				ma.getContentResolver().notifyChange(uri, null);
				ContentResolver cr = ma.getContentResolver();

				bitmap = Media.getBitmap(cr, uri);
				path = uri.getPath();

			} else if (requestCode == REQUEST_GALLERY
					&& resultCode == ma.RESULT_OK) {

				bitmap = Media.getBitmap(ma.getContentResolver(), uri);

			}

			bitmap = getCropImage(bitmap);
			path = getCropPath(bitmap); // <----- croped image
			String photo_id = fpp.RESULT.getJSONArray("face").getJSONObject(0)
					.getString("face_id");
			Photo photo = new Photo();
			photo.setPhoto_path(path);
			photo.setPhoto_id(photo_id);
			photo.setNewPhoto(true);
			listPhoto.add(photo);
			addBitmapToShowList(photo.getBitmap());

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	private Bitmap getCropImage(Bitmap bitmap) {
		double center_x;
		double center_y;
		double height;
		double width;
		try {
			JSONObject result = fpp.RESULT;

			center_x = result.getJSONArray("face").getJSONObject(0)
					.getJSONObject("position").getJSONObject("center")
					.getDouble("x");
			center_y = result.getJSONArray("face").getJSONObject(0)
					.getJSONObject("position").getJSONObject("center")
					.getDouble("y");
			height = result.getJSONArray("face").getJSONObject(0)
					.getJSONObject("position").getDouble("height");
			width = result.getJSONArray("face").getJSONObject(0)
					.getJSONObject("position").getDouble("width");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Bitmap cropBitmap = bitmap;

		return cropBitmap;
	}

	private String getCropPath(Bitmap cropImage) {
		String cropPath = cropImage.toString();

		// String imgeUrl = MediaStore.Images.Media.insertImage(
		// ma.getContentResolver(), bitmap, "kla", "jay");
		// Uri uri = Uri.parse(imgeUrl);
		// File imageFile = new File(getRealPathFromURI(uri));
		// path = imageFile.getPath();

		return cropPath;

	}

	public void addBitmapToShowList(Bitmap bitmap) {
		showBitmapList.add(showBitmapList.size() - 1, bitmap);
		adapter.setImages(showBitmapList);
		fancyCoverFlow.setAdapter(adapter);

	}

}
