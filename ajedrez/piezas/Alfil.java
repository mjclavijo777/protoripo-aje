package ajedrez.piezas;


public class Alfil extends Pieza {

    private static final String SIMBOLO_BLANCO = "A";
    private static final String SIMBOLO_NEGRO  = "a";

    
    public Alfil(boolean esBlanca) {
        super(esBlanca);
    }

    @Override
    public String getSimbolo() {
        return esBlanca ? SIMBOLO_BLANCO : SIMBOLO_NEGRO;
    }

    @Override
    public String getNombre() {
        return "Alfil";
    }

   
    @Override
    public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
        // Movimiento diagonal: igual diferencia en fila y columna
        if (Math.abs(fd - fo) != Math.abs(cd - co)) {
            return false;
        }
        return caminoLibre(fo, co, fd, cd, tablero);
    }
}
