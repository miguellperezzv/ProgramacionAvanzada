package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class ControladorRetiro implements ActionListener{
	
	private VentanaRetiro transacciones;

    public ControladorRetiro(VentanaRetiro transacciones) {
        this.transacciones = transacciones;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource()==transacciones.getbtnVolver()) {
			transacciones.dispose();
			transacciones.getModelo().iniciarPrincipal();
			
		}
		
		if(arg0.getSource()==transacciones.getbtnAnadir()) {
			String valor=transacciones.getTextField().getText();
			boolean validez = false;
			
			if(valor.isEmpty()||transacciones.getTextField_1().getText().isEmpty()) {
				validez=false;
				JOptionPane.showMessageDialog(null, "Ingrese datos en Valor y Nombre");
			}
			else {
				validez=true;
			}
			for(int i=0; i<valor.length();i++) {
				if(valor.charAt(i)<'0' || valor.charAt(i)>'9') {
					validez=false;
				}
				else {
					validez=true;
				}
				
				if(i==(valor.length()-1)&&validez==false) {
					JOptionPane.showMessageDialog(null, "Valor incorrecto");
				}
			}
			
			if(validez==true) {
				SimpleDateFormat formateador = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" , new Locale("es_ES"));
				Date fechaDate = new Date();
				String fecha = formateador.format(fechaDate);
				transacciones.getModelo().crearTablaCuentas();
				transacciones.getModelo().insertarNuevaTransaccion(transacciones.getTextField_1().getText(), 
						transacciones.getCmbBoxCategoria().getSelectedItem().toString(), 
						transacciones.getCmbBoxCuenta().getSelectedItem().toString(), 
						Double.parseDouble(transacciones.getTextField().getText())*-1, 
						fecha,transacciones.getTxtDescripcion().getText());
				JOptionPane.showMessageDialog(transacciones, "Transaccion anadida!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE, null);
			}
			
			
		}
		
		
	}

}
