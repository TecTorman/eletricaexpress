package frame;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

import javax.swing.JTable;
import connect.conexao;
import control.control_produto;
import net.proteanit.sql.DbUtils;
import object.produto;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class produtos extends JFrame {

	private JPanel contentPane;
	private static JTextField textFieldNome;
	private static JTextField textFieldUrl;
	private static JTextField textFieldUn;
	private static JTextField textFieldObra;
	produto prod = new produto();
	control_produto pedD = new control_produto();
	final static conexao produtoC = new conexao();
	public static JTable table;
	private int codigoU;
	private static JTextField textFieldFrete;
	private static JTextArea textFieldDesc;
	static JRadioButton rdbtnFrete;
	static JComboBox comboBoxCat;
	static JComboBox comboBoxCatFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					produtos frame = new produtos();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					comboboxCat();
					atualizar_produtos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public produtos() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(produtos.class.getResource("/img/electric-power-plug-icon-31.png")));
		setLocationRelativeTo(null);
		setTitle("CER - Controle de produtos");
		setResizable(false);
		setBounds(100, 100, 552, 743);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOpces = new JMenu("Opc\u00F5es");
		menuBar.add(mnOpces);

		JMenuItem mntmCategorias = new JMenuItem("Sair");
		mnOpces.add(mntmCategorias);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(20, 90, 516, 298);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("VL. Unit.");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNewLabel.setBounds(332, 48, 53, 14);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("Nome");
		label.setFont(new Font("Verdana", Font.PLAIN, 11));
		label.setBounds(37, 48, 46, 14);
		panel.add(label);

		JLabel lblCustoMoDe = new JLabel("VL. Inst.");
		lblCustoMoDe.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCustoMoDe.setBounds(422, 48, 53, 14);
		panel.add(lblCustoMoDe);

		JLabel lblLink = new JLabel("URL");
		lblLink.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblLink.setBounds(37, 101, 133, 14);
		panel.add(lblLink);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblDescrio.setBounds(37, 150, 103, 14);
		panel.add(lblDescrio);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(37, 70, 177, 20);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldUrl = new JTextField();
		textFieldUrl.setForeground(Color.BLUE);
		textFieldUrl.setColumns(10);
		textFieldUrl.setBounds(37, 119, 222, 20);
		panel.add(textFieldUrl);

		textFieldUn = new JTextField();
		textFieldUn.setText("00.0");
		textFieldUn.setColumns(10);
		textFieldUn.setBounds(332, 70, 53, 20);
		panel.add(textFieldUn);

		textFieldObra = new JTextField();
		textFieldObra.setText("00.0");
		textFieldObra.setColumns(10);
		textFieldObra.setBounds(422, 70, 53, 20);
		panel.add(textFieldObra);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCategoria.setBounds(322, 99, 103, 20);
		panel.add(lblCategoria);

		comboBoxCat = new JComboBox();
		comboBoxCat.setToolTipText("Selecione uma categoria para o produto");
		comboBoxCat.setEditable(true);
		comboBoxCat.setModel(new DefaultComboBoxModel(new String[] { "" }));
		comboBoxCat.setBounds(322, 119, 123, 20);
		panel.add(comboBoxCat);

		textFieldFrete = new JTextField("00.0");
		textFieldFrete.setColumns(10);
		textFieldFrete.setBounds(245, 70, 53, 20);
		panel.add(textFieldFrete);
		textFieldFrete.setEnabled(false);
		JButton btnAdd = new JButton("");
		btnAdd.setToolTipText("Adcionar um nova categoria");
		btnAdd.setIcon(new ImageIcon(produtos.class.getResource("/img/addcat.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// addcat.setVisible(true);
				// removo todos os campos do combobox
				try {
					String novoCat = (String) comboBoxCat.getSelectedItem();
					comboBoxCat.removeAllItems();
					// função que faz o preenchimento do comboBox da categoria
					Connection ExConn = (Connection) produtoC.abrirBDConn();
					Statement stmt = (Statement) ExConn.createStatement();
					stmt.setMaxRows(100);

					String sSQL = "INSERT INTO cer.categoria_produtos VALUES (null,'" + novoCat + "');";
					System.out.println(sSQL);
					boolean res = stmt.execute(sSQL);
					// renovar campos
					String query = "SELECT * FROM categoria_produtos";
					ResultSet rs = stmt.executeQuery(query);

					while (rs.next()) {
						System.out.print(rs);
						comboBoxCat.addItem(rs.getString("Categoria_nome"));
						String Categoria = rs.getString("Categoria_nome");
						System.out.print(Categoria);
					}
					produtoC.fecharBDConn();
					stmt.close();
					ExConn.close();
				} catch (Exception s) {
					// comboBox_3.removeAllItems();
					s.printStackTrace();
				}

			}
		});
		btnAdd.setBounds(452, 119, 23, 20);
		panel.add(btnAdd);

		rdbtnFrete = new JRadioButton("Frete");
		rdbtnFrete.setBackground(SystemColor.controlHighlight);
		rdbtnFrete.setToolTipText("Adicionar frete");
		rdbtnFrete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {
				if (((JRadioButton) rdbtnFrete).isSelected()) {
					// textFieldFrete.setText("00.0");
					textFieldFrete.setEnabled(true);
				} else {
					textFieldFrete.setText("00.0");
					textFieldFrete.setEnabled(false);
				}
			}
		});

		// textFieldFrete.setEnabled(false);

		rdbtnFrete.setFont(new Font("Verdana", Font.PLAIN, 11));
		rdbtnFrete.setBounds(244, 42, 59, 20);
		panel.add(rdbtnFrete);

		JButton btnEnviar = new JButton("");
		btnEnviar.setIcon(new ImageIcon(produtos.class.getResource("/img/add1.png")));
		btnEnviar.setBounds(427, 254, 39, 37);
		panel.add(btnEnviar);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(20, 392, 516, 291);
		contentPane.add(panel_1);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 486, 130);
		panel_1.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnExcluir = new JButton("");
		btnExcluir.setBounds(247, 222, 60, 60);
		panel_1.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				int row = (int) table.getValueAt(linha, 0);
				System.out.println(row);
				// String id = JOptionPane.showInputDialog(null,"Informe o ID do
				// documento:");
				try {
					Connection ExConn = (Connection) produtoC.abrirBDConn();
					Statement stmt = (Statement) ExConn.createStatement();
					String sSQL = "DELETE FROM produtos WHERE codigo='" + row + "';";
					String upSQL = "SELECT *FROM cer.produtos;";
					// realiza a exclusao dos dados
					int res = stmt.executeUpdate(sSQL);
					// executa sql para atualizar apos a exclusao
					ResultSet rs = stmt.executeQuery(upSQL);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					System.out.println(sSQL);
					if (res == 0)
						JOptionPane.showMessageDialog(null, "Dados nao foram encontrados!!!");
					if (res > 0)
						JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!!!");
				} catch (Exception s) {
					JOptionPane.showMessageDialog(null, "Não foi possivel excluir!");
					System.out.println("Ocorreu algum erro seu documento não pode ser excluido");
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(produtos.class.getResource("/img/delete.png")));
		btnExcluir.setToolTipText("Excluir");
		JButton btnUpdate = new JButton("");
		btnUpdate.setBounds(373, 222, 60, 60);
		panel_1.add(btnUpdate);
		btnUpdate.setToolTipText("Salvar edi\u00E7\u00E3o");

		btnUpdate.setIcon(new ImageIcon(produtos.class.getResource("/img/update.png")));
		btnUpdate.setEnabled(false);

		JButton btnEditar = new JButton("");
		btnEditar.setBounds(310, 222, 60, 60);
		panel_1.add(btnEditar);
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(produtos.class.getResource("/img/btnpeen.png")));
		JButton btnAtualizar = new JButton("");
		btnAtualizar.setBounds(436, 222, 60, 60);
		panel_1.add(btnAtualizar);
		btnAtualizar.setToolTipText("Carregar");
		btnAtualizar.setIcon(new ImageIcon(produtos.class.getResource("/img/refesh.png")));

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(produtos.class.getResource("/img/lista.png")));
		label_2.setBounds(10, 11, 67, 60);
		panel_1.add(label_2);

		textFieldDesc = new JTextArea();
		textFieldDesc.setBounds(10, 11, 418, 47);
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.add(textFieldDesc);

		JLabel lblCadastrados = new JLabel("Cadastrados");
		lblCadastrados.setFont(new Font("Verdana", Font.BOLD, 13));
		lblCadastrados.setBounds(187, 21, 160, 14);
		panel_1.add(lblCadastrados);

		comboBoxCatFiltro = new JComboBox();
		comboBoxCatFiltro.setModel(new DefaultComboBoxModel(new String[] { "" }));
		comboBoxCatFiltro.setBounds(368, 36, 102, 20);
		panel_1.add(comboBoxCatFiltro);

		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(369, 11, 35, 14);
		panel_1.add(lblFiltro);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(produtos.class.getResource("/img/lupa.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CatFiltro = (String) comboBoxCatFiltro.getSelectedItem();
				filtro(CatFiltro);
			}
		});
		btnNewButton.setBounds(472, 35, 23, 23);
		panel_1.add(btnNewButton);

		JButton button = new JButton("");
		button.setToolTipText("Acessar  site");
		button.setIcon(new ImageIcon(produtos.class.getResource("/img/link-button.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int urlLinha = table.getSelectedRow();
					String url = (String) table.getValueAt(urlLinha, 4);
					java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
				} catch (Exception erro) {

				}
			}
		});
		button.setBounds(20, 222, 60, 60);
		panel_1.add(button);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizar_produtos();
				limpa_campos();
				btnUpdate.setEnabled(false);
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// pega a linha celecionada
				try {
					rdbtnFrete.setEnabled(true);
					btnUpdate.setEnabled(true);
					int linha = table.getSelectedRow();
					codigoU = (int) table.getValueAt(linha, 0);
					System.out.println(codigoU);
					String Nome_Ori = (String) table.getValueAt(linha, 1);
					Double Uni_Ori = (Double) table.getValueAt(linha, 5);
					Double M_Ori = (Double) table.getValueAt(linha, 6);
					String Link_Ori = (String) table.getValueAt(linha, 4);
					String Desc_Ori = (String) table.getValueAt(linha, 3);
					Double Envio_Ori = (Double) table.getValueAt(linha, 7);
					String Categoria_Ori = (String) table.getValueAt(linha, 2);
					// Remember of MysqlDataBase
					// set all field with old register
					textFieldNome.setText(Nome_Ori);
					String Uni_Ori2 = Double.toString(Uni_Ori);
					textFieldUn.setText(Uni_Ori2);
					String M_Ori2 = Double.toString(M_Ori);
					textFieldObra.setText(M_Ori2);
					textFieldDesc.setText(Desc_Ori);
					textFieldUrl.setText(Link_Ori);
					String Envio_Ori2 = Double.toString(Envio_Ori);
					textFieldFrete.setText(Envio_Ori2);
					comboBoxCat.setSelectedItem(Categoria_Ori);
				} catch (Exception x) {
					JOptionPane.showMessageDialog(null, "Por favor selecione um produto!");
					btnUpdate.setEnabled(false);
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// botao editar

				String nomeU = textFieldNome.getText();
				Double uniU = Double.parseDouble(textFieldUn.getText());
				Double custoU = Double.parseDouble(textFieldObra.getText());
				String urlU = textFieldUrl.getText();
				String descricaoU = textFieldDesc.getText();
				Double freteU = Double.parseDouble(textFieldFrete.getText());
				String categoriaU = (String) comboBoxCat.getSelectedItem();
				// valida valores
				prod.setNome(nomeU);
				prod.setValor_unit(uniU);
				prod.setValor_obra(custoU);
				prod.setUrl(urlU);
				prod.setDescricao(descricaoU);
				prod.setFrete(freteU);
				// pega valores
				nomeU = prod.getNome();
				uniU = prod.getValor_unit();
				custoU = prod.getValor_obra();
				urlU = prod.getUrl();
				descricaoU = prod.getDescricao();
				freteU = prod.getFrete();
				pedD.UpdateProdutos(codigoU, nomeU, descricaoU, categoriaU, uniU, custoU, freteU, urlU);
				btnUpdate.setEnabled(false);
				limpa_campos();
				btnUpdate.setEnabled(false);
			}
		});

		JButton btnNovo = new JButton("");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpa_campos();
				btnUpdate.setEnabled(false);
			}
		});
		btnNovo.setIcon(new ImageIcon(produtos.class.getResource("/img/btnremove.png")));
		btnNovo.setBounds(467, 254, 39, 37);
		panel.add(btnNovo);

		panel_2.setBounds(37, 165, 438, 85);
		panel.add(panel_2);
		panel_2.setLayout(null);

		textFieldDesc.setToolTipText("Descri\u00E7\u00E3o");
		textFieldDesc.setRows(4);
		textFieldDesc.setLineWrap(true);

		JLabel lblFaaUmaBreve = new JLabel("Defina as principais caracterist\u00EDcas do produto");
		lblFaaUmaBreve.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblFaaUmaBreve.setBounds(20, 60, 324, 14);
		panel_2.add(lblFaaUmaBreve);

		JLabel lblCadastroDeProdutos = new JLabel("Cadastro de produtos");
		lblCadastroDeProdutos.setFont(new Font("Verdana", Font.BOLD, 13));
		lblCadastroDeProdutos.setBounds(178, 11, 193, 14);
		panel.add(lblCadastroDeProdutos);

		JLabel lblR = new JLabel("R$:");
		lblR.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblR.setBounds(216, 72, 23, 14);
		panel.add(lblR);

		JLabel label_3 = new JLabel("R$:");
		label_3.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_3.setBounds(299, 73, 23, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("R$:");
		label_4.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_4.setBounds(387, 73, 23, 14);
		panel.add(label_4);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(produtos.class.getResource("/img/plusproduto.png")));
		lblNewLabel_1.setBounds(6, 6, 46, 40);
		panel.add(lblNewLabel_1);

		JLabel lblControleDeProdutos = new JLabel("Controle de produtos");
		lblControleDeProdutos.setBounds(163, 23, 248, 26);
		contentPane.add(lblControleDeProdutos);
		lblControleDeProdutos.setFont(new Font("Verdana", Font.BOLD, 20));

		JLabel label_1 = new JLabel("");
		label_1.setBounds(10, 11, 79, 79);
		contentPane.add(label_1);
		label_1.setIcon(new ImageIcon(produtos.class.getResource("/img/box.png")));

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldNome.getText();
				Double uni = Double.parseDouble(textFieldUn.getText());
				Double custo = Double.parseDouble(textFieldObra.getText());
				String url = textFieldUrl.getText();
				String descricao = textFieldDesc.getText();
				Double frete = Double.parseDouble(textFieldFrete.getText());
				String categoria = (String) comboBoxCat.getSelectedItem();
				// valida valores
				prod.setNome(nome);
				prod.setValor_unit(uni);
				prod.setValor_obra(custo);
				prod.setUrl(url);
				prod.setDescricao(descricao);
				prod.setFrete(frete);
				// pega valores
				nome = prod.getNome();
				uni = prod.getValor_unit();
				custo = prod.getValor_obra();
				url = prod.getUrl();
				descricao = prod.getDescricao();
				frete = prod.getFrete();
				pedD.InsereProdutos(nome, uni, custo, descricao, url, frete, categoria);
				limpa_campos();
			}
		});
	}

	public static void comboboxCat() {
		try {
			// função que faz o preenchimento do comboBox da categoria
			Connection ExConn = (Connection) produtoC.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			stmt.setMaxRows(100);
			String query = "SELECT * FROM categoria_produtos";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.print(rs);
				comboBoxCatFiltro.addItem(rs.getString("Categoria_nome"));
				comboBoxCat.addItem(rs.getString("Categoria_nome"));
				String Categoria = rs.getString("Categoria_nome");
				System.out.print(Categoria);
			}
			ExConn.close();
		} catch (Exception s) {
			// comboBox_3.removeAllItems();
			s.printStackTrace();
		}
	}

	public static void filtro(String categoria) {
		if (comboBoxCatFiltro.getSelectedItem() != "")
			;
		{
			try {
				Connection ExConn = (Connection) produtoC.abrirBDConn();
				Statement stmt = (Statement) ExConn.createStatement();
				String sSQL = "SELECT Codigo as N, Categoria, Nome as Produto, Descr as Descrição ,Link as Url, Unitario as Unidade,M_Obra as Instalaçao,  Envio as frete FROM cer.produtos WHERE Categoria='"
						+ categoria + "';";
				System.out.println(sSQL);
				stmt.setMaxRows(100);
				ResultSet resp = stmt.executeQuery(sSQL);
				table.setModel(DbUtils.resultSetToTableModel(resp));
				stmt.close();
				produtoC.fecharBDConn();
			} catch (Exception e) {
				System.out.println("erro");
				produtoC.fecharBDConn();
				limpa_campos();
			}
		}

	}

	public static void atualizar_produtos() {
		try {
			Connection ExConn = (Connection) produtoC.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = "SELECT Codigo as N, Categoria, Nome as Produto, Descr as Descrição ,Link as Url, Unitario as Unidade,M_Obra as Instalaçao,  Envio as frete FROM cer.produtos ;";
			System.out.println(sSQL);
			stmt.setMaxRows(100);
			ResultSet resp = stmt.executeQuery(sSQL);
			table.setModel(DbUtils.resultSetToTableModel(resp));
			stmt.close();
			produtoC.fecharBDConn();

		} catch (Exception e) {
			System.out.println("erro");
			produtoC.fecharBDConn();
			limpa_campos();
		}
	}

	public static void limpa_campos() {

		textFieldNome.setText("");
		textFieldUrl.setText("");
		textFieldObra.setText("00.0");
		textFieldUn.setText("00.0");
		textFieldDesc.setText("");
		textFieldFrete.setText("00.0");
		comboBoxCat.removeAllItems();
		comboBoxCat.addItem("");
		atualizar_produtos();
		comboboxCat();

	}
}
