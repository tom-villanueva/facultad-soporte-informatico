package Modelo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class TipoDePlan {

    /*
     * Construye un conjunto interseccion copiado de las correlativas
     * Luego lo intersecta con el conjunto de materias aprobadas para
     * ver si la interseccion efectivamente las contiene.
     * */
    public Set<Materia> getInterseccion(LinkedList<Materia> l1, LinkedList<Materia> l2) {
        Set<Materia> interseccion = new HashSet<>(l1);
        Set<Materia> setCursadas = new HashSet<>(l2);

        interseccion.retainAll(setCursadas);
        return interseccion;
    }


    public LinkedList<Materia> verMaterias(Alumno alumno, PlanDeEstudio plan){
        return null;
    };
}
