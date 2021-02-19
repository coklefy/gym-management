package model;

public class Persona {
	private String  CF;
	private String  nome;
	private String  cognome;
	private String  via;
	private String  città;
	private Integer nrCivico;
	private Integer CAP;
	
	public Persona() {}
	
	public String getCF() {
		return this.CF;
	}
	
	public void setCF(String CF) {
		this.CF = CF;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getVia() {
		return this.via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public String getCittà() {
		return this.città;
	}
	
	public void setCittà(String città) {
		this.città = città;
	}
	
	public Integer getNrCivico() {
		return this.nrCivico;
	}
	
	public void setNrCivico(Integer nrCivico) {
		this.nrCivico = nrCivico;
	}
	
	public Integer getCAP() {
		return this.CAP;
	}
	
	public void setCAP(Integer CAP) {
		this.CAP = CAP;
	}

}
