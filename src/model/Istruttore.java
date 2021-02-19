package model;

public class Istruttore extends Persona {
	private Integer istruttoreID; 
	private String specializzazione;
	
	public Istruttore() {}
	
	public Integer getIstruttoreID() {
		return this.istruttoreID;
	}
	
	public void setIstruttoreID(Integer istruttoreID) {
		this.istruttoreID = istruttoreID;
	}
	
	public String getSpecializzazione() {
		return this.specializzazione;
	}
	
	public void setSpecializzazione(String specializzazione) {
		this.specializzazione =  specializzazione;
	}
	
}
