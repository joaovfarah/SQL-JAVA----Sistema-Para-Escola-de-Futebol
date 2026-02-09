package Conexao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ClassesEscola.Aluno;

public class AlunoDAO extends Aluno {
	
	//Construtor
	public AlunoDAO(String nome, String celular, String email, String cpf, String endereco,  Date dataNasc,
			char sexo, int codAluno) {
		super(nome, celular, email, cpf, endereco, codAluno, dataNasc, sexo);
	}
	
	//Insertando dados no banco
	public void create() {
		//Criando instancia
		Conexao con = new Conexao();
		con.conectar();//Chamando metodo para conectar ao banco

		try {
			//Comando pra inserta no banco
			PreparedStatement pst = con.conexao.prepareStatement(
					"insert into aluno ( aluno, sexo, celular, email, cpf, dtNasc, endereco) "
							+ "values (?,?,?,?,?,?,?)");
			//Dados a serem colocados no banco
			pst.setString(1, getNome());
			pst.setString(2, String.valueOf(getSexo()));
			pst.setString(3, getCelular());
			pst.setString(4, getEmail());
			pst.setString(5, getCpf());
			pst.setDate(6, getDataNasc());
			pst.setString(7, getEndereco());

			pst.execute();
			con.desconectar();//Desconectando com o banco
			JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso", "Aviso", JOptionPane.WARNING_MESSAGE);
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + ex.getMessage());
		}
	}
	
	public static List<Aluno> listar(){
		//Conectando o banco
		Conexao con = new Conexao();
		con.conectar();
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			PreparedStatement pst = con.conexao.prepareStatement("SELECT * FROM aluno;");
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()){
				Aluno A = new Aluno();
				A.setCodAluno(resultSet.getInt("codAluno"));
				A.setNome(resultSet.getString("aluno"));
				A.setCelular(resultSet.getString("Celular"));
				A.setEmail(resultSet.getString("email"));
				A.setDataNasc(resultSet.getDate("dtnasc"));
				A.setEndereco(resultSet.getString("endereco"));
				A.setAtivo(resultSet.getString("ativo").charAt(0));
				A.setCpf(resultSet.getString("cpf"));
				alunos.add(A);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.desconectar();
		}
		
		return alunos;
	} 
	public static Aluno buscarAluno(String cpf) {
		List<Aluno> alunos = listar();
		for(Aluno a : alunos) {
			if(a.getCpf().equalsIgnoreCase(cpf)) {
				return a;
			}
		}
		return null ;
	}
	public static void atualizarStatusAluno(int idAluno, String novoStatus)  {
		Conexao con = new Conexao();
		con.conectar();
        
        try {
        	PreparedStatement pst = con.conexao.prepareStatement("UPDATE Aluno SET ativo = ? WHERE codaluno = ?");
            pst.setString(1, novoStatus);
            pst.setInt(2, idAluno);
            pst.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
                con.desconectar();
            
        }
    }
}
