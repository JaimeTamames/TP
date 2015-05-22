package view;

import controlador.ControladorGUI;
import exceptions.MovimientoInvalido;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import logica.Constantes;
import logica.Ficha;
import logica.factorias.FactoriaJuegoComplica;
import logica.factorias.FactoriaJuegoConecta4;
import logica.factorias.FactoriaJuegoGravity;
import logica.factorias.FactoriaJuegoReversi;
import logica.modosDeJuego.ModoAutomatico;
import logica.modosDeJuego.ModoHumano;
import logica.modosDeJuego.ModoJuego;
import logica.modosDeJuego.TipoTurno;
import logica.tablero.TableroSoloLectura;
import observador.Observador;
import view.interfaz.JFrameVistaGUI;

public class VistaGUI implements Observador, MouseListener, ActionListener {

    private JFrameVistaGUI interfaz;
    private ControladorGUI ctrl;

    private ModoJuego modoJuegoB;
    private ModoJuego modoJuegoN;

    public VistaGUI(ControladorGUI ctrl) {
        this.interfaz = new JFrameVistaGUI(ctrl.getPartida().getTablero().getAlto(), ctrl.getPartida().getTablero().getAncho());

        this.interfaz.setTitle(ctrl.getFactoria().toString());
        this.interfaz.getjPanelPartida().getJButtonDeshacer().setEnabled(false);
        this.interfaz.getjLabelTurno().setText(ctrl.getPartida().getTurno().toString());

        this.interfaz.getjPanelCambioJuego().getjLabelColumnas().setVisible(false);
        this.interfaz.getjPanelCambioJuego().getjLabelFilas().setVisible(false);
        this.interfaz.getjPanelCambioJuego().getjSpinnerColumnas().setVisible(false);
        this.interfaz.getjPanelCambioJuego().getjSpinnerFilas().setVisible(false);

        this.interfaz.setVisible(true);

        this.ctrl = ctrl;
        this.modoJuegoB = new ModoHumano(ctrl);
        this.modoJuegoN = new ModoHumano(ctrl);

        if (this.ctrl.getPartida().getTurno() == Ficha.BLANCAS) {
            this.modoJuegoB.comenzar();
        } else {
            this.modoJuegoN.comenzar();
        }

        this.addListeners();
    }

    @Override
    public void onReiniciar(TableroSoloLectura tab, Ficha turno) {
                
        this.paintTablero(tab);
        this.interfaz.getjPanelPartida().getJButtonDeshacer().setEnabled(false);
        this.interfaz.getjLabelTurno().setText(turno.toString());

        if (turno == Ficha.BLANCAS) {
            this.modoJuegoB.comenzar();
        } else {
            this.modoJuegoN.comenzar();
        }
    }

    @Override
    public void onPartidaTerminada(TableroSoloLectura tab, Ficha ganador) {
        this.paintTablero(tab);
        this.interfaz.getjLabelTurno().setText(ganador.toString());

        if (ganador != Ficha.VACIA) {
            JOptionPane.showMessageDialog(null, "Enhorabuena, ganan " + ganador);
        } else {
            JOptionPane.showMessageDialog(null, "La partida finalizo en empate");
        }

        this.interfaz.getjPanelPartida().getJButtonDeshacer().setEnabled(false);
        this.modoJuegoB.terminar();
        this.modoJuegoN.terminar();
    }

    @Override
    public void onCambioJuego(TableroSoloLectura tab, Ficha turno) {               
        
        this.interfaz.resetTable(tab.getAlto(), tab.getAncho());
        this.paintTablero(tab);
        this.interfaz.setTitle(this.ctrl.getFactoria().toString());
        this.interfaz.getjPanelPartida().getJButtonDeshacer().setEnabled(false);
        this.interfaz.getjLabelTurno().setText(turno.toString());

        if (turno == Ficha.BLANCAS) {
            this.modoJuegoB.comenzar();
        } else {
            this.modoJuegoN.comenzar();
        }
        
    }

