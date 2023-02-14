/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAdmin;
import entity.Admin;
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
public class ControllerAdmin extends HttpServlet {

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
        DAOAdmin dao = new DAOAdmin();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerAdmin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerAdmin at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("insertAdmin")) {
                String admin = request.getParameter("admin");
                String password = request.getParameter("password");
                Admin ad = new Admin(admin, password);
                int n = dao.addAdmin(ad);
                response.sendRedirect("ControllerAdmin");
            }
            if (go.equals("listAll")) {
                out.print("<table border=\"1\">\n"
                        + "    <caption>Admin List</caption>\n"
                        + "    <tr>\n"
                        + "        <th>Admin</th>\n"
                        + "        <th>Update</th>\n"
                        + "        <th>Delete</th>\n"
                        + "    </tr>");
                Vector<Admin> vector = dao.getAll("select * from Admin");
                for (Admin adm : vector) {
                    out.print("<tr> \n"
                            + "        <td>" + adm.getAdmin() + "</td>\n"
                            + "        <td><a href=\"ControllerAdmin?go=update&id=" + adm.getAdmin() + "\">Update</a>\n"
                            + "        <td><a href=\"ControllerAdmin?go=delete&id=" + adm.getAdmin() + "\">Delete</a>\n"
                            + "</tr>");
                }
                out.print("</table>");
                    out.print("<a href=\"AdminManager.html\">Back</a>");
            }
            if (go.equals("delete")) {
                String admin = request.getParameter("id");
                dao.remove(admin);
                response.sendRedirect("ControllerAdmin");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String admin = request.getParameter("id");
                    Vector<Admin> vec = dao.getAll("select * from Admin where admin='" + admin + "'");
                    Admin ad = vec.get(0);
                    out.print(" <form action=\"ControllerAdmin\" method=\"post\">\n"
                            + "            <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"admin\">Admin</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"admin\" id=\"admin\" value=\""+ad.getAdmin()+"\" readonly></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"password\">Password</label></td>\n"
                            + "                    <td><input type=\"password\" name=\"password\" id=\"password\" value=\""+ad.getPassword()+"\"></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><input type=\"submit\" value=\"Update Admin\" name=\"submit\" ></td>\n"
                            + "                    <td><input type=\"reset\" value=\"Reset\"></td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "        </form>");
                } else {
                    String admin = request.getParameter("admin");
                    String password = request.getParameter("password");
                    Admin ad = new Admin(admin, password);
                    int n = dao.update(ad);
                    response.sendRedirect("ControllerAdmin");
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
