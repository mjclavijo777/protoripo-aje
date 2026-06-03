package ajedrez.movimiento;

import ajedrez.habilidad.FabricaHabilidades;
import ajedrez.piezas.Pieza;
import ajedrez.piezas.PeonEspecial;
import ajedrez.tablero.Tablero;


public class ServicioHabilidad {

    
    public static class ResultadoHabilidad {

     
        public final boolean exito;

      
        public final String mensaje;

      
        public ResultadoHabilidad(boolean exito, String mensaje) {
            this.exito   = exito;
            this.mensaje = mensaje;
        }
    }

   
    public ResultadoHabilidad cambiarHabilidad(
            Tablero tablero, int fila, int col,
            String nombreHabilidad, boolean turnoBlanco) {

       
        if (!tablero.esPosicionValida(fila, col)) {
            return fallo("Posicion fuera del tablero.");
        }

        Pieza pieza = tablero.getPieza(fila, col);

        if (pieza == null) {
            return fallo("No hay pieza en esa casilla.");
        }
        if (!(pieza instanceof PeonEspecial)) {
            return fallo("Solo el Peon Especial puede cambiar de habilidad.");
        }
        if (pieza.esBlanca() != turnoBlanco) {
            return fallo("Ese peon especial no te pertenece.");
        }
        if (!FabricaHabilidades.esValida(nombreHabilidad)) {
            return fallo("Habilidad invalida. Opciones: peon, caballo, torre, reina, alfil, rey");
        }

   
        PeonEspecial peonEspecial = (PeonEspecial) pieza;
        String habilidadAnterior  = peonEspecial.getHabilidadActual();
        peonEspecial.cambiarHabilidad(nombreHabilidad);

        return new ResultadoHabilidad(
            true,
            "Peon Especial: " + habilidadAnterior + " -> " + nombreHabilidad
        );
    }

   
    private ResultadoHabilidad fallo(String mensaje) {
        return new ResultadoHabilidad(false, mensaje);
    }
}
