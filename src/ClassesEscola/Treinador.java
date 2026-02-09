package ClassesEscola;

public class Treinador extends Pessoa{
	//Atributos
	private int codTreinador;
	private char ativo;
	

	public Treinador(String nome, String celular, String email, String cpf, String endereco) {
		super(nome, celular, email, cpf, endereco);
		// TODO Auto-generated constructor stub
	}

	public Treinador() {
		super();
	}

	//Getters and Setters
	public int getCodTreinador() {
		return codTreinador;
	}

	public void setCodTreinador(int codTreinador) {
		this.codTreinador = codTreinador;
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
		return "Treinador [codTreinador=" + codTreinador + "]";
	}
	
	
}
