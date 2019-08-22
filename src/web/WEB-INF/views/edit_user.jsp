<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
${error}
<form:form action="/admin/user/edit"
           method="post" modelAttribute="user">
    <table>
        <tr>
            <td><form:hidden path="id"/></td>
        </tr>
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
            <td>Access Role</td>
            <td>
                <input type="radio" name="accessRole" value="user"/> User
                <input type="radio" name="accessRole" value="admin"/>Admin
            </td>
        </tr>
        <tr>
            <td><form:hidden path="salt"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Confirm"/></td>
        </tr>
    </table>
</form:form>
<button><a href="/admin/user">Users</a></button>
</body>
</html>