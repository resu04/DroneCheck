# DroneCheck

Aplicación web (Java, Servlets + JSP, Maven, Tomcat) para registrar drones, vuelos,
verificaciones previas e incidencias.

## Estructura

```
src/main/java/fiad/uabc/dronecheck/
  modelo/       Usuario, Dron, Vuelo, VerificacionVuelo, Incidencia
  servicio/     DronService, VueloService   (lógica en memoria; luego pasará a DAO + BD)
  controlador/  Servlets: Inicio, Autenticacion, Dron, Historial, Incidencia, Soporte
  vista/        Ayudantes *View (forward a los JSP)
src/main/webapp/
  css/  js/  img/
  index.jsp                  redirige a /inicio
  WEB-INF/jsp/               páginas (no se pueden abrir directo, solo vía servlet)
  WEB-INF/jsp/fragmentos/    cabecera.jspf, sidebar.jspf, barra-superior.jspf
```

## Rutas

| URL            | Servlet                  | JSP                       |
|----------------|--------------------------|---------------------------|
| /inicio        | InicioController         | WEB-INF/jsp/inicio.jsp    |
| /login         | AutenticacionController  | WEB-INF/jsp/login.jsp     |
| /crearCuenta   | AutenticacionController  | WEB-INF/jsp/crearCuenta.jsp |
| /drones        | DronController           | (pendiente)               |
| /historial     | HistorialController      | WEB-INF/jsp/historial.jsp |
| /incidencias   | IncidenciaController     | WEB-INF/jsp/incidencias.jsp |
| /soporte       | SoporteController        | WEB-INF/jsp/soporte.jsp   |

## Cómo agregar una página nueva

1. Crear `WEB-INF/jsp/pagina.jsp` copiando la estructura de `historial.jsp`
   (define `titulo` y `activa`, e incluye los fragmentos).
2. Crear su servlet en `controlador/` con `@WebServlet("/pagina")` y hacer
   `forward` a `/WEB-INF/jsp/pagina.jsp`.
3. Si va en el menú lateral, agregar el enlace en `fragmentos/sidebar.jspf`.

## Pendiente

- Autenticación real (registro, login, sesión con atributo `usuario`) y filtro de sesión.
- Que los servlets llamen a los servicios; los métodos deben recibir ids, no objetos.
- Paquetes `dao` (memoria → base de datos) y `filtro`.
- Pruebas JUnit para el modelo.
