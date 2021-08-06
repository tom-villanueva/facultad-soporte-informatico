package Vista;

import Modelo.Facultad;

import javax.swing.*;
import java.awt.*;
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
    private JPanel panelBotones;
    private JFrame frame;
    private JPanel formularioAlumno;
    private JPanel formularioCarrera;
    private FormularioAlumnoMateria formularioAlumnoMateria;
    private FormularioAlumnoCarrera formularioAlumnoCarrera;

    private Facultad facultad;
    private JPanel panelContenedor;
    private CardLayout carta;

    public MenuPrincipal() {

        frame = new JFrame("MenuPrincipal");
        facultad = new Facultad();

        panelBotones = new JPanel();
        mainPanel = new JPanel(new BorderLayout());
        formularioAlumno = new FormularioAlumno(this, facultad);
        formularioCarrera = new FormularioCarrera(this, facultad);
        formularioAlumnoMateria = new FormularioAlumnoMateria(facultad);
        formularioAlumnoCarrera = new FormularioAlumnoCarrera(facultad);

        //Inicializar panel contenedor de cartas
        panelContenedor = new JPanel();
        carta = new CardLayout();
        panelContenedor.setLayout(carta);

        panelContenedor.add(formularioAlumno, "FormularioAlumno");
        panelContenedor.add(formularioCarrera, "FormularioCarrera");
        panelContenedor.add(formularioAlumnoMateria, "FormularioAlumnoMateria");
        panelContenedor.add(formularioAlumnoCarrera, "FormularioAlumnoCarrera");

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
                carta.show(panelContenedor, "FormularioAlumno");
            }
        });

        agregarCarreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carta.show(panelContenedor, "FormularioCarrera");
            }
        });

        inscribirAlumnoAMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioAlumnoMateria.actualizarCarreraComboBoxModel();
                carta.show(panelContenedor, "FormularioAlumnoMateria");
            }
        });

        inscribirAlumnoACarreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioAlumnoCarrera.actualizarAlumnoComboBoxModel();
                formularioAlumnoCarrera.actualizarCarreraComboBoxModel();
                carta.show(panelContenedor, "FormularioAlumnoCarrera");
            }
        });
    }

    @Override
    public void volver() {
        //carta.show(panelContnedor, "PanelInfo");
    }

    @Override
    public void run() {

        panelBotones.add(agregarCarreraButton);
        panelBotones.add(agregarAlumnoButton);
        panelBotones.add(inscribirAlumnoACarreraButton);
        panelBotones.add(inscribirAlumnoAMateriaButton);
        panelBotones.add(verTerminacionDeCarreraButton);
        mainPanel.add(panelBotones, BorderLayout.PAGE_START);
        mainPanel.add(panelContenedor, BorderLayout.CENTER);

        frame.setContentPane(this.mainPanel);
        frame.setSize(1300, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }

    public void go() {
        SwingUtilities.invokeLater(this);
    }

    public static void main(String[] args) {
        new MenuPrincipal().go();
    }
}