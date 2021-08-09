package Vista;

import Modelo.Alumno;
import Modelo.Carrera;
import Modelo.Facultad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioTerminacion extends JPanel {

    private JPanel panel;

    private JComboBox<Alumno> alumnoComboBox;
    private DefaultComboBoxModel<Alumno> alumnoComboBoxModel;

    private JButton alumnoButton;

    private Facultad facultad;
    private ProgressListener listener;

    private Alumno alumnoSeleccionado;

    public FormularioTerminacion(ProgressListener listener, Facultad facultad) {
        panel = this;
        this.listener = listener;
        this.facultad = facultad;

        inicializarPanel();
    }

    public void cargarAlumnosComboBox() {
        alumnoComboBoxModel.removeAllElements();
        alumnoComboBoxModel.addAll(facultad.getAlumnos());
    }

    private void inicializarPanel() {
        alumnoButton = new JButton("Verificar");

        alumnoComboBox = new JComboBox<>();
        alumnoComboBoxModel = new DefaultComboBoxModel<>();
        alumnoComboBox.setModel(alumnoComboBoxModel);
        cargarAlumnosComboBox();

        this.add(alumnoComboBox);
        this.add(alumnoButton);

        alumnoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumnoSeleccionado = (Alumno) alumnoComboBox.getSelectedItem();
            }
        });

        alumnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean finalizo = false;
                Carrera carrera;
                if(alumnoSeleccionado != null) {
                    carrera = alumnoSeleccionado.getCarrera();
                    if(carrera != null) {
                        finalizo = carrera.alumnoFinalizo(alumnoSeleccionado);
                    }
                }
                if(finalizo) {
                    JOptionPane.showMessageDialog(panel, "Finalizo!");
                } else {
                    JOptionPane.showMessageDialog(panel, "No finalizo!");
                }
                cleanUp();
            }
        });
    }

    private void cleanUp() {
        alumnoComboBox.setSelectedIndex(0);
        listener.volver();
    }

}
