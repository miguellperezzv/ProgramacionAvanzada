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

public class VentanaCrearBarco extends JFrame{
	
	private JButton btnConectar;
	private Modelo modelo;
	Object[][] botones= new Object[11][11];
	private int[][] matrizCreacion= new int[11][11];
	private ControladorCrearBarco controlador;
	
	public VentanaCrearBarco(Modelo model) {
		modelo = model;
		//inicia en ceros la matrizCreacion
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				matrizCreacion[i][j]=0;
			}

		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
		this.setLayout(new BorderLayout());
		this.setSize(800,500);
		
		
		 
		//Creacion del tablero 1
		JPanel tablero = new JPanel();
		tablero.setLayout(new GridLayout(11, 11));
		
		for (int i=0 ; i<botones.length;i++) {
			for (int k=0 ; k<botones.length; k++) {
				if(i==0) {
					botones[i][k]=new JLabel(Integer.toString(k), SwingConstants.CENTER);
					tablero.add((JLabel) botones[i][k]);

				}else if(k==0){
					botones[i][k]=new JLabel(Integer.toString(i), SwingConstants.CENTER);
					tablero.add((JLabel) botones[i][k]);
				}else {
				botones[i][k]= new JButton();
				((JButton) botones[i][k]).setBackground(Color.white);
				tablero.add((JButton) botones[i][k]);
				((JButton)botones[i][k]).addActionListener(getControlador());
				((JButton)botones[i][k]).addMouseListener(getControlador());

				}

			}
		}
		((JLabel)botones[0][0]).setText("");
		
		
		
		
		
		//Se agrega EL TABLERO
		JPanel tableros= new JPanel(new GridLayout());
		tableros.add(tablero);
		
		this.add(tableros, BorderLayout.CENTER);
		
		
		btnConectar = new JButton("Conectar");
		btnConectar.setBackground(Color.LIGHT_GRAY);
		btnConectar.setEnabled(true);
		btnConectar.addActionListener(getControlador());
		this.add(btnConectar, BorderLayout.SOUTH);
		
		//TITULO
		JLabel lblBatallaNaval = new JLabel("Ubica tus tropas", SwingConstants.CENTER);
		lblBatallaNaval.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.add(lblBatallaNaval, BorderLayout.NORTH);
		
	}

	public JButton getBoton(int i, int j) {
		// TODO Auto-generated method stub
		return (JButton) botones[i][j];
	}
	
	int getElementoMatriz(int x, int y){
		return matrizCreacion[x][y];
	}

	public Modelo getModelo() {
		return modelo;
	}
	public Object[][] getTableroBotones() {
		return botones;
	}
	public ControladorCrearBarco getControlador() {
		if(controlador == null)
			return controlador = new ControladorCrearBarco(this);
		return controlador;
	}

	JButton getBtnConectar() {
		// TODO Auto-generated method stub
		return this.btnConectar;
	}

	public void setMatrizCreacion(int i, int j, int k) {
		matrizCreacion[i][j]=k;		
	}

	public int[][] getMatrizCreacion() {
		// TODO Auto-generated method stub
		return matrizCreacion;
	}

	public int getMatrizCreacion(int i, int j) {
		// TODO Auto-generated method stub
		return matrizCreacion[i][j];
	}
}
