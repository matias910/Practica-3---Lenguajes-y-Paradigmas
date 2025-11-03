import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Tablero extends JPanel {

    // Light
    private Color lightThemeLIGHT = new Color(224, 201, 166);
    private Color lightThemeDARK = new Color(128, 88, 60);
    // Dark
    private Color darkThemeLight = new Color(112, 145, 161);
    private Color darkThemeDark = new Color(47, 62, 70);

    private boolean Rotado = false;
    private boolean darkMode = true;

    private Map<Character, BufferedImage> piezas = new HashMap<>();
    private final char[][] board = {
            { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' },
            { 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' },
            { 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' }
    };

    public Tablero() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(new Color(42, 44, 51));
        setDoubleBuffered(true);
        cargarImagenPiezas();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight());
        int square = size / 8;
        int padding = Math.max(4, square / 16);

        Color light;
        Color dark;

        if (darkMode) {
            light = darkThemeLight;
            dark = darkThemeDark;
        } else {
            light = lightThemeLIGHT;
            dark = lightThemeDARK;
        }

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {

                int x = columna * square;
                int y = fila * square;

                int FilaTablero;
                int ColumnaTablero;

                if (Rotado) {
                    FilaTablero = 7 - fila;
                    ColumnaTablero = 7 - columna;
                } else {
                    FilaTablero = fila;
                    ColumnaTablero = columna;
                }

                boolean esClaro = (fila + columna) % 2 == 0;
                if (esClaro) {
                    g2.setColor(light);
                } else {
                    g2.setColor(dark);
                }

                g2.fillRect(x, y, square, square);

                char pieza = board[FilaTablero][ColumnaTablero];
                if (pieza != ' ') {
                    BufferedImage img = piezas.get(pieza);
                    g2.drawImage(img, x + padding, y + padding, square - 2 * padding, square - 2 * padding,
                            null);

                }
            }

        }
        g2.setColor(new Color(44, 46, 52));
        g2.setStroke(new BasicStroke(3f));
        g2.drawRect(0, 0, square * 8, square * 8);

    }

    private void cargarImagenPiezas() {
        // BLancas
        cargar('K', "/Imagenes/white-king.png");
        cargar('Q', "/Imagenes/white-queen.png");
        cargar('R', "/Imagenes/white-rook.png");
        cargar('B', "/Imagenes/white-bishop.png");
        cargar('N', "/Imagenes/white-knight.png");
        cargar('P', "/Imagenes/white-pawn.png");
        // Negras
        cargar('k', "/Imagenes/black-king.png");
        cargar('q', "/Imagenes/black-queen.png");
        cargar('r', "/Imagenes/black-rook.png");
        cargar('b', "/Imagenes/black-bishop.png");
        cargar('n', "/Imagenes/black-knight.png");
        cargar('p', "/Imagenes/black-pawn.png");
    }

    private void cargar(char a, String ruta) {
        try {
            piezas.put(a, ImageIO.read(getClass().getResource(ruta)));
        } catch (Exception e) {
        }
    }

    private void llenarTablero(Graphics2D g2, char piece, int x, int y, int square){
        String asignacion = switch(piece){
            case 'K' -> "♔";
            case 'Q' -> "♕";
            case 'R' -> "♖";
            case 'B' -> "♗";
            case 'N' -> "♘";
            case 'P' -> "♙";
            case 'k' -> "♚";
            case 'q' -> "♛";
            case 'r' -> "♜";
            case 'b' -> "♝";
            case 'n' -> "♞";
            case 'p' -> "♟";
            default -> "";
        }
    }

    public void CambiarTema() {
        if (darkMode) {
            darkMode = false;
        } else {
            darkMode = true;
        }
        repaint();
    }

    public void Rotar() {
        if (Rotado) {
            Rotado = false;
        } else {
            Rotado = true;
        }
        repaint();
    }
}
