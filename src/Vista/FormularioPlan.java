package Vista;

import Modelo.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;
import java.util.SortedMap;

public class FormularioPlan extends JPanel implements ProgressListener{

    //Paneles
    private JPanel panelCarta;
    private JPanel panelCuatrimestre;
    private FormularioMateria panelMateria;
    private JPanel panel;

    //Labels
    private JLabel tipoLabel;

    //ComboBoxs
    private JComboBox<String> tipoComboBox;

    //Botones
    private JButton cuatrimestreButton;
    private JButton aceptarButton;

    //Tablas
    private JTable cuatrimestreTable;
    private DefaultTableModel cuatrimestreTableModel;

    //Contenedores
    private PlanDeEstudio plan;
    private Cuatrimestre cuatrimestreActual;
    private CardLayout carta;
    private Carrera carrera;
    private ProgressListener listener;

    public FormularioPlan(ProgressListener listener) {
        this.listener = listener;
        //Inicializar panel principal
        this.panel = this;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        initComponents();

        //Inicializar panel contenedor de cartas
        panelCarta = new JPanel();
        carta = new CardLayout();
        panelCarta.setLayout(carta);

        inicializarPanelCuatrimestre();
        panelMateria = new FormularioMateria(this);

        crearColumnasCuatrimestreTable();

        panelCarta.add(panelCuatrimestre, "PanelCuatrimestre");
        panelCarta.add(panelMateria, "PanelMateria");

        this.setComponents();
    }

    public void setPlan(PlanDeEstudio plan) {
        this.plan = plan;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    private void crearColumnasCuatrimestreTable() {
        cuatrimestreTableModel = (DefaultTableModel) cuatrimestreTable.getModel();
        cuatrimestreTableModel.addColumn("Cuatrimestre");
        cuatrimestreTableModel.addColumn("Materias");
    }

    private void cleanUpTable(DefaultTableModel model) {
        model.setRowCount(0);
    }

    private void popularCuatrimestreTable(Integer num, LinkedList<Materia> materias) {
        String[] rowData = {num.toString(), materias.toString()};
        cuatrimestreTableModel.addRow(rowData);
    }

    private void inicializarPanelCuatrimestre() {
        //Inicializar panel de cuatrimestre
        panelCuatrimestre = new JPanel(new BorderLayout());
        cuatrimestreButton = new JButton("Agregar cuatrimestre");
        cuatrimestreTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(cuatrimestreTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //cuatrimestreTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panelCuatrimestre.setBorder(BorderFactory.createTitledBorder("Agregar Cuatrimestre"));
        panelCuatrimestre.add(cuatrimestreButton, BorderLayout.LINE_START);
        panelCuatrimestre.add(scrollPane, BorderLayout.CENTER);

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
                        panelMateria.setCuatrimestreActual(cuatrimestreActual);
                        plan.agregarCuatrimestre(cuatrimestreActual);
                        SortedMap<Integer, Cuatrimestre> cuatrisAnteriores = plan.obtenerCuatrimestresAnteriores(cuatrimestreActual.getNumero());
                        panelMateria.reiniciarModeloCorrelativas(cuatrisAnteriores);
                        aceptarButton.setEnabled(false);
                        carta.next(panelCarta);
                    }
                    case 1 -> System.out.println("se cancelo creacion de cuatrimestre");
                }
            }
        });
    }

    private void initComponents() {
        tipoLabel = new JLabel("Tipo de plan:");
        aceptarButton = new JButton("Aceptar Plan de estudios");
        String[] opcionesTipo = {"A", "B", "C", "D", "E"};

        tipoComboBox = new JComboBox<>(opcionesTipo);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(panelCuatrimestre,
                        "¿Finalizar creación de plan?",
                        "Confirmar creación de plan",
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

                switch (respuesta) {
                    case 0 -> {
                        plan.setPlan(tipoDePlan);
                        carrera.setPlan(plan);
                        JOptionPane.showMessageDialog(panelMateria, "Exito en la creacion de plan de estudio");
                        cleanUpTable(cuatrimestreTableModel);
                        panel.setVisible(false);
                        listener.volver();
                    }
                    case 1 -> JOptionPane.showMessageDialog(panelMateria, "Cancelar creacion de plan de estudio");
                }
                System.out.println(carrera);
            }
        });
    }

    private void setComponents() {
        this.add(tipoLabel);
        this.add(tipoComboBox);
        this.add(panelCarta);
        this.add(aceptarButton);
    }

    @Override
    public void volver() {
        popularCuatrimestreTable(cuatrimestreActual.getNumero(), cuatrimestreActual.getMaterias());
        carta.previous(panelCarta);
        aceptarButton.setEnabled(true);
    }
}
