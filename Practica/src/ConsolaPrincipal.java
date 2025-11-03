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
    private JButton Generar = new JButton("Generar");
    private JButton Borrar = new JButton("Borrar");

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
        Font fontGeneral = new Font("Century Gothic", Font.BOLD, 20);

        // Panel titulo

        // Panel botones
        JPanel PanelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        PanelSuperior.setBackground(ColorFondo);

        // Misma configuracion para los botones
        JButton[] botones = { NuevoTablero, PosicionInicial, Rotar, Tema };
        for (JButton b : botones) {
            b.setFont(fontGeneral);
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
        Titulo.setFont(fontGeneral);
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
        Titulo.setFont(new Font("Century Gothic", Font.BOLD, 28));
        Titulo.setHorizontalAlignment(SwingConstants.CENTER);
        Titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        derecha.add(Titulo);
        derecha.add(Box.createVerticalStrut(12));

        // Cadena FEN
        CadenaFen.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        CadenaFen.setLineWrap(true);
        CadenaFen.setBackground(new Color(35, 37, 43));
        CadenaFen.setForeground(new Color(232, 233, 237));
        CadenaFen.setCaretColor(new Color(232, 233, 237));
        CadenaFen.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        CadenaFen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        derecha.add(CadenaFen);
        derecha.add(Box.createVerticalStrut(12));

        // Botones Generar y borrar
        JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        filaBotones.setOpaque(false); // hace que el fondo no este blanco
        filaBotones.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botones
        JButton[] botonesDerecha = { Generar, Borrar };
        for (JButton b : botonesDerecha) {
            b.setFont(fontGeneral);
            b.setForeground(new Color(232, 233, 237));
            b.setBackground(new Color(52, 54, 61));
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
            b.addChangeListener(e -> {
                ButtonModel m = b.getModel();
                b.setBackground(m.isRollover() ? new Color(61, 64, 71) : new Color(52, 54, 61));
            });
            filaBotones.add(b);
        }
        derecha.add(filaBotones);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(derecha, BorderLayout.EAST);

        // Funcionalidad de Botones
        NuevoTablero.addActionListener(e -> {
            tablero.vacio();
            CadenaFen.setText(" ");
        });
        PosicionInicial.addActionListener(e -> tablero.posicionInicial());
        Rotar.addActionListener(e -> tablero.Rotar());
        Tema.addActionListener(e -> tablero.CambiarTema());
        Borrar.addActionListener(e -> CadenaFen.setText(""));
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsolaPrincipal());
    }
}
