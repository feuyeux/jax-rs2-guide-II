<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Server Login</title>
    <link type="text/css" rel="stylesheet" href="webjars/bootstrap/3.0.3/css/bootstrap.min.css"/>
    <script type="text/javascript" src="webjars/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <h1>Server Login</h1>
    <c:if test="${not empty param.authentication_error}">
        <h1>Woops!</h1>

        <p class="error">Your login attempt was not successful.</p>
    </c:if>
    <c:if test="${not empty param.authorization_error}">
        <h1>Woops!</h1>

        <p class="error">You are not permitted to access that resource.</p>
    </c:if>

    <div class="form-horizontal">
        <div class="row">
            <div class="col-sm-6 col-md-4">
                <form action="<c:url value="/login.do"/>" method="post" role="form">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input id="username" class="form-control" type='text' name='j_username' value="han"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input id="password" class="form-control" type='text' name='j_password' value="han"/>
                    </div>
                    <button class="btn btn-primary" type="submit">Login</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
