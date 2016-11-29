/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mohoncuti;

import bean.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JDBCUtility;

/**
 *
 * @author USER
 */
@WebServlet(name = "ViewMohonCutiServlet", urlPatterns = {"/MohonCuti"})
public class ViewMohonCutiServlet extends HttpServlet {
    
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
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if(session != null){
            // get Staff object from session
            Staff profile = (Staff)session.getAttribute("staffSession");
            Staff penyokong = null;
            Staff pelulus = null;
            String staffno = profile.getStaffNo();
            int id_sokonglulus = profile.getId_sokonglulus();
            String staffNoPenyokong = "", staffNoPelulus = "";
            
            
            try  {
                /* TODO output your page here. You may use following sample code. */
                PreparedStatement preparedStatement = jdbcUtility.getPsSelectSokongLulusViaID();
                preparedStatement.setInt(1, id_sokonglulus);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    staffNoPenyokong = rs.getString("staffNoPenyokong");
                    staffNoPelulus = rs.getString("staffNoPelulus");
                    //out.println(staffNoPenyokong);
                }

            }
            catch (SQLException ex) {            
            }
            
            /********** GET PENYOKONG INFO FROM DATABASE and put into session****/
            try  {
                /* TODO output your page here. You may use following sample code. */
                PreparedStatement preparedStatement = jdbcUtility.getPsSelectStaffViaStaffNo();
                preparedStatement.setString(1, staffNoPenyokong);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name"); //nama staff
                    String email = rs.getString("email"); //email staff
                    int userRole = rs.getInt("role"); // role staff - admin/user biasa
                    int id_jabatan = rs.getInt("id_jabatan");
                    int id_jawatan = rs.getInt("id_jawatan");
                    int id_sokonglulusPenyokong = rs.getInt("id_sokonglulus");
                    int id_cutitahunan = rs.getInt("id_cutitahunan");
                    String photo = rs.getString("photo");
                    
                    penyokong = new Staff();
                    penyokong.setName(name);
                    penyokong.setStaffNo(staffno);
                    penyokong.setEmail(email);
                    penyokong.setRole(userRole);
                    penyokong.setId_jabatan(id_jabatan);
                    penyokong.setId_jawatan(id_jawatan);
                    penyokong.setId_sokonglulus(id_sokonglulus);
                    penyokong.setId_cutitahunan(id_cutitahunan);
                    penyokong.setPhoto(photo);
                }
            }
            catch (SQLException ex) {            
            }
            
            /********** GET PELULUS INFO FROM DATABASE and put into session****/
            try  {
                /* TODO output your page here. You may use following sample code. */
                PreparedStatement preparedStatement = jdbcUtility.getPsSelectStaffViaStaffNo();
                preparedStatement.setString(1, staffNoPelulus);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name"); //nama staff
                    String email = rs.getString("email"); //email staff
                    int userRole = rs.getInt("role"); // role staff - admin/user biasa
                    int id_jabatan = rs.getInt("id_jabatan");
                    int id_jawatan = rs.getInt("id_jawatan");
                    int id_sokonglulusPenyokong = rs.getInt("id_sokonglulus");
                    int id_cutitahunan = rs.getInt("id_cutitahunan");
                    String photo = rs.getString("photo");
                    
                    pelulus = new Staff();
                    pelulus.setName(name);
                    pelulus.setStaffNo(staffno);
                    pelulus.setEmail(email);
                    pelulus.setRole(userRole);
                    pelulus.setId_jabatan(id_jabatan);
                    pelulus.setId_jawatan(id_jawatan);
                    pelulus.setId_sokonglulus(id_sokonglulus);
                    pelulus.setId_cutitahunan(id_cutitahunan);
                    pelulus.setPhoto(photo);
                    //out.println(name);
                }
            }
            catch (SQLException ex) {            
            }
            
            
            //request.setAttribute("staff", profile);
            session.setAttribute("penyokongSession", penyokong);
            session.setAttribute("pelulusSession", pelulus);
            sendPage(request, response, "/pages/mohon/mohoncuti.jsp");
        }
        else{
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
            
    }
    
    void sendPage(HttpServletRequest req, HttpServletResponse res, String fileName) throws ServletException, IOException
    {
        // Get the dispatcher; it gets the main page to the user
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(fileName);

	if (dispatcher == null)
	{
            System.out.println("There was no dispatcher");
	    // No dispatcher means the html file could not be found.
	    res.sendError(res.SC_NO_CONTENT);
	}
	else
	    dispatcher.forward(req, res);
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
