package logica;

public class Barco {

	final int PORTAAVIONES = 0;
	private int aciertosPortaAviones=0;
	final int SUBMARINO = 1;
	private int aciertosSubmarinos=0;
	final int BUQUE = 2;
	private int aciertosBuque=0;
	final int BARCO = 3;
	private int aciertosBarco=0;
	final int LANCHA = 4;
	private int aciertosLancha=0;
	
	final byte VERTICAL = 0;
	final byte HORIZONTAL = 1;

	int tipo;
	int orientacion = 0;
	int cantidadCasillas;

	// Existiran 3barcos
	// cinco casillas consecutivas conforman un portaaviones; tres, un buque, y una
	//barco 2
	
	// casilla aislada, una lancha
	/**
	 * 
	 * @param tipo 0 portaaviones 1 buque 2 barco 3 lancha
	 */
	public Barco(int tipo) {
		orientacion = 0;
		if (tipo == PORTAAVIONES) {
			this.tipo = PORTAAVIONES;
			cantidadCasillas = 5;
		}
		if (tipo == BUQUE) {
			this.tipo = BUQUE;
			cantidadCasillas = 3;
		}
		if (tipo == BARCO) {
			this.tipo = BARCO;
			cantidadCasillas = 2;
		}
		if (tipo == LANCHA) {
			this.tipo = LANCHA;
			cantidadCasillas = 1;
		}
		if(tipo == SUBMARINO) {
			this.tipo=SUBMARINO;
			cantidadCasillas = 4;
		}
	}

	public Barco getBarco() {
		return this;
	}

	public int getTipo() {
		return tipo;
	}
	
	public int getCantidadCasillas() {
		return cantidadCasillas;
	}

	public int getAciertosPortaAviones() {
		return aciertosPortaAviones;
	}

	public int getAciertosSubmarinos() {
		return aciertosSubmarinos;
	}

	public int getAciertosBuque() {
		return aciertosBuque;
	}

	public int getAciertosBarco() {
		return aciertosBarco;
	}

	public int getAciertosLancha() {
		return aciertosLancha;
	}

	public void setAciertosPortaAviones(int aciertosPortaAviones) {
		this.aciertosPortaAviones = aciertosPortaAviones;
	}

	public void setAciertosSubmarinos(int aciertosSubmarinos) {
		this.aciertosSubmarinos = aciertosSubmarinos;
	}

	public void setAciertosBuque(int aciertosBuque) {
		this.aciertosBuque = aciertosBuque;
	}

	public void setAciertosBarco(int aciertosBarco) {
		this.aciertosBarco = aciertosBarco;
	}

	public void setAciertosLancha(int aciertosLancha) {
		this.aciertosLancha = aciertosLancha;
	}
	
}
