package Modelo;

public class Cargador {

    public Cargador(){}

    public Facultad crearFacultadEstatica() {

        Facultad facultad = new Facultad();
        Carrera carrera = new Carrera();
        carrera.setNombre("Lic. en Sistemas");
        Alumno alumno1 = new Alumno("Tomas", "Villanueva");
        Alumno alumno2 = new Alumno("Juan", "Perez");

        facultad.agregarCarrera(carrera);
        facultad.agregarAlumno(alumno1);
        facultad.agregarAlumno(alumno2);

        carrera.agregarAlumno(alumno1);
        carrera.agregarAlumno(alumno2);

        TipoDePlan plana = new PlanA();
        TipoDePlan planb = new PlanB();
        PlanDeEstudio plan = new PlanDeEstudio();
        plan.setPlan(plana);

        Cuatrimestre cuat1 = new Cuatrimestre();
        Cuatrimestre cuat2 = new Cuatrimestre();
        Cuatrimestre cuat3 = new Cuatrimestre();
        Cuatrimestre cuat4 = new Cuatrimestre();
        Cuatrimestre cuat5 = new Cuatrimestre();
        Cuatrimestre cuat6 = new Cuatrimestre();
        Cuatrimestre cuat7 = new Cuatrimestre();
        Cuatrimestre cuat8 = new Cuatrimestre();
        Cuatrimestre cuat9 = new Cuatrimestre();
        Cuatrimestre cuat10 = new Cuatrimestre();

        plan.agregarCuatrimestre(cuat1);
        plan.agregarCuatrimestre(cuat2);
        plan.agregarCuatrimestre(cuat3);
        plan.agregarCuatrimestre(cuat4);
        plan.agregarCuatrimestre(cuat5);
        plan.agregarCuatrimestre(cuat6);
        plan.agregarCuatrimestre(cuat7);
        plan.agregarCuatrimestre(cuat8);
        plan.agregarCuatrimestre(cuat9);
        plan.agregarCuatrimestre(cuat10);

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

        Materia sistemas = new Materia("Sistemas Y Organizaciones", true, true);
        Materia arquitectura = new Materia("Arquitectura de computadoras", true, false);
        Materia algoII = new Materia("Algoritmica y programacion II", true, true);
        Materia estadistica = new Materia("Estadistica", true, true);

        cuat3.agregarMateria(sistemas);
        cuat3.agregarMateria(arquitectura);
        cuat3.agregarMateria(algoII);
        cuat3.agregarMateria(estadistica);

        try {
            algoritmica.agregarCorrelativa(algoritmos);
            analisis.agregarCorrelativa(algebra);
            logica.agregarCorrelativa(elementos);
            arquitectura.agregarCorrelativa(elementos);
            algoII.agregarCorrelativa(algoritmica);
            algoII.agregarCorrelativa(logica);
            estadistica.agregarCorrelativa(algebra);
            estadistica.agregarCorrelativa(analisis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Cursada cursada = new Cursada(algebra);
        Cursada cursada2 = new Cursada(elementos);
        Cursada cursada3 = new Cursada(algoritmos);
        Cursada cursada4 = new Cursada(algoritmica);
        Cursada cursada5 = new Cursada(analisis);
        Cursada cursada6 = new Cursada(logica);

        alumno1.inscribirEn(cursada);
        cursada.setNotaParcial(4);
        //cursada.setNotaFinal(4);
        cursada.aprobarCursada();

        alumno1.inscribirEn(cursada2);
        cursada2.setNotaParcial(4);
        cursada2.aprobarCursada();

        alumno1.inscribirEn(cursada3);
        cursada3.setNotaFinal(7);
        cursada3.aprobarMateria();

        carrera.setPlan(plan);

        System.out.println(carrera);

        System.out.println("Materias: " + plan.verMaterias(alumno1));
        return facultad;
    }

    public static void main(String[] args) {
        Facultad facultad = new Cargador().crearFacultadEstatica();
    }
}
