package FrmFormulario;

import javax.swing.SwingUtilities;

public class MainExternal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SolicitudFrm solicitudFrm = new SolicitudFrm();
            solicitudFrm.setVisible(true);
        });
    }
}

