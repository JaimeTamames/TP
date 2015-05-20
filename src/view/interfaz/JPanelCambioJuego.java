package view.interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class JPanelCambioJuego extends JPanel {

    private JComboBox jComboBoxTiposJuego;
    private JLabel jLabelFilas;
    private JSpinner jSpinnerFilas;
    private JLabel jLabelColumnas;
    private JSpinner jSpinnerColumnas;
    private JButton jButtonCambiar;

    public JPanelCambioJuego() {

        this.init();
    }

    public JComboBox getjComboBoxTiposJuego() {
        return jComboBoxTiposJuego;
    }

    public JLabel getjLabelFilas() {
        return jLabelFilas;
    }

    public JSpinner getjSpinnerFilas() {
        return jSpinnerFilas;
    }

    public JLabel getjLabelColumnas() {
        return jLabelColumnas;
    }

    public JSpinner getjSpinnerColumnas() {
        return jSpinnerColumnas;
    }

    public JButton getjButtonCambiar() {
        return jButtonCambiar;
    }

    private void init() {

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(320, 150));
        this.setMinimumSize(new Dimension(320, 150));
        this.setBorder(BorderFactory.createTitledBorder("Cambio de Juego"));

        String tiposJuego[] = {"CONECTA4", "COMPLICA", "GRAVITY", "REVERSI"};

        this.jComboBoxTiposJuego = new JComboBox(tiposJuego);

        this.jLabelFilas = new JLabel("Filas");
        this.jSpinnerFilas = new JSpinner(new SpinnerNumberModel(4, 4, 100, 1));

        this.jLabelColumnas = new JLabel("Columnas");
        this.jSpinnerColumnas = new JSpinner(new SpinnerNumberModel(4, 4, 100, 1));

        this.jButtonCambiar = new JButton("Cambiar");
        this.jButtonCambiar.setIcon(new ImageIcon("recursos/cambiar.png"));
        this.jButtonCambiar.setPreferredSize(new Dimension(250, 50));

        this.jComboBoxTiposJuego.setBounds(this.getWidth() / 2 - 75, 30, 150, 30);

        JPanel jPanelSpinners = new JPanel();
        jPanelSpinners.setLayout(new FlowLayout());

        this.add(this.jComboBoxTiposJuego);
        jPanelSpinners.add(this.jLabelFilas);
        jPanelSpinners.add(this.jSpinnerFilas);
        jPanelSpinners.add(this.jLabelColumnas);
        jPanelSpinners.add(this.jSpinnerColumnas);
        this.add(jPanelSpinners);
        this.add(this.jButtonCambiar);

        this.setVisible(true);

    }
}
