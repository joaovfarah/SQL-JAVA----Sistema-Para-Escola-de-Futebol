package Conexao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ClassesEscola.Matricula;

public class MatriculaDAO extends Matricula {
	
	//Construtor
	
	public MatriculaDAO(int codMatricula, float mensalidade, Date dtMatric, int hablidade, int codTurma, int codAluno) {
		super(codMatricula, mensalidade, dtMatric, hablidade, codTurma, codAluno);
		// TODO Auto-generated constructor stub
	}
	
	//Insertando dados no banco
	public void create() {
		//Criando instancia
		Conexao con = new Conexao();
		con.conectar();//Chamando metodo para conectar ao banco

		try {
			//Comando pra inserta no banco
			PreparedStatement pst = con.conexao.prepareStatement(
					"insert into matricula ( mensalidade, dtmatric, habilidade, codTurma, codAluno) "
							+ "values (?,?,?,?,?)");
			//Dados a serem colocados no banco
			pst.setFloat(1, getMensalidade());
			pst.setDate(2, getDtMatric());
			pst.setInt(3, getHablidade());
			pst.setInt(4, getCodTurma());
			pst.setInt(5, getCodAluno());

			pst.execute();
			con.desconectar();//Desconectando com o banco
			JOptionPane.showMessageDialog(null, "Matricula executada com sucesso", "Aviso", JOptionPane.WARNING_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + ex.getMessage());
		}
	}


}