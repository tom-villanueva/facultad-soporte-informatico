package Vista;

import Modelo.Facultad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal implements Runnable, ProgressListener{
    private JLabel tituloLabel;
    private JButton agregarCarreraButton;
    private JButton agregarAlumnoButton;
    private JButton inscribirAlumnoACarreraButton;
    private JButton inscribirAlumnoAMateriaButton;
    private JButton verTerminacionDeCarreraButton;
    private JPanel mainPanel;
    private JFrame frame;
    private JPanel formularioAlumno;
    private JPanel formularioCarrera;
    private Facultad facultad;

    public MenuPrincipal() {

        this.frame = new JFrame("MenuPrincipal");
        this.facultad = new Facultad();

        this.mainPanel = new JPanel();
        this.formularioAlumno = new FormularioAlumno(this, facultad);
        this.formularioCarrera = new FormularioCarrera(this, facultad);

        this.instanciarBotones();
    }

    private void instanciarBotones() {
        this.agregarCarreraButton = new JButton("Agregar Carrera");
        this.agregarAlumnoButton = new JButton("Agregar Alumno");
        this.inscribirAlumnoACarreraButton = new JButton("Inscribir Alumno a Carrera");
        this.inscribirAlumnoAMateriaButton = new JButton("Inscribir Alumno a Materia");
        this.verTerminacionDeCarreraButton = new JButton("Ver terminación de Carrera");

        agregarAlumnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(formularioAlumno);
                frame.pack();
            }
        });

        agregarCarreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(formularioCarrera);
                frame.pack();
            }
        });
    }

    @Override
    public void volver() {
        this.frame.setContentPane(this.mainPanel);
    }

    @Override
    public void run() {

        this.mainPanel.add(this.agregarCarreraButton);
        this.mainPanel.add(this.agregarAlumnoButton);
        this.mainPanel.add(this.inscribirAlumnoACarreraButton);
        this.mainPanel.add(this.inscribirAlumnoAMateriaButton);
        this.mainPanel.add(this.verTerminacionDeCarreraButton);


        frame.setContentPane(this.mainPanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void go() {
        SwingUtilities.invokeLater(this);
    }

    public static void main(String[] args) {
        new MenuPrincipal().go();
    }
}