/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvcController;

import controller.ControllerProduct;
import dao.DAOCategory;
import dao.DAOProduct;
import display.ProductDisplay;
import entity.Category;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

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
            String go = request.getParameter("go");
            System.out.println(go);
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("listAll")) {
                String sql = "select * from Product as a join Category as b on a.cateID = b.cateID";
                Vector<ProductDisplay> vector = dao.getDisplay(sql);
                String titleTable = "List of Product";
                request.setAttribute("data", vector);
                request.setAttribute("title", titleTable);
                dispatch(request, response, "/adminJSP/ViewProduct.jsp");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String pid = request.getParameter("id");
                    String cateID = request.getParameter("cateid");
                    int cid = Integer.parseInt(cateID);
                    String sql = "SELECT * FROM Product WHERE pid = '" + pid + "'";
                    Vector<Product> vector = dao.getAll(sql);
                    Product pro = vector.get(0);
                    Vector<Category> cateData = new DAOCategory().getAll("SELECT * from Category");
                    request.setAttribute("data", pro);
                    request.setAttribute("cateId", cid);
                    request.setAttribute("cateData", cateData);
                    dispatch(request, response, "/adminJSP/UpdateProduct.jsp");
                } else {
                    String pid = request.getParameter("pid"), pname = request.getParameter("pname");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    String image = request.getParameter("image"), description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status")), cateID = Integer.parseInt(request.getParameter("CateID"));
                    Product pr = new Product(pid, pname, quantity, price, image, description, status, cateID);
                    int n = dao.update(pr);
                    dispatch(request, response, "ProductController");
                }
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
