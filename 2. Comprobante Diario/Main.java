
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {   
        // Esto lanza tu formulario (clase JFrame)
        SwingUtilities.invokeLater(() -> {
            new comprobantediario().setVisible(true);
        });
    }
}