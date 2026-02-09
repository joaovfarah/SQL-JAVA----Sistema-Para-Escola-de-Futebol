package VerificarErro;

import javax.swing.JOptionPane;

import ClassesEscola.Pessoa;

public class ValidandoBusca {
	
	public static boolean validandoBuscar(String cpf) {
		if(!Pessoa.verificarTamanhoOnze(cpf)){
			JOptionPane.showMessageDialog(null, "Para fazer uma busca digite um cpf valido", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
}
