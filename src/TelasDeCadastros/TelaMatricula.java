package TelasDeCadastros;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClassesEscola.Aluno;
import ClassesEscola.Turma;
import Conexao.AlunoDAO;
import Conexao.MatriculaDAO;
import Conexao.TurmaDAO;

public class TelaMatricula extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<Aluno> alunos = AlunoDAO.listar(); // lista de Alunos
	List<Turma> turma = TurmaDAO.listar();
	private JComboBox<String> comboBoxAluno;
	private JComboBox<String> comboBoxTurma;
	private JComboBox<String> comboBoxHabilidade;
	private int mensalidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMatricula frame = new TelaMatricula();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaMatricula() {

		setTitle("Cadastro Turma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelCadastroMatricula = new JLabel("Cadastro Matricula");
		labelCadastroMatricula.setFont(new Font("Stencil", Font.PLAIN, 25));
		labelCadastroMatricula.setBounds(65, 11, 271, 26);
		contentPane.add(labelCadastroMatricula);

		JLabel lblCodigoAluno = new JLabel("Aluno");
		lblCodigoAluno.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCodigoAluno.setBounds(16, 48, 82, 20);
		contentPane.add(lblCodigoAluno);

		comboBoxAluno = new JComboBox<String>();
		// Opção Padrão
		comboBoxAluno.addItem("Selecione o item");
		// Adicionar os nomes dos treinadores ao JComboBox
		for (Aluno A : alunos) {
			if (A.getAtivo() == 'S') {
				comboBoxAluno.addItem(A.getNome());
			}
		}
		comboBoxAluno.setBounds(108, 49, 141, 22);
		contentPane.add(comboBoxAluno);

		JLabel lblCodigoTurma = new JLabel("Turma");
		lblCodigoTurma.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCodigoTurma.setBounds(16, 81, 51, 20);
		contentPane.add(lblCodigoTurma);

		comboBoxTurma = new JComboBox<String>();
		// Opção Padrão
		comboBoxTurma.addItem("Selecione o item");
		// Adicionar os nomes dos treinadores ao JComboBox
		for (Turma t : turma) {
			comboBoxTurma.addItem(t.getNomeTurma());
		}
		comboBoxTurma.setBounds(108, 82, 141, 22);
		contentPane.add(comboBoxTurma);

		JButton btnGerarMatricula = new JButton("Gerar Matricula");
		btnGerarMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaMatricula()) {
					salvarMatricula();
					limpaCampo();
				}

			}
		});
		btnGerarMatricula.setBounds(134, 190, 115, 35);
		contentPane.add(btnGerarMatricula);

		JLabel lblCodigoHabilidade = new JLabel("Habilidade");
		lblCodigoHabilidade.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCodigoHabilidade.setBounds(16, 112, 82, 20);
		contentPane.add(lblCodigoHabilidade);

		comboBoxHabilidade = new JComboBox<>();
		comboBoxHabilidade.setModel(new DefaultComboBoxModel<>(new String[] {"Selecione o item", "1", "2", "3", "4", "5" }));
		comboBoxHabilidade.setBounds(108, 115, 141, 22);
		contentPane.add(comboBoxHabilidade);
	}

	public void salvarMatricula() {
		int codAluno = pegandoCodAluno();
		int codTurma = pegandoCodTurma();
		int codHabilidade = pegandoCodHabilidade();
		LocalDate dataAtual = LocalDate.now();
		Date sqlDate = Date.valueOf(dataAtual);

		MatriculaDAO matricula = new MatriculaDAO(0, mensalidade, sqlDate, codHabilidade, codTurma, codAluno);
		matricula.create();
	}

	public boolean validaMatricula() {
		if (pegandoCodAluno() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione um Aluno!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (pegandoCodTurma() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione uma Turma!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (pegandoCodHabilidade() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione o nivel de habilidade!", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public int pegandoCodAluno() {
		if (comboBoxAluno.getSelectedIndex() > 0) {
			// Obtendo o índice do item selecionado
			int selectedIndex = comboBoxAluno.getSelectedIndex();

			// Obtendo o objeto Aluno correspondente ao item selecionado
			Aluno alunoSelecionado = alunos.get(selectedIndex - 1);

			// Obtendo o código do aluno selecionado
			int codAluno = alunoSelecionado.getCodAluno();

			return codAluno;
		} else {
			return 0;
		}
	}

	public int pegandoCodTurma() {
		if (comboBoxTurma.getSelectedIndex() > 0) {

			// Obtendo o índice do item selecionado
			int selectedIndex = comboBoxTurma.getSelectedIndex();

			// Obtendo o objeto Treinador correspondente ao item selecionado
			Turma turmaSelecionada = turma.get(selectedIndex - 1);

			if (turmaSelecionada.getCodDtTreinamento() == 4) {
				mensalidade = 120;
			} else if (turmaSelecionada.getCodDtTreinamento() == 5) {
				mensalidade = 80;
			}

			// Obtendo o código do treinador selecionado
			int codTurmas = turmaSelecionada.getCodTurma();

			return codTurmas;
		} else {
			return 0;
		}
	}

	public int pegandoCodHabilidade() {
		if (comboBoxHabilidade.getSelectedIndex() > 0) {

			// Obtendo o índice do item selecionado
			int selectedIndex = comboBoxHabilidade.getSelectedIndex();

			return selectedIndex;
		} else {
			return 0;
		}
	}

	public void limpaCampo() {
		comboBoxAluno.setSelectedIndex(0);
		comboBoxHabilidade.setSelectedIndex(0);
		comboBoxTurma.setSelectedIndex(0);
	}
}
