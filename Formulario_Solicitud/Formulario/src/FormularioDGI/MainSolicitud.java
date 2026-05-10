package FormularioDGI;

import javax.swing.SwingUtilities;

public class MainSolicitud {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SolicitudFormularioFrm solicitudFormularioFrm = new SolicitudFormularioFrm();
            solicitudFormularioFrm.setVisible(true);
        });
    }
}
