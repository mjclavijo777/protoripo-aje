package ajedrez.piezas;


public class Reina extends Pieza {

    private static final String SIMBOLO_BLANCO = "Q";
    private static final String SIMBOLO_NEGRO  = "q";

   
    public Reina(boolean esBlanca) {
        super(esBlanca);
    }

    @Override
    public String getSimbolo() {
        return esBlanca ? SIMBOLO_BLANCO : SIMBOLO_NEGRO;
    }

    @Override
    public String getNombre() {
        return "Reina";
    }

    /**
     * La Reina se mueve en línea recta o diagonal cualquier número de casillas,
     * siempre que el camino esté libre.
     */
    @Override
    public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
        boolean esRecta    = (fo == fd || co == cd);
        boolean esDiagonal = Math.abs(fd - fo) == Math.abs(cd - co);

        if (!esRecta && !esDiagonal) {
            return false; // Dirección inválida para la Reina
        }
        return caminoLibre(fo, co, fd, cd, tablero);
    }
}
