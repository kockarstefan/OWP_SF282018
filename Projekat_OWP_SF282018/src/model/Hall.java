package model;

import java.util.List;

public class Hall {

	private int id;
	private String name;
	private int numOfProjections;
	
	
	public Hall(int id, String name, int numOfProjections) {
		super();
		this.id = id;
		this.name = name;
		this.numOfProjections = numOfProjections;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumOfProjections() {
		return numOfProjections;
	}
	public void setNumOfProjections(int numOfProjections) {
		this.numOfProjections = numOfProjections;
	}
	
	
	
	
	
}
