<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

${error}
<form:form action="/admin/user/add"
           method="post" modelAttribute="user">
    <table>
        <tr>
            <td>Login</td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><form:password path="password"/></td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="confirmPassword"/></td>
        </tr>
        <tr>
            <td>Role</td>
            <td>
                <input type="radio" name="accessRole" value="user"/> User
                <input type="radio" name="accessRole" value="admin"/>Admin
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Подтвердить"/></td>
        </tr>
    </table>
</form:form>

<button><a href="/admin/user">Вернуться</a></button>

</body>
</html>