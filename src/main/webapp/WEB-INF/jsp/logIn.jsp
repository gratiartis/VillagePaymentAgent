<%-- 
    Document   : logIn
    Created on : 13-Sep-2010, 15:51:41
    Author     : Miroslav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HafTrust Register Verifier Login Page</title>
        <style>
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1 align="center">Verifier Registration</h1>
       <hr size="5">
       <p align="center">
            <b>Enter your LogIn details</b>
        </p>
       <form:form commandName="loginFormBean" method="post">
           <table border="0" align="center">
               <tbody>
                   <tr>
                       <td>Email : </td>
                       <td><form:input path="email"/></td>
                       <td><form:errors path="email" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Password : </td>
                       <td><form:password path="password"/></td>
                       <td><form:errors path="password" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td colspan="3"><form:errors cssClass="error" /></td>
                   </tr>
                   <tr>
                      <td align="right"><input type="reset" value="Reset"/></td>
                      <td colspan="2" align="left"><input type="submit" value="Login" name="_target1"/></td>
                   </tr>
               </tbody>
           </table>
       </form:form>
       <p align="right">
            <a href="index.htm">Home</a>
        </p>
       <hr size="5">
    </body>
</html>
