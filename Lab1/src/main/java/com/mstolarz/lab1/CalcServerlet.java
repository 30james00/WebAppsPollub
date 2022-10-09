package com.mstolarz.lab1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "calcServlet", value = "/calc_servlet")
public class CalcServerlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(true);
        ArrayList<String> history = (ArrayList<String>) session.getAttribute("history");
        Cookie[] cookies = request.getCookies();
        String cookieName = "visited";
        boolean isVisited = false;

        //create history list if nonexistent
        if (history == null) {
            history = new ArrayList<>();
        }

        if (cookies != null) {
            for (Cookie c : cookies
            ) {
                if (cookieName.equals(c.getName())) {
                    isVisited = true;
                    break;
                }
            }
        }
        if (!isVisited) {
            Cookie c = new Cookie(cookieName, "yes");
            response.addCookie(c);
        }

        String result = calculate(request);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CalcServerlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>CalcServerlet</h1>");
            out.println("<p>" + (isVisited ? "Known user" : "New user") + "</p>");
            out.println("<a href=\"calc_form.html\">Calculator</a>");
            out.println("<a href=\"calc_form.html\">Clear history</a>");
            out.println("<h2>Result:</h2>");
            out.println("<p>" + result + "</p>");
            out.println("<h2>History:</h2>");
            if (history.isEmpty()) {
                out.println("<p>History is empty</p>");
            } else {
                for (String entry : history
                ) {
                    out.println("<p>" + entry + "</p>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }

        //set updated history
        history.add(result);
        session.setAttribute("history", history);
    }

    /**
     * Calculates result of Calculator's operation
     *
     * @param request serverlet request
     * @return Calculation result or error message
     */
    private String calculate(HttpServletRequest request) {
        String aValueText = request.getParameter("a-value");
        String bValueText = request.getParameter("b-value");
        String operationType = request.getParameter("operation-type");

        double aValue;
        double bValue;
        double result;

        aValueText = aValueText.replace(",", ".");
        bValueText = bValueText.replace(",", ".");

        try {
            aValue = Double.parseDouble(aValueText);
        } catch (NumberFormatException e) {
            return "A value is not a number";
        }
        try {
            bValue = Double.parseDouble(bValueText);
        } catch (NumberFormatException e) {
            return "B value is not a number";
        }

        switch (operationType) {
            case "+":
                result = aValue + bValue;
                break;
            case "-":
                result = aValue - bValue;
                break;
            case "*":
                result = aValue * bValue;
                break;
            case "/":
                if (bValue == 0)
                    return "Zero division Error";
                result = aValue / bValue;
                break;
            default:
                return "Unknown operation";
        }
        return aValueText + " " + operationType + " " + bValueText + " = " + result;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
