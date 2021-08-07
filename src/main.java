import Modelo.*;
import Vista.MenuPrincipal;

public class main {
    public static void main(String[] args) {

        Cargador cargador = new Cargador();
        Carrera sistemas = cargador.cargarMateriasDeArchivo("Licenciatura en Sistemas", "src/sistemas.csv");
        Facultad facultad = new Facultad();
        System.out.println(sistemas);
        facultad.agregarCarrera(sistemas);

        new MenuPrincipal(facultad).go();
    }
}
