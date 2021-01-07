package mx.utez.edu.modelo;

import mx.utez.edu.conexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PendienteDao {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private final String CONSULTAR_PENDIENTES = "SELECT * FROM pendiente;";
    private final String CONSULTA_ESPECIFICA_PENDIENTE = "SELECT * FROM pendiente WHERE idUsuario = ?;";
    private final String INSERTAR_PENDIENTE = "INSERT INTO pendiente(idPendiente,descripcion,fecha,hora,estado,idUsuario) VALUES (?,?,?,?,?,?);";
    private  final String ACTUALIZAR_PENDIENTE = "UPDATE pendiente SET descripcion=?, fecha=?, hora=?, estado = ?, idUsuario=? WHERE idPendiente = ?";
    private final String ELIMINAR_PENDIENTE= "DELETE FROM pendiente WHERE idPendiente = ?;";

    public List<Pendiente> consultarPendientes(){
        List<Pendiente> pendientes = new ArrayList<Pendiente>();
            try{
                con = Conexion.getConnection();
                pstm = con.prepareStatement(CONSULTAR_PENDIENTES);
                rs = pstm.executeQuery();
                while(rs.next()){
                    Pendiente pendiente = new Pendiente();
                    Usuario usuario = new Usuario();
                    pendiente.setIdPendiente(rs.getInt("idPendiente"));
                    pendiente.setDescripcion(rs.getString("descripcion"));
                    pendiente.setFecha(rs.getDate("fecha"));
                    pendiente.setHora(rs.getString("hora"));
                    pendiente.setEstado(rs.getInt("estado"));
                    pendiente.setIdUsuario(rs.getInt("idUsuario"));

                    pendientes.add(pendiente);

                }

            }catch(SQLException e){
                System.out.println("Erroe en el metodo de consultar pendientes de la clase PendienteDao: "+ e);
            }finally{
                try{
                    rs.close();
                    pstm.close();
                    con.close();;
                }catch(SQLException e){
                    System.out.println("Error en cerrar conexiones PendienteDao");
                }

            }
        return pendientes;
    }

    public List<Pendiente> consultarPendiente(int id){
        List<Pendiente> pendientes = new ArrayList<Pendiente>();
        try{
            con = Conexion.getConnection();
            pstm = con.prepareStatement(CONSULTA_ESPECIFICA_PENDIENTE);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            while(rs.next()){
                Pendiente pendiente = new Pendiente();
                Usuario usuario = new Usuario();
                pendiente.setIdPendiente(rs.getInt("idPendiente"));
                pendiente.setDescripcion(rs.getString("descripcion"));
                pendiente.setFecha(rs.getDate("fecha"));
                pendiente.setHora(rs.getString("hora"));
                pendiente.setEstado(rs.getInt("estado"));
                pendiente.setIdUsuario(rs.getInt("idUsuario"));

                pendientes.add(pendiente);

            }

        }catch(SQLException e){
            System.out.println("Erroe en el metodo de consultar pedniente de la clase PendienteDao: "+ e);
        }finally{
            try{
                rs.close();
                pstm.close();
                con.close();;
            }catch(SQLException e){
                System.out.println("Erro en cerrar conexiones PendienteDao");
            }

        }
        return pendientes;
    }


    public Pendiente insertarPendiente(int idPendiente,
                                String descripcion, Date fecha,
                              String hora, int estado, int idUsuario){
        Pendiente pendiente = new Pendiente();
        try{
            con = Conexion.getConnection();
            pstm = con.prepareStatement(INSERTAR_PENDIENTE);
            pstm.setInt(1, idPendiente);
            pstm.setString(2, descripcion);
            pstm.setDate(3, fecha);
            pstm.setString(4, hora);
            pstm.setInt(5, estado);
            pstm.setInt(6, idUsuario);
            pstm.executeUpdate();


            pendiente.setIdPendiente(idPendiente);
            pendiente.setDescripcion(descripcion);
            pendiente.setFecha(fecha);
            pendiente.setHora(hora);
            pendiente.setEstado(estado);
            pendiente.setIdUsuario(idUsuario);


        }catch(SQLException e){
            System.out.println("Error en el metodo de insertar pendiente de la clase PendienteDao: "+ e);
        }finally{
            try{
             
                pstm.close();
                con.close();
            }catch(SQLException e){
                System.out.println("Erro en cerrar conexiones PendienteDao");
            }

        }
        return pendiente;
    }



    public Pendiente actualizarPendiente(String descripcion, Date fecha,
                                         String hora, int estado, int idUsuario, int idPendiente){
        Pendiente pendiente = new Pendiente();
        try{
            con = Conexion.getConnection();
            pstm = con.prepareStatement(ACTUALIZAR_PENDIENTE);


            pstm.setString(1, descripcion);
            pstm.setDate(2, fecha);
            pstm.setString(3, hora);
            pstm.setInt(4, estado);
            pstm.setInt(5, idUsuario);
            pstm.setInt(6, idPendiente);
            pstm.executeUpdate();

            pendiente.setIdPendiente(idPendiente);
            pendiente.setDescripcion(descripcion);
            pendiente.setFecha(fecha);
            pendiente.setHora(hora);
            pendiente.setEstado(estado);
            pendiente.setIdUsuario(idUsuario);


        }catch(SQLException e){
            System.out.println("Error en el metodo de actualizar pendiente de la clase PendienteDao: "+ e);
        }finally{
            try{
                pstm.close();
                con.close();
            }catch(SQLException e){
                System.out.println("Error en cerrar conexiones PendienteDao");
            }

        }
        return pendiente;
    }


    public boolean eliminarPendiente(int id){

        boolean exito =  false;
        try{
            con = Conexion.getConnection();
            pstm = con.prepareStatement(ELIMINAR_PENDIENTE);
            pstm.setInt(1, id);

            pstm.executeUpdate();

            exito = true;

        }catch(SQLException e){
            System.out.println("Error en el metodo de eliminar pendiente de la clase PendienteDao: "+ e);
        }finally{
            try{
                pstm.close();
                con.close();
            }catch(SQLException e){
                System.out.println("Error en cerrar conexiones PendienteDao");
            }

        }
        return exito;
    }
}
