package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControladorAgregarCategoria implements ActionListener {

	
	private VentanaAgregarCategoria vAgregarCategoria;
	
	ControladorAgregarCategoria (VentanaAgregarCategoria v){
		
		this.vAgregarCategoria=v;
		
		
		
	}
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==vAgregarCategoria.getBtnVolver()) {
			
			
				vAgregarCategoria.dispose();
				vAgregarCategoria.getModelo().iniciarPrincipal();
			
			
		}
		
		if(e.getSource()==vAgregarCategoria.getBtnRealizado()) {
			
String categoria = vAgregarCategoria.getTextEntrada();
			
			if(categoria.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese un nombre");
			}
			else {
			vAgregarCategoria.getModelo().crearTablaCategorias();
			vAgregarCategoria.getModelo().insertarNuevaCategoria(vAgregarCategoria.getTextEntrada());
			vAgregarCategoria.dispose();
			vAgregarCategoria.getModelo().iniciarPrincipal();
			}
		}

	}
}
