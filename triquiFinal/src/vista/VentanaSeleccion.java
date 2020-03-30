package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class VentanaSeleccion extends JFrame {

	private Modelo modelo;

	JButton cliente = new JButton();
	JButton servidor = new JButton();
	JLabel estadoConexion;
	JLabel estadoText;
	JLabel puntoEstado;
	JPanel estado;
	Image sinConexion=null;
	Image esperandoConexion=null;
	Image conectado=null;




	// Relaciones con la logica
	private ControladorSeleccion controlador = new ControladorSeleccion(this);

	// Modelo

	public VentanaSeleccion(Modelo m) {
		modelo = m;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Layout del jframe
		this.setLayout(new BorderLayout());
		
		
		JPanel botones = new JPanel();
		 estado = new JPanel();
		//Label con palabra estado
		Border margin = new EmptyBorder(5,0,10,0);
		puntoEstado= new JLabel();
		puntoEstado.setBorder(margin);
		
		try {
			sinConexion = ImageIO.read(new File("img/sinConexion.png"));
			esperandoConexion = ImageIO.read(new File("img/esperandoConexion.png"));
			conectado = ImageIO.read(new File("img/conectado.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		puntoEstado.setIcon(new ImageIcon(sinConexion));
	     
	    estado.add(puntoEstado);
		estadoText= new JLabel("Estado: ", SwingConstants.CENTER);
		estadoText.setBorder(margin);
		//Label con estado de la coneccion
		estadoConexion=new JLabel("servidor apagado.", SwingConstants.CENTER);
		estadoConexion.setBorder(margin);
		
		estado.add(estadoText);
		estado.add(estadoConexion);
		
		//Boton cliente
		cliente.setText("Conectar a una partida");
		cliente.addActionListener(controlador);
		botones.add(cliente);

		//Boton servidor
		servidor.setText("Crear una partida");
		servidor.addActionListener(controlador);
		botones.add(servidor);
		
		this.add(botones, BorderLayout.CENTER);
		this.add(estado , BorderLayout.SOUTH);

		
		
	}
	
	public void setEstado(String estado) {
		
		estadoConexion.setText(estado);
		
	}

	public JButton getBotonServidor() {
		// TODO Auto-generated method stub
		return servidor;
	}
	public JButton getBotonCliente() {
		// TODO Auto-generated method stub
		return cliente;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
	public void setColorEstado(int no0Esperando1Conectado2) {
		if (no0Esperando1Conectado2==0)
			puntoEstado.setIcon(new ImageIcon(sinConexion));
		else if(no0Esperando1Conectado2==1)
			puntoEstado.setIcon(new ImageIcon(esperandoConexion));
			else if(no0Esperando1Conectado2==2)
				puntoEstado.setIcon(new ImageIcon(conectado));

			
	}
	
   
	
}
