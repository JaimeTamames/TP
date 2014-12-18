package logica.reglas;

import logica.Constantes;
import logica.Ficha;
import logica.Tablero;

public class ReglasJuegoComplica extends ReglasJuego {

    @Override
    public Tablero iniciaTablero() {
        return new Tablero(Constantes.ALTOCO, Constantes.ANCHOCO);
    }

    @Override
    public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {

        // En esta version solo hay ganador cuando solo uno de los dos tiene 4 en linea.
        
        if (!(ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, Ficha.BLANCA, f, c)
                ^ ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, Ficha.NEGRA, f, c))) {
            return Ficha.VACIA;

        } else {
            if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, ultimo, f, c)) {
                return ultimo;
            } else {
                return super.siguienteTurno(ultimo);
            }

        }
    }

    @Override
    public boolean tablas(Tablero t) {
        // Las tablas devuelven false porque no pueden existir en esta version.
        return false;
    }

}
