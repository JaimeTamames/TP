package logica.movimiento;

import exceptions.MovimientoInvalido;
import logica.Ficha;
import logica.tablero.Tablero;

public class MovimientoGravity extends Movimiento {

    public MovimientoGravity(int fila, int columna, Ficha turno) {
        super(fila, columna, turno);
    }

    @Override
    public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido {

        if (this.columna >= t.getAncho() || this.columna < 0
                || this.fila >= t.getAlto() || this.fila < 0) {
            throw new MovimientoInvalido("Posicion incorrecta");
        }

        if (t.getFicha(this.fila, this.columna) != Ficha.VACIA) {
            throw new MovimientoInvalido("Casilla ocupada");
        }

        int dAltF = t.getAlto() - 1 - this.fila;
        int dAltI = this.fila;
        int dAnchF = t.getAncho() - 1 - this.columna;
        int dAnchI = this.columna;

        int min = Math.min(dAltF, dAltI);
        min = Math.min(min, dAnchI);
        min = Math.min(min, dAnchF);

        int f = -1, c = -1;

        // si la ficha esta justo en el centro del tablero
        if (min == dAltF && min == dAltI && min == dAnchF && min == dAnchI) {
            f = this.fila;
            c = this.columna;
        } else if (min == dAltF) {
            // si esta igual de cerca de arriba que de la derecha
            if (min == dAnchF) {
                c = t.getAncho() - 1;
                f = t.getAlto() - 1;
                while (t.getFicha(f, c) != Ficha.VACIA) {
                    c--;
                    f--;
                }
                // si esta igual de cerca de arriba que de la izquierda
            } else if (min == dAnchI) {
                c = 0;
                f = t.getAlto() - 1;
                while (t.getFicha(f, c) != Ficha.VACIA) {
                    f--;
                    c++;
                }
                // dista mas cerca solo de la pared de arriba    
            } else {
                c = this.columna;
                f = t.getAlto() - 1;
                while (t.getFicha(f, c) != Ficha.VACIA) {
                    f--;
                }
            }
        } else if (min == dAltI) {
            // si esta igual de cerca de abajo que de la derecha
            if (min == dAnchF) {
                c = t.getAncho() - 1;
                f = 0;
                while (t.getFicha(f, c) != Ficha.VACIA) {
                    f++;
                    c--;
                }
                // si esta igual de cerca de abajo que de la izquierda
            } else if (min == dAnchI) {
                c = 0;
                f = 0;
                while (t.getFicha(f, c) != Ficha.VACIA) {
                    f++;
                    c++;
                }
                // dista mas cerca solo de la pared de abajo   
            } else {
                c = this.columna;
                f = 0;
                while (t.getFicha(f, c) != Ficha.VACIA) {
                    f++;
                }
            }
            // esta mas cerca de la pared de la derecha
        } else if (min == dAnchF) {
            f = this.fila;
            c = t.getAncho() - 1;
            while (t.getFicha(f, c) != Ficha.VACIA) {
                c--;
            }
            // esta mas cerca de la pared de la izquierda
        } else if (min == dAnchI) {
            f = this.fila;
            c = 0;
            while (t.getFicha(f, c) != Ficha.VACIA) {
                c++;
            }
        }

        this.fila = f;
        this.columna = c;
        t.ponerFicha(f, c, turno);

    }

    @Override
    public void undo(Tablero t) {
        t.ponerFicha(this.fila, this.columna, Ficha.VACIA);
    }

}
