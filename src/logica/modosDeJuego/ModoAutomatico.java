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
        // interrumpe la hebra si esta en ejecucion.
        if(this.execThread != null){
            this.execThread.interrupt();
            execThread = null;
        }
    }

    @Override
    public void deshacerpulsado() {
        
    }

    @Override
    public void tableroPulsado(int f, int c) {
               
    }

    private class ModoAutomaticoThread extends Thread {

        @Override
        public void run() {
            if(!Thread.interrupted()){
                controlador.ponerAutomatico();
            }
        }

    };

}
