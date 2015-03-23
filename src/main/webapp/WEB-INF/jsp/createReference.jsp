<%-- 
    Document   : createReference
    Created on : 15-Sep-2010, 14:57:12
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
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <title>HafTrust Register Verifier - References1  Page</title>
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
            <b>Enter your Reference 1 Details</b>
       </p>

       <form:form commandName="rvBean" method="post">
            <table border="0" align="center">
               <tbody>
                   <tr bgcolor="#00FFFF">
                       <td colspan="3" align="left"><b>Reference 1</b></td>
                   </tr>
                    <tr>
                       <td>Title : </td>
                       <td><form:select path="reference1Title">
                            <form:options items="${titleList}" itemValue="value" itemLabel="description" />
                        </form:select></td>
                       <td><form:errors path="reference1Title" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Full Name : </td>
                       <td><form:input path="reference1FullName"/></td>
                       <td><form:errors path="reference1FullName" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Organisation Name : </td>
                       <td><form:input path="reference1OrganisationName"/></td>
                       <td><form:errors path="reference1OrganisationName" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Designation : </td>
                       <td><form:input path="reference1Designation"/></td>
                       <td><form:errors path="reference1Designation" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Telephone : </td>
                       <td><form:input path="reference1ContactNumber"/></td>
                       <td><form:errors path="reference1ContactNumber" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Email : </td>
                       <td><form:input path="reference1Email"/></td>
                       <td><form:errors path="reference1Email" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Address : </td>
                       <td><form:input path="reference1Address"/></td>
                       <td><form:errors path="reference1Address" cssClass="error"/></td>
                   </tr>
                   <tr>
                      <td align="right"><input type="reset" value="Reset"/></td>
                       <td>
                           <input type="submit" value="Cancel" name="_cancel"/>
                           <input type="submit" value="Save" name="_target11"/>
                       </td>
                       <td align="left">
                           <input type="submit" value="Back" name="_target6"/>
                          <input type="submit" value="Next" name="createReference1"/>
                       </td>
                   </tr>
               </tbody>
           </table>
       </form:form>
       <hr size="5">
    </body>
</html>

