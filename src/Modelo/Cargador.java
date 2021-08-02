package Modelo;

public class Cargador {

    public Cargador(){}

    public Facultad crearFacultadEstatica() {

        Facultad facultad = new Facultad();
        Carrera carrera = new Carrera("Lic. en Sistemas");
        Alumno alumno1 = new Alumno("Tomas", "Villanueva");
        Alumno alumno2 = new Alumno("Juan", "Perez");

        facultad.agregarCarrera(carrera);
        facultad.agregarAlumno(alumno1);
        facultad.agregarAlumno(alumno2);

        carrera.agregarAlumno(alumno1);
        carrera.agregarAlumno(alumno2);

        TipoDePlan plana = new PlanA();
        PlanDeEstudio plan = new PlanDeEstudio();

        Cuatrimestre cuat1 = new Cuatrimestre();
        Cuatrimestre cuat2 = new Cuatrimestre();

        plan.agregarCuatrimestre(cuat1);
        plan.agregarCuatrimestre(cuat2);

        Materia algebra = new Materia("algebra", true, false);
        Materia elementos = new Materia("Elementos de informatica", true, true);
        Materia algoritmos = new Materia("Expresion de problemas y algoritmos", true, true);

        cuat1.agregarMateria(algebra);
        cuat1.agregarMateria(elementos);
        cuat1.agregarMateria(algoritmos);

        Materia algoritmica = new Materia("Algoritmica y programacion I", true, true);
        Materia analisis = new Materia("Analisis matematico", true, true);
        Materia logica = new Materia("Elementos de logica y matematica discreta", true, false);

        cuat2.agregarMateria(algoritmica);
        cuat2.agregarMateria(analisis);
        cuat2.agregarMateria(logica);

        try {
            algoritmica.agregarCorrelativa(algoritmos);
            analisis.agregarCorrelativa(algebra);
            logica.agregarCorrelativa(elementos);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        carrera.setPlan(plan);

        System.out.println(carrera);
        return facultad;
    }
}
