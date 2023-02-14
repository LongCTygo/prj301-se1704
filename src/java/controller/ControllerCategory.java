/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOCategory;
import entity.Category;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author LongCT_
 */
public class ControllerCategory extends HttpServlet {

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
        DAOCategory dao = new DAOCategory();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerCategory</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerCategory at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("insertCategory")) {
                String cateName = request.getParameter("cateName");
                int status = Integer.parseInt(request.getParameter("status"));
                Category cat = new Category(cateName, status);
                int n = dao.add(cat);
                response.sendRedirect("ControllerCategory");
            }
            if (go.equals("listAll")) {
                out.print("<table border=\"1\">\n"
                        + "    <caption>Category List</caption>\n"
                        + "    <tr>\n"
                        + "        <th>CategoryID</th>\n"
                        + "        <th>CategoryName</th>\n"
                        + "        <th>Status</th>\n"
                        + "        <th>Update</th>\n"
                        + "        <th>Delete</th>\n"
                        + "    </tr>");
                Vector<Category> vector = dao.getAll("select * from Category");
                for (Category ca : vector) {
                    out.print("<tr> \n"
                            + "        <td>" + ca.getCateId() + "</td>\n"
                            + "        <td>" + ca.getCateName() + "</td>\n"
                            + "        <td>" + (ca.getStatus() == 1 ? "Enable" : "Disable") + "</td>\n"
                            + "        <td><a href=\"ControllerCategory?go=update&id=" + ca.getCateId() + "\">Update</a>\n"
                            + "        <td><a href=\"ControllerCategory?go=delete&id=" + ca.getCateId() + "\">Delete</a>\n"
                            + "    </tr>");
                }
                out.print("</table>");
                out.print("<a href=\"CategoryManager.html\">Back</a>");
            }
            if (go.equals("delete")) {
                String catID = request.getParameter("id");
                int n = dao.remove(catID);
                response.sendRedirect("ControllerCategory");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String id = request.getParameter("id");
                    Vector<Category> vec = dao.getAll("select * from Category where cateId='" + id + "'");
                    Category cat = vec.get(0);
                    out.print("<form action=\"ControllerCategory\" method=\"post\">\n"
                            + "        <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "        <table>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"cateID\">Category ID</label></td>\n"
                            + "                <td><input type=\"text\" name=\"cateID\" id=\"cateID\" value=\"" + cat.getCateId() + "\" readonly></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"cateName\">Category Name</label></td>\n"
                            + "                <td><input type=\"text\" name=\"cateName\" id=\"cateName\" value=\"" + cat.getCateName() + "\"></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"status\">Status</label></td>\n"
                            + "                <td><input type=\"radio\" name=\"status\" id=\"status\" value=\"1\" " + (cat.getStatus() == 1 ? "checked" : "") + ">Enable\n"
                            + "                    <input type=\"radio\" name=\"status\" id=\"status\" value=\"0\" " + (cat.getStatus() == 0 ? "checked" : "") + "> Disable\n"
                            + "                </td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><input type=\"submit\" value=\"Update Category\" name=\"submit\" ></td>\n"
                            + "                <td><input type=\"reset\" value=\"Reset\"></td>\n"
                            + "            </tr>\n"
                            + "        </table>\n"
                            + "    </form>");
                } else {
                    String catID = request.getParameter("cateID");
                    int id = Integer.parseInt(catID);
                    String cateName = request.getParameter("cateName");
                    int status = Integer.parseInt(request.getParameter("status"));
                    Category cat = new Category(id, cateName, status);
                    int n = dao.update(cat);
                    response.sendRedirect("ControllerCategory");
                }
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
