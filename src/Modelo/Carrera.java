package Modelo;

import java.util.LinkedList;

public class Carrera {

    private String nombre;
    private Integer cantidadOptativas;

    private PlanDeEstudio plan;
    private LinkedList<Alumno> alumnos;

    public Carrera() {
        this.alumnos = new LinkedList<Alumno>();
    }

    public void agregarAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "nombre='" + nombre + '\'' +
                ", plan=" + plan +
                ", alumnos=" + alumnos +
                '}';
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadOptativas() {
        return cantidadOptativas;
    }

    public void setCantidadOptativas(Integer cantidadOptativas) {
        this.cantidadOptativas = cantidadOptativas;
    }

    public PlanDeEstudio getPlan() {
        return plan;
    }

    public void setPlan(PlanDeEstudio plan) {
        this.plan = plan;
    }

    public LinkedList<Alumno> getAlumnos() {
        return alumnos;
    }
}
