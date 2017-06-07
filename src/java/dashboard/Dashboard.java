/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

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
public class Dashboard extends HttpServlet {

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
            out.println("<title>Servlet Dashboard</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Dashboard at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        String code = request.getParameter("code");
        JSONArray r = new JSONArray();
        
        switch(code) {
            case "1":
                r = getTotalEmployees();
                break;
            case "2":
                r = getTotalMembers();
                break;
            case "3":
                r = getNewMemberThisWeek();
                break;
            case "4":
                r = getExpiredMemberThisMonth();
                break;
            case "5":
                r = getMemberInExparacy();
                break;
            default :
                break;
        }
        
        out.println(r.toString());
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
    
    public JSONArray getTotalEmployees() {
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        JSONArray r = new JSONArray();
        try {
            PreparedStatement pst = dbcon.prepareStatement("SELECT COUNT(*) EMPLOYEES FROM UMUserMs");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                JSONObject jsob = new JSONObject();
                jsob.put("0", rs.getString("EMPLOYEES"));
                r.put(jsob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConn();
        }
        
        return r;
    }
    
    public JSONArray getTotalMembers() {
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        JSONArray r = new JSONArray();
        try {
            PreparedStatement pst = dbcon.prepareStatement("SELECT COUNT(*) MEMBERS FROM Membership");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                JSONObject jsob = new JSONObject();
                jsob.put("0", rs.getString("MEMBERS"));
                r.put(jsob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConn();
        }
        
        return r;
    }
    
    public JSONArray getExpiredMemberThisMonth() {
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        JSONArray r = new JSONArray();
        try {
            PreparedStatement pst = dbcon.prepareStatement("SELECT COUNT(*) MEMBERS FROM Membership WHERE MEM_ENDDDATE BETWEEN DATE_FORMAT(NOW() ,'%Y-%m-01') AND LAST_DAY(now())");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                JSONObject jsob = new JSONObject();
                jsob.put("0", rs.getString("MEMBERS"));
                r.put(jsob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConn();
        }
        
        return r;
    } 
    
    public JSONArray getMemberInExparacy() {
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        JSONArray r = new JSONArray();
        try {
            PreparedStatement pst = dbcon.prepareStatement("SELECT MEM_NAME, DAY(MEM_ENDDDATE) - DAY(NOW()) DAY_LEFT, MEM_TELP FROM `Membership` WHERE MEM_ENDDDATE >= CURDATE() AND MEM_ENDDDATE < LAST_DAY(CURDATE()) order by MEM_ENDDDATE asc");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                JSONObject jsob = new JSONObject();
                jsob.put("0", rs.getString("MEM_NAME"));
                jsob.put("1", rs.getString("DAY_LEFT"));
                jsob.put("2", rs.getString("MEM_TELP"));
                r.put(jsob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConn();
        }
        
        return r;
    } 
    
    public JSONArray getNewMemberThisWeek() {
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        JSONArray r = new JSONArray();
        try {
            PreparedStatement pst = dbcon.prepareStatement("SELECT COUNT(*) MEMBERS FROM Membership WHERE MEM_STARTDATE BETWEEN SUBDATE(NOW(), WEEKDAY(NOW())) AND DATE_ADD(SUBDATE(NOW(), WEEKDAY(NOW())), INTERVAL 6 DAY)");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                JSONObject jsob = new JSONObject();
                jsob.put("0", rs.getString("MEMBERS"));
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
