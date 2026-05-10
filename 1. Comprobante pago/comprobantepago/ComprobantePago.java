
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import comprobantepago.Transaccion;

import java.awt.*;

public class ComprobantePago extends JFrame {
    private JTextField tCod, tCuenta, tParcial, tDebe, tHaber;
    private DefaultTableModel modelo;
    private JLabel lblDebe, lblHaber;

    public ComprobantePago() {
        setTitle("Empresa Comercial El Tisey S.A. - Comprobante de Pago");
        setSize(900, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlHeader = new JPanel(new GridLayout(3, 1));
        pnlHeader.setBackground(Color.WHITE);
        JLabel lblEmpresa = new JLabel("Empresa Comercial El Tisey S.A.", JLabel.CENTER);
        lblEmpresa.setFont(new Font("Serif", Font.BOLD, 22));
        pnlHeader.add(lblEmpresa);
        pnlHeader.add(new JLabel("Estelí, Nicaragua", JLabel.CENTER));
        pnlHeader.add(new JLabel("Teléfono No. 2713-9999", JLabel.CENTER));
        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.setBackground(Color.WHITE);

        String[] col = {"CÓDIGO", "NOMBRE DE LA CUENTA", "PARCIAL", "DEBE", "HABER"};
        modelo = new DefaultTableModel(col, 0);
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        pnlCentro.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pnlInput = new JPanel(new FlowLayout());
        pnlInput.setBackground(Color.WHITE);
        
        tCod = new JTextField(5);
        tCuenta = new JTextField(12);
        tParcial = new JTextField("0.0", 6);
        tDebe = new JTextField("0.0", 6);
        tHaber = new JTextField("0.0", 6);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(41, 128, 185)); 
        btnAgregar.setForeground(Color.WHITE);

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
        pnlSumas.setBackground(Color.WHITE);
        lblDebe = new JLabel("Sumas Iguales Debe: C$ 0.0");
        lblHaber = new JLabel("Haber: C$ 0.0");
        pnlSumas.add(lblDebe); pnlSumas.add(lblHaber);
        pnlPie.add(pnlSumas, BorderLayout.NORTH);

        JPanel pnlFirmas = new JPanel(new GridLayout(1, 3, 20, 0));
        pnlFirmas.setBackground(Color.WHITE);
        pnlFirmas.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        pnlFirmas.add(crearCajaFirma("ELABORADO POR:"));
        pnlFirmas.add(crearCajaFirma("REVISADO POR:"));
        pnlFirmas.add(crearCajaFirma("AUTORIZADO POR:"));
        // pnlFirmas.add(cajaJPanel("Juan Andres Alvarado"));

        pnlPie.add(pnlFirmas, BorderLayout.CENTER);
        add(pnlPie, BorderLayout.SOUTH);

        
    btnAgregar.addActionListener(e -> {
    
    if (tCod.getText().trim().isEmpty() || tCuenta.getText().trim().isEmpty() ||
        tParcial.getText().trim().isEmpty() || tDebe.getText().trim().isEmpty() ||
        tHaber.getText().trim().isEmpty()) {
        
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
    }

    try {
        
        Transaccion tr = new Transaccion(tCod.getText(), tCuenta.getText(),
            Double.parseDouble(tParcial.getText()),
            Double.parseDouble(tDebe.getText()),
            Double.parseDouble(tHaber.getText()));
        
        modelo.addRow(new Object[]{tr.getCod(), tr.getCuenta(), tr.getParcial(), tr.getDebe(), tr.getHaber()});
        
        double d = 0, h = 0;
        for(int i=0; i<modelo.getRowCount(); i++){
            d += (double)modelo.getValueAt(i, 3);
            h += (double)modelo.getValueAt(i, 4);
        }
        lblDebe.setText("Sumas Iguales Debe: C$ " + d);
        lblHaber.setText("Haber: C$ " + h);
    } catch(Exception ex) {
        JOptionPane.showMessageDialog(this, "Error en los datos.");
    }
});
    }
    private JPanel crearCajaFirma(String texto) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.add(new JLabel(texto, JLabel.CENTER), BorderLayout.NORTH);
        p.add(new JLabel("_Juan Andres Alvarado_", JLabel.CENTER), BorderLayout.CENTER);
        return p;
    }
    // public JPanel cajaJPanel(String texto){
    //     JPanel d = new JPanel(new BorderLayout());
    //     d.setBackground(Color.white);
    //     d.add(new JLabel(texto, JLabel.CENTER), BorderLayout.NORTH);
    //     d.add(new JLabel("Juan Andres Alvarado", JLabel.CENTER),BorderLayout.CENTER);
    //     return d;

    // }
}
