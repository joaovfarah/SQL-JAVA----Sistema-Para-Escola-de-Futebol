package ClassesEscola;

public class Turma {
	//Atributos
	private int codTurma;
	private int codTreinador;
	private String nomeTurma;
	private int codDtTreinamento;

	//Comstructor

	public Turma(int codTurma, int codTreinador, String nomeTurma, int codDtTreinamento) {
		super();
		this.codTurma = codTurma;
		this.codTreinador = codTreinador;
		this.nomeTurma = nomeTurma;
		this.codDtTreinamento = codDtTreinamento;
	}

	public Turma() {
		super();
	}

	//Getters and Setters
	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public int getCodTreinador() {
		return codTreinador;
	}

	public void setCodTreinador(int codTreinador) {
		this.codTreinador = codTreinador;
	}

	public int getCodDtTreinamento() {
		return codDtTreinamento;
	}

	public void setCodDtTreinamento(int codDtTreinamento) {
		this.codDtTreinamento = codDtTreinamento;
	}

	public int getCodTurma() {
		return codTurma;
	}

	public void setCodTurma(int codTurma) {
		this.codTurma = codTurma;
	}

	//toString
	@Override
	public String toString() {
		return "Turma [codTurma=" + codTurma + ", codTreinador=" + codTreinador + ", nomeTurma=" + nomeTurma
				+ ", codDtTreinamento=" + codDtTreinamento + "]";
	}
	
}
