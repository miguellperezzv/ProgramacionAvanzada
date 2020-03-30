package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.CalculosTxCuenta;
import logica.DiagramaCircular;
import logica.ModificarDB;

public class Modelo implements Runnable {

	
	
	private VentanaMostrarTx ventana2 = new VentanaMostrarTx(this);
	private VentanaPrincipal ventana3 = new VentanaPrincipal(this);
	private VentanaTransacciones vTransacciones = new VentanaTransacciones(this);
	private VentanaAgregarCategoria vCategoria = new VentanaAgregarCategoria(this);
	private VentanaAgregarCuenta vCuenta = new VentanaAgregarCuenta(this);
	private VentanaRetiro vRetiro = new VentanaRetiro(this);
	private VentanaGraficos vTxCuenta = new VentanaGraficos(this);
	
	private ModificarDB modificador = new ModificarDB();
	private BufferedImage dobleBuffer;
	
	private Thread hiloDibujoCirculo;
	private boolean dibujarGastos12Meses = false;
	private boolean dibujarNumeroTxPorCuenta = false;
	private boolean cantidadDineroPorCuenta = false;

	public void iniciarMostrarTx() {
		ventana2.setTitle("Transacciones");
		ventana2.setSize(1200, 700);
		ventana2.setResizable(true);
		ventana2.setLocationRelativeTo(null);
		ventana2.setVisible(true);
		this.agregarDatosMostrarTx("Valor", "Ultimo mes");

	}

	public void iniciarPrincipal() {

		ventana3.setTitle("Inicio de sesion");
		ventana3.setSize(550, 300);
		ventana3.setResizable(true);
		ventana3.setLocationRelativeTo(null);
		ventana3.setVisible(true);

		this.crearTablaCategorias();
		this.crearTablaCuentas();
		this.crearTablaTx();

		this.agregarDatosCuentasPrincipal();

	}

	public void iniciarVTransacciones() {
		vTransacciones.setTitle("Nueva Transaccion");
		vTransacciones.setSize(550, 240);
		vTransacciones.setResizable(true);
		vTransacciones.setVisible(true);
		vTransacciones.setLocationRelativeTo(null);
		vTransacciones.ingresarDatosCombo(modificador.getArrayTablaCategorias());
		vTransacciones.ingresarDatosCombo2(modificador.getArrayTablaCuentas());

	}

	public void iniciarVCategoria() {
		vCategoria.setTitle("Agregar Categoria");
		vCategoria.setSize(480, 180);
		vCategoria.setResizable(true);
		vCategoria.setVisible(true);
		vCategoria.setLocationRelativeTo(null);

	}

	public void iniciarVCuenta() {
		vCuenta.setTitle("Agregar Cuenta");
		vCuenta.setSize(480, 180);
		vCuenta.setResizable(true);
		vCuenta.setVisible(true);
		vCuenta.setLocationRelativeTo(null);

	}


	public void crearTablaTx() {

		modificador.crearTablaTx();

	}

	public void crearTablaCuentas() {
		modificador.crearTablaCuentas();
	}

	public void insertarNuevaTransaccion(String titulo, String categoria, String cuenta, double valor, String fecha,
			String descripcion) {

		modificador.insertarNuevaTransaccion(titulo, categoria, cuenta, valor, fecha, descripcion);

	}

	public void crearTablaCategorias() {
		modificador.crearTablaCategorias();
	}

	public void insertarNuevaCategoria(String categoria) {
		modificador.insertarNuevaCategoria(categoria);
		// TODO Auto-generated method stub

	}

	public void insertarNuevaCuenta(String cuenta) {
		modificador.insertarNuevaCuenta(cuenta);
	}

	public ResultSet getTablaCuentas() {
		return modificador.getTablaCuentas();
	}

	public ResultSet getTablaTx(String nombre, String filtro) {
		return modificador.getTablaTx(nombre, filtro);
	}

	public void agregarDatosMostrarTx(String nombre, String filtro) {
		JTable table = new JTable();
		;

		table = ventana2.getTable();

		table.setModel(modificador.cargarDatosTabla(nombre, filtro));

		ventana2.setTable(table);

	}

