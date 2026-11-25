<%-- 
    Document   : login
    Created on : 14 nov 2026, 11:55:04 a.m.
    Author     : Cruz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="titulo" value="Iniciar sesión"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="fragmentos/cabecera.jspf"%>
    </head>
    <body>
        <div class="pagina">
            <%@include file="fragmentos/sidebar.jspf"%>

            <main class="contenido">
                <section class="tarjeta login-card">
                    <header class="login-header">
                        <h1>Iniciar sesión</h1>
                        <p>Inicia sesión para acceder a tu espacio de DroneCheck, organizar tus drones y consultar la información de tus vuelos.</p>
                        <p class="ayuda-formulario">Ingresa el correo electrónico y la contraseña que utilizaste al crear tu cuenta. Si todavía no tienes una, <a href="${ctx}/crearCuenta">puedes registrarte aquí</a>.</p>
                    </header>

                    <c:if test="${not empty mensaje}">
                        <div class="mensaje-feedback error" role="alert"><c:out value="${mensaje}"/></div>
                    </c:if>

                    <form action="${ctx}/login" method="post" class="login-form" id="form-login" novalidate>
                        <div id="mensaje-login" class="mensaje-feedback" role="status" aria-live="polite" hidden></div>
                        <div class="campo-login">
                            <label for="correo">Correo electrónico</label>
                            <div class="input-icono">
                                <span aria-hidden="true">✉</span>
                                <input type="email" name="correo" id="correo" placeholder="tu@correo.com"
                                       autocomplete="email" aria-describedby="ayuda-correo-login" required>
                                <small class="ayuda-campo" id="ayuda-correo-login">Ejemplo: nombre@correo.com</small>
                            </div>
                        </div>

                        <div class="campo-login">
                            <label for="contrasenia">Contraseña</label>
                            <div class="input-icono">
                                <span aria-hidden="true"><img src="${ctx}/img/usuario.svg" alt="" class="icono-usuario"></span>
                                <input type="password" name="contrasenia" id="contrasenia" placeholder="Tu contraseña"
                                       autocomplete="current-password" aria-describedby="ayuda-password-login" required>
                                <small class="ayuda-campo" id="ayuda-password-login">Escribe la contraseña que elegiste al registrarte.</small>
                                <button type="button" class="mostrar-password" aria-label="Mostrar contraseña"
                                        aria-pressed="false">◉
                                </button>
                            </div>
                        </div>

                        <div class="opciones-login">
                            <label class="recordarme">
                                <input type="checkbox" name="recordarme" id="recordarme">
                                <span>Recordarme</span>
                            </label>

                            <a href="#"
                               onclick="alert('Recuperar contraseña estará disponible al conectar la autenticación.'); return false;">
                                ¿Olvidaste tu contraseña?
                            </a>

                        </div>

                        <button type="submit" class="btn-login" id="boton-login">Iniciar sesión</button>
                        <p class="estado-formulario" id="estado-login" role="status" aria-live="polite"></p>

                    </form>

                    <footer class="login-footer">
                        <p>¿No tienes cuenta?
                            <a href="${ctx}/crearCuenta">Regístrate</a>
                        </p>
                    </footer>
                </section>

            </main>
        </div>
        <script src="${ctx}/js/feedback.js"></script>
    </body>
</html>
