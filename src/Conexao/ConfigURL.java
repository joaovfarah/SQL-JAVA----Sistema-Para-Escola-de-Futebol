package Conexao;

import javax.swing.JOptionPane;

public class ConfigURL {
	
	public String formaURL() {
		
		//valores das variáveis para conexão
		String DB_HOST = "";
		String DB_PORT = "";
		String DB_NAME = "";

		// Verificando se as variáveis foram definidas
		if (DB_HOST == null || DB_PORT == null || DB_NAME == null ||
	        DB_HOST.isBlank() || DB_PORT.isBlank() || DB_NAME.isBlank()) {
			JOptionPane.showMessageDialog(null, " Uma ou mais variáveis não foram definidas.");
		    throw new IllegalArgumentException("Uma ou mais variáveis para conexao com o banco de dados não foram definidas.");
		}

		// Construindo a URL de conexão
		return "jdbc:sqlserver://" + DB_HOST + ":" + DB_PORT + ";databaseName=" + DB_NAME + ";integratedSecurity=true;encrypt=false;";
	}
}
