package mx.utez.edu.modelo;

import mx.utez.edu.conexion.Conexion;

import java.sql.*;

public class UsuarioDao {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    private final String INSERTAR_USUARIO = "INSERT INTO usuario (idUsuario, nombre, apellidoP, puesto) VALUES (?,?,?,?);";

    public Usuario insertarUsuario(int idUsuario,
                                       String nombre,
                                       String apellidoP, String puesto){
        Usuario usuario = new Usuario();
        try{
            con = Conexion.getConnection();
            pstm = con.prepareStatement(INSERTAR_USUARIO);
            pstm.setInt(1, idUsuario);
            pstm.setString(2, nombre);
            pstm.setString(3, apellidoP);
            pstm.setString(4, puesto);
            pstm.executeUpdate();

            usuario.setIdUsuario(idUsuario);
            usuario.setNombre(nombre);
            usuario.setApellidoP(apellidoP);
            usuario.setPuesto(puesto);

        }catch(SQLException e){
            System.out.println("Error en el metodo de insertar usuario de la clase usuarioDao: "+ e);
        }finally{
            try{

                pstm.close();
                con.close();
            }catch(SQLException e){
                System.out.println("Error en cerrar conexiones usuarioDao");
            }

        }
        return usuario;
    }



}
