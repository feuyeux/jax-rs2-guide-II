<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <c:url value="/" var="base"/>
    <link type="text/css" rel="stylesheet" href="${base}webjars/bootstrap/3.0.3/css/bootstrap.min.css"/>
    <script type="text/javascript" src="${base}webjars/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="${base}webjars/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <title>Tarots</title>
</head>
<body>
<div class="container">
    <h1>Tarots</h1>

    <div class="panel panel-default">
        <!-- Table -->
        <table class="table">
            <tr>
                <th>Card</th>
                <th>Value</th>
                <th>Reverse-Value</th>
            </tr>
            <c:forEach var="tarot" items="${tarots}">
                <tr>
                    <td width="100px">${tarot.card}</td>
                    <td width="500px">${tarot.v}</td>
                    <td width="500px">${tarot.rv}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>