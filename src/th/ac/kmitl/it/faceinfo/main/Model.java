package th.ac.kmitl.it.faceinfo.main;



public class Model {
	static MainActivity ma;
	
	public static void setActivity(MainActivity main){
		ma = main;
	}
	
	
	public static MainActivity getActivity(){
		return ma;
	}
	
}
