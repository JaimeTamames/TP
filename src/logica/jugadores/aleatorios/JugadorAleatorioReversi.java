package logica.jugadores.aleatorios;

import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;
import static logica.reglas.ReglasJuegoReversi.posicionValida;

public class JugadorAleatorioReversi extends Jugador {

    @Override
    public void obtenFilaColumna(Tablero tab, Ficha color) {
        boolean fin = false;
        //empezamos en la celda superior izquierda del marco que rodea al tablero central de 2x2
        int col = 0;
        int fil = 0;
        while (!fin) {
            col = (int) (tab.getAncho() * Math.random());
            fil = (int) (tab.getAlto() * Math.random());
            if (posicionValida(tab, fil, col, color) && Ficha.VACIA == tab.getFicha(fil, col)) {
                fin = true;
                this.columna = col;
                this.fila = fil;
            }
        }
    }
}
