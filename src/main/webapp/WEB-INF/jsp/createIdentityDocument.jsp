<%-- 
    Document   : createIdentityDocument
    Created on : 14-Sep-2010, 17:04:37
    Author     : Miroslav
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
        <%--<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <script language="javascript" type="text/javascript" src="JavaScript/datetimepicker.js"></script>--%>
        <link href="rfnet.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="JavaScript/datetimepicker_css.js"></script>
        <title>HafTrust Register Verifier - Identity Document Page</title>
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
            <b>Enter your Identity Document Details</b>
       </p>

       <form:form commandName="rvBean" method="post">
            <table border="0" align="center">
               <tbody>
                   <tr bgcolor="#00FFFF">
                       <td colspan="3" align="left"><b> Identity Document Details </b></td>
                   </tr>
                    <tr>
                       <td>Document Type : </td>
                       <td><form:select path="identityDocumentType">
                            <form:options items="${identityDocumentTypeList}" itemValue="value" itemLabel="description" />
                        </form:select></td>
                       <td><form:errors path="identityDocumentType" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Number : </td>
                       <td><form:input path="identityDocumentNumber"/></td>
                       <td><form:errors path="identityDocumentNumber" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Issue Date : </td>
                       <td><form:input path="identityDocumentIssueDate" id="identityDocumentIssueDate"/>
                            <a href="javascript:NewCssCal('identityDocumentIssueDate','ddmmyyyy')"><img
                              src="images/cal.gif" width="16" height="16" alt="Pick an Issue Date"></a>
                       <%--<img src="images/cal.gif"
                        onclick="javascript:NewCal('identityDocumentIssueDate','ddmmyyyy')"
                        width="16" height="16" border="0" alt="Issue Date" />--%> (d-m-yyyy)</td>
                       <td><form:errors path="identityDocumentIssueDate" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Expiry Date : </td>
                       <td><form:input path="identityDocumentExpiryDate" id="identityDocumentExpiryDate"/>
                        <a href="javascript:NewCssCal('identityDocumentExpiryDate','ddmmyyyy')"><img
                              src="images/cal.gif" width="16" height="16" alt="Pick an Expiry Date"></a>
                       <%--<img src="images/cal.gif"
                        onclick="javascript:NewCal('identityDocumentExpiryDate','ddmmyyyy')"
                        width="16" height="16" border="0" alt="Expiry Date" />--%> (d-m-yyyy)</td>
                       <td><form:errors path="identityDocumentExpiryDate" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td align="right"><input type="reset" value="Reset"/></td>
                       <td>
                           <input type="submit" value="Cancel" name="_target9"/>
                           <input type="submit" value="Save" name="_target11"/>
                       </td>
                       <td align="left">
                           <input type="submit" value="Back" name="_target4"/>
                           <input type="submit" value="Next" name="createIdentityDocument"/>
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
