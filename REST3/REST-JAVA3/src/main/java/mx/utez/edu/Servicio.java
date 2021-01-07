package mx.utez.edu;


import mx.utez.edu.modelo.Pendiente;
import mx.utez.edu.modelo.PendienteDao;
import mx.utez.edu.modelo.Usuario;
import mx.utez.edu.modelo.UsuarioDao;

import javax.jws.WebParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/usuarios")
public class Servicio {
    @GET
    @Path("/pendiente")
    // Produces: como te te lo va
    // devolver el servidor
    @Produces(MediaType.APPLICATION_JSON)


    public List<Pendiente> getPendientes(){
        List<Pendiente> pendientes = new ArrayList<>();
       try{
           pendientes = new PendienteDao().consultarPendientes();

       }catch(Exception e){
           e.printStackTrace();
       }

        return pendientes;
    }

    @GET
    @Path("/pendiente/{id}")
    // Produces: como te te lo va
    // devolver el servidor
    @Produces(MediaType.APPLICATION_JSON)


    public List<Pendiente> getPendiente(@PathParam("id") int idUsuario) throws Exception {
        List<Pendiente> pendiente = new ArrayList<>();
        try{
            pendiente = new PendienteDao().consultarPendiente(idUsuario);

        }catch(Exception e){
            e.printStackTrace();
        }

        return pendiente;
    }


    @POST
    @Path("/pendiente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Pendiente savePendiente(@WebParam Pendiente pendiente)throws Exception{

        Pendiente pendienteAdd = new PendienteDao().insertarPendiente(pendiente.getIdPendiente()
                , pendiente.getDescripcion(), pendiente.getFecha(), pendiente.getHora(), pendiente.getEstado(),
                pendiente.getIdUsuario());



        return pendienteAdd;
    }

    @PUT
    @Path("/pendiente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Pendiente updateEmployee(@WebParam Pendiente pendiente, @PathParam("id") int idPendiente)throws Exception{
        Pendiente pendienteUp = new PendienteDao().actualizarPendiente(pendiente.getDescripcion(), pendiente.getFecha(), pendiente.getHora(),
                pendiente.getEstado(), pendiente.getIdUsuario(), idPendiente);



        return pendienteUp;
    }

    @DELETE
    @Path("/pendiente/{id}")
    @Produces(MediaType.APPLICATION_JSON)


    public boolean deleteEmployee( @PathParam("id") int idPendiente)throws Exception{
        boolean pendienteElim = new PendienteDao().eliminarPendiente(idPendiente);

        return pendienteElim;
    }

    @POST
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Usuario savePendiente(@WebParam Usuario usuario)throws Exception{
        Usuario usuarioAdd = new UsuarioDao().insertarUsuario(usuario.getIdUsuario()
                , usuario.getNombre(), usuario.getApellidoP(), usuario.getPuesto());

        return usuarioAdd;
    }

}
