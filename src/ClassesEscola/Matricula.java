package ClassesEscola;

import java.sql.Date;

public class Matricula {
	//Atributos
	private int codMatricula;
	private float mensalidade;
	private Date dtMatric;
	private int hablidade;
	private int codTurma;
	private int codAluno;
	
	//Construtores
	public Matricula(int codMatricula, float mensalidade, Date dtMatric, int hablidade, int codTurma, int codAluno) {
		super();
		this.codMatricula = codMatricula;
		this.mensalidade = mensalidade; 
		this.dtMatric = dtMatric;
		this.hablidade = hablidade;
		this.codTurma = codTurma;
		this.codAluno = codAluno;
	}

	//Getters and Setters 
	public int getCodMatricula() {
		return codMatricula;
	}

	public void setCodMatricula(int codMatricula) {
		this.codMatricula = codMatricula;
	}

	public float getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(float mensalidade) {
		this.mensalidade = mensalidade;
	}

	public Date getDtMatric() {
		return dtMatric;
	}

	public void setDtMatric(Date dtMatric) {
		this.dtMatric = dtMatric;
	}

	public int getHablidade() {
		return hablidade;
	}

	public void setHablidade(int hablidade) {
		this.hablidade = hablidade;
	}

	public int getCodTurma() {
		return codTurma;
	}

	public void setCodTurma(int codTurma) {
		this.codTurma = codTurma;
	}

	public int getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(int codAluno) {
		this.codAluno = codAluno;
	}

	//ToString
	@Override
	public String toString() {
		return "Matricula [codMatricula=" + codMatricula + ", mensalidade=" + mensalidade + ", dtMatric=" + dtMatric
				+ ", hablidade=" + hablidade + ", codTurma=" + codTurma + ", codAluno=" + codAluno + "]";
	}
}
