import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ConsolaPrincipal extends JFrame {

    private JButton NuevoTablero = new JButton("Nuevo");
    private JButton PosicionInicial = new JButton("Posicion Inicial");
    private JButton Rotar = new JButton("Rotar");
    private JButton Tema = new JButton("Cambiar Tema");
    private JLabel Titulo = new JLabel("Ingresar Cadena FEN");
    private JTextArea CadenaFen = new JTextArea();

    public ConsolaPrincipal() {
        Color ColorFondo = new Color(30, 31, 36);

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(ColorFondo);
        setLayout(new BorderLayout());

        // Colores y Font
        Color text = new Color(232, 233, 237);
        Color colorSubtitulos = new Color(156, 163, 175); // <-- for subtle labels
        Color fondoBotones = new Color(52, 54, 61);
        Color fondoMouse = new Color(61, 64, 71);
        Color panelBg = new Color(42, 44, 51);
        Color divider = new Color(44, 46, 52); // <-- define 'divider'
        Font ui = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontTitulo = new Font("Century Gothic", Font.BOLD, 26);

        // Panel titulo

        // Panel botones
        JPanel PanelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        PanelSuperior.setBackground(ColorFondo);

        // Misma configuracion para los botones
        JButton[] botones = { NuevoTablero, PosicionInicial, Rotar, Tema };
        for (JButton b : botones) {
            b.setFont(ui);
            b.setForeground(text);
            b.setBackground(fondoBotones);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
            b.addChangeListener(e -> {
                ButtonModel m = b.getModel();
                b.setBackground(m.isRollover() ? fondoMouse : fondoBotones);
            });
            PanelSuperior.add(b);
        }

        PanelSuperior.add(Titulo);
        Titulo.setFont(ui);
        Titulo.setForeground(text);
        Titulo.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        // Panel superior (bootones y titulo)
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(ColorFondo);
        top.add(PanelSuperior, BorderLayout.CENTER);

        Tablero tablero = new Tablero();
        // Donde va el tablero
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(panelBg);
        center.setBorder(new EmptyBorder(12, 12, 12, 12));
        center.add(tablero, BorderLayout.CENTER);

        // Panel derecha
        JPanel derecha = new JPanel();
        derecha.setBackground(panelBg);
        derecha.setBorder(new EmptyBorder(16, 5, 16, 16));
        derecha.setLayout(new BoxLayout(derecha, BoxLayout.Y_AXIS));
        derecha.setPreferredSize(new Dimension(400, 0));

        Titulo.setForeground(text);
        Titulo.setFont(fontTitulo);
        Titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        derecha.add(Titulo);
        derecha.add(Box.createVerticalStrut(12)); // espacio entre cada elemento

        CadenaFen.setForeground(text);
        CadenaFen.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        CadenaFen.setLineWrap(true); // wrap anywhere, not just words
        CadenaFen.setBackground(new Color(35, 37, 43));
        CadenaFen.setForeground(text);
        CadenaFen.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12)); // el padding
        CadenaFen.setAlignmentX(Component.LEFT_ALIGNMENT);

        CadenaFen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        derecha.add(CadenaFen);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(derecha, BorderLayout.EAST);

        Rotar.addActionListener(e -> tablero.Rotar());
        Tema.addActionListener(e -> tablero.CambiarTema());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsolaPrincipal());
    }
}
