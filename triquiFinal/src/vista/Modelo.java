package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Calendar;

import logica.Cliente;
import logica.Juego;
import logica.PaqueteEnvio;
import logica.Servidor;

public class Modelo {

	private VentanaSeleccion VentanaSeleccion = new VentanaSeleccion(this);
	private VentanaJuego VentanaJuego = new VentanaJuego(this);
	private Servidor servidor;
	private Cliente cliente;
	private int lado;
	private Juego juego=new Juego();

	public void iniciarVentanaSeleccion() {

		VentanaSeleccion.setTitle("Prueba");
		VentanaSeleccion.setSize(300, 140);
		VentanaSeleccion.setResizable(true);
		VentanaSeleccion.setLocationRelativeTo(null);
		VentanaSeleccion.setVisible(true);


	}
	public void iniciarVentanaJuego() {

		VentanaJuego.setTitle("Prueba");
		//VentanaJuego.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		VentanaJuego.setSize(800, 600);

		VentanaJuego.setResizable(true);
		VentanaJuego.setLocationRelativeTo(null);
		VentanaJuego.setVisible(true);
	}

	public void cerrarVentanaSeleccion() {

		VentanaSeleccion.setVisible(false);

	}

	public void iniciarServidor() {
			try {
				getServidor().iniciarServidor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void  iniciarConexion() {
		try {
			getCliente().iniciarConexion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public int[][] getTablero() {
		return VentanaJuego.getMatrizJuego();
	}
	

	public void setTablero(int x, int y, int lado) {
		VentanaJuego.modificarMatriz(x,y,lado);
		graficarTablero(getTablero());
	}


	private Servidor getServidor() {
		if (servidor == null) {
			servidor = new Servidor();
		}
		return servidor;
	}

	private Cliente getCliente() {
		if (cliente == null) {
			cliente = new Cliente();
		}
		return cliente;
	}
	
	private Juego getJuego() {
		if(juego==null) {
			juego= new Juego();
		}
		return juego;
	}
	
	public VentanaJuego getVentanaJuego(){
		if(VentanaJuego ==null)
			VentanaJuego = new VentanaJuego(this);
		return VentanaJuego;
	}
	
	public int getLado() {
		return lado;
	}
	public void setLado(int lado) {
		this.lado=lado;
	}
	
	//Obtener la matriz desde la red, NO USAR usar la matriz local
	//VentanaJuego.getMatrizJuego();
	
	public void enviarTableroACliente(int x, int y, int j) {
		
		PaqueteEnvio paquete = new PaqueteEnvio();
		paquete.setX(x);
		paquete.setY(y);
		paquete.setLado(j);
		
		try {
			//getServidor().enviarTableroACliente(getTablero());
			getServidor().enviarTableroACliente(paquete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void enviarTableroAServidor(int x, int y, int j) {
		
		PaqueteEnvio paquete = new PaqueteEnvio();
		paquete.setX(x);
		paquete.setY(y);
		paquete.setLado(j);
		
		try {
			getCliente().enviarTableroAServidor(paquete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int[][] capturarTablero(){
		
		return VentanaJuego.getMatrizJuego();
		
	}
	
	public void graficarTablero(int [][] n){
		
		int [][] tablero = n;
		
		for (int i=0; i<tablero.length; i++) {
			for (int j=0 ; j<tablero.length;j++) {
				if(tablero[i][j]==0){
					
					
					VentanaJuego.setIcono(i, j, 0);
					VentanaJuego.modificarMatriz(i, j, 0);
					VentanaJuego.getBotonJuego(i,j).setText(Integer.toString(tablero[i][j]));

				} else if (tablero[i][j]==1) {
					VentanaJuego.getBotonJuego(i,j).setText(Integer.toString(tablero[i][j]));
					VentanaJuego.setIcono(i, j, 1);
					VentanaJuego.modificarMatriz(i, j, 1);
					VentanaJuego.getBotonJuego(i,j).setText(Integer.toString(tablero[i][j]));

				} else if (tablero[i][j]==2) {
					VentanaJuego.getBotonJuego(i,j).setText(Integer.toString(tablero[i][j]));
					VentanaJuego.setIcono(i, j, 2);
					VentanaJuego.modificarMatriz(i, j, 2);
					VentanaJuego.getBotonJuego(i,j).setText(Integer.toString(tablero[i][j]));

				}
			}
		}
		
		
	}

	public void setEstado(String string) {
		VentanaSeleccion.setEstado(string);
	}
	public void setTituloJuego(String string) {
		// TODO Auto-generated method stub
		VentanaJuego.setTitle(string);
	}
	public void setTurno(boolean b) {
		VentanaJuego.setTurno(b);
		if(b == true ) {
			this.desbloquearBotones();
		}else {
			this.bloquearBotones();
		}
	}

	public void setColorEstado(int no0Esperando1Conectado2) {
		VentanaSeleccion.setColorEstado(no0Esperando1Conectado2);
	}
	
	public void bloquearBotones() {
		VentanaJuego.getBotonJuego(0, 0).setEnabled(false);
		VentanaJuego.getBotonJuego(0, 1).setEnabled(false);
		VentanaJuego.getBotonJuego(0, 2).setEnabled(false);
		VentanaJuego.getBotonJuego(1, 0).setEnabled(false);
		VentanaJuego.getBotonJuego(1, 1).setEnabled(false);
		VentanaJuego.getBotonJuego(1, 2).setEnabled(false);
		VentanaJuego.getBotonJuego(2, 0).setEnabled(false);
		VentanaJuego.getBotonJuego(2, 1).setEnabled(false);
		VentanaJuego.getBotonJuego(2, 2).setEnabled(false);
	}
	
	public void desbloquearBotones() {
		int matriz [][]=this.VentanaJuego.getMatrizJuego();
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(matriz[i][j]==0) {
					VentanaJuego.getBotonJuego(i, j).setEnabled(true);
				}
			}
		}
	}
	 
	public int EstadoJuego(int [][] tablero) {
		return juego.RevisarEstado(tablero);
	}
	
	public void clienteRecibeTablero() {
		PaqueteEnvio paquete = new PaqueteEnvio();
		paquete = getCliente().obtenerTableroDeServidor();
		setTablero(paquete.getX(), paquete.getY(),	paquete.getLado());
	}
	
	public void servidorRecibeTablero() {
		PaqueteEnvio paquete = new PaqueteEnvio();
		paquete = getServidor().obtenerTableroDeCliente();
		setTablero(paquete.getX(), paquete.getY(), paquete.getLado());
	}
	
	public int verificarGanador() {
		int retorno = 0;
		Juego juego = new Juego();
		retorno=juego.RevisarEstado(this.getTablero());
		
		return retorno;
		
	}
	
	public void cerrarVentanaJuego() {

		VentanaJuego.setVisible(false);

	}
	
}
