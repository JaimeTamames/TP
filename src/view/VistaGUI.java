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
import logica.Partida;
import logica.factorias.*;
import logica.jugadores.Jugador;
import logica.movimiento.Movimiento;
import logica.tablero.TableroSoloLectura;
import observador.Observador;
import view.interfaz.JFrameVistaGUI;

public class VistaGUI implements Observador, MouseListener, ActionListener {

    private JFrameVistaGUI interfaz;
    private ControladorGUI ctrl;

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

        this.addListeners();
    }

    @Override
    public void onReiniciar(TableroSoloLectura tab, Ficha turno) {
        this.paintTablero(tab);
        this.interfaz.getjPanelPartida().getJButtonDeshacer().setEnabled(false);
        this.interfaz.getjLabelTurno().setText(turno.toString());
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
    }

    @Override
    public void onCambioJuego(TableroSoloLectura tab, Ficha turno) {
        this.interfaz.resetTable(tab.getAlto(), tab.getAncho());

        this.paintTablero(tab);
        this.interfaz.setTitle(this.ctrl.getFactoria().toString());
        this.interfaz.getjPanelPartida().getJButtonDeshacer().setEnabled(false);
        this.interfaz.getjLabelTurno().setText(turno.toString());

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
        //comboBoxTipoJugadorBlanco
        this.interfaz.getjPanelGestionJugadores().getjComboBoxJugadorBlancas().addActionListener(this);
        //comboBoxTipoJugadorNegro
        this.interfaz.getjPanelGestionJugadores().getjComboBoxJugadorNegras().addActionListener(this);
        //cambiar
        this.interfaz.getjPanelCambioJuego().getjButtonCambiar().addActionListener(this);
        //aleatorio
        this.interfaz.getjButtonAleatorio().addActionListener(this);
        //salir
        this.interfaz.getjButtonSalir().addActionListener(this);

    }

    //Eventos de raton
    @Override
    public void mouseClicked(MouseEvent me) {

        int f = this.interfaz.getjPanelTablero().getCellFromTable(me.getPoint()).y;
        int c = this.interfaz.getjPanelTablero().getCellFromTable(me.getPoint()).x;

        this.ctrl.poner(f, c);
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
            this.ctrl.undo();
        } //reiniciar
        else if (this.interfaz.getjPanelPartida().getJButtonReiniciar().getActionCommand().equals(ae.getActionCommand())) {
            this.ctrl.reset(this.ctrl.getFactoria());
        } //comboBox
        else if (this.interfaz.getjPanelCambioJuego().getjComboBoxTiposJuego().getActionCommand().equals(ae.getActionCommand())) {

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

            FactoriaJuego fac = this.ctrl.getFactoria();
            Partida p = this.ctrl.getPartida();
            Jugador j = fac.crearJugadorAleatorio();
            Movimiento m = p.getMovimiento(fac, j);
            
            p.ejecutaMovimiento(m);

        } //salir
        else if (this.interfaz.getjButtonSalir().getActionCommand().equals(ae.getActionCommand())) {
            this.interfaz.dispose();
        }
    }
}
