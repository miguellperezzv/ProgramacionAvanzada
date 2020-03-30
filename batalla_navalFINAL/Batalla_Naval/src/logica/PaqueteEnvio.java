package logica;

import java.io.Serializable;

public class PaqueteEnvio implements Serializable {
	
	private int x;
	private int y;
	private int [][] mapa1;
	private int [][] mapa2;
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int[][] getMapa1() {
		return mapa1;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setMapa1(int[][] mapa) {
		this.mapa1 = mapa;
	}
	public int[][] getMapa2() {
		return mapa2;
	}
	public void setMapa2(int[][] mapa2) {
		this.mapa2 = mapa2;
	}
	
	
}
