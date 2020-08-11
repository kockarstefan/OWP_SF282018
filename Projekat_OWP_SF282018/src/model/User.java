package model;

import java.sql.Date;
import java.time.LocalDate;

public class User {

	public enum Role{USER,ADMIN}
	
	private String username;
	private String password;
	private LocalDate registrationDate;
	private Role role;
	private boolean active;
	private boolean loggedIn;
	
	public User() {}

	public User(String username, String password, LocalDate registrationDate, Role role, boolean active, boolean loggedIn) {
		super();
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		this.active = active;
		this.loggedIn = loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
	
	
	
	
	
	
	
	
}
