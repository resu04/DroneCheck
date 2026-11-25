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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Cruz
 */

public class CrearCuentaView {

    private static final String JSP_CREAR_CUENTA = "/WEB-INF/jsp/crearCuenta.jsp";

    public void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(JSP_CREAR_CUENTA).forward(request, response);
    }

    public void mostrarFormulario(HttpServletRequest request, HttpServletResponse response,
            List<String> errores) throws ServletException, IOException {
        request.setAttribute("errores", errores);
        request.getRequestDispatcher(JSP_CREAR_CUENTA).forward(request, response);
    }

    public Map<String, String> obtenerDatos(HttpServletRequest request) {
        Map<String, String> datos = new HashMap<>();

        datos.put("nombreCompleto", valor(request, "full_name"));
        datos.put("nombreUsuario", valor(request, "user_name"));
        datos.put("correo", valor(request, "correo"));
        datos.put("contrasenia", valor(request, "contrasenia"));
        datos.put("confirmarContrasenia", valor(request, "confirmar_contrasenia"));
        datos.put("terminos", String.valueOf(request.getParameter("terminos") != null));
        return datos;
    }

    public boolean validarFormulario(Map<String, String> datos, List<String> errores) {
        if (datos.get("nombreCompleto").isEmpty()) {
            errores.add("El nombre completo es obligatorio.");
        }
        if (datos.get("nombreUsuario").isEmpty()) {
            errores.add("El nombre de usuario es obligatorio.");
        }
        if (!datos.get("correo").matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            errores.add("El correo electrónico no es válido.");
        }
        if (datos.get("contrasenia").length() < 8) {
            errores.add("La contraseña debe tener al menos 8 caracteres.");
        }
        if (!datos.get("contrasenia").equals(datos.get("confirmarContrasenia"))) {
            errores.add("Las contraseñas no coinciden.");
        }
        if (!Boolean.parseBoolean(datos.get("terminos"))) {
            errores.add("Debes aceptar los términos y condiciones.");
        }
        return errores.isEmpty();
    }

    private String valor(HttpServletRequest request, String parametro) {
        String valor = request.getParameter(parametro);
        return valor == null ? "" : valor.trim();
    }
}