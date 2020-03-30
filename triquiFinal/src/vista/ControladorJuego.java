package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControladorJuego implements ActionListener {

	private VentanaJuego ventana;

	public ControladorJuego(VentanaJuego v) {

		ventana = v;

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == ventana.getBotonJuego(0, 0)) {
			
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(0, 0, 1);
				ventana.setIcono(0, 0);
				// Al hacer click pierdo mi turno y se bloquean los botones 
				ventana.getModelo().enviarTableroACliente(0, 0, 1);
				// ventana.setIcono(0, 0);
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);

			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(0, 0, 2);
				ventana.setIcono(0, 0);
				ventana.getModelo().enviarTableroAServidor(0, 0, 2);
				ventana.getModelo().setTurno(false);
			}

			if(ventana.getModelo().verificarGanador()==1) {
				JOptionPane.showMessageDialog(null, "Gano el servidor"); 
			ventana.getModelo().bloquearBotones();}
		
			else if(ventana.getModelo().verificarGanador()==2) {
				JOptionPane.showMessageDialog(null, "Gano el cliente");
		ventana.getModelo().bloquearBotones();}


		} else if (e.getSource() == ventana.getBotonJuego(0, 1)) {
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(0, 1, 1);
				ventana.getModelo().enviarTableroACliente(0, 1, 1);
				ventana.setIcono(0, 1);
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);
			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(0, 1, 2);
				System.out.print("VALOR DEL GANADOR "+
						  ventana.getModelo().EstadoJuego(ventana.getMatrizJuego()));
				ventana.getModelo().enviarTableroAServidor(0, 1, 2);
				ventana.getModelo().setTurno(false);
				if( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==1){
					 System.out.print(" GANADOR 1");
					 ventana.getModelo().bloquearBotones();
				 }
				 else if (( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==2)) {
					 System.out.print(" GANADOR 2");
					 ventana.getModelo().bloquearBotones();
				 }

			}

			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor, fin del juego");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente, fin del juego");

		} else if (e.getSource() == ventana.getBotonJuego(0, 2)) {
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(0, 2, 1);
				ventana.getModelo().enviarTableroACliente(0, 2, 1);
				ventana.setIcono(0, 2);
				if( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==1){
					 System.out.print(" GANADOR 1");
					 ventana.getModelo().bloquearBotones();
				 }
				 else if (( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==2)) {
					 System.out.print(" GANADOR 2");
					 ventana.getModelo().bloquearBotones();
				 }
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);
			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(0, 2, 2);
				ventana.getModelo().enviarTableroAServidor(0, 2, 2);
				ventana.getModelo().setTurno(false);
				if( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==1){
					 System.out.print(" GANADOR 1");
					 ventana.getModelo().bloquearBotones();
				 }
				 else if (( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==2)) {
					 System.out.print(" GANADOR 2");
					 ventana.getModelo().bloquearBotones();
				 }

			}

			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			
			
		} else if (e.getSource() == ventana.getBotonJuego(1, 0)) {
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(1, 0, 1);
				ventana.getModelo().enviarTableroACliente(1, 0, 1);
				ventana.setIcono(1, 0);
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);
				if( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==1){
					 System.out.print(" GANADOR 2");
					 ventana.getModelo().bloquearBotones();
				 }
				 else if (( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==2)) {
					 System.out.print(" GANADOR 1");
					 ventana.getModelo().bloquearBotones();
				 }

			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(1, 0, 2);
				ventana.getModelo().enviarTableroAServidor(1, 0, 2);
				ventana.getModelo().setTurno(false);
				if( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==1){
					 System.out.print(" GANADOR 1");
					 ventana.getModelo().bloquearBotones();
				 }
				 else if (( ventana.getModelo().EstadoJuego(ventana.getMatrizJuego())==2)) {
					 System.out.print(" GANADOR 2");
					 ventana.getModelo().bloquearBotones();
				 }

			}

			
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			
		} else if (e.getSource() == ventana.getBotonJuego(1, 1)) {
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(1, 1, 1);
				ventana.getModelo().enviarTableroACliente(1, 1, 1);
				ventana.setIcono(1, 1);
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);
			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(1, 1, 2);
				ventana.getModelo().enviarTableroAServidor(1, 1, 2);
				ventana.getModelo().setTurno(false);

			}

			
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			
		} else if (e.getSource() == ventana.getBotonJuego(1, 2)) {
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(1, 2, 1);
				ventana.getModelo().enviarTableroACliente(1, 2, 1);
				ventana.setIcono(1, 2);
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);

			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(1, 2, 2);
				ventana.getModelo().enviarTableroAServidor(1, 2, 2);
				ventana.getModelo().setTurno(false);

			}

			
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			
		} else if (e.getSource() == ventana.getBotonJuego(2, 0)) {
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(2, 0, 1);
				ventana.getModelo().enviarTableroACliente(2, 0, 1);
				ventana.setIcono(2, 0);
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);
			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(2, 0, 2);
				ventana.getModelo().enviarTableroAServidor(2, 0, 2);
				ventana.getModelo().setTurno(false);

			}

			
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			
			
		} else if (e.getSource() == ventana.getBotonJuego(2, 1)) {
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(2, 1, 1);
				ventana.getModelo().enviarTableroACliente(2, 1, 1);
				ventana.setIcono(2, 1);
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);
			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(2, 1, 2);
				ventana.getModelo().enviarTableroAServidor(2, 1, 2);
				ventana.getModelo().setTurno(false);

			}

			
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			
		} else if (e.getSource() == ventana.getBotonJuego(2, 2)) {
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			if (ventana.getModelo().getLado() == 1) {
				// Modifica la matriz
				ventana.getModelo().setTablero(2, 2, 1);
				ventana.getModelo().enviarTableroACliente(2, 2, 1);
				ventana.setIcono(2, 2);
				// Al hacer click pierdo mi turno
				ventana.getModelo().setTurno(false);
			}
			if (ventana.getModelo().getLado() == 2) {
				ventana.getModelo().setTablero(2, 2, 2);
				ventana.getModelo().enviarTableroAServidor(2, 2, 2);
				ventana.getModelo().setTurno(false);

			}

			
			
			if(ventana.getModelo().verificarGanador()==1)
				JOptionPane.showMessageDialog(null, "Gano el servidor");
			else if(ventana.getModelo().verificarGanador()==2)
				JOptionPane.showMessageDialog(null, "Gano el cliente");
			
			
			
		}

	}

}
