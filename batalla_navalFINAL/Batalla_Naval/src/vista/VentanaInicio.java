package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaInicio extends JFrame{
	
	private JButton btnServidor;
	
	private JButton btnCliente;
	private Modelo modelo;
	private ControladorInicio controlador;
	
	public VentanaInicio(Modelo model) {
		modelo = model;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
		this.setLayout(new BorderLayout());
		this.setSize(400,200);
		JPanel botones = new JPanel();
			
		btnServidor = new JButton("Servidor");
		btnServidor.setBackground(Color.LIGHT_GRAY);
		btnServidor.setEnabled(true);
		btnServidor.addActionListener(getControlador());
		botones.add(btnServidor);
		
		btnCliente = new JButton("Cliente");
		btnCliente.setBackground(Color.LIGHT_GRAY);
		btnCliente.setEnabled(true);
		btnCliente.addActionListener(getControlador());
		botones.add(btnCliente);
		
		add(botones, BorderLayout.SOUTH);

		JLabel lblBatallaNaval = new JLabel("Bienvenido a Batalla Naval", SwingConstants.CENTER);
		lblBatallaNaval.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblBatallaNaval, BorderLayout.NORTH);
		JLabel lblInstruccion = new JLabel("Elige como quieres jugar", SwingConstants.CENTER);
		lblInstruccion.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(lblInstruccion, BorderLayout.CENTER);
		
	}

	public Modelo getModelo() {
		return modelo;
	}

	
	public ControladorInicio getControlador() {
		if(controlador == null) {
			return controlador = new ControladorInicio(this);
		}
		return controlador;
	}

	public JButton getBtnServidor() {
		return btnServidor;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}
	
}

