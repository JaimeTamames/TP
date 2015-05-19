package logica.jugadores.aleatorios;

import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;

public class JugadorAleatorioComplica extends Jugador {

    @Override
    protected void obtenFilaColumna(Tablero tab, Ficha color) {

        int col = (int) (tab.getAncho() * Math.random());
        this.columna = col;
    }
}
