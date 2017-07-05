package frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.io.GetBufferedRandomAccessSource;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.xml.bind.ParseConversionEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLayeredPane;
import java.awt.Choice;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import connect.SendMailTLS;
import connect.conexao;
import control.control_orcamento;
import control.control_pedido;
import net.proteanit.sql.DbUtils;

import java.awt.Label;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.modelmbean.ModelMBean;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import object.*;
import javax.swing.table.TableCellRenderer;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;

public class inicial extends JFrame {
	static conexao connect = new conexao();
	static control_orcamento orcamento_control = new control_orcamento();
	static orcamento orcamento_obj = new orcamento();
	static carrinho carrinho_obj = new carrinho();
	private JPanel contentPane;
	static JTextField textFieldQtd;
	static JTextField textLoc = new JTextField();
	private String categoriaF, nomedestino, tipo, emaildestino;
	private static String TipoOrc;
	private static String cliente;
	static String criacao_;
	static DefaultTableModel model;
	static pedido obj_pedido = new pedido();
	private static int contador, carrinhoEdit;
	static double custo_final, soma, somaTratarErro, Final_equipamento, soma_erro, Final_maodeObra, result,
			contadorInsta_item, contadorEquip;
	static JComboBox comboBoxCli = new JComboBox();
	static JFormattedTextField JurosObra, JurosEquip;
	static JCheckBox chckbxGerar;
	// tirar -->> new JComboBox(); e colocar no corpo!!!
	static JComboBox comboBoxTipo = new JComboBox();
	static JComboBox comboBoxCategoria = new JComboBox();
	static JComboBox comboBoxModelo = new JComboBox();
	static JComboBox pagamentoObra;
	static JComboBox pagamentoEquip;
	static Date data = new Date();
	static JLabel lblOramento, CalcObra, CalcProd, CalcOrca, DT_Orca, labelLoc, MaoObraPaga, label_1;
	static Label Valor_total = new Label("00.0");
	static Label Valor_totalEqp = new Label("00.0");
	static Label labelVlfrete = new Label("00.0");
	static Label Valor_totalMaodeobra = new Label("00.0");
	static JTable tableCarrinho;
	static JCalendar calendar_1;
	static int CarrinhoCod, codigoOrcamento;
	static JRadioButton rdbtnTaxaDeLocomoo;
	static DecimalFormat formato = new DecimalFormat("##.##");
	static JRadioButton rdbtnFechar;
	static JButton btnAdicionar, btnRemover, btnCalcular, btnPedido, btnExportar, btnGmail;
	// clicadastro cads = new clicadastro();
	ImageIcon iconLogo = new ImageIcon(
			"C:\\Users\\Desktop\\Desktop\\workspace\\Cer - seguran\u00E7a eletronica\\cart.png");
	private static JTable tablePedidos = new JTable();

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
				atualiza_pedido();
				try {

					seleciona_tipo();
					seleciona_cliente();
					inicial frame = new inicial();
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
	 * @throws IOException
	 * @throws ParseException
	 */

	public inicial() throws IOException, ParseException {
		setBackground(UIManager.getColor("Table.dropLineShortColor"));
		// setBackground(UIManager.getColor("controlHighlight"));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(inicial.class.getResource("/img/electric-power-plug-icon-31.png")));

		setResizable(false);
		setLocationRelativeTo(null);
		// pegar os valeres da coluna e insere no combobox
		setTitle("CER - Solu\u00E7\u00F5es El\u00E9tricas");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1118, 765);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.Graphite.WindowsLookAndFeel"); // Windows
		} catch (Exception e) {
			// Inseira qualuquer codigo, se necessário.
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat Hora = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Verdana", Font.PLAIN, 12));
		setJMenuBar(menuBar);

		JMenu mnOpcoes = new JMenu("Op\u00E7oes");
		menuBar.add(mnOpcoes);

		JMenuItem mntmControleDeClientes = new JMenuItem("Controle de clientes");
		mntmControleDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clicadastro.main(null);
			}
		});
		mnOpcoes.add(mntmControleDeClientes);

		JMenuItem mntmControleDeProdutos = new JMenuItem("Controle de produtos");
		mntmControleDeProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtos.main(null);// chamando
			}
		});
		mnOpcoes.add(mntmControleDeProdutos);

		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mnOpcoes.add(mntmAjuda);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnOpcoes.add(mntmSobre);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnOpcoes.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 73, 881, 630);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNmero = new JLabel("N\u00BA");
		lblNmero.setBounds(265, 11, 23, 14);
		panel.add(lblNmero);

		Label labelVlinst = new Label("00.0");
		labelVlinst.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelVlinst.setBounds(691, 79, 51, 22);
		panel.add(labelVlinst);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblStatus.setBounds(42, 57, 46, 14);
		panel.add(lblStatus);

		JLabel lblDataCriao = new JLabel("Data cria\u00E7\u00E3o");
		lblDataCriao.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblDataCriao.setBounds(42, 133, 86, 14);
		panel.add(lblDataCriao);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCliente.setBounds(42, 101, 46, 14);
		panel.add(lblCliente);

		label_1 = new JLabel("000");
		label_1.setBounds(298, 11, 46, 14);
		panel.add(label_1);
		comboBoxCli.setEditable(true);
		comboBoxCli.setBackground(UIManager.getColor("controlHighlight"));

		comboBoxCli.setBounds(180, 105, 124, 20);
		panel.add(comboBoxCli);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(670, 318, 201, 71);
		panel.add(panel_2);
		panel_2.setLayout(null);

		// Image icon = ImageIO.read(new
		// File("C:\\Users\\pedro.henrique\\Documents\\Cer - segurança
		// eletronica\\add1.png"));
		// Image scaled = icon.getScaledInstance(90 ,50,
		// java.awt.Image.SCALE_SMOOTH);

		btnGmail = new JButton("");
		btnGmail.setToolTipText("Anexar por email or\u00E7amento");
		btnGmail.setEnabled(false);
		btnGmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conexao produtos = new conexao();
					Connection ExConn = (Connection) produtos.abrirBDConn();
					Statement stmt = (Statement) ExConn.createStatement();
					stmt.setMaxRows(1);
					nomedestino = (String) comboBoxCli.getSelectedItem();
					String query = "SELECT * FROM clientes WHERE Nome='" + nomedestino + "'";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
						// laço de repetiçao insere no dropdown de usuarios
						System.out.print(rs);
						emaildestino = rs.getString("Email");
					}

					ExConn.close();
				} catch (Exception s) {
					s.printStackTrace();
				}
				SendMailTLS.main(null, emaildestino, nomedestino);
			}
		});
		btnGmail.setIcon(new ImageIcon(inicial.class.getResource("/img/btnmail.png")));

		btnGmail.setBounds(135, 5, 60, 60);
		panel_2.add(btnGmail);

		btnExportar = new JButton("");
		btnExportar.setToolTipText("Gerar or\u00E7amento em pdf");
		btnExportar.setEnabled(false);

		btnExportar.setIcon(new ImageIcon(inicial.class.getResource("/img/btnpdf.png")));
		btnExportar.setBounds(70, 5, 60, 60);
		panel_2.add(btnExportar);

		btnPedido = new JButton("");
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// pegando valores do label e validando setter
				orcamento_obj.setStatus("Orçamento");
				orcamento_obj.setTipo((String) comboBoxTipo.getSelectedItem());
				orcamento_obj.setCliente((String) comboBoxCli.getSelectedItem());
				orcamento_obj.setDt_Orc(DT_Orca.getText());
				orcamento_obj.setVL_Obra(Double.parseDouble(CalcObra.getText().replaceAll(",", ".")));
				orcamento_obj.setVL_produtos(Double.parseDouble(CalcProd.getText().replaceAll(",", ".")));
				orcamento_obj.setVL_Total(Double.parseDouble(CalcOrca.getText().replaceAll(",", ".")));
				insere_carrinho();
				orcamento_control.InsereOrcamento(CarrinhoCod, orcamento_obj.getStatus(), orcamento_obj.getTipo(),
						orcamento_obj.getCliente(), orcamento_obj.getDt_Orc(), orcamento_obj.getVL_Obra(),
						orcamento_obj.getVL_produtos(), orcamento_obj.getVL_Total());
				// atualiza jtable com novos orçamentos
				atualiza_pedido();
				limpa_orcamento();
			}
		});
		btnPedido.setEnabled(false);
		btnPedido.setForeground(SystemColor.controlHighlight);
		btnPedido.setIcon(new ImageIcon(inicial.class.getResource("/img/add.png")));
		btnPedido.setToolTipText("Armazenar or\u00E7amento");
		btnPedido.setBounds(5, 5, 60, 60);
		panel_2.add(btnPedido);

		lblOramento = new JLabel("Or\u00E7amento");
		lblOramento.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblOramento.setBounds(180, 57, 94, 14);
		panel.add(lblOramento);

		Label custoFinal = new Label("00.0");
		custoFinal.setFont(new Font("Verdana", Font.BOLD, 12));
		custoFinal.setBounds(471, 106, 46, 22);
		panel.add(custoFinal);

		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblProduto.setBounds(628, 52, 46, 19);
		panel.add(lblProduto);
		Label labelVluni = new Label("00.0");
		labelVluni.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelVlfrete.setFont(new Font("Verdana", Font.PLAIN, 12));

		labelVlfrete.setBounds(790, 79, 60, 22);
		panel.add(labelVlfrete);
		comboBoxModelo.setEditable(true);
		comboBoxModelo.setBackground(UIManager.getColor("controlHighlight"));

		comboBoxModelo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxModelo.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						String NomePesq = (String) comboBoxModelo.getSelectedItem();
						try {
							custoFinal.setText("00.0");
							labelVluni.setText("00.0");
							labelVlinst.setText("00.0");
							Connection ExConn = (Connection) connect.abrirBDConn();
							Statement stmt = (Statement) ExConn.createStatement();
							System.out.println(categoriaF);
							stmt.setMaxRows(100);
							String query = "SELECT * FROM produtos WHERE Nome='" + NomePesq + "'";
							System.out.println(query);
							ResultSet rs = stmt.executeQuery(query);
							while (rs.next()) {
								System.out.print(rs);
								String C_unitario = rs.getString("Unitario");
								String C_instalacao = rs.getString("M_obra");
								String C_frete = rs.getString("Envio");
								String qtd = (String) textFieldQtd.getText();
								// converte os valores do bd para double
								double qtdDou = Double.parseDouble(qtd);
								double valor_uniDou = Double.parseDouble(C_unitario);
								double custo_itemDou = Double.parseDouble(C_instalacao);
								double custo_frete = Double.parseDouble(C_frete);
								// setando no label convertendo 00,00 dois
								// numeros após a virgula
								C_unitario = String.format("%.2f", valor_uniDou);
								C_instalacao = String.format("%.2f", custo_itemDou);
								C_frete = String.format("%.2f", custo_frete);
								labelVluni.setText(String.format(C_unitario.replaceAll(",", ".")));
								labelVlinst.setText(String.format(C_instalacao.replaceAll(",", ".")));
								labelVlfrete.setText(String.format(C_frete.replaceAll(",", ".")));
								// operação de soma
								// soma_erro=soma;
								soma = (((valor_uniDou + custo_itemDou) * qtdDou) + custo_frete);
								// System.out.println(soma);

								String somaText = String.format("%.2f", soma);
								custoFinal.setText(somaText);

							}

							ExConn.close();
						} catch (Exception w) {
							// soma-=soma_erro;
							// custoFinal.setText("00");
							// labelVluni.setText("00");
							// labelVlinst.setText("00");
							System.out.print("erro");
						}
					}
				});
			}
		});

		comboBoxModelo.setModel(new DefaultComboBoxModel(new String[] { "" }));

		comboBoxModelo.setBounds(705, 52, 127, 20);
		panel.add(comboBoxModelo);

		textFieldQtd = new JTextField();
		textFieldQtd.setHorizontalAlignment(SwingConstants.RIGHT);
		// apos atualizar o campo quantidade
		textFieldQtd.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// quando setar o novo valor na qtd item realiza o custo final
				// novamente com a nova quantidade
				try {
					Double ValorUni = Double.parseDouble(labelVluni.getText());
					Double ValorInst = Double.parseDouble(labelVlinst.getText());
					Double ValorFrete = Double.parseDouble(labelVlfrete.getText());
					labelVluni.getText();
					labelVlinst.getText();
					String qtd = textFieldQtd.getText();
					int qtdF = Integer.parseInt(qtd);
					somaTratarErro = soma;
					double valorF = (((ValorUni + ValorInst) * qtdF) + ValorFrete);
					System.out.println(valorF);
					String Custo_final = Double.toString(valorF);
					custoFinal.setText(Custo_final);
				} catch (Exception ex) {

					soma = somaTratarErro;
					String erro = Double.toString(soma);
					custoFinal.setText(erro);
					textFieldQtd.setText("1");
				}
			}
		});
		textFieldQtd.setText("1");
		textFieldQtd.setBounds(438, 81, 36, 20);
		panel.add(textFieldQtd);
		comboBoxCategoria.setToolTipText("podera adicionar categorias no controle de produtos");
		comboBoxCategoria.setEditable(true);
		comboBoxCategoria.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxCategoria.setBackground(UIManager.getColor("controlHighlight"));

		comboBoxCategoria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				categoriaF = (String) comboBoxCategoria.getSelectedItem();
				custoFinal.setText("00");
				textFieldQtd.setText("1");
				try {
					comboBoxModelo.removeAllItems();
					Connection ExConn = (Connection) connect.abrirBDConn();
					Statement stmt = (Statement) ExConn.createStatement();
					stmt.setMaxRows(100);
					String query = "SELECT * FROM produtos WHERE Categoria='" + categoriaF + "'";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
						comboBoxModelo.addItem(rs.getString("Nome"));
						String Custo_unitario = rs.getString("Nome");
					}
					ExConn.close();

				} catch (Exception w) {

				}
			}
		});

		JLabel lblQuantidade = new JLabel("QTD");
		lblQuantidade.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblQuantidade.setBounds(403, 78, 36, 22);
		panel.add(lblQuantidade);

		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento");
		lblFormaDePagamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaDePagamento.setBounds(119, 158, 127, 14);
		panel.add(lblFormaDePagamento);

		MaoObraPaga = new JLabel("00.0");
		MaoObraPaga.setFont(new Font("Verdana", Font.PLAIN, 11));
		MaoObraPaga.setBounds(297, 188, 51, 14);
		panel.add(MaoObraPaga);

		Valor_total.setBounds(63, 106, 49, 24);
		Valor_total.setFont(new Font("Verdana", Font.BOLD, 14));

		pagamentoEquip = new JComboBox();
		pagamentoEquip.setFont(new Font("Verdana", Font.PLAIN, 12));
		pagamentoEquip.setEnabled(false);
		Valor_totalEqp = new Label("00.0");
		Valor_totalEqp.setBounds(215, 111, 42, 14);

		JurosEquip = new JFormattedTextField();
		JurosEquip.setFont(new Font("Verdana", Font.PLAIN, 11));
		JurosEquip.setHorizontalAlignment(SwingConstants.RIGHT);
		JurosEquip.setText("00.0");
		JurosEquip.setEnabled(false);
		JurosEquip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (pagamentoEquip.getSelectedItem() != "Avista") {

				}
				if (pagamentoEquip.getSelectedItem().equals("Avista")) {

				}
			}
		});
		JurosEquip.setBounds(285, 218, 46, 20);
		panel.add(JurosEquip);

		pagamentoEquip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pagamentoEquip.getSelectedItem().equals("Avista")) {
					JurosEquip.setText(Valor_totalEqp.getText());

				} else {
					JurosEquip.setText("00.0");
				}
			}
		});
		pagamentoEquip.setModel(new DefaultComboBoxModel(new String[] { "", "Avista", "x2", "x3", "x4", "x5", "x6" }));
		pagamentoEquip.setBounds(132, 218, 62, 20);
		panel.add(pagamentoEquip);

		Valor_totalMaodeobra = new Label("00.0");
		Valor_totalMaodeobra.setBounds(386, 111, 46, 14);

		pagamentoObra = new JComboBox();
		pagamentoObra.setFont(new Font("Verdana", Font.PLAIN, 12));
		pagamentoObra.setEnabled(false);
		pagamentoObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pagamentoObra.getSelectedItem().equals("Avista")) {
					JurosObra.setEnabled(true);
					MaoObraPaga.setText(Valor_totalMaodeobra.getText());
				}
				if (pagamentoObra.getSelectedItem().equals("x2")) {
					MaoObraPaga.setText("00.0");
					JurosObra.setEnabled(true);
				}
				if (pagamentoObra.getSelectedItem().equals("x3")) {
					MaoObraPaga.setText("00.0");
					JurosObra.setEnabled(true);
				}
				if (pagamentoObra.getSelectedItem().equals("")) {
					MaoObraPaga.setText("00.0");
				}
			}
		});
		pagamentoObra.setModel(new DefaultComboBoxModel(new String[] { "", "Avista", "x2", "x3" }));
		pagamentoObra.setBounds(132, 183, 62, 20);
		panel.add(pagamentoObra);

		String criacao = sdf.format(data);
		DT_Orca = new JLabel(criacao);
		DT_Orca.setFont(new Font("Verdana", Font.PLAIN, 11));
		DT_Orca.setHorizontalAlignment(SwingConstants.LEFT);
		DT_Orca.setBounds(179, 133, 94, 14);
		panel.add(DT_Orca);

		labelVluni.setBounds(575, 79, 42, 22);
		panel.add(labelVluni);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.BOLD, 10));
		tabbedPane.setBackground(SystemColor.controlShadow);
		tabbedPane.setBounds(360, 144, 511, 162);
		panel.add(tabbedPane);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBackground(SystemColor.scrollbar);
		panel_3.setForeground(Color.BLACK);
		tabbedPane.addTab("Carrinho +", null, panel_3, null);
		panel_3.setLayout(null);
		panel_3.add(Valor_totalEqp);
		panel_3.add(Valor_totalMaodeobra);

		JScrollPane scrollCarrinho = new JScrollPane();
		scrollCarrinho.setBounds(10, 11, 486, 92);

		panel_3.add(scrollCarrinho);
		tableCarrinho = new JTable() {

			public boolean isCellEditable() {
				return false;
			}

		};

		tableCarrinho.setShowVerticalLines(false);
		// tableCarrinho.set
		scrollCarrinho.setViewportView(tableCarrinho);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Verdana", Font.PLAIN, 9));
		lblTotal.setBounds(10, 110, 42, 15);
		panel_3.add(lblTotal);

		JLabel lblTotalEquip = new JLabel("Total produtos:");
		lblTotalEquip.setFont(new Font("Verdana", Font.PLAIN, 9));
		lblTotalEquip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalEquip.setBounds(109, 111, 93, 14);
		panel_3.add(lblTotalEquip);

		JLabel lblTotalInstalaao = new JLabel("Total m\u00E3o de obra:");
		lblTotalInstalaao.setFont(new Font("Verdana", Font.PLAIN, 9));
		lblTotalInstalaao.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalInstalaao.setBounds(262, 111, 108, 14);
		panel_3.add(lblTotalInstalaao);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCategoria.setBounds(403, 52, 86, 19);
		panel.add(lblCategoria);
		comboBoxCategoria.setModel(new DefaultComboBoxModel(new String[] { "" }));

		comboBoxCategoria.setBounds(478, 52, 127, 20);
		panel.add(comboBoxCategoria);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 313, 890, 2);
		panel.add(separator);

		btnExportar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JTable tableG = new JTable();
				tableG = tableCarrinho;
				int count = tableCarrinho.getRowCount();
				String destinatario = comboBoxCli.getSelectedItem().toString();
				String extencao;
				// pega o nome do usuario e adiciona a extenção para gerar o pdf
				String destinatarioN = destinatario;
				destinatarioN += ".pdf";
				Document document = new Document();
				try {
					PdfWriter.getInstance(document, new FileOutputStream(destinatarioN));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				document.open();
				// intancia pdftable com quantidade de colunas
				PdfPTable tab = new PdfPTable(5);
				tab.addCell("Modelo");
				tab.addCell("QTD");
				tab.addCell("VL. Unitário");
				tab.addCell("VL. Mão de obra");
				tab.addCell("Custo total");
				for (int i = 0; i < count; i++) {
					Object obj1 = tableG.getValueAt(i, 0);
					Object obj3 = tableG.getValueAt(i, 2);
					Object obj4 = tableG.getValueAt(i, 3);
					Object obj5 = tableG.getValueAt(i, 4);
					Object obj6 = tableG.getValueAt(i, 5);
					String value1 = obj1.toString();
					String value3 = obj3.toString();
					String value4 = obj4.toString();
					String value5 = obj5.toString();
					String value6 = obj6.toString();
					tab.addCell(value1);
					tab.addCell(value3);
					tab.addCell(value4);
					tab.addCell(value5);
					tab.addCell(value6);
				}
				try {
					Paragraph paragraph = new Paragraph();
					paragraph.add(" \n \n   CER - SOLUÇÕES ELÉTRICAS \n \n \n               Orçamento: \n\n");
					document.addCreationDate();
					document.add(paragraph);
					document.add(tab);

					Paragraph paragraphCustoMaodeObra = new Paragraph();
					Paragraph paragraphCustoEquipamentos = new Paragraph();
					Paragraph paragraphCustoFinal = new Paragraph();
					Paragraph paragraphMensagem = new Paragraph();
					String Valor_equipoTotal_texto = "\n Total equipamentos: ";
					String Valor_maoObra_texto = "\n Total mão de obra: ";
					String Valor_totalCampo_texto = "\n Valor total: ";
					String text = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nObrigado pelo orçamento " + destinatario
							+ "!!!\n \n Qualquer dúvida entre em contato.\n Cer - Soluções Elétricas\n Contato: (31)98603-2648 [WHATSAPP]";

					String Valor_equipoTotal = CalcProd.getText();
					String Valor_maoObra = CalcObra.getText();
					String Valor_totalN = CalcOrca.getText();

					Valor_equipoTotal_texto += Valor_equipoTotal;
					Valor_maoObra_texto += Valor_maoObra;
					Valor_totalCampo_texto += Valor_totalN;

					paragraphCustoMaodeObra.add(Valor_maoObra_texto);
					paragraphCustoEquipamentos.add(Valor_equipoTotal_texto);
					paragraphCustoFinal.add(Valor_totalCampo_texto);
					paragraphMensagem.add(text);

					document.add(paragraphCustoEquipamentos);
					document.add(paragraphCustoMaodeObra);
					document.add(paragraphCustoFinal);
					document.add(paragraphMensagem);
					JOptionPane.showMessageDialog(null, "Orçamento gerado com sucesso");
					//logica para abrir o pdf
					try {
						String solicitante = (String) comboBoxCli.getSelectedItem().toString();
						solicitante += ".pdf";
						java.awt.Desktop.getDesktop().open(new File(solicitante));

					} catch (Exception e) {

					}
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao tentar exportar o arquivo");
					e.printStackTrace();

				}
				document.close();

			}
		});

		String[] columnNames = { "Modelo", "Categoria", "QTD", "VL. Unit.", "VL. Frete", "VL. Inst.", "VL. Total" };
		model = new DefaultTableModel(null, columnNames);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// faz a atualização dos campos calculando o custo final do
				// produto(unitario+custo de mão de obra);
				Double custo_item = Double.parseDouble(labelVlinst.getText());
				Double qtd = Double.parseDouble(textFieldQtd.getText());
				String Modelo = (String) comboBoxModelo.getSelectedItem();
				Double valor_uni = Double.parseDouble(labelVluni.getText());
				Double frete = Double.parseDouble(labelVlfrete.getText());
				// converte valores do textfield para realizar som
				// Faz o calculo dos valores finais
				soma = (custo_item + valor_uni);
				Final_equipamento += (qtd * valor_uni) + frete;
				Final_maodeObra += custo_item * qtd;
				// adiciona a linha no carrinho de compras
				model.insertRow(contador,
						new Object[] { Modelo, categoriaF, qtd, valor_uni, frete, custo_item, (soma * qtd) + frete });
				tableCarrinho.setModel(model);
				// contador de linhas
				contador += 1;
				// exibe na tela total do carrinho
				result += (soma * qtd) + frete;
				// pega o calculo dos valores finais e seta no label
				// DecimalFormat formato = new DecimalFormat("##.##");
				String Valor_totalEquipamento = String.format("%.2f", Final_equipamento);
				String Valor_totalCalculado = String.format("%.2f", result);
				String Valor_totalMaodeObra = String.format("%.2f", Final_maodeObra);
				Valor_totalEqp.setText(Valor_totalEquipamento);
				Valor_total.setText(Valor_totalCalculado);
				Valor_totalMaodeobra.setText(Valor_totalMaodeObra);
			}
		});
		panel_3.add(Valor_total);

		btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// pega linha
				int linha = tableCarrinho.getSelectedRow();
				// pega o custo final da linha excluida

				Double Coluna_qtd = (Double) tableCarrinho.getValueAt(linha, 2);
				Double Coluna_unitario = (Double) tableCarrinho.getValueAt(linha, 3);
				Double Coluna_inst = (Double) tableCarrinho.getValueAt(linha, 5);
				Double colunaFrete = (Double) tableCarrinho.getValueAt(linha, 4);
				Double Coluna_total = (Double) tableCarrinho.getValueAt(linha, 6);
				// remove linha
				Double custoF = Double.valueOf(Valor_total.getText().replaceAll(",", "."));
				Double custoEqp = Double.valueOf(Valor_totalEqp.getText().replaceAll(",", "."));
				Double custoobra = Double.valueOf(Valor_totalMaodeobra.getText().replaceAll(",", "."));
				// regra pra fazer calculo da exclusao
				// Double resultInst=custoInstTela;
				String catch_Coluna_total = Coluna_total.toString();
				result -= Coluna_total;
				Final_equipamento -= (Coluna_qtd * Coluna_unitario) + colunaFrete;
				Final_maodeObra -= Coluna_inst * Coluna_qtd;
				// pega os valores atualizados
				System.out.println(Coluna_qtd);
				// Valor_totalEqp.setText(Double.toString(Coluna_unit-custofEqp));
				String Valor_totalCalculado = Double.toString(result);
				String Valor_totalEquipamento = Double.toString(Final_equipamento);
				String Valor_totalInstalacao = Double.toString(Final_maodeObra);
				Valor_total.setText(Valor_totalCalculado);
				Valor_totalEqp.setText(Valor_totalEquipamento);
				Valor_totalMaodeobra.setText(Valor_totalInstalacao);
				model.removeRow(linha);
				// model.addRow(columnNames);
				contador -= 1;
			}
		});
		btnRemover.setIcon(new ImageIcon(inicial.class.getResource("/img/btnremove.png")));
		btnRemover.setToolTipText("Remover um produto");
		btnRemover.setBounds(812, 122, 36, 39);
		panel.add(btnRemover);

		CalcOrca = new JLabel("00.0");
		CalcOrca.setFont(new Font("Verdana", Font.BOLD, 18));
		CalcOrca.setBounds(210, 359, 103, 22);
		panel.add(CalcOrca);

		CalcProd = new JLabel("00.0");
		CalcProd.setFont(new Font("Verdana", Font.PLAIN, 12));
		CalcProd.setBounds(210, 341, 62, 14);
		panel.add(CalcProd);

		CalcObra = new JLabel("00.0");
		CalcObra.setFont(new Font("Verdana", Font.PLAIN, 12));
		CalcObra.setBounds(210, 325, 62, 14);
		panel.add(CalcObra);

		btnCalcular = new JButton("");
		btnCalcular.setToolTipText("Fa\u00E7a o calculo do or\u00E7amento");
		btnCalcular.setEnabled(false);

		chckbxGerar = new JCheckBox("Confirmar");
		chckbxGerar.setBackground(SystemColor.controlHighlight);
		chckbxGerar.setEnabled(false);
		chckbxGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desativa_btns();
			}
		});

		chckbxGerar.setBounds(310, 359, 86, 23);
		panel.add(chckbxGerar);

		rdbtnFechar = new JRadioButton("fechar");
		rdbtnFechar.setToolTipText("Ap\u00F3s fechamento sera possivel simular financeiro");
		rdbtnFechar.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnFechar.setFont(new Font("Verdana", Font.PLAIN, 9));
		rdbtnFechar.setBackground(SystemColor.scrollbar);
		rdbtnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				fechar_carrinho();
			}
		});
		rdbtnFechar.setBounds(422, 106, 74, 22);
		panel_3.add(rdbtnFechar);

		JLabel label_5 = new JLabel("R$");
		label_5.setFont(new Font("Verdana", Font.PLAIN, 8));
		label_5.setBounds(51, 111, 20, 14);
		panel_3.add(label_5);

		JLabel label_6 = new JLabel("R$");
		label_6.setFont(new Font("Verdana", Font.PLAIN, 8));
		label_6.setBounds(201, 111, 20, 14);
		panel_3.add(label_6);

		JLabel label_7 = new JLabel("R$");
		label_7.setFont(new Font("Verdana", Font.PLAIN, 8));
		label_7.setBounds(371, 111, 19, 14);
		panel_3.add(label_7);
		btnAdicionar.setToolTipText("Adicionar um produto");
		btnAdicionar.setIcon(new ImageIcon(inicial.class.getResource("/img/add1.png")));
		btnAdicionar.setBounds(770, 122, 39, 39);
		panel.add(btnAdicionar);

		JLabel lblValorUnit = new JLabel("Valor Unit.");
		lblValorUnit.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblValorUnit.setBounds(510, 79, 62, 19);
		panel.add(lblValorUnit);

		JLabel lblProdutos = new JLabel("Valor total produtos:");
		lblProdutos.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblProdutos.setBounds(40, 341, 160, 14);
		panel.add(lblProdutos);

		JLabel lblValorTotalMo = new JLabel("Valor total m\u00E3o de obra:");
		lblValorTotalMo.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblValorTotalMo.setBounds(40, 325, 141, 14);
		panel.add(lblValorTotalMo);

		JLabel lblCustoInst = new JLabel("Custo Inst.");
		lblCustoInst.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCustoInst.setBounds(617, 79, 68, 19);
		panel.add(lblCustoInst);

		JLabel lblValorFinal = new JLabel("Valor Total:");
		lblValorFinal.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblValorFinal.setBounds(41, 366, 106, 14);
		panel.add(lblValorFinal);

		JPanel panelPedidos = new JPanel();
		panelPedidos.setBackground(SystemColor.controlHighlight);
		panelPedidos.setLayout(null);
		panelPedidos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelPedidos.setBounds(10, 392, 861, 228);
		panel.add(panelPedidos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 59, 581, 160);
		panelPedidos.add(scrollPane);
		tablePedidos.setShowVerticalLines(false);

		scrollPane.setViewportView(tablePedidos);

		JButton btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Excluir or\u00E7amento");
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int linha = tablePedidos.getSelectedRow();
				int codigo = (int) tablePedidos.getValueAt(linha, 0);
				int carrinho = (int) tablePedidos.getValueAt(linha, 1);
				exclui_pedido(linha, codigo, carrinho);
			}
		});
		btnExcluir.setIcon(new ImageIcon(inicial.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(795, 97, 60, 60);
		panelPedidos.add(btnExcluir);

		JButton btnAtualiza = new JButton("");
		btnAtualiza.setToolTipText("Atualizar registros");
		btnAtualiza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				atualiza_pedido();
			}
		});
		btnAtualiza.setIcon(new ImageIcon(inicial.class.getResource("/img/refesh.png")));
		btnAtualiza.setSelectedIcon(
				new ImageIcon("C:\\Users\\Desktop\\Desktop\\workspace\\Cer - seguran\u00E7a eletronica\\refesh.png"));
		btnAtualiza.setBounds(795, 159, 60, 60);
		panelPedidos.add(btnAtualiza);

		JLabel lblPedidos = new JLabel("Registro de Or\u00E7amentos:");
		lblPedidos.setFont(new Font("Verdana", Font.BOLD, 15));
		lblPedidos.setBounds(264, 11, 263, 26);
		panelPedidos.add(lblPedidos);

		JLabel label_12 = new JLabel("");
		label_12.setBounds(19, 11, 59, 64);
		panelPedidos.add(label_12);
		label_12.setIcon(new ImageIcon(inicial.class.getResource("/img/pedidos.png")));

		JButton btnConfimaEdicao = new JButton("");
		btnConfimaEdicao.setToolTipText("Confirmar edi\u00E7\u00E3o");
		btnConfimaEdicao.setEnabled(false);
		btnConfimaEdicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				orcamento_obj.setStatus("Orçamento");
				orcamento_obj.setTipo((String) comboBoxTipo.getSelectedItem());
				orcamento_obj.setCliente((String) comboBoxCli.getSelectedItem());
				orcamento_obj.setDt_Orc(DT_Orca.getText());
				orcamento_obj.setVL_Obra(Double.parseDouble(CalcObra.getText().replaceAll(",", ".")));
				orcamento_obj.setVL_produtos(Double.parseDouble(CalcProd.getText().replaceAll(",", ".")));
				orcamento_obj.setVL_Total(Double.parseDouble(CalcOrca.getText().replaceAll(",", ".")));
				orcamento_obj.setCodigo(codigoOrcamento);
				control_orcamento.UpdateOrcamento(carrinhoEdit, orcamento_obj.getStatus(), orcamento_obj.getTipo(),
						orcamento_obj.getCliente(), orcamento_obj.getDt_Orc(), orcamento_obj.getVL_Obra(),
						orcamento_obj.getVL_produtos(), orcamento_obj.getVL_Total(), orcamento_obj.getCodigo());
				Update_carrinho(carrinhoEdit);
				btnConfimaEdicao.setEnabled(false);
				atualiza_pedido();
			}
		});

		JButton btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar or\u00E7amento");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnConfimaEdicao.setEnabled(true);
				int linhaEdit = tablePedidos.getSelectedRow();
				carrinhoEdit = (int) tablePedidos.getValueAt(linhaEdit, 1);
				codigoOrcamento = (int) tablePedidos.getValueAt(linhaEdit, 0);

				limpa_orcamento();
				editar_pedido(carrinhoEdit);
				rdbtnFechar.setSelected(false);
				chckbxGerar.setEnabled(true);
				// chckbxGerar.setSelected(true);
			}
		});
		btnEditar.setIcon(new ImageIcon(inicial.class.getResource("/img/btnpeen.png")));
		btnEditar.setBounds(670, 97, 60, 60);
		panelPedidos.add(btnEditar);

		JButton btnAgendamentos = new JButton("");
		btnAgendamentos.setToolTipText("Pedidos agendados");
		btnAgendamentos.setIcon(new ImageIcon(inicial.class.getResource("/img/papel icon.png")));
		btnAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				pedidos.main(null);
			}
		});
		btnAgendamentos.setBounds(732, 159, 60, 60);
		panelPedidos.add(btnAgendamentos);

		btnConfimaEdicao.setIcon(new ImageIcon(inicial.class.getResource("/img/update.png")));
		btnConfimaEdicao.setBounds(732, 97, 60, 60);
		panelPedidos.add(btnConfimaEdicao);

		JButton btnInserirPedido = new JButton("");
		btnInserirPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha_orca = tablePedidos.getSelectedRow();
				// validar objetos
				obj_pedido.setCodigoOrca((int) tablePedidos.getValueAt(linha_orca, 0));
				obj_pedido.setStatus((tablePedidos.getValueAt(linha_orca, 2).toString()));
				obj_pedido.setTipo(tablePedidos.getValueAt(linha_orca, 3).toString());
				obj_pedido.setCliente(tablePedidos.getValueAt(linha_orca, 4).toString());
				obj_pedido.setDt_Orc(tablePedidos.getValueAt(linha_orca, 5).toString());
				obj_pedido.setVL_Obra((Double) tablePedidos.getValueAt(linha_orca, 6));
				obj_pedido.setVL_produtos((Double) tablePedidos.getValueAt(linha_orca, 7));
				obj_pedido.setVL_Total((Double) tablePedidos.getValueAt(linha_orca, 8));
				System.out.println(obj_pedido.getCliente());
				control_pedido pedido = new control_pedido();
				agendar_pedido pedido_agendar = new agendar_pedido();
				if (linha_orca > -1)
					pedido_agendar.setVisible(true);
			}
		});
		btnInserirPedido.setToolTipText("Agendar pedido");
		btnInserirPedido.setIcon(new ImageIcon(inicial.class.getResource("/img/AUTORIZAR.png")));
		btnInserirPedido.setBounds(670, 159, 60, 60);
		panelPedidos.add(btnInserirPedido);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(348, 0, 2, 315);
		panel.add(separator_1);

		JLabel lblCustoFinal = new JLabel("Custo final");
		lblCustoFinal.setToolTipText("");
		lblCustoFinal.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCustoFinal.setBounds(403, 110, 62, 14);
		panel.add(lblCustoFinal);

		JLabel lblMoDeObra = new JLabel("M\u00E3o de obra:");
		lblMoDeObra.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblMoDeObra.setBounds(43, 189, 86, 14);
		panel.add(lblMoDeObra);

		JLabel lblEquipamentos = new JLabel("Produtos:");
		lblEquipamentos.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblEquipamentos.setBounds(42, 220, 106, 14);
		panel.add(lblEquipamentos);

		JLabel labelCart = new JLabel("");
		labelCart.setIcon(new ImageIcon(inicial.class.getResource("/img/cart.png")));

		labelCart.setBounds(354, 11, 30, 30);
		panel.add(labelCart);

		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(inicial.class.getResource("/img/contract.png")));
		label_10.setBounds(10, 0, 46, 50);
		panel.add(label_10);

		JLabel lblFrete = new JLabel("Frete");
		lblFrete.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblFrete.setBounds(748, 79, 36, 19);
		panel.add(lblFrete);

		JLabel lblOramento_1 = new JLabel("Or\u00E7amento");
		lblOramento_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblOramento_1.setBounds(141, 19, 131, 22);
		panel.add(lblOramento_1);

		JLabel lblCarrinho = new JLabel("Produtos");
		lblCarrinho.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCarrinho.setBounds(572, 23, 111, 14);
		panel.add(lblCarrinho);

		// MaskFormatter MascaraJuros=new MaskFormatter("0.00");
		// MascaraJuros.setPlaceholderCharacter('_');

		JurosObra = new JFormattedTextField();
		JurosObra.setFont(new Font("Verdana", Font.PLAIN, 12));
		JurosObra.setEnabled(false);
		JurosObra.setEnabled(false);
		JurosObra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				// alorJuroTotal := Total * JuroAoMes * QuantidadeMeses / 100
				if (pagamentoObra.getSelectedItem() != "Avista") {

					int qtdMes = 0;
					if (pagamentoObra.getSelectedItem().equals("x2"))
						qtdMes = 1;
					if (pagamentoObra.getSelectedItem().equals("x3"))
						qtdMes = 2;
					Double juros = (double) Double.parseDouble(JurosObra.getText().replaceAll(",", "."));
					Double ValorJurosT = ((double) Double
							.parseDouble(Valor_totalMaodeobra.getText().replaceAll(",", "."))
							* (Math.pow((1 + (juros / 100)), qtdMes)));
					String finalJuros = String.valueOf((ValorJurosT));
					MaoObraPaga.setText(finalJuros);

				}
			}
		});
		JurosObra.setBounds(252, 183, 24, 20);
		panel.add(JurosObra);

		JLabel lblJuros = new JLabel("Juros");
		lblJuros.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblJuros.setBounds(202, 189, 42, 13);
		panel.add(lblJuros);

		JLabel lblMontanteR = new JLabel("Montante");
		lblMontanteR.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblMontanteR.setBounds(202, 221, 60, 14);
		panel.add(lblMontanteR);

		JLabel lblServio = new JLabel("Tipo");
		lblServio.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblServio.setBounds(42, 79, 46, 14);
		panel.add(lblServio);
		comboBoxTipo.setBackground(UIManager.getColor("controlHighlight"));

		comboBoxTipo.setEditable(true);
		comboBoxTipo.setBounds(207, 77, 98, 20);
		panel.add(comboBoxTipo);

		JButton btnAddTipo = new JButton("");
		btnAddTipo.setToolTipText("Cadastre um modelo de or\u00E7amento");
		btnAddTipo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					conexao produtos = new conexao();
					String tipo = (String) comboBoxTipo.getSelectedItem();
					comboBoxTipo.removeAllItems();
					comboBoxTipo.addItem("");
					Connection ExConn = (Connection) produtos.abrirBDConn();
					Statement stmt = (Statement) ExConn.createStatement();
					stmt.setMaxRows(100);
					String sSQL = "INSERT INTO categoria_orcamento VALUES(null,'" + tipo + "');";
					System.out.println(sSQL);
					boolean res = stmt.execute(sSQL);
					// renovar campos
					produtos.fecharBDConn();
					stmt.close();
					ExConn.close();
					seleciona_tipo();
				} catch (Exception s) {
					s.printStackTrace();
				}

			}
		});
		btnAddTipo.setIcon(new ImageIcon(inicial.class.getResource("/img/addcat.png")));
		btnAddTipo.setBounds(310, 79, 21, 20);
		panel.add(btnAddTipo);

		JButton btnAddCliente = new JButton("");
		btnAddCliente.setToolTipText("Cadastrar ou modificar cliente");
		btnAddCliente.setIcon(new ImageIcon(inicial.class.getResource("/img/addcat.png")));
		btnAddCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clicadastro.main(null);
			}
		});
		btnAddCliente.setBounds(310, 104, 21, 21);
		panel.add(btnAddCliente);

		JButton btnAddProduto = new JButton("");
		btnAddProduto.setToolTipText("Adicionar produto");
		btnAddProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtos prod = new produtos();
				prod.main(null);
			}
		});
		btnAddProduto.setIcon(new ImageIcon(inicial.class.getResource("/img/addcat.png")));
		btnAddProduto.setBounds(680, 53, 21, 20);
		panel.add(btnAddProduto);

		btnCalcular.setIcon(new ImageIcon(inicial.class.getResource("/img/calc.png")));
		btnCalcular.setBounds(5, 272, 36, 36);
		panel.add(btnCalcular);

		JLabel lblR = new JLabel("R$");
		lblR.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblR.setBounds(191, 326, 23, 14);
		panel.add(lblR);

		JLabel label_3 = new JLabel("R$");
		label_3.setFont(new Font("Verdana", Font.PLAIN, 12));
		label_3.setBounds(191, 341, 23, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("R$");
		label_4.setFont(new Font("Verdana", Font.PLAIN, 12));
		label_4.setBounds(180, 359, 20, 30);
		panel.add(label_4);

		JLabel lblR_1 = new JLabel("R$");
		lblR_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblR_1.setBounds(266, 221, 23, 14);
		panel.add(lblR_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 165, 114, 2);
		panel.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(244, 166, 104, 1);
		panel.add(separator_3);

		JLabel label_8 = new JLabel("%");
		label_8.setBounds(237, 185, 15, 19);
		panel.add(label_8);

		JLabel label_2 = new JLabel("R$");
		label_2.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_2.setBounds(278, 188, 23, 14);
		panel.add(label_2);

		rdbtnTaxaDeLocomoo = new JRadioButton("Taxa de deslocamento:");
		rdbtnTaxaDeLocomoo.setFont(new Font("Verdana", Font.PLAIN, 11));
		rdbtnTaxaDeLocomoo.setBackground(SystemColor.controlHighlight);
		rdbtnTaxaDeLocomoo.setEnabled(false);
		rdbtnTaxaDeLocomoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnTaxaDeLocomoo.isSelected()) {
					textLoc.setEnabled(true);
				} else {
					textLoc.setEnabled(false);
				}
			}
		});
		rdbtnTaxaDeLocomoo.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnTaxaDeLocomoo.setBounds(40, 245, 168, 23);
		panel.add(rdbtnTaxaDeLocomoo);

		JLabel label_13 = new JLabel("R$");
		label_13.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_13.setBounds(211, 251, 20, 14);
		panel.add(label_13);
		textLoc.setFont(new Font("Verdana", Font.PLAIN, 11));

		textLoc.setEnabled(false);
		textLoc.setText("00.0");
		textLoc.setBounds(230, 248, 44, 20);
		panel.add(textLoc);
		textLoc.setColumns(10);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(inicial.class.getResource("/img/btnremove_.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conexao produtos = new conexao();
					String tipo = (String) comboBoxTipo.getSelectedItem();
					comboBoxTipo.removeAllItems();
					comboBoxTipo.addItem("");
					Connection ExConn = (Connection) produtos.abrirBDConn();
					Statement stmt = (Statement) ExConn.createStatement();
					stmt.setMaxRows(100);
					String sSQL = "DELETE FROM cer.categoria_orcamento WHERE Categoria='" + tipo + "';";
					System.out.println(sSQL);
					boolean res = stmt.execute(sSQL);
					// renovar campos
					produtos.fecharBDConn();
					stmt.close();
					ExConn.close();
					seleciona_tipo();
				} catch (Exception s) {
					s.printStackTrace();
				}
			}
		});
		button.setToolTipText("Exclua um modelo de or\u00E7amento");
		button.setBounds(180, 79, 21, 20);
		panel.add(button);

		JLabel label_14 = new JLabel("");
		label_14.setIcon(new ImageIcon(inicial.class.getResource("/img/share-raw-128.png")));
		label_14.setBounds(301, 133, 30, 29);
		panel.add(label_14);

		String hora = sdf.format(data);

		JLabel lblNewLabel = new JLabel("cersolucoeseletricas@gmail.com");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 10));
		lblNewLabel.setBounds(917, 533, 195, 14);
		contentPane.add(lblNewLabel);

		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(inicial.class.getResource("/img/iconinicial.png")));
		label_9.setBounds(265, 0, 84, 73);
		contentPane.add(label_9);

		JLabel lblControlDePedidos = new JLabel("Controle de Pedidos");
		lblControlDePedidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblControlDePedidos.setFont(new Font("Verdana", Font.BOLD, 30));
		lblControlDePedidos.setBounds(344, 22, 351, 44);
		contentPane.add(lblControlDePedidos);

		JTabbedPane Calender = new JTabbedPane(JTabbedPane.TOP);
		Calender.setBorder(null);
		Calender.setFont(new Font("Verdana", Font.BOLD, 11));
		Calender.setBounds(891, 46, 221, 195);
		contentPane.add(Calender);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.controlHighlight);
		Calender.addTab("Calendario", null, panel_5, null);
		panel_5.setLayout(null);

		calendar_1 = new JCalendar();
		calendar_1.getMonthChooser().getComboBox().setFont(new Font("Verdana", Font.PLAIN, 6));
		calendar_1.getMonthChooser().getComboBox().setEnabled(false);
		calendar_1.getDayChooser().setDecorationBackgroundColor(SystemColor.controlHighlight);

		calendar_1.setFont(new Font("Verdana", Font.PLAIN, 7));
		calendar_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		calendar_1.setBounds(0, 0, 219, 161);
		panel_5.add(calendar_1);

		JLabel label = new JLabel("(31)98603-2648");
		label.setFont(new Font("Verdana", Font.BOLD, 10));
		label.setBounds(963, 558, 114, 14);
		contentPane.add(label);

		JLabel label_11 = new JLabel("");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setIcon(new ImageIcon(inicial.class.getResource("/img/logo1.png")));
		label_11.setBounds(926, 394, 173, 127);
		contentPane.add(label_11);

		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					chckbxGerar.setEnabled(true);
					CalcObra.setText(MaoObraPaga.getText());
					CalcProd.setText(JurosEquip.getText());
					String contaObra = MaoObraPaga.getText().replaceAll(",", ".");
					String contaProd = JurosEquip.getText().replaceAll(",", ".");
					Double contaResult = (Double.parseDouble(contaObra) + (Double.parseDouble(contaProd)));
					if (rdbtnTaxaDeLocomoo.isSelected()) {
						if ((textLoc.getText() != "00.0") || (textLoc.getText() != "0.0")
								|| (textLoc.getText() != "000.0") || (textLoc.getText() != "0")) {
							// mudar essa logica esata
							// errada!!!!!!!!!!!!!!!!!!!!!!
							contaResult += Double.valueOf(textLoc.getText());
						}
					}

					// System.out.println(contaResult);
					CalcOrca.setText(contaResult.toString());
				} catch (Exception e) {

				}
			}

		});

		try {
			// função que faz o preenchimento do comboBox da categoria
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			stmt.setMaxRows(100);
			String query = "SELECT * FROM cer.categoria_produtos";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.print(rs);
				comboBoxCategoria.addItem(rs.getString("Categoria_nome"));
				String Categoria = rs.getString("Categoria_nome");
				System.out.print(Categoria);
			}
			categoriaF = (String) comboBoxCategoria.getSelectedItem();
			System.out.print(categoriaF);
			ExConn.close();
		} catch (Exception s) {
			// comboBox_3.removeAllItems();
			s.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")

	public static void seleciona_tipo() {
		try {
			comboBoxTipo.removeAllItems();
			comboBoxTipo.addItem("");
			conexao produtos = new conexao();
			Connection ExConn = (Connection) produtos.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			stmt.setMaxRows(100);
			String query = "SELECT * FROM cer.categoria_orcamento";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				comboBoxTipo.addItem(rs.getString("Categoria"));
				String Categoria = rs.getString("Categoria");
			}
			produtos.fecharBDConn();
			stmt.close();
			ExConn.close();
		} catch (Exception s) {
			s.printStackTrace();
		}
	}

	public static void atualiza_pedido() {
		try {
			conexao connect = new conexao();
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = "SELECT Codigo as N, Codigo_carrinho as Carrinho, Status,Tipo, Nm_Cliente as Solicitante,Dt_Orc as Orçamento,VL_Obra as Instalação,VL_Prod as Produtos,VL_Total as Total FROM cer.Agenda order by Codigo desc;";
			System.out.println(sSQL);
			stmt.setMaxRows(100);
			ResultSet resp = stmt.executeQuery(sSQL);
			tablePedidos.setModel(DbUtils.resultSetToTableModel(resp));
			limpa_orcamento();
			stmt.close();
			connect.fecharBDConn();

		} catch (Exception e) {
			System.out.println("erro");
			connect.fecharBDConn();
		}
	}

	public static void seleciona_cliente() {
		try {
			comboBoxCli.removeAllItems();
			comboBoxCli.addItem("");
			conexao cliente = new conexao();
			Connection ExConn = (Connection) cliente.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			stmt.setMaxRows(100);
			String query = "SELECT * FROM cer.clientes";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// laço de repetiçao insere no dropdown de usuarios
				System.out.print(rs);
				comboBoxCli.addItem(rs.getString("Nome"));
			}
			stmt.close();
			ExConn.close();
		} catch (Exception s) {
			System.out.println("Erro");
		}
	}

	public static void exclui_pedido(int linha, int codigo, int carrinho) {

		// String id = JOptionPane.showInputDialog(null,"Informe o ID do
		// documento:");
		try {
			conexao connect = new conexao();
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQLa = "DELETE FROM cer.Agenda WHERE Codigo='" + codigo + "';";
			String sSQLc = "DELETE FROM cer.carrinho WHERE Codigo='" + carrinho + "';";
			String upSQL = "SELECT *FROM cer.Agenda;";
			// realiza a exclusao dos dados
			int res = stmt.executeUpdate(sSQLa);
			int resc = stmt.executeUpdate(sSQLc);
			// executa sql para atualizar apos a exclusao
			ResultSet rs = stmt.executeQuery(upSQL);
			System.out.println(sSQLa);
			System.out.println(sSQLc);
			atualiza_pedido();
			if (res == 0)
				JOptionPane.showMessageDialog(null, "Selecione um pedido a ser excluido!!!");
			if (res > 0)
				JOptionPane.showMessageDialog(null, "Pedido excluidos com sucesso!!!");
			stmt.close();
			connect.fecharBDConn();
		} catch (Exception s) {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir!");
			System.out.println("Ocorreu algum erro seu pedido não pode ser excluido");
		}
	}

	static int contCarrinho;

	public static void insere_carrinho() {
		try {
			CarrinhoCod = 0;
			conexao connect = new conexao();
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String query = "Select *FROM cer.carrinho;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				CarrinhoCod = rs.getInt("Codigo");
				System.out.println(CarrinhoCod);
			}
			// adiciona 1 ao ultimo carrinho registrdo
			CarrinhoCod += 1;
			for (int linha = 0; linha < tableCarrinho.getRowCount(); linha++) {
				String modelo = (String) tableCarrinho.getModel().getValueAt(linha, 0).toString();
				String categoria = (String) tableCarrinho.getModel().getValueAt(linha, 1).toString();
				String qtd = (String) tableCarrinho.getModel().getValueAt(linha, 2).toString();
				String unitario = (String) tableCarrinho.getModel().getValueAt(linha, 3).toString();
				String frete = (String) tableCarrinho.getModel().getValueAt(linha, 4).toString();
				String maodeobra = (String) tableCarrinho.getModel().getValueAt(linha, 5).toString();
				String total = (String) tableCarrinho.getModel().getValueAt(linha, 6).toString();
				carrinho_obj.setModelo(modelo);
				carrinho_obj.setCategoria(categoria);
				carrinho_obj.setQtd(qtd.replaceAll(",", "."));
				carrinho_obj.setUnitario(unitario.replaceAll(",", "."));
				carrinho_obj.setFrete(frete.replaceAll(",", "."));
				carrinho_obj.setMaodeobra(maodeobra.replaceAll(",", "."));
				carrinho_obj.setTotal(total.replaceAll(",", "."));
				contCarrinho += 1;
				System.out.println("\n");
				System.out.println(modelo);
				System.out.println("\n");
				System.out.println(contCarrinho);
				modelo = carrinho_obj.getModelo();
				categoria = carrinho_obj.getCategoria();
				qtd = carrinho_obj.getQtd();
				unitario = carrinho_obj.getUnitario();
				frete = carrinho_obj.getFrete();
				maodeobra = carrinho_obj.getMaodeobra();
				total = carrinho_obj.getTotal();
				orcamento_control.InsereCarrinho((CarrinhoCod), modelo, categoria, qtd, unitario, maodeobra, frete,
						total);
				connect.fecharBDConn();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro contate o administrador!!!");
		}
	}

	public static void Update_carrinho(int carrinhoEdit) {
		try {
			conexao connect = new conexao();
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String queryDell = "DELETE FROM carrinho WHERE Codigo='" + carrinhoEdit + "';";
			boolean rsD = stmt.execute(queryDell);
			System.out.println(queryDell);
			// adiciona 1 ao ultimo carrinho registrdo
			// CarrinhoCod += 1;
			for (int linha = 0; linha < tableCarrinho.getRowCount(); linha++) {
				String modelo = (String) tableCarrinho.getModel().getValueAt(linha, 0).toString();
				String categoria = (String) tableCarrinho.getModel().getValueAt(linha, 1).toString();
				String qtd = (String) tableCarrinho.getModel().getValueAt(linha, 2).toString();
				String unitario = (String) tableCarrinho.getModel().getValueAt(linha, 3).toString();
				String frete = (String) tableCarrinho.getModel().getValueAt(linha, 4).toString();
				String maodeobra = (String) tableCarrinho.getModel().getValueAt(linha, 5).toString();
				String total = (String) tableCarrinho.getModel().getValueAt(linha, 6).toString();
				carrinho_obj.setModelo(modelo);
				carrinho_obj.setCategoria(categoria);
				carrinho_obj.setQtd(qtd.replaceAll(",", "."));
				carrinho_obj.setUnitario(unitario.replaceAll(",", "."));
				carrinho_obj.setFrete(frete.replaceAll(",", "."));
				carrinho_obj.setMaodeobra(maodeobra.replaceAll(",", "."));
				carrinho_obj.setTotal(total.replaceAll(",", "."));
				contCarrinho += 1;
				modelo = carrinho_obj.getModelo();
				categoria = carrinho_obj.getCategoria();
				qtd = carrinho_obj.getQtd();
				unitario = carrinho_obj.getUnitario();
				frete = carrinho_obj.getFrete();
				maodeobra = carrinho_obj.getMaodeobra();
				total = carrinho_obj.getTotal();
				orcamento_control.InsereCarrinho((carrinhoEdit), modelo, categoria, qtd, unitario, maodeobra, frete,
						total);
				connect.fecharBDConn();
			}
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Erro contate o
			// administrador!!!");
		}
	}

	public static void editar_pedido(int carrinhoEdit) {
		try {
			conexao connect = new conexao();
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			btnGmail.setEnabled(true);
			btnExportar.setEnabled(true);
			// realiza a exclusao dos dados
			stmt.setMaxRows(100);
			String sSQLa = "Select * FROM cer.agenda WHERE Codigo_carrinho='" + carrinhoEdit + "';";
			ResultSet resa = stmt.executeQuery(sSQLa);

			while (resa.next()) {
				// orcamento
				// pegas os valores do banco e seta
				lblOramento.setText(resa.getString("Status"));
				TipoOrc = resa.getString("Tipo");
				cliente = resa.getString("Nm_Cliente");
				CalcProd.setText(resa.getString("VL_Prod"));
				CalcObra.setText(resa.getString("VL_Obra"));
				CalcOrca.setText(resa.getString("VL_Total"));

				criacao_ = resa.getString("Dt_Orc");
			}
			DT_Orca.removeAll();
			DT_Orca.setText(criacao_);
			System.out.println("erro");
			System.out.println(TipoOrc);
			System.out.println(cliente);
			// comboBoxTipo.addItem(status);
			String sSQLc = "Select * FROM carrinho WHERE Codigo='" + carrinhoEdit + "';";
			ResultSet resc = stmt.executeQuery(sSQLc);
			// executa sql para atualizar apos a exclusao
			soma = 0;
			Final_equipamento = 0;
			Final_maodeObra = 0;
			result = 0;
			while (resc.next()) {
				System.out.println(sSQLc);
				// pega valores referentes ao carrinho
				model.insertRow(contador,
						new Object[] { resc.getString("Modelo"), resc.getString("Categoria"), resc.getDouble("Qtd"),
								resc.getDouble("Unitario"), resc.getDouble("Frete"), resc.getDouble("Maodeobra"),
								resc.getDouble("Total") });
				tableCarrinho.setModel(model);

				Double qtd = resc.getDouble("Qtd");
				Double custo_uni = resc.getDouble("Unitario");
				Double mao_obra = resc.getDouble("Maodeobra");
				Double frete = resc.getDouble("Frete");
				Double custo_item = resc.getDouble("Total");
				soma += (custo_item + custo_uni);
				Final_equipamento += (qtd * custo_uni) + frete;
				Final_maodeObra += mao_obra * qtd;
				result += custo_item;
				Valor_totalEqp.setText(Double.valueOf(Final_equipamento).toString());
				Valor_total.setText(Double.valueOf(result).toString());
				Valor_totalMaodeobra.setText(Double.valueOf(Final_maodeObra).toString());
				contador += 1;

			}
			int contCli = comboBoxCli.getItemCount();
			for (int y = 0; y <= contCli; y++) {
				// for enquanto for menor que o count do combobox, pega o valor
				// na posição para realixar a compraração
				String compararC = comboBoxCli.getItemAt(y).toString();
				/// compara com o status no banco de dados
				if (cliente != null) {
					if (cliente.equals(compararC))
						;
					{
						// se for esse vai marcar a posição x
						comboBoxCli.setSelectedItem(cliente);
						y = contCli;
					}
				}
			}
			// Logica para setar o status
			// correto!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			int contTipo = comboBoxTipo.getItemCount();
			System.out.println(contTipo);
			for (int x = 0; x <= contTipo; x++) {
				// for enquanto for menor que o count do combobox, pega o valor
				// na posição para realixar a compraração
				String compararT = comboBoxTipo.getItemAt(x).toString();
				// compara com o status no banco de dados
				System.out.println(TipoOrc);
				System.out.println(compararT);
				if (TipoOrc != null) {
					if (TipoOrc.equals(compararT))
						;
					{
						// se for esse vai marcar a posição x
						comboBoxTipo.setSelectedItem(TipoOrc);
						x = contTipo;
					}
				}
			}
			// Logica para setar o status
			// cliente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			stmt.close();
			connect.fecharBDConn();
		} catch (Exception s) {
			JOptionPane.showMessageDialog(null, "Não foi possivel editar!");
			System.out.println("Ocorreu algum erro seu pedido não pode ser editado");
		}
	}

	public static void limpa_orcamento() {
		// limpa carrinho
		try {
			seleciona_tipo();
			seleciona_cliente();
			fechar_carrinho();
			while (model.getRowCount() > 0) {
				model.removeRow(0);
				// contador de linhas do carrinho
				contador -= 1;
			}
			chckbxGerar.setSelected(false);
			chckbxGerar.setEnabled(false);
			desativa_btns();
			rdbtnFechar.setSelected(false);
			fechar_carrinho();
			pagamentoObra.setSelectedItem("");
			pagamentoEquip.setSelectedItem("");
			soma = 0;
			Final_equipamento = 0;
			Final_maodeObra = 0;
			result = 0;
			JurosObra.setText("");
			MaoObraPaga.setText("00.0");
			JurosEquip.setText("00.0");
			labelVlfrete.setText("00.0");
			textLoc.setText("00.0");
			Valor_total.setText("00.0");
			Valor_totalEqp.setText("00.0");
			Valor_totalMaodeobra.setText("00.0");
			SimpleDateFormat sdf = new SimpleDateFormat();
			String criacao = sdf.format(data);
			DT_Orca.setText(criacao);
			CalcObra.setText("00.0");
			CalcOrca.setText("00.0");
			CalcProd.setText("00.0");
			comboBoxCategoria.setSelectedItem(null);
			comboBoxCli.setSelectedItem(null);
			comboBoxTipo.setSelectedItem(null);

		} catch (Exception e) {

		}
		// tableCarrinho.setModel(null);
		// model.removeTableModelListener(tableCarrinho);
	}

	public static void desativa_btns() {
		if (chckbxGerar.isSelected()) {
			btnPedido.setEnabled(true);
			//btnExportar.setEnabled(true);
			//btnGmail.setEnabled(true);
			btnCalcular.setEnabled(false);
		} else {
			btnPedido.setEnabled(false);
			//btnExportar.setEnabled(false);
			btnCalcular.setEnabled(true);
			//btnGmail.setEnabled(false);
		}
	}

	public static void fechar_carrinho() {
		if (rdbtnFechar.isSelected()) {
			// rdbtnCalcular.setVisible(true);
			btnAdicionar.setEnabled(false);
			btnRemover.setEnabled(false);
			JurosEquip.setEnabled(true);
			JurosObra.setEnabled(false);
			pagamentoEquip.setEnabled(true);
			pagamentoObra.setEnabled(true);
			btnCalcular.setEnabled(true);
			rdbtnTaxaDeLocomoo.setEnabled(true);
			// não esta tratado no objeto objeto
			// textLoc.setEnabled(true);
		} else {
			btnCalcular.setEnabled(false);
			btnAdicionar.setEnabled(true);
			rdbtnTaxaDeLocomoo.setEnabled(false);
			btnRemover.setEnabled(true);
			JurosEquip.setEnabled(true);
			pagamentoEquip.setEnabled(false);
			pagamentoObra.setEnabled(false);
			JurosObra.setEnabled(true);
			// não esta tratado no objeto objeto
			textLoc.setEnabled(false);
		}
	}

}
