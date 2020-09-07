

function abrirDB(){
	
	try{
		db = window.openDatabase("BaseDatos2", "1.0", "DB General", 1000000)
		alert("se abre db")
	}catch(Exception){
		alert ("error")
	}
	
	db.transaction(consultaDB,errorCDB)
	db.transaction(crearTabla)
}

function crearDB(){
	db = window.openDatabase("BaseDatos2", "1.0", "DB General", 1000000)	
	alert("db creada")
	
}  

function crearTabla(tx){
	 tx.executeSql('CREATE TABLE IF NOT EXISTS usuarioCategorias (categoria)');
	 tx.executeSql('CREATE TABLE IF NOT EXISTS usuarioCuentas (cuenta, saldo)');
	 tx.executeSql('CREATE TABLE IF NOT EXISTS usuarioTx (titulo, categoria, descripcion, cuenta, valor, fecha)') 
	 
	 
	 
}

function 

function consultaDB(tx) {
 
tx.executeSql('SELECT * FROM usuarioCuentas', [], exitoCDB, errorCDB);
}

function exitoCDB(tx, results) {
//obtenemos la cantidad de filas
 
var totalcontactos = results.rows.length;
//mostramos la cantidad de contactos
 
console.log("Total Contactos: " + totalcontactos);
//Mostramos los registros en un bucle donde mostramos cada campo del array
 
for (var i=0; i<len; i++){
console.log(" Nombre = " +results.rows.item(i).cuenta + " cant = " + results.rows.item(i).saldo);
 
}
}

function errorCDB(error) {
alert("Error en la consulta SQL: "+error.code);
 
}


function alertas(){
alert("Esto es una alerta")
}


