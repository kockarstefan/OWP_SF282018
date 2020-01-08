package model;

public class Film {

	protected int ID;
	protected String naziv;
	protected String reziser;
	protected String trajanjeFilma;
	protected String distributer;
	protected String zemljaPorekla;
	protected int godinaProizvodnje;
	

	
	public Film(int ID, String naziv, String reziser, String trajanjeFilma, String distributer, String zemljaPorekla,
			int godinaProizvodnje) {
		super();
		this.ID = ID;
		this.naziv = naziv;
		this.reziser = reziser;
		this.trajanjeFilma = trajanjeFilma;
		this.distributer = distributer;
		this.zemljaPorekla = zemljaPorekla;
		this.godinaProizvodnje = godinaProizvodnje;
	}
	
	public Film() {
		this.ID = 0;
		this.naziv = "";
		this.reziser = "";
		this.trajanjeFilma = "";
		this.distributer = "";
		this.zemljaPorekla = "";
		this.godinaProizvodnje = 0;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getReziser() {
		return reziser;
	}
	public void setReziser(String reziser) {
		this.reziser = reziser;
	}
	public String getTrajanjeFilma() {
		return trajanjeFilma;
	}
	public void setTrajanjeFilma(String trajanjeFilma) {
		this.trajanjeFilma = trajanjeFilma;
	}
	public String getDistributer() {
		return distributer;
	}
	public void setDistributer(String distributer) {
		this.distributer = distributer;
	}
	public String getZemljaPorekla() {
		return zemljaPorekla;
	}
	public void setZemljaPorekla(String zemljaPorekla) {
		this.zemljaPorekla = zemljaPorekla;
	}
	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}
	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}
	
	
	
}
