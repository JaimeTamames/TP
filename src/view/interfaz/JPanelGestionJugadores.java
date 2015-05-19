package view.interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelGestionJugadores extends JPanel {

    private JLabel jLabelJugadorBlancas;
    private JComboBox jComboBoxJugadorBlancas;
    private JLabel jLabelJugadorNegras;
    private JComboBox jComboBoxJugadorNegras;

    public JPanelGestionJugadores() {

        this.init();
    }

    public JLabel getjLabelJugadorBlancas() {
        return jLabelJugadorBlancas;
    }

    public JComboBox getjComboBoxJugadorBlancas() {
        return jComboBoxJugadorBlancas;
    }

    public JLabel getjLabelJugadorNegras() {
        return jLabelJugadorNegras;
    }

    public JComboBox getjComboBoxJugadorNegras() {
        return jComboBoxJugadorNegras;
    }

    public void init() {

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(400, 100));
        this.setMinimumSize(new Dimension(400, 100));
        this.setBorder(BorderFactory.createTitledBorder("Gesión de jugadores"));

        String tiposJugadores[] = {"Humano", "Aleatorio"};

        this.jLabelJugadorBlancas = new JLabel("Blancas");
        this.jComboBoxJugadorBlancas = new JComboBox(tiposJugadores);

        this.jLabelJugadorNegras = new JLabel("Negras");
        this.jComboBoxJugadorNegras = new JComboBox(tiposJugadores);

        this.jComboBoxJugadorBlancas.setBounds(this.getWidth() / 2 - 75, 30, 150, 30);
        this.jComboBoxJugadorNegras.setBounds(this.getWidth() / 2 - 75, 30, 150, 30);

        this.add(this.jLabelJugadorBlancas);
        this.add(this.jComboBoxJugadorBlancas);
        this.add(this.jLabelJugadorNegras);
        this.add(this.jComboBoxJugadorNegras);

        this.setVisible(true);
    }
}
