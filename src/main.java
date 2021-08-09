import Modelo.*;
import Vista.MenuPrincipal;

public class main {
    public static void main(String[] args) {

        Cargador cargador = new Cargador();
        Carrera sistemas = cargador.cargarMateriasDeArchivo("Analista Universitario en Sistemas", "A", "/sistemas.csv");
        Carrera turismo = cargador.cargarMateriasDeArchivo("Técnico Universitario Contable", "B", "/turismo.csv");
        Carrera sistemasC = cargador.cargarMateriasDeArchivo("Analista Universitario en Sistemas (C)", "C", "/sistemas.csv");
        Carrera sistemasD = cargador.cargarMateriasDeArchivo("Analista Universitario en Sistemas (D)", "D", "/sistemas.csv");
        Carrera turismoE = cargador.cargarMateriasDeArchivo("Técnico Universitario Contable (E)", "E", "/turismo.csv");

        Alumno alumno1 = cargador.crearAlumno(sistemas, "Tomás", "Villanueva", "/alumno1.csv");
        Alumno alumno2 = cargador.crearAlumno(sistemas, "Juan", "Perez", "/alumno2.csv");
        Alumno alumno3 = cargador.crearAlumno(sistemas, "Roberto", "Carlos", "/alumno3.csv");

        Alumno alumno4 = cargador.crearAlumno(turismo, "Alejandra", "Perez", "/alumno4.csv");
        Alumno alumno5 = cargador.crearAlumno(turismo, "Juan", "Carlos", "/alumno5.csv");

        Alumno alumno6 = cargador.crearAlumno(sistemasC, "Tomás", "Villanueva C", "/alumno1.csv");
        Alumno alumno7 = cargador.crearAlumno(sistemasC, "Juan", "Perez C", "/alumno2.csv");
        Alumno alumno8 = cargador.crearAlumno(sistemasC, "Roberto", "Carlos C", "/alumno3.csv");

        Alumno alumno9 = cargador.crearAlumno(sistemasD, "Tomás", "Villanueva D", "/alumno1.csv");
        Alumno alumno10 = cargador.crearAlumno(sistemasD, "Juan", "Perez D", "/alumno2.csv");
        Alumno alumno11 = cargador.crearAlumno(sistemasD, "Roberto", "Carlos D", "/alumno3.csv");

        Alumno alumno12 = cargador.crearAlumno(turismoE, "Alejandra", "Perez E", "/alumno4.csv");
        Alumno alumno13 = cargador.crearAlumno(turismoE, "Juan", "Carlos E", "/alumno5.csv");


        // Creo la facultad
        Facultad facultad = new Facultad();

        //Agrego las carreras
        facultad.agregarCarrera(sistemas);
        facultad.agregarCarrera(turismo);
        facultad.agregarCarrera(sistemasC);
        facultad.agregarCarrera(sistemasD);
        facultad.agregarCarrera(turismoE);

        // Agrego los alumnos
        facultad.agregarAlumno(alumno1);
        facultad.agregarAlumno(alumno2);
        facultad.agregarAlumno(alumno3);
        facultad.agregarAlumno(alumno4);
        facultad.agregarAlumno(alumno5);
        facultad.agregarAlumno(alumno6);
        facultad.agregarAlumno(alumno7);
        facultad.agregarAlumno(alumno8);
        facultad.agregarAlumno(alumno9);
        facultad.agregarAlumno(alumno10);
        facultad.agregarAlumno(alumno11);
        facultad.agregarAlumno(alumno12);
        facultad.agregarAlumno(alumno13);

        new MenuPrincipal(facultad).go();
    }
}
