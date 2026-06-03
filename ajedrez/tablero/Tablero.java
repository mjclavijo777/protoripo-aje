package ajedrez.tablero;

import ajedrez.piezas.*;


public class Tablero {

    /** Dimensión del tablero estándar de ajedrez */
    public static final int TAMANO = 8;

    /** Matriz interna que almacena las piezas (null = casilla vacía) */
    private final Pieza[][] cuadricula = new Pieza[TAMANO][TAMANO];

    
    public void inicializar() {
        colocarPiezasNegras();
        colocarPiezasBlancas();
    }

    /**
     * Coloca las piezas negras en sus posiciones iniciales (filas 0 y 1).
     */
    private void colocarPiezasNegras() {
        // Fila 0: piezas principales negras
        cuadricula[0][0] = new Torre(false);
        cuadricula[0][1] = new Caballo(false);
        cuadricula[0][2] = new Alfil(false);
        cuadricula[0][3] = new Reina(false);
        cuadricula[0][4] = new Rey(false);
        cuadricula[0][5] = new Alfil(false);
        cuadricula[0][6] = new Caballo(false);
        cuadricula[0][7] = new Torre(false);

        // Fila 1: peones negros (col 4 = peón especial negro)
        for (int col = 0; col < TAMANO; col++) {
            cuadricula[1][col] = (col == 4) ? new PeonEspecial(false) : new Peon(false);
        }
    }

    /**
     * Coloca las piezas blancas en sus posiciones iniciales (filas 6 y 7).
     */
    private void colocarPiezasBlancas() {
        // Fila 7: piezas principales blancas
        cuadricula[7][0] = new Torre(true);
        cuadricula[7][1] = new Caballo(true);
        cuadricula[7][2] = new Alfil(true);
        cuadricula[7][3] = new Reina(true);
        cuadricula[7][4] = new Rey(true);
        cuadricula[7][5] = new Alfil(true);
        cuadricula[7][6] = new Caballo(true);
        cuadricula[7][7] = new Torre(true);

        // Fila 6: peones blancos (col 3 = peón especial blanco)
        for (int col = 0; col < TAMANO; col++) {
            cuadricula[6][col] = (col == 3) ? new PeonEspecial(true) : new Peon(true);
        }
    }

   
    public Pieza getPieza(int fila, int col) {
        return cuadricula[fila][col];
    }

    
    public void setPieza(int fila, int col, Pieza pieza) {
        cuadricula[fila][col] = pieza;
    }

   
    public void limpiarCasilla(int fila, int col) {
        cuadricula[fila][col] = null;
    }

    
    public Pieza[][] getCuadricula() {
        return cuadricula;
    }

    
    public boolean esPosicionValida(int fila, int col) {
        return fila >= 0 && fila < TAMANO && col >= 0 && col < TAMANO;
    }
}
