<%-- 
    Document   : searchVerifier
    Created on : 22-Sep-2010, 10:09:01
    Author     : Miroslav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <title>HafTrust Verify Verifier Select Region Page</title>
    </head>
    <body>
       <h1 align="center">Verifier Verification</h1>
       <hr size="5">
       <p align="center">
            <b>Select Verifier's Region</b>
       </p>
       <form:form commandName="vvBean" method="post">
           <table border="0" align="center">
               <tbody>
                   <tr>
                       <td>Country : </td>
                       <td><c:out value="${vvBean.country.description}"/></td>
                   </tr>
                   <tr>

                       <td>Region : </td>
                       <td><form:select path="idRegion">
                            <form:options items="${regionList}" itemValue="id" itemLabel="description" />
                        </form:select></td>
                   </tr>
                   <tr>
                       <td align="right"><input type="submit" value="Cancel" name="_target4"/></td>
                       <td align="right"><input type="submit" value="Back" name="_target0"/></td>
                       <td align="left"><input type="submit" value="Search" name="_target2"/></td>
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
