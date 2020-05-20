package model;

public class Movie {
	
	private int ID;
	private String title;
	private String director;
	private String genre;
	private int duration;
	private String distributor;
	private String country;
	private int releaseDate;
	private boolean active;
	
	public Movie() {}
	
	public Movie(int iD, String title, String director, String genre, int duration, String distributor, String country,
			int releaseDate, boolean active) {
		super();
		ID = iD;
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.duration = duration;
		this.distributor = distributor;
		this.country = country;
		this.releaseDate = releaseDate;
		this.active = active;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if(duration > 0)
			this.duration = duration;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		if(releaseDate > 0)
			this.releaseDate = releaseDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
}
