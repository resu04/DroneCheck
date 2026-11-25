<%-- 
    Document   : soporte
    Created on : 14 nov 2026, 11:55:43 a.m.
    Author     : Cruz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="titulo" value="Soporte"/>
<c:set var="activa" value="soporte"/>
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

                <section class="seccion-panel pagina-pendiente">
                  <h1>Soporte</h1>
                  <p>Esta sección se agregará después.</p>
                </section>

            </main>
        </div>
    </body>
</html>
