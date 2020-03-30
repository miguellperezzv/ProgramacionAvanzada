package logica;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
	private Socket miSocket;
    private ObjectInputStream datosEntrada;
    private ObjectOutputStream datosSalida;
    
    public void iniciarConexion() throws IOException{
    	
    	// Establecer contacto
    	miSocket = new Socket("127.0.0.1", 7800);    	
    	// Capturar flujos
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
			e.printStackTrace();
			System.out.println("Se perdio la conexion");
		}
		return null;
	}
    
    public void desconectarCliente() throws IOException {
    	datosEntrada.close();
    	datosSalida.close();
    	miSocket.close();        
    }
}
