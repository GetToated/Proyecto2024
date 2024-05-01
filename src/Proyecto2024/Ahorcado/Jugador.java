package Proyecto2024.Ahorcado;

public class Jugador {
    private String nombre;

    private int intentosRealizados;
    private int puntuacion;
    private int puntuacionTotal;


    public Jugador (String nombre,  int intentosRealizados, int puntuacion, int puntuacionTotal) {
        this.nombre = nombre;
        this.intentosRealizados = intentosRealizados;
        this.puntuacion = puntuacion;
        this.puntuacionTotal = puntuacionTotal;
    }
    public String getNombre() {
        return nombre;
    }
    public int getPuntuacioTotal() {
        return puntuacionTotal;
    }
    public void setPuntuacionTotal(int puntuacioTotal) {
        this.puntuacionTotal = puntuacioTotal;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    public int getIntentosRealizados() {
        return intentosRealizados;
    }
    public void setIntentosRealizados(int intentosRealizados) {
        this.intentosRealizados = intentosRealizados;
    }
    public int getPuntuacion() {
        return puntuacion;
    }

}
