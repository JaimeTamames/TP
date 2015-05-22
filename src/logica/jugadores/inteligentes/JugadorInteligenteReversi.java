package logica.jugadores.inteligentes;

import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;
import logica.reglas.ReglasJuegoReversi;

public class JugadorInteligenteReversi extends Jugador {

    @Override
    public void obtenFilaColumna(Tablero tab, Ficha color) {

        int maxVolteadas = 0;

        for (int f = 0; f < tab.getAlto(); f++) {
            for (int c = 0; c < tab.getAncho(); c++) {
                if (tab.getFicha(f, c) == Ficha.VACIA && ReglasJuegoReversi.posicionValida(tab, f, c, color) > 0) {

                    if (maxVolteadas < ReglasJuegoReversi.posicionValida(tab, f, c, color)) {
                        maxVolteadas = ReglasJuegoReversi.posicionValida(tab, f, c, color);
                        this.fila = f;
                        this.columna = c;
                    }
                }
            }
        }
    }
}
