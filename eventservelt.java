package servlets;

import database.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/event")
public class EventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String location = request.getParameter("location");
        double price = Double.parseDouble(request.getParameter("price"));
        int eventId = request.getParameter("eventId") != null ? Integer.parseInt(request.getParameter("eventId")) : 0;

        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement pst;

            if ("create".equalsIgnoreCase(action)) {
                String query = "INSERT INTO events (title, description, date, time, location, price) VALUES (?, ?, ?, ?, ?, ?)";
                pst = con.prepareStatement(query);
                pst.setString(1, title);
                pst.setString(2, description);
                pst.setString(3, date);
                pst.setString(4, time);
                pst.setString(5, location);
                pst.setDouble(6, price);
                pst.executeUpdate();
            } else if ("update".equalsIgnoreCase(action)) {
                String query = "UPDATE events SET title=?, description=?, date=?, time=?, location=?, price=? WHERE id=?";
                pst = con.prepareStatement(query);
                pst.setString(1, title);
                pst.setString(2, description);
                pst.setString(3, date);
                pst.setString(4, time);
                pst.setString(5, location);
                pst.setDouble(6, price);
                pst.setInt(7, eventId);
                pst.executeUpdate();
            } else if ("delete".equalsIgnoreCase(action)) {
                String query = "DELETE FROM events WHERE id=?";
                pst = con.prepareStatement(query);
                pst.setInt(1, eventId);
                pst.executeUpdate();
            }

            con.close();
            response.sendRedirect("adminDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error managing events!");
        }
    }
}
