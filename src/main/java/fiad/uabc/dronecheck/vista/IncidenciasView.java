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
public class IncidenciasView {

    private static final String JSP_INCIDENCIAS = "/WEB-INF/jsp/incidencias.jsp";

    public void mostrarIncidencias(HttpServletRequest request, HttpServletResponse response,
            List<?> incidencias) throws ServletException, IOException {
        request.setAttribute("incidencias", incidencias);
        request.getRequestDispatcher(JSP_INCIDENCIAS).forward(request, response);
    }

    public void mostrarFormularioIncidencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("mostrarFormulario", true);
        request.getRequestDispatcher(JSP_INCIDENCIAS).forward(request, response);
    }
}
