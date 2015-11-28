<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <c:url value="/" var="base"/>
    <link type="text/css" rel="stylesheet"
          href="${base}webjars/bootstrap/3.0.3/css/bootstrap.min.css"/>
    <script type="text/javascript"
            src="${base}webjars/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript"
            src="${base}webjars/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <title>Conference Client</title>
</head>
<body>
<div class="container">
    <h1>Conference Client</h1>

    <h2>${message}</h2>
</div>
</body>
</html>