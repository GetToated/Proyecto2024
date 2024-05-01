package Proyecto2024.Ahorcado;

public class Puntuacion {
    private int mostrarPuntuacionInicio;
    private int mostrarPuntuacionFinal;

    public Puntuacion(int mostrarPuntuacionInicio, int mostrarPuntuacionFinal) {
        this.mostrarPuntuacionInicio = mostrarPuntuacionInicio;
        this.mostrarPuntuacionFinal = mostrarPuntuacionFinal;
    }
    public int getMostrarPuntuacionFinal() {
        return mostrarPuntuacionFinal;
    }
    public void actualizarPuntuacion(int nuevaPuntuacion) {
        this.mostrarPuntuacionInicio += nuevaPuntuacion;
    }
}
