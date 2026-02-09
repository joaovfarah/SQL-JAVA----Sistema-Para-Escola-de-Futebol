package ClassesEscola;

public  abstract class Pessoa {
	// Atributos
	private String nome;
	private String celular;
	private String email;
	private String cpf;
	private String endereco;

	// Construtores
	public Pessoa(String nome, String celular, String email, String cpf, String endereco) {
		super();
		this.nome = nome;
		this.celular = celular;
		this.email = email;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public Pessoa() {
		super();
	}

	// Getters and Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	// ToString
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", celular=" + celular + ", email=" + email + ", cpf=" + cpf + ", endereco="
				+ endereco + "]";
	}
	
	public static boolean verificarTamanhoOnze(String elemento) {
		if(elemento.isBlank()) {
			return false;
		}else if(elemento.length() != 11) {
			return false;
		}
		return true;
	}
	

	public static boolean verificarEmail(String email) {
		if(email.isBlank()) {
			return false;
		}else if(!email.contains("@")) {
			return false;
		}
		return true;
	}
	
}

