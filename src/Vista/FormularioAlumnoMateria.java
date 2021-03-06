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

    private JTable alumnoTable;
    private DefaultTableModel alumnoTableModel;

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

    private void setTamanoColumnas(JTable table) {
        int filasVisibles = 10;
        int cols = table.getColumnModel().getTotalColumnWidth();
        int rows = table.getRowHeight() * filasVisibles;
        Dimension d = new Dimension( cols, rows );
        table.setPreferredScrollableViewportSize( d );
    }

    private void crearColumnasAlumnoTable() {
        alumnoTableModel = (DefaultTableModel) alumnoTable.getModel();

        alumnoTableModel.addColumn("N?? Cuatri");
        alumnoTableModel.addColumn("Materia");
        alumnoTableModel.addColumn("Nota cursada");
        alumnoTableModel.addColumn("Nota final");
        alumnoTableModel.addColumn("Estado");

        setTamanoColumnas(alumnoTable);
    }

    private void popularAlumnoTable(Integer num, Materia materia, int notaC, int notaF, EstadoCursada estado) {
        String[] rowData = {num.toString(), materia.toString(), String.valueOf(notaC), String.valueOf(notaF), estado.name()};
        alumnoTableModel.addRow(rowData);
    }

    private void cleanUpTable(DefaultTableModel model) {
        model.setRowCount(0);
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
                cleanUpTable(alumnoTableModel);
            }
        });

        alumnoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumnoSeleccionado = (Alumno) alumnoComboBox.getSelectedItem();
                if(alumnoSeleccionado != null) {
                    alumnoSeleccionadoLabel.setText(alumnoSeleccionado.toString());
                    cargarMateriasComboBoxModel();

                    cleanUpTable(alumnoTableModel);
                    for(Cursada cursada : alumnoSeleccionado.getCursadas()) {
                        popularAlumnoTable(
                                cursada.getMateria().getNumeroCuatrimestre(),
                                cursada.getMateria(),
                                cursada.getNotaParcial(),
                                cursada.getNotaFinal(),
                                cursada.getEstado()
                                );
                    }
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

        alumnoTable = new JTable();
        crearColumnasAlumnoTable();
        JScrollPane scrollPane = new JScrollPane(alumnoTable);

        aceptarButton = new JButton("inscribir");

        panelStart.add(alumnoLabel);
        panelStart.add(alumnoSeleccionadoLabel);
        panelStart.add(scrollPane);

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
                int respuesta = JOptionPane.showConfirmDialog(panel, "Confirmar", "Confirmar inscripci??n de alumno", JOptionPane.YES_NO_OPTION,
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
                        JOptionPane.showMessageDialog(panel, "Cancelada inscripci??n");
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
        cleanUpTable(alumnoTableModel);
    }

}
