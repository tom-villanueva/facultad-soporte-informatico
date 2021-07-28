package Modelo;

import java.util.LinkedList;

public class Materia {

    private String nombre;
    private boolean obligatoria;
    private boolean promocionable;

    private LinkedList<Materia> correlativas;

    public Materia (String nombre, boolean obligatoria, boolean promocionable) {
        this.nombre = nombre;
        this.obligatoria = obligatoria;
        this.promocionable = promocionable;
    }

    public void agregarCorrelativa(Materia materia) {
        correlativas.add(materia);
    }

    //getters y setters
    public String getNombre() {
        return nombre;
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

}
