package Modelo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

/* Plan B: aprobó los finales de las correlativas */
public class PlanB extends TipoDePlan {

    @Override
    public LinkedList<Materia> verMaterias(Alumno alumno, PlanDeEstudio plan) {
        LinkedList<Materia> aprobadas = alumno.getMateriasAprobadas();
        LinkedList<Materia> noCursables = alumno.getMateriasCursadaAprobada();
        noCursables.addAll(aprobadas);
        LinkedList<Materia> materias = plan.getMaterias();
        LinkedList<Materia> correlativas;
        LinkedList<Materia> posiblesMaterias = new LinkedList<>();

        for (Materia materia : materias) {

            //si la materia no está aprobada
            if(!(noCursables.contains(materia))) {
                correlativas = materia.getCorrelativas();

                //Si la materia no tiene correlativas se puede cursar
                if(correlativas.size() == 0) {
                    posiblesMaterias.add(materia);
                } else {

                    Set<Materia> interseccion = getInterseccion(correlativas, aprobadas);

                    if(interseccion.containsAll(correlativas)) {
                        posiblesMaterias.add(materia);
                    }
                }
            }
        }

        return  posiblesMaterias;
    }
}
