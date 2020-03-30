/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 *
 * @author Estudiantes
 */
public class ControladorPrincipal implements ActionListener {
    private VentanaPrincipal inicio;

    public ControladorPrincipal(VentanaPrincipal inicio) {
        this.inicio = inicio;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == inicio.getBtnNuevaTransaccion()) {
    		inicio.dispose();
    		inicio.getModelo().iniciarVTransacciones();
    	}
    	
    	if(e.getSource()==inicio.getBtnMisTransacciones()) {
    		inicio.dispose();
    		inicio.getModelo().iniciarMostrarTx();
    	}
    	
    	if(e.getSource()==inicio.getBtnCategoria()) {
    		inicio.dispose();
    		inicio.getModelo().iniciarVCategoria();
    	}
    	
    	if(e.getSource()==inicio.getBtnCuentas()) {
    		inicio.dispose();
    		inicio.getModelo().iniciarVCuenta();
    	}
    	
    	if(e.getSource()==inicio.getBtnRetiro()) {
    		inicio.dispose();
    		inicio.getModelo().iniciarVRetiro();
    	}
    	
    	if(e.getSource()==inicio.getBtnReportes()) {
    		inicio.dispose();
    		//inicio.getModelo().iniciarVReporte();
    		inicio.getModelo().iniciarVGraficos();
    	}
    }
    
   
}
