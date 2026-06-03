package ajedrez.movimiento;

import ajedrez.piezas.*;
import ajedrez.tablero.Tablero;


public class ServicioMovimiento {

    
    public static class ResultadoMovimiento {

       
        public final boolean exito;

      
        public final String mensaje;

       
        public final boolean capturoRey;

      
        public ResultadoMovimiento(boolean exito, String mensaje, boolean capturoRey) {
            this.exito      = exito;
            this.mensaje    = mensaje;
            this.capturoRey = capturoRey;
        }
    }

    
    public ResultadoMovimiento mover(Tablero tablero, int fo, int co, int fd, int cd, boolean turnoBlanco) {

       
        if (!tablero.esPosicionValida(fo, co) || !tablero.esPosicionValida(fd, cd)) {
            return fallo("Posicion fuera del tablero.");
        }

        Pieza pieza = tablero.getPieza(fo, co);
        if (pieza == null) {
            return fallo("No hay pieza en esa casilla.");
        }
        if (pieza.esBlanca() != turnoBlanco) {
            return fallo("Esa pieza no te pertenece.");
        }

        Pieza destino = tablero.getPieza(fd, cd);
        if (destino != null && destino.esBlanca() == turnoBlanco) {
            return fallo("No puedes capturar tu propia pieza.");
        }
        if (!pieza.esMovimientoValido(fo, co, fd, cd, tablero.getCuadricula())) {
            return fallo("Movimiento invalido para " + pieza.getNombre() + ".");
        }

        // --- Ejecutar el movimiento ---
        tablero.setPieza(fd, cd, pieza);
        tablero.limpiarCasilla(fo, co);

       
        actualizarEstadoPeon(pieza);

     
        String capturaInfo = (destino != null) ? " | Captura: " + destino.getNombre() : "";
        boolean esVictoria = (destino instanceof Rey);

        return new ResultadoMovimiento(
            true,
            "Movimiento ejecutado" + capturaInfo,
            esVictoria
        );
    }

   
    private void actualizarEstadoPeon(Pieza pieza) {
        if (pieza instanceof PeonEspecial) {
            ((PeonEspecial) pieza).marcarMovido();
        } else if (pieza instanceof Peon) {
            ((Peon) pieza).marcarMovido();
        }
    }

    private ResultadoMovimiento fallo(String mensaje) {
        return new ResultadoMovimiento(false, mensaje, false);
    }
}
