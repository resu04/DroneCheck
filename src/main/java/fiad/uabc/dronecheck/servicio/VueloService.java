package fiad.uabc.dronecheck.servicio;

import fiad.uabc.dronecheck.modelo.Vuelo;
import fiad.uabc.dronecheck.modelo.Dron;
import fiad.uabc.dronecheck.modelo.VerificacionVuelo;
import fiad.uabc.dronecheck.modelo.Incidencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VueloService {

    private final List<Vuelo> vuelos;

    private int siguienteIdVuelo;
    private int siguienteIdIncidencia;

    public VueloService() {
        vuelos = new ArrayList<>();
        siguienteIdVuelo = 1;
        siguienteIdIncidencia = 1;
    }

    public Vuelo prepararVuelo(
            Dron dron,
            int idUsuario,
            VerificacionVuelo verificacion) {

        if (dron == null || verificacion == null) {
            return null;
        }

        if (dron.getIdUsuario() != idUsuario) {
            return null;
        }

        if (!dron.estaDisponible()) {
            return null;
        }

        verificacion.evaluarCondiciones();

        Vuelo nuevoVuelo = new Vuelo(
                dron.getId(),
                idUsuario,
                verificacion
        );

        nuevoVuelo.setId(siguienteIdVuelo++);

        vuelos.add(nuevoVuelo);
        dron.agregarVuelo(nuevoVuelo);

        return nuevoVuelo;
    }

    public boolean verificarCondiciones(Vuelo vuelo) {

        if (vuelo == null || !vuelos.contains(vuelo)) {
            return false;
        }

        VerificacionVuelo verificacion = vuelo.getVerificacion();

        if (verificacion == null) {
            return false;
        }

        return verificacion.evaluarCondiciones();
    }

    public List<String> obtenerAdvertencias(Vuelo vuelo) {

        if (vuelo == null || vuelo.getVerificacion() == null) {
            return Collections.emptyList();
        }

        return vuelo.getVerificacion().getAdvertencias();
    }

    public boolean iniciarVuelo(Vuelo vuelo, Dron dron) {

        if (vuelo == null || dron == null) {
            return false;
        }

        if (!vuelos.contains(vuelo)) {
            return false;
        }

        if (vuelo.getIdDron() != dron.getId()) {
            return false;
        }

        if (vuelo.getIdUsuario() != dron.getIdUsuario()) {
            return false;
        }

        if (!dron.estaDisponible()) {
            return false;
        }

        if (tieneIncidenciasPendientes(dron, vuelo)) {
            return false;
        }

        if (!verificarCondiciones(vuelo)) {
            return false;
        }

        if (!vuelo.iniciar()) {
            return false;
        }

        dron.actualizarEstado(Dron.EN_VUELO);

        return true;
    }

    public boolean finalizarVuelo(
            Vuelo vuelo,
            Dron dron,
            String resultado) {

        if (vuelo == null || dron == null || resultado == null) {
            return false;
        }

        if (!vuelos.contains(vuelo)) {
            return false;
        }

        if (vuelo.getIdDron() != dron.getId()) {
            return false;
        }

        if (!Vuelo.COMPLETADO.equals(resultado)
                && !Vuelo.INTERRUMPIDO.equals(resultado)) {
            return false;
        }

        if (!vuelo.finalizar(resultado)) {
            return false;
        }

        if (tieneIncidenciasPendientes(dron, null)) {
            dron.actualizarEstado(Dron.MANTENIMIENTO);
        } else {
            dron.actualizarEstado(Dron.DISPONIBLE);
        }

        return true;
    }

    public boolean registrarIncidencia(
            Vuelo vuelo,
            Incidencia incidencia,
            Dron dron) {

        if (vuelo == null || incidencia == null || dron == null) {
            return false;
        }

        if (!vuelos.contains(vuelo)) {
            return false;
        }

        if (vuelo.getIdDron() != dron.getId()) {
            return false;
        }

        if (vuelo.getHoraInicio() == null) {
            return false;
        }

        if (!incidencia.registrar()) {
            return false;
        }

        incidencia.setId(siguienteIdIncidencia++);

        vuelo.agregarIncidencia(incidencia);

        if (vuelo.getHoraFin() != null) {
            dron.actualizarEstado(Dron.MANTENIMIENTO);
        }

        return true;
    }

    public boolean marcarIncidenciaRevisada(
            Vuelo vuelo,
            Incidencia incidencia,
            Dron dron) {

        if (vuelo == null || incidencia == null || dron == null) {
            return false;
        }

        if (!vuelos.contains(vuelo)
                || !vuelo.getIncidencias().contains(incidencia)) {
            return false;
        }

        incidencia.actualizarEstado(false);

        if (vuelo.getHoraFin() != null
                && !tieneIncidenciasPendientes(dron, null)
                && Dron.MANTENIMIENTO.equals(dron.consultarEstado())) {

            dron.actualizarEstado(Dron.DISPONIBLE);
        }

        return true;
    }

    public List<Vuelo> obtenerHistorial() {
        return Collections.unmodifiableList(vuelos);
    }

    public List<Vuelo> obtenerHistorialPorUsuario(int idUsuario) {

        List<Vuelo> historial = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getIdUsuario() == idUsuario) {
                historial.add(vuelo);
            }
        }

        return historial;
    }

    public List<Vuelo> obtenerHistorialPorDron(int idDron) {

        List<Vuelo> historial = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getIdDron() == idDron) {
                historial.add(vuelo);
            }
        }

        return historial;
    }

    private boolean tieneIncidenciasPendientes(
            Dron dron,
            Vuelo vueloExcluir) {

        for (Vuelo vuelo : dron.getVuelos()) {

            if (vuelo == vueloExcluir) {
                continue;
            }

            for (Incidencia incidencia : vuelo.getIncidencias()) {

                if (incidencia.isPendienteRevision()) {
                    return true;
                }
            }
        }

        return false;
    }

    public int cantidadVuelosRegistrados() {
        return vuelos.size();
    }
}
