package logica;

import java.io.*;
import java.net.*;

public class Cliente {
	 private String hostNombre;

	    private int puertoServicio;
	    private Socket misocket;
	    private ObjectInputStream datosEntrada;
	    private ObjectOutputStream datosSalida;

	    public Cliente() {
	        puertoServicio = 9060;
	        hostNombre = "127.0.0.1";
	    }
	    
	    public void iniciarConexion() throws IOException{
	    	
	    	// Establecer contacto
	    	misocket = new Socket(hostNombre, puertoServicio);
	    	
	    	// Capturar flujos
	    	datosEntrada = new ObjectInputStream(misocket.getInputStream());
	    	datosSalida = new ObjectOutputStream(misocket.getOutputStream());
	    	
	    }
	    
	    public void desconectarCliente() throws IOException {
	    	datosEntrada.close();
	    	datosSalida.close();
	    	misocket.close();        
	    }	   
	   
	public void enviarTableroAServidor(PaqueteEnvio p) throws IOException {		
		datosSalida.writeObject(p);
		//System.out.println("Lo esta enviando, wii x 2");
	}
	
	
	public PaqueteEnvio obtenerTableroDeServidor() {
		
		PaqueteEnvio paquete = new PaqueteEnvio();

		try {
			paquete = (PaqueteEnvio) datosEntrada.readObject();
			//System.out.println("Le llego el tablero al cliente");
			return paquete;
		} catch ( IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
