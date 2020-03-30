package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import logica.Barco;

public class ControladorCrearBarco implements ActionListener, MouseListener {

	private VentanaCrearBarco ventanaCrearBarco;
	private Modelo sistema;
	int click = 0;
	private final int HORIZONTAL = 0;
	private final int VERTICAL = 1;
	private final int SERVIDOR = 0;
	private final int CLIENTE = 1;
	private int orientacion = HORIZONTAL;
	private int tipoDeBarcoAUsar = 0;

	ControladorCrearBarco(VentanaCrearBarco v) {
		this.ventanaCrearBarco = v;
		sistema = this.ventanaCrearBarco.getModelo();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		


		
		if(arg0.getSource()==ventanaCrearBarco.getBtnConectar()) {
			if(sistema.getLado()==SERVIDOR) {
				Runnable esperaCliente = new Runnable() {

					public void run() {
						try {
							sistema.iniciarServidor();
							sistema.iniciarVentanaJuego();
							sistema.enviarMapaACliente(ventanaCrearBarco.getMatrizCreacion());
							sistema.copearMatrizAVentanaJuego();
							sistema.graficarMapaPropio();
							sistema.setTurno(true);
							if(true) {							
								sistema.servidorRecibeDatos();
							}
						} catch (Exception e) {
							System.err.println("Error intentando crear el servidor");
							e.printStackTrace();
						}
					}
				};
			    Thread hilo = new Thread (esperaCliente);
			    hilo.start();
			}else if(sistema.getLado()==CLIENTE) {
				try {
					sistema.iniciarConexion();
					sistema.iniciarVentanaJuego();
					sistema.enviarMapaAServidor(ventanaCrearBarco.getMatrizCreacion());
					sistema.copearMatrizAVentanaJuego();
					sistema.graficarMapaPropio();
					sistema.setTurno(false);
					sistema.desactivarTodosLosBotones();
					if (true) {						
						sistema.clienteRecibeDatos();
					}	      

				} catch (Exception n) {
					System.err.println("Intento de conexion fallido");
					n.printStackTrace();
				}
			}
			
		}

		
		
		for(int i=1;i<11;i++) {
			for(int j=1;j<11;j++) {
				if(arg0.getSource().equals(ventanaCrearBarco.getBoton(i, j))) {
					
					for(int r=1;r<11;r++) {
						for(int l=1;l<11;l++) {
							if(ventanaCrearBarco.getBoton(r,l).getBackground().equals(Color.yellow)==true) {
								ventanaCrearBarco.getBoton(r,l).setBackground(Color.green);
								if(tipoDeBarcoAUsar==0)
									ventanaCrearBarco.setMatrizCreacion(r, l, 1);
								if(tipoDeBarcoAUsar==1)
									ventanaCrearBarco.setMatrizCreacion(r, l, 2);
								if(tipoDeBarcoAUsar==2)
									ventanaCrearBarco.setMatrizCreacion(r, l, 3);
								if(tipoDeBarcoAUsar==3)
									ventanaCrearBarco.setMatrizCreacion(r, l, 4);
								if(tipoDeBarcoAUsar==4)
									ventanaCrearBarco.setMatrizCreacion(r, l, 5);
							}
						}
					}					
				}
			}
		}
		
		tipoDeBarcoAUsar++;
		if(tipoDeBarcoAUsar==5)
			JOptionPane.showMessageDialog(null, "Ya no hay mas barcos, dale en conectar!");
		//IMPRESION DE MATRIZ LOGICA
		for(int i=1;i<11;i++) {
			for(int j=1;j<11;j++) {
				System.out.print(ventanaCrearBarco.getElementoMatriz(i, j)+" ");
			}
			System.out.println("");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getButton() == 3) {
			if (orientacion == HORIZONTAL) {
				orientacion = VERTICAL;
			} else {
				orientacion = HORIZONTAL;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				if (e.getSource() == ventanaCrearBarco.getBoton(i, j)) {

					sistema.iluminarBotones(new Barco(tipoDeBarcoAUsar), i, j, orientacion,
							ventanaCrearBarco.getTableroBotones());
				}
			}
		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
