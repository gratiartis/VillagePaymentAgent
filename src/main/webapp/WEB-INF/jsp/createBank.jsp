<%-- 
    Document   : createBank
    Created on : 10-Sep-2010, 11:08:36
    Author     : Miroslav Zivkovic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page  import="org.haftrust.verifier.view.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <title>HafTrust Register Verifier - Account Details Page</title>
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
            <b>Enter your Account Details</b>
       </p>

       <form:form commandName="rvBean" method="post">
            <table border="0" align="center">
               <tbody>
                   <tr bgcolor="#00FFFF">
                       <td colspan="3" align="left"><b>Bank Details</b></td>
                   </tr>
                   <tr>
                       <td>Account Number : </td>
                       <td><form:input path="bankAccountNumber"/></td>
                       <td><form:errors path="bankAccountNumber" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Bank Name : </td>
                       <td><form:input path="bankName"/></td>
                       <td><form:errors path="bankName" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Telephone :</td>
                       <td><form:input path="bankContactNumber"/></td>
                       <td><form:errors path="bankContactNumber" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Bank Address : </td>
                       <td><form:input path="bankAddress"/></td>
                       <td><form:errors path="bankAddress" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>SortCode : </td>
                       <td><form:input path="bankSortCode"/></td>
                       <td><form:errors path="bankSortCode" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>IBAN : </td>
                       <td><form:input path="bankIban"/></td>
                       <td><form:errors path="bankIban" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td colspan="3"><form:errors cssClass="error" /></td>
                   </tr>
                   <tr>
                       <td align="right"><input type="reset" value="Reset"/></td>
                       <td>
                           <input type="submit" value="Cancel" name="_cancel"/>
                           <input type="submit" value="Save" name="_target11"/>
                       </td>
                       <td align="left">
                           <input type="submit" value="Back" name="_target5"/>
                           <input type="submit" value="Next" name="createBank"/>
                       </td>
                   </tr>
               </tbody>
           </table>
       </form:form>
        <%--<p align="right">
            <a href="index.htm">Home</a>
        </p>--%>
       <hr size="5">
    </body>
</html>
