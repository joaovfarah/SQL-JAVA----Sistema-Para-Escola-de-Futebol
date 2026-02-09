package Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ClassesEscola.Data;
import ClassesEscola.Turma;

public class TurmaDAO extends Turma {

	// Constructor
	public TurmaDAO(int codTurma, int codTreinador, String nomeTurma, int codDtTreinamento) {
		super(codTurma, codTreinador, nomeTurma, codDtTreinamento);
	}

	// Insertando dados no banco
	public void create() {
		// Criando instancia
		Conexao con = new Conexao();
		con.conectar();// Chamando metodo para conectar ao banco

		try {
			// Comando pra inserta no banco
			PreparedStatement pst = con.conexao
					.prepareStatement("insert into turma (codtreinador, codData, nomeTurma)" + "values (?,?,?)");
			// Dados a serem colocados no banco
			pst.setInt(1, getCodTreinador());
			pst.setInt(2, getCodDtTreinamento());
			pst.setString(3, getNomeTurma());
			pst.execute();
			con.desconectar();// Desconectando com o banco

			JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso", "Aviso", JOptionPane.WARNING_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + ex.getMessage());
		}
	}

	public static List<Turma> listar() {
		// Conectando o banco
		Conexao con = new Conexao();
		con.conectar();

		List<Turma> turma = new ArrayList<Turma>();
		try {
			PreparedStatement pst = con.conexao.prepareStatement("SELECT nomeTurma, codTurma, codData FROM Turma;");
			ResultSet resultSet = pst.executeQuery();
			while (resultSet.next()) {
				Turma t = new Turma();
				t.setNomeTurma(resultSet.getString("nomeTurma"));
				t.setCodTurma(resultSet.getInt("CodTurma"));
				t.setCodDtTreinamento(resultSet.getInt("coddata"));
				turma.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}

		return turma;
	}

	public static List<Data> listarData() {
		// Conectando o banco
		Conexao con = new Conexao();
		con.conectar();

		List<Data> data = new ArrayList<Data>();
		try {
			PreparedStatement pst = con.conexao.prepareStatement("SELECT codData, diadasemana FROM data;");
			ResultSet resultSet = pst.executeQuery();
			while (resultSet.next()) {
				Data d = new Data();
				d.setDiasSemana(resultSet.getString("diaDaSemana"));
				d.setCodData(resultSet.getInt("codData"));
				data.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}

		return data;
	}
}
