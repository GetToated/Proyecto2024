package Proyecto2024.Ahorcado;

public class TipoJugador extends Jugador {
    private boolean esBuenJugador;

    public TipoJugador(String nombre, int edad, int intentosRealizados, int puntuacion, boolean esBuenJugador, int puntuacionTotal) {
        super(nombre, intentosRealizados, puntuacion, puntuacionTotal);
        this.esBuenJugador = esBuenJugador;

    }
    public String[] listas() {
        if (esBuenJugador) {
            return new String[] {"esternocleidomastoideo", "articulacion", "asteroide", "flexionar", "campamento", "dinosaurio", "mosasaurio"};
        } else {
            return new String[] {"java", "leon", "cafe", "hola", "casa", "luz", "alto"};
        }
    }
}
