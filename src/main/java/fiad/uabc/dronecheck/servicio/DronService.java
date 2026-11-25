package fiad.uabc.dronecheck.servicio;

import java.util.ArrayList;
import java.util.List;
import fiad.uabc.dronecheck.modelo.Dron;

public class DronService {

    private final List<Dron> drones = new ArrayList<>();
    private int siguienteId = 1;
    private Dron dronSeleccionado;

    public synchronized Dron registrarDron(String nombre, String modelo, int idUsuario) {
        if (vacio(nombre) || vacio(modelo)) {
            return null;
        }
        Dron dron = new Dron(nombre.trim(), modelo.trim(), idUsuario);
        dron.setId(siguienteId++);
        drones.add(dron);
        return dron;
    }

    public synchronized List<Dron> consultarDrones() {
        return new ArrayList<>(drones);
    }

    public synchronized List<Dron> consultarDrones(int idUsuario) {
        List<Dron> resultado = new ArrayList<>();
        for (Dron d : drones) {
            if (d.getIdUsuario() == idUsuario) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    public synchronized boolean editarDron(int id, String nombre, String modelo) {
        Dron dron = buscar(id);
        if (dron == null || vacio(nombre) || vacio(modelo)) {
            return false;
        }
        dron.setNombre(nombre.trim());
        dron.setModelo(modelo.trim());
        return true;
    }

    public synchronized boolean eliminarDron(int id) {
        Dron dron = buscar(id);
        if (dron == null || Dron.EN_VUELO.equals(dron.getEstadoActual())) {
            return false;
        }
        if (dron == dronSeleccionado) {
            dronSeleccionado = null;
        }
        return drones.remove(dron);
    }

    public synchronized Dron seleccionarDron(int id) {
        dronSeleccionado = buscar(id);
        return dronSeleccionado;
    }

    public synchronized Dron getDronSeleccionado() {
        return dronSeleccionado;
    }

    private Dron buscar(int id) {
        for (Dron d : drones) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    private static boolean vacio(String s) {
        return s == null || s.trim().isEmpty();
    }
}
