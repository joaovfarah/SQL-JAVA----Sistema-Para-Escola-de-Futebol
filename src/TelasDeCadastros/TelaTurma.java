
package TelasDeCadastros;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClassesEscola.Data;
import ClassesEscola.Treinador;
import Conexao.TreinadorDAO;
import Conexao.TurmaDAO;
import VerificarErro.VerificarVazio;

public class TelaTurma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<Data> data = TurmaDAO.listarData();
	private JComboBox<String> comboBoxTreinador;
	private JComboBox<String> comboBoxDiasSemans;
	PlaceholderTextField fieldNome;
	List<Treinador> treinadores = TreinadorDAO.listar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTurma frame = new TelaTurma();
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
	public TelaTurma() {
		
		setTitle("Cadastro Turma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelCadastroTurma = new JLabel("Cadastro Turma");
		labelCadastroTurma.setFont(new Font("Stencil", Font.PLAIN, 25));
		labelCadastroTurma.setBounds(82, 11, 234, 26);
		contentPane.add(labelCadastroTurma);
		
		JLabel lblCodigoTreinador = new JLabel("Treinador");
		lblCodigoTreinador.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCodigoTreinador.setBounds(16, 108, 82, 20);
		contentPane.add(lblCodigoTreinador);
		
	     
		comboBoxTreinador = new JComboBox<String>();
		//Opção Padrão
		comboBoxTreinador.addItem("Selecione o item");
		// Adicionar os nomes dos treinadores ao JComboBox
        for (Treinador t : treinadores) {
        	if (t.getAtivo() == 'S') {
        		comboBoxTreinador.addItem(t.getNome());
			}	
        } 
		comboBoxTreinador.setBounds(113, 109, 115, 22);
		contentPane.add(comboBoxTreinador);
		
		JLabel lblCodigoDiasSemans = new JLabel("Dias");
		lblCodigoDiasSemans.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCodigoDiasSemans.setBounds(16, 139, 82, 26);
		contentPane.add(lblCodigoDiasSemans);
		
		comboBoxDiasSemans = new JComboBox<String>();
		// Opção Padrão
		comboBoxDiasSemans.addItem("Selecione o item");
		// Adicionar os nomes dos treinadores ao JComboBox
		for (Data d : data) {
			comboBoxDiasSemans.addItem(d.getDiasSemana());
		}
		comboBoxDiasSemans.setBounds(113, 140, 115, 22);
		contentPane.add(comboBoxDiasSemans);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNome.setBounds(16, 53, 62, 20);
		contentPane.add(lblNome);
		
		fieldNome = new PlaceholderTextField("Digite o nome da turma");
		fieldNome.setColumns(10);
		fieldNome.setBounds(82, 55, 254, 20);
		contentPane.add(fieldNome);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validandoTurma()) {
					salvarTurma();
					limpaCampo();
				}
			}
		});
		btnSalvar.setBounds(137, 220, 102, 35);
		contentPane.add(btnSalvar);
	}
	
	public void salvarTurma(){
		String nomeTurma = fieldNome.getText();
		int codTreinador = pegandoCodTreinador();
		int codDiasSemana = pegandoCodDiasTreinamento();
		TurmaDAO turma = new TurmaDAO(0, codTreinador, nomeTurma, codDiasSemana);
		turma.create();
	}
	
	public boolean validandoTurma() {
		if(!VerificarVazio.verificarVazio(fieldNome.getText())) {
			JOptionPane.showMessageDialog(this, "Nome da Turma Inválido!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}else if(pegandoCodTreinador() == 0){
			JOptionPane.showMessageDialog(this, "Selecione um Treinador.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}else if(pegandoCodDiasTreinamento() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione os dias da semana!.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public int pegandoCodTreinador() {
		
		if (comboBoxTreinador.getSelectedIndex() > 0 ) {

			// Obtendo o índice do item selecionado
			int selectedIndex = comboBoxTreinador.getSelectedIndex();

			// Obtendo o objeto Treinador correspondente ao item selecionado
			Treinador treinadorSelecionado = treinadores.get(selectedIndex - 1);

			// Obtendo o código do treinador selecionado
			int codTreinador = treinadorSelecionado.getCodTreinador();
			
			return codTreinador;
		}else {
			return 0;
		}
	}
	
	public int pegandoCodDiasTreinamento() {
		if (comboBoxDiasSemans.getSelectedIndex() > 0 ) {

			// Obtendo o índice do item selecionado
			int selectedIndex = comboBoxDiasSemans.getSelectedIndex();
			
			Data dataSelecionada = data.get(selectedIndex - 1);
			
			int codData = dataSelecionada.getCodData();
			
			return codData;
		}else {
			return 0;
		}
	}
	
	public void limpaCampo() {
		fieldNome.setText("");
		comboBoxTreinador.setSelectedIndex(0);
		comboBoxDiasSemans.setSelectedIndex(0);	
	}
	
	
}