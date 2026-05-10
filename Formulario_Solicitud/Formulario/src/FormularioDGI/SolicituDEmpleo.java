package FormularioDGI;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SolicituDEmpleo extends JInternalFrame {
    private List<JTextField> camposAValidar = new ArrayList<>();
    private JTextField cedulaField;
    private JTextField nssField;
    private JTextField pasaporteField;
    private JTextField licenciaField;



    private void configurarValidacionNumerica(JTextField campo, String nombreCampo) {
        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir dígitos, punto decimal y teclas de control (backspace, delete, flechas, etc.)
                if (Character.isDigit(c) || c == '.' || Character.isISOControl(c)) {
                    return;
                }
                e.consume(); // Bloquea la letra

                JOptionPane.showMessageDialog(campo,
                    "Error: El campo '" + nombreCampo + "' solo acepta números y punto.",
                    "Dato No Válido",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        });

    }

    public SolicituDEmpleo() {
        // 1. Configuración básica del JInternalFrame (Ventana Principal)
    super("- Solicitud de Empleo",true, true, true, true);
        setSize(1200, 550); // Un tamaño adecuado para acomodar las pestañas
    setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);


        JLabel lblubicacion = new JLabel("Managua, Nicaragua", JLabel.CENTER);
        lblubicacion.setOpaque(true);
        //lblubicacion.setBackground(Color.white);
        lblubicacion.setForeground(Color.black);
        lblubicacion.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        JLabel lblFecha = new JLabel(LocalDate.now().format(formatter), JLabel.CENTER);
        lblFecha.setOpaque(true);
        //lblFecha.setBackground(Color.white);
        lblFecha.setForeground(Color.black);
        lblFecha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel lbltitulo = new JLabel("FORMULARIO DE SOLICITUD DE EMPLEO", SwingConstants.CENTER);
        lbltitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbltitulo.setOpaque(true);
        lbltitulo.setBackground(new Color(41, 150, 200));
        lbltitulo.setForeground(Color.WHITE);
        lbltitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        

        // 2. Crear el JTabbedPane (Contenedor de pestañas)

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        JPanel contenedor = new JPanel(new BorderLayout());
        JPanel panelTitulos = new JPanel(new GridLayout(3, 1));
        panelTitulos.add(lbltitulo);
        panelTitulos.add(lblubicacion);
        panelTitulos.add(lblFecha);
        contenedor.add(panelTitulos, BorderLayout.NORTH);
        contenedor.add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.addTab("1. Informaci.ón Inicial", crearPanelInformacionInicial());
        tabbedPane.addTab("2. Datos Personales y Documentos", crearPanelDatosYDocumentos());
        tabbedPane.addTab("3. Salud y Familia", crearPanelSaludYFamilia());
        tabbedPane.addTab("4. Escolaridad", crearPanelEscolaridad());
        tabbedPane.addTab("5. Conocimientos", crearPanelConocimientos());
        tabbedPane.addTab("6. Trayectoria Laboral", crearPanelEmpleos());
        tabbedPane.addTab("7. Referencias Generales", crearPanelReferenciasGenerales());
        tabbedPane.addTab("8. Aspectos Económicos / Finalizar", crearPanelEconomicosFinal());
        add(contenedor);

        // ACTIVAR FUNCIONALIDADES PRO
        habilitarEnterGlobal(this.getContentPane());
    }
    // --- Métodos para crear cada panel de pestaña ---

    private JPanel crearPanelInformacionInicial() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    
        // Sección Puesto y Sueldo
     // Cambiamos a GridLayout: 3 filas, 2 columnas, y 10px de espacio horizontal y vertical
        JPanel subPanelDatos = new JPanel(new GridLayout(3, 2, 10, 10));
        subPanelDatos.setBorder(new TitledBorder("Datos del Puesto"));

// --- Fila 1 ---
        subPanelDatos.add(new JLabel("Puesto Solicitado:"));
        JTextField puestoSolicitado = new JTextField();
puestoSolicitado.setColumns(5);
camposAValidar.add(puestoSolicitado);
subPanelDatos.add(puestoSolicitado);

// --- Fila 2 ---
subPanelDatos.add(new JLabel("Sueldo Mensual Deseado:"));
JTextField sueldoDeseado = new JTextField();
sueldoDeseado.setColumns(5);
configurarValidacionNumerica(sueldoDeseado, "Sueldo Mensual Deseado");
camposAValidar.add(sueldoDeseado);
subPanelDatos.add(sueldoDeseado);

