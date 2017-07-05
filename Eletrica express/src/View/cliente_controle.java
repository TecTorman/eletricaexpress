package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.itextpdf.text.pdf.TextField;

import Controler.control_cliente;
import Model.clientes;
import connect.conexao;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class cliente_controle extends JFrame {

	private JPanel contentPane;
	private static JTextField textFieldNome, textFieldCid, textFieldBairro, textFieldMail, textFieldAnotacao,
			textFieldCep, textFieldTelefone, textFieldCpf, textFieldRg;
	private static JTextArea textFieldEnd;
	static JComboBox comboBox = new JComboBox();
	static String nomeSelecionado;
	static int codigoCli;
	static JButton btnAtualizar = new JButton("");
	static JComboBox comboBoxEstado;

	control_cliente cli_control = new control_cliente();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					comboBox.addItem("");
					cliente_controle frame = new cliente_controle();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public cliente_controle() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(cliente_controle.class.getResource("/img/electric-power-plug-icon-31.png")));
		seleciona_cliente();
		setTitle("EE - El\u00E9trica Express");
		setResizable(false);
		setBounds(100, 100, 568, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 540, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSelecioneOCliente = new JLabel("Selecione o cliente");
		lblSelecioneOCliente.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblSelecioneOCliente.setBounds(23, 109, 141, 14);
		panel.add(lblSelecioneOCliente);

		btnAtualizar.setIcon(new ImageIcon(cliente_controle.class.getResource("/img/update.png")));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				// pegar nome da pesquisa de usuario que estiver selecionado
				nomeSelecionado = (String) comboBox.getSelectedItem();
				// copiar valores para o formulario
				recebe_cliente();
				if (comboBox.getSelectedItem() != "")
					btnAtualizar.setEnabled(true);
				if (comboBox.getSelectedItem() == "") {
					btnAtualizar.setEnabled(false);
					limpar_campos();
				}
			}
		});
		comboBox.setBounds(23, 130, 227, 20);
		panel.add(comboBox);

		JLabel lblNewLabel = new JLabel("Nome do cliente");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel.setBounds(50, 181, 123, 14);
		panel.add(lblNewLabel);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(50, 198, 201, 20);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblTelefone.setBounds(270, 181, 123, 14);
		panel.add(lblTelefone);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblEndereo.setBounds(50, 236, 125, 14);
		panel.add(lblEndereo);

		textFieldCid = new JTextField();
		textFieldCid.setColumns(10);
		textFieldCid.setBounds(269, 253, 100, 20);
		panel.add(textFieldCid);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblCidade.setBounds(269, 236, 49, 14);
		panel.add(lblCidade);

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "MG" }));
		comboBoxEstado.setBounds(394, 198, 96, 20);
		panel.add(comboBoxEstado);

		textFieldBairro = new JTextField();
		textFieldBairro.setColumns(10);
		textFieldBairro.setBounds(394, 252, 96, 20);
		panel.add(textFieldBairro);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblBairro.setBounds(394, 236, 38, 14);
		panel.add(lblBairro);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblCpf.setBounds(50, 331, 89, 14);
		panel.add(lblCpf);

		JLabel lblRg = new JLabel("RG");
		lblRg.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblRg.setBounds(166, 331, 89, 14);
		panel.add(lblRg);

		JLabel lblemail = new JLabel("E-Mail");
		lblemail.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblemail.setBounds(301, 332, 89, 14);
		panel.add(lblemail);

		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(301, 348, 189, 20);
		panel.add(textFieldMail);

		textFieldAnotacao = new JTextField();
		textFieldAnotacao.setColumns(10);
		textFieldAnotacao.setBounds(50, 398, 440, 66);
		panel.add(textFieldAnotacao);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblCep.setBounds(270, 284, 38, 14);
		panel.add(lblCep);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(cliente_controle.class.getResource("/img/perosn.png")));
		label.setBounds(389, 11, 141, 142);
		panel.add(label);

		JLabel lblDadosCadastrais = new JLabel("Dados cadastrais");
		lblDadosCadastrais.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDadosCadastrais.setBounds(158, 43, 248, 50);
		panel.add(lblDadosCadastrais);

		JLabel lblAnotaes = new JLabel("Anota\u00E7\u00F5es");
		lblAnotaes.setBounds(50, 379, 89, 14);
		panel.add(lblAnotaes);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblEstado.setBounds(394, 181, 49, 14);
		panel.add(lblEstado);

		MaskFormatter Mascaracep = new MaskFormatter("#####.###");
		Mascaracep.setPlaceholderCharacter('_');

		textFieldCep = new JFormattedTextField(Mascaracep);
		textFieldCep.setBounds(270, 300, 100, 20);
		panel.add(textFieldCep);

		MaskFormatter Mascaracpf = new MaskFormatter("###.###.###-##");
		Mascaracpf.setPlaceholderCharacter('_');

		textFieldCpf = new JFormattedTextField(Mascaracpf);
		textFieldCpf.setBounds(50, 348, 106, 20);
		panel.add(textFieldCpf);

		MaskFormatter mf1 = new MaskFormatter("(###)-#####-####");
		mf1.setPlaceholderCharacter('_');

		textFieldTelefone = new JFormattedTextField(mf1);
		textFieldTelefone.setBounds(268, 198, 106, 20);
		panel.add(textFieldTelefone);

		MaskFormatter mascaraRg = new MaskFormatter("##.###.###");
		mascaraRg.setPlaceholderCharacter('_');

		textFieldRg = new JFormattedTextField(mascaraRg);
		textFieldRg.setBounds(176, 348, 105, 20);
		panel.add(textFieldRg);

		JButton btnGravar = new JButton("");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				clientes cli = new clientes();
				String nome = textFieldNome.getText();
				cli.setNome(nome);

				String telefone = textFieldTelefone.getText();
				cli.setTelefone(telefone.replaceAll("[^0123456789]", ""));

				String endereco = textFieldEnd.getText();
				cli.setEndereço(endereco);

				String cidade = textFieldCid.getText();
				cli.setCidade(cidade);

				String estado = (String) comboBoxEstado.getSelectedItem();
				cli.setEstado(estado);

				String bairro = textFieldBairro.getText();
				cli.setBairro(bairro);

				String cpf = textFieldCpf.getText();
				cpf = cpf.replaceAll("[^0123456789]", "");
				cli.setCpf(cpf);

				String cep = textFieldCep.getText();
				cep = cep.replaceAll("[^0123456789]", "");
				cli.setCep(cep);

				String rg = textFieldRg.getText();
				rg = rg.replaceAll("[^0123456789]", "");
				cli.setRg(rg);

				String mail = textFieldMail.getText();
				cli.setEmail(mail);

				String anotacoes = textFieldAnotacao.getText();
				System.out.println("");
				System.out.println(anotacoes);
				cli.setanotacoes(anotacoes);

				// pegar objetos tratados
				nome = cli.getNome();
				telefone = cli.getTelefone();
				endereco = cli.getEndereço();
				cidade = cli.getCidade();
				estado = cli.getEstado();
				cep = cli.getCep();
				bairro = cli.getBairro();
				cpf = cli.getCpf();
				rg = cli.getRg();
				cli_control.InsereClientes(nome, telefone, endereco, cidade, estado, cep, bairro, cpf, rg, mail,
						anotacoes);
				// limpas os campos e carrega o dropdown novamente
				comboBox.removeAllItems();
				comboBox.addItem("");
				seleciona_cliente();
				inicial.seleciona_cliente();
			}
		});
		btnGravar.setIcon(new ImageIcon(cliente_controle.class.getResource("/img/add.png")));
		btnGravar.setBounds(10, 480, 60, 60);
		panel.add(btnGravar);

		JButton btnNovo = new JButton("");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar_campos();
			}
		});
		btnNovo.setIcon(new ImageIcon(cliente_controle.class.getResource("/img/refesh.png")));
		btnNovo.setBounds(73, 480, 60, 60);
		panel.add(btnNovo);

		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexao conexao = new conexao();
					Connection ExConn = (Connection) conexao.abrirBDConn();
					Statement stmt;
					stmt = (Statement) ExConn.createStatement();
					String sSQL = "DELETE FROM clientes WHERE codigo='" + codigoCli + "';";
					// realiza a exclusao dos dados
					int res = stmt.executeUpdate(sSQL);
					limpar_campos();
					conexao.fecharBDConn();
				} catch (Exception s) {
					limpar_campos();
				}
				comboBox.removeAllItems();
				comboBox.addItem("");
				seleciona_cliente();
				inicial.seleciona_cliente();
			}
		});
		btnExcluir.setIcon(new ImageIcon(cliente_controle.class.getResource("/img/trash.png")));
		btnExcluir.setBounds(138, 480, 60, 60);
		panel.add(btnExcluir);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 160, 538, 2);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 475, 538, 2);
		panel.add(separator_1);

		textFieldEnd = new JTextArea();
		textFieldEnd.setWrapStyleWord(true);
		textFieldEnd.setRows(4);
		textFieldEnd.setTabSize(0);
		Border border = BorderFactory.createLineBorder(Color.black);
		textFieldEnd.setBorder(border);
		textFieldEnd.setLineWrap(true);
		textFieldEnd.setBounds(50, 253, 201, 64);
		panel.add(textFieldEnd);

		btnAtualizar.setEnabled(false);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientes cli = new clientes();
				String nome = textFieldNome.getText();
				cli.setNome(nome);

				String telefone = textFieldTelefone.getText();
				cli.setTelefone(telefone.replaceAll("[^0123456789]", ""));

				String endereco = textFieldEnd.getText();
				cli.setEndereço(endereco);

				String cidade = textFieldCid.getText();
				cli.setCidade(cidade);

				String estado = "teste";// combobox estado
				cli.setEstado(estado);

				String bairro = textFieldBairro.getText();
				cli.setBairro(bairro);

				String cpf = textFieldCpf.getText();
				cpf = cpf.replaceAll("[^0123456789]", "");
				cli.setCpf(cpf);

				String cep = textFieldCep.getText();
				cep = cep.replaceAll("[^0123456789]", "");
				cli.setCep(cep);

				String rg = textFieldRg.getText();
				rg = rg.replaceAll("[^0123456789]", "");
				cli.setRg(rg);

				String mail = textFieldMail.getText();
				cli.setEmail(mail);
				String anotacoes = textFieldAnotacao.getText();
				cli.setanotacoes(anotacoes);
				// pegar objetos tratados
				nome = cli.getNome();
				telefone = cli.getTelefone();
				endereco = cli.getEndereço();
				cidade = cli.getCidade();
				estado = cli.getEstado();
				cep = cli.getCep();
				bairro = cli.getBairro();
				cpf = cli.getCpf();
				rg = cli.getRg();
				anotacoes = cli.getanotacoes();
				cli_control.UpdateClientes(codigoCli, nome, telefone, endereco, cidade, estado, cep, bairro, cpf, rg,
						mail, anotacoes);
				// limpas os campos e carrega o dropdown novament
				comboBox.removeAllItems();
				comboBox.addItem("");
				seleciona_cliente();
				btnAtualizar.setEnabled(false);
				inicial.seleciona_cliente();

			}
		});
		btnAtualizar.setBounds(202, 480, 60, 60);
		panel.add(btnAtualizar);
	}

	public static void seleciona_cliente() {
		try {
			comboBox.removeAllItems();
			comboBox.addItem("");
			// executa essa funcao para carrehar os valores e adicionar no
			// combobox
			conexao conexao = new conexao();
			Connection ExConn = (Connection) conexao.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			stmt.setMaxRows(100);
			String query = "SELECT * FROM clientes";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.print(rs);
				comboBox.addItem(rs.getString("Nome"));
			}
			ExConn.close();

		} catch (Exception s) {
			// comboBox_3.removeAllItems();
			s.printStackTrace();
		}
	}

	public static void recebe_cliente() {
		try {
			conexao produtos = new conexao();
			Connection ExConn = (Connection) produtos.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			stmt.setMaxRows(100);
			String query = "SELECT * FROM clientes where nome='" + nomeSelecionado + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// pega os campos do bd e joga na tela
				System.out.print(rs);
				codigoCli = rs.getInt("Codigo");
				textFieldNome.setText(rs.getString("Nome"));
				textFieldTelefone.setText(rs.getString("Telefone"));
				textFieldEnd.setText(rs.getString("Endereco"));
				textFieldCid.setText(rs.getString("Cidade"));
				textFieldCep.setText(rs.getString("Cep"));
				textFieldBairro.setText(rs.getString("Bairro"));
				textFieldCpf.setText(rs.getString("Cpf"));
				textFieldRg.setText(rs.getString("Rg"));
				textFieldMail.setText(rs.getString("Email"));
				textFieldAnotacao.setText(rs.getString("Anotacoes"));
			}
			ExConn.close();
		} catch (Exception s) {
			// comboBox_3.removeAllItems();
			s.printStackTrace();
		}

	}

	public static void limpar_campos() {
		comboBox.setSelectedItem(null);
		btnAtualizar.setEnabled(false);
		textFieldNome.setText(null);
		textFieldTelefone.setText(null);
		textFieldEnd.setText(null);
		textFieldCid.setText(null);
		textFieldCep.setText(null);
		textFieldBairro.setText(null);
		textFieldCpf.setText(null);
		textFieldRg.setText(null);
		textFieldMail.setText(null);
		textFieldAnotacao.setText(null);
	}
}
