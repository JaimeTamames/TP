package logica.reglas;

import logica.Constantes;
import logica.Ficha;
import logica.tablero.Tablero;

public class ReglasJuegoConecta4 extends ReglasJuego {

    @Override
    public Tablero iniciaTablero() {
        return new Tablero(Constantes.ALTOC4, Constantes.ANCHOC4);
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

}
