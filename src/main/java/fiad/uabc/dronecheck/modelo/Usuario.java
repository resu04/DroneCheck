package fiad.uabc.dronecheck.modelo;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Usuario (Modelo).
 * Relaciones del diagrama:
 *  - Usuario 1 registra 0..* Dron
 *  - Usuario 1 realiza 0..* Vuelo
 */
public class Usuario implements Serializable {

    private static final int ITERACIONES = 65536;
    private static final int LONGITUD_CLAVE = 256;

    private int id;
    private String nombreCompleto;
    private String nombreUsuario;
    private String correo;
    private String contrasenaHash;
    private String etiquetaPerfil;
    private final List<Dron> drones = new ArrayList<>();
    private final List<Vuelo> vuelos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombreCompleto, String nombreUsuario, String correo,
                   String contrasenaPlana, String etiquetaPerfil) {
        this.nombreCompleto = nombreCompleto;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.etiquetaPerfil = etiquetaPerfil;
        setContrasena(contrasenaPlana);
    }

    // ---------- Métodos del diagrama ----------

    /** Valida que los datos obligatorios estén completos. La persistencia la hace el controlador/DAO. */
    public boolean registrar() {
        return !vacio(nombreCompleto)
                && !vacio(nombreUsuario)
                && correoValido(correo)
                && !vacio(contrasenaHash);
    }

    /** Actualiza los datos editables del perfil. */
    public boolean actualizarDatos(String nombreCompleto, String correo, String etiquetaPerfil) {
        if (vacio(nombreCompleto) || !correoValido(correo)) {
            return false;
        }
        this.nombreCompleto = nombreCompleto.trim();
        this.correo = correo.trim();
        this.etiquetaPerfil = etiquetaPerfil;
        return true;
    }

    // ---------- Contraseña (PBKDF2 con sal, solo JDK) ----------

    /** Recibe la contraseña en texto plano y guarda únicamente su hash. */
    public void setContrasena(String contrasenaPlana) {
        if (contrasenaPlana == null || contrasenaPlana.isEmpty()) {
            this.contrasenaHash = null;
            return;
        }
        try {
            byte[] sal = new byte[16];
            new SecureRandom().nextBytes(sal);
            byte[] hash = pbkdf2(contrasenaPlana.toCharArray(), sal);
            this.contrasenaHash = Base64.getEncoder().encodeToString(sal)
                    + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("No se pudo generar el hash", e);
        }
    }

    public boolean verificarContrasena(String contrasenaPlana) {
        if (contrasenaPlana == null || contrasenaHash == null) {
            return false;
        }
        String[] partes = contrasenaHash.split(":");
        if (partes.length != 2) {
            return false;
        }
        try {
            byte[] sal = Base64.getDecoder().decode(partes[0]);
            byte[] esperado = Base64.getDecoder().decode(partes[1]);
            byte[] calculado = pbkdf2(contrasenaPlana.toCharArray(), sal);
            return MessageDigest.isEqual(esperado, calculado);
        } catch (GeneralSecurityException | IllegalArgumentException e) {
            return false;
        }
    }

    private static byte[] pbkdf2(char[] contrasena, byte[] sal) throws GeneralSecurityException {
        PBEKeySpec spec = new PBEKeySpec(contrasena, sal, ITERACIONES, LONGITUD_CLAVE);
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec).getEncoded();
    }

    // ---------- Relaciones ----------

    public void agregarDron(Dron dron) {
        if (dron != null) {
            dron.setIdUsuario(this.id);
            drones.add(dron);
        }
    }

    public void agregarVuelo(Vuelo vuelo) {
        if (vuelo != null) {
            vuelo.setIdUsuario(this.id);
            vuelos.add(vuelo);
        }
    }

    public List<Dron> getDrones() { return Collections.unmodifiableList(drones); }
    public List<Vuelo> getVuelos() { return Collections.unmodifiableList(vuelos); }

    // ---------- Utilidades ----------

    private static boolean vacio(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static boolean correoValido(String s) {
        return s != null && s.trim().matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
    }

    // ---------- Getters y setters ----------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasenaHash() { return contrasenaHash; }
    public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }

    public String getEtiquetaPerfil() { return etiquetaPerfil; }
    public void setEtiquetaPerfil(String etiquetaPerfil) { this.etiquetaPerfil = etiquetaPerfil; }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombreUsuario=" + nombreUsuario + ", correo=" + correo + "}";
    }
}