// --- Fila 3 ---
subPanelDatos.add(new JLabel("Sueldo Mensual Autorizado:"));
JTextField sueldoAutorizado = new JTextField();
sueldoAutorizado.setColumns(5);
configurarValidacionNumerica(sueldoAutorizado, "Sueldo Mensual Autorizado");
camposAValidar.add(sueldoAutorizado);
subPanelDatos.add(sueldoAutorizado);

panel.add(subPanelDatos);
    
// JPanel pnlFoto = new JPanel();

       // pnlFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       // pnlFoto.setPreferredSize(new Dimension(100, 120));
       // pnlFoto.add(new JLabel("FOTOGRAFIA"));
        //gbc.gridy = 1;
        //panel.add(pnlFoto, gbc);
        return panel;

    }



    private JPanel crearPanelDatosYDocumentos() {
        JPanel panel = new JPanel(new GridLayout(2, 1)); // Dos filas: Datos Personales, Documentos

        // --- Sección DATOS PERSONALES ---
        JPanel pnlPersonal = new JPanel(new GridLayout(7, 2, 5, 2));
        pnlPersonal.setBorder(new TitledBorder("DATOS PERSONALES"));
        pnlPersonal.add(new JLabel("Apellido Paterno:"));
        JTextField apellidoPaterno = new JTextField();
        camposAValidar.add(apellidoPaterno);
        pnlPersonal.add(apellidoPaterno);
        pnlPersonal.add(new JLabel("Apellido Materno:"));
        JTextField apellidoMaterno = new JTextField();
        camposAValidar.add(apellidoMaterno);
        pnlPersonal.add(apellidoMaterno);
        pnlPersonal.add(new JLabel("Nombre (s):"));
        JTextField nombre = new JTextField();
        camposAValidar.add(nombre);
        pnlPersonal.add(nombre);

        // Género con RadioButtons

        JPanel pnlSexo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSexo.add(new JLabel("Sexo: "));
        JRadioButton rbMasculino = new JRadioButton("M");
        JRadioButton rbFemenino = new JRadioButton("F");
        ButtonGroup bgSexo = new ButtonGroup();
        bgSexo.add(rbMasculino); bgSexo.add(rbFemenino);
        pnlSexo.add(rbMasculino); pnlSexo.add(rbFemenino);
        pnlPersonal.add(pnlSexo); pnlPersonal.add(new JLabel()); // Espacio vacío
        pnlPersonal.add(new JLabel("Domicilio:"));
        JTextField domicilio = new JTextField();
        camposAValidar.add(domicilio);
        pnlPersonal.add(domicilio);
        pnlPersonal.add(new JLabel("Colonia:"));
        JTextField colonia = new JTextField();
        camposAValidar.add(colonia);
        pnlPersonal.add(colonia);
        pnlPersonal.add(new JLabel("Correo electrónico:"));
        JTextField correo = new JTextField();
        camposAValidar.add(correo);
        pnlPersonal.add(correo);

        panel.add(pnlPersonal);



        // --- Sección DOCUMENTACIÓN ---
        JPanel pnlDocumentos = new JPanel(new GridLayout(4, 2, 5, 2));
        pnlDocumentos.setBorder(new TitledBorder("DOCUMENTACIÓN"));
        pnlDocumentos.add(new JLabel("Cédula:"));
        cedulaField = new JTextField();
        configurarValidacionNumerica(cedulaField, "Cédula");
        camposAValidar.add(cedulaField);
        pnlDocumentos.add(cedulaField);
        pnlDocumentos.add(new JLabel("Núm. Seguridad Social:"));
        nssField = new JTextField();
        configurarValidacionNumerica(nssField, "Núm. Seguridad Social");
        camposAValidar.add(nssField);
        pnlDocumentos.add(nssField);
        pnlDocumentos.add(new JLabel("Pasaporte Núm.:"));
        pasaporteField = new JTextField();
        camposAValidar.add(pasaporteField);
        pnlDocumentos.add(pasaporteField);
        pnlDocumentos.add(new JLabel("Licencia de manejo:"));
        licenciaField = new JTextField();
        camposAValidar.add(licenciaField);
        pnlDocumentos.add(licenciaField);

        panel.add(pnlDocumentos);

        return panel;

    }

    private JPanel crearPanelSaludYFamilia() {
        JPanel panel = new JPanel(new BorderLayout());
        // --- Sección SALUD Y HÁBITOS (Arriba) ---


        // --- SECCIÓN DATOS FAMILIARES CON CHECKBOXE ---
    String[] columnasFamiliar = {"Parentesco", "Nombre", "Vive", "Finado", "Domicilio", "Ocupación"};
    Object[][] datosFamiliar = {
        {"Padre", "", false, false, "", ""},
        {"Madre", "", false, false, "", ""},
        {"Cónyuge", "", false, false, "", ""}
    };

    // Sobrescribimos el modelo de la tabla para que reconozca los booleanos
    DefaultTableModel modelo = new DefaultTableModel(datosFamiliar, columnasFamiliar) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // Indicamos que las columnas 2 y 3 (Vive y Finado) son Booleanas
            if (columnIndex == 2 || columnIndex == 3) {
                return Boolean.class;
            }
            return String.class;
        }
    };

    JTable tablaFamiliar = new JTable(modelo);
    JScrollPane scrollFamiliar = new JScrollPane(tablaFamiliar);
    scrollFamiliar.setBorder(new TitledBorder("DATOS FAMILIARES"));
    panel.add(scrollFamiliar, BorderLayout.CENTER);

    return panel;

}
    private JPanel crearPanelEscolaridad() {
        JPanel panel = new JPanel(new BorderLayout());

        // --- Sección ESCOLARIDAD (Tabla Principal) ---
        String[] columnasEscolar = {"Nivel", "Nombre de la Escuela", "Domicilio", "Fechas (De/A)", "Años", "Título/Certificado"};
        Object[][] datosEscolar = {
            {"Primaria", "", "", "", "", ""},
            {"Secundaria", "", "", "", "", ""},
            {"Preparatoria", "", "", "", "", ""},
            {"Profesional", "", "", "", "", ""},
            {"Comercial u Otras", "", "", "", "", ""}

        };

        JTable tablaEscolar = new JTable(datosEscolar, columnasEscolar);
        JScrollPane scrollEscolar = new JScrollPane(tablaEscolar);
        scrollEscolar.setBorder(new TitledBorder("ESCOLARIDAD"));
        panel.add(scrollEscolar, BorderLayout.CENTER);

        // --- Sección Estudios Actuales (Abajo) ---
        JPanel pnlActual = new JPanel(new GridLayout(2, 2, 5, 2));
        pnlActual.setBorder(new TitledBorder("Estudios que está efectuando en la actualidad"));
        pnlActual.add(new JLabel("Escuela:")); pnlActual.add(new JTextField());
        pnlActual.add(new JLabel("Curso o Carrera:")); pnlActual.add(new JTextField());
        panel.add(pnlActual, BorderLayout.SOUTH);

        return panel;
    }
    // --- SECCIÓN: CONOCIMIENTOS GENERALES ---
    private JPanel crearPanelConocimientos() {
        JPanel p = new JPanel(new GridLayout(5, 2, 10, 10));
        p.setBorder(new TitledBorder("CONOCIMIENTOS GENERALES"));
        agregarCampo(p, "Idiomas (Nivel %):");
        agregarCampo(p, "Funciones de oficina:");
        agregarCampo(p, "Máquinas oficina/taller:");
        agregarCampo(p, "Software que domina:");
        agregarCampo(p, "Otras funciones:");
        return p;

    }
    // --- SECCIÓN: EMPLEOS (TABLA) ---
    private JPanel crearPanelEmpleos() {
        JPanel p = new JPanel(new BorderLayout());
        String[] col = {"Compañía", "Tiempo", "Puesto", "Sueldo", "Motivo Salida", "Jefe"};
        Object[][] data = {{"Actual/Último", "", "", "", "", ""}, {"Anterior", "", "", "", "", ""}, {"Anterior", "", "", "", "", ""}};
        JTable tabla = new JTable(new DefaultTableModel(data, col));
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        p.add(new JLabel("EMPLEO ACTUAL Y ANTERIORES (Describa detalladamente)"), BorderLayout.NORTH);
        return p;

    }
    // --- SECCIÓN: REFERENCIAS Y DATOS GENERALES ---
    private JPanel crearPanelReferenciasGenerales() {
        JPanel p = new JPanel(new GridLayout(2, 1));
        // Tabla Referencias
        JPanel pRef = new JPanel(new BorderLayout());
        pRef.setBorder(new TitledBorder("REFERENCIAS PERSONALES (No parientes)"));
        String[] colRef = {"Nombre", "Teléfono", "Domicilio", "Ocupación", "Tiempo"};
        JTable tablaRef = new JTable(new DefaultTableModel(new Object[3][5], colRef));
        pRef.add(new JScrollPane(tablaRef));

        // Datos Generales
        JPanel pGral = new JPanel(new GridLayout(4, 2));
        pGral.setBorder(new TitledBorder("DATOS GENERALES"));
        agregarCampo(pGral, "¿Cómo supo del empleo?");
        agregarCampo(pGral, "¿Tiene parientes aquí?");
        agregarCampo(pGral, "Disponibilidad de viajar (S/N):");
        agregarCampo(pGral, "Fecha de ingreso posible:");

        p.add(pRef);
        p.add(pGral);
        return p;

    }
    // --- SECCIÓN: ECONÓMICOS Y FIRMA ---
    private JPanel crearPanelEconomicosFinal() {
        JPanel p = new JPanel(new BorderLayout());
        JPanel pEcon = new JPanel(new GridLayout(5, 2));
        pEcon.setBorder(new TitledBorder("DATOS ECONÓMICOS"));
        agregarCampo(pEcon, "¿Otros ingresos? Importe:");
        agregarCampo(pEcon, "¿Vive en casa propia?");
        agregarCampo(pEcon, "¿Paga renta?");
        agregarCampo(pEcon, "Gastos mensuales aprox:");
        agregarCampo(pEcon, "¿Tiene deudas?");
        JPanel pFinal = new JPanel(new GridLayout(3, 1));
        JTextArea txtComentarios = new JTextArea(3, 20);
        txtComentarios.setBorder(new TitledBorder("Comentarios del Entrevistador"));
        JCheckBox chkVerdad = new JCheckBox("Hago constar que mis respuestas son verdaderas");
        JButton btnEnviar = new JButton("VALIDAR Y ENVIAR SOLICITUD");
        btnEnviar.addActionListener(e -> validarFormulario(chkVerdad));
        pFinal.add(new JScrollPane(txtComentarios));
        pFinal.add(chkVerdad);
        pFinal.add(btnEnviar);
        p.add(pEcon, BorderLayout.CENTER);
        p.add(pFinal, BorderLayout.SOUTH);
        
        return p;

    }

    // --- SISTEMA DE VALIDACIÓN ---
    private void validarFormulario(JCheckBox check) {
        // 1. Validar campos vacíos
        for (JTextField f : camposAValidar) {
            if (f.getText().trim().isEmpty()) {
                f.setBackground(new Color(255, 200, 200));
                JOptionPane.showMessageDialog(this, "Faltan campos obligatorios por llenar.");
                return;
            } else {
                f.setBackground(Color.WHITE);
            }
        }
        // 2. Validar declaración de verdad
        if (!check.isSelected()) {
            JOptionPane.showMessageDialog(this, "Debe confirmar que la información es verdadera.");
            return;
        }

        if (!validarDocumentos()) {
            return;
        }

        JOptionPane.showMessageDialog(this, "¡Solicitud validada con exito!");
    }

    private boolean validarDocumentos() {
        boolean valido = true;
        if (!cedulaField.getText().trim().matches("[0-9]{10}")) {
            cedulaField.setBackground(new Color(255, 200, 200));
            valido = false;
        } else {
            cedulaField.setBackground(Color.WHITE);
        }

        if (!nssField.getText().trim().matches("[0-9]{11}")) {
            nssField.setBackground(new Color(255, 200, 200));
            valido = false;
        } else {
            nssField.setBackground(Color.WHITE);
        }

        if (!pasaporteField.getText().trim().matches("[A-Za-z0-9]{6,9}")) {
            pasaporteField.setBackground(new Color(255, 200, 200));
            valido = false;
        } else {
            pasaporteField.setBackground(Color.WHITE);
        }
        if (!licenciaField.getText().trim().matches("[A-Za-z0-9]{5,15}")) {
            licenciaField.setBackground(new Color(255, 200, 200));
            valido = false;
        } else {
            licenciaField.setBackground(Color.WHITE);
        }
        if (!valido) {
            JOptionPane.showMessageDialog(this,
                "Revise los campos de Cédula, Núm. Social, Pasaporte y Licencia."
            );

        }
        return valido;

    }
    // Método auxiliar para no repetir código de etiquetas y campos
    private void agregarCampo(JPanel panel, String etiqueta) {
        panel.add(new JLabel(etiqueta));
        JTextField tf = new JTextField();
        camposAValidar.add(tf); // Se agrega a la lista de validación
        panel.add(tf);
    }

    // --- FUNCIONES PRO (ENTER Y RECURSIVIDAD) ---
    private void habilitarEnterGlobal(Container contenedor) {
        for (Component c : contenedor.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).addActionListener(e -> c.transferFocus());
            } else if (c instanceof Container) {
                habilitarEnterGlobal((Container) c);
            }
        }
    }
}

