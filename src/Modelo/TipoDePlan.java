package Modelo;

import java.util.LinkedList;
import java.util.TreeMap;

public interface TipoDePlan {
    public LinkedList<Materia> verMaterias(Alumno alumno, TreeMap<Integer,Cuatrimestre> cuatrimestres);
}
