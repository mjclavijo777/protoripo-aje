package ajedrez.piezas;


public class Peon extends Pieza {

    private static final String SIMBOLO_BLANCO = "P";
    private static final String SIMBOLO_NEGRO  = "p";

   
    private boolean yaSeMovio = false;

    
    public Peon(boolean esBlanca) {
        super(esBlanca);
    }

    
    public void marcarMovido() {
        this.yaSeMovio = true;
    }

   
    public boolean yaSeMovio() {
        return yaSeMovio;
    }

    @Override
    public String getSimbolo() {
        return esBlanca ? SIMBOLO_BLANCO : SIMBOLO_NEGRO;
    }

    @Override
    public String getNombre() {
        return "Peón";
    }

    /**
     * Reglas del Peón:
     * - Avanza 1 casilla hacia adelante (blancas suben, negras bajan).
     * - En su primer movimiento puede avanzar 2 casillas si el camino está libre.
     * - Captura en diagonal (1 casilla) solo si hay pieza enemiga.
     */
    @Override
    public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
        // Dirección: blancas avanzan hacia arriba (fila decrece), negras hacia abajo
        int direccion = esBlanca ? -1 : 1;

        // --- Avance normal (misma columna) ---
        if (co == cd && tablero[fd][cd] == null) {
            // Avance de una casilla
            if (fd == fo + direccion) {
                return true;
            }
            // Avance de dos casillas solo en el primer movimiento
            if (!yaSeMovio && fd == fo + 2 * direccion && tablero[fo + direccion][co] == null) {
                return true;
            }
        }

        // --- Captura diagonal ---
        if (fd == fo + direccion && Math.abs(cd - co) == 1 && tablero[fd][cd] != null) {
            return true;
        }

        return false;
    }
}
