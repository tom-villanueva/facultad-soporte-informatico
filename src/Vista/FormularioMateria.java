package Vista;

import Modelo.Cuatrimestre;
import Modelo.Materia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;
import java.util.SortedMap;

public class FormularioMateria extends JPanel {

    private JPanel panel;
    private JPanel panelFormulario;
    private JPanel panelCorrelativa;
    private JPanel panelBotones;

    //Campos
    private JLabel materiaLabel;
    private JTextField materiaField;
    private JLabel correlativaLabel;

    //Radio buttons
    private JComboBox<Materia> correlativaComboBox;
    DefaultComboBoxModel<Materia> model;
    private JComboBox<String> tipoComboBox;
    private JRadioButton obligatoriaButton;
    private JRadioButton optativaButton;
    private JRadioButton promocionableButton;
    private JRadioButton noPromocionableButton;
    private ButtonGroup grupo1;
    private ButtonGroup grupo2;

    //Botones
    private JButton masMateriaButton;
    private JButton aceptarMateriaButton;
    private JButton correlativaButton;

    //Tablas
    private JTable materiaTable;
    private JTable correlativaTable;
    private DefaultTableModel materiaTableModel;
    private DefaultTableModel correlativaTableModel;

    //Contenedores
    private LinkedList<Materia> correlativasActual;
    private Cuatrimestre cuatrimestreActual;
    private ProgressListener listener;

    public FormularioMateria(ProgressListener listener) {
        this.listener = listener;
        panel = this;
        panel.setLayout(new BorderLayout());

        inicializarPanelMateria();
        crearColumnasCorrelativaTable();
        crearColumnasMateriaTable();


    }

    public void setCuatrimestreActual(Cuatrimestre cuatrimestreActual) {
        this.cuatrimestreActual = cuatrimestreActual;
    }

    private void setTamanoColumnas(JTable table) {
        int filasVisibles = 10;
        int cols = table.getColumnModel().getTotalColumnWidth();
        int rows = table.getRowHeight() * filasVisibles;
        Dimension d = new Dimension( cols, rows );
        table.setPreferredScrollableViewportSize( d );
    }

