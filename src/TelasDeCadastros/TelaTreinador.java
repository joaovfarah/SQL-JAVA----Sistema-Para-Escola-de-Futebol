package TelasDeCadastros;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClassesEscola.Pessoa;
import ClassesEscola.Treinador;
import Conexao.TreinadorDAO;
import VerificarErro.ValidandoBusca;
import VerificarErro.VerificarVazio;

public class TelaTreinador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelTreiandor;
	PlaceholderTextField fieldEndereco;
	PlaceholderTextField fieldCpf;
	PlaceholderTextField fieldEmail;
	PlaceholderTextField fieldCelular;
	PlaceholderTextField fieldNome;
	JButton btnDesativar;
	Treinador treinadorSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTreinador frame = new TelaTreinador();
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
	public TelaTreinador() {
		setTitle("Cadastro Do Treinador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 349);
		panelTreiandor = new JPanel();
		panelTreiandor.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelTreiandor);
		panelTreiandor.setLayout(null);

		JLabel labelCadastroTreinador = new JLabel("Cadastro Treinador");
		labelCadastroTreinador.setFont(new Font("Stencil", Font.PLAIN, 25));
		labelCadastroTreinador.setBounds(114, 11, 267, 26);
		panelTreiandor.add(labelCadastroTreinador);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNome.setBounds(35, 62, 46, 14);
		panelTreiandor.add(lblNome);

		fieldNome = new PlaceholderTextField("Digite seu nome");
		fieldNome.setColumns(10);
		fieldNome.setBounds(114, 61, 254, 20);
		panelTreiandor.add(fieldNome);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCelular.setBounds(35, 99, 68, 14);
		panelTreiandor.add(lblCelular);

		fieldCelular = new PlaceholderTextField("Digite seu celular");
		fieldCelular.setColumns(10);
		fieldCelular.setBounds(113, 98, 254, 20);
		panelTreiandor.add(fieldCelular);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmail.setBounds(35, 135, 68, 14);
		panelTreiandor.add(lblEmail);

		fieldEmail = new PlaceholderTextField("Digite seu email");
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(113, 134, 254, 20);
		panelTreiandor.add(fieldEmail);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCpf.setBounds(35, 171, 68, 14);
		panelTreiandor.add(lblCpf);

		fieldCpf = new PlaceholderTextField("Digite seu cpf");
		fieldCpf.setColumns(10);
		fieldCpf.setBounds(114, 171, 254, 20);
		panelTreiandor.add(fieldCpf);

		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEndereo.setBounds(35, 193, 90, 35);
		panelTreiandor.add(lblEndereo);

		fieldEndereco = new PlaceholderTextField("Digite seu endereco");
		fieldEndereco.setColumns(10);
		fieldEndereco.setBounds(114, 202, 254, 20);
		panelTreiandor.add(fieldEndereco);

		JButton btnTreinadorSalvar = new JButton("Salvar");
		btnTreinadorSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaTreinador()) {
					salvarTreinador();
					limparCampos();
				}
			}
		});
		btnTreinadorSalvar.setBounds(59, 259, 102, 35);
		panelTreiandor.add(btnTreinadorSalvar);

		JButton btnBuscar = new JButton("buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValidandoBusca.validandoBuscar(fieldCpf.getText())){
					BuscandoTreinador();
					mostrarTreinador();
				}
			}
		});
		btnBuscar.setBounds(191, 259, 102, 35);
		panelTreiandor.add(btnBuscar);

		btnDesativar = new JButton("Desativar");
		btnDesativar.setEnabled(false);
		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desativandoTreinador();
				limparCampos();
				btnDesativar.setEnabled(false);
			}
		});
		btnDesativar.setBounds(324, 259, 102, 35);
		panelTreiandor.add(btnDesativar);
	}

	public void salvarTreinador() {
		String nome = fieldNome.getText();
		String telefone = fieldCelular.getText();
		String email = fieldEmail.getText();
		String cpf = fieldCpf.getText();
		String endereco = fieldEndereco.getText();

		TreinadorDAO treinador = new TreinadorDAO(nome, telefone, email, cpf, endereco);

		treinador.create();
	}

	public boolean validaTreinador() {
		if (!VerificarVazio.verificarVazio(fieldNome.getText())) {
			JOptionPane.showMessageDialog(this, "Nome Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!Pessoa.verificarTamanhoOnze(fieldCelular.getText())) {
			JOptionPane.showMessageDialog(this, "Telefone Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!VerificarVazio.verificarVazio(fieldCelular.getText())) {
			JOptionPane.showMessageDialog(this, "Telefone Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!Pessoa.verificarEmail(fieldEmail.getText())) {
			JOptionPane.showMessageDialog(this, "E-Mail Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!Pessoa.verificarTamanhoOnze(fieldCpf.getText())) {
			JOptionPane.showMessageDialog(this, "CPF Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!VerificarVazio.verificarVazio(fieldEndereco.getText())) {
			JOptionPane.showMessageDialog(this, "Endereço Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void BuscandoTreinador() {
		treinadorSelect = TreinadorDAO.buscarTreinador(fieldCpf.getText());
	}

	public void mostrarTreinador() {
		if (treinadorSelect != null) {
			Treinador t = TreinadorDAO.buscarTreinador(fieldCpf.getText());
			fieldNome.setText(t.getNome());
			fieldCpf.setText(t.getCpf());
			fieldCelular.setText(t.getCelular());
			fieldEmail.setText(t.getEmail());
			fieldEndereco.setText(t	.getEndereco());
			btnDesativar.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(this, "Treinador não encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void desativandoTreinador() {
		TreinadorDAO.atualizarStatusTreinador(treinadorSelect.getCodTreinador(), "N" );
		JOptionPane.showMessageDialog(this, "Treinador desativado", "Aviso", JOptionPane.WARNING_MESSAGE);
	}
	
	public void limparCampos() {
		fieldNome.setText("");
		fieldCpf.setText("");
		fieldCelular.setText("");
		fieldEmail.setText("");
		fieldEndereco.setText("");
	}
}
