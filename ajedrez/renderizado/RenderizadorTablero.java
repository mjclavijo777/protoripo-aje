package ajedrez.renderizado;

import ajedrez.piezas.PeonEspecial;
import ajedrez.tablero.Tablero;


public interface RenderizadorTablero {

    
    void mostrarTablero(Tablero tablero, int resaltFila, int resaltCol);

   
    void mostrarLeyenda(PeonEspecial peonEspecialBlanco, PeonEspecial peonEspecialNegro);

   
    void mostrarBienvenida();

    
    void mostrarAyuda();

  
    void mostrarTurno(boolean turnoBlanco);

   
    void mostrarError(String mensaje);

    
    void mostrarMensaje(String mensaje);

    
    void mostrarVictoria(boolean ganaronBlancas);
}
