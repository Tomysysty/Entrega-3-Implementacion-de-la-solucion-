import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Clase que representa una reserva de un espacio deportivo
class Reserva {
    private String fecha;
    private int hora; // Puede ser en formato de 24 horas o 12 horas, dependiendo de tu preferencia.
    private String tipoEspacio;
    private String lugar;
    private Usuario usuario;

    public Reserva(String fecha, int hora, String tipoEspacio, String lugar, Usuario usuario) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoEspacio = tipoEspacio;
        this.lugar = lugar;
        this.usuario = usuario;
    }

    // Métodos getter
    public String getFecha() {
        return fecha;
    }

    public int getHora() {
        return hora;
    }

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public String getLugar() {
        return lugar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "fecha='" + fecha + '\'' +
                ", hora=" + hora +
                ", tipoEspacio='" + tipoEspacio + '\'' +
                ", lugar='" + lugar + '\'' +
                '}';
    }
}
