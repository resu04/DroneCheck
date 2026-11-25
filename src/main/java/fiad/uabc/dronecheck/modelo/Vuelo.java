package fiad.uabc.dronecheck.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Vuelo (Modelo).
 * Relaciones del diagrama:
 *  - Dron 1 participa 0..* Vuelo      -> idDron
 *  - Usuario 1 realiza 0..* Vuelo     -> idUsuario
 *  - Vuelo contiene 1 VerificacionVuelo (composición)
 *  - Vuelo registra 0..* Incidencia (composición)
 */
public class Vuelo implements Serializable {

    public static final String EN_CURSO = "En curso";
    public static final String COMPLETADO = "Completado";
    public static final String INTERRUMPIDO = "Interrumpido";

    private int id;
    private int idDron;
    private int idUsuario;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;
    private long duracion; // en segundos
    private String resultado;
    private VerificacionVuelo verificacion;
    private final List<Incidencia> incidencias = new ArrayList<>();

    public Vuelo() {
    }

    public Vuelo(int idDron, int idUsuario, VerificacionVuelo verificacion) {
        this.idDron = idDron;
        this.idUsuario = idUsuario;
        this.verificacion = verificacion;
    }

    // ---------- Métodos del diagrama ----------

    /** Inicia el vuelo solo si la verificación previa lo declara apto. */
    public boolean iniciar() {
        if (horaInicio != null) {
            return false; // ya iniciado
        }
        if (verificacion == null || !verificacion.isAptoParaVolar()) {
            return false;
        }
        Date ahora = new Date();
        this.fecha = ahora;
        this.horaInicio = ahora;
        this.resultado = EN_CURSO;
        return true;
    }

    public boolean finalizar() {
        return finalizar(COMPLETADO);
    }

    public boolean finalizar(String resultado) {
        if (horaInicio == null || horaFin != null) {
            return false;
        }
        this.horaFin = new Date();
        this.resultado = resultado;
        this.duracion = calcularDuracion();
        return true;
    }

    /** Duración en segundos entre horaInicio y horaFin (0 si aún no termina). */
    public long calcularDuracion() {
        if (horaInicio == null || horaFin == null) {
            return 0;
        }
        return (horaFin.getTime() - horaInicio.getTime()) / 1000;
    }

    // ---------- Relaciones ----------

    public void agregarIncidencia(Incidencia incidencia) {
        if (incidencia != null) {
            incidencia.setIdVuelo(this.id);
            incidencias.add(incidencia);
        }
    }

    public List<Incidencia> getIncidencias() {
        return Collections.unmodifiableList(incidencias);
    }

    // ---------- Getters y setters ----------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdDron() { return idDron; }
    public void setIdDron(int idDron) { this.idDron = idDron; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Date getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Date horaInicio) { this.horaInicio = horaInicio; }

    public Date getHoraFin() { return horaFin; }
    public void setHoraFin(Date horaFin) { this.horaFin = horaFin; }

    public long getDuracion() { return duracion; }
    public void setDuracion(long duracion) { this.duracion = duracion; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public VerificacionVuelo getVerificacion() { return verificacion; }
    public void setVerificacion(VerificacionVuelo verificacion) { this.verificacion = verificacion; }

    @Override
    public String toString() {
        return "Vuelo{id=" + id + ", idDron=" + idDron + ", idUsuario=" + idUsuario
                + ", resultado=" + resultado + "}";
    }
}