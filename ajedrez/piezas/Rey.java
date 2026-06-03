package ajedrez.piezas;


public class Rey extends Pieza {

    /** Símbolo unicode según color */
    private static final String SIMBOLO_BLANCO = "K";
    private static final String SIMBOLO_NEGRO  = "k";

   
    public Rey(boolean esBlanca) {
        super(esBlanca);
    }

    @Override
    public String getSimbolo() {
        return esBlanca ? SIMBOLO_BLANCO : SIMBOLO_NEGRO;
    }

    @Override
    public String getNombre() {
        return "Rey";
    }

    /**
     * El Rey se mueve exactamente una casilla en cualquier dirección.
     */
    @Override
    public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
        int difFila = Math.abs(fd - fo);
        int difCol  = Math.abs(cd - co);
        // Debe moverse al menos una casilla y máximo una en cada dirección
        return difFila <= 1 && difCol <= 1 && !(difFila == 0 && difCol == 0);
    }
}
