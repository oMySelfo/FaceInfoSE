package th.ac.kmitl.it.faceinfo.main;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.faceinfo.allfragment.testdata;



public class Model {
	static MainActivity ma;
	static List<testdata> listperson = new ArrayList<testdata>();
	

	


	public static List<testdata> getListperson() {
		return listperson;
	}


	public static void setListperson(List<testdata> list) {
		listperson = list;
	}


	public static void setActivity(MainActivity main){
		ma = main;
	}
	
	
	public static MainActivity getActivity(){
		return ma;
	}
	
}
