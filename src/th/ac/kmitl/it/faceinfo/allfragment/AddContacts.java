package th.ac.kmitl.it.faceinfo.allfragment;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.adapter.CoverFlowAdapter;
import th.ac.kmitl.it.faceinfo.main.Data;

import th.ac.kmitl.it.faceinfo.main.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
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
	private boolean onsettext;
	private List<Integer> EdittextId;
	private EditText edittext;
	private View rootView;
	private List<Integer> images;

	public AddContacts(int mode) {
		super();
		this.mode = mode;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.addcontacts, container, false);
		EdittextId = new ArrayList<Integer>();
		EdittextId.add(R.id.addcontact_name);
		EdittextId.add(R.id.addcontact_phone);
		EdittextId.add(R.id.addcontact_fullname);
		EdittextId.add(R.id.addcontact_facebook);
		EdittextId.add(R.id.addcontact_address);
		EdittextId.add(R.id.addcontact_mail);
		EdittextId.add(R.id.addcontact_birthday);
		EdittextId.add(R.id.addcontact_detail);
		images = new ArrayList<Integer>();
		images.add(R.drawable.tae);
		images.add(R.drawable.add_image);
		data = Data.getData();
		CoverFlowAdapter adapter = new CoverFlowAdapter();
		
		adapter.setImages(images);

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
		fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
		eventfancyCoverFlowClick();

		return rootView;
	}

	private void eventfancyCoverFlowClick() {
		// Short Click (Add Picture)
		registerForContextMenu(fancyCoverFlow);
		fancyCoverFlow.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> view, View container,
					int position, long id) {
				if (position == images.size() - 1) {
					view.showContextMenu();
					System.out.println("Okkkkkkkkkkk");
				}
			}
		});

		deletebutton = (ImageButton) rootView
				.findViewById(R.id.addcontact_deletebutton);
		editbutton = (ImageButton) rootView
				.findViewById(R.id.addcontact_editbutton);
		if (mode == PAGE_ADDCONTACT) {
			onsettext = true;
			editbutton.setImageResource(R.drawable.save);
		} else {
			onsettext = false;
			for (int i = 0; i < EdittextId.size(); i++) {
				edittext = (EditText) rootView.findViewById(EdittextId.get(i));
				edittext.setEnabled(onsettext);
			}
		}
		editbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode == PAGE_ADDCONTACT) {
					// insert to DB
					data.getMainActivity().displayView(0);

				} else if (onsettext) {
					onsettext = false;
					for (int i = 0; i < EdittextId.size(); i++) {
						edittext = (EditText) rootView.findViewById(EdittextId
								.get(i));
						edittext.setEnabled(onsettext);
					}
					// update to database;
				} else {
					onsettext = true;
					for (int i = 0; i < EdittextId.size(); i++) {
						edittext = (EditText) rootView.findViewById(EdittextId
								.get(i));
						edittext.setEnabled(onsettext);
					}
				}

			}
		});

		

		// Long Click (Delete Picture)
		fancyCoverFlow
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> view,
							View container, int position, long id) {
						if (position != images.size() - 1) {
							alertDiaLog();
						}
						System.out.println("FlowCurPos " + position);
						return true;
					}
				});

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
			alertDiaLog();
		} else if (item.getTitle() == "Gallery") {
			System.out.println("Gallery");
		} else {
			return false;
		}
		return true;

	}

	private void alertDiaLog() {
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
								// delete pic
							}
						});

		AlertDialog alert = builder.create();
		alert.show();
	}
}
