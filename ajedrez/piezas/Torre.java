package ajedrez.piezas;


public class Torre extends Pieza {

    private static final String SIMBOLO_BLANCO = "T";
    private static final String SIMBOLO_NEGRO  = "t";

   
    public Torre(boolean esBlanca) {
        super(esBlanca);
    }

    @Override
    public String getSimbolo() {
        return esBlanca ? SIMBOLO_BLANCO : SIMBOLO_NEGRO;
    }

    @Override
    public String getNombre() {
        return "Torre";
    }

  
    @Override
    public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
        // Debe moverse solo en una dirección (fila o columna, no ambas)
        if (fo != fd && co != cd) {
            return false;
        }
        return caminoLibre(fo, co, fd, cd, tablero);
    }
}
