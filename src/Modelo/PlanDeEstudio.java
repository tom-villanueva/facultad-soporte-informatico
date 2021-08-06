package Modelo;

import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class PlanDeEstudio {

    private TreeMap<Integer, Cuatrimestre> cuatrimestres;
    private Integer cantidadCuatrimestres;
    private TipoDePlan plan;

    public PlanDeEstudio() {
        //this.plan = plan;
        this.cantidadCuatrimestres = 1;
        this.cuatrimestres = new TreeMap<>();
    }

    /*
    * Ver las materias posibles para un alumno según el plan
    */
    public LinkedList<Materia> verMaterias(Alumno alumno) {
        return this.plan.verMaterias(alumno, this);
    }

    /*
    * Agrega un cuatrimestre y le asigna un número manejado internamente
    * */
    public void agregarCuatrimestre(Cuatrimestre cuatrimestre) {
        cuatrimestre.setNumero(this.cantidadCuatrimestres);
        this.cuatrimestres.put(this.cantidadCuatrimestres, cuatrimestre);
        this.cantidadCuatrimestres += 1;
    }

    /*
    * Obtiene los cuatrimestres anteriores a un número de cuatrimestre dado
    * */
    public SortedMap<Integer, Cuatrimestre> obtenerCuatrimestresAnteriores(Integer numeroCuatrimestre) {
        return this.cuatrimestres.headMap(numeroCuatrimestre);
    }

    /*
    * Obtiene los cuatrimestres dado un rango numérico
    * */
    public SortedMap<Integer, Cuatrimestre> obtenerCuatrimestresRango(Integer numeroCuatrimestreDesde, Integer numeroCuatrimestreHasta) {
        return this.cuatrimestres.subMap(numeroCuatrimestreDesde, numeroCuatrimestreHasta);
    }

    /*
    * Obtiene todas las materias de todos los cuatrimestres
    * */
    public LinkedList<Materia> getMaterias() {
        Cuatrimestre cuatri;
        LinkedList<Materia> materias = new LinkedList<>();

        for (Map.Entry<Integer, Cuatrimestre> entry : cuatrimestres.entrySet()) {
            cuatri = entry.getValue();
            materias.addAll(cuatri.getMaterias());
        }

        return materias;
    }

    /*
    * Calcula la cantidad de optativas
    * */
    public int calcularCantidadOptativas() {
        Cuatrimestre cuatri;
        int total = 0;

        for (Map.Entry<Integer, Cuatrimestre> entry : cuatrimestres.entrySet()) {
            cuatri = entry.getValue();
            total += cuatri.getCantidadOptativas();
        }

        return total;
    }

    @Override
    public String toString() {
        return "PlanDeEstudio{" +
                "cuatrimestres=" + cuatrimestres +
                ", cantidadCuatrimestres=" + (cantidadCuatrimestres-1) +
                ", plan=" + plan +
                '}';
    }

    //getters y setters
    public TipoDePlan getPlan() {
        return plan;
    }

    public TreeMap<Integer, Cuatrimestre> getCuatrimestres() {
        return cuatrimestres;
    }

    public void setPlan(TipoDePlan plan) {
        this.plan = plan;
    }
}
