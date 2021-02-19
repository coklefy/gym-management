package model;

public class EsGruppo {
	private Integer codiceEsercitazione;
	private String CFc;
	private Integer codiceGruppo;
	private Integer codiceSettore;
	
	public EsGruppo() {}
	
	public Integer getCodiceEsercitazione() {
		return this.codiceEsercitazione;
	}
	
	public void setCodiceEsercitazione(Integer codice) {
		 this.codiceEsercitazione = codice;
	}
	
	public String getCFc() {
		return this.CFc;
	}
	
	public void setCFc(String CF) {
		this.CFc = CF;
	}	
	
	public Integer getCodiceGruppo() {
		return this.codiceGruppo;
	}
	
	public void setCodiceGruppo(Integer codice) {
		this.codiceGruppo = codice;
	}
	
	public Integer getCodiceSettore() {
		return this.codiceSettore;
	}
	
	public void setCodiceSettore(Integer codice) {
		this.codiceSettore = codice;
	}
}
