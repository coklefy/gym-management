package model;


public class Fisioterapista extends Persona{
	private Integer fisioterapistaID; 
	private String specializzazione;
	
	public Fisioterapista() {}
	
	public Integer getFisioterapistaID() {
		return this.fisioterapistaID;
	}
	
	public void setFisioterapistaID(Integer fisioterapistaID) {
		this.fisioterapistaID = fisioterapistaID;
	}
	
	public String getSpecializzazione() {
		return this.specializzazione;
	}
	
	public void setSpecializzazione(String specializzazione) {
		this.specializzazione =  specializzazione;
	}
	
	
}
