package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import Controler.control_orcamento;
import Controler.control_pedido;
import Model.pedido;
import View.*;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.UIManager;

public class agendar_pedido extends JFrame {
	JDateChooser dateChooser;
	private JPanel contentPane;

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
					agendar_pedido frame = new agendar_pedido();
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
	public agendar_pedido() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(agendar_pedido.class.getResource("/img/electric-power-plug-icon-31.png")));
		setTitle("EE - Agendamento");
		setResizable(false);
		setBounds(100, 100, 248, 182);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Agendar pedido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel.setBounds(33, 11, 175, 39);
		contentPane.add(lblNewLabel);

		JLabel lblAgendamento = new JLabel("Possivel atendimento:");
		lblAgendamento.setFont(new Font("Verdana", Font.PLAIN, 9));
		lblAgendamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgendamento.setBounds(0, 67, 135, 22);
		contentPane.add(lblAgendamento);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(137, 69, 95, 20);
		contentPane.add(dateChooser);

		JButton btnAgendar = new JButton("");
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					SimpleDateFormat sdtPedido = new SimpleDateFormat("yyyy-MM-dd");
					String data = sdtPedido.format(dateChooser.getDate());
					System.out.println(data);
					inicial.obj_pedido.setDt_Atend(data);

					System.out.println(inicial.obj_pedido.getDt_Atend());
					control_pedido c_pedido = new control_pedido();
					System.out.println(inicial.obj_pedido.getCliente());
					control_orcamento c_orcamento = new control_orcamento();
					c_orcamento.Orcamento_Pedido(inicial.obj_pedido.getCodigoOrca());
					c_pedido.insere_pedido(inicial.obj_pedido.getCodigoOrca(), inicial.obj_pedido.getStatus(),
							inicial.obj_pedido.getDt_Atend());
					inicial.atualiza_pedido();
					pedidos.main(null);
					// exibir_pedidos.setVisible(true);
					dispose();
				} catch (Exception e) {

				}
			}
		});
		btnAgendar.setIcon(new ImageIcon(agendar_pedido.class.getResource("/img/add1.png")));
		btnAgendar.setBounds(147, 101, 41, 41);
		contentPane.add(btnAgendar);

		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(agendar_pedido.class.getResource("/img/btnremove.png")));
		btnCancelar.setBounds(191, 101, 41, 41);
		contentPane.add(btnCancelar);
	}
}
