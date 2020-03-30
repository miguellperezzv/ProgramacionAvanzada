package logica;

import java.awt.Component;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import datos.SentenciaSQL;

public class ModificarDB {

	private SentenciaSQL sentencias = new SentenciaSQL();

	public void crearTablaTx() {

		sentencias.crearTablaTx();

	}

	public void insertarNuevaTransaccion(String titulo, String categoria, String cuenta, double valor, String fecha , String descripcion) {
		sentencias.insertarNuevaTransaccion(titulo, categoria, cuenta, valor, fecha,descripcion);

	}

	public ResultSet getTablaCuentas() {
		return sentencias.getTablaCuentas();
	}
	
	public Object[][] getTablaCuentas(boolean cualquiera){
		return sentencias.getTablaCuentas(true);
	}
	
	public int getCantidadCuentas() {
		return sentencias.getCantidadCuentas();
	}

	public void crearTablaCuentas() {
		System.out.println("modificar linea 34 Refresco tabla");

		sentencias.crearTablaCuentas();

	}

	public void crearTablaCategorias() {
		sentencias.crearTablaCategorias();
		
	}

	public void insertarNuevaCategoria(String categoria) {
		sentencias.insertarCategoria(categoria);
	}

	public void insertarNuevaCuenta(String cuenta) {
		sentencias.insertarCuenta(cuenta);
	}

	public ResultSet getTablaTx(String nombre , String filtro) {
		// TODO Auto-generated method stub
		
		return sentencias.getTablaTx(nombre , filtro);
		
	}
	

	
	public DefaultTableModel cargarDatosTabla(String orden,String filtro) {
		ResultSet rs = sentencias.getTablaTx(orden , filtro);
		DefaultTableModel modeloTabla = new DefaultTableModel ();
		modeloTabla.setColumnIdentifiers(new Object[] {"Titulo","Descripcion","Categoria","Cuenta","Valor","Fecha"});
		
		DecimalFormat df = new DecimalFormat("#######0.##");
		try  {
			while(rs.next()) {
				modeloTabla.addRow(new Object[] {rs.getString("titulo"),rs.getString("descripcion"),rs.getString("categoria"),rs.getString("cuenta"),df.format(rs.getDouble("valor")),rs.getString("fecha")});
				

			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return modeloTabla;
	}

	public ArrayList<String> getArrayTablaCategorias() {
		// TODO Auto-generated method stub
		return sentencias.getTablaCategorias();
	}

	public DefaultTableModel cargarDatosTablaCuentasPrincipal() {
		ResultSet rs = sentencias.getTablaCuentas();
		DefaultTableModel modeloTabla = new DefaultTableModel ();
		modeloTabla.setColumnIdentifiers(new Object[] {"cuenta","saldo"});
		
		DecimalFormat df = new DecimalFormat("#,###,###,##0.##");
		System.out.println(rs.equals(null));

		try  {
			while(rs.next()) {

				modeloTabla.addRow(new Object[] {rs.getString("cuenta"),df.format(rs.getDouble("saldo"))});

			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return modeloTabla;
	}

	public ArrayList<String> getArrayTablaCuentas() {
		// TODO Auto-generated method stub
		return sentencias.getArrayTablaCuentas();
	}

	/*public void getDatosTablaFiltrados(String filtro) {
		sentencias.actualizarTablaFiltrada(filtro);
	}*/

	public int getGastosTotalMes(int mes, int ano) {
		
		String mesS = null;
		String anoS = Integer.toString(ano);
		String mesSiguienteS = null;
		String anoSiguienteS=Integer.toString(ano);
		
		if(mes>=1 && mes<=9) {
			mesS="0";
			mesS=mesS+Integer.toString(mes);
			
		}else if (mes>=10) {
			mesS=Integer.toString(mes);

		}
		
		if(mes+1>=1 && mes+1<=9) {
			mesSiguienteS="0";
			mesSiguienteS=mesSiguienteS+Integer.toString(mes+1);
			
	
		}else if (mes+1>=10 && mes+1<=11) {
			mesSiguienteS=Integer.toString(mes+1);

		}else if (mes+1>=12) {
			mesSiguienteS="0";
			mesSiguienteS+=Integer.toString(1);
			
			anoSiguienteS=Integer.toString(ano+1);
		}
		
		
		return sentencias.getGastosTotalMes(mesSiguienteS,mesS , anoS, anoSiguienteS);
	}

	public int getTxTotales(String cuenta) {
		// TODO Auto-generated method stub
		return sentencias.getTxTotales(cuenta);
	}

	public ArrayList<String> getArrayTablaCuentasSaldo() {
		// TODO Auto-generated method stub
		return sentencias.getArrayTablaCuentasSaldo();
	}

	public int getCantidadTotal() {
		// TODO Auto-generated method stub
		return (int) sentencias.getSaldoTotal();
	}

	public int getArrayCantidadCuentas() {
		// TODO Auto-generated method stub
		return sentencias.getCantidadCuentas();
	}
	
	public String[] getTablaCategorias(boolean bool) {
		return sentencias.getCategoriasString();
	}
	
	

}
