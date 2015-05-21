package logica.reglas;

import logica.Ficha;
import logica.tablero.Tablero;

/**
 * Determina las reglas del juego que se esta jugando en la aplicacion.
 */
public abstract class ReglasJuego {

    /**
     * Inicia un tablero nuevo para empezar a jugar.
     *
     * @return tablero vacio,
     */
    public abstract Tablero iniciaTablero();

    /**
     * Comprueba si existe una jugada ganadora en esa fila y columna en el turno
     * dado.
     *
     * @param f fila.
     * @param c columna.
     * @param ultimo turno de la jugada.
     * @param tablero tablero sobre el que se analiza la jugada.
     * @return Ficha del ganador.
     */
    public abstract Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero);

    public abstract boolean tablas(Tablero t);

    /**
     * Devuelve el turno que tendría la siguiente jugada.
     *
     * @param ultimo turno del jugador que realizo la ultima jugada.
     * @return ficha del color al que perteneceel turno.
     */
    public Ficha siguienteTurno(Ficha ultimo) {

        if (ultimo == Ficha.BLANCAS) {
            return Ficha.NEGRAS;
        }

        return Ficha.BLANCAS;
    }

    /**
     * Devuelve a quien le pertenece el turno al inicio de la partida.
     *
     * @return ficha del color al que perteneceel turno.
     */
    public abstract Ficha jugadorInicial();
}
