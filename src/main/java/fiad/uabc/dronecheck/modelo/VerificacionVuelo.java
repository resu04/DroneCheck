package fiad.uabc.dronecheck.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * VerificacionVuelo (Modelo).
 * Revisión previa al vuelo. Vuelo 1 contiene 1 VerificacionVuelo (composición).
 * Los umbrales son valores de referencia: ajústalos al dron que uses.
 */
public class VerificacionVuelo implements Serializable {

    public static final int BATERIA_MINIMA = 30;          // %
    public static final int SATELITES_MINIMOS = 8;
    public static final double VIENTO_MAXIMO = 10.0;      // m/s
    public static final double TEMPERATURA_MINIMA = 0.0;  // °C
    public static final double TEMPERATURA_MAXIMA = 40.0; // °C

    private int bateria;
    private int satelites;
    private double temperatura;
    private double velocidadViento;
    private String estadoGeneral;
    private boolean aptoParaVolar;
    private List<String> advertencias = new ArrayList<>();

    public VerificacionVuelo() {
    }

    public VerificacionVuelo(int bateria, int satelites, double temperatura, double velocidadViento) {
        this.bateria = bateria;
        this.satelites = satelites;
        this.temperatura = temperatura;
        this.velocidadViento = velocidadViento;
    }

    // ---------- Métodos del diagrama ----------

    /** Evalúa todas las condiciones y actualiza estadoGeneral y aptoParaVolar. */
    public boolean evaluarCondiciones() {
        generarAdvertencias();
        aptoParaVolar = advertencias.isEmpty();
        estadoGeneral = aptoParaVolar ? "Apto para volar" : "No apto para volar";
        return aptoParaVolar;
    }

    /** Reconstruye la lista de advertencias según los valores actuales. */
    public List<String> generarAdvertencias() {
        advertencias = new ArrayList<>();
        if (bateria < BATERIA_MINIMA) {
            advertencias.add("Batería baja (" + bateria + "%, mínimo " + BATERIA_MINIMA + "%)");
        }
        if (satelites < SATELITES_MINIMOS) {
            advertencias.add("Pocos satélites GPS (" + satelites + ", mínimo " + SATELITES_MINIMOS + ")");
        }
        if (velocidadViento > VIENTO_MAXIMO) {
            advertencias.add("Viento fuerte (" + velocidadViento + " m/s, máximo " + VIENTO_MAXIMO + " m/s)");
        }
        if (temperatura < TEMPERATURA_MINIMA || temperatura > TEMPERATURA_MAXIMA) {
            advertencias.add("Temperatura fuera de rango (" + temperatura + " °C)");
        }
        return advertencias;
    }

    // ---------- Getters y setters ----------

    public int getBateria() { return bateria; }
    public void setBateria(int bateria) { this.bateria = bateria; }

    public int getSatelites() { return satelites; }
    public void setSatelites(int satelites) { this.satelites = satelites; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public double getVelocidadViento() { return velocidadViento; }
    public void setVelocidadViento(double velocidadViento) { this.velocidadViento = velocidadViento; }

    public String getEstadoGeneral() { return estadoGeneral; }
    public void setEstadoGeneral(String estadoGeneral) { this.estadoGeneral = estadoGeneral; }

    public boolean isAptoParaVolar() { return aptoParaVolar; }
    public void setAptoParaVolar(boolean aptoParaVolar) { this.aptoParaVolar = aptoParaVolar; }

    public List<String> getAdvertencias() { return advertencias; }
    public void setAdvertencias(List<String> advertencias) { this.advertencias = advertencias; }
}
