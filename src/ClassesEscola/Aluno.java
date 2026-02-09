package ClassesEscola;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Aluno extends Pessoa{

	//Atributos
	private int codAluno;
	private Date dataNasc;
	private char sexo;
	private char ativo;
	
	//Constructor
	public Aluno() {
		super();
	}

	public Aluno(String nome, String celular, String email, String cpf, String endereco, int codAluno, Date dataNasc,
			char sexo) {
		super(nome, celular, email, cpf, endereco);
		this.codAluno = codAluno;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
	}

	//Getters and Setters

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public int getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(int codAluno) {
		this.codAluno = codAluno;
	}
	
	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	//ToString
	@Override
	public String toString() {
		return "Aluno [codAluno=" + codAluno + ", dataNasc=" + dataNasc + ", sexo=" + sexo + "]";
	}
	
	public static java.sql.Date validandoDate(String dateString) {
		if (dateString.equalsIgnoreCase("__/__/____")) {
			JOptionPane.showMessageDialog(null, "Data vazia.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return null;
		} else {
			// Atributo sql
			java.sql.Date dataSql = null;

			// Formato de data
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);

			try {
				// Analisa a string da data e converta para Date
				java.util.Date dataUtil = dateFormat.parse(dateString);

				// Obtenha o tempo em milissegundos e use-o para construir um java.sql.Date
				dataSql = new java.sql.Date(dataUtil.getTime());
			} catch (ParseException ex) {
				JOptionPane.showMessageDialog(null, "Formato inválido!\nInsira um formato Válido __/__/____", "Aviso", JOptionPane.WARNING_MESSAGE);
				return null;
			}
			return dataSql;
		}
	}
}
