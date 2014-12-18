package logica.movimiento;

import logica.Ficha;
import logica.Tablero;

public class MovimientoComplica extends Movimiento{

    public MovimientoComplica(int columna, Ficha turno) {
        super(columna, turno);
    }

    @Override
    public boolean ejecutaMovimiento(Tablero t) {
        
        if(this.columna >= t.getAncho() || this.columna < 0)
            return false;
        
        int fil = 0;
        while (fil < t.getAlto()
                && t.getFicha(fil, this.columna) != Ficha.VACIA) {
            fil++;
        }
        
        this.fila = (fil == t.getAlto()) ? 0 : fil;
        
        t.ponerFicha(this.fila, this.columna, this.turno);
        
        return true;
    }

    @Override
    public void undo(Tablero t) {
        t.ponerFicha(this.fila, this.columna, Ficha.VACIA);
    }

    

}
