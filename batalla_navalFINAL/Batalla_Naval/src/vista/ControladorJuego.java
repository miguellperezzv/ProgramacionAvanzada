package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import logica.Barco;

public class ControladorJuego extends MouseAdapter implements ActionListener {

	private VentanaJuego ventana;
	private Modelo sistema;
	private final int HORIZONTAL=0;
	private final int VERTICAL=1;
	private final int SERVIDOR=0;
	private final int CLIENTE=1;
	private int orientacion=HORIZONTAL;

	public ControladorJuego(VentanaJuego v) {

		ventana = v;
		sistema = ventana.getSistema();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// En caso que se presione algo del tablero 2
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				if (e.getSource() == ventana.getBotonTablero2(i, j)) {
					if(sistema.getLado()==SERVIDOR) {
						sistema.desactivarTodosLosBotones();
						sistema.enviarMovimientoACliente(i, j);
						sistema.verificarMovimientoPropio(i, j);
						sistema.graficarMatrizDeEnterosAtacada();
						sistema.verificarDerribo();
						sistema.verificarGanador();
						sistema.setTurno(false);
					}else if(sistema.getLado()==CLIENTE){
						sistema.desactivarTodosLosBotones();
						sistema.enviarMovimientoAServidor(i, j);
						sistema.verificarMovimientoPropio(i, j);
						sistema.graficarMatrizDeEnterosAtacada();
						sistema.verificarDerribo();
						sistema.verificarGanador();
						sistema.setTurno(false);
					}
					// FALTA HACER REFERENCIA CON MODELO
					//ventana.getBotonTablero2(i, j).setBackground(Color.red);

				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	

	// METODOS POR AHORA NO SE USAN
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
