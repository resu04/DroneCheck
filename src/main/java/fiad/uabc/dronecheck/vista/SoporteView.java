/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fiad.uabc.dronecheck.vista;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Cruz
 */
public class SoporteView {

    private static final String JSP_SOPORTE = "/WEB-INF/jsp/soporte.jsp";

    public void mostrarAdvertencias(HttpServletRequest request, HttpServletResponse response,
            List<String> advertencias) throws ServletException, IOException {
        request.setAttribute("advertencias", advertencias);
        request.getRequestDispatcher(JSP_SOPORTE).forward(request, response);
    }

    public void mostrarOpcionesSoporte(HttpServletRequest request, HttpServletResponse response,
            List<String> opciones) throws ServletException, IOException {
        request.setAttribute("opcionesSoporte", opciones);
        request.getRequestDispatcher(JSP_SOPORTE).forward(request, response);
    }
}
