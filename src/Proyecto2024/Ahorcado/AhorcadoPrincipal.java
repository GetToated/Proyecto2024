package Proyecto2024.Ahorcado;
import java.util.*;
public class AhorcadoPrincipal {
    Scanner scanner = new Scanner(System.in);
    private int intentos;
    private Timer time;
    private int tiempoRestante;
    private int tiempoLimite;
    private int intentosUsados;
    private boolean palabraAdivinada;
    private Puntuacion puntuacion;
    private Jugador jugador;

    public AhorcadoPrincipal(int intentos, int tiempoLimite) {
        this.intentos = intentos;
        this.tiempoLimite = tiempoLimite;
        this.puntuacion = new Puntuacion(0, 0);
        this.jugador = null;
    }


    Logros logro1 = new Logros("Modo de Juego Ganado.", false);
    Logros logroIntentos = new Logros("Hecho en la mitad de intentos", false);
    public int getIntentos() {
        return intentos;
    }
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
    public void juegoClasico(TipoJugador jugador) {
        String palabraOculta = listaSegunJugador(jugador);
        int intentosRestantes = getIntentos();
        intentosUsados = 0;
        PalabraEscondida palabraEscondida = new PalabraEscondida(palabraOculta, "_".repeat(palabraOculta.length()), false);
        while (intentosRestantes > 0 && !palabraEscondida.letrasAdivinadas()) {
            System.out.print("Intentos Restantes: " + intentosRestantes + "\n");
            System.out.print("Palabra: " + palabraEscondida.obtenerPalabraOculta() + "\n");
            char letra = pedirLetra();

            if (palabraEscondida.adivinaLetra(letra)) {
                System.out.println("La letra " + letra + " está en la palabra");
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
        PalabraEscondida palabraEscondida = new PalabraEscondida(palabraOculta, "_".repeat(palabraOculta.length()), false);

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
            deterTemporizador();
            if (!palabraEscondida.letrasAdivinadas() && !palabraAdivinada) {
                System.out.println("\nSe acabó el tiempo, la palabra era " + palabraOculta);
                seguirJugandoCrono();
            }
        });
        thread.start();
        while (tiempoRestante > 0 && !palabraEscondida.letrasAdivinadas()) {
            char letra = pedirLetra();
            if (palabraEscondida.adivinaLetra(letra)) {
                System.out.println("\nLa letra " + letra + " está en la palabra.");
            } else {
                System.out.println("\nLa letra " + letra + " NO está en la palabra");
            }
        }
        if (palabraEscondida.letrasAdivinadas()) {
            palabraAdivinada = true;
            System.out.println("Ganaste!, la palabra es: " + palabraOculta);
            int puntosGanados = calcularPuntuacion(intentosUsados);
            puntuacion.actualizarPuntuacion(puntuacion.getMostrarPuntuacionFinal() + puntosGanados);
            jugador.setPuntuacion(jugador.getPuntuacion() +puntosGanados);
            System.out.println("Puntuacion final: " + jugador.getPuntuacion());
            logro1.capProgreso(100);
            if (logro1.getVerificarLogro()) {
                System.out.println("Felicidades has ganado el Logro: " + logro1.getLogroNombre());
            }
            seguirJugandoCrono();
        }
    }



    private String listaSegunJugador(TipoJugador jugador) {
        String[] lista = jugador.listas();
        Random random = new Random();
        int indice = random.nextInt(lista.length);
        return lista[indice];
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
    public void seguirJugando() {
        System.out.print("Quieres seguir jugando? SI[1] o NO[2]: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingresa un número válido.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        if (opcion == 1) {
            juegoClasico((TipoJugador) jugador);
        } else if (opcion == 2) {
            System.out.println("Intentos Realizados: " + jugador.getIntentosRealizados());
            int puntuacionActualizada = jugador.getPuntuacioTotal() + jugador.getPuntuacion();
            System.out.println("Puntuacion Total: " + puntuacionActualizada);
            jugador.setPuntuacionTotal(puntuacionActualizada);
            return;
        } else {
            System.out.println("Opción inválida. Saliendo del juego.");
        }
    }
    public void seguirJugandoCrono() {
        System.out.print("Quieres seguir jugando? SI[1] o NO[2]: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingresa un número válido.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        if (opcion == 1) {
            juegoCrono((TipoJugador) jugador);
        } else if (opcion == 2) {
            System.out.println("Intentos Realizados: " + jugador.getIntentosRealizados());
            int puntuacionActualizada = jugador.getPuntuacioTotal() + jugador.getPuntuacion();
            System.out.println("Puntuacion Total: " + (jugador.getPuntuacioTotal() + jugador.getPuntuacion()));
            jugador.setPuntuacionTotal(puntuacionActualizada);
            return;
        } else {
            System.out.println("Opción inválida. Saliendo del juego.");
        }
    }
    public void deterTemporizador() {
        time.cancel();
    }
    public void comienzoDelJuego() {
        try {
            System.out.print("Introduza su nombre: ");
            String nombreJugador = scanner.nextLine();
            System.out.print("Introduza su edad: ");
            int edadJugador = scanner.nextInt();
            if (!validacionEdad(edadJugador)) {
                System.out.println("Edad no validad, usted es demasiado pequeño :(");
                return;
            }
            System.out.println("te consideras buen jugador del ahorcado? SI[true] o NO[false]: ");
            boolean esBuenJugador = scanner.nextBoolean();

            jugador = new TipoJugador(nombreJugador, edadJugador, 0, 0, esBuenJugador, 0);
            System.out.println("Gracias " + jugador.getNombre() + " por participar!");


            imprimirInstrucciones();
            String modoASeleccionar = seleccionDeModoDeJuego();
            if (modoASeleccionar.equalsIgnoreCase("1")) {
                System.out.println("Has seleccionado el modo Clasico.");
                juegoClasico((TipoJugador) jugador);
            } else if (modoASeleccionar.equalsIgnoreCase("2")) {
                System.out.println("Has seleccionado el modo Cronometrado");
                juegoCrono((TipoJugador) jugador);
            } else {
                System.out.println("Opcion no valida.");
            }

        } catch (InputMismatchException exception) {
            System.out.println("Error: Entrada No Valida");
        } catch (Exception exception) {
            System.out.println("error: " + exception.getMessage());
        }
    }


    private boolean validacionEdad(int guardarEdad) {
        return guardarEdad >= 6 && guardarEdad <= 120;
    }
    private void imprimirInstrucciones() {
        ModosDeJuego modosDeJuego = new ModosDeJuego("Clásico", "Cronometrado", 10);
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


}
