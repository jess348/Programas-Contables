import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class comprobantediario extends JFrame {
    private JTextField txtCodigo, txtCuenta, txtDebe, txtHaber;
    private JTextField txtElaborado, txtRevisado, txtAutorizado; // Campos de firma rellenables
    private JLabel lblTotalDebe, lblTotalHaber;
    private JTable tabla;
    private DefaultTableModel modelo;
    private double sumaDebe = 0, sumaHaber = 0;

    public comprobantediario() {
        setTitle("UNI - Comprobante de Diario");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));

        // --- ENCABEZADO ---
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1));
        JLabel lblEmpresa = new JLabel("Empresa Comercial ", JLabel.CENTER);
        lblEmpresa.setFont(new Font("Arial", Font.BOLD, 18));
        pnlHeader.add(lblEmpresa);
        pnlHeader.add(new JLabel("COMPROBANTE DE DIARIO", JLabel.CENTER));
        add(pnlHeader, BorderLayout.NORTH);

        // --- TABLA CONTABLE (CENTRO) ---
        String[] columnas = {"Código", "Nombre de la Cuenta", "Debe (C$)", "Haber (C$)"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // --- PANEL INFERIOR (ENTRADAS + TOTALES + FIRMAS) ---
        JPanel pnlInferiorCompleto = new JPanel(new BorderLayout(10, 10));

        // 1. Inputs para agregar datos
        JPanel pnlInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Registro de Movimientos"));
        txtCodigo = new JTextField(5);
        txtCuenta = new JTextField(15);
        txtDebe = new JTextField("0.0", 7);
        txtHaber = new JTextField("0.0", 7);
        JButton btnAgregar = new JButton("Agregar a Tabla");
        
        pnlInput.add(new JLabel("Cod:")); pnlInput.add(txtCodigo);
        pnlInput.add(new JLabel("Cuenta:")); pnlInput.add(txtCuenta);
        pnlInput.add(new JLabel("Debe:")); pnlInput.add(txtDebe);
        pnlInput.add(new JLabel("Haber:")); pnlInput.add(txtHaber);
        pnlInput.add(btnAgregar);

        // 2. Sumas Iguales y Firmas
        JPanel pnlContabilidad = new JPanel(new GridLayout(3, 1, 10, 10));
        
        // Sumas Iguales
        JPanel pnlSumas = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlSumas.setBackground(new Color(230, 230, 230));
        lblTotalDebe = new JLabel("SUMAS IGUALES: C$ 0.0");
        lblTotalHaber = new JLabel("C$ 0.0");
        lblTotalDebe.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotalHaber.setFont(new Font("Arial", Font.BOLD, 13));
        pnlSumas.add(lblTotalDebe);
        pnlSumas.add(new JLabel(" | "));
        pnlSumas.add(lblTotalHaber);

        // Sección de Firmas Rellenables
        JPanel pnlFirmas = new JPanel(new GridLayout(1, 3, 30, 0));
        pnlFirmas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlFirmas.add(crearCajaFirma("ELABORADO POR:", "E"));
        pnlFirmas.add(crearCajaFirma("REVISADO POR:", "R"));
        pnlFirmas.add(crearCajaFirma("AUTORIZADO POR:", "A"));

        // Botón Limpiar
        JButton btnLimpiar = new JButton("Limpiar Comprobante Completo");
        btnLimpiar.setBackground(new Color(255, 200, 200));

        pnlContabilidad.add(pnlSumas);
        pnlContabilidad.add(pnlFirmas);
        pnlContabilidad.add(btnLimpiar);

        pnlInferiorCompleto.add(pnlInput, BorderLayout.NORTH);
        pnlInferiorCompleto.add(pnlContabilidad, BorderLayout.CENTER);
        
        add(pnlInferiorCompleto, BorderLayout.SOUTH);

        // --- LOGICA DE EVENTOS ---
        btnAgregar.addActionListener(e -> agregarFila());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
    }

    // Método para crear las líneas de firma donde se puede escribir
    private JPanel crearCajaFirma(String titulo, String tipo) {
        JPanel p = new JPanel(new BorderLayout());
        JTextField txtFirma = new JTextField();
        txtFirma.setHorizontalAlignment(JTextField.CENTER);
        // MatteBorder crea la línea inferior solamente
        txtFirma.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtFirma.setBackground(new Color(250, 250, 250));

        if (tipo.equals("E")) txtElaborado = txtFirma;
        else if (tipo.equals("R")) txtRevisado = txtFirma;
        else if (tipo.equals("A")) txtAutorizado = txtFirma;

        p.add(new JLabel(titulo, JLabel.CENTER), BorderLayout.NORTH);
        p.add(txtFirma, BorderLayout.CENTER);
        return p;
    }

    private void agregarFila() {
        try {
            double d = Double.parseDouble(txtDebe.getText());
            double h = Double.parseDouble(txtHaber.getText());
            
            modelo.addRow(new Object[]{txtCodigo.getText(), txtCuenta.getText(), d, h});
            
            // Actualización de Sumas Iguales
            sumaDebe += d;
            sumaHaber += h;
            lblTotalDebe.setText("SUMAS IGUALES: C$ " + sumaDebe);
            lblTotalHaber.setText("C$ " + sumaHaber);
            
            // Limpiar solo los campos de entrada para la siguiente fila
            txtCodigo.setText(""); txtCuenta.setText("");
            txtDebe.setText("0.0"); txtHaber.setText("0.0");
            txtCodigo.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese montos válidos.");
        }
    }

    private void limpiarFormulario() {
        modelo.setRowCount(0);
        sumaDebe = 0; sumaHaber = 0;
        lblTotalDebe.setText("SUMAS IGUALES: C$ 0.0");
        lblTotalHaber.setText("C$ 0.0");
        txtCodigo.setText(""); txtCuenta.setText("");
        txtDebe.setText("0.0"); txtHaber.setText("0.0");
        txtElaborado.setText(""); txtRevisado.setText(""); txtAutorizado.setText("");
        txtCodigo.requestFocus();
    }
}