package ajedrez.juego;

import ajedrez.movimiento.ServicioHabilidad;
import ajedrez.movimiento.ServicioMovimiento;
import ajedrez.piezas.Pieza;
import ajedrez.piezas.PeonEspecial;
import ajedrez.renderizado.RenderizadorTablero;
import ajedrez.tablero.Tablero;
import java.util.Scanner;


public class ControladorJuego {

    // --- Dependencias inyectadas (DIP) ---
    private final Tablero              tablero;
    private final RenderizadorTablero  renderizador;
    private final ServicioMovimiento   servicioMovimiento;
    private final ServicioHabilidad    servicioHabilidad;
    private final AnalizadorComando    analizadorComando;

    /** Indica de qué equipo es el turno actual */
    private boolean turnoBlanco = true;

    /** Referencias a los peones especiales para mostrar en la leyenda */
    private PeonEspecial peonEspecialBlanco = null;
    private PeonEspecial peonEspecialNegro  = null;

    // -------------------------------------------------------------------------

   
    public ControladorJuego(
            Tablero tablero,
            RenderizadorTablero renderizador,
            ServicioMovimiento servicioMovimiento,
            ServicioHabilidad servicioHabilidad) {
        this.tablero            = tablero;
        this.renderizador       = renderizador;
        this.servicioMovimiento = servicioMovimiento;
        this.servicioHabilidad  = servicioHabilidad;
        this.analizadorComando  = new AnalizadorComando();
    }

    

    /**
     * Inicia y mantiene el loop principal del juego hasta que alguien gane o salga.
     */
    public void iniciar() {
        tablero.inicializar();
        localizarPeonesEspeciales();
        renderizador.mostrarBienvenida();

        Scanner entrada = new Scanner(System.in);

        while (true) {
            // Mostrar estado actual del juego
            renderizador.mostrarTablero(tablero, -1, -1);
            renderizador.mostrarLeyenda(peonEspecialBlanco, peonEspecialNegro);
            renderizador.mostrarTurno(turnoBlanco);

            // Leer yanalizar comando del jugador
            String lineaEntrada = entrada.nextLine();
            AnalizadorComando.ComandoJuego comando = analizadorComando.analizar(lineaEntrada);

            // Procesar el comando
            boolean juegoTerminado = procesarComando(comando);
            if (juegoTerminado) {
                break;
            }
        }
        entrada.close();
    }

   
    private boolean procesarComando(AnalizadorComando.ComandoJuego comando) {
        switch (comando.tipo) {
            case MOVER:
                return manejarMover(comando.args[0], comando.args[1]);

            case HABILIDAD:
                manejarHabilidad(comando.args[0], comando.args[1]);
                return false;

            case AYUDA:
                renderizador.mostrarAyuda();
                return false;

            case SALIR:
                renderizador.mostrarMensaje("Hasta luego!");
                return true;

            default:
                // args[0] puede contener el mensaje de error si existe
                String msgError = (comando.args != null && comando.args.length > 0)
                    ? comando.args[0]
                    : "Comando no reconocido.";
                renderizador.mostrarError(msgError);
                return false;
        }
    }

    
    private boolean manejarMover(String origenStr, String destinoStr) {
        int[] origen  = AnalizadorPosicion.convertir(origenStr);
        int[] destino = AnalizadorPosicion.convertir(destinoStr);

        if (origen == null || destino == null) {
            renderizador.mostrarError("Posicion invalida. Ejemplo: e2 o a4");
            return false;
        }

        ServicioMovimiento.ResultadoMovimiento resultado = servicioMovimiento.mover(
            tablero, origen[0], origen[1], destino[0], destino[1], turnoBlanco
        );

        if (!resultado.exito) {
            renderizador.mostrarError(resultado.mensaje);
            return false;
        }

        // Movimento exitoso
        renderizador.mostrarMensaje(origenStr + " -> " + destinoStr + "  |  " + resultado.mensaje);

        // Verificar victoria
        if (resultado.capturoRey) {
            renderizador.mostrarTablero(tablero, -1, -1);
            renderizador.mostrarVictoria(turnoBlanco);
            return true;
        }

        // Actualizar peones especiales en caso de que se hayan movido
        localizarPeonesEspeciales();
        // Cambiar de turno
        turnoBlanco = !turnoBlanco;
        return false;
    }

    private void manejarHabilidad(String posStr, String nombreHabilidad) {
        int[] pos = AnalizadorPosicion.convertir(posStr);

        if (pos == null) {
            renderizador.mostrarError("Posicion invalida. Ejemplo: d2");
            return;
        }

        ServicioHabilidad.ResultadoHabilidad resultado = servicioHabilidad.cambiarHabilidad(
            tablero, pos[0], pos[1], nombreHabilidad, turnoBlanco
        );

        if (!resultado.exito) {
            renderizador.mostrarError(resultado.mensaje);
            return;
        }

        // El cambio de habilidad consume el turno
        renderizador.mostrarMensaje("[*] " + resultado.mensaje + "  (turno consumido)");
        turnoBlanco = !turnoBlanco;
    }

 
    private void localizarPeonesEspeciales() {
        peonEspecialBlanco = null;
        peonEspecialNegro  = null;

        for (int fila = 0; fila < Tablero.TAMANO; fila++) {
            for (int col = 0; col < Tablero.TAMANO; col++) {
                Pieza pieza = tablero.getPieza(fila, col);
                if (pieza instanceof PeonEspecial) {
                    if (pieza.esBlanca()) {
                        peonEspecialBlanco = (PeonEspecial) pieza;
                    } else {
                        peonEspecialNegro = (PeonEspecial) pieza;
                    }
                }
            }
        }
    }
}
