package Modelo;

import java.util.LinkedList;

public class Alumno {
    private String nombre;
    private String apellido;

    private LinkedList<Cursada> cursadas;

    public Alumno(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
        this.cursadas = new LinkedList<Cursada>();
    }

    public void inscribirEn(Cursada cursada){
        cursadas.add(cursada);
    }

    //getters y setters
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

    public LinkedList getCursadas() {
        return cursadas;
    }

}
