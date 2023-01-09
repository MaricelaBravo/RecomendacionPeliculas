package paq;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;

@Path("usuario")
public class UsuarioResource 
{

    @Context
    private UriInfo context;
    private static String idUsuario = "";
    private static Map<String, Usuario> listaUsuarios = new ConcurrentHashMap<String, Usuario>();
    
    public UsuarioResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSON() throws ClassNotFoundException 
    {
        List<Usuario> listaRegistrada;
        listaRegistrada = UtilBD.cargaListaUsuarios();
        for(Usuario cada: listaRegistrada)
        {
            idUsuario = cada.getUsuario();
            listaUsuarios.put(idUsuario, cada);
        }
        //Obtener solo la lista de usuarios
        String listaJSON = JSONArray.toJSONString(listaRegistrada);
        return listaJSON;
    }
    

    @GET
    @Path("{idUsuario}")
    @Produces(MediaType.TEXT_HTML)
    public String getHtml(@PathParam("idUsuario") String idUsuario) 
    {
        final Usuario usu = listaUsuarios.get(idUsuario);
        if (usu == null) 
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return usu.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postNuevoUsuario(
            @FormParam("usuario") String usuario, 
            @FormParam("pass") String pass, 
            @FormParam("nombre") String nombre, 
            @FormParam("edad") String edad, 
            @FormParam("genero") String genero,
            @FormParam("correo") String correo) throws ClassNotFoundException 
    {
        Usuario usu = new Usuario(usuario, pass, nombre, Integer.parseInt(edad), genero, correo);
        idUsuario = usuario;
        listaUsuarios.put(idUsuario, usu);
        
        UtilBD.registraUsuario(usu);
        
        System.out.println("Usuario creado " + usu.toString());
        return Response.created(URI.create("/usuario/" + usu.getUsuario())).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creaNuevoUsuario(Usuario us) throws ClassNotFoundException 
    {
        idUsuario = us.getUsuario();
        listaUsuarios.put(idUsuario, us);
        
        UtilBD.registraUsuario(us);
        
        System.out.println("Usuario creado " + us.toString());
        return Response.created(URI.create("/usuario/" + us.getUsuario())).build();
    }
   
    
}
