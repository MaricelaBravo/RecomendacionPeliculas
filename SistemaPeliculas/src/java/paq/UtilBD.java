/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilBD 
{
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost/peliculas";
    private static final String USER = "root";
    private static final String PASS = "admin";


    static Connection conn;
    private static Statement stmt;

    public static void obtieneConexion() 
    {
        try 
        {
            Class.forName(JDBC_DRIVER);
            System.out.println("Conectandose a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void registraUsuario(Usuario usu) throws ClassNotFoundException
    {
        try 
        {
            System.out.println("Para registrar " + usu.toString());
            Class.forName(JDBC_DRIVER);
            System.out.println("Conectandose a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "INSERT INTO USUARIOS VALUES (?,?,?,?,?,?)";
            PreparedStatement prep = conn.prepareStatement(query);  
            prep.setString(1,usu.getUsuario()); 
            prep.setString(2,usu.getPass());
            prep.setString(3,usu.getNombre()); 
            prep.setInt(4,usu.getEdad());
            prep.setString(5,usu.getGenero());
            prep.setString(6,usu.getCorreo());
            prep.executeUpdate();
        }
        catch(SQLException ex) 
        {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Usuario> cargaListaUsuarios() throws ClassNotFoundException
    {
        List<Usuario> lista = new ArrayList<Usuario>(); 
         try 
        {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) 
            {
                System.out.println("UtilBD ====> Conectado a la base de datos");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM USUARIOS;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) 
                {
                    String usuario = rs.getString("usuario");
                    String pass = rs.getString("pass");
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    String genero = rs.getString("genero");
                    String correo = rs.getString("correo");
                    Usuario usu = new Usuario(usuario, pass, nombre, edad, genero, correo);
                    lista.add(usu);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
