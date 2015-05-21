package logica.reglas;

import logica.Constantes;
import logica.Ficha;
import logica.tablero.Tablero;

public class ReglasJuegoReversi extends ReglasJuego {

    @Override
    public Tablero iniciaTablero() {

        int filaAux = (Constantes.ALTORE / 2) - 1;
        int columnaAux = (Constantes.ANCHORE / 2) - 1;

        Tablero tab = new Tablero(Constantes.ALTORE, Constantes.ANCHORE);
        tab.ponerFicha(filaAux, columnaAux, Ficha.NEGRAS);
        tab.ponerFicha(filaAux, columnaAux + 1, Ficha.BLANCAS);
        tab.ponerFicha(filaAux + 1, columnaAux, Ficha.BLANCAS);
        tab.ponerFicha(filaAux + 1, columnaAux + 1, Ficha.NEGRAS);

        return tab;
    }

    @Override
    public Ficha hayGanador(int f, int c, Ficha ganador, Tablero tablero) {

        int fichasBlancas = 0, fichasNegras = 0, fichasVacias = 0;

        for (int fila = 0; fila < tablero.getAlto(); fila++) {
            for (int columna = 0; columna < tablero.getAncho(); columna++) {

                if (tablero.getFicha(fila, columna) == Ficha.BLANCAS) {
                    fichasBlancas++;
                } else if (tablero.getFicha(fila, columna) == Ficha.NEGRAS) {
                    fichasNegras++;
                } else {
                    fichasVacias++;
                }
            }
        }

        //Si un juguador se queda sin fichas gana el jugador contrario.
        if (fichasBlancas == 0) {
            return Ficha.NEGRAS;
        } else if (fichasNegras == 0) {
            return Ficha.BLANCAS;
        } else if (fichasVacias > 0 || (fichasVacias == 0 && fichasBlancas == fichasNegras)) {
            return Ficha.VACIA;
        } else {
            return (fichasBlancas > fichasNegras) ? Ficha.BLANCAS : Ficha.NEGRAS;
        }

    }

    @Override
    public boolean tablas(Tablero t) {
        return t.isCompleto();
    }
    
    @Override
    public Ficha jugadorInicial() {
        return Ficha.NEGRAS;
    }
}
