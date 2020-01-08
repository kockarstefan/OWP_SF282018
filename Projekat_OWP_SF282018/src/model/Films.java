package model;

public class Films {

	protected String naziv;
	protected String trajanjeFilma;
	protected String distributer;
	protected String zemljaPorekla;
	protected int godinaProizvodnje;
	
	
	
	public Films(String naziv, String trajanjeFilma, String distributer, String zemljaPorekla, int godinaProizvodnje) {
		super();
		this.naziv = naziv;
		this.trajanjeFilma = trajanjeFilma;
		this.distributer = distributer;
		this.zemljaPorekla = zemljaPorekla;
		this.godinaProizvodnje = godinaProizvodnje;
	}
	
	public Films() {
		this.naziv = "";
		this.trajanjeFilma = "";
		this.distributer = "";
		this.zemljaPorekla = "";
		this.godinaProizvodnje = 0;
		
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
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
