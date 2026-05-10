package FormularioDGI;

import javax.swing.*;
import java.awt.*;

public class BoletaPagoDialog extends JDialog {

    private JComboBox<String> cmbBancos;
    private JTextField txtCuenta;
    private JButton btnPagar, btnCancelar;
    private boolean pagoExitoso = false;

    // El constructor recibe el folio y el monto calculado desde el formulario principal
    public BoletaPagoDialog(Window parent, String folio, double monto) {
        super(parent, "Pasarela de Pago DGI", Dialog.ModalityType.APPLICATION_MODAL);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        JPanel pnlHeader = new JPanel(new GridLayout(2,1));
        pnlHeader.setBackground(new Color(41,128,185));

        JLabel lblTitulo = new JLabel("Pago de Impuestos DGI", JLabel.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));


        JLabel lblDatos = new JLabel("Folio: " + folio + " | Monto: C$ " + String.format("%.2f", monto), JLabel.CENTER);
        lblDatos.setForeground(Color.WHITE);

        pnlHeader.add(lblTitulo);
        pnlHeader.add(lblDatos);
        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlCentro = new JPanel(new GridLayout(2,2,15,15));
        pnlCentro.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        pnlCentro.setBackground(Color.WHITE);

        String[] bancos = {"Lafise Bancentro","Banpro","BAC Credomatic","Ficohsa"};
        cmbBancos = new JComboBox<>(bancos);
        cmbBancos.setBackground(Color.WHITE);

        txtCuenta = new JTextField();

        pnlCentro.add(new JLabel("Seleccione Su Banco De Preferencia."));
        pnlCentro.add(cmbBancos);
        pnlCentro.add(new JLabel("Numero De Cuenta."));
        pnlCentro.add(txtCuenta);
        add(pnlCentro, BorderLayout.CENTER);

        //botones

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBtn.setBackground(Color.WHITE);

        btnPagar = new JButton("Confirmar Pago✅");
        btnPagar.setBackground(new Color(39,174,96));
        btnPagar.setForeground(Color.WHITE);

        btnCancelar = new JButton("Cancelar Pago❌");
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);

        pnlBtn.add(btnPagar);
        pnlBtn.add(btnCancelar);
        add(pnlBtn, BorderLayout.SOUTH);

        btnCancelar.addActionListener(e -> dispose());

        btnPagar.addActionListener(e ->{
            String cuenta = txtCuenta.getText().trim();
            if (cuenta.isEmpty()|| !cuenta.matches("\\d+")) {
                JOptionPane.showMessageDialog(this,"Ingrese Un Numero De Cuenta Válido");
                txtCuenta.requestFocus();
                return;
                
            }
            String bancoseleccionado = (String) cmbBancos.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Conectando Con "+ bancoseleccionado + "...\n"+"¡Su Transaccion Fue Aprovada!\n"+"Se Ha Debitado C$"+ String.format("%.2f", monto)+ "De Su Cuenta.","Pago Exitoso!", JOptionPane.INFORMATION_MESSAGE);

            pagoExitoso = true;
            dispose();
        });
    }
        public boolean isPagoExitoso() {
        return pagoExitoso;
    }

}
