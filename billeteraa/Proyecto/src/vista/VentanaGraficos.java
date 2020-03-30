package vista;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaGraficos extends JFrame {

	//private ControladorGraficoTxPorCuenta controlador = new ControladorGraficoTxPorCuenta(this);
	private Modelo modelo;
	private JComboBox comboSeleccionGrafico;
	ControladorGraficos controlador = new ControladorGraficos(this);
	JButton boton= new JButton ("VOLVER");
	JLabel lblFiltro= new JLabel("Grafico:");

	Canvas lienzo;

	
	public VentanaGraficos(Modelo model) {
		
		
		this.modelo= model;


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lienzo=new Canvas();
		lienzo.setPreferredSize(new Dimension(this.getWidth()-50, this.getHeight()-50));
		JPanel panel = new JPanel();
		
		comboSeleccionGrafico=new JComboBox();
		comboSeleccionGrafico.addItem("Numero de transacciones por cuenta");
		comboSeleccionGrafico.addItem("Gastos de los ultimos 12 meses");
		comboSeleccionGrafico.addItem("Cantidad de Dinero por Cuenta");
		
		
		GridBagLayout layout =new GridBagLayout();
		panel.setLayout(layout);
		
		JPanel  combolbl= new JPanel();
		
		combolbl.add(lblFiltro);
		combolbl.add(comboSeleccionGrafico);
		
		comboSeleccionGrafico.addActionListener(controlador);
		boton.addActionListener(controlador);
		
		GridBagConstraints c = new GridBagConstraints();
		
		//c.fill=GridBagConstraints.BOTH;
		c.weightx=0;
		c.weighty=0;
		c.fill=GridBagConstraints.CENTER;
		c.gridx=0;
		c.gridy=0;
		c.gridheight=1;
		c.gridwidth=1;
		panel.add(combolbl,c);
		
		

		c.weightx=1;
		c.weighty=1;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=1;
		c.gridheight=1;
		c.gridwidth=1;
		panel.add(lienzo,c);
		
		c.weightx=0;
		c.weighty=0;
		c.fill=GridBagConstraints.BOTH;
		c.anchor=GridBagConstraints.WEST;
		c.gridx=0;
		c.gridy=3;
		c.gridheight=1;
		c.gridwidth=1;
		panel.add(boton,c);

		
		
		this.add(panel);
		

	}
public JComboBox getComboSeleccionGrafico() {
	return comboSeleccionGrafico;
}
	public Modelo getModelo() {
		return modelo;
	}


	public Canvas getLienzo() {
		return lienzo;
	}
	public JButton getBoton() {
		return boton;
	}
	

}
