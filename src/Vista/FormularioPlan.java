package Vista;

import Modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;
import java.util.SortedMap;

public class FormularioPlan extends JPanel {

    //Paneles
    private JPanel panelCarta;
    private JPanel panelCuatrimestre;
    private JPanel panelMateria;

    //Labels
    private JLabel materiaLabel;
    private JTextField materiaField;
    private JLabel tipoLabel;
    private JLabel correlativaLabel;

    //ComboBoxs
    private JComboBox<Materia> correlativaComboBox;
    DefaultComboBoxModel<Materia> model;
    private JComboBox<String> tipoComboBox;
    private JRadioButton obligatoriaButton;
    private JRadioButton optativaButton;
    private JRadioButton promocionableButton;
    private JRadioButton noPromocionableButton;

    //Botones
    private JButton masMateriaButton;
    private JButton aceptarMateriaButton;
    private JButton correlativaButton;
    private JButton cuatrimestreButton;
    private JButton aceptarButton;

    //Tablas
    private JTable correlativaTable;
    private JTable cuatrimestreTable;
    private JTable materiaTable;
    private DefaultTableModel cuatrimestreTableModel;
    private DefaultTableModel correlativaTableModel;
    private DefaultTableModel materiaTableModel;

    //Contenedores
    private LinkedList<Cuatrimestre> cuatrimestres;
    private LinkedList<Materia> correlativasActual;
    private PlanDeEstudio plan;
    private Materia materiaActual;
    private Cuatrimestre cuatrimestreActual;
    CardLayout carta;

    public FormularioPlan() {
        plan = new PlanDeEstudio();
        //Inicializar panel principal
        JPanel panel = this;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        initComponents();

        //Inicializar panel contenedor de cartas
        panelCarta = new JPanel();
        carta = new CardLayout();
        panelCarta.setLayout(carta);

        inicializarPanelCuatrimestre();
        inicializarPanelMateria();

        //Inicializar tablas
        crearColumnasCuatrimestreTable();
        crearColumnasCorrelativaTable();
        crearColumnasMateriaTable();

        panelCarta.add(panelCuatrimestre, "PanelCuatrimestre");
        panelCarta.add(panelMateria, "PanelMateria");
        this.setComponents();
    }

    private void setTamanoColumnas(JTable table) {
        int filasVisibles = 10;
        int cols = table.getColumnModel().getTotalColumnWidth();
        int rows = table.getRowHeight() * filasVisibles;
        Dimension d = new Dimension( cols, rows );
        table.setPreferredScrollableViewportSize( d );
    }

    private void crearColumnasCuatrimestreTable() {
        cuatrimestreTableModel = (DefaultTableModel) cuatrimestreTable.getModel();

        cuatrimestreTableModel.addColumn("Cuatrimestre");
        cuatrimestreTableModel.addColumn("Materias");
        setTamanoColumnas(cuatrimestreTable);
    }

    private void cleanUpTable(DefaultTableModel model) {
        //int rows = model.getRowCount();
        //for (int i = rows; i >= 0 ; i--) {
        //    model.removeRow(i);
        //}
        model.setRowCount(0);
    }

    private void popularCuatrimestreTable(Integer num, LinkedList<Materia> materias) {
        String[] rowData = {num.toString(), materias.toString()};
        cuatrimestreTableModel.addRow(rowData);
    }

    private void crearColumnasMateriaTable() {
        materiaTableModel = (DefaultTableModel) materiaTable.getModel();

        materiaTableModel.addColumn("Materias");
        setTamanoColumnas(materiaTable);
    }

    private void popularMateriaTable(Materia materia) {
        String[] rowData = {materia.toString()};
        materiaTableModel.addRow(rowData);
    }

    private void crearColumnasCorrelativaTable() {
        correlativaTableModel = (DefaultTableModel) correlativaTable.getModel();

        correlativaTableModel.addColumn("Cuatrimestre");
        correlativaTableModel.addColumn("Materia");
        setTamanoColumnas(correlativaTable);
    }

    private void popularCorrelativaTable(Integer num, Materia materia) {
        String[] rowData = {num.toString(), materia.toString()};
        correlativaTableModel.addRow(rowData);
    }

