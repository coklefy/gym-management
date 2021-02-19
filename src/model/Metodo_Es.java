package model;

public class Metodo_Es {
		private String CF;
		private String metodo;
		private Integer codiceSettore;
		
		public Metodo_Es() {}
		
		public String getCF() {
			return this.CF;
		}
		
		public void setCF(String cf){
			this.CF = cf;
		}
		
		public String getMetodo() {
			return this.metodo;
		}
		
		public void setMetodo(String metodo) {
			this.metodo = metodo;
		}
		
		public Integer getCodiceSettore() {
			return this.codiceSettore;
		}
		
		public void setCodiceSettore(Integer codiceSettore) {
			this.codiceSettore = codiceSettore;
		}
}
