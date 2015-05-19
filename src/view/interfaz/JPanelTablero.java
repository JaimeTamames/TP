package view.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class JPanelTablero extends JPanel {

    private int rows;
    private int columns;

    private Color[][] cells;
    private Color color;

    public JPanelTablero(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;

        this.cells = new Color[rows][columns];

        initComponents();
    }

    private void initComponents() {

        this.setLayout(null);
        this.setBorder(null);

        if (this.rows > this.columns) {
            this.setPreferredSize(new Dimension((500 / this.rows) * this.columns, 500));
        } else {
            this.setPreferredSize(new Dimension(500, (500 / this.columns) * this.rows));
        }

        this.setVisible(true);

        for (int f = 0; f < this.rows; f++) {
            for (int c = 0; c < this.columns; c++) {
                this.markCell(f, c, Color.LIGHT_GRAY);
            }
        }
    }

    public void markCell(int row, int col, Color c) {
        this.cells[row][col] = c;
        this.repaint();
    }

    public void markCell(Point p, Color c) {
        this.cells[p.y][p.x] = c;
        this.repaint();
    }

    public Point getCellFromTable(int x, int y) {

        int edgeCell = 500 / Math.max(this.columns, this.rows);
        x = x / edgeCell;
        y = y / edgeCell;

        if ((x < this.columns) && (y < this.rows)) {
            return new Point(x, this.rows - 1 - y);
        }

        return null;

    }

    public Point getCellFromTable(Point p) {

        int edgeCell = 500 / Math.max(this.columns, this.rows);
        p.x = p.x / edgeCell;
        p.y = p.y / edgeCell;

        if ((p.x < this.columns) && (p.y < this.rows)) {
            return new Point(p.x, this.rows - 1 - p.y);
        }

        return null;

    }

    public void reset(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        this.cells = new Color[rows][columns];

        if (this.rows > this.columns) {
            this.setPreferredSize(new Dimension((500 / this.rows) * this.columns, 500));
        } else {
            this.setPreferredSize(new Dimension(500, (500 / this.columns) * this.rows));
        }

        for (int f = 0; f < this.rows; f++) {
            for (int c = 0; c < this.columns; c++) {
                this.cells[f][c] = Color.LIGHT_GRAY;
            }
        }

        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        int edgeCell = 500 / Math.max(this.columns, this.rows);

        for (int f = 0; f < this.rows; f++) {
            for (int c = 0; c < this.columns; c++) {
                g.setColor(Color.WHITE);
                g.drawRect(c * edgeCell, (this.rows - 1 - f) * edgeCell, edgeCell, edgeCell);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect((c * edgeCell) + 1, ((this.rows - 1 - f) * edgeCell) + 1, edgeCell - 2, edgeCell - 2);
                g.setColor(this.cells[f][c]);
                g.fillOval((c * edgeCell) + 2, ((this.rows - 1 - f) * edgeCell) + 2, edgeCell - 4, edgeCell - 4);
            }
        }

    }

}
