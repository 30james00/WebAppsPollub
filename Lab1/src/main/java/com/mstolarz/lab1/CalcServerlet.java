package com.mstolarz.lab1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServerlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalcServerlet at " + request.getContextPath() + "</h1>");
            out.println(calculate(request));
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String calculate(HttpServletRequest request) {
        String aValueText = request.getParameter("a-value");
        String bValueText = request.getParameter("b-value");
        String operationType = request.getParameter("operation-type");

        double aValue;
        double bValue;

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
                return String.valueOf(aValue + bValue);
            case "-":
                return String.valueOf(aValue - bValue);
            case "*":
                return String.valueOf(aValue * bValue);
            case "/":
                if (bValue == 0)
                    return "Zero division Error";
                return String.valueOf(aValue / bValue);
            default:
                return "Unknown operation";
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
