<%-- 
    Document   : createNewAccount
    Created on : Feb 26, 2021, 11:06:21 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="anhnt.Utilities.RegistrationCreateError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create new Account</h1>
        <form action="createRecord" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERROR}"/>

            Username*<input type="text" name="txtUsername" value="" />(6-20 characters)<br />
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br />
            </c:if>

            Password* <input type="password" name="txtPassword" value="" />(6-30 characters)<br />
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">${errors.passwordLengthErr}</font><br />
            </c:if>

            Confirm* <input type="password" name="txtConfirm" value="" /><br />
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">${errors.confirmNotMatch}</font><br />
            </c:if>

            Full name <input type="text" name="txtFullName" value="" />(2 - 50) characters)<br />
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">${errors.fullNameLengthErr}</font><br />
            </c:if>
            <input type="submit" value="Create New Account" name="btAction"/>
            <input type="reset" value="Reset" />

        </form><br />
        <c:if test="${not empty errors.usernameExisted}">
            <font color="red">${errors.usernameExisted}</font><br />
        </c:if>


    </body>
</html>
