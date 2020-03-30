package logica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

public class CalculosTxCuenta {

	private Canvas lienzo;

	private ModificarDB modificador = new ModificarDB();
	private int cantidadCuentas;
	private int valorMax = 0;
	private ArrayList cuentas = modificador.getArrayTablaCuentas();

	public CalculosTxCuenta(Canvas v) {
		lienzo = v;
	}

	public int ancho() {
		return ((lienzo.getWidth() - 150) / this.getCantidadCuentas())-10;
	}

	public int posX(int x) {

		return x + 50;


	}
	public int getCantidadCuentas() {
		return cantidadCuentas=cuentas.size();
	}
	public int getvalorMax() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (modificador.getTxTotales(cuentas.get(i).toString()) > valorMax) {
				valorMax = modificador.getTxTotales(cuentas.get(i).toString());

			}
		}

		return valorMax;
	}
	
	
	
	public int posY(int y) {

		int a = lienzo.getHeight() - 50 - y;

		return a;
	}

	public ArrayList getArrayCuentas() {
		return cuentas;
	}

	public int getFactorMultiplicativo() {
		
		return (lienzo.getHeight() - 120) / this.getvalorMax();
	}
	
	public Color getColor(int i) {
		
		Color[] colores = {new Color(255, 69, 0),
				   new Color (255, 195, 0),
				   new Color(189, 183, 107),
				   new Color(153, 102, 204),
				   new Color(127, 255, 0),
				   new Color(102, 205, 170),
				   new Color(222, 184, 135),
				   new Color(184, 134, 11),
				   new Color(160, 82, 45),
				   new Color(169, 169, 169),
				   new Color(255, 182, 193)};
		
		if(i>=0 && i<=10) {
			return colores[i];
		}else 
			return colores[1];
		
		
		
	}

}
