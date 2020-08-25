package model;

import java.time.LocalDateTime;

public class Ticket {
	
	private int id;
	private Projections projection;
	private Seat seat;
	private Hall hall;
	private LocalDateTime dateSold;
	private User user;
	private boolean active;
	
	public Ticket(int id, Projections projection, Seat seat, Hall hall, LocalDateTime dateSold, User user,
			boolean active) {
		super();
		this.id = id;
		this.projection = projection;
		this.seat = seat;
		this.hall = hall;
		this.dateSold = dateSold;
		this.user = user;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Projections getProjection() {
		return projection;
	}

	public void setProjection(Projections projection) {
		this.projection = projection;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public LocalDateTime getDateSold() {
		return dateSold;
	}

	public void setDateSold(LocalDateTime dateSold) {
		this.dateSold = dateSold;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
