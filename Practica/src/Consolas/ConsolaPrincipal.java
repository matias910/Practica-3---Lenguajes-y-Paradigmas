package Consolas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ConsolaPrincipal extends JFrame {

    private JTextField FenString = new JTextField();
    private JLabel TextoInicial = new JLabel();
    private JButton BotonEnviarCadena = new JButton();

    public ConsolaPrincipal() {
        setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Texto Inicial //
        this.add(TextoInicial);
        TextoInicial.setBounds(250, 20, 200, 40);
        TextoInicial.setText("Ingresa la cadena FEN: ");

        // Campo De Texto //
        this.add(FenString);
        FenString.setBounds(20, 70, 500, 40);

        // Botón enviar Cadena //
        this.add(BotonEnviarCadena);
        BotonEnviarCadena.setBounds(550, 70, 100, 40);
        BotonEnviarCadena.setText("Generar");

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsolaPrincipal());
    }

}
