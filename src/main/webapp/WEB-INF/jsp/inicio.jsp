<%-- 
    Document   : inicio
    Created on : 14 nov 2026, 11:43:52 a.m.
    Author     : Cruz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="titulo" value="Inicio"/>
<c:set var="activa" value="inicio"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="fragmentos/cabecera.jspf"%>
    </head>
    <body>
        <div class="pagina">
            <%@include file="fragmentos/sidebar.jspf"%>

            <main class="contenido panel-principal">
                <%@include file="fragmentos/barra-superior.jspf"%>

                <div class="columnas-inicio">
                    <section class="seccion-panel">
                        <h2>Dron seleccionado</h2>
                        <div class="vacio">
                            <div class="circulo-vacio">
                                <img src="${ctx}/img/usuario.svg" alt="" class="icono-usuario grande">
                                <span class="pequeno-candado"></span>
                            </div>
                            <h3 id="titulo-inicio">Bienvenido a DroneCheck</h3>

                            <p id="descripcion-inicio">
                                Organiza la información de tus drones y lleva un control de tus vuelos
                                desde un solo lugar. Crea una cuenta para comenzar.
                            </p>
                            <div class="pasos-inicio" aria-label="Cómo comenzar">
                                <div class="paso-inicio"><span>1</span><div><strong>Crea tu cuenta</strong><small>Regístrate con tu nombre y correo electrónico.</small></div></div>
                                <div class="paso-inicio"><span>2</span><div><strong>Registra tus drones</strong><small>Ten la información de tus equipos organizada.</small></div></div>
                                <div class="paso-inicio"><span>3</span><div><strong>Consulta tus vuelos</strong><small>Revisa el historial y da seguimiento a tus actividades.</small></div></div>
                            </div>
                        </div>
                    </section>

                    <section class="seccion-panel">
                        <div class="encabezado-seccion">
                            <h2>Mis drones</h2>
                            <button class="boton-azul pequeno boton-agregar" type="button">+ Agregar dron</button>
                        </div>

                        <div class="vacio">
                            <div class="circulo-vacio dron-vacio">✈</div>
                            <h3>No tienes drones registrados</h3>
                            <p id="descripcion-drones">
                                Cuando crees tu cuenta podrás agregar tus equipos y consultar aquí la información que registres.
                            </p>
                            <p class="nota-informativa">¿Primera vez en DroneCheck? Utiliza el botón <strong>Registrarse</strong> de la parte superior derecha para crear tu cuenta.</p>

                            <button class="boton-azul boton-agregar" id="agregar-vacio" type="button" hidden>+ Agregar dron</button>
                        </div>
                    </section>
                </div>

            </main>
        </div>
    </body>
</html>
