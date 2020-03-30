package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

public class ControladorMostrarTx implements ActionListener {
	private VentanaMostrarTx inicio;

	ControladorMostrarTx(VentanaMostrarTx v) {

		inicio = v;

	}

	public void actionPerformed(ActionEvent e) {
		inicio.getModelo().crearTablaTx();
		//EVENTO BOTON VOLVER
		if (e.getSource() == inicio.getBtnVolver()) {
			inicio.dispose();
			inicio.getModelo().iniciarPrincipal();

		}else {
		inicio.getModelo().agregarDatosMostrarTx((String)inicio.getCombo().getSelectedItem(), (String)inicio.getComboFiltroFechas().getSelectedItem());
		}
		

		
	
		

	}

}
