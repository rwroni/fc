/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import con.connection;
import core.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rw
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        JSONObject toWrite = null;
        try {
            p : {
                if(user.equals(""))                                 {out.println("Username tidak boleh kosong");    break p;};
                if(pass.equals(""))                                 {out.println("Password tidak boleh kosong");    break p;};
                if((toWrite = checkUser(user, pass)) == null)       {out.println("User tidak ditemukan");           break p;};
                
                HttpSession session = request.getSession();
                session.setAttribute("nama", toWrite.getString("0"));
                out.println(toWrite.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    public JSONObject checkUser(String p_username, String p_paswword) {
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        JSONObject r = new JSONObject();
        try {
            PreparedStatement pst = dbcon.prepareStatement("SELECT USER_NAME FROM UMUserMs WHERE USER_CODE = ? AND PASSWORD = ?");
            pst.setString(1, p_username);
            pst.setString(2, p_paswword);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JSONObject jsob = new JSONObject();
                jsob.put("0", rs.getString("USER_NAME"));
                System.out.println(r);
                r = jsob;
            }
            else {
                r = null;
            }
        } catch (Exception e) {
        } finally {
            conn.closeConn();
        }
        
        return r;
    }

}
