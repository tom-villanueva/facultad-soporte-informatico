package Modelo;

import java.util.LinkedList;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class PlanE extends TipoDePlan{
    @Override
    public LinkedList<Materia> verMaterias(Alumno alumno, PlanDeEstudio plan) {
        LinkedList<Materia> cursadas = alumno.getMateriasCursadaAprobada();
        cursadas.addAll(alumno.getMateriasAprobadas());
        cursadas.addAll(alumno.getMateriasEnCurso());

        LinkedList<Materia> materias = plan.getMaterias();
        LinkedList<Materia> correlativas;

        LinkedList<Materia> sinCorrelativas = new LinkedList<>();
        LinkedList<Materia> posiblesMaterias = new LinkedList<>();

        for (Materia materia : materias) {

            //si la materia no está aprobada
            if(!(cursadas.contains(materia))) {
                correlativas = materia.getCorrelativas();

                //Si la materia no tiene correlativas las agrego para luego verificar
                if(correlativas.size() == 0) {
                    sinCorrelativas.add(materia);
                } else {
                    /*
                     * (..."aprobó los finales de las correlativas")
                     * */
                    Set<Materia> interseccion = getInterseccion(correlativas, alumno.getMateriasAprobadas());

                    if(interseccion.containsAll(correlativas)) {
                        posiblesMaterias.add(materia);
                    }
                }
            }
        }

        /*
         * Para cada materia que no tiene correlativas
         * se tienen que haber aprobado los finales de TODAS las materias de 3
         * cuatrimestres previos cualesquiera.
         **/
        cursadas = alumno.getMateriasAprobadas();
        for(Materia materia : sinCorrelativas) {

            if(materia.getNumeroCuatrimestre() == 1) {
                posiblesMaterias.add(materia);
            } else {
                int desde = 1;
                int hasta = materia.getNumeroCuatrimestre();
                int contador = 0;

                SortedMap<Integer, Cuatrimestre> cuatrisAnteriores = plan.obtenerCuatrimestresRango(desde, hasta, true);

                for (int i = 0; i < hasta; i++) {

                    LinkedList<Materia> materiasAnteriores = cuatrisAnteriores.get(i+1).getMaterias();
                    Set<Materia> interseccion = getInterseccion(materiasAnteriores, cursadas);

                    if(interseccion.containsAll(materiasAnteriores)) {
                        contador += 1;
                    }
                }

                //si cumple que tres cuatrimestres previos estan completos con finales.
                if(contador == 3) {
                    posiblesMaterias.add(materia);
                }
            }
        }

        return posiblesMaterias;
    }

    @Override
    public String toString() {
        return "E";
    }
}
