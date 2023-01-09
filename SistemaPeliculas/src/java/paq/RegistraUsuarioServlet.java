/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package paq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import org.json.simple.parser.ParseException;

@WebServlet(name = "RegistraUsuarioServlet", urlPatterns = {"/RegistraUsuarioServlet"})
public class RegistraUsuarioServlet extends HttpServlet 
{   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, ParseException 
    {
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");
        String nombre = request.getParameter("nombre");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String genero = request.getParameter("genero");
        String correo = request.getParameter("correo");
        List<String> listaUsuarios = new ArrayList();
        
        //Cliente para crear un usuario
        Usuario nuevo = new Usuario(usuario, pass, nombre, edad, genero, correo);
        UtilBD.registraUsuario(nuevo);
        if(ClientesAPIs.creaUsuario(nuevo))
        {
            System.out.println("Usuario creado");
            
        }

        
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) 
        {
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistraUsuarioServlet</title>");    
            out.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Usuario registrados </h1>");
            listaUsuarios = ClientesAPIs.obtieneUsuarios();
            
            out.println("<div><table width=\"80%\" style=\"margin: 0 auto; border:2px solid;text-align:center\" bgcolor=\"gainsboro\">");
            out.println("<tr style=\"outline: thin solid\">");
            out.println("<th>Usuario</th>");
            out.println("</tr>");
            for(String cadaUsuario :listaUsuarios)   {
                out.println("<tr style=\"outline: thin solid\">");
                out.println("<td style=\"text-align:left\">" + cadaUsuario + "</td>");
                out.println("</tr>");
            }
            out.println("</table></div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistraUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RegistraUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistraUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RegistraUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
