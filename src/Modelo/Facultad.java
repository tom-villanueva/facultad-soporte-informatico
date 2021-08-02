package Modelo;

import Modelo.Alumno;
import Modelo.Carrera;

import java.util.LinkedList;

public class Facultad {

    private LinkedList<Alumno> alumnos;
    private LinkedList<Carrera> carreras;

    public Facultad() {
        this.alumnos = new LinkedList<>();
        this.carreras = new LinkedList<>();
    }

    public void agregarCarrera(Carrera carrera) {
        this.carreras.add(carrera);
    }

    public void agregarAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public LinkedList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(LinkedList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public LinkedList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(LinkedList<Carrera> carreras) {
        this.carreras = carreras;
    }
}
