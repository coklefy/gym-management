package model;

import java.sql.Date;

public class Controllo_Macchinario {
	 private Integer codiceControllo;
	 private Integer codiceFabbricazione;
	 private String esito;
	 private Date data;
	 
	 public Controllo_Macchinario() {}
	 
	 public Integer getCodiceControllo() {
		 return this.codiceControllo;
	 }
	 
	 public void setCodiceControllo(Integer codiceC) {
		 this.codiceControllo = codiceC;
	 }
	 
	 public Integer getCodiceFabbricazione() {
		 return this.codiceFabbricazione;
	 }
	 
	 public void setCodiceFabbricazione(Integer codiceF){
		 this.codiceFabbricazione = codiceF;
	 }
	 
	 public String getEsito() {
		 return this.esito;
	 }
	 
	 public void setEsito(String esito) {
		 this.esito = esito;
	 }
	 
	 public Date getData() {
		 return this.data;
	 }
	 
	 public void setData(Date data) {
		 this.data = data;
	 }
	 
}
