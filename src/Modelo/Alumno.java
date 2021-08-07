package Modelo;

import java.util.LinkedList;

public class Alumno {
    private String nombre;
    private String apellido;

    private LinkedList<Cursada> cursadas;
    private Carrera carrera;

    public Alumno(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
        cursadas = new LinkedList<Cursada>();
    }

    public void inscribirEn(Cursada cursada){
        cursadas.add(cursada);
    }

    public boolean estaInscriptoEn(Materia materia) {
        for (Cursada cursada : cursadas) {
            if(cursada.getMateria() == materia) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Materia> getMateriasAprobadas() {
        LinkedList<Materia> res = new LinkedList<>();

        for (Cursada cursada : cursadas) {
            if(cursada.getEstado() == EstadoCursada.APROBADA) {
                res.add(cursada.getMateria());
            }
        }
        return res;
    }

    public LinkedList<Materia> getMateriasCursadaAprobada() {
        LinkedList<Materia> res = new LinkedList<>();

        for (Cursada cursada : cursadas) {
            if(cursada.getEstado() == EstadoCursada.CURSADA_APROBADA) {
                res.add(cursada.getMateria());
            }
        }
        return res;
    }

    //getters y setters

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LinkedList<Cursada> getCursadas() {
        return cursadas;
    }

    @Override
    public String toString() {
        return nombre +" "+ apellido;
    }
}
