package ajedrez;

import ajedrez.juego.ControladorJuego;
import ajedrez.movimiento.ServicioHabilidad;
import ajedrez.movimiento.ServicioMovimiento;
import ajedrez.renderizado.RenderizadorConsola;
import ajedrez.renderizado.RenderizadorTablero;
import ajedrez.tablero.Tablero;

public class Main {

   
    public static void main(String[] args) {

        // --- Crear componentes individuales ---
        Tablero             tablero            = new Tablero();
        RenderizadorTablero renderizador       = new RenderizadorConsola();
        ServicioMovimiento  servicioMovimiento = new ServicioMovimiento();
        ServicioHabilidad   servicioHabilidad  = new ServicioHabilidad();

        // --- Inyectar dependencias en el controlador (DIP) ---
        ControladorJuego controlador = new ControladorJuego(
            tablero,
            renderizador,
            servicioMovimiento,
            servicioHabilidad
        );

        
        controlador.iniciar();
    }
}
