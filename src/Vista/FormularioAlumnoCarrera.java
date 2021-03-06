package Vista;

import Modelo.Alumno;
import Modelo.Carrera;
import Modelo.Facultad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioAlumnoCarrera extends JPanel {

    JPanel panel;
    private JPanel panelComboBox;
    private JPanel panelBotones;

    private JLabel carreraLabel;
    private JLabel alumnosLabel;
    private JLabel carreraSeleccionadaLabel;
    private JLabel alumnoSeleccionadoLabel;
    private JLabel inscribirLabel;

    private JComboBox<Carrera> carreraComboBox;
    private JComboBox<Alumno> alumnoComboBox;

    private DefaultComboBoxModel<Carrera> carreraModel;
    private DefaultComboBoxModel<Alumno> alumnoModel;

    private JButton aceptarButton;

    private Facultad facultad;
    private ProgressListener listener;
    private Carrera carreraSeleccionada;
    private Alumno alumnoSeleccionado;

    public FormularioAlumnoCarrera(ProgressListener listener, Facultad facultad) {
        this.listener = listener;
        panel = this;
        panel.setLayout(new BorderLayout());
        this.facultad = facultad;

        inicializarPanelBotones();
        inicializarPanelComboBox();

        panel.add(panelComboBox, BorderLayout.PAGE_START);
        panel.add(panelBotones, BorderLayout.CENTER);
    }

    public void actualizarAlumnoComboBoxModel() {
        alumnoModel.removeAllElements();
        alumnoModel.addAll(facultad.getAlumnos());
    }

    public void actualizarCarreraComboBoxModel() {
        carreraModel.removeAllElements();
        carreraModel.addAll(facultad.getCarreras());
    }

    private void initCarreraComboBoxModel() {
        carreraModel.addAll(facultad.getCarreras());
    }

    private void initAlumnosModel() {
        alumnoModel.addAll(facultad.getAlumnos());
    }

    private void inicializarPanelComboBox() {
        panelComboBox = new JPanel();
        carreraLabel = new JLabel("Carreras:");
        alumnosLabel = new JLabel("Alumnos:");

        carreraComboBox = new JComboBox<>();
        carreraModel = new DefaultComboBoxModel<>();
        initCarreraComboBoxModel();
        carreraComboBox.setModel(carreraModel);

        alumnoComboBox = new JComboBox<>();
        alumnoModel = new DefaultComboBoxModel<>();
        initAlumnosModel();
        alumnoComboBox.setModel(alumnoModel);

        panelComboBox.add(carreraLabel);
        panelComboBox.add(carreraComboBox);
        panelComboBox.add(alumnosLabel);
        panelComboBox.add(alumnoComboBox);

        carreraComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carreraSeleccionada = (Carrera) carreraComboBox.getSelectedItem();
                if(carreraSeleccionada != null) {
                    carreraSeleccionadaLabel.setText(carreraSeleccionada.getNombre());
                }
            }
        });

        alumnoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumnoSeleccionado = (Alumno) alumnoComboBox.getSelectedItem();
                if(alumnoSeleccionado != null) {
                    alumnoSeleccionadoLabel.setText(alumnoSeleccionado.toString());
                }
            }
        });
    }

    private void inicializarPanelBotones() {
        JPanel labelPanel = new JPanel();
        panelBotones = new JPanel(new BorderLayout());

        inscribirLabel = new JLabel("Inscribir a");
        JLabel enLabel = new JLabel("en");
        alumnoSeleccionadoLabel = new JLabel();
        carreraSeleccionadaLabel = new JLabel();
        aceptarButton = new JButton("Inscribir");

        labelPanel.add(inscribirLabel);
        labelPanel.add(alumnoSeleccionadoLabel);
        labelPanel.add(enLabel);
        labelPanel.add(carreraSeleccionadaLabel);

        panelBotones.add(labelPanel,  BorderLayout.PAGE_START);
        panelBotones.add(aceptarButton, BorderLayout.PAGE_END);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panel, "Confirmar", "Confirmar inscripci??n de alumno", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                switch (respuesta) {
                    case 0 -> {
                        if(alumnoSeleccionado != null && carreraSeleccionada != null) {
                            if (!(carreraSeleccionada.getAlumnos().contains(alumnoSeleccionado)) && alumnoSeleccionado.getCarrera() == null) {
                                carreraSeleccionada.agregarAlumno(alumnoSeleccionado);
                                alumnoSeleccionado.setCarrera(carreraSeleccionada);
                                JOptionPane.showMessageDialog(panel, "Inscripto alumno " + alumnoSeleccionado.toString() + " en " + carreraSeleccionada.getNombre());
                                cleanUp();
                                listener.volver();
                            } else {
                                JOptionPane.showMessageDialog(panel, "Alumno ya inscripto en/la carrera");
                                cleanUp();
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Selecciona alumno y carrera");
                            cleanUp();
                        }

                    }
                    case 1 -> {
                        JOptionPane.showMessageDialog(panel, "Cancelada inscripci??n de alumno");
                        cleanUp();
                        listener.volver();
                    }
                }
            }
        });
    }

    private void cleanUp() {
        carreraComboBox.setSelectedIndex(0);
        alumnoComboBox.setSelectedIndex(0);
        carreraSeleccionadaLabel.setText("");
        alumnoSeleccionadoLabel.setText("");
    }

}
