/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBillDetail;
import entity.BillDetail;
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
public class ControllerBillDetail extends HttpServlet {

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
        DAOBillDetail dao = new DAOBillDetail();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerBillDetail</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerBillDetail at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("insertBillDetail")) {
                String bid = request.getParameter("bid");
                String pid = request.getParameter("pid");
                int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
                double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
                double subtotal = Double.parseDouble(request.getParameter("subtotal"));
                BillDetail bd = new BillDetail(bid, pid, buyQuantity, buyPrice, subtotal);
                int n = dao.add(bd);
                response.sendRedirect("ControllerBillDetail");
            }
            if (go.equals("listAll")) {
                out.print("<table border=\"1\">\n"
                        + "    <caption>BillDetail List</caption>\n"
                        + "    <tr>\n"
                        + "        <th>Bill ID</th>\n"
                        + "        <th>Product ID</th>\n"
                        + "        <th>Buy Quantity</th>\n"
                        + "        <th>Buy Price</th>\n"
                        + "        <th>Subtotal</th>\n"
                        + "        <th>Update</th>\n"
                        + "        <th>Delete</th>\n"
                        + "    </tr>");
                Vector<BillDetail> vector = dao.getAll("Select * from BillDetail");
                for (BillDetail b : vector) {
                    out.print("<tr> \n"
                            + "        <td>" + b.getBid() + "</td>\n"
                            + "        <td>" + b.getPid() + "</td>\n"
                            + "        <td>" + b.getBuyQuantity() + "</td>\n"
                            + "        <td>" + b.getBuyPrice() + "</td>\n"
                            + "        <td>" + b.getSubtotal() + "</td>\n"
                            + "        <td><a href=\"ControllerBillDetail?go=update&bid=" + b.getBid() + "&pid=" + b.getPid() + "\">Update</a>\n"
                            + "        <td><a href=\"ControllerBillDetail?go=delete&bid=" + b.getBid() + "&pid=" + b.getPid() + "\">Delete</a>\n"
                            + "    </tr>");
                }
                out.print("</table>");
                out.print("<a href=\"BillDetailManager.html\">Back</a>");
            }
            if (go.equals("delete")) {
                String pid = request.getParameter("pid");
                String bid = request.getParameter("bid");
                int n = dao.remove(pid, bid);
                response.sendRedirect("ControllerBillDetail");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String pid = request.getParameter("pid");
                    String bid = request.getParameter("bid");
                    Vector<BillDetail> vec = dao.getAll("select * from BillDetail where bid='" + bid + "' and pid='" + pid + "'");
                    BillDetail bi = vec.get(0);
                    out.print("<form action=\"ControllerBillDetail\" method=\"post\">\n"
                            + "            <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"bid\">BillID</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"bid\" id=\"bid\" value=\"" + bi.getBid() + "\" readonly></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"pid\">ProductID</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"pid\" id=\"pid\" value=\"" + bi.getPid() + "\" readonly></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"buyQuantity\">BuyQuantity</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"buyQuantity\" id=\"buyQuantity\" value=\"" + bi.getBuyQuantity() + "\" ></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"buyPrice\">BuyPrice</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"buyPrice\" id=\"buyPrice\" value=\"" + bi.getBuyPrice() + "\" ></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"subtotal\">SubTotal</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"subtotal\" id=\"subtotal\" value=\"" + bi.getSubtotal() + "\" ></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><input type=\"submit\" value=\"Update Bill\" name=\"submit\" ></td>\n"
                            + "                    <td><input type=\"reset\" value=\"Reset\"></td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "        </form>");
                } else {
                    String bid = request.getParameter("bid");
                    String pid = request.getParameter("pid");
                    int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
                    double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
                    double subtotal = Double.parseDouble(request.getParameter("subtotal"));
                    BillDetail bd = new BillDetail(bid, pid, buyQuantity, buyPrice, subtotal);
                    int n = dao.update(bd);
                    response.sendRedirect("ControllerBillDetail");
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
