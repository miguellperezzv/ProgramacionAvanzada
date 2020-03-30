package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

public class VentanaAgregarCuenta extends JFrame {

	private JPanel contentPane;
	private JTextField textEntrada;
	private JLabel lblTitulo = new JLabel("Selecione el Nombre de la CUENTA a Agregar o Eliminar");
	private JButton btnVolver = new JButton("Volver");
	private JButton btnRealizado = new JButton("Realizado");
	private Modelo m;
	private ControladorAgregarCuenta controlador = new ControladorAgregarCuenta(this);
	
	/**
	 * Create the frame.
	 */
	public VentanaAgregarCuenta(Modelo modelo) {
		m=modelo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblTitulo.setBounds(10, 11, 437, 21);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTitulo);
		
		
		btnVolver.setBounds(10, 99, 88, 23);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(controlador);
		
		btnRealizado.setBounds(348, 99, 99, 23);
		contentPane.add(btnRealizado);
		btnRealizado.addActionListener(controlador);
		
		textEntrada = new JTextField();
		textEntrada.setBounds(107, 59, 234, 23);
		contentPane.add(textEntrada);
		textEntrada.setColumns(10);
	}
	
	public JButton getBtnVolver() {
		return btnVolver;
	}
	
	public JButton getBtnRealizado() {
		return btnRealizado;
	}
	
	public String getTextEntrada() {
		return textEntrada.getText();
	}
	
	public Modelo getModelo() {
		return m;
	}
}
