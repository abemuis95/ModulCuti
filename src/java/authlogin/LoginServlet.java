/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package authlogin;

import bean.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JDBCUtility;

/**
 *
 * @author U
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private JDBCUtility jdbcUtility;
    private Connection con;
    
    public void init() throws ServletException
    {
        String driver = "com.mysql.jdbc.Driver";

        String dbName = "db_cutirehat";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";

        jdbcUtility = new JDBCUtility(driver,
                                      url,
                                      userName,
                                      password);

        jdbcUtility.jdbcConnect();
        con = jdbcUtility.jdbcGetConnection();
        jdbcUtility.prepareSQLStatement();
    }    
    
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
        
        Staff profile = null;
        //Get the session object
	HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800);
        String staffno = request.getParameter("staffno");
        String password = request.getParameter("password");
        
        try {
            PreparedStatement preparedStatement = jdbcUtility.getPsSelectLoginViaStaffNoPassword();
            preparedStatement.setString(1, staffno);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                profile = new Staff();
                profile.setStaffNo(staffno);
//                PreparedStatement preparedStatement2 = jdbcUtility.getPsSelectStaffViaStaffNo();
//                ResultSet rs2 = preparedStatement2.executeQuery();
//                while(rs2.next()){
//                    int id = rs2.getInt("id");
//                    String name = rs2.getString("name"); //nama staff
//                    String email = rs2.getString("email"); //email staff
//                    int userRole = rs2.getInt("role"); // role staff - admin/user biasa
//                    int id_jabatan = rs2.getInt("id_jabatan");
//                    int id_jawatan = rs2.getInt("id_jawatan");
//                    int id_sokonglulus = rs2.getInt("id_sokonglulus");
//                    int id_cutitahunan = rs2.getInt("id_cutitahunan");
//                    String photo = rs2.getString("photo");
//
//                    profile = new Staff();
//                    profile.setStaffNo(staffno);
//                    profile.setEmail(email);
//                    profile.setRole(userRole);
//                    profile.setId_jabatan(id_jabatan);
//                    profile.setId_jawatan(id_jawatan);
//                    profile.setId_sokonglulus(id_sokonglulus);
//                    profile.setId_cutitahunan(id_cutitahunan);
//                    profile.setPhoto(photo);
//                    //response.sendRedirect(request.getContextPath() + "/welcome.jsp");
//                    
//                    
//                }
                    
                
                
            }
        }
        catch (SQLException ex) {            
        }
        
        if (profile != null) {
            session.setAttribute("staffSession", profile);
            response.sendRedirect("Home"); //redirect to Home servlet
        }
        else {
            response.sendRedirect(request.getContextPath() + "/not-exist.html");
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
