package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorGraficos implements ActionListener {

	private VentanaGraficos vent;

	ControladorGraficos(VentanaGraficos v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (vent.getComboSeleccionGrafico().getSelectedItem().equals("Gastos de los ultimos 12 meses"))
			vent.getModelo().graficar("Gastos de los ultimos 12 meses");
		
		else if (vent.getComboSeleccionGrafico().getSelectedItem().equals("Numero de transacciones por cuenta"))
			vent.getModelo().graficar("Numero de transacciones por cuenta");
		
		else if (vent.getComboSeleccionGrafico().getSelectedItem().equals("Cantidad de Dinero por Cuenta"))
			vent.getModelo().graficar("Cantidad de Dinero por Cuenta");
		
		if (arg0.getSource()==vent.getBoton()) {
			vent.getModelo().graficar("Volver");
			vent.dispose();
			vent.getModelo().iniciarPrincipal();
		}
		
		
	}
}
