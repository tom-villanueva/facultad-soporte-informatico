package Modelo;

import java.util.LinkedList;
import java.util.TreeMap;

public class PlanDeEstudio {

    private TreeMap<Integer, Cuatrimestre> cuatrimestres;
    private TipoDePlan plan;

    public PlanDeEstudio(TipoDePlan plan) {
        this.plan = plan;
    }

    public LinkedList<Materia> verMaterias(Alumno alumno) {
        return this.plan.verMaterias(alumno, this.cuatrimestres);
    }

    //getters y setters
    public TipoDePlan getPlan() {
        return plan;
    }

    public void setPlan(TipoDePlan plan) {
        this.plan = plan;
    }
}
