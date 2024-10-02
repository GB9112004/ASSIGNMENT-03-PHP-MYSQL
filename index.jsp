<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="database.DatabaseConnection" %>

<!DOCTYPE html>
<html>
<head>
    <title>Event Management System</title>
</head>
<body>
    <h1>Welcome to Event Management</h1>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="events.jsp">Events</a>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
    </nav>

    <h2>Featured Events</h2>
    <%
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM events LIMIT 5");

            while (rs.next()) {
    %>
                <div>
                    <h3><%= rs.getString("title") %></h3>
                    <p><%= rs.getString("description") %></p>
                    <p>Date: <%= rs.getString("date") %> | Location: <%= rs.getString("location") %></p>
                    <a href="eventDetails.jsp?id=<%= rs.getInt("id") %>">View Details</a>
                </div>
    <%
            }
           
