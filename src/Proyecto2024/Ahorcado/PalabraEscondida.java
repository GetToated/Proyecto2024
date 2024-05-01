package Proyecto2024.Ahorcado;

public class PalabraEscondida {
    private String palabra;
    private String mostrarPalabra;
    private boolean verificarLetra;

    public PalabraEscondida(String palabra, String mostrarPalabra, boolean verificarLetra) {

        this.palabra = palabra;
        this.mostrarPalabra = "_".repeat(palabra.length());
        this.verificarLetra = verificarLetra;
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
        verificarLetra = letraEncontrada;
        return letraEncontrada;
    }

}