    private void inicializarPanelCuatrimestre() {
        //Inicializar panel de cuatrimestre
        panelCuatrimestre = new JPanel(/*new BoxLayout(panelCuatrimestre, BoxLayout.LINE_AXIS)*/);
        cuatrimestreButton = new JButton("Agregar cuatrimestre");
        cuatrimestreTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(cuatrimestreTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cuatrimestreTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panelCuatrimestre.setBorder(BorderFactory.createTitledBorder("Agregar Cuatrimestre"));
        panelCuatrimestre.add(cuatrimestreButton);
        panelCuatrimestre.add(scrollPane);

        cuatrimestreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panelCuatrimestre,
                        "¿Seguro que quiere crear cuatrimestre?",
                        "Confirmar creación de cuatrimestre",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                switch (respuesta) {
                    case 0 -> {
                        cuatrimestreActual = new Cuatrimestre();
                        plan.agregarCuatrimestre(cuatrimestreActual);
                        SortedMap<Integer, Cuatrimestre> cuatrisAnteriores = plan.obtenerCuatrimestresAnteriores(cuatrimestreActual.getNumero());
                        model.removeAllElements();
                        for (Integer num : cuatrisAnteriores.keySet()) {
                            model.addAll(cuatrisAnteriores.get(num).getMaterias());
                        }
                        aceptarButton.setEnabled(false);
                        carta.next(panelCarta);
                    }
                    case 1 -> System.out.println("se cancelo creacion de cuatrimestre");
                }
            }
        });
    }

    private void inicializarPanelMateria() {
        panelMateria = new JPanel(/*new BoxLayout(panelMateria, BoxLayout.PAGE_AXIS)*/);
        materiaLabel = new JLabel("Nombre:");
        materiaField = new JTextField(10);
        masMateriaButton = new JButton("Aceptar y Agregar otra materia");
        aceptarMateriaButton = new JButton("Finalizar agregado de materias");
        materiaTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(materiaTable);

        obligatoriaButton = new JRadioButton("Obligatoria");
        obligatoriaButton.setSelected(true);
        obligatoriaButton.setActionCommand("obligatoria");
        optativaButton = new JRadioButton("Optativa");
        optativaButton.setActionCommand("optativa");
        noPromocionableButton = new JRadioButton("no promocionable");
        noPromocionableButton.setSelected(true);
        noPromocionableButton.setActionCommand("no promocionable");
        promocionableButton = new JRadioButton("promocionable");
        promocionableButton.setActionCommand("promocionable");

        ButtonGroup grupo1 = new ButtonGroup();
        grupo1.add(obligatoriaButton);
        grupo1.add(optativaButton);
        ButtonGroup grupo2 = new ButtonGroup();
        grupo2.add(noPromocionableButton);
        grupo2.add(promocionableButton);

        model = new DefaultComboBoxModel<>();
        correlativaComboBox = new JComboBox<>();
        correlativaComboBox.setModel(model);

        correlativaButton = new JButton("+");
        correlativaTable = new JTable();
        JScrollPane scrollPaneCorrelativa = new JScrollPane(correlativaTable);
        correlativaLabel = new JLabel("Correlativa:");
        correlativasActual = new LinkedList<>();
        panelMateria.setBorder(BorderFactory.createTitledBorder("Agregar Materias"));
        panelMateria.add(materiaLabel);
        panelMateria.add(materiaField);
        panelMateria.add(obligatoriaButton);
        panelMateria.add(optativaButton);
        panelMateria.add(noPromocionableButton);
        panelMateria.add(promocionableButton);
        panelMateria.add(correlativaLabel);
        panelMateria.add(correlativaComboBox);
        panelMateria.add(correlativaButton);
        panelMateria.add(scrollPaneCorrelativa);
        panelMateria.add(masMateriaButton);
        panelMateria.add(scrollPane);
        panelMateria.add(aceptarMateriaButton);

        //Crea una materia y la agrega al cuatrimestre actual
        masMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panelCuatrimestre,
                        "¿Crear materia?",
                        "Confirmar creación de materia",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                String opcion1 = grupo1.getSelection().getActionCommand();
                String opcion2 = grupo2.getSelection().getActionCommand();

                boolean obligatoria = Objects.equals(opcion1, "obligatoria");
                boolean promocionable = Objects.equals(opcion2, "promocionable");

                switch (respuesta) {
                    case 0 -> {
                        Materia materia = new Materia(materiaField.getText(), obligatoria, promocionable);
                        materia.setCorrelativas(correlativasActual);
                        cuatrimestreActual.agregarMateria(materia);
                        cleanUpTable(correlativaTableModel);
                        popularMateriaTable(materia);
                    }
                    case 1 -> System.out.println("se cancelo creacion de cuatrimestre");
                }
            }
        });

        //Agregar correlativa a la lista de correlativas, y actualiza la tabla de correlativas
        correlativaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panelCuatrimestre,
                        "¿Agregar correlativa?",
                        "Confirmar creación de correlativa",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                switch (respuesta) {
                    case 0 -> {
                        Materia correlativa = (Materia) correlativaComboBox.getSelectedItem();
                        if (correlativa != null && !correlativasActual.contains(correlativa)) {
                            correlativasActual.add(correlativa);
                            popularCorrelativaTable(correlativa.getNumeroCuatrimestre(), correlativa);
                            JOptionPane.showMessageDialog(panelMateria, "Agregada correlativa");
                        } else {
                            JOptionPane.showMessageDialog(panelMateria, "No hay correlativa o ya existe", "error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 1 -> {
                        JOptionPane.showMessageDialog(panelMateria, "cancelar Agregada correlativa");
                        System.out.println("se cancelo creacion de correlativa");
                    }
                }
            }
        });

        //Actualiza la vista finalizar creacion de materias
        aceptarMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panelCuatrimestre,
                        "¿Finalizar creación de materias?",
                        "Confirmar creación de materia",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                switch (respuesta) {
                    case 0:
                        if (cuatrimestreActual.getMaterias().size() != 0) {
                            JOptionPane.showMessageDialog(panelMateria, "Exito en la creacion de cuatrimestre");
                            popularCuatrimestreTable(cuatrimestreActual.getNumero(), cuatrimestreActual.getMaterias());
                            carta.previous(panelCarta);
                            aceptarButton.setEnabled(true);
                            cleanUpTable(materiaTableModel);
                            cleanUpTable(correlativaTableModel);
                            correlativasActual.clear();
                        } else {
                            JOptionPane.showMessageDialog(panelMateria, "No hay materias para el cuatrimestre");
                        }
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panelMateria, "Cancelar agregar materias");
                        carta.previous(panelCarta);
                        System.out.println("se cancelo creacion de materias");
                        break;
                }
            }
        });
    }

    private void initComponents() {
        tipoLabel = new JLabel("Tipo de plan:");
        aceptarButton = new JButton("Aceptar");
        String[] opcionesTipo = {"A", "B", "C", "D", "E"};

        tipoComboBox = new JComboBox<>(opcionesTipo);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panelCuatrimestre,
                        "¿Finalizar creación de carrera?",
                        "Confirmar creación de carrera",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                TipoDePlan tipoDePlan;
                String tipo = (String) tipoComboBox.getSelectedItem();
                switch (Objects.requireNonNull(tipo)) {
                    //case "A" -> tipoDePlan = new PlanA();
                    case "B" -> tipoDePlan = new PlanB();
                    case "C" -> tipoDePlan = new PlanC();
                    case "D" -> tipoDePlan = new PlanD();
                    case "E" -> tipoDePlan = new PlanE();
                    default -> tipoDePlan = new PlanA();
                }
                plan.setPlan(tipoDePlan);
                 
                System.out.println(plan);
            }
        });
    }

    private void setComponents() {
        this.add(tipoLabel);
        this.add(tipoComboBox);
        this.add(panelCarta);
        this.add(aceptarButton);
    }

    public static void main(String[] args) {
        JPanel formulario = new FormularioPlan();

        JFrame frame = new JFrame();
        
        frame.setLayout(new FlowLayout());
        frame.setContentPane(formulario);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
