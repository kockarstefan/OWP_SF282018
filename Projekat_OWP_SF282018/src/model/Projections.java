package model;

import java.time.LocalDateTime;

public class Projections {

	private int id;
	private Movie movie;
	private ProjectionTypes projectionType;
	private Hall hall;
	private LocalDateTime date;
	private double price;
	private User admin;
	private boolean active;
	
	public Projections(int id, Movie movie, ProjectionTypes projectionType, Hall hall, LocalDateTime date, double price,
			User admin, boolean active) {
		super();
		this.id = id;
		this.movie = movie;
		this.projectionType = projectionType;
		this.hall = hall;
		this.date = date;
		this.price = price;
		this.admin = admin;
		this.active = active;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public ProjectionTypes getProjectionType() {
		return projectionType;
	}
	public void setProjectionType(ProjectionTypes projectionType) {
		this.projectionType = projectionType;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
