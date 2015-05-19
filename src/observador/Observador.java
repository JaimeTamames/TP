package observador;

import exceptions.MovimientoInvalido;
import logica.Ficha;
import logica.tablero.TableroSoloLectura;

public interface Observador {

    public void onReiniciar(TableroSoloLectura tab, Ficha turno);

    public void onPartidaTerminada(TableroSoloLectura tab, Ficha ganador);

    public void onCambioJuego(TableroSoloLectura tab, Ficha turno);

    public void onDeshacerNoPosible();

    public void onDeshacer(TableroSoloLectura tab, Ficha turno, boolean hayMas);

    public void onMovimientoTerminado(TableroSoloLectura tab, Ficha jugador, Ficha turno);

    public void onMovimientoincorrecto(MovimientoInvalido movException);

}
