package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame{

	private JPanel contentPane;
	
	//Relaciones con la logica
	private ControladorPrincipal controlador = new ControladorPrincipal(this);
	
	//Modelo
	private Modelo modelo;
	
	
	//Creacion de botones y demas para la ventana
	private JButton btnNuevaTransaccion= new JButton("Nuevo ingreso");
	private JButton btnRetiro= new JButton("Nuevo retiro");
	private JButton btnMisTransacciones = new JButton("Mis Transacciones");
	private JButton btnCategoria = new JButton("Agregar/Eliminar Categoria");
	private JButton btnCuenta= new JButton("Agregar/Eliminar Cuenta");
	private JButton btnReportes = new JButton ("Reportes");
	private JTable tblCuentas= new JTable(){public boolean isCellEditable(int rowIndec, int vColIndex) {
		return false;
	}
};
	private JScrollPane scroll=new JScrollPane();
	

	
	
	public VentanaPrincipal(Modelo m) {

		modelo=m;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1;
		c.weighty=1;
		
		
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=1;
		c.gridheight=2;
		c.gridwidth=1;
		scroll.setViewportView(tblCuentas);
		contentPane.add(scroll,c);

		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=3;
		c.gridheight=1;
		c.gridwidth=1;
		btnCategoria.addActionListener(controlador);
		contentPane.add(btnCategoria,c);
		
		
		c.gridx=1;
		c.gridy=1;
		c.gridheight=1;
		c.gridwidth=1;

		btnNuevaTransaccion.addActionListener (controlador);
		contentPane.add(btnNuevaTransaccion,c);
		
		c.gridx=1;
		c.gridy=3;
		c.gridheight=1;
		c.gridwidth=1;
		
		btnMisTransacciones.addActionListener(controlador);
		contentPane.add(btnMisTransacciones,c);
		
		c.gridx=1;
		c.gridy=4;
		c.gridheight=1;
		c.gridwidth=1;
		btnCuenta.addActionListener(controlador);
		contentPane.add(btnCuenta,c);
		
		c.gridx=1;
		c.gridy=2;
		c.gridheight=1;
		c.gridwidth=1;
		btnRetiro.addActionListener(controlador);
		contentPane.add(btnRetiro,c);
	
		c.gridx=0;
		c.gridy=4;
		c.gridheight=1;
		c.gridwidth=1;
		btnReportes.addActionListener(controlador);
		contentPane.add(btnReportes,c);

		
		
		
	}


	public JButton getBtnNuevaTransaccion() {
		// TODO Auto-generated method stub
		return btnNuevaTransaccion;
	}
	
	public JButton getBtnMisTransacciones() {
		return btnMisTransacciones;
	}
	
	public JButton getBtnCategoria() {
		return btnCategoria;
	}
	
	public JButton getBtnCuentas() {
		return btnCuenta;
	}
	
	public JButton getBtnRetiro() {
		return btnRetiro;
	}
	
	public JButton getBtnReportes() {
		return btnReportes;
	}
	
	public Modelo getModelo() {
		return modelo;
	}


	public JTable getTable() {
		// TODO Auto-generated method stub
		return tblCuentas;
	}
	
	public void setTable(JTable x) {
		tblCuentas=x;
		scroll.setViewportView(tblCuentas);
	}


	
}
