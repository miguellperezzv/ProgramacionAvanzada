package logica;

import java.io.*;
import java.net.*;

public class Servidor {
	
	private int puertoServicio=7800;
	private ServerSocket servidor;
	private Socket miSocket;
	private ObjectOutputStream datosSalida;
	private ObjectInputStream datosEntrada;
	
	public void iniciarServidor() throws IOException {
		// abrir el socket en el puerto que me envian
		servidor = new ServerSocket(puertoServicio);
		// esperar un usuario
		System.out.println("Esperando cliente");
		miSocket = servidor.accept();
		System.out.println("Cliente aceptado");
		// capturar flujos
		datosSalida = new ObjectOutputStream(miSocket.getOutputStream());		
		datosEntrada = new ObjectInputStream(miSocket.getInputStream());
	}
	
	public void enviarPaquete(PaqueteEnvio paquete) throws IOException {
		datosSalida.writeObject(paquete);
	}
	
	public PaqueteEnvio recibirPaquete() {
		PaqueteEnvio paquete = new PaqueteEnvio();
		
		try {
			paquete = (PaqueteEnvio) datosEntrada.readObject();
			return paquete;
		} catch (IOException | ClassNotFoundException e){
			System.err.println("Se perdio la conexion");
			e.printStackTrace();
		}
		return null;
	}
	
	public void desconectarServidor() throws IOException {
        datosEntrada.close();
        datosSalida.close();
        miSocket.close();        
    }
	
}
