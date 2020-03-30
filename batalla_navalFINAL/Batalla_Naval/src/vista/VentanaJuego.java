package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;

public class VentanaJuego extends JFrame {

	private Modelo sistema;
	private JButton btnColorizq;
	private JButton btnColorder;
	private JLabel lblDeQuienEs;
	private ControladorJuego controlador;
	private Object[][] botones= new Object[11][11];
	private Object[][] botones2= new Object[11][11];
	private int mapa1[][] = new int[11][11];
	private int mapa2[][] = new int[11][11];

	public VentanaJuego(Modelo model) {
		sistema = model;
		//matrizJuegoenCeros
		for(int i=1;i<11;i++) {
			for(int j=1;j<11;j++) {
				mapa2[i][j]=0;
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
		this.setLayout(new BorderLayout());
		this.setSize(850,550);
		
		
		 
		//Creacion del tablero 1
		JPanel tablero = new JPanel();
		tablero.setLayout(new GridLayout(11, 11));
		
		for (int i=0 ; i<botones.length;i++) {
			for (int k=0 ; k<botones.length; k++) {
				if(i==0) {
					botones[i][k]=new JLabel(Integer.toString(k), SwingConstants.CENTER);
					tablero.add((JLabel) botones[i][k]);
					mapa1[i][k]=0;
				}else if(k==0){
					botones[i][k]=new JLabel(Integer.toString(i), SwingConstants.CENTER);
					tablero.add((JLabel) botones[i][k]);
					mapa1[i][k]=0;
				}else {
				botones[i][k]= new JButton();
				//((JButton) botones[i][k]).setBackground(Color.white);
				tablero.add((JButton) botones[i][k]);
				mapa1[i][k]=1;
				((JButton)botones[i][k]).addMouseListener(getControlador());	
				}

			}
		}
		
		((JLabel)botones[0][0]).setText("");						
		
		//Creacion del tablero 2
		JPanel tablero2 = new JPanel();
		tablero2.setLayout(new GridLayout(11, 11));
		
		for (int i=0 ; i<botones2.length;i++) {
			for (int k=0 ; k<botones2.length; k++) {
				if(i==0) {
					botones2[i][k]=new JLabel(Integer.toString(k), SwingConstants.CENTER);
					tablero2.add((JLabel) botones2[i][k]);
					mapa2[i][k]=0;

				}else if(k==0){
					botones2[i][k]=new JLabel(Integer.toString(i), SwingConstants.CENTER);
					tablero2.add((JLabel) botones2[i][k]);
					mapa2[i][k]=0;
				}else {
				botones2[i][k]= new JButton();
				//((JButton) botones2[i][k]).setBackground(Color.white);
				mapa2[i][k]=1;
				tablero2.add((JButton) botones2[i][k]);
				((JButton)botones2[i][k]).addActionListener(getControlador());
				((JButton)botones2[i][k]).addMouseListener(getControlador());	
				}

			}
		}
		((JLabel)botones2[0][0]).setText("");
		
		//Se agregan los dos tableros
		JPanel ambosTableros= new JPanel(new GridLayout());
		ambosTableros.add(tablero);
		ambosTableros.add(tablero2);
		this.add(ambosTableros, BorderLayout.CENTER);
		
		//Indicador turno izq
		btnColorizq = new JButton();
		btnColorizq.setBackground(Color.LIGHT_GRAY);
		btnColorizq.setEnabled(false);
		this.add(btnColorizq, BorderLayout.WEST);
		
		//Indicador turno der
		btnColorder = new JButton();
		btnColorder.setBackground(Color.LIGHT_GRAY);
		btnColorder.setEnabled(false);
		this.add(btnColorder, BorderLayout.EAST);
		
		//TITULO
		JLabel lblBatallaNaval = new JLabel("BATALLA NAVAL", SwingConstants.CENTER);
		lblBatallaNaval.setFont(new Font("SansSerif", Font.BOLD, 36));
		this.add(lblBatallaNaval, BorderLayout.NORTH);
		
		//Indicador de quien es el turno
		
		JPanel inferior= new JPanel();
		
		
		lblDeQuienEs = new JLabel("De quien es el turno", SwingConstants.CENTER);
		lblDeQuienEs.setFont(new Font("DialogInput", Font.ITALIC, 23));
		this.add(lblDeQuienEs, BorderLayout.SOUTH);
		addMouseListener(getControlador());
		inferior.add(lblDeQuienEs);
		
		this.add(inferior, BorderLayout.SOUTH);
		
	}
	/**
	 * 
	 * @param x posicion x
	 * @param y posicion y
	 * @return el boton del tablero 1 (izq) pedido
	 */
	public JButton getBotonTablero1(int x, int y) {
		
		return (JButton)botones[x][y];
		
		
	}
	/**
	 * 
	 * @param x posicion x
	 * @param y	 posicion y
	 * @return el boton del tablero 2 (der) pedido
	 */
	public JButton getBotonTablero2(int x, int y) {
		return (JButton)botones2[x][y];
	}
	

	public void setTurno(boolean siono) {
		/*private JButton btnColorizq;
		private JButton btnColorder;
		private JLabel lblDeQuienEs;*/
		if(siono==true) {
			btnColorizq.setBackground(Color.green);
			btnColorder.setBackground(Color.green);
			lblDeQuienEs.setText("Es tu turno, selecciona una casilla");
		}else if(siono==false) {
			btnColorizq.setBackground(Color.red);
			btnColorder.setBackground(Color.red);
			lblDeQuienEs.setText("Espera, es el turno de tu oponente");

		}
	}
	
	public Modelo getSistema() {
		return sistema;
	}
	
	public ControladorJuego getControlador() {
		if(controlador==null)
			return controlador = new ControladorJuego(this);
		return controlador;
	}
	public int[][] getMapa1() {
		return mapa1;
	}
	public int[][] getMapa2() {
		return mapa2;
	}
	public void setMapa1(int[][] mapa1) {
		this.mapa1 = mapa1;
	}
	
	public void setMapa2(int[][] mapa2) {
		this.mapa2 = mapa2;
	}

	public Object[][] getTablero1(){
		return botones;
	}
	public Object[][] getTablero2(){
		return botones2;
	}
	public void desactivarBoton(int x, int y) {
		((JButton)botones2[x][y]).setEnabled(false);
		
	}


}


