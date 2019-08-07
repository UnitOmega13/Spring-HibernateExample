<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration of new Product</title>
</head>
<body>
<div align="center">
    <form action="/new_product" method="post">
        Name <input type="text" name="name"> <br>
        Description <input type="text" name="description"> <br>
        Price <input type="number" step="0.01" min="0" placeholder="0,00" name="price"> <br>
        <input type="submit" value="Add new Product"></form>
    </form>
    <form action="/products">
        <input type="submit" value="Return back">
    </form>
</div>
</body>
</html>
