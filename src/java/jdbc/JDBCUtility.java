/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.util.*;
import java.sql.*;

/**
 *
 * @author U
 */
public class JDBCUtility 
{
   Connection con;
   String driver;
   String url;
   String userName;
   String password;
   PreparedStatement psSelectLoginViaStaffNoPassword = null;
   PreparedStatement psSelectStaffViaStaffNo = null;
   PreparedStatement psSelectStaffViaID = null;
   PreparedStatement psSelectSokongLulusViaID = null;
   PreparedStatement psInsertMohonCutiRehat = null;
   PreparedStatement psKiraBilanganCuti = null;

   //use this constructor if using ConnectionPool
   public JDBCUtility()
   {
   }

   //use this constructor if not using ConnectionPool
   //ConnectionPool is used for multi user!
   public JDBCUtility(String driver,
                      String url,
                      String userName,
                      String password)
   {
      this.driver = driver;
      this.url = url;
      this.userName = userName;
      this.password = password;
   }

   public  void jdbcConnect()
   {
      try
	   {
         Class.forName (driver);
         con = DriverManager.getConnection(url, userName, password);
         DatabaseMetaData dma = con.getMetaData ();
         System.out.println("\nConnected to " + dma.getURL());
         System.out.println("Driver       " + dma.getDriverName());
         System.out.println("Version      " + dma.getDriverVersion());
         System.out.println("");
	   }
	   catch (SQLException ex)
	   {
         while (ex != null)
         {
		      System.out.println ("SQLState: " +
                                 ex.getSQLState ());
            System.out.println ("Message:  " +
                                 ex.getMessage ());
		      System.out.println ("Vendor:   " +
                                 ex.getErrorCode ());
            ex = ex.getNextException ();
		      System.out.println ("");
         }

         System.out.println("Connection to the database error");
	   }
	   catch (java.lang.Exception ex)
	   {
         ex.printStackTrace ();
	   }
   }

   public Connection jdbcGetConnection()
   {
   	return con;
   }

   public void jdbcConClose()
   {
   	try
   	{
         con.close();
   	}
   	catch (Exception ex)
	   {
	   }
   }

    public void prepareSQLStatement()
    {      
        try
        {
            
            //select login via staff no and password
            String sqlSelectLoginViaStaffNoPassword = "SELECT * FROM login WHERE staffno = ? AND pwd = ? "; 
            psSelectLoginViaStaffNoPassword = con.prepareStatement(sqlSelectLoginViaStaffNoPassword);
            
            //select staff via staff no 
            String sqlSelectStaffViaStaffNo = "SELECT * FROM staff WHERE staffno = ?"; 
            psSelectStaffViaStaffNo = con.prepareStatement(sqlSelectStaffViaStaffNo);
            
            //select staff via staff no 
            String sqlSelectStaffViaID = "SELECT * FROM staff WHERE id = ?"; 
            psSelectStaffViaID = con.prepareStatement(sqlSelectStaffViaID);
            
            String sqlSelectSokongLulusViaID = "SELECT * FROM sokonglulus WHERE id_sokonglulus = ?"; 
            psSelectSokongLulusViaID = con.prepareStatement(sqlSelectSokongLulusViaID);
            
            String sqlInsertMohonCutiRehat = "INSERT INTO mohoncutirehat("
                    + "tarikhMula, "
                    + "tarikhTamat, "
                    + "tarikhMohon, "
                    + "id_sokonglulus, "
                    + "catatan, "
                    + "alamatCuti,"
                    + "bilanganCuti) "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            psInsertMohonCutiRehat = con.prepareStatement(sqlInsertMohonCutiRehat);
            
            //kira bilangan cuti menggunakan SQL DATEDIFF WHERE tarikh NOT IN (SELECT tarikh FROM cutiumum ) 
            String sqlKiraBilanganCuti = " SELECT (DATEDIFF(?, ?)+1) "
                    + " - (SELECT COUNT(*) FROM cutiumum WHERE (cutiumum.tarikh BETWEEN ? AND ?) AND kampus = 3  ) AS totalCuti ";
            psKiraBilanganCuti = con.prepareStatement(sqlKiraBilanganCuti); 
             
            
        }
	catch (SQLException ex)
	{
            while (ex != null)
            {
                System.out.println ("SQLState: " +
                                 ex.getSQLState ());
                System.out.println ("Message:  " +
                                 ex.getMessage ());
		System.out.println ("Vendor:   " +
                                 ex.getErrorCode ());
                ex = ex.getNextException ();
		      System.out.println ("");
            }
            
            System.out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex)
	{
            ex.printStackTrace ();
	}
    }

   
    /**
     * @return the psSelectLoginViaStaffNoPassword
     */
    public PreparedStatement getPsSelectLoginViaStaffNoPassword() {
        return psSelectLoginViaStaffNoPassword;
    }

    /**
     * @return the psSelectStaffViaStaffNo
     */
    public PreparedStatement getPsSelectStaffViaStaffNo() {
        return psSelectStaffViaStaffNo;
    }

    /**
     * @return the psSelectStaffViaID
     */
    public PreparedStatement getPsSelectStaffViaID() {
        return psSelectStaffViaID;
    }

    /**
     * @return the psSelectSokongLulusViaID
     */
    public PreparedStatement getPsSelectSokongLulusViaID() {
        return psSelectSokongLulusViaID;
    }

    /**
     * @return the psInsertMohonCutiRehat
     */
    public PreparedStatement getPsInsertMohonCutiRehat() {
        return psInsertMohonCutiRehat;
    }

    /**
     * @return the psKiraBilanganCuti
     */
    public PreparedStatement getPsKiraBilanganCuti() {
        return psKiraBilanganCuti;
    }

    
}
