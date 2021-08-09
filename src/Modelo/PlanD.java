package Modelo;

import java.util.LinkedList;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class PlanD extends TipoDePlan{
    @Override
    public LinkedList<Materia> verMaterias(Alumno alumno, PlanDeEstudio plan) {
        LinkedList<Materia> noCursables = alumno.getMateriasCursadaAprobada();
        noCursables.addAll(alumno.getMateriasAprobadas());
        noCursables.addAll(alumno.getMateriasEnCurso());

        LinkedList<Materia> cursadas = alumno.getMateriasCursadaAprobada();
        cursadas.addAll(alumno.getMateriasAprobadas());

        LinkedList<Materia> materias = plan.getMaterias();
        LinkedList<Materia> correlativas;

        LinkedList<Materia> sinCorrelativas = new LinkedList<>();
        LinkedList<Materia> posiblesMaterias = new LinkedList<>();

        for (Materia materia : materias) {

            //si la materia no está aprobada
            if(!(noCursables.contains(materia))) {
                correlativas = materia.getCorrelativas();

                //Si la materia no tiene correlativas las agrego para luego verificar
                if(correlativas.size() == 0) {
                    sinCorrelativas.add(materia);
                } else {
                    /*
                     * (..."aprobó las cursadas de las correlativas")
                     * */
                    Set<Materia> interseccion = getInterseccion(correlativas, cursadas);

                    if(interseccion.containsAll(correlativas)) {
                        posiblesMaterias.add(materia);
                    }
                }
            }
        }

        /*
         * Para cada materia que no tiene correlativas
         * se tienen que haber aprobado los finales de TODAS las materias de 3
         * cuatrimestres previos.
         **/
        cursadas = alumno.getMateriasAprobadas();
        for(Materia materia : sinCorrelativas) {

            int desde = materia.getNumeroCuatrimestre() - 3;
            int hasta = materia.getNumeroCuatrimestre();

            if(desde < 0) {
                desde = 1;
            }

            SortedMap<Integer, Cuatrimestre> cuatrisAnteriores = plan.obtenerCuatrimestresRango(desde, hasta, false);
            LinkedList<Materia> materiasAnteriores = getMateriasCuatrimestres(cuatrisAnteriores);

            Set<Materia> interseccion = getInterseccion(materiasAnteriores, cursadas);

            if(interseccion.containsAll(materiasAnteriores)) {
                posiblesMaterias.add(materia);
            }
        }

        return posiblesMaterias;
    }

    private LinkedList<Materia> getMateriasCuatrimestres(SortedMap<Integer, Cuatrimestre> cuatrisAnteriores) {
        LinkedList<Materia> materias = new LinkedList<>();

        for (Integer num : cuatrisAnteriores.keySet()) {
            materias.addAll(cuatrisAnteriores.get(num).getMaterias());
        }

        return materias;
    }

    @Override
    public String toString() {
        return "D";
    }
}
