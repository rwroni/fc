package spackage;

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
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roni
 */
public class SubmitPackage extends HttpServlet {

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
            out.println("<title>Servlet submit_package</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet submit_package at " + request.getContextPath() + "</h1>");
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
        String name         = request.getParameter("name");
        String period       = request.getParameter("period");
        String price        = request.getParameter("price");
        try {
            p : {
                if(name.equals(""))                                 {out.println("Nama paket tidak boleh kosong");      break p;};
                if(period.equals(""))                               {out.println("Period tidak boleh kosong");          break p;};
                if(price.equals(""))                                {out.println("harga tidak boleh kosong");           break p;};
                if(insertPackage(name, period, price) == -1)        {out.println("Gagal memasukkan data");              break p;};
                
                out.println("Data berhasil disimpan");
            }
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
    
    public int insertPackage(String p_name, String p_period, String p_price) {
        int r = -1;
        
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        try {
            PreparedStatement pst = dbcon.prepareStatement("INSERT INTO PMPackageMs values(null, ?, ?, ?, now(), 1)");
            pst.setString(1, p_name);
            pst.setString(2, p_period);
            pst.setString(3, p_price);
            
            r = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            conn.closeConn();
        }
        
        return r;
    }

}
