/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import vista.VentanaSeleccion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;

import javax.swing.JOptionPane;

/**
 *
 * @author Estudiantes
 */
public class ControladorSeleccion implements ActionListener {
	private VentanaSeleccion inicio;
	private VentanaJuego Vjuego;
	private final int SERVIDOR=1;
	private final int CLIENTE=2;
	boolean activo=true;

	public ControladorSeleccion(VentanaSeleccion inicio) {
		this.inicio = inicio;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == inicio.getBotonServidor()) {
		
			inicio.getModelo().setLado(SERVIDOR);

			inicio.getBotonServidor().setEnabled(false);
			inicio.getBotonCliente().setEnabled(false);
			inicio.getModelo().setEstado("esperando cliente.");
			inicio.getModelo().setColorEstado(1);
			
			//El hilo evita que el Jframe se congele y el boton se quede pegado
			Runnable esperaCliente = new Runnable() {

				public void run() {
						// TODO Auto-generated method stub
					try {
						inicio.getModelo().iniciarServidor();
						inicio.getModelo().setEstado("cliente conectado.");
						inicio.getModelo().setColorEstado(2);
						Thread.sleep(1);
						inicio.setVisible(false);
						inicio.getModelo().iniciarVentanaJuego();
						inicio.getModelo().setTituloJuego("Servidor");
						//El turno es del servidor
						inicio.getModelo().setTurno(true);
						while(activo) {
							inicio.getModelo().servidorRecibeTablero();
							inicio.getModelo().setTurno(true);
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		    Thread hilo = new Thread (esperaCliente);
		    hilo.start();
		      
		

		} else {
			
			inicio.getModelo().setLado(CLIENTE);
			inicio.getBotonServidor().setEnabled(false);
			inicio.getModelo().setEstado("conectando..");
			inicio.getModelo().setColorEstado(1);

			try {
				inicio.getModelo().iniciarConexion();
				inicio.getModelo().setEstado("conectado");
				inicio.setVisible(false);
				inicio.getModelo().iniciarVentanaJuego();
				inicio.getModelo().setTituloJuego("Cliente");
				//El turno es del servidor NO del cliente
				inicio.getModelo().setTurno(false);
				inicio.getModelo().getVentanaJuego().setTitle("Cliente");

				//El turno es del servidor NO del cliente

				Runnable graficarTablero = new Runnable() {

					public void run() {
						// TODO Auto-generated method stub

						while(activo) {
							try {
								//Recibe tablero de la red y a la vez grafica
								inicio.getModelo().clienteRecibeTablero();
								inicio.getModelo().setTurno(true);

							} catch (Exception e) {
	
							}
						}
					}
				};
			    Thread hiloLectura = new Thread (graficarTablero);
			    hiloLectura.start();
			      

			} catch (Exception n) {
				inicio.getModelo().setEstado("Intento de conexion fallido");
				n.printStackTrace();
			}

		}
	}

}
