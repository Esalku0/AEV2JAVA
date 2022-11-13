package acts;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.security.auth.login.LoginContext;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JTextField txtConsulta;
	private JButton btnEjecutarConsulta;
	private JTextField txtUsuari;
	private JButton btnLogin;
	private JTextArea txtContenido;
	private JButton btnMostrarTablas;
	private JButton btnEstructuraTabla;
	private JTextField txtNombreTabla;
	private JScrollPane scrollPane;
	private JButton btnCerrarConexion;
	private JLabel lblContrasenya;
	private JLabel lblTablaInfo;
	private JPasswordField txtContrasenya;
	private JLabel lblUsuari;

	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 121, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtConsulta = new JTextField();
		txtConsulta.setBounds(252, 29, 414, 37);
		contentPane.add(txtConsulta);
		txtConsulta.setColumns(10);

		btnEjecutarConsulta = new JButton("EXECUTAR");
		btnEjecutarConsulta.setBounds(676, 29, 105, 37);
		contentPane.add(btnEjecutarConsulta);
		
		scrollPane = new JScrollPane();
		getScrollPane().setBounds(252, 77, 529, 274);
		contentPane.add(getScrollPane());

		txtContenido = new JTextArea();
		getScrollPane().setViewportView(txtContenido);

		txtUsuari = new JTextField();
		txtUsuari.setBounds(90, 80, 118, 20);
		contentPane.add(txtUsuari);
		txtUsuari.setColumns(10);

		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(100, 142, 98, 37);
		contentPane.add(btnLogin);
		btnLogin.setHideActionText(true);
		
		 btnMostrarTablas = new JButton("Mostrar Taules");
		btnMostrarTablas.setBounds(653, 394, 128, 37);
		contentPane.add(btnMostrarTablas);
		
		btnEstructuraTabla = new JButton("Taula info");
		btnEstructuraTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEstructuraTabla.setBounds(100, 266, 98, 37);
		contentPane.add(btnEstructuraTabla);
		
		txtNombreTabla = new JTextField();
		txtNombreTabla.setColumns(10);
		txtNombreTabla.setBounds(90, 235, 118, 20);
		contentPane.add(txtNombreTabla);
		
		 btnCerrarConexion = new JButton("TANCAR CONEXIÓ");
		 getBtnCerrarConexion().setForeground(Color.RED);
		 getBtnCerrarConexion().setBackground(Color.WHITE);
		getBtnCerrarConexion().setBounds(67, 394, 149, 37);
		contentPane.add(getBtnCerrarConexion());
		
		 lblUsuari = new JLabel("Usuari:");
		lblUsuari.setBounds(42, 83, 46, 14);
		contentPane.add(lblUsuari);
		
		lblContrasenya = new JLabel("Contrasenya:");
		lblContrasenya.setHorizontalAlignment(SwingConstants.TRAILING);
		lblContrasenya.setBounds(10, 114, 78, 14);
		contentPane.add(lblContrasenya);
		
		lblTablaInfo = new JLabel("Nom taula:");
		getLblTablaInfo().setHorizontalAlignment(SwingConstants.TRAILING);
		getLblTablaInfo().setBounds(14, 238, 66, 14);
		contentPane.add(getLblTablaInfo());
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setBounds(90, 111, 118, 20);
		contentPane.add(txtContrasenya);
		
		
		setVisible(true);
		txtConsulta.setVisible(false);
		btnEstructuraTabla.setVisible(false);
		getScrollPane().setVisible(false);
		getLblTablaInfo().setVisible(false);
		txtNombreTabla.setVisible(false);
		txtContenido.setVisible(false);
		btnEjecutarConsulta.setVisible(false);
		btnMostrarTablas.setVisible(false);
		getBtnCerrarConexion().setVisible(false);
	}


	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTxtConsulta() {
		return txtConsulta;
	}

	public JButton getBtnEjecutarConsulta() {
		return btnEjecutarConsulta;
	}

	public JTextField getTxtUsuari() {
		return txtUsuari;
	}

	public JTextField getTxtContrasenya() {
		return txtContrasenya;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JTextArea getTxtContenido() {
		return txtContenido;
	}
	
	public JButton getBtnMostrarTablas() {
		return btnMostrarTablas;
	}

	public JButton getBtnEstructuraTabla() {
		return btnEstructuraTabla;
	}

	public JTextField getTxtNombreTabla() {
		return txtNombreTabla;
	}
	public JButton getbtnCerrarConexion() {
		return getBtnCerrarConexion();
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public JLabel getLblTablaInfo() {
		return lblTablaInfo;
	}

	public JButton getBtnCerrarConexion() {
		return btnCerrarConexion;
	}
	public JLabel getLblUsuari() {
		return lblUsuari;
	}
	public JLabel getLblPass() {
		return lblContrasenya;
	}
}