	public void agregarDatosCuentasPrincipal() {
		JTable table = new JTable();
		table = ventana3.getTable();
		table.setModel(modificador.cargarDatosTablaCuentasPrincipal());
		ventana3.setTable(table);
	}

	public int getGastosTotalMes(int mes, int ano) {
		return modificador.getGastosTotalMes(mes, ano);
	}
	
	public int getTxTotales(String cuenta) {
		return modificador.getTxTotales(cuenta);
	}
	public void iniciarVRetiro() {
		vRetiro.setTitle("Nueva Transaccion");
		vRetiro.setSize(550, 240);
		vRetiro.setResizable(true);
		vRetiro.setVisible(true);
		vRetiro.setLocationRelativeTo(null);
		vRetiro.ingresarDatosCombo(modificador.getArrayTablaCategorias());
		vRetiro.ingresarDatosCombo2(modificador.getArrayTablaCuentas());

	}

	public void iniciarVGraficos() {
		vTxCuenta.setTitle("Reportes");
		vTxCuenta.setSize(1400,750);
		vTxCuenta.setResizable(true);
		vTxCuenta.setLocationRelativeTo(null);
		vTxCuenta.setVisible(true);
		vTxCuenta.getModelo().graficar("Numero de transacciones por cuenta");
		
	}

	public void dibujarGrafico12Meses() {
		dobleBuffer = new BufferedImage(vTxCuenta.getLienzo().getWidth(), vTxCuenta.getLienzo().getHeight(),BufferedImage.TYPE_INT_ARGB);
		
		Canvas lienzo = vTxCuenta.getLienzo();
        
		//DIBUJAR SOBRE EL BUFFER
        Graphics lapiz = dobleBuffer.getGraphics(); 
        
        //FONDO
        lapiz.setColor(Color.white);  
        lapiz.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
        
        //VARIABLES
        int cantMeses = 12;
        int sumEspacios = 140;
        int datoLimite = 0;
        int dato = 0;
        int alturaDato = 0;
        int contador = 0;
        String stringContador = "";
        String datoBig = "";
        int limiteBarra = 560;
        String stringDato = null;
        
        //DIBUJAR EJES
        lapiz.setColor(Color.black);
        int puntoOrigenX = 120;
        int puntoOrigenY = 600;
        int puntoFinalYX = 120;
        int puntoFinalYY = 20;
        int puntoFinalXX = 1350;
        int puntoFinalXY = 600;
        lapiz.drawLine(puntoOrigenX,puntoOrigenY,puntoFinalXX,puntoFinalXY);  // EJE X
        lapiz.drawLine(puntoOrigenX,puntoOrigenY,puntoFinalYX,puntoFinalYY);  // EJE Y
        
        //MOSTRAR MESES
        lapiz.setColor(Color.black);
        lapiz.drawString("Enero", 140, 625);
        lapiz.drawString("Febero", 230, 625);
        lapiz.drawString("Marzo", 320, 625);
        lapiz.drawString("Abril", 410, 625);
        lapiz.drawString("Mayo", 500, 625);
        lapiz.drawString("Junio", 590, 625);
        lapiz.drawString("Julio", 680, 625);
        lapiz.drawString("Agosto", 770, 625);
        lapiz.drawString("Septiembre", 860, 625);
        lapiz.drawString("Octubre", 950, 625);
        lapiz.drawString("Noviembre", 1040, 625);
        lapiz.drawString("Diciembre", 1130, 625);
        
        //CALCULAR DATO LIMITE
        for (int cont = 1 ; cont <= cantMeses ; cont++) {
        	if (this.getGastosTotalMes(cont, 2018) > datoLimite) {
        		datoLimite = this.getGastosTotalMes(cont, 2018);
        	}
        }
        
        //DIBUJAR DIVISIONES EJE Y
        lapiz.setColor(Color.black);
        int intervalo = 40;
        for (int cont = 0 ; cont < 5; cont ++) {
        	String stringDatoLimiteCopy = Integer.toString(datoLimite-(cont*(datoLimite/5)));
            lapiz.drawString("$"+stringDatoLimiteCopy, 30, intervalo+4);
            lapiz.drawLine(puntoOrigenX-10,intervalo,puntoOrigenX+4,intervalo);
            intervalo += 112;
        }
        CalculosTxCuenta c = new CalculosTxCuenta(lienzo);
        //DIBUJAR BARRAS
        for(int cont = 0 ; cont <= cantMeses ; cont++){
	        BigInteger Numero = new BigInteger (Integer.toString(this.getGastosTotalMes(cont+1, 2018)));
        	dato = Numero.intValue();
	        if (dato != 0){
	        	stringDato = Integer.toString(dato);
	        	Numero = Numero.multiply(BigInteger.valueOf(limiteBarra));
	            Numero = Numero.divide(BigInteger.valueOf(datoLimite));
	            alturaDato = Numero.intValue();
	            lapiz.setColor(c.getColor(cont));
	            lapiz.fillRect (sumEspacios, 40 + (int)(560-alturaDato), 50, (int)(alturaDato));
	            stringDato = Integer.toString(dato);
	            lapiz.setColor(Color.red);
	            lapiz.drawString("$"+stringDato, sumEspacios, 35 + (int)(560-alturaDato));
	        } else {
	        	alturaDato = 0;
	        	stringDato = Integer.toString(dato);
	        	lapiz.setColor(Color.red);
	        	lapiz.drawString("$"+stringDato, sumEspacios, 35 + (int)(560-alturaDato));
	        }
	        sumEspacios += 90;
        }
		Graphics pincel = lienzo.getGraphics();
	
		try {
			pincel.drawImage(dobleBuffer, 0, 0, lienzo);
			}catch (Exception e){
				
			}
		
}

