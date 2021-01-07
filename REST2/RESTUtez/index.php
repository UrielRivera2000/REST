<?php 
    header('Content-Type: application/JSON');
    $metodo = $_SERVER['REQUEST_METHOD'];
    switch($metodo){
        case 'GET':
	        if($_GET['accion']=='personal'){
            	try {
					$conexion = new PDO ( "mysql:host=localhost;dbname=utez", "root", "" );
				} catch ( PDOException $e ) {
					echo $e->getMessage ();
				}
                if (isset( $_GET['id'] )) {// muestra el registro con id
                    $pstm = $conexion->prepare( 'SELECT personal.id, nombre,apellidoP, apellidoM,puesto.puesto, sueldo, fechaNac FROM personal INNER JOIN puesto ON personal.puesto = puesto.id WHERE personal.id =:p');
                    $pstm->bindParam ( ':p', $_GET ['id'] );
                    $pstm->execute ();
					$rs = $pstm->fetchALL ( PDO::FETCH_ASSOC );
                    echo json_encode ( $rs[0], JSON_PRETTY_PRINT );
                } else { // muestra todos los registros
                    $pstm = $conexion->prepare( 'SELECT personal.id, nombre,apellidoP, apellidoM,puesto.puesto, sueldo, fechaNac FROM personal INNER JOIN puesto ON personal.puesto = puesto.id' );
                    $pstm->execute ();
                    $rs = $pstm->fetchALL ( PDO::FETCH_ASSOC );
                    echo json_encode ( $rs, JSON_PRETTY_PRINT );
                }
            }else{
            	if($_GET['accion']=='puesto'){
					try {
						$conexion = new PDO ( "mysql:host=localhost;dbname=utez", "root", "" );
					} catch ( PDOException $e ) {
						echo $e->getMessage ();
					}
	                if (isset( $_GET['id'] )) {// muestra el registro con id
	                    $pstm = $conexion->prepare( 'SELECT * FROM puesto WHERE id =:p');
	                    $pstm->bindParam ( ':p', $_GET ['id'] );
	                    $pstm->execute ();
						$rs = $pstm->fetchALL ( PDO::FETCH_ASSOC );
	             	       echo json_encode ( $rs[0], JSON_PRETTY_PRINT );
	                } else { // muestra todos los registros
	                    $pstm = $conexion->prepare( 'SELECT * FROM puesto' );
	                    $pstm->execute ();
	                    $rs = $pstm->fetchALL ( PDO::FETCH_ASSOC );
	               	     echo json_encode ( $rs, JSON_PRETTY_PRINT );
               		 }
            	}
            }
            exit();
            break;
        case 'POST':
        	if($_GET['accion']=='personal'){
				try{
					$conexion = new PDO ( "mysql:host=localhost;dbname=utez","root","" );
				} catch ( PDOException $e ){
					echo $e->getMessage ();
				}
				try{
					$data = json_decode(file_get_contents("php://input"));
					$sql = "INSERT INTO personal(id, nombre, apellidoP, apellidoM,
					sueldo, puesto, fechaNac) 
					VALUES (:id, :nombre, :apellidoP, :apellidoM,
					:sueldo, :puesto, :fechaNac);";
					$statement = $conexion->prepare($sql);
					$statement->bindParam(':nombre', $data->nombre);
					$statement->bindParam(':apellidoP', $data->apellidoP);
					$statement->bindParam(':apellidoM', $data->apellidoM);
					$statement->bindParam(':sueldo', $data->sueldo);
					$statement->bindParam(':puesto', $data->puesto);
					$statement->bindParam(':fechaNac', $data->fechaNac);
					$statement->bindParam(':id', $data->id);
					$statement->execute();
					$postId = $conexion->lastInsertId();
					echo $postId;
					if ($postId) {
						$_POST['id'] = $postId;
						echo json_encode($_POST);
					}
					exit();
				}catch( PDOException $e ){
					echo "Error: ".$e->getMessage ();
				}
			}else{
				if($_GET['accion']=='puesto'){
					try{
					$conexion = new PDO ( "mysql:host=localhost;dbname=utez","root","" );
				} catch ( PDOException $e ){
					echo $e->getMessage ();
				}
				try{
					$data = json_decode(file_get_contents("php://input"));
					$sql = "INSERT INTO puesto(id, puesto, descripcion) 
					VALUES (:id, :puesto, :descripcion);";
					$statement = $conexion->prepare($sql);
					$statement->bindParam(':puesto', $data->puesto);
					$statement->bindParam(':descripcion', $data->descripcion);
					$statement->bindParam(':id', $data->id);
					$statement->execute();
					$postId = $conexion->lastInsertId();
					echo $postId;
					if ($postId) {
						$_POST['id'] = $postId;
						echo json_encode($_POST);
					}
					exit();
				}catch( PDOException $e ){
					echo "Error: ".$e->getMessage ();
					}
				}
			}
            break;
        case 'PUT':
        $data = json_decode(file_get_contents("php://input"));
        if($_GET['accion']=='personal'){
				try{
					$conexion = new PDO ( "mysql:host=localhost;dbname=utez","root","" );
				} catch ( PDOException $e ){
					echo $e->getMessage ();
				}
				try{ //Estrucutura de la sentencia
					
					$sql = "UPDATE personal
					SET nombre=:nombre, apellidoP=:apellidoP, 
						apellidoM=:apellidoM, sueldo=:sueldo, puesto=:puesto, 
						fechaNac=:fechaNac WHERE id=:id";
					$statement = $conexion->prepare($sql);
					$statement->bindParam(':nombre', $data->nombre);
					$statement->bindParam(':apellidoP', $data->apellidoP);
					$statement->bindParam(':apellidoM', $data->apellidoM);
					$statement->bindParam(':sueldo', $data->sueldo);
					$statement->bindParam(':puesto', $data->puesto);
					$statement->bindParam(':fechaNac', $data->fechaNac);
					$statement->bindParam(':id', $data->id);
					$statement->execute();
					echo json_encode($statement->execute());//Ejecuto la sentencia
					
					exit();
				}catch( PDOException $e ){
					echo "Error: ".$e->getMessage ();
				}
			}else if ($_GET['accion']=='puesto') {
				try{
					$conexion = new PDO ( "mysql:host=localhost;dbname=utez","root","" );
				} catch ( PDOException $e ){
					echo $e->getMessage ();
				}
				try{
					$sql = "UPDATE puesto 
					SET puesto=:puesto, descripcion=:descripcion
					WHERE id=:id;";
					$statement = $conexion->prepare($sql);
					$statement->bindParam(':puesto', $data->puesto);
					$statement->bindParam(':descripcion', $data->descripcion);				
					$statement->bindParam(':id', $data->id);
					echo json_encode($statement->execute());
					exit();
				}catch( PDOException $e ){
					echo "Error: ".$e->getMessage ();
				}
			}
            break; 
        case 'DELETE':
        if($_GET['accion']=='personal'){
				try{
					$conexion = new PDO ( "mysql:host=localhost;dbname=utez","root","" );
				} catch ( PDOException $e ){
					echo $e->getMessage ();
				}
				$id = $_GET['id'];
				$sql = "DELETE FROM personal WHERE id=:id";
				$statement = $conexion->prepare($sql);
				$statement->bindParam(':id', $id);
				$statement->execute();
				exit();
			}else{
				if($_GET['accion']=='puesto'){
					try{
						$conexion = new PDO ( "mysql:host=localhost;dbname=utez","root","" );
						} catch ( PDOException $e ){
							echo $e->getMessage ();
						}
						$id = $_GET['id'];
						$sql = "DELETE FROM puesto WHERE id=:id";
						$statement = $conexion->prepare($sql);
						$statement->bindParam(':id', $id);
						$statement->execute();
						exit();
					}
			}
            break;
        default:
            echo "Método no soportado;";
            break;
    }

?>