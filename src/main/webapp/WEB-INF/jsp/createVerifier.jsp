<%-- 
    Document   : createVerifier
    Created on : 10-Sep-2010, 11:07:42
    Author     : Miroslav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <title>HafTrust Register Verifier Personal Details Page</title>
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
            <b>Input your Personal Details</b>
       </p>
       <form:form commandName="rvBean" method="post" action="registerVerifier.htm" enctype="multipart/form-data">
            <table border="0" align="center">
               <tbody>
                   <tr bgcolor="#00FFFF">
                       <td colspan="3" align="left"><b> Personal Details </b></td>
                   </tr>
                   <tr>
                       <td>First Name :</td>
                       <td><form:input path="firstName"/></td>
                       <td><form:errors path="firstName" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Middle Name : </td>
                       <td><form:input path="middleName"/></td>
                       <td><form:errors path="middleName" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Last Name : </td>
                       <td><form:input path="lastName"/></td>
                       <td><form:errors path="lastName" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Telephone Number : </td>
                       <td><form:input path="telephoneNumber"/></td>
                       <td><form:errors path="telephoneNumber" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Gender : </td>
                       <td><form:select path="gender">
                            <form:options items="${genderList}" itemValue="value" itemLabel="description" />
                        </form:select></td>
                       <td><form:errors path="gender" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Date of Birth : </td>
                       <td><form:input path="dob" id="dob"/>
                           <a href="javascript:NewCssCal('dob','ddmmyyyy')"><img
                              src="images/cal.gif" width="16" height="16" alt="Pick a Date Of Birth"></a>
                       <%--<img src="images/cal.gif"
                        onclick="javascript:NewCal('dob','ddmmyyyy')"
                        width="16" height="16" border="0" alt="Date of Birth" />--%> (d-m-yyyy)</td>
                       <td><form:errors path="dob" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Email : </td>
                       <td><form:input disabled="true" path="email"/></td>
                       <td></td>
                   </tr>
                   <tr>
                       <td>Education Level : </td>
                       <td><form:select path="educationLevel">
                            <form:options items="${educationLevelList}" itemValue="value" itemLabel="description" />
                        </form:select></td>
                       <td></td>
                   </tr>
                   <tr>
                       <td>Education Type : </td>
                       <td><form:select path="educationType">
                            <form:options items="${educationTypeList}" itemValue="value" itemLabel="description" />
                        </form:select></td>
                       <td></td>
                   </tr>
                   <tr bgcolor="#00FFFF">
                       <td colspan="3" align="left"><b>Address</b></td>
                   </tr>
                   <tr>
                       <td>Street : </td>
                       <td><form:input path="street"/></td>
                       <td><form:errors path="street" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Village : </td>
                       <td><form:input path="village"/></td>
                       <td><form:errors path="village" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Postcode : </td>
                       <td><form:input path="postcode"/></td>
                       <td><form:errors path="postcode" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>Town : </td>
                       <td><form:input path="town"/></td>
                       <td><form:errors path="town" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>City : </td>
                       <td><form:input path="city"/></td>
                       <td><form:errors path="city" cssClass="error"/></td>
                   </tr>
                   <tr>
                       <td>District : </td>
                       <td><form:input disabled="true" path="district.description"/></td>
                       <td></td>
                   </tr>
                   <tr>
                       <td>Region : </td>
                       <td><form:input disabled="true" path="region.description"/></td>
                       <td></td>
                   </tr>
                   <tr>
                       <td>Country : </td>
                       <td><form:input disabled="true" path="country.description"/></td>
                       <td></td>
                   </tr>
                   <tr bgcolor="#00FFFF">
                       <td colspan="3" align="left"><b>Personal Photo</b></td>
                   </tr>
                   <tr>
                      <td>Photo : </td>
                      <td> <spring:bind path="rvBean.file">
                                <input type="file"  name="file" size="25" value="Upload"/>
                      </td>
                      <td><form:errors path="file" cssClass="error"/></td>
                      </spring:bind>
                  </tr>
                   <tr>
                       <td align="right"><input type="reset" value="Reset"/></td>
                       <td>
                           <input type="submit" value="Cancel" name="_cancel"/>
                           <input type="submit" value="Save" name="_target11"/>
                       </td>
                       <td align="left">
                           <input type="submit" value="Back" name="_target3"/>
                           <input type="submit" value="Next" name="createVerifier"/>
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