	public BufferedImage dibujarGrafico12MesesHTML(String ancho, String alto) {
		int anchoPantalla= Integer.parseInt(ancho);
		int altoPantalla=Integer.parseInt(alto);
		
		dobleBuffer = new BufferedImage(anchoPantalla, altoPantalla,BufferedImage.TYPE_INT_ARGB);
		
		Canvas lienzo = new Canvas();
				lienzo.setSize(new Dimension(anchoPantalla,altoPantalla));

        
		//DIBUJAR SOBRE EL BUFFER
        Graphics lapiz = dobleBuffer.getGraphics(); 
        
        //FONDO
        lapiz.setColor(Color.white);  
        lapiz.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
        
        //VARIABLES
        int cantMeses = 12;
        int sumEspacios = 140;
        int datoLimite = 0;
        int dato = 0;
        int alturaDato = 0;
        int contador = 0;
        String stringContador = "";
        String datoBig = "";
        int limiteBarra = 560;
        String stringDato = null;
        
        //DIBUJAR EJES
        lapiz.setColor(Color.black);
        int puntoOrigenX = 120;
        int puntoOrigenY = 600;
        int puntoFinalYX = 120;
        int puntoFinalYY = 20;
        int puntoFinalXX = 1350;
        int puntoFinalXY = 600;
        lapiz.drawLine(puntoOrigenX,puntoOrigenY,puntoFinalXX,puntoFinalXY);  // EJE X
        lapiz.drawLine(puntoOrigenX,puntoOrigenY,puntoFinalYX,puntoFinalYY);  // EJE Y
        
        //MOSTRAR MESES
        lapiz.setColor(Color.black);
        lapiz.drawString("Enero", 140, 625);
        lapiz.drawString("Febero", 230, 625);
        lapiz.drawString("Marzo", 320, 625);
        lapiz.drawString("Abril", 410, 625);
        lapiz.drawString("Mayo", 500, 625);
        lapiz.drawString("Junio", 590, 625);
        lapiz.drawString("Julio", 680, 625);
        lapiz.drawString("Agosto", 770, 625);
        lapiz.drawString("Septiembre", 860, 625);
        lapiz.drawString("Octubre", 950, 625);
        lapiz.drawString("Noviembre", 1040, 625);
        lapiz.drawString("Diciembre", 1130, 625);
        
        //CALCULAR DATO LIMITE
        for (int cont = 1 ; cont <= cantMeses ; cont++) {
        	if (this.getGastosTotalMes(cont, 2018) > datoLimite) {
        		datoLimite = this.getGastosTotalMes(cont, 2018);
        	}
        }
        
        //DIBUJAR DIVISIONES EJE Y
        lapiz.setColor(Color.black);
        int intervalo = 40;
        for (int cont = 0 ; cont < 5; cont ++) {
        	String stringDatoLimiteCopy = Integer.toString(datoLimite-(cont*(datoLimite/5)));
            lapiz.drawString("$"+stringDatoLimiteCopy, 30, intervalo+4);
            lapiz.drawLine(puntoOrigenX-10,intervalo,puntoOrigenX+4,intervalo);
            intervalo += 112;
        }
        CalculosTxCuenta c = new CalculosTxCuenta(lienzo);
        //DIBUJAR BARRAS
        for(int cont = 0 ; cont <= cantMeses ; cont++){
	        BigInteger Numero = new BigInteger (Integer.toString(this.getGastosTotalMes(cont+1, 2018)));
        	dato = Numero.intValue();
	        if (dato != 0){
	        	stringDato = Integer.toString(dato);
	        	Numero = Numero.multiply(BigInteger.valueOf(limiteBarra));
	            Numero = Numero.divide(BigInteger.valueOf(datoLimite));
	            alturaDato = Numero.intValue();
	            lapiz.setColor(c.getColor(cont));
	            lapiz.fillRect (sumEspacios, 40 + (int)(560-alturaDato), 50, (int)(alturaDato));
	            stringDato = Integer.toString(dato);
	            lapiz.setColor(Color.red);
	            lapiz.drawString("$"+stringDato, sumEspacios, 35 + (int)(560-alturaDato));
	        } else {
	        	alturaDato = 0;
	        	stringDato = Integer.toString(dato);
	        	lapiz.setColor(Color.red);
	        	lapiz.drawString("$"+stringDato, sumEspacios, 35 + (int)(560-alturaDato));
	        }
	        sumEspacios += 90;
        }
		Graphics pincel = lienzo.getGraphics();
	
		try {
			pincel.drawImage(dobleBuffer, 0, 0, lienzo);
			}catch (Exception e){
				
			}
		return dobleBuffer;
		
}
public void dibujarGraficoTxPorCuenta() {
	
		dobleBuffer = new BufferedImage(vTxCuenta.getLienzo().getWidth(), vTxCuenta.getLienzo().getHeight(),
			BufferedImage.TYPE_INT_ARGB);

	
		Canvas lienzo;
		lienzo = vTxCuenta.getLienzo();
		CalculosTxCuenta c = new CalculosTxCuenta(lienzo);
		Double valorLimite = 0.0;
		
		Graphics lapiz = dobleBuffer.getGraphics(); // bibujar sobre el doble buffer
		//Fondo
			lapiz.setColor(Color.white);
			lapiz.fillRect(0, 0, vTxCuenta.getWidth(), vTxCuenta.getHeight());

		//Lineas ejes
			lapiz.setColor(Color.black);
			//Linea horizontal
			lapiz.drawLine(50, lienzo.getHeight()-50, lienzo.getWidth()-50, lienzo.getHeight()-50);
			//Linea vertical
			
			lapiz.drawLine(50, 50, 50, lienzo.getHeight()-50);
		//Barra
			lapiz.setColor(Color.BLUE);
			//Punto origen x,y , ancho,cuanto abajo
			
		
			
		//Limites ejes
			
			lapiz.setColor(Color.BLACK);
			//Limites eje y
			
				//Lineas en orden de arriba abajo
				//Linea
				lapiz.drawLine(45, c.posY(c.getvalorMax()*c.getFactorMultiplicativo()), 
						55,c.posY(c.getvalorMax()*c.getFactorMultiplicativo()) );
				
				//Numero
				valorLimite=c.getvalorMax()*8.0/8.0;
				lapiz.drawString(Double.toString(valorLimite), 
						15, c.posY(c.getvalorMax()*c.getFactorMultiplicativo())
						);
	
				
				lapiz.drawLine(
						45, c.posY(7*(c.getvalorMax()*c.getFactorMultiplicativo())/8), 
						55, c.posY(7*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
						);
				
				valorLimite=c.getvalorMax()*7.0/8.0;
				lapiz.drawString(Double.toString(valorLimite),
						15, c.posY(7*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
						);
				
				lapiz.drawLine(
						45, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/4), 
						55, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/4)
						);
				
				valorLimite=c.getvalorMax()*3.0/4.0;
				lapiz.drawString(Double.toString(valorLimite), 
						15, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/4)
						);
				
