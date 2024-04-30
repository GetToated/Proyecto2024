package Proyecto2024.Ahorcado;
import java.util.*;
public class AhorcadoPrincipal {
    Scanner scanner = new Scanner(System.in);
    private int intentos;
    private boolean victoriaODerrota;
    private Timer time;
    private int tiempoRestante;
    private int tiempoLimite;

    public AhorcadoPrincipal(int intentos, int tiempoLimite) {
        this.intentos = intentos;
        this.tiempoLimite = tiempoLimite;
    }
    public int getIntentos() {
        return intentos;
    }
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
    public void juegoClasico() {
        String palabraOculta = palabraRandom();
        int intentosRestantes = getIntentos();
        PalabraEscondida palabraEscondida = new PalabraEscondida(palabraOculta, "_".repeat(palabraOculta.length()), false);
        while (intentosRestantes > 0 && !palabraEscondida.letrasAdivinadas()) {
            System.out.print("Intentos Restantes: " + intentosRestantes + "\n");
            System.out.print("Palabra: " +  palabraEscondida.obtenerPalabraOculta() + "\n");
            char letra = pedirLetra();
            if (palabraEscondida.adivinaLetra(letra)) {
                System.out.println("La letra " + letra  + " esta en la palabra");
            } else
                System.out.println("La letra " + letra + "  NO esta en la palabra");
                intentosRestantes--;
        }
        if (palabraEscondida.letrasAdivinadas()) {
            System.out.println("Ganaste! " + palabraOculta + " era la palabra.");
        } else if (tiempoRestante <= 0) {
            System.out.println("perdiste la palabra era " + palabraOculta);
        } else {
            System.out.println("lost");
        }
    }
    public void juegoCrono() {
        String palabraOculta = palabraRandom();
        int intentosRestantes = getIntentos();
        iniciarTemporizador();
        PalabraEscondida palabraEscondida = new PalabraEscondida(palabraOculta, "_".repeat(palabraOculta.length()), false);
        Thread thread = new Thread(() -> {
            while (tiempoRestante > 0) {
                System.out.print("\rTiempo Restante: " + tiempoRestante + " segundos   ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tiempoRestante--;
            }
        });
        thread.start();
        while (intentosRestantes > 0 && tiempoRestante > 0 && !palabraEscondida.letrasAdivinadas()) {
            char letra = pedirLetra();
            if (palabraEscondida.adivinaLetra(letra)) {
                System.out.println("\nLa letra " + letra + " está en la palabra.");
            } else {
                System.out.println("\nLa letra " + letra + " NO está en la palabra");
            }
            intentosRestantes--;
        }
        deterTemporizador();

        if (palabraEscondida.letrasAdivinadas()) {
            System.out.println("Ganaste!, la palabra es: " + palabraOculta);
        } else {
            System.out.println("Se acabó el tiempo, la palabra era " + palabraOculta);
        }
    }

    public void iniciarTemporizador() {
        tiempoRestante = tiempoLimite;
        time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                tiempoRestante--;
                if (tiempoRestante == 0) {
                    time.cancel();
                }
            }
        }, 1000, 1000);
    }
    public void deterTemporizador() {
        time.cancel();
    }
    public void comienzoDelJuego() {
        try {
            String nombreJugador = nombre();
            int edadJugador = edad();
            if (!validacionEdad(edadJugador)) {
                System.out.println("Edad no validad, usted es demasiado pequeño :(");
                return;
            }
            System.out.println("Gracias " + nombreJugador + " por participar!");
            Logros logro1 = new Logros("Ganado por primera vez.", false);
            Logros logro2 = new Logros("Cronometro antes de tiempo.", false);

            imprimirInstrucciones();
            String modoASeleccionar = seleccionDeModoDeJuego();
            if (modoASeleccionar.equalsIgnoreCase("1")) {
                System.out.println("Has seleccionado el modo Clasico.");
                juegoClasico();
            } else if (modoASeleccionar.equalsIgnoreCase("2")) {
                System.out.println("Has seleccionado el modo Cronometrado");
                juegoCrono();
            } else
                System.out.println("Opcion no valida, introduzca un numero dicho anteriormente.");


        } catch (InputMismatchException exception) {
            System.out.println("Error: eqeq");
        } catch (NumberFormatException exception) {
            System.out.println("e2qeqeq");
        } catch (Exception exception) {
            System.out.println("eqrqr" + exception.getMessage());
        }
    }
    private String nombre() {
        System.out.print("Introduzca tu nombre: ");
        return scanner.nextLine();
    }
    private int edad() {
        System.out.print("Introduzca tu edad: ");
        return scanner.nextInt();
    }
    private boolean validacionEdad(int guardarEdad) {
        return guardarEdad >= 6 && guardarEdad <= 120;
    }
    private void imprimirInstrucciones() {
        ModosDeJuego modosDeJuego = new ModosDeJuego("Clásico", "Cronometrado", 10, 7);
        System.out.println("Aquí estan las instrucciones del modo " + modosDeJuego.getModoClasico() + ":\n");
        modosDeJuego.instuccionesClasico();
        System.out.println("Aquí estan las instrucciones del modo " + modosDeJuego.getModoCrono() + ":\n");
        modosDeJuego.instruccionesCrono();
    }
    private String seleccionDeModoDeJuego() {
        System.out.print("Selecciona 1 para jugar al modo clasico o selecciona 2 para el modo Cronometrado: ");
        return scanner.next();
    }
    private char pedirLetra() {
        System.out.print("Introduce una letra: ");
        return scanner.next().charAt(0);
    }
    private String palabraRandom() {
        String[] lista = {"leon", "java", "programacion", "logica", "esternocleidomastoiedo", "mar"};
        Random random = new Random();
        int indice = random.nextInt(lista.length);
        return lista[indice];
    }


}
