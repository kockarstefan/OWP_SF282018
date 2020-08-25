package model;

public class ProjectionTypes {

	private int id;
	private String type;
	

	public ProjectionTypes(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String name) {
		this.type = type;
	}
	
	
}
