package con;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rw
 */
public class connection {
       private Connection conn = null;

    public connection() {
        String URL = "jdbc:mysql://localhost:3306/fitnesscenter";
        String USER = "root";
        String PASSWD = "";

        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Class.forName("com.mysql.jdbc.Driver");
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            this.conn = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (Exception e) {
            System.out.println("Error when konakting: ");
            e.printStackTrace();
        }
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    public void closeConn() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (Exception e) {
                Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
