package ArqueoCaja;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmArqueoCaja().setVisible(true);
        });
    }
}
