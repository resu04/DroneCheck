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

public class HistorialView {

    private static final String JSP_HISTORIAL = "/WEB-INF/jsp/historial.jsp";

    public void mostrarHistorial(HttpServletRequest request, HttpServletResponse response,
            List<?> vuelos) throws ServletException, IOException {
        request.setAttribute("vuelos", vuelos);
        request.getRequestDispatcher(JSP_HISTORIAL).forward(request, response);
    }

    public void mostrarFiltros(HttpServletRequest request, HttpServletResponse response,
            List<?> drones, Integer dronFiltro) throws ServletException, IOException {
        request.setAttribute("drones", drones);
        request.setAttribute("dronFiltro", dronFiltro);
        request.getRequestDispatcher(JSP_HISTORIAL).forward(request, response);
    }
}
