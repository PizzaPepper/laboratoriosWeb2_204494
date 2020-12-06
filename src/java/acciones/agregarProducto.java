/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import DAO.DAOPersistencia;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objetosNegocio.Producto;

/**
 *
 * @author eliu
 */
public class agregarProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String Clave = request.getParameter("clave");
        String Nombre = request.getParameter("nombre");
        String Unidad = request.getParameter("unidad");

        HttpSession session = request.getSession();
        RequestDispatcher rd;
        DAOPersistencia p=new DAOPersistencia();
        String siguiente = null;

        Producto prod=new Producto(Clave, Nombre, Unidad);
        
        
        if (Nombre == null) {
            if(p.existe(prod)==true){
                session.setAttribute("errorKey", "Ya existe una clave igual");
                siguiente="CapturaClave.jsp";
            }else{
                session.setAttribute("errorKey", "");
                siguiente = "CapturaProducto.jsp";
            }
                
            
        } else {
            siguiente = "Productos.jsp";
            p.agregar(prod);
        }
        rd = request.getRequestDispatcher(siguiente);

        // Redirecciona a la página JSP siguiente
        rd.forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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