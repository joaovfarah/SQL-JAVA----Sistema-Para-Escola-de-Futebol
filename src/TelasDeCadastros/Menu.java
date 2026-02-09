package TelasDeCadastros;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu {

	private JFrame tela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.tela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		tela = new JFrame();
		tela.getContentPane().setForeground(new Color(0, 0, 0));
		tela.setTitle("Menu de Cadastro");
		tela.setBounds(100, 100, 515, 243);
//		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.getContentPane().setLayout(null);

		JLabel lblTelaDeCadastro = new JLabel("  Menu");
		lblTelaDeCadastro.setForeground(new Color(0, 0, 0));
		lblTelaDeCadastro.setFont(new Font("Arial", Font.PLAIN, 35));
		lblTelaDeCadastro.setBounds(183, 25, 114, 51);
		tela.getContentPane().add(lblTelaDeCadastro);

		JButton btnAluno = new JButton("ALUNO");
		btnAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaAluno telaAluno = new TelaAluno();
					telaAluno.setVisible(true);
					telaAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAluno.setBounds(10, 106, 114, 36);
		tela.getContentPane().add(btnAluno);

		JButton btnTreinador = new JButton("TREINADOR");
		btnTreinador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaTreinador telaTreinador = new TelaTreinador();
					telaTreinador.setVisible(true);
					telaTreinador.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		}

		);
		btnTreinador.setBounds(250, 106, 115, 36);
		tela.getContentPane().add(btnTreinador);

		JButton btnTurma = new JButton("TURMA");
		btnTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaTurma telaTurma = new TelaTurma();
					telaTurma.setVisible(true);
					telaTurma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnTurma.setBounds(134, 106, 106, 36);
		tela.getContentPane().add(btnTurma);
		
		JButton btnMatricula = new JButton("MATRICULA");
		btnMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaMatricula telaMatricula = new TelaMatricula();
					telaMatricula.setVisible(true);
					telaMatricula.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnMatricula.setBounds(375, 106, 115, 36);
		tela.getContentPane().add(btnMatricula);
	}
}
