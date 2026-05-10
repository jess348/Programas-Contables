package comprobantediario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class comprobantediario extends JInternalFrame {
    private JTextField txtCodigo, txtCuenta, txtDebe, txtHaber;
    private JTextField txtElaborado, txtRevisado, txtAutorizado; 
    private JLabel lblTotalDebe, lblTotalHaber;
    private JTable tabla;
    private DefaultTableModel modelo;
    private double sumaDebe = 0, sumaHaber = 0;

    public comprobantediario() {
        super("UNI - Comprobante de Diario - El Tisey S.A.", true, true, true, true);
        setSize(850, 750);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(15, 15));

        // --- ENCABEZADO ---
        JPanel pnlHeader = new JPanel(new GridLayout(3, 1));
        pnlHeader.setBackground(Color.WHITE);
        
        JLabel lblEmpresa = new JLabel("Empresa Comercial El Tisey S.A.", JLabel.CENTER);
        lblEmpresa.setFont(new Font("Serif", Font.BOLD, 22));
        
        JLabel lblUbicacion = new JLabel("Estelí, Nicaragua | Tel: 2713-9999", JLabel.CENTER);
        
        JLabel lblTituloDoc = new JLabel("COMPROBANTE DE DIARIO", JLabel.CENTER);
        lblTituloDoc.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloDoc.setOpaque(true);
        lblTituloDoc.setBackground(new Color(41, 128, 185));
        lblTituloDoc.setForeground(Color.WHITE);

        pnlHeader.add(lblEmpresa);
        pnlHeader.add(lblUbicacion);
        pnlHeader.add(lblTituloDoc);
        add(pnlHeader, BorderLayout.NORTH);

        // --- TABLA CONTABLE ---
        String[] columnas = {"Código", "Nombre de la Cuenta", "Debe (C$)", "Haber (C$)"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // --- PANEL INFERIOR ---
        JPanel pnlInferiorCompleto = new JPanel(new BorderLayout(10, 10));
        pnlInferiorCompleto.setBackground(Color.WHITE);

        JPanel pnlInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlInput.setBackground(Color.WHITE);
        pnlInput.setBorder(BorderFactory.createTitledBorder("Registro de Movimientos"));
        
        txtCodigo = new JTextField(5);
        txtCuenta = new JTextField(15);
        txtDebe = new JTextField("0.0", 7);
        txtHaber = new JTextField("0.0", 7);
        
        JButton btnAgregar = new JButton("Agregar a Tabla");
        btnAgregar.setBackground(new Color(41, 128, 185));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        
        pnlInput.add(new JLabel("Cod:")); pnlInput.add(txtCodigo);
        pnlInput.add(new JLabel("Cuenta:")); pnlInput.add(txtCuenta);
        pnlInput.add(new JLabel("Debe:")); pnlInput.add(txtDebe);
        pnlInput.add(new JLabel("Haber:")); pnlInput.add(txtHaber);
        pnlInput.add(btnAgregar);

        JPanel pnlContabilidad = new JPanel(new BorderLayout());
        pnlContabilidad.setBackground(Color.WHITE);
        
        JPanel pnlSumas = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 10));
        pnlSumas.setBackground(new Color(245, 245, 245));
        pnlSumas.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        lblTotalDebe = new JLabel("TOTAL DEBE: C$ 0.0");
        lblTotalHaber = new JLabel("TOTAL HABER: C$ 0.0");
        lblTotalDebe.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblTotalHaber.setFont(new Font("SansSerif", Font.BOLD, 13));
        pnlSumas.add(lblTotalDebe); pnlSumas.add(lblTotalHaber);

        JPanel pnlFirmas = new JPanel(new GridLayout(1, 3, 30, 0));
        pnlFirmas.setBackground(Color.WHITE);
        pnlFirmas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlFirmas.add(crearCajaFirma("ELABORADO POR:", "E"));
        pnlFirmas.add(crearCajaFirma("REVISADO POR:", "R"));
        pnlFirmas.add(crearCajaFirma("AUTORIZADO POR:", "A"));

        JButton btnLimpiar = new JButton("Limpiar Comprobante Completo");
        btnLimpiar.setBackground(new Color(52, 73, 94));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);

        pnlContabilidad.add(pnlSumas, BorderLayout.NORTH);
        pnlContabilidad.add(pnlFirmas, BorderLayout.CENTER);
        pnlContabilidad.add(btnLimpiar, BorderLayout.SOUTH);

        pnlInferiorCompleto.add(pnlInput, BorderLayout.NORTH);
        pnlInferiorCompleto.add(pnlContabilidad, BorderLayout.CENTER);
        add(pnlInferiorCompleto, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarFila());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
    }

    private JPanel crearCajaFirma(String titulo, String tipo) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        JTextField txtFirma = new JTextField();
        txtFirma.setHorizontalAlignment(JTextField.CENTER);
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
            
            sumaDebe += d;
            sumaHaber += h;
            lblTotalDebe.setText("TOTAL DEBE: C$ " + sumaDebe);
            lblTotalHaber.setText("TOTAL HABER: C$ " + sumaHaber);
            
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
        lblTotalDebe.setText("TOTAL DEBE: C$ 0.0");
        lblTotalHaber.setText("TOTAL HABER: C$ 0.0");
        txtCodigo.setText(""); txtCuenta.setText("");
        txtDebe.setText("0.0"); txtHaber.setText("0.0");
        txtElaborado.setText(""); txtRevisado.setText(""); txtAutorizado.setText("");
        txtCodigo.requestFocus();
    }
}