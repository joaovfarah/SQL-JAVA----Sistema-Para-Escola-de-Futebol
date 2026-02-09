package ClassesEscola;

public class Data {
		//Atributos
		private int codData;
		private String diasSemana;
		
		//Constructor
		public Data(int codData, String diasSemana) {
			super();
			this.codData = codData;
			this.diasSemana = diasSemana;
		}
		
		public Data() {
			super();
		}
		
		//get e set 
		public int getCodData() {
			return codData;
		}

		public void setCodData(int codData) {
			this.codData = codData;
		}

		public String getDiasSemana() {
			return diasSemana;
		}

		public void setDiasSemana(String diasSemana) {
			this.diasSemana = diasSemana;
		}

		//toString
		@Override
		public String toString() {
			return "Data [codData=" + codData + ", diasSemana=" + diasSemana + "]";
		}
}
