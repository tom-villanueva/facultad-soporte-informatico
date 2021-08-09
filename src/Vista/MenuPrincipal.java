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
    private JButton menuPrincipalButton;
    private JPanel mainPanel;
    private JPanel panelBotones;
    private JFrame frame;
    private JPanel formularioAlumno;
    private JPanel formularioCarrera;
    private FormularioAlumnoMateria formularioAlumnoMateria;
    private FormularioAlumnoCarrera formularioAlumnoCarrera;
    private VistaPrincipal vistaPrincipal;
    private FormularioTerminacion formularioTerminacion;

    private Facultad facultad;
    private JPanel panelContenedor;
    private CardLayout carta;

    public MenuPrincipal(Facultad facultad) {

        frame = new JFrame("Sistema de soporte informático");
        this.facultad = facultad;

        panelBotones = new JPanel();
        mainPanel = new JPanel(new BorderLayout());
        formularioAlumno = new FormularioAlumno(this, facultad);
        formularioCarrera = new FormularioCarrera(this, facultad);
        formularioAlumnoMateria = new FormularioAlumnoMateria(this, facultad);
        formularioAlumnoCarrera = new FormularioAlumnoCarrera(this, facultad);
        vistaPrincipal = new VistaPrincipal(this, facultad);
        formularioTerminacion = new FormularioTerminacion(this, facultad);

        //Inicializar panel contenedor de cartas
        panelContenedor = new JPanel();
        carta = new CardLayout();
        panelContenedor.setLayout(carta);

        panelContenedor.add(vistaPrincipal, "VistaPrincipal");
        panelContenedor.add(formularioAlumno, "FormularioAlumno");
        panelContenedor.add(formularioCarrera, "FormularioCarrera");
        panelContenedor.add(formularioAlumnoMateria, "FormularioAlumnoMateria");
        panelContenedor.add(formularioAlumnoCarrera, "FormularioAlumnoCarrera");
        panelContenedor.add(formularioTerminacion, "FormularioTerminacion");

        this.instanciarBotones();
    }

    private void instanciarBotones() {
        agregarCarreraButton = new JButton("Alta Carrera");
        agregarAlumnoButton = new JButton("Alta Alumno");
        inscribirAlumnoACarreraButton = new JButton("Inscribir Alumno a Carrera");
        inscribirAlumnoAMateriaButton = new JButton("Inscribir Alumno a Materia");
        verTerminacionDeCarreraButton = new JButton("Ver terminación de Carrera");
        menuPrincipalButton = new JButton("Menú Principal");

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

        verTerminacionDeCarreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioTerminacion.cargarAlumnosComboBox();
                carta.show(panelContenedor, "FormularioTerminacion");
            }
        });

        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
    }

    @Override
    public void volver() {
        vistaPrincipal.cargarCarrerasTable();
        vistaPrincipal.cargarAlumnosTable();
        carta.show(panelContenedor, "VistaPrincipal");
    }

    @Override
    public void run() {

        panelBotones.add(agregarCarreraButton);
        panelBotones.add(agregarAlumnoButton);
        panelBotones.add(inscribirAlumnoACarreraButton);
        panelBotones.add(inscribirAlumnoAMateriaButton);
        panelBotones.add(verTerminacionDeCarreraButton);
        panelBotones.add(menuPrincipalButton);
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

}