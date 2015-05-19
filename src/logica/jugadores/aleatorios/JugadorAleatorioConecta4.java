package logica.jugadores.aleatorios;

import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;

public class JugadorAleatorioConecta4 extends Jugador {

    @Override
    protected void obtenFilaColumna(Tablero tab, Ficha color) {
        boolean fin = false;
        int col = 0;
        while (!fin) {
            col = (int) (tab.getAncho() * Math.random());
            if (tab.getFicha(tab.getAlto() - 1, col) == Ficha.VACIA) {
                fin = true;
                this.columna = col;
            }

        }
    }

}
