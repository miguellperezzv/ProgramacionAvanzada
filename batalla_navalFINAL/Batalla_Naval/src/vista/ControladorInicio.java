package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControladorInicio implements ActionListener{

	private final VentanaInicio ventanaInicial;
	private Modelo sistema;
	private final int  SERVIDOR=0;
	private final int  CLIENTE=1;
	public ControladorInicio(VentanaInicio ventanaInicial) {
		this.ventanaInicial = ventanaInicial;
		sistema = this.ventanaInicial.getModelo();
	}
	
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource()==ventanaInicial.getBtnServidor()) {
			sistema.iniciarVentanaCrearBarco();
			JOptionPane.showMessageDialog(null, "Si desea cambiar la direccion de su barco de click derecho y seleccione una posicion");
			sistema.setLado(SERVIDOR);
		}else {
			sistema.iniciarVentanaCrearBarco();
			sistema.setLado(CLIENTE);
		}
	}
 
}
