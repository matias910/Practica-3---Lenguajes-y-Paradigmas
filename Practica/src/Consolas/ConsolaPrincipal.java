package Consolas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ConsolaPrincipal extends JFrame {

    private JTextField FenString = new JTextField();
    private JLabel TextoInicial = new JLabel();
    private JButton BotonEnviarCadena = new JButton();
    private JButton BotonLimpiarTablero = new JButton();
    private Tablero tablero = new Tablero();

    public ConsolaPrincipal() {
        setVisible(true);
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Texto Inicial //
        this.add(TextoInicial);
        TextoInicial.setBounds(300, 10, 400, 40);
        TextoInicial.setText("Ingresa la cadena FEN:");
        TextoInicial.setFont(new Font("Georgia", Font.ITALIC, 24));
        TextoInicial.setForeground(new Color(90, 40, 10));

        // Campo De Texto //
        this.add(FenString);
        FenString.setBounds(20, 70, 520, 40);
        FenString.setFont(new Font("Georgia", Font.ITALIC, 16));

        // Botón enviar Cadena //
        this.add(BotonEnviarCadena);
        BotonEnviarCadena.setBounds(560, 70, 90, 40);
        BotonEnviarCadena.setText("Generar");
        BotonEnviarCadena.setFont(new Font("Georgia", Font.ITALIC, 14));
        BotonEnviarCadena.setForeground(new Color(90, 40, 10));

        // Boton Limpiar Tablero//
        this.add(BotonLimpiarTablero);
        BotonLimpiarTablero.setBounds(670, 70, 90, 40);
        BotonLimpiarTablero.setText("Limpiar");
        BotonLimpiarTablero.setFont(new Font("Georgia", Font.ITALIC, 14));
        BotonLimpiarTablero.setForeground(new Color(90, 40, 10));

        // Tablero //
        this.add(tablero, BorderLayout.CENTER);
        tablero.setBounds(20, 130, 500, 500);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsolaPrincipal());
    }

}
