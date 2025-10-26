package Consolas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Tablero extends JPanel {

    private char[][] board = new char[8][8];

    public Tablero() {
        setBackground(new Color(40, 40, 40));
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(500, 500));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cw = getWidth() / 8;
        int ch = getHeight() / 8;

        Color light = new Color(238, 238, 210);
        Color dark = new Color(118, 150, 86);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int x = i * cw;
                int y = j * ch;

                if ((i + j) % 2 == 0) {
                    g.setColor(light);
                } else {
                    g.setColor(dark);
                }

                g.fillRect(x, y, cw, ch);

            }

        }

    }
}
