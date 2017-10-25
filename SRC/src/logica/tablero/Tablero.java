package logica.tablero;

import logica.Constantes;
import logica.Ficha;

/**
 * Representacion del estado de un tablero.
 */
public class Tablero implements TableroSoloLectura {

    // Almacena el tipo de ficha que contine cada posicion del tablero
    private Ficha[][] tablero;
    private int alto;
    private int ancho;
    private int numFichas;

    /**
     * Constructora por defecto. Asigna la dimension por defecto del tablero y
     * lo configura en su estado inicial; Tablero de 8x8.
     *
     * @param alto alto del tablero
     * @param ancho ancho del tablero
     */
    public Tablero(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;

        this.tablero = new Ficha[this.alto][this.ancho];
        this.reset();
    }

    @Override
    public int getAlto() {
        return this.alto;
    }

    @Override
    public int getAncho() {
        return this.ancho;
    }

    @Override
    public Ficha getFicha(int fil, int col) {

        try {
            return this.tablero[fil][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(fil + " / " + col);
        }

        return this.tablero[fil][col];
    }

    /**
     * Comprueba si el tablero esta lleno de fichas o no.
     *
     * @return true si el tablero esta lleno (numFichas es igual a todas las
     * casillas del tablero).
     */
    public boolean isCompleto() {
        return this.numFichas >= this.alto * this.ancho;
    }

    // Metodos de la clase--
    /**
     * Inserta una ficha del color dado en la posicion dada en el tablero.
     *
     * @param fil fila donde insetar la ficha.
     * @param col columna donde insetar la ficha.
     * @param color color de la ficha a insertar.
     */
    public void ponerFicha(int fil, int col, Ficha color) {

        this.tablero[fil][col] = color;

        if (color == Ficha.VACIA) {
            numFichas--;
        } else {
            numFichas++;
        }
    }

    /**
     * Voltea una ficha del color dado en la posicion dada en el tablero.
     *
     * @param fil fila donde voltea la ficha.
     * @param col columna donde voltea la ficha.
     * @param color color de la ficha a voltear.
     */
    public void voltearFicha(int fil, int col, Ficha color) {

        this.tablero[fil][col] = color;

        if (color == Ficha.VACIA) {
            numFichas--;
        }
    }

    /**
     * Asgina el valor vacio a todas las posiciones del tablero. Devuelve el
     * valor 0 al numero de fichas en el tablero.
     */
    public void reset() {
        for (int fil = 0; fil < this.alto; fil++) {
            for (int col = 0; col < this.ancho; col++) {
                this.tablero[fil][col] = Ficha.VACIA;
            }
        }

        numFichas = 0;
    }

    /**
     * Devuelve una cadena de texto que contiene el tablero pintado.
     *
     * @return cadena de texto con el contenido del tablero
     */
    private String representarTablero() {
        String resultado = "\t ";

        for (int c = 0; c < this.ancho; c++) {
            resultado += " " + (c + 1) + " ";
        }

        resultado += Constantes.SALTO_LINEA;
        resultado += "     \t ";

        for (int c = 0; c < this.ancho; c++) {
            resultado += " _ ";
        }

        resultado += Constantes.SALTO_LINEA;

        for (int f = this.alto - 1; f >= 0; f--) {

            resultado += (" " + (f + 1) + "\t|");

            for (int c = 0; c < this.ancho; c++) {

                if (this.tablero[f][c] == Ficha.BLANCAS) {
                    resultado += " B ";
                } else if (this.tablero[f][c] == Ficha.NEGRAS) {
                    resultado += " N ";
                } else {
                    resultado += " O ";
                }
            }
            resultado += Constantes.SALTO_LINEA;
        }

        return resultado;
    }

    @Override
    public String toString() {
        return this.representarTablero();
    }

}
