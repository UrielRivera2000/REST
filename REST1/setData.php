<html>
<head>
	<title>Formulario</title>
</head>
	<body>
		<form action="setData.php" method="POST">
	 	<p>Ingresa el nombre: <input type="text" name="nombre" /></p>
	 	<p>Ingresa el apellido: <input type="text" name="apellido" /></p>
	 	<p>Ingresa el posicion: <input type="text" name="posicion" /></p>
	 	<p>Ingresa el genero: <input type="text" name="genero" /></p>
	 	<p>Ingresa la fecha de nacimiento: <input type="text" name="fdn" /></p>
	 	<p>Ingresa el salario: <input type="number" name="salario" /></p>
	 	<p><input type="submit" /></p>
	</form>
	</body>
</html>



<?php
	ini_set('display_errors', 1); /*Cuando se ejecute la peticion obtendremos los datos*/
	$nombre = $_REQUEST["nombre"];
	$apellido = $_REQUEST["apellido"];
	$fdn = $_REQUEST["fdn"];
	$salario = $_REQUEST["salario"];
	$posicion = $_REQUEST["posicion"];
	$genero = $_REQUEST["genero"];

	if($nombre != "" && $apellido != "" && $fdn != "" && $salario != ""
		&& $posicion != "" && $genero != ""){

	

	try{
		$pdo = new PDO("mysql:host=127.0.0.1;dbname=staff", "root", "");
		$resultado = $pdo->prepare('INSERT INTO staff(fname, Iname, position, sex, dob,
		 salary) VALUES (:a, :b, :c, :d, :e, :f)');
		$resultado->bindParam(":a", $nombre, PDO::PARAM_STR);
		$resultado->bindParam(":b", $apellido, PDO::PARAM_STR);
		$resultado->bindParam(":c", $posicion, PDO::PARAM_STR);
		$resultado->bindParam(":d", $genero, PDO::PARAM_STR);
		$resultado->bindParam(":e", $fdn, PDO::PARAM_STR);
		$resultado->bindParam(":f", $salario, PDO::PARAM_INT);
		$resultado->execute();
		echo "Registro exitoso";
	}catch(PDOException $e){
		echo "Error " . $e->getMessage();
	}
}else{
	echo "-1";
}

?>