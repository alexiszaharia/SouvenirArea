/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementProduse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Alexis
 */
public class Comanda extends HttpServlet {

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
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject responseJson = new JSONObject();
        
        int id = Integer.parseInt(request.getParameter("id"));
        int cantitate = Integer.parseInt(request.getParameter("cantitate"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String telefon = request.getParameter("telefon");
        String email = request.getParameter("email");
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver problem: " + ex.getMessage());
            responseJson.put("cod_operatie", 1);
            out.print(responseJson);
            out.flush();
            return;
        }
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:souvenirarea",
                    "zahar", "pwdzahar");
            Statement s = conn.createStatement();
            String query = "INSERT INTO clienti(nume, prenume, telefon, email) VALUES('" + nume + "', '" + prenume 
                    + "', '" + telefon + "', '" + email + "')";
                        
            s.executeUpdate(query);
            
            query = "SELECT id_client FROM clienti WHERE nume LIKE '" + nume + "' AND prenume LIKE '" + prenume + "'";
            
            ResultSet rs = s.executeQuery(query);
            rs.next();
            int idClient = rs.getInt(1);
            
            query = "INSERT INTO comenzi(modalitate_plata, stare, id_client) VALUES('ramburs', 'in lucru', " 
                    + idClient + ")";
            s.executeUpdate(query);
            
            query = "SELECT id_comanda FROM comenzi WHERE id_client=" + idClient;
            
            rs = s.executeQuery(query);
            rs.next();
            int idComanda = rs.getInt(1);
            
            query = "INSERT INTO rand_comenzi(id_comanda, id_produs, pret, cantitate) VALUES(" + idComanda + 
                    ", " + id + ", 30, " + cantitate + ")";
            s.executeUpdate(query);
            
            rs.close();
            s.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Probleme la conexiune: " + ex.getMessage());
            responseJson.put("cod_operatie", 1);
            out.print(responseJson);
            out.flush();
            return;
        }
        
        responseJson.put("cod_operatie", 0);
        out.print(responseJson);
        out.flush();
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
