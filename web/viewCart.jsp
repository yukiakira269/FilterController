<%-- 
    Document   : viewCart
    Created on : Mar 1, 2021, 10:24:42 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart includes</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="removeBook">
                    <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${entry.key}
                            </td>
                            <td>
                                ${entry.value}
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" 
                                       value="${entry.key}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="search.jsp">
                                Add More Books to Your Cart
                            </a>
                        </td>
                        <td>
                            <input type="submit" 
                                   value="Remove Selected Items" name="btAction"/>
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>

    </c:if>
    <c:if test="${empty cart.items}">
        <h2>No cart exists!!!</h2>
    </c:if>
</body>
</html>
