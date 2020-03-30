package vista;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import logica.*;

public class Modelo {
	private VentanaJuego ventanaJuego;
	private VentanaCrearBarco ventanaCrearBarco;
	private VentanaInicio ventanaInicio;
	private Servidor servidor;
	private Cliente cliente;
	private Barco barco;
	private int lado;
	private final int DISPAROACERTADO=9;
	private final int DISPAROFALLIDO=8;
	
	public void iniciarVentanaJuego() {
		// TODOAuto-generated method stub
		getVentanaCrearBarco().setVisible(false);
		getVentanaJuego().setTitle("Juego");		
		getVentanaJuego().setResizable(true);
		getVentanaJuego().setLocationRelativeTo(null);
		getVentanaJuego().setVisible(true);
		
		//Si quiere eliminar el texto de los botones
		//VentanaJuego.eliminarTextoBotones();
	}
	
	public void iniciarVentanaCrearBarco() {
		getVentanaInicio().setVisible(false);
		getVentanaCrearBarco().setTitle("Ubicar tropas");
		getVentanaCrearBarco().setResizable(true);
		getVentanaCrearBarco().setLocationRelativeTo(null);
		getVentanaCrearBarco().setVisible(true);
	}
	
	public void iniciarVentanaIncial() {
		getVentanaInicio().setTitle("Batalla Naval");
		getVentanaInicio().setResizable(true);
		getVentanaInicio().setLocationRelativeTo(null);
		getVentanaInicio().setVisible(true);
	}
	public VentanaJuego getVentanaJuego() {
		if(ventanaJuego == null)
			ventanaJuego = new VentanaJuego(this);
		
		return ventanaJuego;
	}
	
	public VentanaCrearBarco getVentanaCrearBarco() {
		if(ventanaCrearBarco == null)
			ventanaCrearBarco = new VentanaCrearBarco(this);
		
		return ventanaCrearBarco;
	}

	public VentanaInicio getVentanaInicio() {
		if(ventanaInicio == null)
			ventanaInicio = new VentanaInicio(this);
		
		return ventanaInicio;
	}
	public Servidor getServidor() {
		if(servidor == null)
			servidor = new Servidor();
		
		return servidor;
	}

	public Cliente getCliente() {
		if(cliente == null)
			cliente = new Cliente();
		
		return cliente;
	}
	
	public Barco getBarco() {
		if(barco == null)
			return barco = new Barco(0);
		return barco;
	}

	public int getLado() {
		return lado;
	}
	
	public void setLado(int l) {
		lado = l;
	}
	public void setTurno (boolean siono) {
		getVentanaJuego().setTurno(siono);
	}
	

