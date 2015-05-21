package logica.modosDeJuego;

import controlador.ControladorGUI;

public class ModoAutomatico implements ModoJuego {

    private ModoAutomaticoThread execThread;
    private ControladorGUI controlador;

    public ModoAutomatico(ControladorGUI c) {
        this.controlador = c;
        this.execThread = null;
    }

    @Override
    public void comenzar() {
        // bloquea el tablero
        // ejecuta la hebra del movimiento
        this.execThread = new ModoAutomaticoThread();
        this.execThread.start();
    }

    @Override
    public void terminar() {
        // interrumpe la hebra si esta en ejecución.
        if(this.execThread != null){
            this.execThread.interrupt();
            execThread = null;
        }
    }

    @Override
    public void deshacerpulsado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tableroPulsado(int f, int c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class ModoAutomaticoThread extends Thread {

        @Override
        public void run() {
            controlador.ponerAutomatico();
        }

    };

}
