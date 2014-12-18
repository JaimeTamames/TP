package logica;

import logica.movimiento.Movimiento;
import logica.reglas.ReglasJuego;

/**
 * Representa el estado de una partida de conecta4
 */
public class Partida {

    // Almacena el tablero de la partida
    private Tablero tablero;
    // Almacena el turno actual de la partida
    private Ficha turno;
    // Almacena el estado de la partida, si continua o esta finalizada
    private boolean terminada;
    // Almacena al ganador de la partida
    private Ficha ganador;
    // Almacena las jugadas que se realizan en la partida
    private Pila jugadas;
    // Almacena las reglas del juego en ejecucion en la partida
    private ReglasJuego reglas;

    /**
     * Constructora por defecto. Inicializa todos los atributos de la partida y
     * los configura a su estado inicial.
     */
    public Partida(ReglasJuego reglas) {
        this.reglas = reglas;
        this.tablero = reglas.iniciaTablero();
        this.turno = Ficha.BLANCA;
        this.terminada = false;
        this.ganador = Ficha.VACIA;
        this.jugadas = new Pila();
    }

    public boolean isTerminada() {
        return terminada;
    }

    public Ficha getGanador() {
        return ganador;
    }

    public Ficha getTurno() {
        return turno;
    }

    public ReglasJuego getReglas() {
        return reglas;
    }

    /**
     * Cambia el turno de la partida
     */
    private void cambiarTurno() {
        this.turno = this.reglas.siguienteTurno(this.turno);
    }

    /**
     * Ejecuta un movimiento en el tablero. La jugada queda registrada en la
     * pila.
     *
     * @return true si la jugada se ha realizado con exito, false en caso
     * contrario
     */
    public boolean ejecutaMovimiento(Movimiento mv) {
        if (mv.ejecutaMovimiento(this.tablero)) {

            // Apilamos la jugada
            this.jugadas.apilar(mv);

            // Comprobamos si hay ganador
            this.ganador = reglas.hayGanador(mv.getFila(), mv.getColumna(), this.turno, this.tablero);
            // Terminamos la partida si hay tablas o existe un ganador
            this.terminada = reglas.tablas(this.tablero) || this.ganador != Ficha.VACIA;

            // Si no sucede nada de esto se cambia el turno
            if (!this.terminada) {
                this.cambiarTurno();
            }

            return true;
        }

        return false;
    }

    /**
     * Deshace la ultima jugada de la partida.
     *
     * @return true si se ha realizado el deshacer con exito false en caso
     * contrario.
     */
    public boolean deshacer() {
        Movimiento m = this.jugadas.desapilar();
        if (m != null) {
            m.undo(this.tablero);
            return true;
        }

        return false;
    }

    /**
     * Devuelve la partida a su estado inicial.
     */
    public void resetear(ReglasJuego reglas) {
        this.reglas = reglas;
        this.tablero.reset();
        this.turno = Ficha.BLANCA;
        this.terminada = false;
        this.ganador = Ficha.VACIA;
        this.jugadas.reset();
    }

    @Override
    public String toString() {
        return ("Turno de: "
                + Constantes.SALTO_LINEA
                + this.tablero.toString());
    }
}
