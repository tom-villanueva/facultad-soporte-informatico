package Vista;

import Modelo.Alumno;
import Modelo.Facultad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FormularioAlumno extends JPanel {

    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel apellidoLabel;
    private JTextField apellidoField;
    private JButton aceptarButton;

    public FormularioAlumno(ProgressListener listener, Facultad facultad) {
        JPanel panel = this;
        panel.setBorder(BorderFactory.createTitledBorder("Agregar Alumno"));
        this.nombreLabel = new JLabel("Nombre:");
        this.nombreField = new JTextField(10);
        this.apellidoLabel = new JLabel("apellido:");
        this.apellidoField = new JTextField(10);
        this.aceptarButton = new JButton("Aceptar");
        setComponents();

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();

                Alumno alumno = new Alumno(nombre, apellido);

                int respuesta = JOptionPane.showConfirmDialog(panel, "Confirmar", "Confirmar creación de alumno", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                switch (respuesta) {
                    case 0 -> {
                        if(!Objects.equals(nombre, "") && !Objects.equals(apellido, "")) {
                            facultad.agregarAlumno(alumno);
                            JOptionPane.showMessageDialog(panel, "Creado alumno: " + nombre + " " + apellido);
                            listener.volver();
                        } else {
                            JOptionPane.showMessageDialog(panel, "Debe poner nombre y apellido");
                        }
                    }
                    case 1 -> {
                        JOptionPane.showMessageDialog(panel, "Cancelado");
                        listener.volver();
                    }
                }
                cleanUp();
                //listener.volver();
            }
        });
    }

    private void setComponents() {
        this.add(this.nombreLabel);
        this.add(this.nombreField);
        this.add(this.apellidoLabel);
        this.add(this.apellidoField);
        this.add(this.aceptarButton);
    }

    private void cleanUp() {
        this.nombreField.setText("");
        this.apellidoField.setText("");
    }
}
