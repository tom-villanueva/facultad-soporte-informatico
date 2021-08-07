package Vista;

import Modelo.Alumno;
import Modelo.Carrera;
import Modelo.Facultad;
import Modelo.Materia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaPrincipal extends JPanel {

    private JPanel carrerasPanel;
    private JPanel alumnosPanel;

    private JTable tablaCarreras;
    private JTable tablaAlumnos;

    private DefaultTableModel tablaCarrerasModel;
    private DefaultTableModel tablaAlumnosModel;

    private JLabel carrerasLabel;
    private JLabel alumnosLabel;

    private Facultad facultad;
    private ProgressListener listener;

    public VistaPrincipal(ProgressListener listener, Facultad facultad) {
        this.listener = listener;
        this.facultad = facultad;

        inicializarPanelCarreras();
        inicializarPanelAlumnos();

        this.add(carrerasPanel);
        this.add(alumnosPanel);
    }

    private void inicializarTablaCarreras() {
        tablaCarrerasModel = (DefaultTableModel) tablaCarreras.getModel();

        tablaCarrerasModel.addColumn("Nombre");
        tablaCarrerasModel.addColumn("Tipo Plan");
    }

    public void cargarCarrerasTable() {
        tablaCarrerasModel.setRowCount(0);

        for(Carrera carrera : facultad.getCarreras()) {
            String[] rowData = {carrera.getNombre(), carrera.getPlan().getPlan().toString()};
            tablaCarrerasModel.addRow(rowData);
        }
    }

    private void inicializarTablaAlumnos() {
        tablaAlumnosModel = (DefaultTableModel) tablaAlumnos.getModel();

        tablaAlumnosModel.addColumn("Nombre");
        tablaAlumnosModel.addColumn("Carrera/s");
    }

    public void cargarAlumnosTable() {
        tablaAlumnosModel.setRowCount(0);

        for(Alumno alumno : facultad.getAlumnos()) {
            String[] rowData = {alumno.toString(), String.valueOf(alumno.getCarrera())};
            tablaAlumnosModel.addRow(rowData);
        }
    }

    private void inicializarPanelCarreras() {
        carrerasPanel = new JPanel();
        carrerasLabel = new JLabel("Carreras:");

        tablaCarreras = new JTable();
        inicializarTablaCarreras();
        cargarCarrerasTable();
        JScrollPane scrollPane = new JScrollPane(tablaCarreras, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        carrerasPanel.add(carrerasLabel);
        carrerasPanel.add(scrollPane);
    }

    private void inicializarPanelAlumnos() {
        alumnosPanel = new JPanel();
        alumnosLabel = new JLabel("Alumnos:");

        tablaAlumnos = new JTable();
        inicializarTablaAlumnos();
        cargarAlumnosTable();
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        alumnosPanel.add(alumnosLabel);
        alumnosPanel.add(scrollPane);
    }

}
