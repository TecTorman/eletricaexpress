package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connect.conexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.toedter.calendar.JDayChooser;

import Controler.control_pedido;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class pedidos extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JButton btnFinalizado;
	static int CodigoPendente;
	static JDateChooser de_data, ate_data;
	static String de_data_, ate_data_;

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
					pedidos frame = new pedidos();
					frame.setVisible(true);
					carregar_pedidos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public pedidos() {
		setResizable(false);
		setBackground(SystemColor.textText);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(pedidos.class.getResource("/img/electric-power-plug-icon-31.png")));
		setTitle("EE - El\u00E9trica Express");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.Metal.WindowsLookAndFeel"); // Windows
		} catch (Exception e) {
			// Inseira qualuquer codigo, se necessário.
		}
		setBounds(100, 100, 616, 401);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.GRAY);
		tabbedPane.setBackground(SystemColor.controlHighlight);
		tabbedPane.setBounds(10, 0, 584, 368);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBackground(SystemColor.controlHighlight);
		tabbedPane.addTab("Registro de Pedidos", new ImageIcon(pedidos.class.getResource("/img/pedido.png")), panel,
				null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 555, 183);
		panel.add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(false);
		scrollPane.setViewportView(table);

		btnFinalizado = new JButton("");
		btnFinalizado.setToolTipText("Alterar status para finalizado");
		btnFinalizado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				CodigoPendente = (int) table.getValueAt(linha, 0);
				String Status = "Finalizado";
				control_pedido.atualiza_pedido(CodigoPendente, Status);
				carregar_pedidos();
			}
		});
		btnFinalizado.setBounds(375, 205, 61, 60);
		btnFinalizado.setIcon(new ImageIcon(pedidos.class.getResource("/img/btnfeito.png")));
		panel.add(btnFinalizado);

		JButton btnPendente = new JButton("");
		btnPendente.setToolTipText("Alterar status para pendente");
		btnPendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				CodigoPendente = (int) table.getValueAt(linha, 0);
				String Status = "Pendente";
				control_pedido.atualiza_pedido(CodigoPendente, Status);
				carregar_pedidos();
			}
		});
		btnPendente.setBounds(440, 205, 61, 60);
		btnPendente.setIcon(new ImageIcon(pedidos.class.getResource("/img/btnexclamation.png")));
		panel.add(btnPendente);

		JButton btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Excluir pedido");
		btnExcluir.setBounds(504, 205, 61, 60);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Linha = table.getSelectedRow();
				int Cod = (int) table.getValueAt(Linha, 0);
				control_pedido.excluir_pedido(Cod);
				carregar_pedidos();
			}
		});
		btnExcluir.setIcon(new ImageIcon(pedidos.class.getResource("/img/delete.png")));
		panel.add(btnExcluir);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setForeground(Color.GRAY);
		tabbedPane_1.setBackground(UIManager.getColor("controlHighlight"));
		tabbedPane_1.setBounds(20, 205, 334, 103);
		panel.add(tabbedPane_1);

		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Pesquisa", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setFont(new Font("Verdana", Font.PLAIN, 9));
		lblPeriodo.setBounds(10, 11, 68, 14);
		panel_1.add(lblPeriodo);

		de_data = new JDateChooser();
		de_data.setBounds(67, 13, 95, 20);
		panel_1.add(de_data);

		JLabel label = new JLabel("\u00E1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Verdana", Font.PLAIN, 9));
		label.setBounds(168, 12, 20, 14);
		panel_1.add(label);

		ate_data = new JDateChooser();
		ate_data.setBounds(194, 13, 95, 20);
		panel_1.add(ate_data);

		JLabel label_1 = new JLabel("Status:");
		label_1.setFont(new Font("Verdana", Font.PLAIN, 9));
		label_1.setBounds(10, 43, 68, 14);
		panel_1.add(label_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "Pendente", "Finalizado" }));
		comboBox.setBounds(67, 43, 117, 20);
		panel_1.add(comboBox);

		JButton btnPesquisaData = new JButton("");
		btnPesquisaData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (de_data.getDate() != (null)) {
					SimpleDateFormat sdfPesquisa = new SimpleDateFormat("yyyy-MM-dd");
					String dedata = sdfPesquisa.format(de_data.getDate());
					de_data_ = dedata;
					System.out.println(dedata);

					String atedata = sdfPesquisa.format(ate_data.getDate());
					ate_data_ = atedata;
				}
				pesquisa_por_data(de_data_, ate_data_);
			}
		});
		btnPesquisaData.setIcon(new ImageIcon(pedidos.class.getResource("/img/lupa.png")));
		btnPesquisaData.setBounds(293, 11, 23, 23);
		panel_1.add(btnPesquisaData);

		JButton btnPesquisaStatus = new JButton("");
		btnPesquisaStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = comboBox.getSelectedItem().toString();
				pesquisa_por_status(status);
				comboBox.setSelectedItem(null);
			}
		});
		btnPesquisaStatus.setIcon(new ImageIcon(pedidos.class.getResource("/img/lupa.png")));
		btnPesquisaStatus.setBounds(190, 41, 23, 23);
		panel_1.add(btnPesquisaStatus);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(pedidos.class.getResource("/img/refesh.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carregar_pedidos();
			}
		});
		button.setToolTipText("Atualizar tabela");
		button.setBounds(504, 268, 61, 57);
		panel.add(button);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int linha = table.getSelectedRow();
					String solicitante = (String) table.getValueAt(linha, 2);
					solicitante += ".pdf";
					java.awt.Desktop.getDesktop().open(new File(solicitante));

				} catch (Exception e) {

				}
			}
		});
		button_1.setIcon(new ImageIcon(pedidos.class.getResource("/img/btnpdf.png")));
		button_1.setToolTipText("Abrir Orcamento");
		button_1.setBounds(440, 268, 61, 57);
		panel.add(button_1);
	}

	public static void carregar_pedidos() {
		conexao connect = new conexao();
		try {
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = "SELECT p.Codigo as N,p.Status, a.Nm_Cliente as Solicitante,a.Tipo,p.Dt_Atend as Agendado,a.VL_Obra as Instalacao,a.VL_Prod as Produtos,a.VL_Total as Total FROM orcamento a INNER JOIN pedido p ON a.Codigo=p.Codigo_Orca Order by p.Codigo desc;";
			System.out.println(sSQL);
			stmt.setMaxRows(100);
			ResultSet resp = stmt.executeQuery(sSQL);
			table.setModel(DbUtils.resultSetToTableModel(resp));
			stmt.close();
			connect.fecharBDConn();
		} catch (Exception e) {

		}
	}

	public static void pesquisa_por_data(String de_data_, String ate_data_) {
		conexao connect = new conexao();
		try {
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = "SELECT p.Codigo as N,p.Status, a.Nm_Cliente as Solicitante,a.Tipo,p.Dt_Atend as Agendado,a.VL_Obra as Instalacao,a.VL_Prod as Produtos,a.VL_Total as Total FROM orcamento a INNER JOIN pedido p ON a.Codigo=p.Codigo_Orca WHERE Dt_Atend BETWEEN ('"
					+ de_data_ + "') AND ('" + ate_data_ + "')";
			// String sSQL = "SELECT p.Codigo as N,p.Status, a.Nm_Cliente as
			// Solicitante,a.Tipo,p.Dt_Atend as Agendado,a.VL_Obra as
			// Instalacao,a.VL_Prod as Produtos,a.VL_Total as Total FROM agenda
			// a INNER JOIN pedido p ON a.Codigo=p.Codigo_Orca WHERE DATE
			// p.Dt_Atend >='"
			// + de_data_ + "' and Date p.Dt_Atend <='" + ate_data_ + "' order
			// by p.Codigo desc;";

			System.out.println(sSQL);
			de_data.setDate(null);
			ate_data.setDate(null);
			stmt.setMaxRows(100);
			ResultSet resp = stmt.executeQuery(sSQL);
			table.setModel(DbUtils.resultSetToTableModel(resp));
			stmt.close();
			connect.fecharBDConn();
		} catch (Exception e) {

		}

	}

	public static void pesquisa_por_status(String status) {
		conexao connect = new conexao();
		try {
			Connection ExConn = (Connection) connect.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = "SELECT p.Codigo as N,p.Status, a.Nm_Cliente as Solicitante,a.Tipo,p.Dt_Atend as Agendado,a.VL_Obra as Instalacao,a.VL_Prod as Produtos,a.VL_Total as Total FROM orcamento a INNER JOIN pedido p ON a.Codigo=p.Codigo_Orca  WHERE p.Status='"
					+ status + "' order by p.Codigo desc;";
			System.out.println(sSQL);
			de_data.setDate(null);
			ate_data.setDate(null);
			stmt.setMaxRows(100);
			ResultSet resp = stmt.executeQuery(sSQL);
			table.setModel(DbUtils.resultSetToTableModel(resp));
			stmt.close();
			connect.fecharBDConn();
		} catch (Exception e) {

		}
	}
}
