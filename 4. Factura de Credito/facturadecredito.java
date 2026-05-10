

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class facturadecredito extends JFrame {
    private JTextField txtCliente, txtDireccion, txtSubTotal, txtIVA, txtTotal;
    private JTable tabla;
    private DefaultTableModel modelo;

    public facturadecredito() {
        // Configuración básica [cite: 10, 28]
        setTitle("Empresa Comercial");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- ENCABEZADO (Igual al diseño anterior) ---
        JPanel pnlHeader = new JPanel(new GridLayout(4, 1));
        pnlHeader.add(new JLabel("Empresa Comercial ", JLabel.CENTER));
        pnlHeader.add(new JLabel("Managua, Nicaragua | No. RUC J0000123456789", JLabel.CENTER));
        
        JLabel lblFactura = new JLabel("Factura de Crédito", JLabel.CENTER);
        lblFactura.setOpaque(true);
        lblFactura.setBackground(Color.PINK);
        pnlHeader.add(lblFactura);

        // --- DATOS DEL CLIENTE ---
        JPanel pnlDatos = new JPanel(new GridLayout(2, 1, 5, 5));
        txtCliente = new JTextField();
        txtDireccion = new JTextField();
        
        JPanel fila1 = new JPanel(new BorderLayout());
        fila1.add(new JLabel("Cliente: "), BorderLayout.WEST);
        fila1.add(txtCliente, BorderLayout.CENTER);
        
        JPanel fila2 = new JPanel(new BorderLayout());
        fila2.add(new JLabel("Dirección: "), BorderLayout.WEST);
        fila2.add(txtDireccion, BorderLayout.CENTER);
        
        pnlDatos.add(fila1);
        pnlDatos.add(fila2);

        // --- TABLA CON LÓGICA AUTOMÁTICA  ---
        String[] columnas = {"CANT.", "DESCRIPCIÓN", "P. UNIT.", "VALOR TOTAL"};
        modelo = new DefaultTableModel(columnas, 10);
        tabla = new JTable(modelo);
        
        // Escuchador para calcular automáticamente al escribir
        modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int fila = e.getFirstRow();
                    int col = e.getColumn();
                    
                    // Si cambia CANT (0) o P.UNIT (2), calculamos VALOR TOTAL (3)
                    if (col == 0 || col == 2) {
                        calcularFila(fila);
                    }
                    calcularGranTotal();
                }
            }
        });

        // --- PANEL DE TOTALES Y BOTÓN LIMPIAR ---
        txtSubTotal = new JTextField(10); txtSubTotal.setEditable(false);
        txtIVA = new JTextField(10); txtIVA.setEditable(false);
        txtTotal = new JTextField(10); txtTotal.setEditable(false);
        
        JButton btnLimpiar = new JButton("Limpiar Formulario");
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });

        JPanel pnlSur = new JPanel(new BorderLayout());
        JPanel pnlTotales = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlTotales.add(new JLabel("Sub Total:")); pnlTotales.add(txtSubTotal);
        pnlTotales.add(new JLabel("IVA (15%):")); pnlTotales.add(txtIVA);
        pnlTotales.add(new JLabel("TOTAL C$:")); pnlTotales.add(txtTotal);

        pnlSur.add(btnLimpiar, BorderLayout.WEST);
        pnlSur.add(pnlTotales, BorderLayout.EAST);

        // Ensamblaje
        JPanel pnlNorte = new JPanel(new BorderLayout());
        pnlNorte.add(pnlHeader, BorderLayout.NORTH);
        pnlNorte.add(pnlDatos, BorderLayout.CENTER);
        
        add(pnlNorte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);
    }

    private void calcularFila(int fila) {
        try {
            double cant = Double.parseDouble(modelo.getValueAt(fila, 0).toString());
            double precio = Double.parseDouble(modelo.getValueAt(fila, 2).toString());
            modelo.setValueAt(cant * precio, fila, 3);
        } catch (Exception e) { /* Ignorar si los datos no son números aún */ }
    }

    private void calcularGranTotal() {
        double subtotal = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valor = modelo.getValueAt(i, 3);
            if (valor != null) {
                subtotal += Double.parseDouble(valor.toString());
            }
        }
        double iva = subtotal * 0.15; // IVA 15% según el requerimiento 
        double total = subtotal + iva;

        txtSubTotal.setText(String.format("%.2f", subtotal));
        txtIVA.setText(String.format("%.2f", iva));
        txtTotal.setText(String.format("%.2f", total));
    }

    private void limpiarFormulario() {
        txtCliente.setText("");
        txtDireccion.setText("");
        txtSubTotal.setText("");
        txtIVA.setText("");
        txtTotal.setText("");
        // Reiniciar tabla
        modelo.setRowCount(0);
        modelo.setRowCount(10);
    }
}