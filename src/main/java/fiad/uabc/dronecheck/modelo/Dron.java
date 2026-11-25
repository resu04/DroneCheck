package fiad.uabc.dronecheck.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dron (Modelo). Relaciones del diagrama: - Usuario 1 registra 0..* Dron ->
 * idUsuario - Dron 1 participa 0..* Vuelo -> lista de vuelos
 */
public class Dron implements Serializable {

    public static final String DISPONIBLE = "Disponible";
    public static final String EN_VUELO = "En vuelo";
    public static final String MANTENIMIENTO = "Mantenimiento";
    public static final String FUERA_DE_SERVICIO = "Fuera de servicio";

    private int id;
    private int idUsuario;
    private String nombre;
    private String modelo;
    private String estadoActual;
    private final List<Vuelo> vuelos = new ArrayList<>();

    public Dron() {
        this.estadoActual = DISPONIBLE;
    }

    public Dron(String nombre, String modelo, int idUsuario) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.idUsuario = idUsuario;
        this.estadoActual = DISPONIBLE;
    }
    
        public static ArrayList<Usuarios> inicializaUsuarios() {
        try {
            ArrayList<Usuarios> lista = new ArrayList();
        } catch (Exception e) 
            return null;

        }

    // ---------- Métodos del diagrama ----------
    public boolean actualizarEstado(String nuevoEstado) {
        if (nuevoEstado == null) {
            return false;
        }
        switch (nuevoEstado) {
            case DISPONIBLE:
            case EN_VUELO:
            case MANTENIMIENTO:
            case FUERA_DE_SERVICIO:
                this.estadoActual = nuevoEstado;
                return true;
            default:
                return false;
        }
    }

    public String consultarEstado() {
        return estadoActual;
    }

    public void agregarVuelo(Vuelo vuelo) {
        if (vuelo != null) {
            vuelo.setIdDron(this.id);
            vuelos.add(vuelo);
        }
    }

    public List<Vuelo> getVuelos() {
        return Collections.unmodifiableList(vuelos);
    }

    public boolean estaDisponible() {
        return DISPONIBLE.equals(estadoActual);
    }

    // ---------- Getters y setters ----------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    @Override
    public String toString() {
        return "Dron{id=" + id + ", idUsuario=" + idUsuario + ", nombre=" + nombre
                + ", modelo=" + modelo + ", estadoActual=" + estadoActual + "}";
    }
}
