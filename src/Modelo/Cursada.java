package Modelo;

public class Cursada {

    private Integer notaParcial;
    private Integer notaFinal;
    private Materia materia;
    private EstadoCursada estado;

    public Cursada (Materia materia) {
        this.materia = materia;
        this.estado = EstadoCursada.EN_CURSO;
    }

    public boolean aprobarMateria() {
        if(this.materia.isPromocionable()){
            if(this.notaParcial >= 4) {
                this.setEstado(EstadoCursada.APROBADA);
                return true;
            }
        }
        else {
            if(this.notaFinal >= 4) {
                this.setEstado(EstadoCursada.APROBADA);
                return true;
            }
        }
        return false;
    }

    public void aprobarCursada() {
        this.setEstado(EstadoCursada.CURSADA_APROBADA);
    }

    public void desaprobarMateria() {
        this.setEstado(EstadoCursada.DESAPROBADA);
    }

    //getters y setters
    public Integer getNotaParcial() {
        return notaParcial;
    }

    public void setNotaParcial(Integer notaParcial) {
        this.notaParcial = notaParcial;
    }

    public Integer getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Integer notaFinal) {
        this.notaFinal = notaFinal;
    }

    public EstadoCursada getEstado() {
        return estado;
    }

    public void setEstado(EstadoCursada estado) {
        this.estado = estado;
    }
}
