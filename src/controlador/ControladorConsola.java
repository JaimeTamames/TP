package controlador;

import logica.movimiento.Movimiento;
import java.util.Scanner;
import logica.Constantes;
import logica.Ficha;
import logica.Partida;
import logica.factorias.FactoriaJuego;
import logica.jugadores.Jugador;
import logica.parser.Comando;
import logica.parser.ParserAyudaComandos;
import view.VistaConsola;

/**
 * Controla la ejecucion de la aplicacion. Pregunta al usuario que quiere hacer
 * y actualiza la partida en torno a lo que indique.
 *
 */
public class ControladorConsola {

    private Partida partida;
    private Scanner in;
    private Jugador jugadorB;
    private Jugador jugadorN;
    private FactoriaJuego factoria;
    private boolean fin;

    /**
     *
     * Instancia los parametros de la clase dando asi comienzo al juego
     * (conecta4 por defecto).
     *
     * @param f factoria de juego
     * @param in lector de la entrada estandar
     * @param p partida
     */
    public ControladorConsola(FactoriaJuego f, Scanner in, Partida p) {
        this.factoria = f;
        this.in = in;
        this.partida = p;
        this.partida.addObservador(new VistaConsola());
        this.jugadorB = f.crearJugadorHumano(in);
        this.jugadorN = f.crearJugadorHumano(in);
        this.fin = false;
    }

    /**
     * Realiza la simulacion del juego, solicitando comandos al usuario hasta
     * que este finalice. Una ves se finaliza le informa al usuario del
     * resultado final.
     */
    public void run() {
        while (!this.fin) {
            System.out.println(this.partida.getTablero().toString() + Constantes.SALTO_LINEA + "Turno de: " + this.partida.getTurno());
            System.out.println("Que quieres hacer? ('ayuda' para ver opciones): ");

            Comando cmd = ParserAyudaComandos.parsea(this.in.nextLine().split(" "));

            if (cmd != null) {

                cmd.execute(this);

            }
        }
    }

    public void reset(FactoriaJuego factoria) {
        this.factoria = factoria;
        this.partida.resetear(this.factoria.crearReglas());
        this.jugadorB = this.factoria.crearJugadorHumano(in);
        this.jugadorN = this.factoria.crearJugadorHumano(in);
        this.fin = false;
    }

    public void undo() {
        this.partida.deshacer();
    }

    public void ejecutarMovimiento() {

        Movimiento mv;

        mv = this.partida.getMovimiento(this.factoria, this.getJugador());

        this.partida.ejecutaMovimiento(mv);

    }

    public void ponerJugador(Ficha color, String tipoJugador) {
        if (color == Ficha.NEGRAS) {
            if (tipoJugador.equalsIgnoreCase("aleatorio")) {
                this.jugadorN = factoria.crearJugadorAleatorio();
            } else if (tipoJugador.equalsIgnoreCase("humano")) {
                this.jugadorN = factoria.crearJugadorHumano(this.in);
            }
        } else if (color == Ficha.BLANCAS) {
            if (tipoJugador.equalsIgnoreCase("aleatorio")) {
                this.jugadorB = factoria.crearJugadorAleatorio();
            } else if (tipoJugador.equalsIgnoreCase("humano")) {
                this.jugadorB = factoria.crearJugadorHumano(this.in);
            }
        }
    }

    public void finalizar() {
        this.fin = true;
    }

    public Scanner getIn() {
        return this.in;
    }

    public Jugador getJugador() {
        if (this.partida.getTurno() == Ficha.BLANCAS) {
            return this.jugadorB;
        } else {
            return this.jugadorN;
        }
    }
}
