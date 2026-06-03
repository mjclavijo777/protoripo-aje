package ajedrez.piezas;


public class Caballo extends Pieza {

    private static final String SIMBOLO_BLANCO = "C";
    private static final String SIMBOLO_NEGRO  = "c";

    
    public Caballo(boolean esBlanca) {
        super(esBlanca);
    }

    @Override
    public String getSimbolo() {
        return esBlanca ? SIMBOLO_BLANCO : SIMBOLO_NEGRO;
    }

    @Override
    public String getNombre() {
        return "Caballo";
    }

    
    @Override
    public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
        int difFila = Math.abs(fd - fo);
        int difCol  = Math.abs(cd - co);
        // Forma de L: (2,1) o (1,2)
        return (difFila == 2 && difCol == 1) || (difFila == 1 && difCol == 2);
    }
}
