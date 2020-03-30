<html>
<head>

<title>Nuevo egreso</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link rel="stylesheet" href="/Proyecto/CSS/BasePaginaCSS.css">
	<link rel="icon" href="../imagenes/favicon.ico">
</head>
<body>
<h1 id="titulo">Nuevo Egreso</h1>

<form action="/Proyecto/InsertarTransaccion" method="get">
<table id="tabla">
	<%@ page import="logica.ModificarDB"%>
		<%@ page import="java.sql.ResultSet"%>
		<%@ page import="java.sql.SQLException"%>
		<%@ page import="java.sql.Statement"%>
		<%@ page import = "java.util.ArrayList" %>
		
		<%!ModificarDB db = new ModificarDB();%>
<tr>
	
		<td>Titulo</td>
		
		
		<td>
		<form action="/Proyecto/InsertarTransaccion" method="get">	
		<input type="text" class="form-control" name="titulo"></td>
	<tr>
		<td>Valor</td>
		<td><input type="number" class="form-control" min="0"  name="valorEgreso">
		</td>
	<tr>
		<td>Descripcion</td>
		<td><input type="text" class="form-control" name='descr'></td>
	<tr>
		<td>Categoria</td>
		<td><select class="form-control" name="Categorias"> 
			<%String[] arrayCategorias= db.getTablaCategorias(true); %>
			<%for (int k=0; k<arrayCategorias.length; k++){%>
				<option value="<%=arrayCategorias[k] %>"><% out.print(arrayCategorias[k]);%>	
			<%}%>
	</select></td>
	<tr>
		<td>Cuenta</td>
		<td><select class="form-control" name="Cuenta">
				<%Object[][] arrayCuentas= db.getTablaCuentas(true); %>
			
			<%//-1 para que no salga total
			for (int l=0; l<db.getCantidadCuentas()-1; l++){%>
				<option value="<%=arrayCuentas[0][l].toString()%>"><% out.print(arrayCuentas[0][l].toString());%>	
			<%}%>
		</select></td>
	<tr>
	</tr>
	</tr>
</table>

<br>
<br>
<input type="button" class="btn btn-primary"  value="Volver"
OnClick="location.href= '/Proyecto/index.jsp'">
<input type="submit" class="btn btn-success" value='Enviar' >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</form>
</html>
