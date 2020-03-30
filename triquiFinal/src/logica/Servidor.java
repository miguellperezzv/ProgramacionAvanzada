package logica;

import java.io.*;
import java.net.*;

/**
 *
 * @author Estudiantes
 */
public class Servidor extends Thread {

	private int puertoServicio;
	private boolean activo;
	private ServerSocket servidor;
	private Socket misocket;
	private ObjectInputStream datosEntrada;
    private ObjectOutputStream datosSalida;
	public Servidor() {
		puertoServicio = 9060;
		activo = true;
		
	}

	public void iniciarServidor() throws IOException {
		
		servidor = new ServerSocket(puertoServicio);
		
		misocket = servidor.accept();
		datosSalida = new ObjectOutputStream(misocket.getOutputStream());
		datosEntrada = new ObjectInputStream(misocket.getInputStream());
		
	}

	public void enviarTableroACliente(PaqueteEnvio p) throws IOException {		
		datosSalida.writeObject(p);
		//System.out.println("Lo esta enviando, wii");
	}
	
	public PaqueteEnvio obtenerTableroDeCliente() {
		
		PaqueteEnvio paquete = new PaqueteEnvio();

		try {
			paquete = (PaqueteEnvio) datosEntrada.readObject();
			//System.out.println("Le llego el tablero al servidor");
			return paquete;
		} catch ( IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void desconectarServidor() throws IOException {
        datosEntrada.close();
        datosSalida.close();
        misocket.close();        
    }
	
}
