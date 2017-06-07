/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smembership;

import con.connection;
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
public class SubmitAddMember extends HttpServlet {

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
            out.println("<title>Servlet SubmitAddMember</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitAddMember at " + request.getContextPath() + "</h1>");
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
        String idno        = request.getParameter("idno");
        String bdate        = request.getParameter("bdate");
        String gender        = request.getParameter("gender");
        String address        = request.getParameter("address");
        String phone        = request.getParameter("phone");
        String paket        = request.getParameter("package");
        
        try {
            p : {
                if(name.equals(""))                                     {out.println("Nama paket tidak boleh kosong");      break p;};
                if(idno.equals(""))                                     {out.println("Id No. tidak boleh kosong");          break p;};
                if(bdate.equals(""))                                    {out.println("Birth Date tidak boleh kosong");           break p;};
                if(gender.equals(""))                                   {out.println("Gender tidak boleh kosong");           break p;};
                if(address.equals(""))                                  {out.println("Address tidak boleh kosong");           break p;};
                if(phone.equals(""))                                    {out.println("Phone tidak boleh kosong");           break p;};
                if(paket.equals(""))                                    {out.println("Package tidak boleh kosong");           break p;};
                if(insertMemberShip(name, idno, bdate, gender, address, phone, paket)== -1)         {out.println("Gagal memasukkan data");              break p;};
                
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
    
    public int insertMemberShip(String name, String idno, String bdate, String gender, String address, String phone, String paket) {
        int r = -1;
        
        connection conn = new connection();
        Connection dbcon = conn.getConn();
        try {
            PreparedStatement pst = dbcon.prepareStatement("INSERT INTO Membership values(null, ?, ?, now(), ?, ?, ?, ?, now(), now(), now(), 1)");
//            pst.setString(1, name);
//            pst.setString(2, idno);
//            pst.setString(3, bdate);
//            pst.setString(4, gender);
//            pst.setString(5, address);
//            pst.setString(6, phone);
//            pst.setString(7, paket);
            
            pst.setString(1, name);
            pst.setString(2, idno);
            pst.setString(3, gender);
            pst.setString(4, address);
            pst.setString(5, phone);
            pst.setString(6, paket);
            
            r = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            conn.closeConn();
        }
        
        return r;
    }
}
