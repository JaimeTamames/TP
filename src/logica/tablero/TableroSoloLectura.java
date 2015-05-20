package logica.tablero;

import logica.Ficha;

public interface TableroSoloLectura {

    public int getAlto();

    public int getAncho();

    public Ficha getFicha(int fil, int col);

    public String toString();

}
