<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<h1>Order Details</h1>
<form:form action="/user/payment" method="post" modelAttribute="userOrder">
    <table>
        <tr>
            <td>E-mail:</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Phone number:</td>
            <td><form:input path="phoneNumber"/></td>
        </tr>
        <tr>
            <td>Delivery address:</td>
            <td><form:input path="address"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Confirm"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>