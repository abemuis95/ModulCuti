/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sejarahpermohonan;

import bean.Staff;
import bean.MohonCuti;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "SejarahPermohonanServlet", urlPatterns = {"/SejarahPermohonan"})
public class SejarahPermohonanServlet extends HttpServlet {
    
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
        MohonCuti mcuti = null;
        ArrayList<MohonCuti> mohon = new ArrayList<MohonCuti>();
        
        //check if session is not null, else redirect to login page
        if(session != null){
            
            // get Staff object from session
            Staff profile = (Staff)session.getAttribute("staffSession");
            int staff_id = profile.getId();
            int id_sokonglulus = profile.getId_sokonglulus();
           
            try  {
                
                PreparedStatement preparedStatement = jdbcUtility.getPsSelectMohonCutiRehatViaIDSokongLulus();
                preparedStatement.setInt(1, id_sokonglulus);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String tarikhMohon = rs.getString("tarikhMohon");
                    String tarikhMula = rs.getString("tarikhMula");
                    String tarikhTamat = rs.getString("tarikhTamat");
                    int bilanganCuti = rs.getInt("bilanganCuti");
                    String catatan = rs.getString("catatan");
                    String status = rs.getString("status");
                    
                    mcuti = new MohonCuti();
                    mcuti.setId(id);
                    mcuti.setTarikhMohon(tarikhMohon);
                    mcuti.setTarikhMula(tarikhMula);
                    mcuti.setTarikhTamat(tarikhTamat);
                    mcuti.setBilanganCuti(bilanganCuti);
                    mcuti.setCatatan(catatan);
                    mcuti.setStatus(status);
                    
                    mohon.add(mcuti);
                }
                
                request.setAttribute("listPermohonan", mohon); //use jstl to loop the list
                sendPage(request, response, "/pages/semak/semakcuti.jsp");
            }
            catch (SQLException ex) { 
                out.println("in exception");
            }
            
            
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
