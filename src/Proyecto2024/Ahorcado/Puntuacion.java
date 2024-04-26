package Proyecto2024.Ahorcado;

public class Puntuacion {
    private int mostrarPuntuacionInicio;
    private int MostrarPuntuacionFinal;
    public  Puntuacion(int mostrarPuntuacionInicio, int mostrarPuntuacionFinal ) {

        this.mostrarPuntuacionInicio = mostrarPuntuacionInicio;
        this.MostrarPuntuacionFinal = mostrarPuntuacionFinal;
    }
    public int getMostrarPuntuacionInicio() {
        return mostrarPuntuacionInicio;
    }
    public void setMostrarPuntuacionInicio(int mostrarPuntuacionInicio) {
        this.mostrarPuntuacionInicio = mostrarPuntuacionInicio;
    }
    public int getMostrarPuntuacionFinal() {
        return MostrarPuntuacionFinal;
    }
    public void setMostrarPuntuacionFinal(int mostrarPuntuacionFinal) {
        this.MostrarPuntuacionFinal = mostrarPuntuacionFinal;
    }

}
