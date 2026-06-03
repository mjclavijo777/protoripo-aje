package ajedrez.habilidad;

import ajedrez.piezas.*;


public class Habilidades {

   
    public static class HabilidadPeon implements HabilidadMovimiento {

        /** Pieza interna delegada para la logica de movimiento */
        private final Peon peonInterno;

        
        public HabilidadPeon(boolean esBlanca) {
            this.peonInterno = new Peon(esBlanca);
        }

        /** Permite marcar que el peon ya se movio (para el avance doble) */
        public void marcarMovido() {
            peonInterno.marcarMovido();
        }

        @Override
        public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
            return peonInterno.esMovimientoValido(fo, co, fd, cd, tablero);
        }

        @Override
        public String getNombre() { return "peon"; }

        @Override
        public String getSimboloEspecial(boolean esBlanca) {
            // [P] = peon especial blanco, [p] = peon especial negro
            return esBlanca ? "[P]" : "[p]";
        }
    }

   
    public static class HabilidadCaballo implements HabilidadMovimiento {

        private final Caballo caballoInterno;

        public HabilidadCaballo(boolean esBlanca) {
            this.caballoInterno = new Caballo(esBlanca);
        }

        @Override
        public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
            return caballoInterno.esMovimientoValido(fo, co, fd, cd, tablero);
        }

        @Override
        public String getNombre() { return "caballo"; }

        @Override
        public String getSimboloEspecial(boolean esBlanca) {
            return esBlanca ? "[C]" : "[c]";
        }
    }

    
    /**
     * Habilidad que otorga al Peon Especial el movimiento de la Torre.
     */
    public static class HabilidadTorre implements HabilidadMovimiento {

        private final Torre torreInterna;

        public HabilidadTorre(boolean esBlanca) {
            this.torreInterna = new Torre(esBlanca);
        }

        @Override
        public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
            return torreInterna.esMovimientoValido(fo, co, fd, cd, tablero);
        }

        @Override
        public String getNombre() { return "torre"; }

        @Override
        public String getSimboloEspecial(boolean esBlanca) {
            return esBlanca ? "[T]" : "[t]";
        }
    }

    
    /**
     * Habilidad que otorga al Peon Especial el movimiento de la Reina.
     * Es la mas poderosa: mueve en cualquier direccion sin limite.
     */
    public static class HabilidadReina implements HabilidadMovimiento {

        private final Reina reinaInterna;

        public HabilidadReina(boolean esBlanca) {
            this.reinaInterna = new Reina(esBlanca);
        }

        @Override
        public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
            return reinaInterna.esMovimientoValido(fo, co, fd, cd, tablero);
        }

        @Override
        public String getNombre() { return "reina"; }

        @Override
        public String getSimboloEspecial(boolean esBlanca) {
            return esBlanca ? "[Q]" : "[q]";
        }
    }

   
    /**
     * Habilidad que otorga al Peon Especial el movimiento del Alfil.
     */
    public static class HabilidadAlfil implements HabilidadMovimiento {

        private final Alfil alfilInterno;

        public HabilidadAlfil(boolean esBlanca) {
            this.alfilInterno = new Alfil(esBlanca);
        }

        @Override
        public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
            return alfilInterno.esMovimientoValido(fo, co, fd, cd, tablero);
        }

        @Override
        public String getNombre() { return "alfil"; }

        @Override
        public String getSimboloEspecial(boolean esBlanca) {
            return esBlanca ? "[A]" : "[a]";
        }
    }

 
    /**
     * Habilidad que otorga al Peon Especial el movimiento del Rey.
     */
    public static class HabilidadRey implements HabilidadMovimiento {

        private final Rey reyInterno;

        public HabilidadRey(boolean esBlanca) {
            this.reyInterno = new Rey(esBlanca);
        }

        @Override
        public boolean esMovimientoValido(int fo, int co, int fd, int cd, Pieza[][] tablero) {
            return reyInterno.esMovimientoValido(fo, co, fd, cd, tablero);
        }

        @Override
        public String getNombre() { return "rey"; }

        @Override
        public String getSimboloEspecial(boolean esBlanca) {
            return esBlanca ? "[K]" : "[k]";
        }
    }
}
