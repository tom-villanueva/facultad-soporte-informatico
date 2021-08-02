package Vista;

import Modelo.Carrera;
import Modelo.Facultad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioCarrera extends JPanel {
    private JLabel nombreLabel;
    private JTextField nombreField;
    private JButton agregarPlanButton;
    private JButton aceptarButton;
    private ProgressListener listener;

    public FormularioCarrera(ProgressListener listener, Facultad facultad) {
        JPanel panel = this;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(10);
        agregarPlanButton = new JButton("Agregar plan de estudio");
        aceptarButton = new JButton("Aceptar");
        this.listener = listener;

        setComponents();

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                Carrera carrera = new Carrera(nombre);
                //carrera.setPlan();
                //carrera.setCantidadOptativas();

                int respuesta = JOptionPane.showConfirmDialog(panel, "Confirmar", "Confirmar creación de carrera", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                switch (respuesta) {
                    case 0:
                        if(verificarCarreraCorrecta(carrera)) {
                            facultad.agregarCarrera(carrera);
                            JOptionPane.showMessageDialog(panel, "Creada carrera: "+nombre);
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "Falta plan de carrera");
                        }
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panel, "Cancelado");
                        break;
                }
                cleanUp();
                listener.volver();
            }
        });

        agregarPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private boolean verificarCarreraCorrecta(Carrera carrera){
        if(carrera.getPlan() == null){
            return false;
        }
        else {
            return true;
        }
    }

    private void setComponents() {
        this.add(nombreLabel);
        this.add(nombreField);
        this.add(agregarPlanButton);
        this.add(aceptarButton);
    }

    private void cleanUp() {
        nombreField.setText("");
    }
}
