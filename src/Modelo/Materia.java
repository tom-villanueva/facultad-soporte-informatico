package Modelo;

import java.util.LinkedList;
import java.util.Objects;

public class Materia {

    private String nombre;
    private boolean obligatoria;
    private boolean promocionable;
    private Integer numeroCuatrimestre;

    private LinkedList<Materia> correlativas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materia materia = (Materia) o;
        return obligatoria == materia.obligatoria && promocionable == materia.promocionable && nombre.equals(materia.nombre) && numeroCuatrimestre.equals(materia.numeroCuatrimestre) && correlativas.equals(materia.correlativas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, obligatoria, promocionable, numeroCuatrimestre, correlativas);
    }

    public Materia (String nombre, boolean obligatoria, boolean promocionable) {
        this.nombre = nombre;
        this.obligatoria = obligatoria;
        this.promocionable = promocionable;
        this.correlativas = new LinkedList<Materia>();
    }

    public void agregarCorrelativa(Materia materia) throws Exception {
        if(this.numeroCuatrimestre > materia.getNumeroCuatrimestre()){
            correlativas.add(materia);
        }
        else {
            throw new Exception("La materia no puede ser correlativa");
        }
    }

    @Override
    public String toString() {
        return nombre;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setCorrelativas(LinkedList<Materia> correlativas) {
        this.correlativas = correlativas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public boolean isPromocionable() {
        return promocionable;
    }

    public void setPromocionable(boolean promocionable) {
        this.promocionable = promocionable;
    }

    public LinkedList<Materia> getCorrelativas() {
        return correlativas;
    }

    public Integer getNumeroCuatrimestre() {
        return numeroCuatrimestre;
    }

    public void setNumeroCuatrimestre(Integer numeroCuatrimestre) {
        this.numeroCuatrimestre = numeroCuatrimestre;
    }
}
