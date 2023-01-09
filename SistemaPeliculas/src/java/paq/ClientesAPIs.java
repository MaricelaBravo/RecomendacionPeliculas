
package paq;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientesAPIs 
{
    
    public static boolean creaUsuario(Usuario us)
    {
        boolean creado = false;
        Form form = new Form();
        form.param("usuario", us.getUsuario());
        form.param("pass", us.getPass());
        form.param("nombre", us.getNombre());
        String edad = "" + us.getEdad();
        form.param("edad", edad);
        form.param("genero", us.getGenero());
        form.param("correo", us.getCorreo());
        
        
        Client client = ClientBuilder.newClient();
        Response response =
            client.target("http://localhost:8089/SistemaPeliculas/webresources/usuario")
            .request(MediaType.APPLICATION_FORM_URLENCODED)
            .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                    Response.class);
        if (response.getStatus() == Status.CREATED.getStatusCode()) 
        {
            //System.out.println("Usuario creado");
            creado = true;
        }
        return creado;
    }
    
    public static List<String> obtieneUsuarios() throws ParseException 
    {
        List<String> usuarios = new ArrayList();
       
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8089/SistemaPeliculas/webresources/usuario");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        JSONParser parser = new JSONParser();
        JSONArray responseJSON = (JSONArray) parser.parse(response.readEntity(String.class));

        for (int i = 0; i < responseJSON.size(); i++) 
        {
            JSONObject cadaUsuario = (JSONObject) responseJSON.get(i);
            String nombre = (String) cadaUsuario.get("nombre");
            String edad = (String) cadaUsuario.get("edad");
            String correo = (String) cadaUsuario.get("correo");
            
            usuarios.add(nombre + " " + edad + " " + correo);
        }
        return usuarios;
    }
    
}
