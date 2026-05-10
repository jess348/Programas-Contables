package FormularioDGI;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class SolicitudFormularioFrm extends JInternalFrame {

    private Contribuyente contribuyente;
    private JTabbedPane tabbedPane;
    private JTextField txtNombre, txtApellido, txtCedula, txtNumInss, txtNomComercial, txtIdentReguladora;
    private JTextField txtCalle, txtNumero, txtCiudad, txtDepartamento, txtNumCasa, txtDistrito, txtNomSector,
            txtTelefono, txtCorreo, txtCodigoPostal, txtCelular, txtMunicipio;
    private JTextField txtIngresos, txtGastos, txtFuente;
    private JTextField txtNombreFamiliar, txtRelacionFamiliar, txtCedulaFamiliar;
    private JTextField txtNombreRep, txtCedulaRep, txtPoderRep, txtCargoRep, txtPeriodoRep, txtFechaInicioRep,
            txtFechaFinRep, txtDireccionRep, txtDepartamentoRep, txtMunicipioRep, txtDistritoRep, txtTelefonoRep;
    private JTextField txtActEcoPrin, txtFechaIni, txtCIIU, txtProdLider;
    private JTextField txtActEcoSec, txtFechaIniSec, txtCIIUSec, txtProdLiderSec;
    private JTextField txtBarrioDom, txtBarrioNeg, txtNomLicencia, txtCategoria, txtDirNegocio, txtActComercial,
            txtPyme;
    private JFormattedTextField txtFechaNacimiento;
    private JComboBox<String> cmboxRegimen;
    private JComboBox<String> cmbSexo;
    private JComboBox<String> cmbDepartamento;
    private int confirm;

    public SolicitudFormularioFrm() {
        super("Formulario de Solicitud - DGI", true, true, true, true);
        setSize(980, 750);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        contribuyente = new Contribuyente();

        JPanel pnlHeader = new JPanel(new GridLayout(3, 1));
        pnlHeader.setBackground(Color.WHITE);

        JLabel lblEmpresa = new JLabel("Dirección General de Ingresos (DGI)", JLabel.CENTER);
        lblEmpresa.setFont(new Font("Serif", Font.BOLD, 22));
        JLabel lblUbicacion = new JLabel("Managua, Nicaragua | Formulario de Registro", JLabel.CENTER);
        JLabel lblTituloDoc = new JLabel("SOLICITUD DE REGISTRO UNIFICADO", JLabel.CENTER);
        lblTituloDoc.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloDoc.setOpaque(true);
        lblTituloDoc.setBackground(new Color(41, 128, 185));
        lblTituloDoc.setForeground(Color.WHITE);

        pnlHeader.add(lblEmpresa);
        pnlHeader.add(lblUbicacion);
        pnlHeader.add(lblTituloDoc);
        add(pnlHeader, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("DATOS BASICOS", crearPanelDatosBasicos());
        tabbedPane.addTab("FAMILIARES", crearPanelFamiliares());
        tabbedPane.addTab("DOMICILIO", crearPanelDomicilio());
        tabbedPane.addTab("REPRESENTANTE LEGAL", crearPanelRepresentanteLegal());
        tabbedPane.addTab("ACTIVIDAD ECONÓMICA", crearPanelActividadEconomica());
        tabbedPane.addTab("INFORMACIÓN FINANCIERA", crearPanelInfoFinanciera());
        tabbedPane.addTab("DATOS ALCALDÍA", crearPanelAlcaldia());

        add(tabbedPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnGuardar = new JButton("Guardar Registro");
        btnGuardar.setBackground(new Color(41, 128, 185));
        btnGuardar.setForeground(Color.WHITE);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);

        JButton btnLimpiar = new JButton("Limpiar Formulario");
        btnLimpiar.setBackground(new Color(241, 196, 15));
        btnLimpiar.setForeground(Color.WHITE);

        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnLimpiar);
        add(buttonPanel, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarDatos());
        btnCancelar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private JPanel crearPanelFormulario(int filas) {
        JPanel p = new JPanel(new GridLayout(filas, 2, 15, 15));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return p;
    }

    private JPanel crearPanelDatosBasicos() {
        try {
            MaskFormatter mascaraFecha = new MaskFormatter("##/##/####");
            mascaraFecha.setPlaceholderCharacter('_');
            txtFechaNacimiento = new JFormattedTextField(mascaraFecha);
        } catch (Exception e) {
            txtFechaNacimiento = new JFormattedTextField();
        }

        JPanel panel = crearPanelFormulario(12);
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtCedula = new JTextField();
        txtNumInss = new JTextField();
        cmbSexo = new JComboBox<>(new String[] { "Masculino", "Femenino", "Otro" });
        txtNomComercial = new JTextField();
        txtIdentReguladora = new JTextField();

        panel.add(new JLabel("Nombres:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellidos:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Cédula de Identidad:"));
        panel.add(txtCedula);
        panel.add(new JLabel("Número de INSS:"));
        panel.add(txtNumInss);
        panel.add(new JLabel("Fecha de Nacimiento:"));
        panel.add(txtFechaNacimiento);
        panel.add(new JLabel("Sexo:"));
        panel.add(cmbSexo);
        panel.add(new JLabel("Nombre Comercial:"));
        panel.add(txtNomComercial);
        panel.add(new JLabel("Identificación de la Reguladora:"));
        panel.add(txtIdentReguladora);

        return panel;
    }

    // [crearPanelDomicilio, crearPanelInfoFinanciera, crearPanelFamiliares,
    // crearPanelActividadEconomica, crearPanelRepresentanteLegal,
    // crearPanelAlcaldia - Mantenidos según tu código]

    private JPanel crearPanelDomicilio() {
        JPanel panel = crearPanelFormulario(12);

        String[] departamentos = {
                "Boaco", "Carazo", "Chinandega", "Chontales", "Estelí",
                "Granada", "Jinotega", "León", "Madriz", "Managua",
                "Masaya", "Matagalpa", "Nueva Segovia", "Río San Juan",
                "Rivas", "RACCN", "RACCS"
        };
        cmbDepartamento = new JComboBox<>(departamentos);
        cmbDepartamento.setBackground(Color.WHITE);

        txtCalle = new JTextField();
        txtNumero = new JTextField();
        txtCiudad = new JTextField();
        txtMunicipio = new JTextField();
        // txtDepartamento = new JTextField();
        txtNumCasa = new JTextField();
        txtDistrito = new JTextField();
        txtNomSector = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        txtCodigoPostal = new JTextField();
        txtCelular = new JTextField();
        panel.add(new JLabel("Calle/Avenida:"));
        panel.add(txtCalle);
        panel.add(new JLabel("Número de Casa:"));
        panel.add(txtNumCasa);
        panel.add(new JLabel("Ciudad:"));
        panel.add(txtCiudad);
        panel.add(new JLabel("Municipio:"));
        panel.add(txtMunicipio);
        panel.add(new JLabel("Departamento:"));
        panel.add(cmbDepartamento);
        panel.add(new JLabel("Distrito:"));
        panel.add(txtDistrito);
        panel.add(new JLabel("Nombre del Sector:"));
        panel.add(txtNomSector);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Correo Electrónico:"));
        panel.add(txtCorreo);
        panel.add(new JLabel("Código Postal:"));
        panel.add(txtCodigoPostal);
        panel.add(new JLabel("Celular:"));
        panel.add(txtCelular);

        return panel;
    }

    private JPanel crearPanelInfoFinanciera() {
        JPanel panel = crearPanelFormulario(12);
        txtIngresos = new JTextField("0.00");
        txtGastos = new JTextField("0.00");
        txtFuente = new JTextField();
        cmboxRegimen = new JComboBox<>(new String[] { "Régimen General", "Cuota Fija", "Régimen Especial" });
        panel.add(new JLabel("Ingresos Anuales (C$):"));
        panel.add(txtIngresos);
        panel.add(new JLabel("Gastos Anuales (C$):"));
        panel.add(txtGastos);
        panel.add(new JLabel("Fuente Principal:"));
        panel.add(txtFuente);
        panel.add(new JLabel("Régimen Fiscal:"));
        panel.add(cmboxRegimen);
        return panel;
    }

    private JPanel crearPanelFamiliares() {
        JPanel panel = crearPanelFormulario(12);
        txtNombreFamiliar = new JTextField();
        txtRelacionFamiliar = new JTextField();
        txtCedulaFamiliar = new JTextField();
        panel.add(new JLabel("Nombre Completo:"));
        panel.add(txtNombreFamiliar);
        panel.add(new JLabel("Parentesco:"));
        panel.add(txtRelacionFamiliar);
        panel.add(new JLabel("Cédula:"));
        panel.add(txtCedulaFamiliar);
        return panel;
    }

    private JPanel crearPanelActividadEconomica() {
        JPanel panel = crearPanelFormulario(12);
        txtActEcoPrin = new JTextField();
        txtFechaIni = new JTextField();
        txtCIIU = new JTextField();
        txtProdLider = new JTextField();
        txtActEcoSec = new JTextField();
        txtFechaIniSec = new JTextField();
        txtCIIUSec = new JTextField();
        txtProdLiderSec = new JTextField();
        panel.add(new JLabel("Actividad Económica Principal:"));
        panel.add(txtActEcoPrin);
        panel.add(new JLabel("Fecha de Inicio:"));
        panel.add(txtFechaIni);
        panel.add(new JLabel("Código CIIU:"));
        panel.add(txtCIIU);
        panel.add(new JLabel("Producto Líder:"));
        panel.add(txtProdLider);
        panel.add(new JLabel("Actividad Económica Secundaria:"));
        panel.add(txtActEcoSec);
        panel.add(new JLabel("Fecha de Inicio Secundaria:"));
        panel.add(txtFechaIniSec);
        panel.add(new JLabel("Código CIIU Secundaria:"));
        panel.add(txtCIIUSec);
        panel.add(new JLabel("Producto Líder Secundaria:"));
        panel.add(txtProdLiderSec);
        return panel;
    }

    private JPanel crearPanelRepresentanteLegal() {
        JPanel panel = crearPanelFormulario(12);
                String[] departamentos = {
                "Boaco", "Carazo", "Chinandega", "Chontales", "Estelí",
                "Granada", "Jinotega", "León", "Madriz", "Managua",
                "Masaya", "Matagalpa", "Nueva Segovia", "Río San Juan",
                "Rivas", "RACCN", "RACCS"
        };
        cmbDepartamento = new JComboBox<>(departamentos);
        cmbDepartamento.setBackground(Color.WHITE);
        txtNombreRep = new JTextField();
        txtCedulaRep = new JTextField();
        txtPoderRep = new JTextField();
        txtCargoRep = new JTextField();
        txtPeriodoRep = new JTextField();
        txtFechaInicioRep = new JTextField();
        txtFechaFinRep = new JTextField();
        txtDireccionRep = new JTextField();
        txtMunicipioRep = new JTextField();
        txtDistritoRep = new JTextField();
        txtTelefonoRep = new JTextField();
        panel.add(new JLabel("Nombre Representante:"));
        panel.add(txtNombreRep);
        panel.add(new JLabel("Cédula:"));
        panel.add(txtCedulaRep);
        panel.add(new JLabel("Número de Poder:"));
        panel.add(txtPoderRep);
        panel.add(new JLabel("Cargo:"));
        panel.add(txtCargoRep);
        panel.add(new JLabel("Periodo de Duración:"));
        panel.add(txtPeriodoRep);
        panel.add(new JLabel("Fecha Inicio:"));
        panel.add(txtFechaInicioRep);
        panel.add(new JLabel("Fecha Fin:"));
        panel.add(txtFechaFinRep);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccionRep);
        panel.add(new JLabel("Departamento:"));
        panel.add(cmbDepartamento);
        panel.add(new JLabel("Municipio:"));
        panel.add(txtMunicipioRep);
        panel.add(new JLabel("Distrito:"));
        panel.add(txtDistritoRep);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefonoRep);
        return panel;
    }

    private JPanel crearPanelAlcaldia() {
        JPanel panel = crearPanelFormulario(12);
        txtBarrioDom = new JTextField();
        txtBarrioNeg = new JTextField();
        txtNomLicencia = new JTextField();
        txtCategoria = new JTextField();
        txtDirNegocio = new JTextField();
        txtActComercial = new JTextField();
        txtPyme = new JTextField();
        panel.add(new JLabel("Barrio del Domicilio:"));
        panel.add(txtBarrioDom);
        panel.add(new JLabel("Barrio del Negocio:"));
        panel.add(txtBarrioNeg);
        panel.add(new JLabel("Nombre de la Licencia Comercial:"));
        panel.add(txtNomLicencia);
        panel.add(new JLabel("Categoría:"));
        panel.add(txtCategoria);
        panel.add(new JLabel("Dirección del Negocio:"));
        panel.add(txtDirNegocio);
        panel.add(new JLabel("Actividad Comercial:"));
        panel.add(txtActComercial);
        panel.add(new JLabel("¿Es PYME? (Sí/No):"));
        panel.add(txtPyme);
        return panel;
    }

    private void guardarDatos() {
        String ruc = txtCedula.getText().trim();
        if (!ValidarFormatoRUC(ruc)) {
            JOptionPane.showMessageDialog(this, "Error: El RUC debe tener el formato (###-######-####A).");
            return;
        }

        if (!validarFormatos()) {
            return;
        }

        try {
            double ingresos = Double.parseDouble(txtIngresos.getText());
            double gastos = Double.parseDouble(txtGastos.getText());
            double utilidad = ingresos - gastos;
            double impuestoAPagar = 0;
            String regimen = cmboxRegimen.getSelectedItem().toString();

            if (regimen.equals("Régimen General")) {
                impuestoAPagar = (utilidad > 0) ? utilidad * 0.30 : 0;
            } else {
                impuestoAPagar = ingresos * 0.03;
            }

            String folio = generarFolio();
            String mensaje = "DETALLE DE DECLARACIÓN\n" +
                    "---------------------------------\n" +
                    "Folio No: " + folio + "\n" +
                    "Utilidad Neta: C$ " + String.format("%.2f", utilidad) + "\n" +
                    "Impuesto Calculado: C$ " + String.format("%.2f", impuestoAPagar) + "\n" +
                    "---------------------------------\n" +
                    "¿Desea confirmar el envío a la DGI?";
            confirm = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);

            // PRIMERO: Pedir confirmación y asignar a la variable global

            // SEGUNDO: Si confirma, procesar todos los datos una sola vez
            if (confirm == JOptionPane.YES_OPTION) {
                contribuyente.setNombre(txtNombre.getText());
                contribuyente.setApellido(txtApellido.getText());
                contribuyente.setCedula(ruc);
                contribuyente.setNumInss(txtNumInss.getText());
                contribuyente.setSexo((String) cmbSexo.getSelectedItem());
                contribuyente.setNomComercial(txtNomComercial.getText());
                contribuyente.setIdentReguladora(txtIdentReguladora.getText());

                Domicilio dom = new Domicilio();
                dom.setCalle(txtCalle.getText());
                dom.setNumero(txtNumero.getText());
                dom.setCiudad(txtCiudad.getText());
                dom.setMunicipio(txtMunicipio.getText());
                // Capturar el departamento del JComboBox
                dom.setDepartamento((String) cmbDepartamento.getSelectedItem());
                contribuyente.setDomicilio(dom);

                InfoFinanciera fin = new InfoFinanciera();
                fin.setIngresosAnuales(ingresos);
                fin.setGastosAnuales(gastos);
                fin.setFuenteIngresos(txtFuente.getText());
                contribuyente.setInfoFinanciera(fin);

                Familiar fam = new Familiar();
                fam.setNombre(txtNombreFamiliar.getText());
                fam.setRelacion(txtRelacionFamiliar.getText());
                fam.setCedula(txtCedulaFamiliar.getText());
                contribuyente.getFamiliares().clear();
                contribuyente.getFamiliares().add(fam);

                RepresentanteLegal rep = new RepresentanteLegal();
                rep.setNombre(txtNombreRep.getText());
                rep.setCedula(txtCedulaRep.getText());
                rep.setPoder(txtPoderRep.getText());
                contribuyente.setRepresentanteLegal(rep);

                if (impuestoAPagar > 0) {
                    Window parentWindow = SwingUtilities.getWindowAncestor(this);
                    BoletaPagoDialog dialogPago = new BoletaPagoDialog(parentWindow, folio, impuestoAPagar);
                    
                    dialogPago.setVisible(true);

                    if (dialogPago.isPagoExitoso()) {
                        JOptionPane.showMessageDialog(this, "Declaracion y Pago Aplicados" + folio+"!");
                        limpiarCampos();

                    }else{
                        JOptionPane.showMessageDialog(this,"La Declaracion Se Guardó, Pero el Pago Quedó PENDIENTE.");
                    
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "La Declaración Enviada Exitosamente Con Folio" + folio +"!\n"+"Su Declaracion es en CERO, No Requiere Pago en Bancos.");
                    limpiarCampos();
                }

            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Los campos financieros deben ser numéricos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage());
        }
    }

    private boolean validarFormatos() {
        // 1. Validación de Correo
        String email = txtCorreo.getText().trim();
        if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "El formato del correo electrónico es inválido.");
            txtCorreo.requestFocus();
            return false;
        }

        // 2. Validación de Celular
        String celular = txtCelular.getText().trim();
        if (!celular.isEmpty() && !celular.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "El número celular debe contener exactamente 8 dígitos.");
            txtCelular.requestFocus();
            return false;
        }
        return true;
    }

    private String generarFolio() {
        return "DGI-" + (System.currentTimeMillis() % 1000000);
    }

    private boolean ValidarFormatoRUC(String ruc) {
        String regex = "^[0-9]{3}-[0-9]{6}-[0-9]{4}[A-Z]$";
        return ruc.matches(regex);
    }

    private void limpiarCampos() {
        JTextField[] campos = {
                txtNombre, txtApellido, txtCedula, txtCalle, txtNumero, txtCiudad, txtDepartamento,
                txtFuente, txtNombreFamiliar, txtRelacionFamiliar, txtCedulaFamiliar,
                txtNombreRep, txtCedulaRep, txtPoderRep, txtCargoRep, txtPeriodoRep,
                txtFechaInicioRep, txtFechaFinRep, txtDireccionRep, txtDepartamentoRep,
                txtMunicipioRep, txtDistritoRep, txtTelefonoRep, txtActEcoPrin, txtFechaIni,
                txtCIIU, txtProdLider, txtActEcoSec, txtFechaIniSec, txtCIIUSec, txtProdLiderSec,
                txtBarrioDom, txtBarrioNeg, txtNomLicencia, txtCategoria, txtDirNegocio, txtActComercial, txtPyme,
                txtActComercial, txtNomComercial, txtNumCasa, txtMunicipio, txtMunicipioRep, txtDistrito, txtNomSector,
                txtTelefono, txtCorreo, txtCodigoPostal, txtCelular,txtNumInss,txtIdentReguladora,txtNumero

        };
        for (JTextField f : campos) {
            if (f != null)
                f.setText("");
        }
        txtIngresos.setText("0.00");
        txtGastos.setText("0.00");
        cmbDepartamento.setSelectedIndex(0);

    }
}