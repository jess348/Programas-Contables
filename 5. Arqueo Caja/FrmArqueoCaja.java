package arqueocaja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase principal de la aplicación de Arqueo de Caja.
 * Esta clase crea una interfaz gráfica usando Swing para gestionar el arqueo de caja.
 */
public class FrmArqueoCaja extends JFrame {
    private ArqueoCaja arqueoCaja;

    private JTextField txtFecha;
    private JTextField txtHoraCierre;
    private JTextField txtCajero;
    private JTextField txtFacturaInicial;
    private JTextField txtFacturaFinal;
    private JTextField txtTipoCambio;
    private JTextField txtEfectivoInicial;
    private JTextField txtVentasDia;

    private JLabel lblTotalEfectivo;
    private JLabel lblIngresosSistema;
    private JLabel lblSobranteFaltante;

    private JTable tablaCordobas;
    private JTable tablaDolares;

    public FrmArqueoCaja() {
        arqueoCaja = new ArqueoCaja();
        setTitle("Arqueo de Caja");
        setSize(1300, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        Font tituloFont = new Font("Arial", Font.BOLD, 16);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color bgColor = new Color(245, 246, 250);
        Color headerColor = new Color(33, 103, 178);
        Color headerTextColor = Color.WHITE;

        getContentPane().setBackground(bgColor);

        // Panel superior con información de la empresa
        JPanel panelEmpresa = new JPanel(new GridLayout(4, 1, 4, 4));
        panelEmpresa.setBackground(bgColor);
        panelEmpresa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblEmpresaNombre = new JLabel("Empresa Comercial El Tisey S.A.", SwingConstants.CENTER);
        lblEmpresaNombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblEmpresaNombre.setForeground(headerColor);
        panelEmpresa.add(lblEmpresaNombre);

        JLabel lblEmpresaDireccion = new JLabel("Estelí, Nicaragua", SwingConstants.CENTER);
        lblEmpresaDireccion.setFont(labelFont);
        panelEmpresa.add(lblEmpresaDireccion);

        JLabel lblEmpresaTelefono = new JLabel("Teléfono: No. 2713-9999", SwingConstants.CENTER);
        lblEmpresaTelefono.setFont(labelFont);
        panelEmpresa.add(lblEmpresaTelefono);

        JLabel lblEmpresaExtra = new JLabel("del Semáforo del Parque Central 1 cuadra al Norte", SwingConstants.CENTER);
        lblEmpresaExtra.setFont(labelFont);
        panelEmpresa.add(lblEmpresaExtra);

        // Panel de encabezado con campos
        JPanel panelEncabezado = new JPanel(new GridBagLayout());
        panelEncabezado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelEncabezado.setBackground(bgColor);
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.insets = new Insets(5, 5, 5, 5);
        gbcHeader.anchor = GridBagConstraints.WEST;

        // Fila 1
        gbcHeader.gridx = 0; gbcHeader.gridy = 0;
        panelEncabezado.add(new JLabel("Fecha:"), gbcHeader);
        gbcHeader.gridx = 1; gbcHeader.fill = GridBagConstraints.HORIZONTAL; gbcHeader.weightx = 1.0;
        txtFecha = new JTextField();
        txtFecha.setFont(labelFont);
        panelEncabezado.add(txtFecha, gbcHeader);

        gbcHeader.gridx = 2; gbcHeader.fill = GridBagConstraints.NONE; gbcHeader.weightx = 0;
        panelEncabezado.add(new JLabel("Hora de Cierre:"), gbcHeader);
        gbcHeader.gridx = 3; gbcHeader.fill = GridBagConstraints.HORIZONTAL; gbcHeader.weightx = 1.0;
        txtHoraCierre = new JTextField();
        txtHoraCierre.setFont(labelFont);
        panelEncabezado.add(txtHoraCierre, gbcHeader);

        // Espacio vacío
        gbcHeader.gridx = 4; gbcHeader.fill = GridBagConstraints.NONE; gbcHeader.weightx = 0;
        panelEncabezado.add(new JLabel(""), gbcHeader);

        // Fila 2
        gbcHeader.gridx = 0; gbcHeader.gridy = 1;
        panelEncabezado.add(new JLabel("Cajero(a):"), gbcHeader);
        gbcHeader.gridx = 1; gbcHeader.fill = GridBagConstraints.HORIZONTAL; gbcHeader.weightx = 1.0;
        txtCajero = new JTextField();
        txtCajero.setFont(labelFont);
        panelEncabezado.add(txtCajero, gbcHeader);

        gbcHeader.gridx = 2; gbcHeader.fill = GridBagConstraints.NONE; gbcHeader.weightx = 0;
        panelEncabezado.add(new JLabel("Factura Inicial:"), gbcHeader);
        gbcHeader.gridx = 3; gbcHeader.fill = GridBagConstraints.HORIZONTAL; gbcHeader.weightx = 1.0;
        txtFacturaInicial = new JTextField();
        txtFacturaInicial.setFont(labelFont);
        panelEncabezado.add(txtFacturaInicial, gbcHeader);

        gbcHeader.gridx = 4; gbcHeader.fill = GridBagConstraints.NONE; gbcHeader.weightx = 0;
        panelEncabezado.add(new JLabel("Factura Final:"), gbcHeader);
        gbcHeader.gridx = 5; gbcHeader.fill = GridBagConstraints.HORIZONTAL; gbcHeader.weightx = 1.0;
        txtFacturaFinal = new JTextField();
        txtFacturaFinal.setFont(labelFont);
        panelEncabezado.add(txtFacturaFinal, gbcHeader);

        // Panel superior combinado
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(bgColor);
        panelSuperior.add(panelEmpresa, BorderLayout.NORTH);
        panelSuperior.add(panelEncabezado, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);

        // Crear tablas con denominaciones
        tablaCordobas = crearTablaMoneda(labelFont, tituloFont, headerColor, headerTextColor, new double[]{500, 200, 100, 50, 20, 10, 5, 1, 0.50, 0.25});
        tablaDolares = crearTablaMoneda(labelFont, tituloFont, headerColor, headerTextColor, new double[]{100, 50, 20, 10, 5, 1, 0.50, 0.25, 0.10, 0.05});

        // Panel central con dos tablas separadas
        JPanel panelTablas = new JPanel(new GridLayout(1, 2, 20, 10));
        panelTablas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTablas.setBackground(bgColor);

        JPanel panelCordobas = new JPanel(new BorderLayout());
        panelCordobas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(headerColor, 2), "Córdobas C$", 0, 0, tituloFont, headerColor));
        panelCordobas.setBackground(bgColor);
        panelCordobas.add(new JScrollPane(tablaCordobas), BorderLayout.CENTER);
        panelTablas.add(panelCordobas);

