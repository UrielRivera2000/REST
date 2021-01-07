package mx.edu.utez;

import mx.edu.utez.modelo.Usuario;
import mx.edu.utez.modelo.UsuarioDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebParam;

@Path("sisa")
public class Servicio {

    @GET
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON) //Lo que recibes del servidor
    //@Consumes(MediaType.APPLICATION_JSON) Lo que mandas del servidor

    public List<Usuario> getusuarios(){
        List<Usuario> usuarios =  new ArrayList<>();
        try {
            usuarios = new UsuarioDao().consultarUsuarios();
        }catch (Exception e){
            e.printStackTrace();
        }
        return usuarios;
    }

    @GET
    @Path("/usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON) //Lo que recibes del servidor
    //@Consumes(MediaType.APPLICATION_JSON) Lo que mandas del servidor

    public List<Usuario> getUsuario(@PathParam("id") String matricula){
        List<Usuario> usuarios =  new ArrayList<>();
        try {
            usuarios = new UsuarioDao().consultarUsuario(matricula);
        }catch (Exception e){
            e.printStackTrace();
        }
        return usuarios;
    }

    @POST
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario saveEmployee(@WebParam Usuario usuario) throws Exception{
        Usuario usuarioAdd = new UsuarioDao().insertarUsuario(usuario.getMatricula(), usuario.getNombre(), usuario.getApellidoP(),
                usuario.getApellidoM(), usuario.getFechaNac(), usuario.getCorreo(), usuario.getTelefono());

        return usuarioAdd;
    }

    @PUT
    @Path("/usuario/{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario updateUsuario(@WebParam Usuario usuario, @PathParam("matricula")String matricula) throws Exception{
        Usuario empleadoUp = new UsuarioDao().actualizarUsuario(usuario.getNombre(), usuario.getApellidoP(), usuario.getApellidoM(),
                usuario.getCorreo(), usuario.getTelefono(), usuario.getFechaNac(), matricula);
        return empleadoUp;
    }

    @DELETE
    @Path("/usuario/{matricula}")
    public boolean deleteEmployee(@PathParam("matricula")String matricula) throws Exception{
        boolean empleadoDe = new UsuarioDao().eliminarUsuario(matricula);
        return empleadoDe;
    }

}
