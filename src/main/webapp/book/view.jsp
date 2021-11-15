<%--
  Created by IntelliJ IDEA.
  User: Thai Vu
  Date: 16-Nov-21
  Time: 12:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<html>
<head>
    <title>View Book</title>
</head>
<style>
    div {
        margin-left: 400px;
        width: 700px;
    }
</style>
<body>
<center>
    <h1><a href="/books" style="border: none; color: #dc3545; text-decoration: none">BOOK MANAGER</a></h1>
</center>
<div align="center">
    <table border="1" cellpadding="5" class="table table-info table-hover table-striped">
        <tr>
            <td colspan="2" style="text-align: center"><h4>View Book</h4></td>
        </tr>
        <tr>
            <th>Name Book:</th>
            <td>${book.nameBook}</td>
        </tr>
        <tr>
            <th>Price:</th>
            <td>${book.price}</td>
        </tr>
        <tr>
            <th>Description:</th>
            <td>${book.description}</td>
        </tr>
        <tr>
            <th>Category:</th>
            <td>${book.getCategory().nameCategory}</td>
        </tr>
        <tr>
            <th>Actions:</th>
            <td>
                <a href="/books?action=edit&id=${book.id}"><input type="submit" value="Edit Book"  class="btn btn-outline-info"></a>
                <a href="/books?action=delete&id=${book.id}" ><input type="submit" value="Delete Book" class="btn btn-outline-info"></a>
            </td>
        </tr>
    </table>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>

</html>