        JPanel panelDolares = new JPanel(new BorderLayout());
        panelDolares.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(headerColor, 2), "Dólares U$", 0, 0, tituloFont, headerColor));
        panelDolares.setBackground(bgColor);
        panelDolares.add(new JScrollPane(tablaDolares), BorderLayout.CENTER);
        panelTablas.add(panelDolares);

        add(panelTablas, BorderLayout.CENTER);

        // Panel de resumen
        JPanel panelResumen = new JPanel(new GridBagLayout());
        panelResumen.setBackground(bgColor);
        panelResumen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(headerColor, 2), "Resumen", 0, 0, tituloFont, headerColor));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelResumen.add(new JLabel("Efectivo Inicial:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtEfectivoInicial = new JTextField();
        txtEfectivoInicial.setFont(labelFont);
        panelResumen.add(txtEfectivoInicial, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelResumen.add(new JLabel("Ventas del Día:"), gbc);
        gbc.gridx = 1;
        txtVentasDia = new JTextField();
        txtVentasDia.setFont(labelFont);
        panelResumen.add(txtVentasDia, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelResumen.add(new JLabel("Tipo de Cambio (TC):"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtTipoCambio = new JTextField("35.00");
        txtTipoCambio.setFont(labelFont);
        panelResumen.add(txtTipoCambio, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelResumen.add(new JLabel("Total Efectivo:"), gbc);
        gbc.gridx = 1;
        lblTotalEfectivo = new JLabel("0.00");
        lblTotalEfectivo.setFont(labelFont);
        panelResumen.add(lblTotalEfectivo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelResumen.add(new JLabel("Ingresos en Sistema:"), gbc);
        gbc.gridx = 1;
        lblIngresosSistema = new JLabel("0.00");
        lblIngresosSistema.setFont(labelFont);
        panelResumen.add(lblIngresosSistema, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelResumen.add(new JLabel("Sobrante / Faltante:"), gbc);
        gbc.gridx = 1;
        lblSobranteFaltante = new JLabel("0.00");
        lblSobranteFaltante.setFont(labelFont);
        panelResumen.add(lblSobranteFaltante, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnCalcular = new JButton("Calcular Totales");
        btnCalcular.setFont(buttonFont);
        btnCalcular.setBackground(headerColor);
        btnCalcular.setForeground(headerTextColor);
        btnCalcular.setFocusPainted(false);
        panelResumen.add(btnCalcular, gbc);

        gbc.gridx = 1;
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(buttonFont);
        btnLimpiar.setBackground(Color.RED);
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        panelResumen.add(btnLimpiar, gbc);

        add(panelResumen, BorderLayout.SOUTH);

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularResumen();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private JTable crearTablaMoneda(Font labelFont, Font tituloFont, Color headerBg, Color headerFg, double[] denominaciones) {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"DENOMINACIÓN", "CANTIDAD", "MONTO"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Solo cantidad editable
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return String.class;
            }
        };

        for (double den : denominaciones) {
            modelo.addRow(new Object[]{String.format("%.2f", den), "0", "0.00"});
        }

        JTable tabla = new JTable(modelo);
        tabla.setFont(labelFont);
        tabla.setRowHeight(24);
        tabla.getTableHeader().setFont(tituloFont);
        tabla.getTableHeader().setBackground(headerBg);
        tabla.getTableHeader().setForeground(headerFg);

        // Listener para recalcular monto al cambiar cantidad
        modelo.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int col = e.getColumn();
            if (col == 1) { // Columna cantidad
                try {
                    double den = Double.parseDouble((String) modelo.getValueAt(row, 0));
                    String cantStr = (String) modelo.getValueAt(row, 1);
                    if (cantStr == null || cantStr.trim().isEmpty()) cantStr = "0";
                    int cant = Integer.parseInt(cantStr.trim());
                    double monto = den * cant;
                    modelo.setValueAt(String.format("%.2f", monto), row, 2);
                } catch (NumberFormatException ex) {
                    modelo.setValueAt("0.00", row, 2);
                }
            }
        });

        return tabla;
    }

    private void calcularResumen() {
        // Validaciones
        if (txtFecha.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la fecha.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtFecha.requestFocus();
            return;
        }
        if (txtHoraCierre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la hora de cierre.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtHoraCierre.requestFocus();
            return;
        }
        if (txtCajero.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el cajero.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtCajero.requestFocus();
            return;
        }

        double efectivoInicial, ventasDia, tc;
        try {
            efectivoInicial = Double.parseDouble(txtEfectivoInicial.getText().trim());
            ventasDia = Double.parseDouble(txtVentasDia.getText().trim());
            tc = Double.parseDouble(txtTipoCambio.getText().trim());
            if (tc <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Usar ArqueoCaja para lógica
        arqueoCaja.realizarArqueo(efectivoInicial, 0); // Efectivo contado se calcula después
        arqueoCaja.agregarVenta(ventasDia); // Asumir ventasDia como una venta total

        double totalCordobas = sumarTabla(tablaCordobas);
        double totalDolares = sumarTabla(tablaDolares);
        double totalEfectivoContado = totalCordobas + (totalDolares * tc);

        // Setear efectivo contado en servicio
        arqueoCaja.getArqueoServicio().setEfectivoContado(totalEfectivoContado);

        double totalVentas = arqueoCaja.getTotalVentas();
        double efectivoEsperado = arqueoCaja.getEfectivoEsperado();
        double diferencia = arqueoCaja.getDiferencia();

        lblTotalEfectivo.setText(String.format("%.2f", totalEfectivoContado));
        lblIngresosSistema.setText(String.format("%.2f", efectivoEsperado));
        lblSobranteFaltante.setText(String.format("%.2f", diferencia));

        String mensaje;
        if (diferencia == 0) {
            mensaje = "Arqueo cuadrado.";
        } else if (diferencia > 0) {
            mensaje = "Sobrante de efectivo.";
        } else {
            mensaje = "Faltante de efectivo.";
        }
        JOptionPane.showMessageDialog(this, mensaje, "Resultado del Arqueo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos() {
        arqueoCaja.limpiarArqueo();

        txtFecha.setText("");
        txtHoraCierre.setText("");
        txtCajero.setText("");
        txtFacturaInicial.setText("");
        txtFacturaFinal.setText("");
        txtEfectivoInicial.setText("");
        txtVentasDia.setText("");
        txtTipoCambio.setText("35.00");

        lblTotalEfectivo.setText("0.00");
        lblIngresosSistema.setText("0.00");
        lblSobranteFaltante.setText("0.00");

        // Limpiar tablas
        limpiarTabla(tablaCordobas);
        limpiarTabla(tablaDolares);
    }

    private void limpiarTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt("0", i, 1);
            modelo.setValueAt("0.00", i, 2);
        }
    }

    private double sumarTabla(JTable tabla) {
        double total = 0;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Object valor = tabla.getValueAt(i, 2);
            if (valor != null && !valor.toString().trim().isEmpty()) {
                try {
                    total += Double.parseDouble(valor.toString().trim());
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmArqueoCaja().setVisible(true));
    }
}