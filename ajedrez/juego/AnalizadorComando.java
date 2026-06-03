package ajedrez.juego;


public class AnalizadorComando {

    
    public enum TipoComando {
        /** Mover una pieza: mover <orig> <dest> */
        MOVER,
        /** Cambiar habilidad del peón especial: habilidad <pos> <hab> */
        HABILIDAD,
        /** Mostrar la ayuda */
        AYUDA,
        /** Salir del juego */
        SALIR,
        /** Comando no reconocido o con formato incorrecto */
        DESCONOCIDO
    }

    
    public static class ComandoJuego {

        /** Tipo de acción que representa este comando */
        public final TipoComando tipo;

        /** Argumentos extraídos del comando (posiciones, nombres, etc.) */
        public final String[] args;

       
        public ComandoJuego(TipoComando tipo, String... args) {
            this.tipo = tipo;
            this.args = args;
        }
    }

    
    public ComandoJuego analizar(String lineaEntrada) {
        if (lineaEntrada == null || lineaEntrada.isBlank()) {
            return new ComandoJuego(TipoComando.DESCONOCIDO);
        }

        // Normalizar: minúsculas y quitar espacios extremos
        String[] partes = lineaEntrada.trim().toLowerCase().split("\\s+");

        switch (partes[0]) {
            case "mover":
            case "m":
                // Requiere exactamente: mover <origen> <destino>
                if (partes.length < 3) {
                    return new ComandoJuego(TipoComando.DESCONOCIDO,
                        "Uso: mover <origen> <destino>  (ej: mover e2 e4)");
                }
                return new ComandoJuego(TipoComando.MOVER, partes[1], partes[2]);

            case "habilidad":
            case "h":
                // Requiere: habilidad <posición> <nombre>
                if (partes.length < 3) {
                    return new ComandoJuego(TipoComando.DESCONOCIDO,
                        "Uso: habilidad <casilla> <habilidad>  (ej: habilidad d2 reina)");
                }
                return new ComandoJuego(TipoComando.HABILIDAD, partes[1], partes[2]);

            case "ayuda":
            case "help":
                return new ComandoJuego(TipoComando.AYUDA);

            case "salir":
            case "exit":
            case "quit":
                return new ComandoJuego(TipoComando.SALIR);

            default:
                // Atajo: si son dos tokens de 2 caracteres, se trata como "mover <orig> <dest>"
                if (partes.length == 2 && partes[0].length() == 2 && partes[1].length() == 2) {
                    return new ComandoJuego(TipoComando.MOVER, partes[0], partes[1]);
                }
                return new ComandoJuego(TipoComando.DESCONOCIDO,
                    "Comando desconocido. Escribe 'ayuda' para ver los comandos disponibles.");
        }
    }
}
