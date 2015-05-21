package logica.jugadores.aleatorios;

import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;

public class JugadorAleatorioReversi extends Jugador {

    @Override
    public void obtenFilaColumna(Tablero tab, Ficha color) {
        boolean fin = false;
        //empezamos en la celda superior izquierda del marco que rodea al tavlero central de 2x2
        int col = (tab.getAlto() / 2) - 2, fil = (tab.getAlto() / 2) - 2;
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
