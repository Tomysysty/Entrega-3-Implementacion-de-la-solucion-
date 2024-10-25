import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Interfaz extends JPanel {
    private JTabbedPane tabbedPane;
    private JPanel panelSolicitarReserva;
    private JPanel panelConsultarReservas;
    private JPanel panelEliminarReservas;

    private JComboBox<String> comboBoxTipoEspacio;
    private JComboBox<String> comboBoxFecha;
    private JComboBox<String> comboBoxHora;
    private JComboBox<String> comboBoxLugar;
    private JComboBox<String> comboBoxUsuario;
    private JButton botonSolicitar;

    private JTextArea areaReservas; // Para mostrar las reservas consultadas
    private JComboBox<String> comboBoxReservasEliminar;
    private JButton botonEliminar;

    private ArrayList<Usuario> usuarios;

    public Interfaz(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;

        // Crear el JTabbedPane
        tabbedPane = new JTabbedPane();

        // Panel para solicitar reserva
        panelSolicitarReserva = crearPanelSolicitarReserva();
        tabbedPane.addTab("Solicitar Reserva", panelSolicitarReserva);

        // Panel para consultar reservas
        panelConsultarReservas = crearPanelConsultarReservas();
        tabbedPane.addTab("Consultar Reservas", panelConsultarReservas);

        // Panel para eliminar reservas
        panelEliminarReservas = crearPanelEliminarReservas();
        tabbedPane.addTab("Eliminar Reservas", panelEliminarReservas);

        // Agregar el JTabbedPane al panel principal
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelSolicitarReserva() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Margen alrededor de los componentes

        // Configuración de los componentes
        comboBoxTipoEspacio = new JComboBox<>(new String[]{"Cancha de Fútbol", "Pista de Tenis", "Gimnasio", "Piscina"});
        comboBoxFecha = new JComboBox<>(new String[]{"23-11-2024", "25-10-2024", "01-12-2024"});
        comboBoxHora = new JComboBox<>(new String[]{"6:00 am", "8:00 am", "3:00 pm"});
        comboBoxLugar = new JComboBox<>(new String[]{"Complejo Deportivo 1", "Complejo Deportivo 2", "Complejo Deportivo 3"});
        comboBoxUsuario = new JComboBox<>();

        botonSolicitar = new JButton("Solicitar Reserva");

        // Llenar el JComboBox de usuarios
        for (Usuario usuario : usuarios) {
            comboBoxUsuario.addItem(usuario.getNombre());
        }

        // Añadir componentes al panel con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        panel.add(new JLabel("Tipo de Espacio:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        panel.add(comboBoxTipoEspacio, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Fecha:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(comboBoxFecha, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Hora:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(comboBoxHora, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Lugar:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(comboBoxLugar, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(comboBoxUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(botonSolicitar, gbc);

        // Acción del botón "Solicitar Reserva"
        botonSolicitar.addActionListener(e -> solicitarReserva());

        panel.setBorder(BorderFactory.createTitledBorder("Formulario de Solicitud de Reserva"));

        return panel;
    }

    private JPanel crearPanelConsultarReservas() {
        JPanel panel = new JPanel(new BorderLayout());
        areaReservas = new JTextArea(10, 30);
        areaReservas.setEditable(false);

        JComboBox<String> comboBoxUsuarioConsulta = new JComboBox<>();
        for (Usuario usuario : usuarios) {
            comboBoxUsuarioConsulta.addItem(usuario.getNombre());
        }

        JButton botonConsultar = new JButton("Consultar Reservas");
        botonConsultar.addActionListener(e -> consultarReservas(comboBoxUsuarioConsulta.getSelectedItem().toString()));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Seleccione un Usuario:"));
        topPanel.add(comboBoxUsuarioConsulta);
        topPanel.add(botonConsultar);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaReservas), BorderLayout.CENTER); // Cambiar ScrollPane a JScrollPane

        panel.setBorder(BorderFactory.createTitledBorder("Consulta de Reservas"));

        return panel;
    }

    private JPanel crearPanelEliminarReservas() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        comboBoxReservasEliminar = new JComboBox<>();
        JButton botonCargarReservasEliminar = new JButton("Cargar Reservas");
        botonCargarReservasEliminar.setPreferredSize(new Dimension(100, 30)); // Reducir tamaño del botón
        botonCargarReservasEliminar.setMargin(new Insets(5, 10, 5, 10)); // Ajustar margen

        botonCargarReservasEliminar.addActionListener(e -> cargarReservasParaEliminar());

        botonEliminar = new JButton("Eliminar Reserva");
        botonEliminar.setPreferredSize(new Dimension(100, 30)); // Reducir tamaño del botón
        botonEliminar.setMargin(new Insets(5, 10, 5, 10)); // Ajustar margen
        botonEliminar.addActionListener(e -> eliminarReserva());

        panel.add(comboBoxReservasEliminar);
        panel.add(botonCargarReservasEliminar);
        panel.add(botonEliminar);

        panel.setBorder(BorderFactory.createTitledBorder("Eliminación de Reservas"));

        return panel;
    }

    private void solicitarReserva() {
        String tipoEspacioSeleccionado = (String) comboBoxTipoEspacio.getSelectedItem();
        String fechaSeleccionada = (String) comboBoxFecha.getSelectedItem();
        String horaSeleccionada = (String) comboBoxHora.getSelectedItem();
        String lugarSeleccionado = (String) comboBoxLugar.getSelectedItem();
        String usuarioSeleccionado = (String) comboBoxUsuario.getSelectedItem();

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(usuarioSeleccionado)) {
                Reserva nuevaReserva = new Reserva(fechaSeleccionada, Integer.parseInt(horaSeleccionada.split(":")[0]), tipoEspacioSeleccionado, lugarSeleccionado, usuario);
                usuario.solicitarReserva(nuevaReserva);
                JOptionPane.showMessageDialog(this, "Reserva solicitada con éxito para " + usuario.getNombre());
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Error al solicitar la reserva.");
    }

    private void consultarReservas(String usuarioSeleccionado) {
        areaReservas.setText(""); // Limpiar el área de texto

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(usuarioSeleccionado)) {
                areaReservas.append("Reservas de " + usuario.getNombre() + ":\n\n");
                for (Reserva reserva : usuario.getReservas()) {
                    areaReservas.append("Fecha: " + reserva.getFecha() + "\n");
                    areaReservas.append("Hora: " + reserva.getHora() + "\n");
                    areaReservas.append("Tipo: " + reserva.getTipoEspacio() + "\n");
                    areaReservas.append("Lugar: " + reserva.getLugar() + "\n\n");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
    }

    private void cargarReservasParaEliminar() {
        comboBoxReservasEliminar.removeAllItems(); // Limpiar comboBox

        for (Usuario usuario : usuarios) {
            for (Reserva reserva : usuario.getReservas()) {
                comboBoxReservasEliminar.addItem(reserva.toString());
            }
        }
    }

    private void eliminarReserva() {
        String reservaSeleccionada = (String) comboBoxReservasEliminar.getSelectedItem();
        for (Usuario usuario : usuarios) {
            if (usuario.eliminarReserva(reservaSeleccionada)) {
                JOptionPane.showMessageDialog(this, "Reserva eliminada con éxito.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Error al eliminar la reserva.");
    }
}
