package Vista;

import Modelo.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioAlumnoMateria extends JPanel {

    private JPanel panel;
    private JPanel panelComboBox;
    private JPanel panelFormulario;

    private JLabel carreraLabel;
    private JLabel alumnosLabel;
    private JLabel alumnoSeleccionadoLabel;
    private JLabel alumnoLabel;
    private JLabel materiasLabel;

    private JComboBox<Carrera> carreraComboBox;
    private JComboBox<Alumno> alumnoComboBox;
    private JComboBox<Materia> materiaComboBox;

    private DefaultComboBoxModel<Carrera> carreraModel;
    private DefaultComboBoxModel<Alumno> alumnoModel;
    private DefaultComboBoxModel<Materia> materiaModel;

    private JButton aceptarButton;

    private Facultad facultad;
    private ProgressListener listener;
    private Alumno alumnoSeleccionado;
    private Carrera carreraSeleccionada;
    private Materia materiaSeleccionada;

    public FormularioAlumnoMateria(ProgressListener listener, Facultad facultad) {
        this.listener = listener;
        panel = this;
        this.setLayout(new BorderLayout());
        this.facultad = facultad;

        inicializarPanelComboBox();
        inicializarPanelFormulario();

        panel.add(panelComboBox, BorderLayout.PAGE_START);
        panel.add(panelFormulario, BorderLayout.CENTER);
    }

    private void initCarreraComboBoxModel() {
        carreraModel.addAll(facultad.getCarreras());
    }

    public void actualizarCarreraComboBoxModel() {
        carreraModel.removeAllElements();
        carreraModel.addAll(facultad.getCarreras());
    }

    private void cargarAlumnosComboBoxModel() {
        alumnoModel.removeAllElements();
        if(carreraSeleccionada != null) {
            alumnoModel.addAll(carreraSeleccionada.getAlumnos());
        }
    }

    private void cargarMateriasComboBoxModel() {
        materiaModel.removeAllElements();
        materiaModel.addAll(carreraSeleccionada.getPlan().verMaterias(alumnoSeleccionado));
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
        alumnoComboBox.setModel(alumnoModel);

        panelComboBox.add(carreraLabel);
        panelComboBox.add(carreraComboBox);
        panelComboBox.add(alumnosLabel);
        panelComboBox.add(alumnoComboBox);

        carreraComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carreraSeleccionada = (Carrera) carreraComboBox.getSelectedItem();
                cargarAlumnosComboBoxModel();
            }
        });

        alumnoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumnoSeleccionado = (Alumno) alumnoComboBox.getSelectedItem();
                if(alumnoSeleccionado != null) {
                    alumnoSeleccionadoLabel.setText(alumnoSeleccionado.toString());
                    cargarMateriasComboBoxModel();
                }
            }
        });
    }

    private void inicializarPanelFormulario() {
        panelFormulario = new JPanel(new BorderLayout());
        JPanel panelStart = new JPanel();
        JPanel panelCenter = new JPanel();

        alumnoLabel = new JLabel("Alumno Seleccionado:");
        alumnoSeleccionadoLabel = new JLabel();
        materiasLabel = new JLabel("Materias posibles:");

        materiaComboBox = new JComboBox<>();
        materiaModel = new DefaultComboBoxModel<>();
        materiaComboBox.setModel(materiaModel);

        aceptarButton = new JButton("inscribir");

        panelStart.add(alumnoLabel);
        panelStart.add(alumnoSeleccionadoLabel);

        panelCenter.add(materiasLabel);
        panelCenter.add(materiaComboBox);

        panelFormulario.add(panelStart, BorderLayout.PAGE_START);
        panelFormulario.add(panelCenter, BorderLayout.CENTER);
        panelFormulario.add(aceptarButton, BorderLayout.PAGE_END);

        materiaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materiaSeleccionada = (Materia) materiaComboBox.getSelectedItem();
            }
        });

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panel, "Confirmar", "Confirmar inscripción de alumno", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                switch(respuesta) {
                    case 0:
                        if(materiaSeleccionada != null) {
                            Cursada cursada = new Cursada(materiaSeleccionada);
                            if(!alumnoSeleccionado.estaInscriptoEn(materiaSeleccionada)) {
                                alumnoSeleccionado.inscribirEn(cursada);
                                JOptionPane.showMessageDialog(panel, "Inscripto alumno en materia");
                                cleanUp();
                                listener.volver();
                            } else {
                                JOptionPane.showMessageDialog(panel, "Alumno ya inscripto en esa materia");
                                cleanUp();
                            }
                        }
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panel, "Cancelada inscripción");
                        cleanUp();
                        listener.volver();
                        break;
                }
            }
        });
    }

    private void cleanUp() {
        carreraComboBox.setSelectedIndex(0);
        materiaComboBox.setSelectedIndex(0);
        alumnoComboBox.removeAll();
        materiaComboBox.removeAll();
        alumnoSeleccionadoLabel.setText("");
    }

}
