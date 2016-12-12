/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batalcuti;

import bean.MohonCuti;
import bean.Staff;
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
@WebServlet(name = "BatalCutiServlet", urlPatterns = {"/BatalCuti"})
public class BatalCutiServlet extends HttpServlet {
    
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
        if(session != null){
            if (session.getAttribute("staffSession") != null) {
                // get Staff object from session
                Staff profile = (Staff) session.getAttribute("staffSession");
                String staffno = profile.getStaffNo();
                int id = Integer.parseInt(request.getParameter("id"));
                String tarikhMulaTamat = "";

                try {
                    /* TODO output your page here. You may use following sample code. */
                    PreparedStatement preparedStatement = jdbcUtility.getPsSelectMohonCutiRehatViaID();
                    preparedStatement.setInt(1, id);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        String tarikhMohon = rs.getString("tarikhMohon");
                        String tarikhMula = rs.getString("tarikhMula");
                        String tarikhTamat = rs.getString("tarikhTamat");
                        int bilanganCuti = rs.getInt("bilanganCuti");
                        String alamatCuti = rs.getString("alamatCuti");
                        String catatan = rs.getString("catatan");
                        String status = rs.getString("status");
                        
                        tarikhMulaTamat = tarikhMula + " - " + tarikhTamat;
                        
                        mcuti = new MohonCuti();
                        mcuti.setId(id);
                        mcuti.setTarikhMohon(tarikhMohon);
                        mcuti.setTarikhMula(tarikhMula);
                        mcuti.setTarikhTamat(tarikhTamat);
                        mcuti.setBilanganCuti(bilanganCuti);
                        mcuti.setAlamatCuti(alamatCuti);
                        mcuti.setCatatan(catatan);
                        mcuti.setStatus(status);
                    }

                } catch (SQLException ex) {
                }
                
                request.setAttribute("tarikhMulaTamat", tarikhMulaTamat);
                request.setAttribute("batalCuti", mcuti);

                sendPage(request, response, "/pages/batal/batalcuti.jsp");
            }
            else{
                response.sendRedirect(request.getContextPath() + "/index.jsp");
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
