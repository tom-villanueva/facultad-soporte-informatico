package Modelo;

import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

public class PlanDeEstudio {

    private TreeMap<Integer, Cuatrimestre> cuatrimestres;
    private Integer cantidadCuatrimestres;
    private TipoDePlan plan;

    public PlanDeEstudio() {
        //this.plan = plan;
        this.cantidadCuatrimestres = 1;
        this.cuatrimestres = new TreeMap<Integer, Cuatrimestre>();
    }

    // Ver las materias posibles para un alumno según el plan
    public LinkedList<Materia> verMaterias(Alumno alumno) {
        return this.plan.verMaterias(alumno, this.cuatrimestres);
    }

    public void agregarCuatrimestre(Cuatrimestre cuatrimestre) {
        cuatrimestre.setNumero(this.cantidadCuatrimestres);
        this.cuatrimestres.put(this.cantidadCuatrimestres, cuatrimestre);
        this.cantidadCuatrimestres += 1;
    }

    public SortedMap<Integer, Cuatrimestre> obtenerCuatrimestresAnteriores(Integer numeroCuatrimestre) {
        return this.cuatrimestres.headMap(numeroCuatrimestre);
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

    public void setPlan(TipoDePlan plan) {
        this.plan = plan;
    }
}
