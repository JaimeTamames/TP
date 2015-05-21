package logica.reglas;

import logica.Constantes;
import logica.Ficha;
import logica.tablero.Tablero;

public class ReglasJuegoGravity extends ReglasJuego {

    @Override
    public Tablero iniciaTablero() {
        return new Tablero(Constantes.ALTOGR, Constantes.ANCHOGR);
    }

    @Override
    public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {
        if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, ultimo, f, c)) {
            return ultimo;
        } else {
            return Ficha.VACIA;
        }
    }

    @Override
    public boolean tablas(Tablero t) {
        return t.isCompleto();
    }

    @Override
    public Ficha jugadorInicial() {
        return Ficha.BLANCAS;
    }
}