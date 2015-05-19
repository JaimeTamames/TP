package logica.movimiento;

import exceptions.ColumnaIncorrecta;
import logica.Ficha;
import logica.tablero.Tablero;

public class MovimientoComplica extends Movimiento {

    private Ficha fichaSustituida;

    public MovimientoComplica(int columna, Ficha turno) {
        super(columna, turno);
        fichaSustituida = Ficha.VACIA;
    }

    @Override
    public void ejecutaMovimiento(Tablero t) throws ColumnaIncorrecta {

        if (this.columna >= t.getAncho() || this.columna < 0) {
            throw new ColumnaIncorrecta(t.getAncho());
        }

        while (this.fila < t.getAlto()
                && t.getFicha(this.fila, this.columna) != Ficha.VACIA) {
            this.fila++;
        }

        if (this.fila >= t.getAlto()) {
            // Desplazamos todas las fichas una posición hacia abajo para hacer
            // hueco en la ultima posicion.

            this.fichaSustituida = t.getFicha(0, this.columna);

            this.fila--;

            for (int f = 0; f < this.fila; f++) {
                t.ponerFicha(f, this.columna, t.getFicha(f + 1, this.columna));
            }

        }

        t.ponerFicha(this.fila, this.columna, this.turno);
    }

    @Override
    public void undo(Tablero t) {
        if (this.fichaSustituida == Ficha.VACIA) {
            t.ponerFicha(this.fila, this.columna, Ficha.VACIA);
        } else {

            for (int f = t.getAlto() - 1; f >= 1; f--) {
                t.ponerFicha(f, this.columna, t.getFicha(f - 1, this.columna));
            }

            t.ponerFicha(0, this.columna, this.fichaSustituida);
        }
    }

}
