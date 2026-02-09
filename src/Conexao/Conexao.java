package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	ConfigURL configURL = new ConfigURL();
	
	private String url =  configURL.formaURL();
	
	public Connection conexao;
	
	// Conexão com o banco
	public void conectar() {
		try {
			conexao  = DriverManager.getConnection(url);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	//Desconectando do banco
	public void desconectar() {
		try {
			conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível desconectar do banco");
		}
	}
}