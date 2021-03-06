package logica;

import logica.tablero.Tablero;
import exceptions.MovimientoInvalido;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import logica.factorias.FactoriaJuego;
import logica.jugadores.Jugador;
import logica.movimiento.Movimiento;
import logica.reglas.ReglasJuego;
import logica.tablero.TableroSoloLectura;
import observable.Observable;
import observador.Observador;

/**
 * Representa el estado de una partida de conecta4
 */
public class Partida implements Observable {

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
    // Almacena un conjunto de objetos obeservables
    private ArrayList<Observador> obs;

    /**
     * Constructora por defecto. Inicializa todos los atributos de la partida y
     * los configura a su estado inicial.
     *
     * @param reglas
     */
    public Partida(ReglasJuego reglas) {
        this.reglas = reglas;
        this.tablero = reglas.iniciaTablero();
        this.turno = reglas.jugadorInicial();
        this.terminada = false;
        this.ganador = Ficha.VACIA;
        this.jugadas = new Pila();
        this.obs = new ArrayList<>();
    }

    public boolean isTerminada() {
        return this.terminada;
    }

    public Ficha getTurno() {
        return this.turno;
    }

    public ReglasJuego getReglas() {
        return this.reglas;
    }

    public TableroSoloLectura getTablero() {
        return this.tablero;
    }

    /**
     * Ejecuta un movimiento en el tablero. La jugada queda registrada en la
     * pila.
     *
     * @param mv
     */
    public void ejecutaMovimiento(Movimiento mv) {

        if (this.terminada) {
            for (Observador o : this.obs) {
                o.onMovimientoincorrecto(new MovimientoInvalido("Partida ya terminda"));
            }
        } else {

            Ficha color = mv.getJugador();

            if (color != this.getTurno()) {
                for (Observador o : this.obs) {
                    o.onMovimientoincorrecto(new MovimientoInvalido("Turno incorrecto"));
                }
            } else {

                try {
                    mv.ejecutaMovimiento(this.tablero);

                    // Apilamos la jugada
                    this.jugadas.apilar(mv);

                    // Comprobamos si hay ganador
                    this.ganador = this.reglas.hayGanador(mv.getFila(), mv.getColumna(),
                            this.turno, this.tablero);
                    // Terminamos la partida si hay tablas o existe un ganador
                    this.terminada = ((this.ganador != Ficha.VACIA) || this.reglas
                            .tablas(this.tablero));

                    // Si no sucede nada de esto se cambia el turno
                    if (!this.terminada) {
                        // almacenamos el turno actual
                        Ficha jugador = this.turno;
                        // cambiamos el turno, si procede
                        this.turno = this.reglas.siguienteTurno(this.turno, this.tablero);

                        for (Observador o : this.obs) {
                            o.onMovimientoTerminado(this.tablero, jugador, this.turno);
                        }
                    } else {
                        for (Observador o : this.obs) {
                            o.onPartidaTerminada(this.tablero, this.ganador);
                        }
                    }

                } catch (MovimientoInvalido mi) {
                    for (Observador o : this.obs) {
                        o.onMovimientoincorrecto(mi);
                    }
                }
            }
        }
    }

    public void getMovAutomatico(FactoriaJuego f) {

        //Esperar 2 segundos
        try {
            Thread.sleep(Constantes.MSRETARDOJUGADORAUTOMATICO);
            //Calcula y ejecuta un movimiento aleatorio, o, en su defecto inteligente (el que mas fichas gire)
            Movimiento mv = f.crearJugadorInteligente().getMovimiento(f, this.tablero, this.turno);
            
            
            // Llamamos al ejecuta movimiento en la hebra de swing para evitar conflictos en la vista
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ejecutaMovimiento(mv);
                }
            });
            

        } catch (InterruptedException ex) {
            ex.getMessage();
        }

    }

    public Movimiento getMovimiento(FactoriaJuego factoria, Jugador jugador) {

        return jugador.getMovimiento(factoria, this.tablero, this.turno);

    }

    /**
     * Deshace la ultima jugada de la partida.
     *
     */
    public void deshacer() {
        Movimiento m;

        if (this.jugadas.getNumUndo() == 0) {
            for (Observador o : this.obs) {
                o.onDeshacerNoPosible();
            }
        } else {
            m = this.jugadas.desapilar();
            m.undo(this.tablero);

            this.turno = m.getJugador();

            for (Observador o : this.obs) {
                o.onDeshacer(this.tablero, this.turno, this.jugadas.getNumUndo() > 0);
            }

        }

    }

    /**
     * Devuelve la partida a su estado inicial.
     *
     * @param reglas
     */
    public void resetear(ReglasJuego reglas) {
        ReglasJuego reglasAnteriores = this.reglas;
        this.reglas = reglas;
        this.tablero = reglas.iniciaTablero();
        this.turno = reglas.jugadorInicial();
        this.terminada = false;
        this.ganador = Ficha.VACIA;
        this.jugadas.reset();

        if (reglasAnteriores == reglas) {
            for (Observador o : this.obs) {
                o.onReiniciar(this.tablero, reglas.jugadorInicial());
            }
        } else {
            for (Observador o : this.obs) {
                o.onCambioJuego(this.tablero, this.turno);
            }
        }

    }

    @Override
    public void addObservador(Object o) {
        if (!this.obs.contains(o)) {
            this.obs.add((Observador) o);
        }
    }

    @Override
    public void removeObservador(Object o) {
        this.obs.remove(o);
    }
}
