package arqueocaja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

public class FrmArqueoCaja extends JInternalFrame {
    private JTextField txtFecha, txtCajero, txtTipoCambio, txtVentasDia;
    private JLabel lblTotalEfectivo, lblIngresosSistema, lblSobranteFaltante;
    private JTable tablaCordobas, tablaDolares;
    
    // Colores institucionales definidos previamente
    private final Color azulEmpresarial = new Color(41, 128, 185);
    private final Color fondoGris = new Color(245, 245, 245);

    public FrmArqueoCaja() {
        // Activamos botones de control (minimizar, maximizar, cerrar)
        super("Arqueo de Caja - Empresa Comercial El Tisey S.A.", true, true, true, true);
        setSize(1200, 800);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        initComponents();
    }

    private void initComponents() {
        // --- ENCABEZADO (Mismo estilo que Factura y Comprobante) ---
        JPanel pnlHeader = new JPanel(new GridLayout(3, 1));
        pnlHeader.setBackground(Color.WHITE);
        
        JLabel lblEmpresa = new JLabel("Empresa Comercial El Tisey S.A.", JLabel.CENTER);
        lblEmpresa.setFont(new Font("Serif", Font.BOLD, 22));
        
        JLabel lblUbicacion = new JLabel("Estelí, Nicaragua | Tel: 2713-9999", JLabel.CENTER);
        
        JLabel lblTituloDoc = new JLabel("ARQUEO DE CAJA DIARIO", JLabel.CENTER);
        lblTituloDoc.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloDoc.setOpaque(true);
        lblTituloDoc.setBackground(azulEmpresarial);
        lblTituloDoc.setForeground(Color.WHITE);

        pnlHeader.add(lblEmpresa);
        pnlHeader.add(lblUbicacion);
        pnlHeader.add(lblTituloDoc);
        add(pnlHeader, BorderLayout.NORTH);

        // --- CUERPO CENTRAL (TABLAS DE DENOMINACIONES) ---
        JPanel pnlTablas = new JPanel(new GridLayout(1, 2, 20, 0));
        pnlTablas.setBackground(Color.WHITE);
        pnlTablas.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        tablaCordobas = crearTablaMoneda("Córdobas (C$)", new double[]{500, 200, 100, 50, 20, 10, 5, 1, 0.50, 0.25});
        tablaDolares = crearTablaMoneda("Dólares (U$)", new double[]{100, 50, 20, 10, 5, 1, 0.50, 0.25, 0.10, 0.05});

        pnlTablas.add(new JScrollPane(tablaCordobas));
        pnlTablas.add(new JScrollPane(tablaDolares));
        add(pnlTablas, BorderLayout.CENTER);

        // --- PANEL INFERIOR (INPUTS Y RESUMEN) ---
        JPanel pnlSur = new JPanel(new BorderLayout(10, 10));
        pnlSur.setBackground(Color.WHITE);
        pnlSur.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        // Formulario de datos
        JPanel pnlDatos = new JPanel(new GridLayout(2, 4, 10, 10));
        pnlDatos.setBackground(fondoGris);
        pnlDatos.setBorder(BorderFactory.createTitledBorder("Información de Cierre"));

        txtFecha = new JTextField();
        txtCajero = new JTextField();
        new JTextField("0.00");
        txtVentasDia = new JTextField("0.00");
        txtTipoCambio = new JTextField("36.62"); // TC actualizado

        pnlDatos.add(new JLabel("Fecha:")); pnlDatos.add(txtFecha);
        pnlDatos.add(new JLabel("Cajero:")); pnlDatos.add(txtCajero);
        pnlDatos.add(new JLabel("Ventas Sistema:")); pnlDatos.add(txtVentasDia);
        pnlDatos.add(new JLabel("T. Cambio:")); pnlDatos.add(txtTipoCambio);

        // Resumen de Totales
        JPanel pnlResultados = new JPanel(new GridLayout(1, 3, 20, 0));
        pnlResultados.setBackground(azulEmpresarial);
        
        lblTotalEfectivo = crearLabelResumen("TOTAL EFECTIVO: C$ 0.00");
        lblIngresosSistema = crearLabelResumen("SISTEMA: C$ 0.00");
        lblSobranteFaltante = crearLabelResumen("DIFERENCIA: C$ 0.00");

        pnlResultados.add(lblTotalEfectivo);
        pnlResultados.add(lblIngresosSistema);
        pnlResultados.add(lblSobranteFaltante);

        pnlSur.add(pnlDatos, BorderLayout.NORTH);
        pnlSur.add(pnlResultados, BorderLayout.CENTER);
        
        // Botonera
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBotones.setBackground(Color.WHITE);
        JButton btnCalcular = new JButton("Procesar Arqueo");
        btnCalcular.setBackground(azulEmpresarial);
        btnCalcular.setForeground(Color.WHITE);
        
        btnCalcular.addActionListener(e -> calcularTotales());
        pnlBotones.add(btnCalcular);
        pnlSur.add(pnlBotones, BorderLayout.SOUTH);

        add(pnlSur, BorderLayout.SOUTH);
    }

    private JTable crearTablaMoneda(String titulo, double[] dens) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"DENOMINACIÓN", "CANTIDAD", "SUBTOTAL"}, 0);
        for (double d : dens) model.addRow(new Object[]{String.format("%.2f", d), 0, "0.00"});
        
        JTable t = new JTable(model);
        t.setRowHeight(25);
        t.getTableHeader().setBackground(azulEmpresarial);
        t.getTableHeader().setForeground(Color.WHITE);
        
        // Lógica de cálculo automático por celda
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            if (e.getColumn() == 1) {
                try {
                    double den = Double.parseDouble(model.getValueAt(row, 0).toString());
                    int cant = Integer.parseInt(model.getValueAt(row, 1).toString());
                    model.setValueAt(String.format("%.2f", den * cant), row, 2);
                } catch (Exception ex) { model.setValueAt("0.00", row, 2); }
            }
        });
        return t;
    }

    private JLabel crearLabelResumen(String texto) {
        JLabel l = new JLabel(texto, JLabel.CENTER);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("SansSerif", Font.BOLD, 14));
        l.setPreferredSize(new Dimension(200, 40));
        return l;
    }

    private void calcularTotales() {
        double totalCordobas = sumarColumna(tablaCordobas);
        double totalDolares = sumarColumna(tablaDolares);
        double tc = Double.parseDouble(txtTipoCambio.getText());
        
        double efectivoContado = totalCordobas + (totalDolares * tc);
        double ventasSistema = Double.parseDouble(txtVentasDia.getText());
        double diferencia = efectivoContado - ventasSistema;

        lblTotalEfectivo.setText("TOTAL EFECTIVO: C$ " + String.format("%.2f", efectivoContado));
        lblIngresosSistema.setText("SISTEMA: C$ " + String.format("%.2f", ventasSistema));
        lblSobranteFaltante.setText("DIFERENCIA: C$ " + String.format("%.2f", diferencia));

        if (diferencia < 0) lblSobranteFaltante.setForeground(Color.YELLOW);
        else lblSobranteFaltante.setForeground(Color.WHITE);
    }

    private double sumarColumna(JTable t) {
        double s = 0;
        for (int i = 0; i < t.getRowCount(); i++) {
            s += Double.parseDouble(t.getValueAt(i, 2).toString());
        }
        return s;
    }
}