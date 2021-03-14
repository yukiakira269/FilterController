<%-- 
    Document   : search
    Created on : Feb 26, 2021, 7:00:26 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER}
        </font>

        <h1>Search Page</h1>
        <form action="search">
            Search Value <input type="text" name="txtSearchValue" value="" />
            <input type="submit" value="Search" name="btAction"/>
        </form>
        <form action="logout">
            <input type="submit" value="Log Out" name="btAction"/>
        </form>
        <%@include file="shopping.html"%>
        <br />


        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <jsp:useBean id="columns" 
                                 class="anhnt.tbl_User.Tbl_UserDAO" scope="request"/>
                    <thead>
                        <tr>
                            <th>No.</th>
                                <c:forEach items="${columns.columnNames}" 
                                           var="columnName" varStatus="count">
                                <th>${columnName}</th>
                                </c:forEach> 
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="update">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.userId}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.userId}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ADMIN" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="delete">
                                        <c:param name="pk" value="${dto.userId}"/>
                                        <c:param name="lastSearchValue" 
                                                 value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${param.txtSearchValue}" />
                                </td>

                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h1>No records found!!!</h1>
        </c:if>
    </c:if>


</body>
</html>
