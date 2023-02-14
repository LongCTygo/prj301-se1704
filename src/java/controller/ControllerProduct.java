/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOProduct;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LongCT_
 */
public class ControllerProduct extends HttpServlet {

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
        DAOProduct dao = new DAOProduct();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerProduct</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerProduct at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("insertProduct")) {
                String pid = request.getParameter("pid"), pname = request.getParameter("pname");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                String image = request.getParameter("image"), description = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status")), cateID = Integer.parseInt(request.getParameter("CateID"));
                Product pr = new Product(pid, pname, quantity, price, image, description, status, cateID);
                int n = dao.add(pr);
                response.sendRedirect("ControllerProduct");
            }
            if (go.equals("listAll")) {
                String sql = "select * from Product as a join Category as b on a.cateID = b.cateID";
                out.print("<table border=\"1\">\n"
                        + "    <caption>Product List</caption>\n"
                        + "    <tr>\n"
                        + "        <th>Product ID</th>\n"
                        + "        <th>Category</th>\n"
                        + "        <th>Product Name</th>\n"
                        + "        <th>Quantity</th>\n"
                        + "        <th>Price</th>\n"
                        + "        <th>Image</th>\n"
                        + "        <th>Description</th>\n"
                        + "        <th>Status</th>\n"
                        + "        <th>Update</th>\n"
                        + "        <th>Delete</th>\n"
                        + "    </tr>");
                ResultSet rs = dao.getData(sql);
                try {
                    while (rs.next()) {
                        out.print("<tr> \n"
                                + "        <td>" + rs.getString(1) + "</td>\n"
                                + "        <td>" + rs.getString(10) + "</td>\n"
                                + "        <td>" + rs.getString(2) + "</td>\n"
                                + "        <td>" + rs.getInt(3) + "</td>\n"
                                + "        <td>" + rs.getDouble(4) + "</td>\n"
                                + "        <td><img src=\"" + rs.getString(5) + "\" width=\"100\" height=\"150\"></td>\n"
                                + "        <td>" + rs.getString(6) + "</td>\n"
                                + "        <td>" + (rs.getInt(7) == 1 ? "Enable" : "Disable") + "</td>\n"
                                + "        <td><a href=\"ControllerProduct?go=update&id=" + rs.getString(1) + "&cateID="+rs.getInt(8)+"\">Update</a>\n"
                                + "        <td><a href=\"ControllerProduct?go=delete&id=" + rs.getString(1) +"\">Delete</a>\n"
                                + "    </tr>");
                    }
                    out.print("</table>");
                    out.print("<a href=\"ProductManager.html\">Back</a>");
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.print("</table>");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String pid = request.getParameter("id");
                    int cateID=Integer.parseInt(request.getParameter("cateID"));
                    ResultSet rsPro = dao.getData("select * from Product where pid='" + pid + "'");
                    ResultSet rsCate = dao.getData("select * from Category ");
                    out.print("<form action=\"ControllerProduct\" method=\"post\">\n"
                            + "            <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    Category: <select name=\"CateID\">");
                    try {
                        while (rsCate.next()) {
                            out.print("<option value=\"" + rsCate.getInt(1) + "\""+(rsCate.getInt(1)==cateID?"selected":"")+">" + rsCate.getString(2) + "</option>");
                        }
                        out.print("</select>\n");
                        if (rsPro.next()) {
                            out.print("</tr>\n"              
                                    + "                <tr>\n"
                                    + "                    <td><label for=\"pid\">Pid</label></td>\n"
                                    + "                    <td><input type=\"text\" name=\"pid\" id=\"pid\" value=\""+rsPro.getString(1)+"\" readonly></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for=\"pname\">Pname</label></td>\n"
                                    + "                    <td><input type=\"text\" name=\"pname\" id=\"pname\" value=\""+rsPro.getString(2)+"\"></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for=\"quantity\">Quantity</label></td>\n"
                                    + "                    <td><input type=\"text\" name=\"quantity\" id=\"quantity\" value=\""+rsPro.getInt(3)+"\" ></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for=\"price\">Price</label></td>\n"
                                    + "                    <td><input type=\"text\" name=\"price\" id=\"price\" value=\""+rsPro.getDouble(4)+"\"></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for=\"image\">Image</label></td>\n"
                                    + "                    <td><input type=\"text\" name=\"image\" id=\"image\" value=\""+rsPro.getString(5)+"\" ></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for=\"description\">Description</label></td>\n"
                                    + "                    <td><input type=\"text\" name=\"description\" id=\"description\" value=\""+rsPro.getString(6)+"\"></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for=\"status\">Status</label></td>\n"
                                    + "                    <td><input type=\"radio\" name=\"status\" id=\"status\" value=\"1\""+(rsPro.getInt("status")==1?"checked":"")+">Enable\n"
                                    + "                        <input type=\"radio\" name=\"status\" id=\"status\" value=\"0\""+(rsPro.getInt("status")==0?"checked":"")+">Disable</td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><input type=\"submit\" value=\"Insert Product\" name=\"submit\" ></td>\n"
                                    + "                    <td><input type=\"reset\" value=\"Reset\"></td>\n"
                                    + "                </tr>\n"
                                    + "            </table>\n"
                                    + "        </form>");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.print("</select>");
                } else {
                    String pid = request.getParameter("pid"), pname = request.getParameter("pname");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    String image = request.getParameter("image"), description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status")), cateID = Integer.parseInt(request.getParameter("CateID"));
                    Product pr = new Product(pid, pname, quantity, price, image, description, status, cateID);
                    int n = dao.update(pr);
                    response.sendRedirect("ControllerProduct");
                }
            }
            if (go.equals("delete")) {
                String pid = request.getParameter("id");
                int n = dao.remove(pid);
                response.sendRedirect("ControllerProduct");
            }
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
