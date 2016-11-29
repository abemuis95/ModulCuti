<%-- 
    Document   : login
    Created on : Nov 23, 2016, 11:22:50 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Form</h1>
        <form action="LoginServlet" method="post">
            <table border="0">
                <tbody>
                    <tr>
                        <td colspan="2">
                            
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>Enter your staff id:</td>
                        <td><input type="text" name="staffno"><br></td>
                    </tr>
                    <tr>
                        <td>Enter password:</td>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Submit"/></td>
                    </tr>
                </tbody>
            </table>

         </form>
    </body>
</html>
