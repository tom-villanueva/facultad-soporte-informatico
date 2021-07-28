package Modelo;

import java.util.LinkedList;

public class Cuatrimestre {

    private Integer numero;
    private LinkedList<Materia> obligatorias;
    private LinkedList<Materia> optativas;

    public Cuatrimestre() {
    }

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
