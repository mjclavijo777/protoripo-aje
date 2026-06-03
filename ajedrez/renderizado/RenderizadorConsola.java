package ajedrez.renderizado;

import ajedrez.piezas.Pieza;
import ajedrez.piezas.PeonEspecial;
import ajedrez.tablero.Tablero;



public class RenderizadorConsola implements RenderizadorTablero {

    /** Ancho fijo de cada celda del tablero en caracteres */
    private static final int ANCHO_CELDA = 5;

    /** Linea separadora larga para cabeceras */
    private static final String LINEA =
        "+-----+-----+-----+-----+-----+-----+-----+-----+";

    /** Linea de titulo del juego */
    private static final String TITULO =
        "=======================================================";

    
    @Override
    public void mostrarTablero(Tablero tablero, int resaltFila, int resaltCol) {
        System.out.println();
        System.out.println(TITULO);
        System.out.println("         TABLERO DE AJEDREZ ESPECIAL");
        System.out.println(TITULO);
        System.out.println();

        // Encabezado de columnas (letras a-h)
        System.out.print("   |");
        for (char col = 'A'; col <= 'H'; col++) {
            System.out.printf("  %c  |", col);
        }
        System.out.println();
        System.out.println("---" + LINEA);

        // Filas del tablero (de 8 a 1)
        for (int fila = 0; fila < Tablero.TAMANO; fila++) {
            int numeroFila = 8 - fila;

            // Numero de fila al inicio
            System.out.printf(" %d |", numeroFila);

            for (int col = 0; col < Tablero.TAMANO; col++) {
                Pieza pieza = tablero.getPieza(fila, col);
                String contenido = obtenerContenidoCelda(pieza);

                // El peon especial ocupa 3 chars [X], centrado en 5
                if (contenido.length() == 3) {
                    // 1 espacio antes, 1 despues: " [X] "
                    System.out.printf(" %s |", contenido);
                } else {
                    // Pieza normal (1 char) o vacio (1 char): "  X  "
                    System.out.printf("  %s  |", contenido);
                }
            }

            // Numero de fila al final
            System.out.printf(" %d%n", numeroFila);
            System.out.println("---" + LINEA);
        }

        // Pie de columnas
        System.out.print("   |");
        for (char col = 'A'; col <= 'H'; col++) {
            System.out.printf("  %c  |", col);
        }
        System.out.println();
        System.out.println();
    }

  
    private String obtenerContenidoCelda(Pieza pieza) {
        if (pieza == null) {
            return ".";  // Casilla vacia
        }
        // El simbolo del PeonEspecial ya viene en formato "[X]" (3 chars)
        // Los demas simbolos son 1 caracter (letra)
        return pieza.getSimbolo();
    }

    
    @Override
    public void mostrarLeyenda(PeonEspecial peonBlanco, PeonEspecial peonNegro) {
        System.out.println("-------------------------------------------------------");
        System.out.println("  LEYENDA DE PIEZAS:");
        System.out.println("    Blancas (MAYUSCULAS) : K=Rey  Q=Reina  T=Torre");
        System.out.println("                           A=Alfil  C=Caballo  P=Peon");
        System.out.println("    Negras  (minusculas)  : k=Rey  q=Reina  t=Torre");
        System.out.println("                           a=Alfil  c=Caballo  p=Peon");
        System.out.println("    Casilla vacia         : .");

        if (peonBlanco != null) {
            System.out.println("    Peon Especial BLANCO  : "
                + peonBlanco.getSimbolo()
                + "  (habilidad activa: " + peonBlanco.getHabilidadActual() + ")");
        }
        if (peonNegro != null) {
            System.out.println("    Peon Especial NEGRO   : "
                + peonNegro.getSimbolo()
                + "  (habilidad activa: " + peonNegro.getHabilidadActual() + ")");
        }
        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

   
    @Override
    public void mostrarBienvenida() {
        System.out.println();
        System.out.println(TITULO);
        System.out.println("         AJEDREZ ESPECIAL - CON PEONES MUTANTES");
        System.out.println(TITULO);
        System.out.println();
        mostrarAyuda();
    }

    /**
     * Muestra la lista de comandos disponibles para el jugador.
     */
    @Override
    public void mostrarAyuda() {
        System.out.println("  COMANDOS DISPONIBLES:");
        System.out.println("  ------------------------------------------------");
        System.out.println("  mover <orig> <dest>      Mover pieza");
        System.out.println("                           Ejemplo: mover e2 e4");
        System.out.println("  <orig> <dest>            Atajo rapido");
        System.out.println("                           Ejemplo: e2 e4");
        System.out.println("  habilidad <pos> <hab>    Cambiar habilidad del peon especial");
        System.out.println("                           Ejemplo: habilidad d2 reina");
        System.out.println("                           Opciones: peon caballo torre reina alfil rey");
        System.out.println("  ayuda                    Mostrar esta ayuda");
        System.out.println("  salir                    Salir del juego");
        System.out.println("  ------------------------------------------------");
        System.out.println("  * Peon Especial BLANCO: casilla D2  (aparece como [X])");
        System.out.println("  * Peon Especial NEGRO : casilla E7  (aparece como [x])");
        System.out.println("  ------------------------------------------------");
        System.out.println();
    }

   
    @Override
    public void mostrarTurno(boolean turnoBlanco) {
        String equipo = turnoBlanco ? "BLANCAS (mayusculas)" : "NEGRAS  (minusculas)";
        System.out.println("  >> Turno de las " + equipo);
        System.out.println("  >> Comandos: [mover e2 e4]  [habilidad d2 reina]  [ayuda]  [salir]");
        System.out.print  ("  >> ");
    }

    
    @Override
    public void mostrarError(String mensaje) {
        System.out.println("  [ERROR] " + mensaje);
    }

    
    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println("  [OK]    " + mensaje);
    }

  
    @Override
    public void mostrarVictoria(boolean ganaronBlancas) {
        String equipo = ganaronBlancas ? "BLANCAS" : "NEGRAS";
        System.out.println();
        System.out.println(TITULO);
        System.out.println("   !!! VICTORIA DE LAS " + equipo + " !!!");
        System.out.println(TITULO);
        System.out.println();
    }
}
