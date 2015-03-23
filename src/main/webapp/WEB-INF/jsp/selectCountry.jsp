<%-- 
    Document   : selectCountry
    Created on : 10-Sep-2010, 10:59:34
    Author     : Miroslav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <title>HafTrust Register Verifier Page</title>
    </head>
    <body>
       <h1 align="center">Verifier Registration</h1>
       <hr size="5">
       <p align="center">
            <b>Select your Country</b>
        </p>
       <form:form commandName="selectCountryFormBean" method="post">
           <table border="0" align="center">
               <tbody>
                   <tr>
                       <td>Country : </td>
                       <td><form:select path="countryId">
                               <form:options items="${countryList}" itemValue="id" itemLabel="description" />
                        </form:select></td>
                       <td><form:errors path="countryId" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td align="right"><input type="submit" value="Cancel" name="_cancel"/></td>
                       <td colspan="2" align="left"><input type="submit" value="Next" name="selectCountry"/></td>
                       <td></td>
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
