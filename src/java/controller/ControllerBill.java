/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBill;
import entity.Bill;
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
public class ControllerBill extends HttpServlet {

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
        DAOBill dao = new DAOBill();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerBill</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerBill at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("insertBill")) {
                String bid = request.getParameter("bid");
                String recAddress = request.getParameter("recAddress");
                String recPhone = request.getParameter("recPhone");
                String note = request.getParameter("note");
                int status = Integer.parseInt(request.getParameter("status"));
                String cid = request.getParameter("cid");
                Bill bill = new Bill(bid, recAddress, recPhone, note, status, cid);
                int n = dao.add(bill);
                response.sendRedirect("ControllerBill");
            }
            if (go.equals("listAll")) {
                out.print("<table style=\"border: 1px solid black\">\n"
                        + "    <caption>Bill List</caption>\n"
                        + "    <tr>\n"
                        + "        <th>BillID</th>\n"
                        + "        <th>Date Create</th>\n"
                        + "        <th>Rec Address</th>\n"
                        + "        <th>Rec Phone</th>\n"
                        + "        <th>Note</th>\n"
                        + "        <th>Total Money</th>\n"
                        + "        <th>Status</th>\n"
                        + "        <th>CustomerID</th>\n"
                        + "        <th>Update</th>\n"
                        + "        <th>Delete</th>\n"
                        + "    </tr>");
                Vector<Bill> vector = dao.getAll("select * from Bill");
                for (Bill b : vector) {
                    out.print(" <tr> \n"
                            + "        <td>" + b.getBid() + "</td>\n"
                            + "        <td>" + b.getDateCreate() + "</td>\n"
                            + "        <td>" + b.getRecAddress() + "</td>\n"
                            + "        <td>" + b.getRecPhone() + "</td>\n"
                            + "        <td>" + b.getNote() + "</td>\n"
                            + "        <td>" + b.getTotalMoney() + "</td>\n"
                            + "        <td>" + (b.getStatus() == 1 ? "Enable" : "Disable") + "</td>\n"
                            + "        <td>" + b.getCid() + "</td>\n"
                            + "        <td><a href=\"ControllerBill?go=update&id=" + b.getBid() + "\">Update</a>\n"
                            + "        <td><a href=\"ControllerBill?go=delete&id=" + b.getBid() + "\">Delete</a>\n"
                            + "    </tr>");
                }
                out.print("</table>");
                    out.print("<a href=\"BillManager.html\">Back</a>");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String id = request.getParameter("id");
                    Vector<Bill> vec = dao.getAll("select * from Bill where bid='" + id + "'");
                    Bill bi = vec.get(0);
                    out.print("<form action=\"ControllerBill\" method=\"post\">\n"
                            + "            <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"bid\">BillID</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"bid\" id=\"bid\" value=\"" + bi.getBid() + "\" readonly></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"dateCreate\">dateCreate</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"dateCreate\" id=\"dateCreate\" value=\"" + bi.getDateCreate() + "\" readonly></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"recAddress\">RecAddress</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"recAddress\" id=\"recAddress\" value=\"" + bi.getRecAddress() + "\"></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"recPhone\">RecPhone</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"recPhone\" id=\"recAddress\" value=\"" + bi.getRecPhone() + "\" ></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"note\">Note</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"note\" id=\"note\" value=\"" + bi.getNote() + "\"></td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"status\">Status</label></td>\n"
                            + "                <td><input type=\"radio\" name=\"status\" id=\"status\" value=\"1\" " + (bi.getStatus() == 1 ? "checked" : "") + ">Enable\n"
                            + "                    <input type=\"radio\" name=\"status\" id=\"status\" value=\"0\" " + (bi.getStatus() == 0 ? "checked" : "") + "> Disable\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"cid\">CID</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"cid\" id=\"cid\" value=\"" + bi.getCid() + "\" readonly></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><input type=\"submit\" value=\"Update Bill\" name=\"submit\" ></td>\n"
                            + "                    <td><input type=\"reset\" value=\"Reset\"></td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "        </form>");
                } else {
                    String bid = request.getParameter("bid");
                    String recAddress = request.getParameter("recAddress");
                    String recPhone = request.getParameter("recPhone");
                    String note = request.getParameter("note");
                    int status = Integer.parseInt(request.getParameter("status"));
                    String cid = request.getParameter("cid");
                    Bill bill = new Bill(bid, recAddress, recPhone, note, status, cid);
                    int n = dao.update(bill);
                    response.sendRedirect("ControllerBill");
                }
            }
            if (go.equals("delete")) {
                String id = request.getParameter("id");
                int n = dao.remove(id);
                response.sendRedirect("ControllerBill");
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
