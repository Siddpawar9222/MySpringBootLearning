<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.entities.User" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <h1>All Users</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>City</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null) {
                for (User user : users) { 
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getCity() %></td>
            </tr>
            <% 
                }
            }
            %>
        </tbody>
    </table>
</body>
</html>
