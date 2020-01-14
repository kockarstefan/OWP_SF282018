package model;

import java.sql.Date;

public class User {

	public enum Role{Korisnik,Administrator}
	
	protected int id;
	protected String korisnickoIme;
	protected String lozinka;
	protected String datumRegistacije;
	protected Role role;
	
	public User(int id, String korisnickoIme, String lozinka, String datumRegistacije, Role role) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRegistacije = datumRegistacije;
		this.role = role;
	}

	

	public User() {
		super();
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}


	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}


	public String getDatumRegistacije() {
		return datumRegistacije;
	}



	public void setDatumRegistacije(String datumRegistacije) {
		this.datumRegistacije = datumRegistacije;
	}



	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	
	
	
	
	
	
	
}
