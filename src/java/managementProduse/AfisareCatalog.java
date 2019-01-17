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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet utilizat pentru trimiterea catalogului de produse cerute.
 * @author Alexis
 */
public class AfisareCatalog extends HttpServlet {

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
        
        String categorie = request.getParameter("categorie");
        
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
            String query;
            
            if (categorie.equals("tot")) {
                query = "SELECT id_produs, denumire, pret, cale_poza FROM produse";
            } else {
                query = "SELECT p.id_produs, p.denumire, p.pret, p.cale_poza FROM produse p " +
                    "JOIN categorii_produse c ON p.id_categorie = c.id_categorie " +
                    "WHERE c.denumire LIKE '" + categorie + "'";
            }
            ResultSet rs = s.executeQuery(query);
            
            JSONArray array = new JSONArray();
            
            while (rs.next()) {
                JSONObject produs = new JSONObject();
                produs.put("id", rs.getInt(1));
                produs.put("denumire", rs.getString(2));
                produs.put("pret", rs.getFloat(3));
                produs.put("cale_poza", rs.getString(4));
                
                array.add(produs);
            }
            
            responseJson.put("catalog", array);
            
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
