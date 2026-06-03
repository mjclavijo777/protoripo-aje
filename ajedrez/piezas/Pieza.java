package ajedrez.piezas;


public abstract class Pieza {

    /** Indica si la pieza pertenece al equipo blanco */
    protected final boolean esBlanca;

  
    public Pieza(boolean esBlanca) {
        this.esBlanca = esBlanca;
    }

   
    public boolean esBlanca() {
        return esBlanca;
    }

    public abstract String getSimbolo();

    
    public abstract String getNombre();

   
    public abstract boolean esMovimientoValido(
        int filaOrigen, int colOrigen,
        int filaDestino, int colDestino,
        Pieza[][] tablero
    );

  
    protected boolean caminoLibre(int fo, int co, int fd, int cd, Pieza[][] tablero) {
        // Calculamos la dirección de avance en fila y columna
        int dirFila = Integer.compare(fd, fo);
        int dirCol  = Integer.compare(cd, co);

        int f = fo + dirFila;
        int c = co + dirCol;

        // Recorremos cada casilla intermedia (sin incluir origen ni destino)
        while (f != fd || c != cd) {
            if (tablero[f][c] != null) {
                return false; // Hay una pieza bloqueando
            }
            f += dirFila;
            c += dirCol;
        }
        return true;
    }
}
