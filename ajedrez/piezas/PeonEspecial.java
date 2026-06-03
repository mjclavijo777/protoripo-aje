package ajedrez.piezas;

import ajedrez.habilidad.FabricaHabilidades;
import ajedrez.habilidad.HabilidadMovimiento;
import ajedrez.habilidad.Habilidades;


public class PeonEspecial extends Pieza {

 
    private static final String PREFIJO_BLANCO = "\u001B[1;96m"; 
   
    private static final String PREFIJO_NEGRO  = "\u001B[1;91m"; 

    
    private HabilidadMovimiento habilidadActual;

 
    private final Habilidades.HabilidadPeon habilidadPeon;

   
    public PeonEspecial(boolean esBlanca) {
        super(esBlanca);
        // La habilidad inicial es la de un peón normal
        this.habilidadPeon   = new Habilidades.HabilidadPeon(esBlanca);
        this.habilidadActual = habilidadPeon;
    }

    
    public void cambiarHabilidad(String nombreHabilidad) {
        
        if ("peon".equals(nombreHabilidad.toLowerCase())) {
            this.habilidadActual = habilidadPeon;
        } else {
            this.habilidadActual = FabricaHabilidades.crear(nombreHabilidad, esBlanca);
        }
    }

   
    public String getHabilidadActual() {
        return habilidadActual.getNombre();
    }

    
    public void marcarMovido() {
        habilidadPeon.marcarMovido();
    }

    @Override
    public String getSimbolo() {
        // Símbolo especial según la habilidad activa para diferenciarlo visualmente
        return habilidadActual.getSimboloEspecial(esBlanca);
    }

    @Override
    public String getNombre() {
        return "Peón Especial (" + habilidadActual.getNombre() + ")";
    }

    /**
     * Delega la validación del movimiento a la habilidad actualmente activa.
     * Este es el patrón Strategy en acción.
     */
    @Override
    public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
        return habilidadActual.esMovimientoValido(fo, co, fd, cd, tablero);
    }
}
