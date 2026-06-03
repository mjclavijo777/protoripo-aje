package ajedrez.habilidad;

import ajedrez.piezas.Pieza;


public interface HabilidadMovimiento {

   
    boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero);

    
    String getNombre();

    
    String getSimboloEspecial(boolean esBlanca);
}
