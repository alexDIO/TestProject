<%--
  Created by IntelliJ IDEA.
  User: olomakovskyi
  Date: 11/3/2014
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tm" uri="/WEB-INF/transportManager.tld"%>

<c:if test="${param['delete'] != null}">
    <tm:manageTransportMap holder="${holder}" action="deleteByMark" mark="${param['mark']}"/>
</c:if>

<html>
<head>
    <title></title>
</head>
<body>
    <form method="get">
        <table>
            <tr>
                <td>
                    <span>Select manufacturer</span>
                </td>
                <td>
                    <select name="mark">
                        <c:forEach var="item" items="${manufacturers.all}" varStatus="loopCounter">
                            <option value="${item.description}">${item.description}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="submit" name="delete" value="Delete">
                </td>
            </tr>

            <tr>
                <td>
                    <a href="<c:url value="/mvc/delete"/>">Refresh</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
