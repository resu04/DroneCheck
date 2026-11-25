/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fiad.uabc.dronecheck.vista;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cruz
 */
public class LoginView {

    private static final String JSP_LOGIN = "/WEB-INF/jsp/login.jsp";

    public void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(JSP_LOGIN).forward(request, response);
    }

    public Map<String, String> obtenerCredenciales(HttpServletRequest request) {
        Map<String, String> credenciales = new HashMap<>();
        
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");
        credenciales.put("correo", correo == null ? "" : correo.trim());
        credenciales.put("contrasenia", contrasenia == null ? "" : contrasenia);
        credenciales.put("recordarme", String.valueOf(request.getParameter("recordarme") != null));
        return credenciales;
    }

    public void mostrarMensaje(HttpServletRequest request, HttpServletResponse response,
            String mensaje, boolean esError) throws ServletException, IOException {
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("mensajeEsError", esError);
        request.getRequestDispatcher(JSP_LOGIN).forward(request, response);
    }
}
