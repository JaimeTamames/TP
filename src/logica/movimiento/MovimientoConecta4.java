package logica.movimiento;

import exceptions.*;
import logica.Ficha;
import logica.tablero.Tablero;

public class MovimientoConecta4 extends Movimiento {

    public MovimientoConecta4(int columna, Ficha turno) {
        super(columna, turno);
    }

    @Override
    public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido {

        if (this.columna >= t.getAncho() || this.columna < 0) {
            throw new ColumnaIncorrecta(t.getAncho());
        }

        int fil = 0;
        while (fil < t.getAlto()
                && t.getFicha(fil, this.columna) != Ficha.VACIA) {
            fil++;
        }

        if (fil >= t.getAlto()) {
            throw new ColumnaLlena(this.columna);
        } else {
            this.fila = fil;
        }

        t.ponerFicha(this.fila, this.columna, this.turno);
    }

    @Override
    public void undo(Tablero t) {
        t.ponerFicha(this.fila, this.columna, Ficha.VACIA);
    }

}
