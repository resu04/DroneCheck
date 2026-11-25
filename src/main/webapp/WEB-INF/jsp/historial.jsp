<%-- 
    Document   : historial
    Created on : 14 nov 2026, 11:50:51 a.m.
    Author     : Cruz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="titulo" value="Historial"/>
<c:set var="activa" value="historial"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="fragmentos/cabecera.jspf"%>
        <title>que quieres perro</title>
    </head>
    <body>
        <div class="pagina">
            <%@include file="fragmentos/sidebar.jspf"%>

            <main class="contenido panel-principal">
                <%@include file="fragmentos/barra-superior.jspf"%>

                <section class="seccion-panel historial-panel">
                    <div class="encabezado-seccion historial-encabezado">
                        <h1>Historial de vuelos</h1>
                        <select aria-label="Filtrar drones" id="filtro-dron">
                            <option>Todos los drones</option>
                        </select>
                    </div>

                    <div class="tabla-scroll">
                        <table class="tabla-historial">
                            <thead>
                                <tr>
                                    <th>Fecha y hora</th>
                                    <th>Dron</th>
                                    <th>Duración</th>
                                    <th>Condiciones</th>
                                    <th>Resultado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr>
                                    <td colspan="6">
                                        <div class="historial-vacio">
                                            <div class="circulo-vacio">◷</div>
                                            <h3>No hay vuelos registrados</h3>
                                            <p>Cuando registres un vuelo, aparecerá aquí su información.</p>
                                            <a href="${ctx}/inicio" class="boton-azul">Volver a inicio</a>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </section>

            </main>
        </div>
    </body>
</html>
