import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombre;
    private String contraseña;
    private ArrayList<Reserva> reservas;

    public Usuario(int id, String nombre, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.reservas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void solicitarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public boolean eliminarReserva(String reservaSeleccionada) {
        // Lógica para eliminar una reserva basada en la descripción de la reserva
        for (Reserva reserva : reservas) {
            if (reserva.toString().equals(reservaSeleccionada)) {
                reservas.remove(reserva);
                return true;
            }
        }
        return false;
    }

    public void login(int id, String contraseña) {
        if (this.id == id && this.contraseña.equals(contraseña)) {
            System.out.println("Bienvenido " + nombre);
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }
}
