package com.mstolarz.lab3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ListServlet", value = "/ListServlet")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //utworzenie obiektu połączenia do bazy danych MySQL:
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone = UTC", "root", "student");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //utworzenie obiektu do wykonywania zapytań do bd:
            Statement st;
            try {
                st = conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String query = "SELECT * FROM country WHERE continent = 'Europe'";
            //wykonanie zapytania SQL:
            ResultSet rs;
            try {
                rs = st.executeQuery(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //pobierz i wyświetl dane z odpowiedniej kolumny
                try {
                    out.println(rs.getString("name"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
