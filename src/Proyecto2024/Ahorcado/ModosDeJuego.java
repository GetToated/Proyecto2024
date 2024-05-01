package Proyecto2024.Ahorcado;

import java.util.Scanner;

public class ModosDeJuego {
    private String modoClasico;
    private String modoCrono;
    private int intentosClasico;


    public ModosDeJuego(String modoClasico, String modoCrono,  int intentosClasico) {
        this.modoClasico = modoClasico;
        this.modoCrono = modoCrono;
        this.intentosClasico = intentosClasico;
    }
    public String getModoClasico(){
        return modoClasico;
    }

    public String getModoCrono() {
        return modoCrono;
    }


    public void instuccionesClasico() {
        System.out.println("-Tendrás un total de " + intentosClasico + " intentos.\n-Podrás obtener un cierto puntaje dependiendo de en cuantos intentos adivines la palabra.\n-Deberas escribir una letra, en el caso que no aciertes se insertará como fallada y no se podra repetir, si aciertas se colocará como acertada.\n-Pasarlo bien\n");
    }
    public void instruccionesCrono() {
        System.out.println("-Tendras 60 segundos para adivinar la palabra.\n-NO veras la longitud de la palabra, lo cual se dificulta.\n-Deberas escribir una letra, en el caso que no aciertes se insertará como fallada y no se podra repetir, si aciertas se colocará como acertada.\n-Pasarlo bien\n" );
    }

}
