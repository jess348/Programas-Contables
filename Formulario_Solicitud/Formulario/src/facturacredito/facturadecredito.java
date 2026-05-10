package facturacredito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import java.awt.*;

public class facturadecredito extends JInternalFrame {
    private JTextField txtCliente, txtDireccion, txtSubTotal, txtIVA, txtTotal;
    private JTable tabla;
    private DefaultTableModel modelo;
    private boolean isUpdating = false;

    public facturadecredito() {
        // Título y botones de control activados
        super("Factura de Crédito - Empresa Comercial El Tisey S.A.", true, true, true, true);
        setSize(850, 750);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // --- ENCABEZADO ESTILO CONTADO ---
        JPanel pnlHeader = new JPanel(new GridLayout(3, 1));
        pnlHeader.setBackground(Color.WHITE);
        
        JLabel lblEmpresa = new JLabel("Empresa Comercial El Tisey S.A.", JLabel.CENTER);
        lblEmpresa.setFont(new Font("Serif", Font.BOLD, 22));
        
        JLabel lblUbicacion = new JLabel("Estelí, Nicaragua | Tel: 2713-9999", JLabel.CENTER);
        
        JLabel lblTituloDoc = new JLabel("FACTURA DE CRÉDITO", JLabel.CENTER);
        lblTituloDoc.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloDoc.setOpaque(true);
        lblTituloDoc.setBackground(new Color(41, 128, 185)); // Azul profesional para coincidir con Contado
        lblTituloDoc.setForeground(Color.WHITE);

        pnlHeader.add(lblEmpresa);
        pnlHeader.add(lblUbicacion);
        pnlHeader.add(lblTituloDoc);

        // --- DATOS DEL CLIENTE ---
        JPanel pnlDatos = new JPanel(new GridLayout(2, 1, 5, 5));
        pnlDatos.setBackground(Color.WHITE);
        pnlDatos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        txtCliente = new JTextField();
        txtDireccion = new JTextField();
        
        JPanel fila1 = new JPanel(new BorderLayout(10, 0));
        fila1.setBackground(Color.WHITE);
        fila1.add(new JLabel("Nombre del Cliente: "), BorderLayout.WEST);
        fila1.add(txtCliente, BorderLayout.CENTER);
        
        JPanel fila2 = new JPanel(new BorderLayout(10, 0));
        fila2.setBackground(Color.WHITE);
        fila2.add(new JLabel("Dirección Domiciliar: "), BorderLayout.WEST);
        fila2.add(txtDireccion, BorderLayout.CENTER);
        
        pnlDatos.add(fila1);
        pnlDatos.add(fila2);

        // --- TABLA DE PRODUCTOS ---
        String[] columnas = {"CANT.", "DESCRIPCIÓN", "P. UNIT.", "VALOR TOTAL"};
        modelo = new DefaultTableModel(columnas, 15); // 15 filas iniciales
        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        
        // Listener para cálculos
        modelo.addTableModelListener(e -> {
            if (isUpdating) return;
            if (e.getType() == TableModelEvent.UPDATE) {
                int fila = e.getFirstRow();
                int col = e.getColumn();
                if (col == 0 || col == 2) {
                    isUpdating = true;
                    calcularFila(fila);
                    calcularGranTotal();
                    isUpdating = false;
                }
            }
        });

        // --- PANEL DE TOTALES (ESTÉTICA LIMPIA) ---
        JPanel pnlSur = new JPanel(new BorderLayout(20, 0));
        pnlSur.setBackground(Color.WHITE);
        pnlSur.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton btnLimpiar = new JButton("Nueva Factura");
        btnLimpiar.setBackground(new Color(52, 73, 94)); // Gris oscuro profesional
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        JPanel pnlTotales = new JPanel(new GridLayout(3, 2, 10, 5));
        pnlTotales.setBackground(new Color(245, 245, 245));
        pnlTotales.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        txtSubTotal = new JTextField("0.00", 10); txtSubTotal.setEditable(false);
        txtIVA = new JTextField("0.00", 10); txtIVA.setEditable(false);
        txtTotal = new JTextField("0.00", 10); txtTotal.setEditable(false);
        
        // Alineación derecha para números
        txtSubTotal.setHorizontalAlignment(JTextField.RIGHT);
        txtIVA.setHorizontalAlignment(JTextField.RIGHT);
        txtTotal.setHorizontalAlignment(JTextField.RIGHT);

        pnlTotales.add(new JLabel("SUB-TOTAL:")); pnlTotales.add(txtSubTotal);
        pnlTotales.add(new JLabel("I.V.A. (15%):")); pnlTotales.add(txtIVA);
        pnlTotales.add(new JLabel("TOTAL NETO C$:")); pnlTotales.add(txtTotal);

        pnlSur.add(btnLimpiar, BorderLayout.WEST);
        pnlSur.add(pnlTotales, BorderLayout.EAST);

        // --- ENSAMBLAJE ---
        JPanel pnlNorte = new JPanel(new BorderLayout());
        pnlNorte.add(pnlHeader, BorderLayout.NORTH);
        pnlNorte.add(pnlDatos, BorderLayout.CENTER);
        
        add(pnlNorte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);
    }

    private void calcularFila(int fila) {
        try {
            Object cantObj = modelo.getValueAt(fila, 0);
            Object precioObj = modelo.getValueAt(fila, 2);
            if (cantObj != null && precioObj != null) {
                double cant = Double.parseDouble(cantObj.toString());
                double precio = Double.parseDouble(precioObj.toString());
                modelo.setValueAt(cant * precio, fila, 3);
            }
        } catch (NumberFormatException e) {
            modelo.setValueAt(0.00, fila, 3);
        }
    }

    private void calcularGranTotal() {
        double subtotal = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valor = modelo.getValueAt(i, 3);
            if (valor != null) {
                try {
                    subtotal += Double.parseDouble(valor.toString());
                } catch (NumberFormatException e) {}
            }
        }
        double iva = subtotal * 0.15;
        double total = subtotal + iva;

        txtSubTotal.setText(String.format("%.2f", subtotal));
        txtIVA.setText(String.format("%.2f", iva));
        txtTotal.setText(String.format("%.2f", total));
    }

    private void limpiarFormulario() {
        txtCliente.setText("");
        txtDireccion.setText("");
        txtSubTotal.setText("0.00");
        txtIVA.setText("0.00");
        txtTotal.setText("0.00");
        isUpdating = true;
        modelo.setRowCount(0);
        modelo.setRowCount(15);
        isUpdating = false;
        txtCliente.requestFocus();
    }
}