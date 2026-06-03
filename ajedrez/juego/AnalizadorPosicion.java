package ajedrez.juego;


public class AnalizadorPosicion {

    
    public static int[] convertir(String notacion) {
        if (notacion == null || notacion.length() != 2) {
            return null;
        }

        char letra  = notacion.charAt(0);
        char numero = notacion.charAt(1);

        // La columna viene de la letra: 'a' → 0, 'h' → 7
        int col = letra - 'a';
        // La fila se invierte: '8' → 0 (top), '1' → 7 (bottom)
        int fila = 8 - (numero - '0');

        // Verificar que esté dentro del rango del tablero
        if (col < 0 || col > 7 || fila < 0 || fila > 7) {
            return null;
        }

        return new int[]{fila, col};
    }

    
    public static String aNotacion(int fila, int col) {
        char letra  = (char) ('a' + col);
        char numero = (char) ('0' + (8 - fila));
        return "" + letra + numero;
    }
}
