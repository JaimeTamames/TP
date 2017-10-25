package logica.reglas;

import logica.Ficha;
import logica.tablero.Tablero;

/**
 * Contiene los metodos necesarios para comprobar un 4 en linea en un tablero
 * dado.
 */
public class ReglasJuegoCuatroEnLinea {

    /**
     * Camprueba si hay cuatro fichas en línea desde la posición dada.
     *
     * @param t tablero de juego
     * @param turno turno de la jugada
     * @param f fila
     * @param c columna
     * @return true si se han dado 4 en linea y false en cc.
     */
    public static boolean cuatroEnLinea(Tablero t, Ficha turno, int f, int c) {

            return ((ReglasJuegoCuatroEnLinea.columnaValida(t, c)
                    && ReglasJuegoCuatroEnLinea.filaValida(t, f))
                    && (ReglasJuegoCuatroEnLinea.columnaConecta4(t, turno, f, c) >= 4
                    || ReglasJuegoCuatroEnLinea.filaConecta4(t, turno, f, c) >= 4
                    || ReglasJuegoCuatroEnLinea.diagonalConecta4DerToIz(t, turno, f, c) >= 4
                    || ReglasJuegoCuatroEnLinea.diagonalConecta4IzToDer(t, turno, f, c) >= 4));
            
    }
    
    public static int sumaEnLinea(Tablero t, Ficha turno, int f, int c){
    
        if (ReglasJuegoCuatroEnLinea.columnaValida(t, c)
                    && ReglasJuegoCuatroEnLinea.filaValida(t, f)){
        
            return (ReglasJuegoCuatroEnLinea.columnaConecta4(t, turno, f, c)
                    + ReglasJuegoCuatroEnLinea.filaConecta4(t, turno, f, c)
                    + ReglasJuegoCuatroEnLinea.diagonalConecta4DerToIz(t, turno, f, c)
                    + ReglasJuegoCuatroEnLinea.diagonalConecta4IzToDer(t, turno, f, c)) - 3;
        }
        
        return 0;
    }

    /**
     * Indica si la fila es valida (mayor o igual que 0 y menor el que numero de
     * filas)
     *
     * @param f fila
     * @return true si es valida y false en caso contrario
     */
    public static boolean filaValida(Tablero t, int f) {
        return (0 <= f && f < t.getAlto());
    }

    /**
     * Indica si la columna es valida (mayor o igual que 0 y menor el que numero
     * de columnas)
     *
     * @param c columna
     * @return true si es valida y false en caso contrario
     */
    public static boolean columnaValida(Tablero t, int c) {
        return (0 <= c && c < t.getAncho());
    }

    // Metodos de comprobacion de conecta4--
    /**
     * Comprueba si la ficha situada en la posicion dada conecta 4 fichas en su
     * columna.
     *
     * @param color color de la ficha dada.
     * @param fil fila donde se situa la ficha dada.
     * @param col columna donde se situa la ficha dada.
     * @return true si se conectan 4 fichas, false en caso contrario.
     */
    private static int columnaConecta4(Tablero t, Ficha color, int fil, int col) {

        if (!ReglasJuegoCuatroEnLinea.columnaValida(t, col)) {
            return 0;
        }

        int filaInit = fil + 1;
        int enLinea = 1;

        while (ReglasJuegoCuatroEnLinea.filaValida(t, filaInit)
                && (t.getFicha(filaInit, col) == color)) {
            enLinea++;
            filaInit++;
        }
        filaInit = fil - 1;
        while (ReglasJuegoCuatroEnLinea.filaValida(t, filaInit)
                && (t.getFicha(filaInit, col) == color)) {
            enLinea++;
            filaInit--;
        }

        return enLinea;
    }

    /**
     * Comprueba si la ficha situada en la posicion dada conecta 4 fichas en su
     * fila.
     *
     * @param color color de la ficha dada.
     * @param fil fila donde se situa la ficha dada.
     * @param col columna donde se situa la ficha dada.
     * @return true si se conectan 4 fichas, false en caso contrario.
     */
    private static int filaConecta4(Tablero t, Ficha color, int fil, int col) {

        if (!ReglasJuegoCuatroEnLinea.filaValida(t, fil)) {
            return 0;
        }

        int columnaInit = col + 1;
        int enLinea = 1;

        while (ReglasJuegoCuatroEnLinea.columnaValida(t, columnaInit)
                && (t.getFicha(fil, columnaInit) == color)) {
            enLinea++;
            columnaInit++;
        }
        columnaInit = col - 1;
        while (ReglasJuegoCuatroEnLinea.columnaValida(t, columnaInit)
                && (t.getFicha(fil, columnaInit) == color)) {
            enLinea++;
            columnaInit--;
        }

        return enLinea;
    }

    /**
     * Comprueba si la ficha situada en la posicion dada conecta 4 fichas en su
     * diagonal de derecha a izquierda.
     *
     * @param color color de la ficha dada.
     * @param fil fila donde se situa la ficha dada.
     * @param col columna donde se situa la ficha dada.
     * @return true si se conectan 4 fichas, false en caso contrario.
     */
    private static int diagonalConecta4DerToIz(Tablero t, Ficha color, int fil, int col) {

        int columnaInit = col + 1;
        int filaInit = fil + 1;
        int enLinea = 1;
        while (ReglasJuegoCuatroEnLinea.filaValida(t, filaInit) && ReglasJuegoCuatroEnLinea.columnaValida(t, columnaInit)
                && (t.getFicha(filaInit, columnaInit) == color)) {
            enLinea++;
            columnaInit++;
            filaInit++;
        }
        filaInit = fil - 1;
        columnaInit = col - 1;
        while (ReglasJuegoCuatroEnLinea.filaValida(t, filaInit) && ReglasJuegoCuatroEnLinea.columnaValida(t, columnaInit)
                && (t.getFicha(filaInit, columnaInit) == color)) {
            enLinea++;
            columnaInit--;
            filaInit--;
        }

        return enLinea;

    }

    /**
     * Comprueba si la ficha situada en la posicion dada conecta 4 fichas en su
     * diagonal de izquierda derecha.
     *
     * @param color color de la ficha dada.
     * @param fil fila donde se situa la ficha dada.
     * @param col columna donde se situa la ficha dada.
     * @return true si se conectan 4 fichas, false en caso contrario.
     */
    private static int diagonalConecta4IzToDer(Tablero t, Ficha color, int fil, int col) {

        int columnaInit = col + 1;
        int filaInit = fil - 1;
        int enLinea = 1;

        while (ReglasJuegoCuatroEnLinea.filaValida(t, filaInit) && ReglasJuegoCuatroEnLinea.columnaValida(t, columnaInit)
                && (t.getFicha(filaInit, columnaInit) == color)) {
            enLinea++;
            columnaInit++;
            filaInit--;
        }
        filaInit = fil + 1;
        columnaInit = col - 1;
        while (ReglasJuegoCuatroEnLinea.filaValida(t, filaInit) && ReglasJuegoCuatroEnLinea.columnaValida(t, columnaInit)
                && (t.getFicha(filaInit, columnaInit) == color)) {
            enLinea++;
            columnaInit--;
            filaInit++;
        }

        return enLinea;
    }
    
    

}
