package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControladorAgregarCuenta implements ActionListener {
	
	
	private VentanaAgregarCuenta vAgregarC;
	
	ControladorAgregarCuenta( VentanaAgregarCuenta v){
		this.vAgregarC=v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==vAgregarC.getBtnRealizado()) {
			
			String cuenta = vAgregarC.getTextEntrada();
			if(cuenta.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese un valor");
			}
			else {
				vAgregarC.getModelo().crearTablaCuentas();
				vAgregarC.getModelo().insertarNuevaCuenta(vAgregarC.getTextEntrada());
				vAgregarC.dispose();
				vAgregarC.getModelo().iniciarPrincipal();
			}
	
		}
		
		if(e.getSource()==vAgregarC.getBtnVolver()) {
			vAgregarC.dispose();
			vAgregarC.getModelo().iniciarPrincipal();
			
		}
		
	}

}
