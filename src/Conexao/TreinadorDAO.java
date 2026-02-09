package Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ClassesEscola.Treinador;

public class TreinadorDAO extends Treinador {
	
	

	// Construtor
	public TreinadorDAO(String nome, String telefone, String email, String cpf, String endereco) {
		super(nome, telefone, email, cpf, endereco);
	}

	// Insertando dados no banco
	public void create() {
		// Criando instancia
		Conexao con = new Conexao();
		con.conectar();// Chamando metodo para conectar ao banco

		try {
			// Comando pra inserta no banco
			PreparedStatement pst = con.conexao.prepareStatement(
					"insert into treinador ( treinador, celular, email, cpf, endereco) " + "values (?,?,?,?,?)");
			// Dados a serem colocados no banco
			pst.setString(1, getNome());
			pst.setString(2, getCelular());
			pst.setString(3, getEmail());
			pst.setString(4, getCpf());
			pst.setString(5, getEndereco());

			pst.execute();
			con.desconectar();// Desconectando com o banco
			JOptionPane.showMessageDialog(null, "Treinador cadastrado com sucesso", "Aviso", JOptionPane.WARNING_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + ex.getMessage());
		}
	}
	
	public static List<Treinador> listar(){
		//Conectando o banco
		Conexao con = new Conexao();
		con.conectar();
		
		List<Treinador> treinadores = new ArrayList<Treinador>();
		try {
			PreparedStatement pst = con.conexao.prepareStatement("SELECT * FROM treinador;");
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()){
				Treinador t = new Treinador();
				t.setCodTreinador(resultSet.getInt("codtreinador"));
				t.setNome(resultSet.getString("treinador"));
				t.setCelular(resultSet.getString("celular"));
				t.setEmail(resultSet.getString("email"));
				t.setCpf(resultSet.getString("cpf"));
				t.setEndereco(resultSet.getString("endereco"));
				t.setAtivo(resultSet.getString("ativo").charAt(0));
				treinadores.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.desconectar();
		}
		
		return treinadores;
	}
	
	public static Treinador buscarTreinador(String cpf) {
		List<Treinador> treinador = listar();
		for(Treinador t : treinador) {
			if(t.getCpf().equalsIgnoreCase(cpf)) {
				return t;
			}
		}
		return null ;
	}
	
	public static void atualizarStatusTreinador(int codTreinador, String novoStatus)  {
		Conexao con = new Conexao();
		con.conectar();
        
        try {
        	PreparedStatement pst = con.conexao.prepareStatement("UPDATE Treinador SET ativo = ? WHERE codTreinador = ?");
            pst.setString(1, novoStatus);
            pst.setInt(2, codTreinador);
            pst.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
                con.desconectar();
            
        }
    }
}
