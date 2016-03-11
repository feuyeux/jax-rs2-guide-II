<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Server Index</title>
    <link type="text/css" rel="stylesheet" href="webjars/bootstrap/3.0.3/css/bootstrap.min.css"/>
    <script type="text/javascript" src="webjars/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1>Resource Server</h1>
    <authz:authorize ifAllGranted="ROLE_KING">
        <div class="form-horizontal">
            <form action="<c:url value="/logout.do"/>" role="form">
                <button class="btn btn-primary" type="submit">Logout</button>
            </form>
        </div>
    </authz:authorize>
</div>

</body>
</html>