    @Override
    public void onDeshacerNoPosible() {
        JOptionPane.showMessageDialog(null, "No hay movimientos que deshacer", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void onDeshacer(TableroSoloLectura tab, Ficha turno, boolean hayMas) {
        this.paintTablero(tab);
        this.interfaz.getjLabelTurno().setText(turno.toString());

        if (!hayMas) {
            this.interfaz.getjPanelPartida().getJButtonDeshacer().setEnabled(false);
        }
    }

    @Override
    public void onMovimientoTerminado(TableroSoloLectura tab, Ficha jugador, Ficha turno) {
        this.paintTablero(tab);
        this.interfaz.getjLabelTurno().setText(turno.toString());
        this.interfaz.getjPanelPartida().getJButtonDeshacer().setEnabled(true);

        if (turno == Ficha.BLANCAS) {
            this.modoJuegoB.comenzar();
        } else {
            this.modoJuegoN.comenzar();
        }
    }

    @Override
    public void onMovimientoincorrecto(MovimientoInvalido movException) {
        JOptionPane.showMessageDialog(null, movException.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void paintTablero(TableroSoloLectura tab) {
        for (int f = 0; f < tab.getAlto(); f++) {
            for (int c = 0; c < tab.getAncho(); c++) {
                if (tab.getFicha(f, c).equals(Ficha.BLANCAS)) {
                    this.interfaz.getjPanelTablero().markCell(f, c, Color.white);
                } else if (tab.getFicha(f, c).equals(Ficha.NEGRAS)) {
                    this.interfaz.getjPanelTablero().markCell(f, c, Color.black);
                } else {
                    this.interfaz.getjPanelTablero().markCell(f, c, Color.LIGHT_GRAY);
                }
            }
        }
    }

    private void addListeners() {
        //tablero
        this.interfaz.getjPanelTablero().addMouseListener(this);
        //deshacer
        this.interfaz.getjPanelPartida().getJButtonDeshacer().addActionListener(this);
        //reinciar
        this.interfaz.getjPanelPartida().getJButtonReiniciar().addActionListener(this);
        //comboBoxCambioJuego
        this.interfaz.getjPanelCambioJuego().getjComboBoxTiposJuego().addActionListener(this);
        //cambiar
        this.interfaz.getjPanelCambioJuego().getjButtonCambiar().addActionListener(this);
        //aleatorio
        this.interfaz.getjButtonAleatorio().addActionListener(this);
        //salir
        this.interfaz.getjButtonSalir().addActionListener(this);

        //comboBoxTipoJugadorBlanco
        this.interfaz.getjPanelGestionJugadores().getjComboBoxJugadorBlancas().addActionListener(this);
        //comboBoxTipoJugadorNegro
        this.interfaz.getjPanelGestionJugadores().getjComboBoxJugadorNegras().addActionListener(this);

    }

    //Eventos de raton
    @Override
    public void mouseClicked(MouseEvent me) {

        int f = this.interfaz.getjPanelTablero().getCellFromTable(me.getPoint()).y;
        int c = this.interfaz.getjPanelTablero().getCellFromTable(me.getPoint()).x;

        if (this.ctrl.getPartida().getTurno() == Ficha.BLANCAS) {
            this.modoJuegoB.tableroPulsado(f, c);
        } else {
            this.modoJuegoN.tableroPulsado(f, c);
        }
    }

    // INSERVIBLE
    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
    //-----------

    //actionListener
    @Override
    public void actionPerformed(ActionEvent ae) {

        //deshacer
        if (this.interfaz.getjPanelPartida().getJButtonDeshacer().getActionCommand().equals(ae.getActionCommand())) {
            if (this.ctrl.getPartida().getTurno() == Ficha.BLANCAS) {
                this.modoJuegoB.deshacerpulsado();
            } else {
                this.modoJuegoN.deshacerpulsado();
            }
        } //reiniciar
        else if (this.interfaz.getjPanelPartida().getJButtonReiniciar().getActionCommand().equals(ae.getActionCommand())) {
            this.modoJuegoB.terminar();
            this.modoJuegoN.terminar();
            this.ctrl.reset(this.ctrl.getFactoria());
        } //comboBox cambio de juego
        else if (this.interfaz.getjPanelCambioJuego().getjComboBoxTiposJuego().equals(ae.getSource())) {
            
            switch ((String) this.interfaz.getjPanelCambioJuego().getjComboBoxTiposJuego().getSelectedItem()) {
                case "GRAVITY":
                    this.interfaz.getjPanelCambioJuego().getjLabelColumnas().setVisible(true);
                    this.interfaz.getjPanelCambioJuego().getjLabelFilas().setVisible(true);
                    this.interfaz.getjPanelCambioJuego().getjSpinnerColumnas().setVisible(true);
                    this.interfaz.getjPanelCambioJuego().getjSpinnerFilas().setVisible(true);
                    break;
                case "COMPLICA":
                    this.interfaz.getjPanelCambioJuego().getjLabelColumnas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjLabelFilas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjSpinnerColumnas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjSpinnerFilas().setVisible(false);
                    break;
                case "CONECTA4":
                    this.interfaz.getjPanelCambioJuego().getjLabelColumnas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjLabelFilas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjSpinnerColumnas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjSpinnerFilas().setVisible(false);
                    break;
                case "REVERSI":
                    this.interfaz.getjPanelCambioJuego().getjLabelColumnas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjLabelFilas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjSpinnerColumnas().setVisible(false);
                    this.interfaz.getjPanelCambioJuego().getjSpinnerFilas().setVisible(false);
                    break;
            }

        }//cambiar
        else if (this.interfaz.getjPanelCambioJuego().getjButtonCambiar().getActionCommand().equals(ae.getActionCommand())) {
            
            this.modoJuegoB.terminar();
            this.modoJuegoN.terminar();

            switch ((String) this.interfaz.getjPanelCambioJuego().getjComboBoxTiposJuego().getSelectedItem()) {
                case "GRAVITY":
                    Constantes.ALTOGR = (int) this.interfaz.getjPanelCambioJuego().getjSpinnerFilas().getValue();
                    Constantes.ANCHOGR = (int) this.interfaz.getjPanelCambioJuego().getjSpinnerColumnas().getValue();
                    this.ctrl.reset(new FactoriaJuegoGravity());
                    break;
                case "COMPLICA":
                    this.ctrl.reset(new FactoriaJuegoComplica());
                    break;
                case "CONECTA4":
                    this.ctrl.reset(new FactoriaJuegoConecta4());
                    break;
                case "REVERSI":
                    this.ctrl.reset(new FactoriaJuegoReversi());
            }
        } //aleatorio
        else if (this.interfaz.getjButtonAleatorio().getActionCommand().equals(ae.getActionCommand())) {

            TableroSoloLectura tab = this.ctrl.getPartida().getTablero();

            boolean fin = false;
            int c = 0, f = 0;
            while (!fin) {
                c = (int) (tab.getAncho() * Math.random());
                f = (int) (tab.getAlto() * Math.random());
                if (tab.getFicha(f, c) == Ficha.VACIA && this.ctrl.getPartida().getReglas().esPosibleMover(f, c, tab, this.ctrl.getPartida().getTurno())) {
                    fin = true;
                }
            }

            //poner ficha aleatoria
            if (this.ctrl.getPartida().getTurno() == Ficha.BLANCAS) {
                this.modoJuegoB.tableroPulsado(f, c);
            } else {
                this.modoJuegoN.tableroPulsado(f, c);
            }

        } //salir
        else if (this.interfaz.getjButtonSalir().getActionCommand().equals(ae.getActionCommand())) {
            this.modoJuegoB.terminar();
            this.modoJuegoN.terminar();
            this.interfaz.dispose();
        } //cambio de modo juego en jugador blanco
        else if (this.interfaz.getjPanelGestionJugadores().getjComboBoxJugadorBlancas().equals(ae.getSource())) {
            
            this.modoJuegoB.terminar();
            
            if (this.interfaz.getjPanelGestionJugadores().getjComboBoxJugadorBlancas().getSelectedItem().equals(TipoTurno.AUTOMATICO)) {
                this.modoJuegoB = new ModoAutomatico(this.ctrl);
            } else {
                this.modoJuegoB = new ModoHumano(this.ctrl);
            }
            
            if(this.ctrl.getPartida().getTurno() == Ficha.BLANCAS){
                this.modoJuegoB.comenzar();                
            }
            
        } //cambio de modo juego en jugador negro
        else if (this.interfaz.getjPanelGestionJugadores().getjComboBoxJugadorNegras().equals(ae.getSource())) {
            
            this.modoJuegoN.terminar();
            
            if (this.interfaz.getjPanelGestionJugadores().getjComboBoxJugadorNegras().getSelectedItem().equals(TipoTurno.AUTOMATICO)) {
                this.modoJuegoN = new ModoAutomatico(this.ctrl);
            } else {
                this.modoJuegoN = new ModoHumano(this.ctrl);
            }
            
            if(this.ctrl.getPartida().getTurno() == Ficha.NEGRAS){
                this.modoJuegoN.comenzar();                
            }
        }
    }
}
