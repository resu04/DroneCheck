<%-- 
    Document   : create_account
    Created on : 14 nov 2026, 11:49:17 a.m.
    Author     : Cruz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="titulo" value="Crear cuenta"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="fragmentos/cabecera.jspf"%>
    </head>
    <body>
        <div class="pagina">
            <%@include file="fragmentos/sidebar.jspf"%>

            <main class="contenido">
                <section class="tarjeta-registro">
                    <header class="encabezado">
                        <h1>Crear cuenta</h1>
                        <p>Crea tu cuenta de DroneCheck para comenzar a organizar tus drones y llevar el seguimiento de tus vuelos.</p>
                        <p class="ayuda-formulario">Completa tus datos. Utiliza un correo al que tengas acceso y crea una contraseña de al menos 8 caracteres.</p>
                    </header>

                    <c:if test="${not empty mensaje}">
                        <div class="mensaje-feedback error" role="alert"><c:out value="${mensaje}"/></div>
                    </c:if>
                    <c:if test="${not empty errores}">
                        <div class="mensaje-feedback error" role="alert">
                            <ul>
                                <c:forEach var="e" items="${errores}"><li><c:out value="${e}"/></li></c:forEach>
                            </ul>
                        </div>
                    </c:if>

                    <form action="${ctx}/crearCuenta" method="post" class="formulario" id="form-registro" novalidate>
                        <div id="mensaje-registro" class="mensaje-feedback" role="status" aria-live="polite" hidden></div>

                        <div class="campo">
                            <label for="full_name" class="sr-only">Nombre completo</label>
                            <input type="text" id="full_name" name="full_name" placeholder="Nombre completo"
                                   autocomplete="name" required>
                        </div>

                        <div class="campo">
                            <label for="user_name" class="sr-only">Nombre de usuario</label>
                            <input type="text" id="user_name" name="user_name" placeholder="Nombre de usuario"
                                   autocomplete="username" required>
                        </div>

                        <div class="campo">
                            <label for="correo" class="sr-only">Correo electrónico</label>
                            <input type="email" id="correo" name="correo" placeholder="Correo electrónico"
                                   autocomplete="email" required>
                        </div>

                        <div class="campo campo-password">
                            <label for="contrasenia" class="sr-only">Contraseña</label>
                            <input type="password" id="contrasenia" name="contrasenia" placeholder="Contraseña"
                                   autocomplete="new-password" minlength="8" aria-describedby="ayuda-password-registro" required>
                            <button type="button" class="mostrar-password" data-target="contrasenia"
                                    aria-label="Mostrar contraseña">◉
                            </button>
                            <small class="ayuda-campo" id="ayuda-password-registro">Usa al menos 8 caracteres.</small>
                        </div>

                        <div class="campo campo-password">
                            <label for="confirmar_contrasenia" class="sr-only">Confirmar contraseña</label>
                            <input type="password" id="confirmar_contrasenia" name="confirmar_contrasenia"
                                   placeholder="Confirmar contraseña" autocomplete="new-password" required>
                            <button type="button" class="mostrar-password" data-target="confirmar_contrasenia"
                                    aria-label="Mostrar contraseña">◉
                            </button>
                        </div>

                        <label class="terminos">
                            <input type="checkbox" name="terminos" required>
                            <span>
                                Acepto los
                                <a href="#" onclick="alert('Los términos se añadirán después.'); return false;">
                                    términos y condiciones
                                </a>
                            </span>
                        </label>

                        <button type="submit" class="btn-crear" id="boton-registro">Crear cuenta</button>
                        <p class="estado-formulario" id="estado-registro" role="status" aria-live="polite"></p>

                    </form>

                    <p class="iniciar-sesion">¿Ya tienes cuenta?
                        <a href="${ctx}/login">Inicia sesión</a>
                    </p>
                </section>

            </main>
        </div>
        <script src="${ctx}/js/feedback.js"></script>
    </body>
</html>
