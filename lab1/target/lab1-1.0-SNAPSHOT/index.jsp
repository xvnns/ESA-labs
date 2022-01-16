<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.lab1.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.lab1.entity.LineItem" %>

<!DOCTYPE html>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<% List<Order> orders = (List<Order>) request.getAttribute("orders");%>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Date</th>
        <th>Products</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (Order order : orders) { %>
    <tr>
        <td><%= order.getId() %>
        </td>
        <td><%= order.getDate() %>
        </td>
        <td>
            <ul>
                <% for (LineItem item : order.getLineItems()) { %>
                    <li><%= item.getProduct().toString() %></li>
                <% } %>
            </ul>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>