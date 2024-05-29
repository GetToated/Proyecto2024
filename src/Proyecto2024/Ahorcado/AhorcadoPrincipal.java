package Proyecto2024.Ahorcado;

import java.util.*;

public class AhorcadoPrincipal {
    private Interfaz alarma;
    Scanner scanner = new Scanner(System.in);
    private final int intentos;
    private Timer time;
    private int tiempoRestante;
    private final int tiempoLimite;
    private int intentosUsados;
    private boolean palabraAdivinada;
    private final Puntuacion puntuacion;
    private Jugador jugador;
    private ModosDeJuego modosDeJuego;

    public AhorcadoPrincipal(int intentos, int tiempoLimite) {
        this.intentos = intentos;
        this.tiempoLimite = tiempoLimite;
        this.puntuacion = new Puntuacion(0, 0);
        this.jugador = null;
        this.alarma = new Alarma();
        this.modosDeJuego = new ModosDeJuego("Clásico", "Cronometrado", intentos);  // Inicialización de ModosDeJuego
    }

    Logros logro1 = new Logros("Modo de Juego Ganado.", false);
    Logros logroIntentos = new Logros("Hecho en la mitad de intentos", false);

    public int getIntentos() {
        return intentos;
    }

    public void comienzoDelJuego() {
        String nombreJugador;
        try {
            while (true) {
                System.out.print("Introduzca su nombre: ");
                nombreJugador = scanner.nextLine();
                if (!nombreJugador.matches("[a-zA-Z]+")) {
                    System.out.println("Error: Nombre no válido. Por favor, ingrese un nombre.");
                    continue;
                }
                break;

            }
            System.out.print("Introduza su edad: ");
            int edadJugador = scanner.nextInt();
            if (!validacionEdad(edadJugador)) {
                System.out.println("Edad no validad, usted es demasiado pequeño o la edad no es válida. :(");
                return;
            }
            System.out.println("Te consideras buen jugador del ahorcado?(para ajustar la dificultad)  SI[true] o NO[false]: ");
            boolean esBuenJugador = scanner.nextBoolean();

            jugador = new TipoJugador(nombreJugador, edadJugador, 0, 0, esBuenJugador, 0);
            System.out.println("Gracias " + jugador.getNombre() + " por participar!");

            modosDeJuego.imprimirInstrucciones();

            String modoASeleccionar = seleccionDeModoDeJuego();
            if (modoASeleccionar.equalsIgnoreCase("1")) {
                System.out.println("Has seleccionado el modo Clasico.");
                juegoClasico((TipoJugador) jugador);
            } else if (modoASeleccionar.equalsIgnoreCase("2")) {
                System.out.println("Has seleccionado el modo Cronometrado");
                juegoCrono((TipoJugador) jugador);
            } else {
                System.out.println("Opción no valida.");
            }

        } catch (InputMismatchException exception) {
            System.out.println("Error: Entrada No Valida");
            scanner.next();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private boolean validacionEdad(int guardarEdad) {
        return guardarEdad >= 6 && guardarEdad <= 120;
    }

    public void juegoClasico(TipoJugador jugador) {
        String palabraOculta = listaSegunJugador(jugador);
        int intentosRestantes = getIntentos();
        intentosUsados = 0;
        PalabraEscondida palabraEscondida = new PalabraEscondida(palabraOculta, "_".repeat(palabraOculta.length()));
        while (intentosRestantes > 0 && !palabraEscondida.letrasAdivinadas()) {
            System.out.print("Intentos Restantes: " + intentosRestantes + "\n");
            System.out.print("Palabra: " + palabraEscondida.obtenerPalabraOculta() + "\n");
            char letra = pedirLetra();

            if (palabraEscondida.adivinaLetra(letra)) {
                System.out.println("La letra " + letra + " esta en la palabra");
            } else {
                System.out.println("La letra " + letra + " NO está en la palabra");
                intentosRestantes--;
                intentosUsados++;
            }
        }
        if (palabraEscondida.letrasAdivinadas()) {
            System.out.println("¡Ganaste! " + palabraOculta + " era la palabra.");

            int puntosGanados = calcularPuntuacion(intentosUsados);
            puntuacion.actualizarPuntuacion(puntuacion.getMostrarPuntuacionFinal() + puntosGanados);
            jugador.setPuntuacion(jugador.getPuntuacion() + puntosGanados);
            jugador.setIntentosRealizados(jugador.getIntentosRealizados() + getIntentos() - intentosRestantes);
            System.out.println("Puntuacion Total: " + jugador.getPuntuacion());
            System.out.println("Intentos Realizados: " + jugador.getIntentosRealizados());
            logro1.capProgreso(100);
            if (logro1.getVerificarLogro()) {
                System.out.println("Felicidades! desbloqueaste el logro: " + logro1.getLogroNombre());
            }
            if (intentosUsados <= getIntentos() / 2) {
                logroIntentos.capProgreso(100);
                if (logroIntentos.getVerificarLogro()) {
                    System.out.println("Felicidades! desbloqueaste el logro: " + logroIntentos.getLogroNombre());
                }
            }
        } else {
            System.out.println("Perdiste, la palabra era: " + palabraOculta);
        }
        seguirJugando();
    }

    private int calcularPuntuacion(int intentosUsados) {
        if (intentosUsados <= intentos / 4) {
            return 50;
        } else if (intentosUsados <= intentos / 2) {
            return 30;
        } else {
            return 10;
        }
    }

    public void juegoCrono(TipoJugador jugador) {
        String palabraOculta = listaSegunJugador(jugador);

        iniciarTemporizador();
        PalabraEscondida palabraEscondida = new PalabraEscondida(palabraOculta, "_".repeat(palabraOculta.length()));

        Thread thread = new Thread(() -> {
            while (tiempoRestante > 0 && !palabraAdivinada) {
                System.out.print("\rTiempo Restante: " + tiempoRestante + " segundos   ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tiempoRestante--;
            }
            detenerTemporizador();
            if (!palabraEscondida.letrasAdivinadas() && !palabraAdivinada) {
                System.out.println("\nSe acabó el tiempo, la palabra era " + palabraOculta);
                alarma.sonar();
                seguirJugandoCrono();
            }
        });
        thread.start();
        while (tiempoRestante > 0 && !palabraEscondida.letrasAdivinadas()) {
            char letra = pedirLetra();
            if (palabraEscondida.adivinaLetra(letra)) {
                System.out.println("\nLa letra " + letra + " está en la palabra.");
            } else {
                System.out.println("\nLa letra " + letra + " NO está en la palabra.");
            }
        }
        if (palabraEscondida.letrasAdivinadas()) {
            palabraAdivinada = true;
            System.out.println("\n¡Ganaste! " + palabraOculta + " era la palabra.");
            detenerTemporizador();
            int puntosGanados = calcularPuntuacionCrono(tiempoRestante);
            puntuacion.actualizarPuntuacion(puntuacion.getMostrarPuntuacionFinal() + puntosGanados);
            jugador.setPuntuacion(jugador.getPuntuacion() + puntosGanados);
            System.out.println("Puntuacion Total: " + jugador.getPuntuacion());
            logro1.capProgreso(100);
            if (logro1.getVerificarLogro()) {
                System.out.println("Felicidades! desbloqueaste el logro: " + logro1.getLogroNombre());
            }
        }
        seguirJugandoCrono();
    }

    private void iniciarTemporizador() {
        tiempoRestante = tiempoLimite;
    }

    private void detenerTemporizador() {
        tiempoRestante = 0;
    }

    private int calcularPuntuacionCrono(int tiempoRestante) {
        if (tiempoRestante > tiempoLimite / 2) {
            return 50;
        } else if (tiempoRestante > tiempoLimite / 4) {
            return 30;
        } else {
            return 10;
        }
    }

    public void seguirJugando() {
        System.out.println("Desea seguir jugando? SI[true] o NO[false]: ");
        boolean seguir = scanner.nextBoolean();
        if (seguir) {
            comienzoDelJuego();
        } else {
            System.out.println("Gracias por jugar!");
        }
    }

    public void seguirJugandoCrono() {
        System.out.println("Desea seguir jugando? SI[true] o NO[false]: ");
        boolean seguir;
        while (!scanner.hasNextBoolean()) {
            System.out.println("Ingresa un valor booleano válido: ");
            scanner.next();
        }
        seguir = scanner.nextBoolean();
        if (seguir) {
            comienzoDelJuego();
        } else {
            System.out.println("Gracias por jugar!");
        }
    }


    private char pedirLetra() {
        System.out.print("Introduce una letra: ");
        return scanner.next().charAt(0);
    }

    private String listaSegunJugador(TipoJugador jugador) {
        String[] lista = jugador.listas();
        Random random = new Random();
        int indice = random.nextInt(lista.length);
        return lista[indice];
    }

    private void imprimirInstrucciones() {

    }

    private String seleccionDeModoDeJuego() {
        System.out.println("Selecciona el modo de juego:\n1. Clasico\n2. Cronometrado");
        return scanner.next();
    }

}
