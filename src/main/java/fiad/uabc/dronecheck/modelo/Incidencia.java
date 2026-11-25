package fiad.uabc.dronecheck.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Incidencia (Modelo).
 * Vuelo 1 registra 0..* Incidencia (composición) -> idVuelo.
 */
public class Incidencia implements Serializable {

    private int id;
    private int idVuelo;
    private String tipo;
    private String descripcion;
    private Date fecha;
    private boolean pendienteRevision;

    public Incidencia() {
    }

    public Incidencia(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    // ---------- Métodos del diagrama ----------

    /** Valida los datos, fija la fecha actual y la deja pendiente de revisión. */
    public boolean registrar() {
        if (tipo == null || tipo.trim().isEmpty()
                || descripcion == null || descripcion.trim().isEmpty()) {
            return false;
        }
        this.fecha = new Date();
        this.pendienteRevision = true;
        return true;
    }

    /** Marca la incidencia como pendiente (true) o ya revisada (false). */
    public void actualizarEstado(boolean pendienteRevision) {
        this.pendienteRevision = pendienteRevision;
    }

    // ---------- Getters y setters ----------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdVuelo() { return idVuelo; }
    public void setIdVuelo(int idVuelo) { this.idVuelo = idVuelo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public boolean isPendienteRevision() { return pendienteRevision; }
    public void setPendienteRevision(boolean pendienteRevision) { this.pendienteRevision = pendienteRevision; }

    @Override
    public String toString() {
        return "Incidencia{id=" + id + ", tipo=" + tipo + ", pendienteRevision=" + pendienteRevision + "}";
    }
}
