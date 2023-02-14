/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOCustomer;
import entity.Customer;
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
public class ControllerCustomer extends HttpServlet {

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
        DAOCustomer dao = new DAOCustomer();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerCustomer</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Hello " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//            request.getParameter("NameFromClient"); -- single value
//            request.getInitParameterNames("NameFromClient"); --array

            String go = request.getParameter("go");
            // if call sevlet direct --> go=null
            if (go == null) {
                go = "listAll";//default
            }
            if (go.equals("insertCustomer")) {
                String cid = request.getParameter("cid");
                String cname = request.getParameter("cname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String address = request.getParameter("address");
                int status = Integer.parseInt(request.getParameter("status"));
                Customer cus = new Customer(cid, cname, username, password, address, status);
                int n = dao.add(cus);
                //display after insert
                response.sendRedirect("ControllerCustomer");
            }
            if (go.equals("listAll")) {
                out.print("<table border=\"1\">\n"
                        + "    <caption>Customer List</caption>\n"
                        + "    <tr>\n"
                        + "        <th>CustomerID</th>\n"
                        + "        <th>Customer Name</th>\n"
                        + "        <th>Username</th>\n"
                        + "        <th>Address</th>\n"
                        + "        <th>Status</th>\n"
                        + "        <th>Update</th>\n"
                        + "        <th>Delete</th>\n"
                        + "    </tr>");
                Vector<Customer> vector = dao.getAll("select * from customer");
                for (Customer temp : vector) {
                    out.print(" <tr> \n"
                            + "        <td>" + temp.getCid() + "</td>\n"
                            + "        <td>" + temp.getCname() + "</td>\n"
                            + "        <td>" + temp.getUsername() + "</td>\n"
                            + "        <td>" + temp.getAddress() + "</td>\n"
                            + "        <td>" + (temp.getStatus() == 1 ? "Enable" : "Disable") + "</td>\n"
                            + "        <td><a href=\"ControllerCustomer?go=update&id=" + temp.getCid() + "\">Update</a>\n"
                            + "        <td><a href=\"ControllerCustomer?go=delete&id=" + temp.getCid() + "\">Delete</a>\n"
                            + "    </tr>");
                }
                out.print("</table>");
                out.print("<a href=\"CustomerManager.html\">Back</a>");
            }
            if (go.equals("delete")) {
                String id = request.getParameter("id");
                dao.remove(id);
                response.sendRedirect("ControllerCustomer");
            }
            if (go.equals("update")) {
                //check hien thi form hay update bang submit
                String submit = request.getParameter("submit");
                if (submit == null) {//hien thi form chua submit
                    //lay ra ban ghi can hien thi
                    String id = request.getParameter("id");
                    Vector<Customer> vec = dao.getAll("select * from Customer where cid ='" + id + "'");
                    Customer cus = vec.get(0);
                    //display
                    out.print("<form action=\"ControllerCustomer\" method=\"post\">\n"
                            + "<input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                   <td><label for=\"cid\">Cid</label></td>\n"
                            + "                   <td><input type=\"text\" name=\"cid\" id=\"cid\" value=\"" + cus.getCid() + "\" readonly></td> \n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"cname\">Customer Name</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"cname\" id=\"cname\" value=\"" + cus.getCname() + "\" ></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"username\">Username</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"username\" id=\"username\" value=\"" + cus.getUsername() + "\"></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"password\">Password</label></td>\n"
                            + "                    <td><input type=\"password\" name=\"password\" id=\"password\" value=\"" + cus.getPassword() + "\" readonly></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"address\">Address</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"address\" id=\"address\" value=\"" + cus.getAddress() + "\"></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"status\">Status</label></td>\n"
                            + "                    <td><input type=\"radio\" name=\"status\" id=\"status\" value=\"1\"  " + (cus.getStatus() == 1 ? "checked" : "") + ">Enable\n"
                            + "                        <input type=\"radio\" name=\"status\" id=\"status\" value=\"0\" " + (cus.getStatus() == 0 ? "checked" : "") + "> Disable\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><input type=\"submit\" value=\"Update Customer\" name=\"submit\" ></td>\n"
                            + "                    <td><input type=\"reset\" value=\"Reset\"></td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "        </form>");
                } else { //da submit
                    String cid = request.getParameter("cid");
                    String cname = request.getParameter("cname");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String address = request.getParameter("address");
                    int status = Integer.parseInt(request.getParameter("status"));
                    Customer cus = new Customer(cid, cname, username, password, address, status);
                    int n = dao.update(cus);
                    response.sendRedirect("ControllerCustomer");
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
