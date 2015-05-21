package controlador;

import logica.Partida;
import logica.factorias.FactoriaJuego;
import view.VistaGUI;

/**
 * Controla la ejecucion de la aplicacion. Muestra la interfaz y la actualiza
 * segun lo que se haga.
 *
 */
public class ControladorGUI {

    private Partida partida;
    private FactoriaJuego factoria;
    private boolean fin;

    /**
     * Instancia los parametros de la clase dando asi comienzo al juego
     * (conecta4 por defecto).
     *
     * @param f factoria de juego
     * @param p partida
     *
     */
    public ControladorGUI(FactoriaJuego f, Partida p) {

        this.factoria = f;
        this.partida = p;
        this.partida.addObservador(new VistaGUI(this));
        this.fin = false;
    }

    public FactoriaJuego getFactoria() {
        return this.factoria;
    }

    public Partida getPartida() {
        return this.partida;
    }

    public void undo() {
        this.partida.deshacer();
    }

    public void poner(int fila, int columna) {
        this.partida.ejecutaMovimiento(this.factoria.crearMovimiento(fila, columna, this.partida.getTurno()));
    }

    public void reset(FactoriaJuego factoria) {
        this.factoria = factoria;
        this.partida.resetear(this.factoria.crearReglas());
        this.fin = false;
    }

    public void finalizar() {
        this.fin = true;
    }
    
    public void ponerAutomatico(){
        this.partida.getMovAutomatico();
    }
}
