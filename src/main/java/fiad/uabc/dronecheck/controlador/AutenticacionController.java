package fiad.uabc.dronecheck.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controlador de autenticación: inicio de sesión (/login) y registro de
 * cuentas (/crearCuenta). La validación real todavía está pendiente.
 */
@WebServlet(urlPatterns = {"/login", "/crearCuenta"})
public class AutenticacionController extends HttpServlet {

    private static final String JSP_LOGIN = "/WEB-INF/jsp/login.jsp";
    private static final String JSP_CREAR_CUENTA = "/WEB-INF/jsp/crearCuenta.jsp";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String destino = esCrearCuenta(request) ? JSP_CREAR_CUENTA : JSP_LOGIN;
        request.getRequestDispatcher(destino).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if (esCrearCuenta(request)) {
            // Aquí implementaremos el registro de usuarios (registrarUsuario).
            request.setAttribute("mensaje",
                    "El registro de cuentas todavía no está configurado.");
            request.getRequestDispatcher(JSP_CREAR_CUENTA).forward(request, response);
            return;
        }

        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");

        // Aquí implementaremos la validación del usuario (iniciarSesion).

        request.setAttribute("mensaje",
                "La autenticación todavía no está configurada.");
        request.getRequestDispatcher(JSP_LOGIN).forward(request, response);
    }

    private boolean esCrearCuenta(HttpServletRequest request) {
        return "/crearCuenta".equals(request.getServletPath());
    }
}
