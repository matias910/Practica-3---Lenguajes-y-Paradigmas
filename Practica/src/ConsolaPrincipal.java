import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ConsolaPrincipal extends JFrame {

    private JButton NuevoTablero = new JButton("Nuevo");
    private JButton PosicionInicial = new JButton("Posicion Inicial");
    private JButton Rotar = new JButton("Rotar");
    private JButton Tema = new JButton("Cambiar Tema");
    private JLabel Titulo = new JLabel("Ajedrez en Notacion Fen");
    private Color ColorFondo = new Color(30, 31, 36);

    public ConsolaPrincipal() {
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
        Font uiTitulo = ui.deriveFont(Font.BOLD, 16f);

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
        Titulo.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));

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

        JPanel right = new JPanel(new BorderLayout());

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        Rotar.addActionListener(e -> tablero.Rotar());
        Tema.addActionListener(e -> tablero.CambiarTema());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsolaPrincipal());
    }
}