    private void cleanUpTable(DefaultTableModel model) {
        model.setRowCount(0);
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

    public void reiniciarModeloCorrelativas(SortedMap<Integer, Cuatrimestre> cuatrisAnteriores) {
        model.removeAllElements();
        for (Integer num : cuatrisAnteriores.keySet()) {
            model.addAll(cuatrisAnteriores.get(num).getMaterias());
        }
    }

    //Inicializa los componentes del panel de correlativas
    private void inicializarPanelCorrelativa() {
        panelCorrelativa = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel();
        panelCorrelativa.setBorder(BorderFactory.createTitledBorder("Correlativas"));

        //settear correlativa combobox
        model = new DefaultComboBoxModel<>();
        correlativaComboBox = new JComboBox<>();
        correlativaComboBox.setModel(model);

        //settear componentes relacionados a las correlativas
        correlativaButton = new JButton("+");
        correlativaTable = new JTable();
        JScrollPane scrollPaneCorrelativa = new JScrollPane(correlativaTable);
        correlativaLabel = new JLabel("Correlativa:");
        correlativasActual = new LinkedList<>();

        panelFormulario.add(correlativaLabel);
        panelFormulario.add(correlativaComboBox);
        panelFormulario.add(correlativaButton);
        panelCorrelativa.add(panelFormulario, BorderLayout.PAGE_START);
        panelCorrelativa.add(scrollPaneCorrelativa, BorderLayout.CENTER);
    }

    //Inicializa los componentes del panel de datos de la materia
    private void inicializarPanelFormulario() {
        panelFormulario = new JPanel(new BorderLayout());
        JPanel panelNombre = new JPanel();
        JPanel panelBotones = new JPanel();
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos"));

        materiaLabel = new JLabel("Nombre:");
        materiaField = new JTextField(10);

        //setear radio buttons
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

        //settear button group
        grupo1 = new ButtonGroup();
        grupo1.add(obligatoriaButton);
        grupo1.add(optativaButton);
        grupo2 = new ButtonGroup();
        grupo2.add(noPromocionableButton);
        grupo2.add(promocionableButton);

        panelNombre.add(materiaLabel);
        panelNombre.add(materiaField);
        panelBotones.add(obligatoriaButton);
        panelBotones.add(optativaButton);
        panelBotones.add(noPromocionableButton);
        panelBotones.add(promocionableButton);

        panelFormulario.add(panelNombre, BorderLayout.PAGE_START);
        panelFormulario.add(panelBotones, BorderLayout.CENTER);
    }

    private void inicializarPanelBotones() {
        panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBorder(BorderFactory.createTitledBorder("Botones"));

        masMateriaButton = new JButton("Aceptar y Agregar otra materia");
        aceptarMateriaButton = new JButton("Finalizar agregado de materias");
        materiaTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(materiaTable);

        panelBotones.add(masMateriaButton, BorderLayout.LINE_START);
        panelBotones.add(scrollPane, BorderLayout.CENTER);
        panelBotones.add(aceptarMateriaButton, BorderLayout.PAGE_END);
    }

    private void inicializarPanelMateria() {

        inicializarPanelCorrelativa();
        inicializarPanelFormulario();
        inicializarPanelBotones();

        panel.setBorder(BorderFactory.createTitledBorder("Agregar Materias"));

        panel.add(panelFormulario, BorderLayout.LINE_START);
        panel.add(panelCorrelativa, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.PAGE_END);

        //Crea una materia y la agrega al cuatrimestre actual
        masMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panel,
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
                        if(!(Objects.equals(materiaField.getText(), ""))) {
                            Materia materia = new Materia(materiaField.getText(), obligatoria, promocionable);
                            materia.setCorrelativas(correlativasActual);
                            cuatrimestreActual.agregarMateria(materia);
                            //clean up del formulario
                            cleanUpTable(correlativaTableModel);
                            materiaField.setText("");
                            obligatoriaButton.setSelected(true);
                            noPromocionableButton.setSelected(true);
                            popularMateriaTable(materia);
                            correlativaComboBox.setSelectedIndex(0);
                            JOptionPane.showMessageDialog(panel, "Materia agregada");
                        } else {
                            JOptionPane.showMessageDialog(panel, "Materia no tiene nombre");
                        }
                    }
                    case 1 -> {
                        System.out.println("se cancelo creacion de materia");
                        JOptionPane.showMessageDialog(panel, "No se agrega materia");
                    }
                }
            }
        });

        //Agregar correlativa a la lista de correlativas, y actualiza la tabla de correlativas
        correlativaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panel,
                        "¿Agregar correlativa?",
                        "Confirmar creación de correlativa",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                switch (respuesta) {
                    case 0 -> {
                        Materia correlativa = (Materia) correlativaComboBox.getSelectedItem();
                        System.out.println(correlativa);
                        System.out.println(correlativasActual.contains(correlativa));
                        if (correlativa != null && !correlativasActual.contains(correlativa)) {
                            correlativasActual.add(correlativa);
                            popularCorrelativaTable(correlativa.getNumeroCuatrimestre(), correlativa);
                            JOptionPane.showMessageDialog(panel, "Agregada correlativa");
                        } else {
                            JOptionPane.showMessageDialog(panel, "No hay correlativa o ya existe", "error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 1 -> {
                        JOptionPane.showMessageDialog(panel, "cancelar Agregada correlativa");
                        System.out.println("se cancelo creacion de correlativa");
                    }
                }
            }
        });

        //Actualiza la vista finalizar creacion de materias
        aceptarMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panel,
                        "¿Finalizar creación de materias?",
                        "Confirmar creación de materia",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                switch (respuesta) {
                    case 0:
                        if (cuatrimestreActual.getMaterias().size() != 0) {
                            JOptionPane.showMessageDialog(panel, "Exito en la creacion de cuatrimestre");
                            cleanUpTable(materiaTableModel);
                            cleanUpTable(correlativaTableModel);
                            correlativasActual.clear();
                            System.out.println(cuatrimestreActual);
                            listener.volver();
                        } else {
                            JOptionPane.showMessageDialog(panel, "No hay materias para el cuatrimestre");
                        }
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panel, "Cancelar agregar materias");
                        //carta.previous(panelCarta);
                        cleanUpTable(materiaTableModel);
                        cleanUpTable(correlativaTableModel);
                        correlativasActual.clear();
                        listener.volver();
                        System.out.println("se cancelo creacion de materias");
                        break;
                }
            }
        });
    }

}