				lapiz.drawLine(
						45, c.posY(5*(c.getvalorMax()*c.getFactorMultiplicativo())/8), 
						55, c.posY(5*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
						);
				
				valorLimite=c.getvalorMax()*5.0/8.0;
				lapiz.drawString(Double.toString(valorLimite), 
						15, c.posY(5*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
						);
				
				lapiz.drawLine(
						45, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/2), 
						55, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/2)
						);
				
				valorLimite=c.getvalorMax()*1.0/2.0;
				lapiz.drawString(Double.toString(valorLimite), 
						15, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/2)
						);
				
				lapiz.drawLine(
						45, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/8), 
						55, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
						);
	
				valorLimite=c.getvalorMax()*3.0/8.0;
				lapiz.drawString(Double.toString(valorLimite), 
						15, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
						);
				
				lapiz.drawLine(
						45, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/4), 
						55, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/4)
						);
				
				valorLimite=c.getvalorMax()*1.0/4.0;
				lapiz.drawString(Double.toString(valorLimite), 
						15, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/4)
						);
				
				lapiz.drawLine(
						45, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/8), 
						55, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/8)
						);
				
				valorLimite=c.getvalorMax()*1.0/8.0;
				lapiz.drawString(Double.toString(valorLimite), 
						15, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/8)
						);
				
			
			
			//Limites verticales
			
				int posX=5;
				int posY=0;
				
				//Dibujo rectangulos de datos
					
					for (int i=0 ; i< c.getArrayCuentas().size(); i++) {
						//Barras rectangulares
						lapiz.setColor(c.getColor(i));
						lapiz.fillRect(c.posX(posX),
								c.posY(modificador.getTxTotales(c.getArrayCuentas().get(i).toString())*c.getFactorMultiplicativo()),c.ancho(),
								modificador.getTxTotales(c.getArrayCuentas().get(i).toString())*c.getFactorMultiplicativo()
								);
						posX=posX+5+c.ancho();
					}
		
				//Dibujo nombres de cuentas
					posX=8;
					posY=0;
					for (int i=0 ; i< c.getArrayCuentas().size(); i++) {
						lapiz.setColor(Color.BLACK);	
						if(c.getArrayCuentas().get(i).toString().equals("Total") == false) {
							lapiz.drawString(c.getArrayCuentas().get(i).toString(), c.posX(posX), c.posY(-15));
							posX=posX+10+c.ancho();
						}
					}
				//Dibujo valor de la barra
					posX=0;
					posY=0;
					for (int i=0 ; i< c.getArrayCuentas().size(); i++) {
						lapiz.setColor(Color.BLACK);	
						if(c.getArrayCuentas().get(i).toString().equals("Total") == false) {
							lapiz.drawString((Integer.toString(modificador.getTxTotales(c.getArrayCuentas().get(i).toString()))), 
									c.posX(posX+c.ancho()/2),
									c.posY(5+modificador.getTxTotales(c.getArrayCuentas().get(i).toString())*c.getFactorMultiplicativo()));
							posX=posX+c.ancho()+5;
						}
					}
			
		
			// todo dibujado en el doble buffer, pintarlo al canvas
		Graphics pincel = lienzo.getGraphics();
		try {
		pincel.drawImage(dobleBuffer, 0, 0, lienzo);
		}catch (Exception e){
		}
	}

