package Proyecto2024.Ahorcado;

public class TipoJugador extends Jugador {
    private boolean esBuenJugador;

    public TipoJugador(String nombre, int edad, int intentosRealizados, int puntuacion, boolean esBuenJugador) {
        super(nombre, edad, intentosRealizados, puntuacion);
        this.esBuenJugador = esBuenJugador;

    }
    public boolean getEsBuenJugador() {
        return esBuenJugador;
    }
    public void setEsBuenJugador(boolean esBuenJugador) {
        this.esBuenJugador = esBuenJugador;
    }
    public String[] listas() {
        if (esBuenJugador) {
            return new String[] {"esternocleidomastoideo", "articulacion", "asteroide", "flexionar", "campamento", "dinosaurio"};
        } else {
            return new String[] {"leon", "cafe", "hola", "casa", "luz", "alto"};
        }
    }
}
