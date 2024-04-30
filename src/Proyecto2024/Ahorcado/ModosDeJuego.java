package Proyecto2024.Ahorcado;

import java.util.Scanner;

public class ModosDeJuego {
    private String modoClasico;
    private String modoCrono;
    private int intentosCrono;
    private int intentosClasico;


    public ModosDeJuego(String modoClasico, String modoCrono, int intentosCrono, int intentosClasico) {
        this.modoClasico = modoClasico;
        this.modoCrono = modoCrono;
        this.intentosCrono = intentosCrono ;
        this.intentosClasico = intentosClasico;
    }
    public String getModoClasico(){
        return modoClasico;
    }

    public void setModoClasico(String modoClasico) {
        this.modoClasico = modoClasico;
    }
    public String getModoCrono() {
        return modoCrono;
    }
    public void setModoCrono(String modoCrono) {
        this.modoCrono = modoCrono;
    }
    public int getIntentosClasico() {
        return intentosClasico;
    }
    public int getIntentosCrono() {
        return intentosCrono;
    }
    public void instuccionesClasico() {
        System.out.println("-Tendrás un total de " + intentosClasico + " intentos.\n-Deberas escribir una letra, en el caso que no aciertes se insertará como fallada y no se podra repetir, si aciertas se colocará como acertada.\n-Pasarlo bien");
    }
    public void instruccionesCrono() {
        System.out.println("-Tendras " + intentosCrono + " intentos, lo cual son más.\n-Tendras segundos para adivinar la palabra.\n-Deberas escribir una letra, en el caso que no aciertes se insertará como fallada y no se podra repetir, si aciertas se colocará como acertada.\n-Pasarlo bien\n" );
    }
    public String eleccionModo() {
        Scanner scanner = new Scanner(System.in);
        int eleccion = scanner.nextInt();
        if (eleccion == 1) {
            return modoClasico;
        }
        else if (eleccion == 2) {
            return modoCrono;
        }
        else
            System.out.println("No has pulsado ninguno de los dos numeros proporcionados. Porfavor vuelva a intentarlo.");
            return eleccionModo();
    }
}
