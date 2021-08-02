package Modelo;

import java.util.LinkedList;

public class Cuatrimestre {

    private Integer numero;
    private LinkedList<Materia> obligatorias;
    private LinkedList<Materia> optativas;

    public Cuatrimestre() {
        this.obligatorias = new LinkedList<Materia>();
        this.optativas = new LinkedList<Materia>();
    }

    public void agregarMateria(Materia materia) {
        materia.setNumeroCuatrimestre(this.numero);
        if(materia.isObligatoria()) {
            this.obligatorias.add(materia);
        }
        else {
            this.optativas.add(materia);
        }
    }

    public Integer getCantidadOptativas() {
        return this.optativas.size();
    }

    public LinkedList<Materia> getMaterias() {
        LinkedList<Materia> todas = new LinkedList<>();
        todas.addAll(obligatorias);
        todas.addAll(optativas);
        return todas;
    }

    @Override
    public String toString() {
        return "Cuatrimestre{" +
                "numero=" + numero +
                ", obligatorias=" + obligatorias +
                ", optativas=" + optativas +
                '}';
    }

    // getters y setters

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LinkedList<Materia> getObligatorias() {
        return obligatorias;
    }

    public void setObligatorias(LinkedList<Materia> obligatorias) {
        this.obligatorias = obligatorias;
    }

    public LinkedList<Materia> getOptativas() {
        return optativas;
    }

    public void setOptativas(LinkedList<Materia> optativas) {
        this.optativas = optativas;
    }
}
