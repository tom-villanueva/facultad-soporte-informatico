import Modelo.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Supplier;

public class Cargador {

    public Cargador(){}

    public Carrera cargarMateriasDeArchivo(String nombreCarrera, String tipoPlan, String fileName){
        Carrera carrera = new Carrera();
        carrera.setNombre(nombreCarrera);
        TipoDePlan planX;

        switch (tipoPlan) {
            case "B" -> planX = new PlanB();
            case "C" -> planX = new PlanC();
            case "D" -> planX = new PlanD();
            case "E" -> planX = new PlanE();
            default -> planX = new PlanA();
        }
        PlanDeEstudio plan = new PlanDeEstudio();
        plan.setPlan(planX);
        carrera.setPlan(plan);
        carrera.setCantidadOptativas(0);

        try (
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(fileName))))) {
            LinkedList<Materia> materias = new LinkedList<>();
            Cuatrimestre cuatri =  new Cuatrimestre();
            plan.agregarCuatrimestre(cuatri);

            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                int num = Integer.parseInt(values[0]);
                String nombre = values[1];
                boolean obligatoria = Boolean.parseBoolean(values[2]);
                boolean promocionable = Boolean.parseBoolean(values[3]);

                if(num != cuatri.getNumero()) {
                    cuatri = new Cuatrimestre();
                    plan.agregarCuatrimestre(cuatri);
                }

                Materia materia = new Materia(nombre, obligatoria, promocionable);
                cuatri.agregarMateria(materia);
                materias.add(materia);

                if(!Objects.equals(values[4], "")) {
                    String[] correlativas = values[4].split(";");
                    for(String correlativa : correlativas) {
                        for(Materia materiaC : materias) {
                            //System.out.println("materiaC: "+materiaC+" +Correlativa "+correlativa + " " +materiaC.getNombre().equals(correlativa));
                            if(materiaC.getNombre().equals(correlativa)){
                                materia.agregarCorrelativa(materiaC);
                                //System.out.println("materia: "+materia+" +Correlativa "+materiaC);
                            }
                        }
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carrera;
    }

    public Alumno crearAlumno(Carrera carrera, String nombreAl, String apellidoAl, String fileName) {
        Alumno alumno = new Alumno(nombreAl, apellidoAl);
        LinkedList<Materia> materias = carrera.getPlan().getMaterias();

        Cursada cursada;

        carrera.agregarAlumno(alumno);
        alumno.setCarrera(carrera);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(fileName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                for(Materia materia : materias) {
                    if(materia.getNombre().equals(values[0])){
                        cursada = new Cursada(materia);
                        alumno.inscribirEn(cursada);

                        int notaCursada = Integer.parseInt(values[1]);
                        int notaFinal = Integer.parseInt(values[2]);

                        cursada.setNotaParcial(notaCursada);
                        cursada.setNotaFinal(notaFinal);

                        if (notaCursada >= 4 && notaFinal == 0) {

                            if(notaCursada >= 7) {
                                cursada.aprobarMateria();
                            } else {
                                cursada.aprobarCursada();
                            }

                        } else if(notaFinal >= 4) {
                            cursada.aprobarMateria();
                        } else {
                            cursada.desaprobarMateria();
                        }

                    }
                }
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return alumno;
    }

}
