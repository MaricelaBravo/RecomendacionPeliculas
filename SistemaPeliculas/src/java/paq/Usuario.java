
package paq;


public class Usuario 
{
    private String usuario;
    private String pass;
    private String nombre;
    private int edad;
    private String genero;
    private String correo;

    public Usuario(String usuario, String pass, String nombre, int edad, String genero, String correo) 
    {
        this.usuario = usuario;
        this.pass = pass;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    @Override
    public String toString() {
        return "{" 
                + "\"usuario\":" + "\"" + usuario + "\""
                + ", \"pass\":" + "\"" + pass + "\""
                + ", \"nombre\":" + "\"" + nombre + "\""
                + ", \"edad\":" + "\"" + edad + "\""
                + ", \"genero\":" + "\"" + genero + "\""
                + ", \"correo\":" + "\"" + correo + "\""
                + '}';
    }

    
    
}
