package logica.modosDeJuego;

import controlador.ControladorGUI;


public class ModoHumano implements ModoJuego{
    
    private ControladorGUI controlador;

    public ModoHumano(ControladorGUI c) {
        this.controlador = c;
    }

    @Override
    public void comenzar() {
        // no hace nada
    }

    @Override
    public void terminar() {
        // no hace nada
    }

    @Override
    public void deshacerpulsado() {
        this.controlador.getPartida().deshacer();
    }

    @Override
    public void tableroPulsado(int f, int c) {
        this.controlador.poner(f, c);
    }
    
}
