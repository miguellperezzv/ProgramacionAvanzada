package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class VentanaJuego extends JFrame {

	private JPanel contentPane;
	private ControladorJuego controlador = new ControladorJuego(this);
	int matrizJuego[][] = {{0,0,0},{0,0,0},{0,0,0}};

	private Modelo model;
	
	JButton btnJuego00 = new JButton();
	JButton btnJuego01 = new JButton();
	JButton btnJuego02 = new JButton();
	JButton btnJuego10 = new JButton();
	JButton btnJuego11 = new JButton();
	JButton btnJuego12 = new JButton();
	JButton btnJuego20 = new JButton();
	JButton btnJuego21 = new JButton();
	JButton btnJuego22 = new JButton();
	JLabel lblTurno ;
	JLabel lblTitulo;
	JButton btnColorI = new JButton();
	JButton btnColorD = new JButton();

	Image circulo;
	Image equis;


	public VentanaJuego(Modelo m) {
		model=m;
		
		this.setLayout(new BorderLayout());
		
		lblTurno= new JLabel("Pruebas", SwingConstants.CENTER);
		lblTitulo= new JLabel("Triqui", SwingConstants.CENTER);

		
		JPanel botonesTriqui = new JPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		botonesTriqui.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_btnJuego00 = new GridBagConstraints();
		gbc_btnJuego00.gridx = 0;
		gbc_btnJuego00.gridy = 0;
		gbc_btnJuego00.weightx=1;
		gbc_btnJuego00.weighty=1;
		gbc_btnJuego00.fill=GridBagConstraints.BOTH;
		botonesTriqui.add(btnJuego00, gbc_btnJuego00);
		
		GridBagConstraints gbc_btnJuego01 = new GridBagConstraints();
		gbc_btnJuego01.gridx = 1;
		gbc_btnJuego01.gridy = 0;
		gbc_btnJuego01.weightx=1;
		gbc_btnJuego01.weighty=1;
		gbc_btnJuego01.fill=GridBagConstraints.BOTH;
		botonesTriqui.add(btnJuego01, gbc_btnJuego01);
		
		GridBagConstraints gbc_btnJuego02 = new GridBagConstraints();
		gbc_btnJuego02.gridx = 2;
		gbc_btnJuego02.gridy = 0;
		gbc_btnJuego02.weightx=1;
		gbc_btnJuego02.weighty=1;
		gbc_btnJuego02.fill=GridBagConstraints.BOTH;

		botonesTriqui.add(btnJuego02, gbc_btnJuego02);
		
		GridBagConstraints gbc_btnJuego10 = new GridBagConstraints();
		gbc_btnJuego10.gridx = 0;
		gbc_btnJuego10.gridy = 1;
		gbc_btnJuego10.weightx=1;
		gbc_btnJuego10.weighty=1;
		gbc_btnJuego10.fill=GridBagConstraints.BOTH;

		botonesTriqui.add(btnJuego10, gbc_btnJuego10);
		
		GridBagConstraints gbc_btnJuego11 = new GridBagConstraints();
		gbc_btnJuego11.gridx = 1;
		gbc_btnJuego11.gridy = 1;
		gbc_btnJuego11.weightx=1;
		gbc_btnJuego11.weighty=1;
		gbc_btnJuego11.fill=GridBagConstraints.BOTH;

		botonesTriqui.add(btnJuego11, gbc_btnJuego11);
		
		GridBagConstraints gbc_btnJuego12 = new GridBagConstraints();
		gbc_btnJuego12.gridx = 2;
		gbc_btnJuego12.gridy = 1;
		gbc_btnJuego12.weightx=1;
		gbc_btnJuego12.weighty=1;
		gbc_btnJuego12.fill=GridBagConstraints.BOTH;

		botonesTriqui.add(btnJuego12, gbc_btnJuego12);
		

		GridBagConstraints gbc_btnJuego20 = new GridBagConstraints();
		gbc_btnJuego20.gridx = 0;
		gbc_btnJuego20.gridy = 2;
		gbc_btnJuego20.weightx=1;
		gbc_btnJuego20.weighty=1;
		gbc_btnJuego20.fill=GridBagConstraints.BOTH;

		botonesTriqui.add(btnJuego20, gbc_btnJuego20);
		
		GridBagConstraints gbc_btnJuego21 = new GridBagConstraints();
		gbc_btnJuego21.gridx = 1;
		gbc_btnJuego21.gridy = 2;
		gbc_btnJuego21.weightx=1;
		gbc_btnJuego21.weighty=1;
		gbc_btnJuego21.fill=GridBagConstraints.BOTH;

		botonesTriqui.add(btnJuego21, gbc_btnJuego21);
		
		GridBagConstraints gbc_btnJuego22 = new GridBagConstraints();
		gbc_btnJuego22.gridx = 2;
		gbc_btnJuego22.gridy = 2;
		gbc_btnJuego22.weightx=1;
		gbc_btnJuego22.weighty=1;
		gbc_btnJuego22.fill=GridBagConstraints.BOTH;

		botonesTriqui.add(btnJuego22, gbc_btnJuego22);
			
		
		//Imagenes de las selecciones
	    try {
			circulo = ImageIO.read(new File("img/circulo.png"));
			equis = ImageIO.read(new File("img/equis.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		this.getContentPane().add(botonesTriqui, BorderLayout.CENTER);
		this.getContentPane().add(lblTurno , BorderLayout.SOUTH);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);
		this.getContentPane().add(btnColorD, BorderLayout.EAST);
		this.getContentPane().add(btnColorI, BorderLayout.WEST);

		//this.getContentPane().add(btnColor, BorderLayout.WEST);

		
		//Color por defecto indicadores
		btnColorI.setBackground(Color.gray);
		btnColorD.setBackground(Color.gray);
		//Margen del titulo y letra
		Border margin = new EmptyBorder(40,40,40,10);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 40));
		lblTitulo.setBorder(margin);
		
		//Margen del turno y letra
		margin = new EmptyBorder(40,40,80,40);
		lblTurno.setFont(new Font("Arial", Font.PLAIN, 30));
		lblTurno.setBorder(margin);
		
		//margen botones
		margin = new EmptyBorder(0,80,0,80);
		botonesTriqui.setBorder(margin);
		
		//se agregan controladorWes
		btnJuego00.addActionListener(controlador);
		btnJuego01.addActionListener(controlador);
		btnJuego02.addActionListener(controlador);
		btnJuego10.addActionListener(controlador);
		btnJuego11.addActionListener(controlador);
		btnJuego12.addActionListener(controlador);
		btnJuego20.addActionListener(controlador);
		btnJuego20.addActionListener(controlador);
		btnJuego21.addActionListener(controlador);
		btnJuego22.addActionListener(controlador);


		//Fondo blanco para los botones
		btnJuego00.setBackground(Color.white);
		btnJuego01.setBackground(Color.white);
		btnJuego02.setBackground(Color.white);
		btnJuego10.setBackground(Color.white);
		btnJuego11.setBackground(Color.white);
		btnJuego12.setBackground(Color.white);
		btnJuego20.setBackground(Color.white);
		btnJuego21.setBackground(Color.white);
		btnJuego22.setBackground(Color.white);
		
		

	}
	
	public JButton getBotonJuego(int x, int y) {
		
		if(x==0 && y==0) {
			return btnJuego00;
		}else if (x==0 && y==1) {
			return btnJuego01;
		}
		else if (x==0 && y==2) {
			return btnJuego02;
		}else if (x==1 && y==0) {
			return btnJuego10;
		}else if (x==1 && y==1) {
			return btnJuego11;
		}
		else if (x==1 && y==2) {
			return btnJuego12;
		}else if (x==2 && y==0) {
			return btnJuego20;
		}else if (x==2 && y==1) {
			return btnJuego21;
		}else if (x==2 && y==2) {
			return btnJuego22;
		}
		return null;
		
		
		
	}
	
	public int[][] getMatrizJuego(){
		return matrizJuego;
		
	}
	
	public Modelo getModelo() {
		return model;
	}

	public JLabel getlblTurno() {
		// TODO Auto-generated method stub
		return lblTurno;
	}

	public void modificarMatriz(int x, int y, int valor) {
		// TODO Auto-generated method stub
		this.matrizJuego[x][y]=valor;
	}
	
	public void setMatrizJuego(int [][] m) {
		// TODO Auto-generated method stub
		this.matrizJuego = m;
	}
	
	//El setIconolocal
	public void setIcono(int x, int y) {
		
		//getModelo().setLado(1);
		if (getModelo().getLado()==1) {
			if(x==0 && y==0)
				btnJuego00.setIcon(new ImageIcon(circulo));
					else if(x==0 && y==1)
						btnJuego01.setIcon(new ImageIcon(circulo));
						else if(x==0 && y==2)
							btnJuego02.setIcon(new ImageIcon(circulo));
							else if(x==1 && y==0)
								btnJuego10.setIcon(new ImageIcon(circulo));
								else if(x==1 && y==1)
									btnJuego11.setIcon(new ImageIcon(circulo));
									else if(x==1 && y==2)
										btnJuego12.setIcon(new ImageIcon(circulo));
										else if(x==2 && y==0)
											btnJuego20.setIcon(new ImageIcon(circulo));
											else if(x==2 && y==1)
												btnJuego21.setIcon(new ImageIcon(circulo));
												else if(x==2 && y==2)
													btnJuego22.setIcon(new ImageIcon(circulo));
													
		}else if (this.getModelo().getLado()==2) {
			if(x==0 && y==0) 
				btnJuego00.setIcon(new ImageIcon(equis));
			else if(x==0 && y==1)
						btnJuego01.setIcon(new ImageIcon(equis));
						else if(x==0 && y==2)
							btnJuego02.setIcon(new ImageIcon(equis));
							else if(x==1 && y==0)
								btnJuego10.setIcon(new ImageIcon(equis));
								else if(x==1 && y==1)
									btnJuego11.setIcon(new ImageIcon(equis));
									else if(x==1 && y==2)
										btnJuego12.setIcon(new ImageIcon(equis));
										else if(x==2 && y==0)
											btnJuego20.setIcon(new ImageIcon(equis));
											else if(x==2 && y==1)
												btnJuego21.setIcon(new ImageIcon(equis));
												else if(x==2 && y==2)
													btnJuego22.setIcon(new ImageIcon(equis));
													
		}

	}
	//El setIconoRemoto
public void setIcono(int x, int y, int lado) {
		
		if (lado==1) {
			if(x==0 && y==0)
				btnJuego00.setIcon(new ImageIcon(circulo));
					else if(x==0 && y==1)
						btnJuego01.setIcon(new ImageIcon(circulo));
						else if(x==0 && y==2)
							btnJuego02.setIcon(new ImageIcon(circulo));
							else if(x==1 && y==0)
								btnJuego10.setIcon(new ImageIcon(circulo));
								else if(x==1 && y==1)
									btnJuego11.setIcon(new ImageIcon(circulo));
									else if(x==1 && y==2)
										btnJuego12.setIcon(new ImageIcon(circulo));
										else if(x==2 && y==0)
											btnJuego20.setIcon(new ImageIcon(circulo));
											else if(x==2 && y==1)
												btnJuego21.setIcon(new ImageIcon(circulo));
												else if(x==2 && y==2)
													btnJuego22.setIcon(new ImageIcon(circulo));
													
		}else if (lado==2) {
			if(x==0 && y==0) 
				btnJuego00.setIcon(new ImageIcon(equis));
			else if(x==0 && y==1)
						btnJuego01.setIcon(new ImageIcon(equis));
						else if(x==0 && y==2)
							btnJuego02.setIcon(new ImageIcon(equis));
							else if(x==1 && y==0)
								btnJuego10.setIcon(new ImageIcon(equis));
								else if(x==1 && y==1)
									btnJuego11.setIcon(new ImageIcon(equis));
									else if(x==1 && y==2)
										btnJuego12.setIcon(new ImageIcon(equis));
										else if(x==2 && y==0)
											btnJuego20.setIcon(new ImageIcon(equis));
											else if(x==2 && y==1)
												btnJuego21.setIcon(new ImageIcon(equis));
												else if(x==2 && y==2)
													btnJuego22.setIcon(new ImageIcon(equis));
													
		}

	}
	public void setTurno(boolean esTuTurno) {
		if (esTuTurno==true) {
			btnColorI.setBackground(Color.green);
			btnColorD.setBackground(Color.green);
			lblTurno.setText("Es tu turno, juega");
			model.desbloquearBotones();
		}else {
			btnColorI.setBackground(Color.red);
			btnColorD.setBackground(Color.red);
			lblTurno.setText("Es el turno del otro");
			model.bloquearBotones();

		}
			
	}

}
