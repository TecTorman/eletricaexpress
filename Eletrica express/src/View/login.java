package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

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
					login frame = new login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("EE - El\u00E9trica Express");
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(login.class.getResource("/img/electric-power-plug-icon-31.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 224);

		// JMenuBar menuBar = new JMenuBar();
		// setJMenuBar(menuBar);

		// JMenu mnAjuda = new JMenu("Ajuda");
		// menuBar.add(mnAjuda);

		// JMenuItem mntmSobre = new JMenuItem("Sobre");
		// mnAjuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(login.class.getResource("/img/logo1.png")));
		label.setBounds(45, 10, 114, 128);
		contentPane.add(label);

		JLabel lblNome = new JLabel("Usu\u00E1rio:");
		lblNome.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNome.setBounds(209, 49, 127, 20);
		contentPane.add(lblNome);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Verdana", Font.BOLD, 15));
		lblSenha.setBounds(209, 104, 87, 20);
		contentPane.add(lblSenha);

		textField = new JTextField();
		textField.setBounds(302, 49, 165, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnEntrar = new JButton("Entrar");
		// funcao que faz com que o enter acione o botao
		getRootPane().setDefaultButton(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((textField.getText()).equals("admin") && (passwordField.getText()).equals("admin")) {
					inicial.main(null);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
				}

			}
		});
		btnEntrar.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnEntrar.setBounds(367, 140, 63, 46);
		contentPane.add(btnEntrar);

		JLabel lblCersolucoeseletricasgmailcom = new JLabel("cersolucoeseletricas@gmail.com");
		lblCersolucoeseletricasgmailcom.setFont(new Font("Verdana", Font.BOLD, 10));
		lblCersolucoeseletricasgmailcom.setBounds(12, 150, 191, 18);
		contentPane.add(lblCersolucoeseletricasgmailcom);

		passwordField = new JPasswordField();
		passwordField.setBounds(301, 104, 166, 18);
		contentPane.add(passwordField);
	}
}
