package logica.jugadores.aleatorios;

import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;

public class JugadorAleatorioReversi extends Jugador {

    @Override
    public void obtenFilaColumna(Tablero tab, Ficha color) {
        boolean fin = false;
        int col = 0, fil = 0;
        while (!fin) {
            col = (int) (tab.getAncho() * Math.random());
            fil = (int) (tab.getAlto() * Math.random());
            if (Ficha.VACIA == tab.getFicha(fil, col)) {
                fin = true; 
                this.columna = col;
                this.fila = fil;
            }
        }
    }
}