public BufferedImage dibujarGraficoTxPorCuentaHTML(String anchoPantallaS, String altoPantallaS) {
	
	int anchoPantalla= Integer.parseInt(anchoPantallaS);
	int altoPantalla=Integer.parseInt(altoPantallaS);
	dobleBuffer = new BufferedImage(anchoPantalla, altoPantalla,
		BufferedImage.TYPE_INT_ARGB);


	Canvas lienzo;
	lienzo = new Canvas();
	lienzo.setSize(new Dimension(anchoPantalla,altoPantalla));
	CalculosTxCuenta c = new CalculosTxCuenta(lienzo);
	Double valorLimite = 0.0;
	
	Graphics lapiz = dobleBuffer.getGraphics(); // bibujar sobre el doble buffer
	//Fondo
		lapiz.setColor(Color.white);
		lapiz.fillRect(0, 0, anchoPantalla, altoPantalla);

	//Lineas ejes
		lapiz.setColor(Color.black);
		//Linea horizontal
		lapiz.drawLine(50, lienzo.getHeight()-50, lienzo.getWidth()-50, lienzo.getHeight()-50);
		//Linea vertical
		
		lapiz.drawLine(50, 50, 50, lienzo.getHeight()-50);
	//Barra
		lapiz.setColor(Color.BLUE);
		//Punto origen x,y , ancho,cuanto abajo
		
	
		
	//Limites ejes
		
		lapiz.setColor(Color.BLACK);
		//Limites eje y
		
			//Lineas en orden de arriba abajo
			//Linea
			lapiz.drawLine(45, c.posY(c.getvalorMax()*c.getFactorMultiplicativo()), 
					55,c.posY(c.getvalorMax()*c.getFactorMultiplicativo()) );
			
			//Numero
			valorLimite=c.getvalorMax()*8.0/8.0;
			lapiz.drawString(Double.toString(valorLimite), 
					15, c.posY(c.getvalorMax()*c.getFactorMultiplicativo())
					);

			
			lapiz.drawLine(
					45, c.posY(7*(c.getvalorMax()*c.getFactorMultiplicativo())/8), 
					55, c.posY(7*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
					);
			
			valorLimite=c.getvalorMax()*7.0/8.0;
			lapiz.drawString(Double.toString(valorLimite),
					15, c.posY(7*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
					);
			
			lapiz.drawLine(
					45, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/4), 
					55, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/4)
					);
			
			valorLimite=c.getvalorMax()*3.0/4.0;
			lapiz.drawString(Double.toString(valorLimite), 
					15, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/4)
					);
			
			lapiz.drawLine(
					45, c.posY(5*(c.getvalorMax()*c.getFactorMultiplicativo())/8), 
					55, c.posY(5*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
					);
			
			valorLimite=c.getvalorMax()*5.0/8.0;
			lapiz.drawString(Double.toString(valorLimite), 
					15, c.posY(5*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
					);
			
			lapiz.drawLine(
					45, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/2), 
					55, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/2)
					);
			
			valorLimite=c.getvalorMax()*1.0/2.0;
			lapiz.drawString(Double.toString(valorLimite), 
					15, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/2)
					);
			
			lapiz.drawLine(
					45, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/8), 
					55, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
					);

			valorLimite=c.getvalorMax()*3.0/8.0;
			lapiz.drawString(Double.toString(valorLimite), 
					15, c.posY(3*(c.getvalorMax()*c.getFactorMultiplicativo())/8)
					);
			
			lapiz.drawLine(
					45, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/4), 
					55, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/4)
					);
			
			valorLimite=c.getvalorMax()*1.0/4.0;
			lapiz.drawString(Double.toString(valorLimite), 
					15, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/4)
					);
			
			lapiz.drawLine(
					45, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/8), 
					55, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/8)
					);
			
			valorLimite=c.getvalorMax()*1.0/8.0;
			lapiz.drawString(Double.toString(valorLimite), 
					15, c.posY((c.getvalorMax()*c.getFactorMultiplicativo())/8)
					);
			
		
		
		//Limites verticales
		
			int posX=5;
			int posY=0;
			
			//Dibujo rectangulos de datos
				
				for (int i=0 ; i< c.getArrayCuentas().size(); i++) {
					//Barras rectangulares
					lapiz.setColor(c.getColor(i));
					lapiz.fillRect(c.posX(posX),
							c.posY(modificador.getTxTotales(c.getArrayCuentas().get(i).toString())*c.getFactorMultiplicativo()),c.ancho(),
							modificador.getTxTotales(c.getArrayCuentas().get(i).toString())*c.getFactorMultiplicativo()
							);
					posX=posX+5+c.ancho();
				}
	
			//Dibujo nombres de cuentas
				posX=8;
				posY=0;
				for (int i=0 ; i< c.getArrayCuentas().size(); i++) {
					lapiz.setColor(Color.BLACK);	
					if(c.getArrayCuentas().get(i).toString().equals("Total") == false) {
						lapiz.drawString(c.getArrayCuentas().get(i).toString(), c.posX(posX), c.posY(-15));
						posX=posX+10+c.ancho();
					}
				}
			//Dibujo valor de la barra
				posX=0;
				posY=0;
				for (int i=0 ; i< c.getArrayCuentas().size(); i++) {
					lapiz.setColor(Color.BLACK);	
					if(c.getArrayCuentas().get(i).toString().equals("Total") == false) {
						lapiz.drawString((Integer.toString(modificador.getTxTotales(c.getArrayCuentas().get(i).toString()))), 
								c.posX(posX+c.ancho()/2),
								c.posY(5+modificador.getTxTotales(c.getArrayCuentas().get(i).toString())*c.getFactorMultiplicativo()));
						posX=posX+c.ancho()+5;
					}
				}
		
	
		// todo dibujado en el doble buffer, pintarlo al canvas
	Graphics pincel = lienzo.getGraphics();
	try {
	pincel.drawImage(dobleBuffer, 0, 0, lienzo);
	}catch (Exception e){
	}
	return dobleBuffer;
}
	
