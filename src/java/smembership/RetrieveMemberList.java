/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smembership;

import properties.connection;
import core.JSONArray;
import core.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roni
 */
public class RetrieveMemberList extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RetrieveMemberList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RetrieveMemberList at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            out.println(getMemberList().toString());
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public JSONArray getMemberList() {
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        JSONArray r = new JSONArray();
        try {
            PreparedStatement pst = dbcon.prepareStatement("SELECT MEM_NAME, MEM_GENDER, MEM_TELP, P.PACK_NAME, M.MEM_ENDDDATE  FROM Membership M, PMPackageMs P where M.PACK_ID = P.PACK_ID");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                JSONObject jsob = new JSONObject();
                jsob.put("0", rs.getString("MEM_NAME"));
                jsob.put("1", rs.getString("MEM_GENDER"));
                jsob.put("2", rs.getString("MEM_TELP"));
                jsob.put("3", rs.getString("PACK_NAME"));
                jsob.put("4", rs.getString("MEM_ENDDDATE"));
                
                r.put(jsob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConn();
        }
        
        return r;
    }

}
