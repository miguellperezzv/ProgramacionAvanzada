package logica;

import java.awt.Canvas;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DiagramaCircular {
	
	private Canvas lienzo;
	private ModificarDB modificador = new ModificarDB();
	private ArrayList<String> cuentas= new ArrayList<String>();
	private ArrayList<String> saldo=new ArrayList<>();
	private int cantCuentas= modificador.getArrayCantidadCuentas()-1;
	private int saldoTotal=0;
	private int Diametro=0;
	private int x=0;
	private int y=0;
	
	private int xRecuadro=0;
	private int yRecuadro=0;
	
	private ArrayList<Double> puntos= new ArrayList<>();
	private ArrayList<Double> porc= new ArrayList<>();
	//private int saldoTotal = modificador.getCantidadTotal();
	
	
	
	public DiagramaCircular(Canvas l){
		
		lienzo=l;
		Diametro=lienzo.getHeight()-175;
		x=(lienzo.getWidth()-(lienzo.getWidth()-80));
		y=(lienzo.getHeight()-(lienzo.getHeight()-30));
		
		xRecuadro=x+400;
		yRecuadro=y+200;
		
		for(int j=0; j<cantCuentas; j++) {
			if(Double.parseDouble(modificador.getArrayTablaCuentasSaldo().get(j))<0) {
				saldo.add(modificador.getArrayTablaCuentasSaldo().get(j));
			}
			else {
				saldo.add(modificador.getArrayTablaCuentasSaldo().get(j));
				saldoTotal+=Double.parseDouble(modificador.getArrayTablaCuentasSaldo().get(j));
			}
			
		}
		
		for(int j=0; j<cantCuentas; j++) {
			cuentas.add(modificador.getArrayTablaCuentas().get(j));
		}
		
		
		for(int j=0; j<cantCuentas; j++) {
			double angulo=0;   
			
			angulo=((2*Math.PI/(saldoTotal))*Double.parseDouble(saldo.get(j)));
			if(angulo<0) {
				puntos.add( Math.toDegrees(0));
				porc.add(Math.toDegrees(0));
			}
			else {
				puntos.add( Math.toDegrees(angulo));
				porc.add(Math.toDegrees(angulo)*(100.0/360.0));
			}
			
			
			
			
		}

		this.lienzo=l;
	}
	
	public int getxRecuadro() {
		return xRecuadro;
	}

	public int getyRecuadro() {
		return yRecuadro;
	}

	public ArrayList<String>  getArrayCuentas() {
		return cuentas;
	}
	
	public ArrayList<String> getArraySaldo(){
		return saldo;
	}

	public int getCantCuentas() {
		// TODO Auto-generated method stub
		return cantCuentas;
	}
	
	public ArrayList<Double> getPuntos(){
		return puntos;
	}

	public int getDiametro() {
		return Diametro;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSaldoTotal() {
		// TODO Auto-generated method stub
		return saldoTotal;
	}

	public ArrayList<Double> getporc() {
		// TODO Auto-generated method stub
		return porc;
	}

	
}