private void dibujarcantidadDineroPorCuenta() {
	dobleBuffer = new BufferedImage(vTxCuenta.getLienzo().getWidth(), vTxCuenta.getLienzo().getHeight(),
			BufferedImage.TYPE_INT_ARGB);
	
	Canvas lienzo = vTxCuenta.getLienzo();
	Graphics lapiz = dobleBuffer.getGraphics(); 
	DiagramaCircular c = new DiagramaCircular(lienzo);
	int cantCuentas=c.getCantCuentas();
	ArrayList<String> nombreCuentas=c.getArrayCuentas();
	//float startAngle=0;
	ArrayList<Double> coord2=c.getPuntos();
	ArrayList<Double> porc= c.getporc();
	Color[] colores = {new Color(192, 57, 43),
					   new Color (95, 75, 139),
					   new Color(195, 155, 211),
					   new Color(84, 153, 199),
					   new Color(72, 201, 176),
					   new Color(45, 152, 98),
					   new Color(220, 118, 51),
					   new Color(244, 208, 63),
					   new Color(189, 195, 199),
					   new Color(192, 57, 43),
					   new Color(135, 165, 214)};
	
	DecimalFormat df = new DecimalFormat("#.00");
	
	//Fondo
		lapiz.setColor(Color.white);
		lapiz.fillRect(0, 0, vTxCuenta.getWidth(), vTxCuenta.getHeight());

	
	//graficador
	int sumaAng=0;
	int sumaEspacios=0;
	//System.out.print("tama�ocantcuentas"+ nombreCuentas.size());
	

	for(int i=0;i<cantCuentas;i++) {
		int angulo=0;
		lapiz.setColor(colores[i]);
		
		if((coord2.get(i) - Double.valueOf(coord2.get(i)).intValue())>0.5){
			angulo=Double.valueOf(coord2.get(i)).intValue()+1;
		}
		else {
			angulo=Double.valueOf(coord2.get(i)).intValue();
		}
		lapiz.fillArc(c.getX(), c.getY(), c.getDiametro(),c.getDiametro(),sumaAng, angulo);
		sumaAng+=angulo;
		
		if(i<cantCuentas) {
			lapiz.fillRect(c.getxRecuadro()-30,c.getyRecuadro()-10+sumaEspacios,20,10);
			lapiz.setColor(Color.WHITE);
			
			lapiz.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			lapiz.setColor(Color.BLACK);
			lapiz.drawString( df.format(porc.get(i)) +"%,"+ nombreCuentas.get(i),c.getxRecuadro(),c.getyRecuadro()+sumaEspacios);
			sumaEspacios+=20;
		}
		else {
		
		}
		
	}
	

	Graphics pincel = lienzo.getGraphics();
	try {
		pincel.drawImage(dobleBuffer, 0, 0, lienzo);
		}catch (Exception e){
		}
}
public BufferedImage dibujarcantidadDineroPorCuentaHTML(String ancho, String alto) {
	int anchoPantalla= Integer.parseInt(ancho);
	int altoPantalla= Integer.parseInt(alto);

	
	dobleBuffer = new BufferedImage(anchoPantalla, altoPantalla,
			BufferedImage.TYPE_INT_ARGB);
	
	Canvas lienzo = new Canvas();
		lienzo.setSize(new Dimension(anchoPantalla,altoPantalla));

	Graphics lapiz = dobleBuffer.getGraphics(); 
	DiagramaCircular c = new DiagramaCircular(lienzo);
	int cantCuentas=c.getCantCuentas();
	ArrayList<String> nombreCuentas=c.getArrayCuentas();
	//float startAngle=0;
	ArrayList<Double> coord2=c.getPuntos();
	ArrayList<Double> porc= c.getporc();
	Color[] colores = {new Color(192, 57, 43),
					   new Color (95, 75, 139),
					   new Color(195, 155, 211),
					   new Color(84, 153, 199),
					   new Color(72, 201, 176),
					   new Color(45, 152, 98),
					   new Color(220, 118, 51),
					   new Color(244, 208, 63),
					   new Color(189, 195, 199),
					   new Color(192, 57, 43),
					   new Color(135, 165, 214)};
	
	DecimalFormat df = new DecimalFormat("#.00");
	
	//Fondo
		lapiz.setColor(Color.white);
		lapiz.fillRect(0, 0, anchoPantalla, altoPantalla);

	
	//graficador
	int sumaAng=0;
	int sumaEspacios=0;
	//System.out.print("tama�ocantcuentas"+ nombreCuentas.size());
	

	for(int i=0;i<cantCuentas;i++) {
		int angulo=0;
		lapiz.setColor(colores[i]);
		
		if((coord2.get(i) - Double.valueOf(coord2.get(i)).intValue())>0.5){
			angulo=Double.valueOf(coord2.get(i)).intValue()+1;
		}
		else {
			angulo=Double.valueOf(coord2.get(i)).intValue();
		}
		lapiz.fillArc(c.getX(), c.getY(), c.getDiametro(),c.getDiametro(),sumaAng, angulo);
		sumaAng+=angulo;
		
		if(i<cantCuentas) {
			lapiz.fillRect(c.getxRecuadro()-30,c.getyRecuadro()-10+sumaEspacios,20,10);
			lapiz.setColor(Color.WHITE);
			
			lapiz.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			lapiz.setColor(Color.BLACK);
			lapiz.drawString( df.format(porc.get(i)) +"%,"+ nombreCuentas.get(i),c.getxRecuadro(),c.getyRecuadro()+sumaEspacios);
			sumaEspacios+=20;
		}
		else {
		
		}
		
	}
	

	Graphics pincel = lienzo.getGraphics();
	try {
		pincel.drawImage(dobleBuffer, 0, 0, lienzo);
		}catch (Exception e){
		}
	return dobleBuffer;
}
	

	@Override
	public void run() {

		if (dibujarGastos12Meses == true ) {
			while (dibujarGastos12Meses == true) {
				this.dibujarGrafico12Meses();
				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {
				}
			}
		} else if (dibujarNumeroTxPorCuenta == true ) {
			while (dibujarNumeroTxPorCuenta == true) {
				this.dibujarGraficoTxPorCuenta();
				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {
				}
			}
		}
		
		else if(cantidadDineroPorCuenta==true) {
			while(cantidadDineroPorCuenta==true) {
				this.dibujarcantidadDineroPorCuenta();
				try {
					Thread.sleep(100);
				}catch(InterruptedException ex) {
					
				}
			}
		}
	}

	

	public void graficar(String queDibujar) {
		if (queDibujar.equals("Gastos de los ultimos 12 meses") == true) {
			dibujarGastos12Meses = true;
			cantidadDineroPorCuenta=false;
			dibujarNumeroTxPorCuenta = false;
		} else if (queDibujar.equals("Numero de transacciones por cuenta") == true) {
			dibujarNumeroTxPorCuenta = true;
			cantidadDineroPorCuenta=false;
			dibujarGastos12Meses = false;
		}
		else if (queDibujar.equals("Cantidad de Dinero por Cuenta")==true) {
			cantidadDineroPorCuenta=true;
			dibujarNumeroTxPorCuenta = false;
			dibujarGastos12Meses = false;
		}else if (queDibujar.equals("Volver") == true){
			cantidadDineroPorCuenta=false;
			dibujarNumeroTxPorCuenta = false;
			dibujarGastos12Meses = false;
		}
	
			hiloDibujoCirculo = new Thread(this);
			hiloDibujoCirculo.start();
	}

}
