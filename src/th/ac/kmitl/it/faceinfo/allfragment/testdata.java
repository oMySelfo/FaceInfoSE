package th.ac.kmitl.it.faceinfo.allfragment;

public class testdata {
	String path;
	String name;
	boolean group;
	public boolean isGroup() {
		return group;
	}
	public void setGroup(boolean group) {
		this.group = group;
	}
	public testdata(String path, String name) {
		super();
		this.path = path;
		this.name = name;
		group=false;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
