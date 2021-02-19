package model;

public class Macchinario {
	private Integer codiceMac;
	private String tipo;
	private String nome;
	private String grado;
	private Integer annoFirst;
	private Integer codiceSet;

	public Macchinario() {}
	
	public Integer getCodiceMac() {
		return this.codiceMac;
	}
	
	public void setCodiceMac(Integer codiceMac) {
		this.codiceMac = codiceMac;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getGrado() {
		return this.grado;
	}
	
	public void setGrado(String grado) {
		this.grado = grado;
	}
	
	public Integer getAnno() {
		return this.codiceSet;
	}
	
	public void setAnno(Integer anno) {
		this.annoFirst = anno;
	}
	
	
	public Integer getCodiceSet() {
		return this.codiceSet;
	}
	
	public void setCodiceSet(Integer codiceSet) {
		this.codiceSet = codiceSet;
	}
	
	
	
}


