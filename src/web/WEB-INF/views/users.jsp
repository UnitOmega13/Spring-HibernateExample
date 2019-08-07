<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div style="text-align: center">
    ${accessDenied}
    <p>
        <button onclick="location.href='/users/add'"> New user</button>
        <button onclick="location.href='/products'"> Products</button>
    </p>
    <table align="center" border="1">
        <tr>
            <th>Email</th>
            <th>Login</th>
            <th>Password</th>
            <th>Role</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.email}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.accessRole}</td>
                <td>${user.id}</td>
                <td><a href="/admin/users/edit?id=${user.id}">Update</a></td>
                <td><a href="/admin/users/remove?id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
