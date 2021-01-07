package mx.edu.utez.modelo;

import mx.edu.utez.conexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    private final String CONSULTAR_USUARIOS = "SELECT * FROM usuario";

    private final String CONSULTAR_USUARIO = "SELECT * FROM usuario WHERE matricula = ?";

    private final String CREAR_USUARIO = "INSERT INTO usuario(matricula,nombre,apellidoP,apellidoM,fechaNac,correo,telefono) VALUES (?,?,?,?,?,?,?)";

    private final String ACTUALIZAR_USUARIO = "UPDATE usuario SET nombre = ?," +
            "apellidoP = ?, apellidoM = ?, fechaNac = ?, correo = ?, telefono = ? WHERE (matricula = ?);";

    private final String ELIMINAR_USUARIO = "DELETE FROM usuario WHERE matricula=?";

    public List<Usuario> consultarUsuarios(){
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try{
            con = Conexion.getConnection();
            pstm = con.prepareStatement(CONSULTAR_USUARIOS);
            rs = pstm.executeQuery();
            while(rs.next()){
                Usuario usuario =  new Usuario();
                usuario.setMatricula(rs.getString("matricula"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidoP(rs.getString("apellidoP"));
                usuario.setApellidoM(rs.getString("apellidoM"));
                usuario.setFechaNac(rs.getDate("fechaNac"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));

                usuarios.add(usuario);

            }
        }catch (SQLException e){
            System.out.println("Error en el metodo consultarUsuarios de la clase UsuarioDao"+e.getMessage());
        }finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones"+e.getMessage());
            }
        }

        return usuarios;
    }

    public List<Usuario> consultarUsuario(String id){
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try{
            con = Conexion.getConnection();
            pstm = con.prepareStatement(CONSULTAR_USUARIO);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while(rs.next()){
                Usuario usuario =  new Usuario();
                usuario.setMatricula(rs.getString("matricula"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidoP(rs.getString("apellidoP"));
                usuario.setApellidoM(rs.getString("apellidoM"));
                usuario.setFechaNac(rs.getDate("fechaNac"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));

                usuarios.add(usuario);

            }
        }catch (SQLException e){
            System.out.println("Error en el metodo consultarUsuario de la clase UsuarioDao"+e.getMessage());
        }finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones"+e.getMessage());
            }
        }

        return usuarios;
    }

    public Usuario insertarUsuario(String matricula, String nombre, String apellidoP, String apellidoM, Date fechaNac, String correo, String telefono){
        Usuario usuario = new Usuario();

        try{
            con = Conexion.getConnection();
            pstm = con.prepareStatement(CREAR_USUARIO);

            pstm.setString(1, matricula);
            pstm.setString(2, nombre);
            pstm.setString(3, apellidoP);
            pstm.setString(4,apellidoM);
            pstm.setDate(5,fechaNac);
            pstm.setString(6, correo);
            pstm.setString(7, telefono);
            pstm.executeUpdate();

            usuario.setMatricula(matricula);
            usuario.setNombre(nombre);
            usuario.setApellidoP(apellidoP);
            usuario.setApellidoM(apellidoM);
            usuario.setFechaNac(fechaNac);
            usuario.setCorreo(correo);
            usuario.setTelefono(telefono);

        }catch (SQLException e){
            System.out.println("Error en el metodo insertarUsuario de la clase UsuarioDao"+e.getMessage());
        }finally {
            try {
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones"+e.getMessage());
            }
        }

        return usuario;
    }

    public Usuario actualizarUsuario(String nombre, String apellidoP, String apellidoM, String correo, String telefono, Date fechaNac, String matricula){
        Usuario usuario = new Usuario();
        try {
            con = Conexion.getConnection();
            pstm = con.prepareStatement(ACTUALIZAR_USUARIO);

            pstm.setString(1,nombre);
            pstm.setString(2,apellidoP);
            pstm.setString(3,apellidoM);
            pstm.setDate(4,fechaNac);
            pstm.setString(5,correo);
            pstm.setString(6,telefono);
            pstm.setString(7,matricula);

            pstm.executeUpdate();

            usuario.setMatricula(matricula);
            usuario.setNombre(nombre);
            usuario.setApellidoP(apellidoP);
            usuario.setApellidoM(apellidoM);
            usuario.setCorreo(correo);
            usuario.setTelefono(telefono);
            usuario.setFechaNac(fechaNac);

        }catch(SQLException e){
            System.out.println("Error en el metodo actualizarUsuario de la clase UsuarioDao..."+e.getMessage());
        }finally{
            try{
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones");
            }

        }
        return usuario;
    }
    public boolean eliminarUsuario(String matricula){
        boolean var = false;
        try {
            con = Conexion.getConnection();
            pstm = con.prepareStatement(ELIMINAR_USUARIO);
            pstm.setString(1, matricula);
            pstm.executeUpdate();
            var = true;
        }catch(SQLException e){
            System.out.println("Error en el metodo eliminarUsuario de la clase UsuarioDao..."+e.getMessage());
        }finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones");
            }

        }
        return var;
    }

}
