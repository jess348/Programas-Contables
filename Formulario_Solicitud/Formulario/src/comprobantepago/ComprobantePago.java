package comprobantepago;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ComprobantePago extends JInternalFrame {

    private JTextField tCod, tCuenta, tParcial, tDebe, tHaber;
    private DefaultTableModel modelo;
    private JLabel lblDebe, lblHaber;

    public ComprobantePago() {
        super("Comprobante de Pago - Empresa Comercial El Tisey S.A.", true, true, true, true);

        setSize(900, 750);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlHeader = new JPanel(new GridLayout(3, 1));
        pnlHeader.setBackground(Color.WHITE);
        JLabel lblEmpresa = new JLabel("Empresa Comercial El Tisey S.A.", JLabel.CENTER);
        lblEmpresa.setFont(new Font("Serif", Font.BOLD, 22));
        pnlHeader.add(lblEmpresa);
        pnlHeader.add(new JLabel("Estelí, Nicaragua | Tel: 2713-9999", JLabel.CENTER));
        
        JLabel lblTituloDoc = new JLabel("COMPROBANTE DE PAGO", JLabel.CENTER);
        lblTituloDoc.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloDoc.setOpaque(true);
        lblTituloDoc.setBackground(new Color(41, 128, 185));
        lblTituloDoc.setForeground(Color.WHITE);
        pnlHeader.add(lblTituloDoc);
        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.setBackground(Color.WHITE);

        String[] col = {"CÓDIGO", "NOMBRE DE LA CUENTA", "PARCIAL", "DEBE", "HABER"};
        modelo = new DefaultTableModel(col, 0);
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlCentro.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pnlInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlInput.setBackground(Color.WHITE);
        pnlInput.setBorder(BorderFactory.createTitledBorder("Registro de Movimientos"));
        
        tCod = new JTextField(5);
        tCuenta = new JTextField(12);
        tParcial = new JTextField("0.0", 6);
        tDebe = new JTextField("0.0", 6);
        tHaber = new JTextField("0.0", 6);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(41, 128, 185)); 
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);

        pnlInput.add(new JLabel("Cod:")); pnlInput.add(tCod);
        pnlInput.add(new JLabel("Cuenta:")); pnlInput.add(tCuenta);
        pnlInput.add(new JLabel("Parcial:")); pnlInput.add(tParcial);
        pnlInput.add(new JLabel("Debe:")); pnlInput.add(tDebe);
        pnlInput.add(new JLabel("Haber:")); pnlInput.add(tHaber);
        pnlInput.add(btnAgregar);

        pnlCentro.add(pnlInput, BorderLayout.SOUTH);
        add(pnlCentro, BorderLayout.CENTER);

        JPanel pnlPie = new JPanel(new BorderLayout());
        pnlPie.setBackground(Color.WHITE);

        JPanel pnlSumas = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 10));
        pnlSumas.setBackground(new Color(245, 245, 245));
        pnlSumas.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        lblDebe = new JLabel("TOTAL DEBE: C$ 0.0");
        lblHaber = new JLabel("TOTAL HABER: C$ 0.0");
        lblDebe.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblHaber.setFont(new Font("SansSerif", Font.BOLD, 13));
        
        pnlSumas.add(lblDebe); pnlSumas.add(lblHaber);
        pnlPie.add(pnlSumas, BorderLayout.NORTH);

        JPanel pnlFirmas = new JPanel(new GridLayout(1, 3, 20, 0));
        pnlFirmas.setBackground(Color.WHITE);
        pnlFirmas.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        pnlFirmas.add(crearCajaFirma("ELABORADO POR:"));
        pnlFirmas.add(crearCajaFirma("REVISADO POR:"));
        pnlFirmas.add(crearCajaFirma("AUTORIZADO POR:"));

        pnlPie.add(pnlFirmas, BorderLayout.CENTER);
        add(pnlPie, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            if (tCod.getText().trim().isEmpty() || tCuenta.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código y Cuenta son obligatorios.");
                return;
            }

            try {
                Transaccion tr = new Transaccion(
                    tCod.getText(),
                    tCuenta.getText(),
                    Double.parseDouble(tParcial.getText()),
                    Double.parseDouble(tDebe.getText()),
                    Double.parseDouble(tHaber.getText())
                );
                
                modelo.addRow(new Object[]{tr.getCod(), tr.getCuenta(), tr.getParcial(), tr.getDebe(), tr.getHaber()});
                
                double d = 0, h = 0;
                for(int i = 0; i < modelo.getRowCount(); i++){
                    d += (double)modelo.getValueAt(i, 3);
                    h += (double)modelo.getValueAt(i, 4);
                }
                lblDebe.setText("TOTAL DEBE: C$ " + d);
                lblHaber.setText("TOTAL HABER: C$ " + h);

                limpiarCampos();

            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese valores numéricos válidos.");
            }
        });
    }

    private void limpiarCampos() {
        tCod.setText("");
        tCuenta.setText("");
        tParcial.setText("0.0");
        tDebe.setText("0.0");
        tHaber.setText("0.0");
        tCod.requestFocus();
    }

    private JPanel crearCajaFirma(String texto) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.add(new JLabel(texto, JLabel.CENTER), BorderLayout.NORTH);
        p.add(new JLabel("______________________", JLabel.CENTER), BorderLayout.CENTER);
        p.add(new JLabel("Firma y Sello", JLabel.CENTER), BorderLayout.SOUTH);
        return p;
    }
}