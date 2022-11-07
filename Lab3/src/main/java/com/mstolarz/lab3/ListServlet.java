package com.mstolarz.lab3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "ListServlet", value = "/ListServlet")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            //utworzenie obiektu połączenia do bazy danych MySQL:
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone = UTC", "root", "student");
            //utworzenie obiektu do wykonywania zapytań do bd:
            Statement st;
            st = conn.createStatement();
            String query = "SELECT * FROM country WHERE continent = 'Europe'";
            //wykonanie zapytania SQL:
            ResultSet rs;
            HttpSession session = request.getSession(true);
            CountryBean country;
            ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
            rs = st.executeQuery(query);
            while (rs.next()) {
                //pobierz i wyświetl dane z odpowiedniej kolumny
                country = new CountryBean();
                country.setCode(rs.getString("code"));
                country.setName(rs.getString("name"));
                country.setPopulation(rs.getInt("population"));
                countryList.add(country);
            }
            session.setAttribute("list", countryList);
            response.sendRedirect("countryList.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
