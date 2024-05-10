package Proyecto2024.Ahorcado;

public class PalabraEscondida {
    private String palabra;
    private String mostrarPalabra;


    public PalabraEscondida(String palabra, String mostrarPalabra) {

        this.palabra = palabra;
        this.mostrarPalabra = "_".repeat(palabra.length());

    }
    public String obtenerPalabraOculta() {
        return mostrarPalabra;
    }
    public boolean letrasAdivinadas() {
        return !mostrarPalabra.contains("_");
    }
    public boolean adivinaLetra(char letra) {
        boolean letraEncontrada = false;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                mostrarPalabra = mostrarPalabra.substring(0, i) + letra + mostrarPalabra.substring(i + 1);
                letraEncontrada = true;
            }
        }
        return letraEncontrada;
    }

}



