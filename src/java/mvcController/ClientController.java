/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvcController;

import dao.DAOCategory;
import dao.DAOProduct;
import display.ProductDisplay;
import entity.Category;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ClientController", urlPatterns = {"/ClientController"})
public class ClientController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            DAOProduct dao = new DAOProduct();
            DAOCategory daoCat = new DAOCategory();
            String go = request.getParameter("go");
            System.out.println(go);
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("listAll")) {
                String sql = "select * from Product as a join Category as b on a.cateID = b.cateID";
                Vector<ProductDisplay> vector = dao.getDisplay(sql);
                //Cat list
                Vector<Category> catList = daoCat.getAll("SELECT * from Category");
                String titleTable = "List of Product";
                request.setAttribute("data", vector);
                request.setAttribute("title", titleTable);
                request.setAttribute("dataMenu", catList);
                dispatch(request, response, "/client/index.jsp");
            }
            if (go.equals("display")){
                int cid = new Integer(request.getParameter("cid"));
                String sql = "select * from Product as a join Category as b on a.cateID = b.cateID"
                        + "WHERE cid = '" + cid + "'";
                Vector<ProductDisplay> vector = dao.getDisplay(sql);
                //Cat list
                Vector<Category> catList = daoCat.getAll("SELECT * from Category");
                String titleTable = "List of Product";
                request.setAttribute("data", vector);
                request.setAttribute("title", titleTable);
                request.setAttribute("dataMenu", catList);
                dispatch(request, response, "/client/index.jsp");
            }
        }
    }
    
    void dispatch(HttpServletRequest request, HttpServletResponse response, String url) 
            throws ServletException, IOException {
        //call jsp
        RequestDispatcher dispatch
                = request.getRequestDispatcher(url);
        dispatch.forward(request, response);
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