	public void iniciarServidor() {
		try {
			getServidor().iniciarServidor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarConexion() {
		try {
			getCliente().iniciarConexion();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMapaACliente(int [][] tablero) {
		PaqueteEnvio paquete = new PaqueteEnvio();
		paquete.setMapa1(tablero);
		
		try {
			getServidor().enviarPaquete(paquete);
		} catch (IOException e) {
			System.err.println("No fue posible enviar mapa a cliente");
			e.printStackTrace();
		}
	}
	
	public void enviarMapaAServidor(int [][] tablero) {
		PaqueteEnvio paquete = new PaqueteEnvio();
		paquete.setMapa1(tablero);
		
		try {
			getCliente().enviarPaquete(paquete);
		} catch (IOException e) {
			System.err.println("No fue posible enviar mapa a servidor");
			e.printStackTrace();
		}
	}
	
	public void servidorRecibeDatos() {
		Runnable lectorServidor = new Runnable() {
			public void run() {
				while(true) {
					PaqueteEnvio paquete = new PaqueteEnvio();
					paquete = getServidor().recibirPaquete();
					if(paquete.getMapa1()!=null) {
						getVentanaJuego().setMapa2(paquete.getMapa1());
					}else {
						verificarMovimientoRecibido(paquete.getX(), paquete.getY());
						graficarMatrizDeEnterosPropia();
						verificarGanadorGlobal();
						setTurno(true);
						activarBotones();
					}
					
				}
			}
		};
		Thread hiloLectura = new Thread(lectorServidor);
		hiloLectura.start();
	}
	
	public void clienteRecibeDatos() {
		Runnable lectorCliente = new Runnable() {
			public void run() {
				while(true) {
					PaqueteEnvio paquete = new PaqueteEnvio();
					paquete = getCliente().recibirPaquete();
					if(paquete.getMapa1()!=null) {
						getVentanaJuego().setMapa2(paquete.getMapa1());
					}else{
						verificarMovimientoRecibido(paquete.getX(), paquete.getY());
						graficarMatrizDeEnterosPropia();
						verificarGanadorGlobal();
						setTurno(true);
						activarBotones();
					}
				}
			}
		};
		Thread hiloLectura = new Thread(lectorCliente);
		hiloLectura.start();
	}
	
	
	public void enviarMovimientoACliente(int x, int y) {
		PaqueteEnvio paquete = new PaqueteEnvio();
		paquete.setX(x);
		paquete.setY(y);
		try {
			getServidor().enviarPaquete(paquete);
		} catch (IOException e) {
			System.err.println("No fue posible enviar movimiento a cliente");
			e.printStackTrace();
		}
	}
	
	public void enviarMovimientoAServidor(int x, int y) {
		PaqueteEnvio paquete = new PaqueteEnvio();
		paquete.setX(x);
		paquete.setY(y);
		try {
			getCliente().enviarPaquete(paquete);
		} catch (IOException e) {
			System.err.println("No fue posible enviar movimiento a servidor");
			e.printStackTrace();
		}
	}
	
	public void graficarMapaPropio() {
		int mapa[][] = new int[11][11];
		mapa = getVentanaJuego().getMapa1();
		for(int i=1; i<mapa.length;i++) {
			for(int j=1; j<mapa.length ;j++) {
				if(mapa[i][j]==1) {					
					getVentanaJuego().getBotonTablero1(i, j).setBackground(Color.red);
				}
				if(mapa[i][j]==2) {					
					getVentanaJuego().getBotonTablero1(i, j).setBackground(Color.black);
				}
				if(mapa[i][j]==3) {					
					getVentanaJuego().getBotonTablero1(i, j).setBackground(Color.blue);
				}
				if(mapa[i][j]==4) {					
					getVentanaJuego().getBotonTablero1(i, j).setBackground(Color.cyan);
				}
				if(mapa[i][j]==5) {					
					getVentanaJuego().getBotonTablero1(i, j).setBackground(Color.darkGray);
				}
			}
		}
	}

	public void setColorBoton(JButton boton, Color color) {
		// TODO Auto-generated method stub
		boton.setBackground(color);
	}


	public void iluminarBotones(Barco barco, int i, int j, int orientacion, Object[][] tablero) {
	
		//Se pinta todo de blanco
		
		for (int r = 1; r < 11; r++) {
			for (int l = 1; l < 11; l++) {
				//ACTIVAR PARA QUE LOS BOTONES AZULES NO SEAN REPINTADOS
				if( ( (JButton)tablero[r][l]).getBackground().equals(Color.green)==false)
				this.setColorBoton((JButton)tablero[r][l], Color.white);
			}
		}

		//Se pintan los botones que se necesiten
		if(orientacion==0) {
			for (int k = 0; k < barco.getCantidadCasillas(); k++) {
				if( ( (JButton)tablero[i][j+k]).getBackground().equals(Color.green)==false)
				this.setColorBoton((JButton)tablero[i][j+k], Color.yellow);
			}
		}else if (orientacion==1){
			for (int k = 0; k < barco.getCantidadCasillas(); k++) {
				if( ( (JButton)tablero[i+k][j]).getBackground().equals(Color.green)==false)

				this.setColorBoton((JButton)tablero[i+k][j], Color.yellow);
			}
		}

		

	}
	//Si solo se necesita iluminar una casilla
	public void iluminarBotones( int i, int j, int orientacion, Object[][] tablero) {
		
		//Se pinta todo de blanco
		
		for (int r = 1; r < 11; r++) {
			for (int l = 1; l < 11; l++) {
				//ACTIVAR PARA QUE LOS BOTONES AZULES NO SEAN REPINTADOS
				if( ( (JButton)tablero[r][l]).getBackground().equals(Color.green)==false)
				this.setColorBoton((JButton)tablero[r][l], Color.red);
				System.out.println("192 modelo");
			}
		}

		//Se pintan los botones que se necesiten
		if(orientacion==0) {
			for (int k = 0; k < 1; k++) {
				if(getVentanaJuego().getBotonTablero1(i, j+k).getBackground().equals(Color.green))	
				this.setColorBoton((JButton)tablero[i][j+k], Color.yellow);
			}
		}else if (orientacion==1){
			for (int k = 0; k < 1; k++) {
				if(getVentanaJuego().getBotonTablero1(i+k, j).getBackground()!=Color.green)
				this.setColorBoton((JButton)tablero[i+k][j], Color.yellow);
			}
		}	
	}
	
	public int[][] getMatrizLogica(){
		return ventanaCrearBarco.getMatrizCreacion();
	}

	public void copearMatrizAVentanaJuego() {
		getVentanaJuego().setMapa1(getVentanaCrearBarco().getMatrizCreacion());
	}
	

	public void verificarMovimientoPropio(int x, int y) {
		int [][] m = getVentanaJuego().getMapa2();
		if(m[x][y]==0) {
			getVentanaJuego().desactivarBoton(x, y);
			m[x][y]=DISPAROFALLIDO;
		}
		
		if(m[x][y]==1) {
			getVentanaJuego().desactivarBoton(x, y);
			m[x][y]=DISPAROACERTADO;
			getBarco().setAciertosPortaAviones(getBarco().getAciertosPortaAviones()+1);
		}
		
		if(m[x][y]==2) {
			getVentanaJuego().desactivarBoton(x, y);
			m[x][y]=DISPAROACERTADO;
			getBarco().setAciertosSubmarinos(getBarco().getAciertosSubmarinos()+1);
		}
		
		if(m[x][y]==3) {
			getVentanaJuego().desactivarBoton(x, y);
			m[x][y]=DISPAROACERTADO;
			getBarco().setAciertosBuque(getBarco().getAciertosBuque()+1);
		}
		
		if(m[x][y]==4) {
			getVentanaJuego().desactivarBoton(x, y);
			m[x][y]=DISPAROACERTADO;
			getBarco().setAciertosBarco(getBarco().getAciertosBarco()+1);
		}
		
		if(m[x][y]==5) {
			getVentanaJuego().desactivarBoton(x, y);
			m[x][y]=DISPAROACERTADO;
			getBarco().setAciertosLancha(getBarco().getAciertosLancha()+1);
		}
		getVentanaJuego().setMapa2(m);
	}
	
	public void verificarMovimientoRecibido(int x, int y) {
		int [][] m = getVentanaJuego().getMapa1();
		if(m[x][y]==0) {
			m[x][y]=DISPAROFALLIDO;
		}
		
		if(m[x][y]==1 || m[x][y]==2 || m[x][y]==3 || m[x][y]==4 || m[x][y]==5) {
			m[x][y]=DISPAROACERTADO;
		}
		getVentanaJuego().setMapa1(m);
	}
	public void verificarDerribo() {
		if(getBarco().getAciertosPortaAviones()==5) {
			mostrarMensaje("Has derribado el porta aviones de tu enemigo.");
			getBarco().setAciertosPortaAviones(getBarco().getAciertosPortaAviones()+1);
		}
		if(getBarco().getAciertosSubmarinos()==4) {
			mostrarMensaje("Has derribado el submarino de tu enemigo.");
			getBarco().setAciertosSubmarinos(getBarco().getAciertosSubmarinos()+1);
		}
		if(getBarco().getAciertosBuque()==3) {
			mostrarMensaje("Has derribado el buque de tu enemigo.");
			getBarco().setAciertosBuque(getBarco().getAciertosBuque()+1);
		}
		if(getBarco().getAciertosBarco()==2) {
			mostrarMensaje("Has derribado el barco de tu enemigo.");
			getBarco().setAciertosBarco(getBarco().getAciertosBarco()+1);
		}
		if(getBarco().getAciertosLancha()==1) {
			mostrarMensaje("Has derribado la lancha de tu enemigo.");
			getBarco().setAciertosLancha(getBarco().getAciertosLancha()+1);
		}
	}
		
	public void verificarGanador() {
		if(getBarco().getAciertosPortaAviones()>=5 && getBarco().getAciertosSubmarinos()>=4 && 
				getBarco().getAciertosBuque()>=3 && getBarco().getAciertosBarco()>=2 &&
				getBarco().getAciertosLancha()>=1)
			mostrarMensaje("Has ganado");
	}
	public void graficarMatrizDeEnterosPropia() {
		int [][] m = getVentanaJuego().getMapa1();
		for(int i=1; i<m.length; i++) {
			for(int j=1; j<m.length; j++) {
				if(m[i][j]==DISPAROFALLIDO)
					getVentanaJuego().getBotonTablero1(i, j).setText("O");
				if(m[i][j]==DISPAROACERTADO)
					getVentanaJuego().getBotonTablero1(i, j).setText("X");
			}
		}
	}
	public void graficarMatrizDeEnterosAtacada() {
		int [][] m = getVentanaJuego().getMapa2();
		for(int i=1; i<m.length; i++) {
			for(int j=1; j<m.length; j++) {
				if(m[i][j]==DISPAROFALLIDO)
					getVentanaJuego().getBotonTablero2(i, j).setText("O");
				if(m[i][j]==DISPAROACERTADO)
					getVentanaJuego().getBotonTablero2(i, j).setText("X");
			}
		}
	}
	
	public void verificarGanadorGlobal() {
		int [][] m = getVentanaJuego().getMapa1();
		int c=0;
		for(int i=1; i<m.length; i++) {
			for(int j=1; j<m.length; j++) {
				if(m[i][j]==DISPAROACERTADO)
					c += 1;
			}
		}
		if(c==15)
			mostrarMensajeInterno("Has perdido.");
	}
	
	public void desactivarTodosLosBotones() {
		int [][] m = getVentanaJuego().getMapa2();
		for(int i=1; i<m.length; i++) {
			for(int j=1; j<m.length; j++) {
				getVentanaJuego().getBotonTablero2(i, j).setEnabled(false);
			}
		}
	}
	
	public void activarBotones() {
		int [][] m = getVentanaJuego().getMapa2();
		for(int i=1; i<m.length; i++) {
			for(int j=1; j<m.length; j++) {
				if(m[i][j]==0 || m[i][j]==1 || m[i][j]==2 || m[i][j]==3 || m[i][j]==4 || m[i][j]==5)
					getVentanaJuego().getBotonTablero2(i, j).setEnabled(true);
			}
		}
	}
	public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(getVentanaJuego(), msg, "Felicidades", JOptionPane.INFORMATION_MESSAGE);
    }
	
	public void mostrarMensajeInterno(String msg) {
        JOptionPane.showMessageDialog(getVentanaJuego(), msg, "Ouchhh", JOptionPane.INFORMATION_MESSAGE);
    }
}
