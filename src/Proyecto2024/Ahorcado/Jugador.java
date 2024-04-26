package Proyecto2024.Ahorcado;

public class Jugador {
    private String nombre;
    private int edad;
    private int intentosRealizados;
    private int puntuacion;

    public Jugador (String nombre, int edad, int intentosRealizados, int puntuacion) {

        this.nombre = nombre;
        this.edad = edad;
        this.intentosRealizados = intentosRealizados;
        this.puntuacion = puntuacion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
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
    public void setPuntuacion(int puntuacion) {
        return;
    }

}
