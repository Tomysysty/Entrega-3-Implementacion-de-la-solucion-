import javax.swing.*;
import java.util.ArrayList;

public class SistemaReservas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Usuario> usuarios = new ArrayList<>();
            usuarios.add(new Usuario(1, "David", "1234"));
            usuarios.add(new Usuario(2, "Emilio1", "contraseña2"));
            usuarios.add(new Usuario(3, "Emilio2", "contraseña3"));

            // Mostrar cuadro de diálogo de inicio de sesión
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID:"));
            String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");

            // Validar login
            boolean loginExitoso = false;
            for (Usuario usuario : usuarios) {
                if (usuario.getNombre().equals(JOptionPane.showInputDialog("Ingrese su nombre:"))) {
                    usuario.login(id, contraseña);
                    loginExitoso = true;
                    break;
                }
            }

            if (!loginExitoso) {
                JOptionPane.showMessageDialog(null, "Acceso denegado. Usuario o contraseña incorrectos.");
                return; // Salir si el login falla
            }

            JFrame frame = new JFrame("Sistema de Reservas de Espacios Deportivos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new Interfaz(usuarios));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
