package FrmFormulario;

// import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
// import javax.swing.JInternalFrame;
import javax.swing.JFrame;
// import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import FormularioDGI.SolicitudFormularioFrm;

// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.SwingConstants;
import java.awt.BorderLayout;
// import java.awt.Font;

import comprobantepago.ComprobantePago;
import comprobantediario.comprobantediario;
import facturacontado.FrmFacturaContado;
import facturacredito.facturadecredito;
import arqueocaja.FrmArqueoCaja;
import FormularioDGI.SolicituDEmpleo;
// import FormularioDGI.SolicitudFormularioFrm;

/**
 * Clase principal que representa el formulario de solicitud principal de la
 * aplicación contable.
 * Esta clase extiende JFrame y proporciona una interfaz gráfica con un menú
 * para acceder a diferentes
 * formularios relacionados con comprobantes de pago, diarios, facturas y
 * arqueos de caja.
 * El propósito es servir como punto de entrada para navegar entre los
 * diferentes módulos contables.
 */
public class SolicitudFrm extends JFrame {

    private final VentManager ventManager = new VentManager();
    private JDesktopPane desktopPane;

    /**
     * Constructor de la clase SolicitudFrm.
     * Inicializa la ventana principal configurando el título, tamaño, operación de
     * cierre,
     * ubicación centrada en la pantalla, la barra de menú y los componentes
     * iniciales.
     */
    public SolicitudFrm() {
        setTitle("Formulario de Solicitud");
        setSize(1250, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setJMenuBar(createMenuBar());
        initComponents();
    }

    /**
     * Método que crea y configura la barra de menú de la aplicación.
     * Crea menús para cada tipo de formulario contable (pago, diario, facturas,
     * arqueo, DGI)
     * y añade un elemento de menú "Abrir" a cada uno que permite abrir el
     * formulario correspondiente.
     * Retorna la JMenuBar configurada.
     */ // private JMenuBar createMenuBar() {
    // JMenuBar menuBar = new JMenuBar();
    // String[] menuNames = {"Comprobante de Pago", "Comprobante Diario", "Factura
    // De Contado", "Factura de Credito", "Arqueo De Caja", "Formulario DGI"};
    // for (String name : menuNames) {
    // JMenu menu = new JMenu(name);
    // menuBar.add(menu);
    // }
    // return menuBar;
    // }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        String[] menuNom = {
                "Comprobante de Pago",
                "Comprobante Diario",
                "Factura De Contado",
                "Factura de Credito",
                "Arqueo De Caja",
                "Formulario DGI",
                "Formulario De Solicitud De Empleo"
                // "Solicitud de Trabajo"
        };

        for (String name : menuNom) {
            JMenu menu = new JMenu(name);
            JMenuItem item = new JMenuItem("Abrir " + name);
            item.addActionListener(e -> openSubFrame(name));
            menu.add(item);
            menuBar.add(menu);
        }

        return menuBar;
    }

    
     //Método que abre un subformulario basado en el título proporcionado.
     //Utiliza un switch para determinar qué clase JFrame instanciar según el título
     //del menú seleccionado.
     //Configura la nueva ventana para que se cierre sin terminar la aplicación y la
     //muestra centrada
     //respecto a la ventana principal.
     //@param owner La ventana principal (padre) para centrar la nueva ventana.
     //@param title El título del menú seleccionado, que determina qué formulario abrir.
    
    private void openSubFrame(String title) {
        switch (title) {
            case "Comprobante de Pago":
            ventManager.openInternal(desktopPane, new ComprobantePago());
            
            // // Crear la instancia de la ventana interna
            // ComprobantePago ventanaPago = new ComprobantePago();
            // desktopPane.add(ventanaPago);
            // ventanaPago.setVisible(true);
            // try {
            //     ventanaPago.setSelected(true);
            // } catch (java.beans.PropertyVetoException e) {
            //     System.out.println("Error al seleccionar la ventana: " + e.getMessage());
            // }
            break;

            case "Comprobante Diario":
                ventManager.openInternal(desktopPane, new comprobantediario());
            //     ventManager.open(this, new comprobantediario());
                break;
            case "Factura De Contado":
                ventManager.openInternal(desktopPane, new FrmFacturaContado());
            //     JInternalFrame internalFactura = new FrmFacturaContado();
            //     desktopPane.add(internalFactura);
            //     internalFactura.setVisible(true);
            //     try {
            //         internalFactura.setSelected(true);
            //     } catch (java.beans.PropertyVetoException ex) {
            //         // ignore
            //     }
                break;
            case "Factura de Credito":
                ventManager.openInternal(desktopPane, new facturadecredito());
            //     ventManager.open(this, new facturadecredito());
                break;
            case "Arqueo De Caja":
                ventManager.openInternal(desktopPane, new FrmArqueoCaja());
            //     ventManager.open(this, new FrmArqueoCaja());
                break;
            case "Formulario DGI":
                ventManager.openInternal(desktopPane, new SolicitudFormularioFrm());
            //     ventManager.open(this, new SolicitudFormularioFrm());
                break;
            case "Formulario De Solicitud De Empleo":
                ventManager.openInternal(desktopPane, new SolicituDEmpleo());
                break;

            // default:
            //     JFrame frame = new JFrame(title);
            //     frame.setSize(400, 300);
            //     frame.add(new JLabel("Contenido de " + title, SwingConstants.CENTER), BorderLayout.CENTER);
            //     ventManager.open(this, frame);
        }
    }

    // Método que inicializa los componentes principales de la interfaz gráfica.
    // Crea un panel de contenido con un diseño BorderLayout y añade una etiqueta de
    // título
    // centrada con un borde vacío para espaciado. El panel se añade al centro del
    // contenedor principal.

    private void initComponents() {
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);
    }

    // private final VentManager VentManager = new VentManager();

    // luego en openSubFrame:
    // VentManager.open(this, frame);

    // private JFrame activeFrame;{
    // if (activeFrame != null && activeFrame.isDisplayable()) {
    // JOptionPane.showMessageDialog(
    // this,
    // "Solo puede abrir una ventana a la vez.",
    // "Error",
    // JOptionPane.ERROR_MESSAGE
    // );
    // return;
    // }
    // }
    // frame.addWindowListener(new WindowAdapter() {
    // @Override
    // public void windowClosed(WindowEvent e) {
    // activeFrame = null;
    // }
    // });

}
