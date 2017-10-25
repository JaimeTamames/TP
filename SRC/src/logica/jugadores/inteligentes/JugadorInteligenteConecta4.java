package logica.jugadores.inteligentes;

import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;
import logica.reglas.ReglasJuegoCuatroEnLinea;

public class JugadorInteligenteConecta4 extends Jugador {

    @Override
    protected void obtenFilaColumna(Tablero tab, Ficha color) {

        Ficha contrario = (color == Ficha.BLANCAS) ? Ficha.NEGRAS : Ficha.BLANCAS;

        int maxPosibles = 0;

        for (int f = 0; f < tab.getAlto(); f++) {
            for (int c = 0; c < tab.getAncho(); c++) {

                if ((ReglasJuegoCuatroEnLinea.cuatroEnLinea(tab, color, f, c)
                        || ReglasJuegoCuatroEnLinea.cuatroEnLinea(tab, contrario, f, c))
                        && tab.getFicha(f, c) == Ficha.VACIA
                        && !inferiorVacia(f, c, tab)) {
                    this.fila = f;
                    this.columna = c;
                    return;
                } else if (ReglasJuegoCuatroEnLinea.sumaEnLinea(tab, contrario, f, c) >= 6
                        && tab.getFicha(f, c) == Ficha.VACIA) {
                    this.fila = f;
                    this.columna = c;
                    maxPosibles = 6;
                } else if (ReglasJuegoCuatroEnLinea.sumaEnLinea(tab, color, f, c) > maxPosibles
                        && tab.getFicha(f, c) == Ficha.VACIA
                        && !ReglasJuegoCuatroEnLinea.cuatroEnLinea(tab, contrario, superior(f + 1, tab), c)) {

                    maxPosibles = ReglasJuegoCuatroEnLinea.sumaEnLinea(tab, color, f, c);
                    this.fila = f;
                    this.columna = c;
                }
            }
        }
    }

    private boolean inferiorVacia(int f, int c, Tablero t) {

        if (f - 1 >= 0) {
            if(t.getFicha(f - 1, c) == Ficha.VACIA){
                return true;
            }
        }

        return false;
    }

    private int superior(int f, Tablero t) {

        if (f > t.getAlto()) {
            return f - 1;
        }
        return f;
    }
}
