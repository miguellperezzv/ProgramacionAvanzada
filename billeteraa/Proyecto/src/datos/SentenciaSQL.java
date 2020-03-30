package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SentenciaSQL {
	private String url = "jdbc:sqlite:DB/BaseDatos.db";
	private Connection conn;

	public void crearTablaTx() {

		String sql = "CREATE TABLE IF NOT EXISTS usuarioTx (\n" + "	titulo text not null ,\n"
				+ "	categoria text NOT NULL, descripcion, cuenta text Not null , valor double not null, fecha String not null);";

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			// create a new table
			stmt.close();
			stmt.execute(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void crearTablaCategorias() {
		String sql = "create table if not exists usuarioCategorias (categoria text );";
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();

			stmt.execute(sql);
			stmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void crearTablaCuentas() {
		String sql = "CREATE TABLE IF NOT EXISTS usuarioCuentas (cuenta text not null , saldo double not null , primary key (cuenta));";

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarNuevaTransaccion(String titulo, String categoria, String cuenta, double valor, String fecha,
			String descripcion) {

		String sql = "insert into usuarioTx (titulo,descripcion,categoria,cuenta,valor,fecha) values ('" + titulo
				+ "','" + descripcion + "','" + categoria + "','" + cuenta + "'," + valor + ",'" + fecha + "');";

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();

			stmt.execute(sql);
			stmt.close();

			this.actualizarSaldo(cuenta, valor);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		this.insertarTotal();

	}

	public void insertarCuenta(String cuenta) {

		boolean valido = validarCuenta(cuenta);

		if (valido == false) {
			String sql = "insert into usuarioCuentas (cuenta , saldo) values ('" + cuenta + "','0');";

			try (Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					) {
				stmt.execute(sql);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		}else {

			String sql = "delete from usuarioCuentas where cuenta= ('" + cuenta + "');";

			try (Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();){
				/*try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				stmt.execute(sql);

			} catch (SQLException e ) {
				System.out.println(e.getMessage()+"hola");
			}
		}

		this.insertarTotal();
		
	}

	public void insertarCategoria(String categoria) {
		boolean valido = validarCategoria(categoria);

		if (valido == false) {
			String sql = "insert into usuarioCategorias (categoria) values ('" + categoria + "');";

			try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
				System.out.println(sql);

				stmt.execute(sql);
				

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		else {
			String sql = "delete from usuarioCategorias where categoria = ('" + categoria + "');";
			try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {

				stmt.execute(sql);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void actualizarSaldo(String cuenta, double saldo) {

		String sql = "update usuarioCuentas set saldo = saldo+" + saldo + " where cuenta = '" + cuenta + "'";
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();

			stmt.execute(sql);
			stmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public boolean validarCategoria(String categoria) {
		boolean valido = false;
		String sql = "select Categoria from usuarioCategorias";
		ResultSet rs = null;

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}

		try {
			while (rs.next()) {

				if (rs.getString("categoria").equals(categoria)) {
					valido = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return valido;
	}

	public boolean validarCuenta(String cuenta) {
		boolean valido = false;
		String sql = "select Cuenta from usuarioCuentas";
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();){
			/*try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getString("cuenta").equals(cuenta)) {
					valido = true;
				}
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}

	
		return valido;
	}

	public void insertarTotal() {
		String sql = "delete from usuarioCuentas where cuenta= ('Total');";
		try  (Connection conn = DriverManager.getConnection(url); 
				Statement stmt = conn.createStatement();){
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			stmt.execute(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		double saldoTotal=1;
		try {
			saldoTotal = this.getSaldoTotal();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sql = " insert into usuarioCuentas (cuenta,saldo) values ('Total'," + saldoTotal + ");";
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {

			stmt.execute(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public double getSaldoTotal()  {
		String sql = "select sum(saldo) from usuarioCuentas";
		ResultSet rs = null;
		double saldo = 0;
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();) {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			rs = stmt.executeQuery(sql);
			saldo = rs.getDouble(1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saldo;

	}

	//Devuelve datos de cuenta y saldo unicamente, son los datos de la tabla
	//del inicio
	public ResultSet getTablaCuentas() {
		String sql = "select cuenta,saldo from UsuarioCuentas";
		ResultSet rs = null;

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;

	}
	
	public Object[][] getTablaCuentas(boolean array){
		String sql = "select cuenta,saldo from UsuarioCuentas";
		ResultSet rs = null;
		Object tablaCuentas[][]= new Object[2][this.getCantidadCuentas()];
		int i=0;
		DecimalFormat df = new DecimalFormat("#,###,###,##0.##");
		
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tablaCuentas[0][i]=rs.getString("cuenta");
				tablaCuentas[1][i]=rs.getDouble("saldo");
				
			i++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		for (int k=0;k<this.getCantidadCuentas();k++) {
			tablaCuentas[1][k]=df.format(tablaCuentas[1][k]);
		}
		return tablaCuentas;
	}
	
	public int getCantidadCuentas() {
		String cantidadCuentasSQL="select count(*) as count from usuarioCuentas;";
		ResultSet rs = null;
		int cantidad=0;
		try {try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(cantidadCuentasSQL);
			cantidad= rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return cantidad;
	}
	
	public int getCantidadCategorias() {
		String cantidadCuentasSQL="select count(*) as count from usuarioCategorias;";
		ResultSet rs = null;
		int cantidad=0;
		try {try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(cantidadCuentasSQL);
			cantidad= rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return cantidad;
	}
	//Orden es orden alfabetico de las columnas
	//Filtro es la fecha
	public ResultSet getTablaTx(String orden, String filtro) {
		ResultSet rs = null;
		String sql = null;

		Date hoy = new Date();
		Date unasHoras = new Date();
		Date semanaAntes = new Date();
		Date mesAntes = new Date();
		Date mesSeisAntes = new Date();

		mesAntes.setMonth(hoy.getMonth() - 1);
		mesSeisAntes.setMonth(hoy.getMonth() - 6);
		semanaAntes.setDate(hoy.getDate() - 7);
		unasHoras.setHours(unasHoras.getHours() - 12);
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("es_ES"));

		System.out.println(orden + "\n");
		System.out.println(filtro);

		if (orden.equals("Nombres") == true) {

			if (filtro.equals("Ultimo mes")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesAntes) + "' and '" + formateador.format(hoy) + "' order by titulo ASC;";
				System.out.println(sql);
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 6 meses")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesSeisAntes) + "' and '" + formateador.format(hoy)
						+ "' order by titulo ASC;";
				try {try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 12 horas")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(unasHoras) + "' and '" + formateador.format(hoy)
						+ "' order by titulo ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}else if (filtro.equals("Todas las transacciones")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx order by titulo ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		} else if (orden.equals("Categorias")) {

			if (filtro.equals("Ultimo mes")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesAntes) + "' and '" + formateador.format(hoy)
						+ "' order by categoria ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 6 meses")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesSeisAntes) + "' and '" + formateador.format(hoy)
						+ "' order by categoria ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 12 horas")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(unasHoras) + "' and '" + formateador.format(hoy)
						+ "' order by categoria ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}else if (filtro.equals("Todas las transacciones")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx order by categoria ASC;";
				try {
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}

		} else if (orden.equals("Cuenta")) {
			if (filtro.equals("Ultimo mes")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesAntes) + "' and '" + formateador.format(hoy) + "' order by cuenta ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 6 meses")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesSeisAntes) + "' and '" + formateador.format(hoy)
						+ "' order by cuenta ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 12 horas")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(unasHoras) + "' and '" + formateador.format(hoy)
						+ "' order by cuenta ASC;";
			
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}else if (filtro.equals("Todas las transacciones")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx order by cuenta ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		} else if (orden.equals("Valor")) {
			if (filtro.equals("Ultimo mes")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesAntes) + "' and '" + formateador.format(hoy) + "' order by valor ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 6 meses")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesSeisAntes) + "' and '" + formateador.format(hoy)
						+ "' order by valor ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 12 horas")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(unasHoras) + "' and '" + formateador.format(hoy) + "' order by valor ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}else if (filtro.equals("Todas las transacciones")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx order by valor ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		} else if (orden.equals("Fecha")) {

			if (filtro.equals("Ultimo mes")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesAntes) + "' and '" + formateador.format(hoy) + "' order by fecha ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 6 meses")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesSeisAntes) + "' and '" + formateador.format(hoy)
						+ "' order by fecha ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 12 horas")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(unasHoras) + "' and '" + formateador.format(hoy) + "' order by fecha ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}else if (filtro.equals("Todas las transacciones")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx order by fecha ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		} else if (orden.equals("Descripcion")) {

			if (filtro.equals("Ultimo mes")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesAntes) + "' and '" + formateador.format(hoy)
						+ "' order by descripcion ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					
					rs = stmt.executeQuery(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 6 meses")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(mesSeisAntes) + "' and '" + formateador.format(hoy)
						+ "' order by descripcion ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (filtro.equals("Hace 12 horas")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx WHERE  fecha between '"
						+ formateador.format(unasHoras) + "' and '" + formateador.format(hoy)
						+ "' order by descripcion ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			
			}else if (filtro.equals("Todas las transacciones")) {
				sql = "SELECT titulo, descripcion, categoria, cuenta, valor, fecha FROM   usuarioTx order by descripcion ASC;";
				try {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}

		}

		return rs;

	}

	public ArrayList<String> getTablaCategorias() {
		String sql = "select categoria from usuarioCategorias";
		ArrayList<String> salida = new ArrayList<String>();

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			while (rs.next()) {
				salida.add(rs.getString("categoria"));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return salida;
	}

	public ArrayList<String> getArrayTablaCuentas() {
		String sql = "select cuenta,saldo from UsuarioCuentas";
		ResultSet rs = null;
		ArrayList<String> cuentas = new ArrayList<String>();

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				cuentas.add(rs.getString("cuenta"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return cuentas;

	}

	
	public int getGastosTotalMes(String mesSiguiente ,String mes, String ano, String anoSiguiente) {

		ResultSet rs = null;
		String sql;
		
		sql = "select sum(valor) as valor from usuarioTx where fecha between '" + ano + "-" + mes + "' and '"+ anoSiguiente + "-" + mesSiguiente + "' ;";

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		int suma = 0;
		try {
			suma = rs.getInt("valor");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suma;
	}

	public int getTxTotales(String cuenta) {
		int total=0;
		ResultSet rs = null;
		String sql;
		
		sql = "select valor from usuarioTx where cuenta='"+cuenta+"';";
		

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		try {
			while (rs.next()) {

			    total++;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return total;
	}

	public ArrayList<String> getArrayTablaCuentasSaldo() {
		String sql = "select cuenta,saldo from UsuarioCuentas";
		ResultSet rs = null;
		ArrayList<String> cuentas = new ArrayList<String>();

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				cuentas.add(rs.getString("saldo"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return cuentas;
	}

	public int getCantidadTotal() {
		String sql = "select saldo from UsuarioCuentas";
		ResultSet rs = null;
		int suma=0;
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				suma+=rs.getInt("Saldo");

			}
		
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return suma;
	}

	public String[] getCategoriasString() {

		String sql = "select categoria from usuarioCategorias;";
		System.out.println("CantidadCategorias: "+ this.getCantidadCategorias());
		ResultSet rs = null;
		int i=0;
		String[] array = new String[this.getCantidadCategorias()];
		
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				array[i] = rs.getString("categoria");
				
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}


		return array;
	}

}
