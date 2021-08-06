package Vista;

import Modelo.Carrera;
import Modelo.Facultad;
import Modelo.PlanDeEstudio;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class FormularioCarrera extends JPanel implements ProgressListener{
    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel cantidadOptativasLabel;
    private JFormattedTextField cantidadOptativasField;
    private JButton agregarPlanButton;
    private JButton aceptarButton;
    private ProgressListener listener;
    private FormularioPlan planPanel;

    private Carrera carrera;
    private NumberFormatter formatter;

    public FormularioCarrera(ProgressListener listener, Facultad facultad) {
        carrera = new Carrera();
        JPanel panel = this;
        panel.setBorder(BorderFactory.createTitledBorder("Agregar Carrera"));
        this.setLayout(new BorderLayout());
        planPanel = new FormularioPlan(this);
        planPanel.setVisible(false);
        nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(10);
        cantidadOptativasLabel = new JLabel("Cantidad optativas:");

        //Codigo para el formateado
        NumberFormat format = NumberFormat.getInstance();
        formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        cantidadOptativasField = new JFormattedTextField(formatter);
        cantidadOptativasField.setColumns(10);
        cantidadOptativasField.setEnabled(false);

        agregarPlanButton = new JButton("Editar plan de estudio");
        aceptarButton = new JButton("Aceptar carrera");
        this.listener = listener;

        setComponents();

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                carrera.setNombre(nombre);

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
                System.out.println(carrera);
                cleanUp();
                listener.volver();
            }
        });

        agregarPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planPanel.setCarrera(carrera);
                planPanel.setPlan(new PlanDeEstudio());
                planPanel.setVisible(true);
                agregarPlanButton.setEnabled(false);
                aceptarButton.setEnabled(false);
            }
        });
    }

    private boolean verificarCarreraCorrecta(Carrera carrera){
        if(carrera.getPlan() == null && carrera.getNombre() == null){
            return false;
        }
        else {
            return true;
        }
    }

    private void setComponents() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.add(nombreLabel);
        panelFormulario.add(nombreField);
        panelFormulario.add(agregarPlanButton);
        panelFormulario.add(cantidadOptativasLabel);
        panelFormulario.add(cantidadOptativasField);

        this.add(panelFormulario, BorderLayout.PAGE_START);
        this.add(planPanel, BorderLayout.CENTER);
        this.add(aceptarButton, BorderLayout.PAGE_END);
    }

    private void cleanUp() {
        carrera = new Carrera();
        nombreField.setText("");
    }

    @Override
    public void volver() {
        agregarPlanButton.setEnabled(true);
        aceptarButton.setEnabled(true);

        carrera.setCantidadOptativas();
        formatter.setMaximum(carrera.getCantidadOptativas());
        cantidadOptativasField.setEnabled(true);
    }
}
