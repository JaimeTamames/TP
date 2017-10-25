package logica.reglas;

import logica.Constantes;
import logica.Ficha;
import logica.tablero.Tablero;
import logica.tablero.TableroSoloLectura;

public class ReglasJuegoComplica extends ReglasJuego {

    @Override
    public Tablero iniciaTablero() {
        return new Tablero(Constantes.ALTOCO, Constantes.ANCHOCO);
    }

    @Override
    public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {
        // si la ficha estuviera en la ultima fila, se produce un caso especial
        if (f < tablero.getAlto() - 1) {
            if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, ultimo, f, c)) {
                return ultimo;
            } else {
                return Ficha.VACIA;
            }
        } else {

            boolean enLineaBl = false;
            boolean enLineaNG = false;

            for (int i = 0; i < tablero.getAlto(); i++) {
                if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, tablero.getFicha(i, c), i, c)) {
                    if (tablero.getFicha(i, c) == Ficha.BLANCAS) {
                        enLineaBl = true;
                    } else if (tablero.getFicha(i, c) == Ficha.NEGRAS) {
                        enLineaNG = true;
                    }
                }
            }

            if (enLineaBl && enLineaNG) {
                // Si ambos jugadores tienen 4 en linea, no hay ganador
                return Ficha.VACIA;
            } else if (enLineaBl) {
                return Ficha.BLANCAS;
            } else if (enLineaNG) {
                return Ficha.NEGRAS;
            } else {
                return Ficha.VACIA;
            }
        }
    }

    @Override
    public boolean tablas(Tablero t) {
        // Las tablas devuelven false porque no pueden existir en esta version.
        return false;
    }

    @Override
    public Ficha jugadorInicial() {
        return Ficha.BLANCAS;
    }

    @Override
    public boolean esPosibleMover(int fila, int columna, TableroSoloLectura t, Ficha turno) {
        return fila < t.getAlto() 
                && columna < t.getAncho();
    }
}