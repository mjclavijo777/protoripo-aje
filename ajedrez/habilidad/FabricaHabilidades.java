package ajedrez.habilidad;

public class FabricaHabilidades {

    /**
     * Lista de nombres válidos de habilidades disponibles.
     * Se usa para validación y mostrar ayuda al jugador.
     */
    public static final String[] HABILIDADES_DISPONIBLES = {
        "peon", "caballo", "torre", "reina", "alfil", "rey"
    };

    
    public static HabilidadMovimiento crear(String nombre, boolean esBlanca) {
        switch (nombre.toLowerCase()) {
            case "peon":    return new Habilidades.HabilidadPeon(esBlanca);
            case "caballo": return new Habilidades.HabilidadCaballo(esBlanca);
            case "torre":   return new Habilidades.HabilidadTorre(esBlanca);
            case "reina":   return new Habilidades.HabilidadReina(esBlanca);
            case "alfil":   return new Habilidades.HabilidadAlfil(esBlanca);
            case "rey":     return new Habilidades.HabilidadRey(esBlanca);
            default:
                throw new IllegalArgumentException(
                    "Habilidad desconocida: '" + nombre + "'. " +
                    "Opciones válidas: peon, caballo, torre, reina, alfil, rey"
                );
        }
    }

   
    public static boolean esValida(String nombre) {
        if (nombre == null) return false;
        for (String h : HABILIDADES_DISPONIBLES) {
            if (h.equals(nombre.toLowerCase())) return true;
        }
        return false;
    }
}
