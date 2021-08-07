package Modelo;

import java.util.*;
/* Plan A: aprobó las cursadas de las correlativas */
public class PlanA extends TipoDePlan{
    @Override
    public LinkedList<Materia> verMaterias(Alumno alumno, PlanDeEstudio plan) {
        // Las materias que aprobó las cursadas, y las que aprobó
        LinkedList<Materia> cursadas = alumno.getMateriasCursadaAprobada();
        cursadas.addAll(alumno.getMateriasAprobadas());

        LinkedList<Materia> materias = plan.getMaterias();
        LinkedList<Materia> correlativas;
        LinkedList<Materia> posiblesMaterias = new LinkedList<>();

        for (Materia materia : materias) {

            //si la materia no está aprobada/cursada aprobada
            if(!(cursadas.contains(materia))) {
                correlativas = materia.getCorrelativas();

                //Si la materia no tiene correlativas se puede cursar
                if(correlativas.size() == 0) {
                    posiblesMaterias.add(materia);
                } else {

                    Set<Materia> interseccion = getInterseccion(correlativas, cursadas);

                    if(interseccion.containsAll(correlativas)) {
                        posiblesMaterias.add(materia);
                    }
                }
            }
        }

        return  posiblesMaterias;
    }

    @Override
    public String toString() {
        return "A";
    }
}
