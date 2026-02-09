package TelasDeCadastros;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import ClassesEscola.Aluno;
import ClassesEscola.Pessoa;
import Conexao.AlunoDAO;
import VerificarErro.ValidandoBusca;
import VerificarErro.VerificarVazio;

public class TelaAluno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelAluno;
	private final ButtonGroup buttonGroupSexo;
	JRadioButton rdbtnMasculino;
	JRadioButton rdbtnFeminino;
	PlaceholderTextField fieldNome;
	PlaceholderTextField fieldCelular;
	PlaceholderTextField fieldEmail;
	PlaceholderTextField fieldCpf;
	MaskFormatter dateFormatter = null;
	PlaceholderTextField fieldEndereco;
	private JFormattedTextField fieldDtNasc;
	private JButton btnBuscar;
	private JButton btnDesativar;
	private Aluno alunoSelect;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAluno frame = new TelaAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAluno() {
		setTitle("Cadastro Do Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 425);
		panelAluno = new JPanel();
		panelAluno.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelAluno);
		panelAluno.setLayout(null);
		
		JLabel labelCadastroAluno = new JLabel("Cadastro Aluno");
		labelCadastroAluno.setBounds(111, 11, 207, 26);
		labelCadastroAluno.setFont(new Font("Stencil", Font.PLAIN, 25));
		panelAluno.add(labelCadastroAluno);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNome.setBounds(33, 88, 46, 14);
		panelAluno.add(lblNome);

		fieldNome = new PlaceholderTextField("Digite seu nome");
		fieldNome.setBounds(121, 90, 254, 20);
		panelAluno.add(fieldNome);
		fieldNome.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSexo.setBounds(33, 116, 46, 14);
		panelAluno.add(lblSexo);
		
		buttonGroupSexo = new ButtonGroup();
		rdbtnMasculino = new JRadioButton("MASCULINO");
		buttonGroupSexo.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(111, 115, 115, 21);
		panelAluno.add(rdbtnMasculino);
		
		rdbtnFeminino = new JRadioButton("FEMININO");
		buttonGroupSexo.add(rdbtnFeminino);
		rdbtnFeminino.setBounds(222, 117, 90, 21);
		panelAluno.add(rdbtnFeminino);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCelular.setBounds(33, 146, 68, 14);
		panelAluno.add(lblCelular);	

		fieldCelular = new PlaceholderTextField("Digite seu telefone");
		fieldCelular.setColumns(10);
		fieldCelular.setBounds(121, 145, 254, 20);
		panelAluno.add(fieldCelular);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmail.setBounds(33, 177, 68, 14);
		panelAluno.add(lblEmail);

		fieldEmail = new PlaceholderTextField("Digite seu email");
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(121, 176, 254, 20);
		panelAluno.add(fieldEmail);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCpf.setBounds(33, 208, 68, 14);
		panelAluno.add(lblCpf);

		fieldCpf = new PlaceholderTextField("Digite seu cpf");
		fieldCpf.setColumns(10);
		fieldCpf.setBounds(121, 207, 254, 20);
		panelAluno.add(fieldCpf);
		
		JLabel lblDtNasc = new JLabel("Dt de nasc");
		lblDtNasc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDtNasc.setBounds(33, 226, 90, 35);
		panelAluno.add(lblDtNasc);
		
		//Criando variavel da date
		fieldDtNasc = new JFormattedTextField();
		fieldDtNasc.setBounds(121, 235, 68, 20);
		//definindo formato
		fieldDtNasc.setText("__/__/____");
		fieldDtNasc.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fieldDtNasc.getText().equals("__/__/____")) {
                    fieldDtNasc.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (fieldDtNasc.getText().isEmpty()) {
                    fieldDtNasc.setText("__/__/____");
                }
            }
        });
        fieldDtNasc.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkField();
            }

            private void checkField() {
                if (fieldDtNasc.getText().equals("__/__/____")) {
                    fieldDtNasc.setForeground(Color.GRAY);
                } else {
                    fieldDtNasc.setForeground(Color.BLACK);
                }
            }
        });
		panelAluno.add(fieldDtNasc);
		
		
		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEndereo.setBounds(33, 255, 90, 35);
		panelAluno.add(lblEndereo);
		
	    fieldEndereco  = new PlaceholderTextField("Digite seu endereco");
		fieldEndereco.setColumns(10);
		fieldEndereco.setBounds(121, 266, 254, 20);
		panelAluno.add(fieldEndereco);

		JButton btnAlunoSalvar = new JButton("Salvar");
		btnAlunoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validaAluno()) {
					salvarAluno();
					limparCampo();
				}
			}
		});
		btnAlunoSalvar.setBounds(56, 320, 102, 35);
		panelAluno.add(btnAlunoSalvar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValidandoBusca.validandoBuscar(fieldCpf.getText())) {
					buscaAluno();
					mostrarAluno();
				}
			}
		});
		btnBuscar.setBounds(174, 320, 102, 35);
		panelAluno.add(btnBuscar);
		
		btnDesativar = new JButton("Desativar");
		btnDesativar.setEnabled(false);
		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desativandoAluno();
				limparCampo();
				btnDesativar.setEnabled(false);
			}
		});
		btnDesativar.setBounds(286, 320, 102, 35);
		panelAluno.add(btnDesativar);
		
	}
	
	public void salvarAluno() {
		String nome = fieldNome.getText();
		String celular =  fieldCelular.getText();
		String email = fieldEmail.getText();
		String cpf = fieldCpf.getText();
		String endereco = fieldEndereco.getText();
		char sexo = validandoSexo();
		Date data = Aluno.validandoDate(fieldDtNasc.getText());
		AlunoDAO aluno = new AlunoDAO(nome, celular, email, cpf, endereco, data, sexo, 0);
		
	    aluno.create();	 
	} 
	
	public boolean validaAluno() {
		if(!VerificarVazio.verificarVazio(fieldNome.getText())) {
			JOptionPane.showMessageDialog(this, "Nome Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (validandoSexo() == '0') {
			JOptionPane.showMessageDialog(this, "Por favor, selecione o sexo.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!Pessoa.verificarTamanhoOnze(fieldCelular.getText())) {
			JOptionPane.showMessageDialog(this, "Telefone Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!Pessoa.verificarEmail(fieldEmail.getText())) {
			JOptionPane.showMessageDialog(this, "Email Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!Pessoa.verificarTamanhoOnze(fieldCpf.getText())) {
			JOptionPane.showMessageDialog(this, "CPF Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}else if(Aluno.validandoDate(fieldDtNasc.getText()) == null ) {
//			JOptionPane.showMessageDialog(this, "Data Inválida!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if(!VerificarVazio.verificarVazio(fieldEndereco.getText())) {
			JOptionPane.showMessageDialog(this, "Endereço Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public char validandoSexo(){
		if (rdbtnMasculino.isSelected()) {
	        return 'M';
	    } else if (rdbtnFeminino.isSelected()) {
	    	 return 'F';
	    } else {
	        return '0';
	    }
	}
	
	public void buscaAluno(){
		alunoSelect = AlunoDAO.buscarAluno(fieldCpf.getText());
	}
	public void mostrarAluno() {
		if(alunoSelect != null) {
			fieldNome.setText(alunoSelect.getNome());
			fieldCpf.setText(alunoSelect.getCpf());
			//data
			java.sql.Date dataNascimento = alunoSelect.getDataNasc();

			// Convertendo a data para uma string formatada
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dataFormatada = dateFormat.format(dataNascimento);
			// Definindo a data formatada no campo de texto
			fieldDtNasc.setText(dataFormatada);
			
			fieldCelular.setText(alunoSelect.getCelular());
			fieldEmail.setText(alunoSelect.getEmail());
			fieldEndereco.setText(alunoSelect.getEndereco());
			btnDesativar.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(this, "Aluno não encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void desativandoAluno() {
		AlunoDAO.atualizarStatusAluno(alunoSelect.getCodAluno(), "N" );
		JOptionPane.showMessageDialog(this, "Aluno desativado", "Aviso", JOptionPane.WARNING_MESSAGE);
	}
	
	void limparCampo() {
		fieldNome.setText("");
		fieldCpf.setText("");
		fieldDtNasc.setText("");
		fieldCelular.setText("");
		fieldEmail.setText("");
		fieldEndereco.setText("");
		buttonGroupSexo.clearSelection();
	}
}
