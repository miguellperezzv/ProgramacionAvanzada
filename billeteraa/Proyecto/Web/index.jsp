<html>

<head>
<title>Gestor financiero</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="/Proyecto/CSS/BasePaginaCSS.css">
<link rel="stylesheet" href="/Proyecto/CSS/PrincipalCSS.css">
<link rel="icon" href="imagenes/favicon.ico">
</head>
<body>

	<h1>Bienvenido a su gestor financiero</h1>
	<p>Seleccione una opcion para continuar</p>
	<div>
		<input type="button" class="btn btn-info" value="Nuevo ingreso"
			OnClick="location.href= 'Vista/Ingreso.jsp'">
		
		<input type="button" class="btn btn-info" value="Nuevo retiro" OnClick="location.href = 'Vista/Egreso.jsp'">
	</div>

	<div>
		<input type="button" class="btn btn-info" value="Agregar/eliminar cuentas"
			OnClick="location.href= 'Vista/AgregarCuenta.html'"> 
		<input
			type="button" class="btn btn-info" value="Agregar/eliminar categoria"
			OnClick="location.href= 'Vista/AgregarCategoria.html'">
	</div>

	<div>
		<input type="button" class="btn btn-info" value="Mis transacciones"
			OnClick="location.href= 'Vista/OrdenMostrarTx.html'"> 
		
		<input type="button" class="btn btn-info" value="Reportes"
			OnClick="location.href= 'Vista/Reportes.html'">
	</div>

	<br>


	<table>
		<caption>Resumen</caption>
		<tr>
			<th>Cuenta</th>
			<th>Saldo</th>
		</tr>

		<%@ page import="logica.ModificarDB"%>
		<%@ page import="java.sql.ResultSet"%>
		<%@ page import="java.sql.SQLException"%>
		<%@ page import="java.sql.Statement"%>



		<%!ModificarDB db = new ModificarDB();%>
		<%
			Object arreglo[][] = db.getTablaCuentas(true);
			System.out.println(arreglo.equals(null));
			
			
		%>


		<%
			for (int i = 0; i < db.getCantidadCuentas(); i++) {
		%>
		<tr>
			<%
				for (int j = 0; j < arreglo.length; j++) {
						if (j == 0) {
			%>
			<td>
				<%
					out.print(arreglo[j][i].toString());
				%>
			</td>
			<%
				} else {
			%>
			<td>
				<%
					out.print(arreglo[j][i]);
				/*0,0|1,0
				 0,1|1,1
				 0,2|1,2
				 0,3|1,3
				*/
				%>
			</td>
			<%
				}
					}
			%>
		</tr>
		<%
			}
		%>
	</table>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>

