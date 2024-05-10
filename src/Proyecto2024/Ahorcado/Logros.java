package Proyecto2024.Ahorcado;

public class Logros {
    private final String logroNombre;
    private boolean verificarLogro;
    private int progresoLogro;

    public Logros(String logroNombre, boolean verificarLogro) {

        this.verificarLogro = verificarLogro;
        this.logroNombre = logroNombre;
        this.progresoLogro = 0;
    }
    public String getLogroNombre() {
        return logroNombre;
    }

    public boolean getVerificarLogro() {
        return verificarLogro;
    }


    public void capProgreso(int cantidadPuntos) {
        progresoLogro += cantidadPuntos;
        if (progresoLogro >= 100) {
            verificarLogro = true;
        }
    }

}
