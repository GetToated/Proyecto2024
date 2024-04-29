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
    public void mostrarPalabraMetodo() {
        String palabraAMostrar = "";
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            if (mostrarPalabra.charAt(i) != '-') {
                palabraAMostrar += letra + " ";
            } else
                palabraAMostrar += "- ";
            }
        System.out.println("La palabra oculta era: " + palabraAMostrar);
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
    public boolean verificarLetra(char letra) {
        boolean letraEncontrada = false;
        for (int i = 0; i < palabra.length(); i ++) {
            if (palabra.charAt(i) == letra) {
                mostrarPalabra = mostrarPalabra.substring(0 , i) + letra + mostrarPalabra.substring(i + 1);
                letraEncontrada = true;
            }
        }
        verificarLetra = letraEncontrada;
        return letraEncontrada;
    }
}



