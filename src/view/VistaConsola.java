package view;

import exceptions.MovimientoInvalido;
import logica.Ficha;
import logica.tablero.TableroSoloLectura;
import observador.Observador;

public class VistaConsola implements Observador {

    @Override
    public void onReiniciar(TableroSoloLectura tab, Ficha turno) {
        System.out.println("Nueva partida");
        //System.out.println(tab.toString() + Constantes.SALTO_LINEA + "Turno de: " + turno);
    }

    @Override
    public void onPartidaTerminada(TableroSoloLectura tab, Ficha ganador) {

        if (ganador != Ficha.VACIA) {
            System.out.println("Enohorabuena, ganan " + ganador);
        } else {
            System.out.println("La partida finalizo en empate");
        }
    }

    @Override
    public void onCambioJuego(TableroSoloLectura tab, Ficha turno) {
        System.out.println("Se ha cambiado el juego");
        //System.out.println(tab.toString() + Constantes.SALTO_LINEA + "Turno de: " + turno);
    }

    @Override
    public void onDeshacerNoPosible() {
        System.err.println("No hay jugadas para deshacer");
    }

    @Override
    public void onDeshacer(TableroSoloLectura tab, Ficha turno, boolean hayMas) {
        System.out.println("Movimiento desecho");
        //System.out.println(tab.toString() + Constantes.SALTO_LINEA + "Turno de: " + turno);
    }

    @Override
    public void onMovimientoTerminado(TableroSoloLectura tab, Ficha jugador, Ficha turno) {
        System.out.println("Fin de turno de " + jugador);
        //System.out.println(tab.toString() + Constantes.SALTO_LINEA + "Turno de: " + turno);
    }

    @Override
    public void onMovimientoincorrecto(MovimientoInvalido movException) {
        System.err.println(movException.getMessage());
    }
}
