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

public class InicioView {

    private static final String JSP_INICIO = "/WEB-INF/jsp/inicio.jsp";

    public void mostrarDrones(HttpServletRequest request, HttpServletResponse response,
            List<?> drones) throws ServletException, IOException {
        request.setAttribute("drones", drones);
        request.getRequestDispatcher(JSP_INICIO).forward(request, response);
    }

    public void mostrarDronSeleccionado(HttpServletRequest request, HttpServletResponse response,
            Object dronSeleccionado) throws ServletException, IOException {
        request.setAttribute("dronSeleccionado", dronSeleccionado);
        request.getRequestDispatcher(JSP_INICIO).forward(request, response);
    }
}
