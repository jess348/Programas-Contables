package facturacontado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.swing.text.DateFormatter;
// import java.text.SimpleDateFormat;
/**
 * Clase principal de la aplicación de Factura de Contado.
 * Esta clase crea una interfaz gráfica usando Swing para gestionar facturas,
 * permitiendo agregar productos, calcular totales y mostrar información del cliente y empresa.
 */public class FrmFacturaContado extends JInternalFrame {
    // Servicio que maneja la lógica de negocio de la factura
    private FacturaServicio facturaServicio;

    // Campos de texto para entrada de datos de productos
    private JTextField txtCantidad, txtDescripcion, txtPrecioUnitario;

    // Campos para información del cliente
    private JTextField txtNombreCliente;
    // private JFormattedTextField txtFechaVencimiento;

    // Checkbox para indicar si es pago de contado
    private JCheckBox chkPagoContado;

    // Tabla para mostrar los productos agregados
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    // Etiquetas para mostrar los totales calculados
    private JLabel lblSubtotal, lblIVA, lblTotal;

    // Botones para acciones del usuario
    private JButton btnAgregarProducto, btnCalcularTotales, btnLimpiarFactura;

    /**
     * Constructor de la clase. Inicializa la interfaz gráfica,
     * configura los componentes y establece los eventos.
     */
    public FrmFacturaContado() {
        super("Factura de Contado", true, true, true, true);
        // Inicializar el servicio de factura
        facturaServicio = new FacturaServicio();

        // Configurar la ventana principal
        setSize(760, 660);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Definir estilos generales para la interfaz(RECORDAR CAMBIAR COLORES xD)
        Font tituloFont = new Font("Arial", Font.BOLD, 16);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color bgColor = new Color(240, 248, 255); // Azul muy claro
        Color buttonColor = new Color(70, 130, 180); // Azul acero
        Color buttonTextColor = Color.WHITE;

        // Establecer el color de fondo de la ventana
        getContentPane().setBackground(bgColor);

        // Crear el panel de encabezado con información de la empresa
        JPanel panelEncabezado = new JPanel(new GridLayout(6, 1, 5, 5));
        // panelEncabezado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 2), "Encabezado", 0, 0, tituloFont, Color.BLUE));
        panelEncabezado.setBackground(bgColor);

        JLabel lblTituloFactura = new JLabel("Factura de Contado", SwingConstants.CENTER);
        lblTituloFactura.setFont(new Font("Arial", Font.BOLD, 20));
        lblTituloFactura.setForeground(Color.BLUE);
        panelEncabezado.add(lblTituloFactura);

        JLabel lblNombreEmpresa = new JLabel("Empresa Comercial El Tisey S.A.", SwingConstants.CENTER);
        lblNombreEmpresa.setFont(labelFont);
        panelEncabezado.add(lblNombreEmpresa);

        JLabel lblDireccionEmpresa = new JLabel("Estelí, Nicaragua, del Semáforo del Parque Central 1 cuadra al Norte y 75 varas al Este", SwingConstants.CENTER);
        lblDireccionEmpresa.setFont(labelFont);
        panelEncabezado.add(lblDireccionEmpresa);

        // JLabel lblNITEmpresa = new JLabel("NIT: 123456789-0", SwingConstants.CENTER);
        // lblNITEmpresa.setFont(labelFont);
        // panelEncabezado.add(lblNITEmpresa);

        JLabel lblTelefonoEmpresa = new JLabel("Teléfono: No. 2713-9999", SwingConstants.CENTER);
        lblTelefonoEmpresa.setFont(labelFont);
        panelEncabezado.add(lblTelefonoEmpresa);

        JLabel lblEmailEmpresa = new JLabel("Encuentranos en: www.eltisey.com", SwingConstants.CENTER);
        lblEmailEmpresa.setFont(labelFont);
        panelEncabezado.add(lblEmailEmpresa);

        // Panel Norte combinado
        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.add(panelEncabezado, BorderLayout.NORTH);

        // Crear el panel para información del cliente
        JPanel panelCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCliente.setBackground(bgColor);

        JLabel lblNombreCliente = new JLabel("Nombre Cliente:");
        lblNombreCliente.setFont(labelFont);
        panelCliente.add(lblNombreCliente);

        txtNombreCliente = new JTextField(20);
        txtNombreCliente.setFont(labelFont);
        panelCliente.add(txtNombreCliente);

        JLabel lblFechaVencimiento = new JLabel("Fecha Vencimiento:");
        lblFechaVencimiento.setFont(labelFont);
        panelCliente.add(lblFechaVencimiento);

        // txtFechaVencimiento = new JFormattedTextField(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy")));
        // txtFechaVencimiento.setColumns(10);
        // txtFechaVencimiento.setFont(labelFont);
        // panelCliente.add(txtFechaVencimiento);

        chkPagoContado = new JCheckBox("Pago de Contado");
        chkPagoContado.setFont(labelFont);
        chkPagoContado.setBackground(bgColor);
        chkPagoContado.setSelected(true); // Por defecto marcado
        panelCliente.add(chkPagoContado);

        panelNorth.add(panelCliente, BorderLayout.CENTER);

        // Crear el panel para agregar productos
        JPanel panelAgregar = new JPanel(new GridLayout(4, 2, 10, 10));
        panelAgregar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 2), "Agregar Producto", 0, 0, tituloFont, Color.BLUE));
        panelAgregar.setBackground(bgColor);

        // Campos para agregar productos esto se puede mejorar con un combo box para seleccionar productos predefinidos y solo ingresar cantidad, pero bueno, esto es lo que se pidió xD
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(labelFont);
        panelAgregar.add(lblCantidad);
        txtCantidad = new JTextField();
        txtCantidad.setFont(labelFont);
        panelAgregar.add(txtCantidad);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(labelFont);
        panelAgregar.add(lblDescripcion);
        txtDescripcion = new JTextField();
        txtDescripcion.setFont(labelFont);
        panelAgregar.add(txtDescripcion);

        //recordar
        JLabel lblPrecio = new JLabel("Precio Unitario:");
        lblPrecio.setFont(labelFont);
        panelAgregar.add(lblPrecio);
        txtPrecioUnitario = new JTextField();
        txtPrecioUnitario.setFont(labelFont);
        panelAgregar.add(txtPrecioUnitario);

        btnAgregarProducto = new JButton("Agregar Producto");
        btnAgregarProducto.setFont(buttonFont);
        btnAgregarProducto.setBackground(buttonColor);
        btnAgregarProducto.setForeground(buttonTextColor);
        btnAgregarProducto.setFocusPainted(false);
        panelAgregar.add(btnAgregarProducto);

        panelNorth.add(panelAgregar, BorderLayout.SOUTH);
        add(panelNorth, BorderLayout.NORTH);

        // Crear la tabla para mostrar los productos agregados
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio Unitario");
        modeloTabla.addColumn("Valor Total");
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setFont(labelFont);
        tablaProductos.setRowHeight(25);
        tablaProductos.getTableHeader().setFont(tituloFont);
        tablaProductos.getTableHeader().setBackground(buttonColor);
        tablaProductos.getTableHeader().setForeground(buttonTextColor);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Productos Agregados"));
        add(scrollPane, BorderLayout.CENTER);

        // Crear el panel para mostrar los totales y botones de acción
        JPanel panelTotales = new JPanel(new GridLayout(4, 2, 10, 10));
        panelTotales.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 2), "Totales", 0, 0, tituloFont, Color.BLUE));
        panelTotales.setBackground(bgColor);

        JLabel lblSub = new JLabel("Subtotal:");
        lblSub.setFont(labelFont);
        panelTotales.add(lblSub);
        lblSubtotal = new JLabel("0.00");
        lblSubtotal.setFont(labelFont);
        lblSubtotal.setForeground(Color.RED);
        panelTotales.add(lblSubtotal);

        JLabel lblIva = new JLabel("IVA (15%):");
        lblIva.setFont(labelFont);
        panelTotales.add(lblIva);
        lblIVA = new JLabel("0.00");
        lblIVA.setFont(labelFont);
        lblIVA.setForeground(Color.RED);
        panelTotales.add(lblIVA);

        JLabel lblTot = new JLabel("Total General:");
        lblTot.setFont(labelFont);
        panelTotales.add(lblTot);
        lblTotal = new JLabel("0.00");
        lblTotal.setFont(tituloFont);
        lblTotal.setForeground(Color.BLUE);
        panelTotales.add(lblTotal);

        btnCalcularTotales = new JButton("Calcular Totales");
        btnCalcularTotales.setFont(buttonFont);
        btnCalcularTotales.setBackground(buttonColor);
        btnCalcularTotales.setForeground(buttonTextColor);
        btnCalcularTotales.setFocusPainted(false);
        panelTotales.add(btnCalcularTotales);

        btnLimpiarFactura = new JButton("Limpiar Factura");
        btnLimpiarFactura.setFont(buttonFont);
        btnLimpiarFactura.setBackground(Color.RED);
        btnLimpiarFactura.setForeground(Color.WHITE);
        btnLimpiarFactura.setFocusPainted(false);
        panelTotales.add(btnLimpiarFactura);

        add(panelTotales, BorderLayout.SOUTH);

        // Configurar ActionListeners para navegación con Enter entre campos
        txtCantidad.addActionListener(e -> txtDescripcion.requestFocus());
        txtDescripcion.addActionListener(e -> txtPrecioUnitario.requestFocus());
        txtPrecioUnitario.addActionListener(e -> btnAgregarProducto.doClick());

        // Configurar los eventos de los botones
        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        btnCalcularTotales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotales();
            }
        });

        btnLimpiarFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFactura();
            }
        });
    }

    /**
     * Método para agregar un producto a la factura.
     * Lee los valores de los campos, crea un objeto Producto y lo agrega al servicio.
     * También actualiza la tabla y limpia los campos.
     */
    private void agregarProducto() {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            String descripcion = txtDescripcion.getText();
            double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText());

            Producto producto = new Producto(cantidad, descripcion, precioUnitario);
            facturaServicio.agregarProducto(producto);

            // Agregar a la tabla
            modeloTabla.addRow(new Object[]{cantidad, descripcion, precioUnitario, producto.getValorTotal()});

            // Limpiar campos
            txtCantidad.setText("");
            txtDescripcion.setText("");
            txtPrecioUnitario.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para calcular y mostrar los totales de la factura.
     * Obtiene subtotal, IVA y total del servicio y actualiza las etiquetas.
     */
    private void calcularTotales() {
        double subtotal = facturaServicio.calcularSubtotal();
        double iva = facturaServicio.calcularIVA();
        double total = facturaServicio.calcularTotalGeneral();

        lblSubtotal.setText(String.format("%.2f", subtotal));
        lblIVA.setText(String.format("%.2f", iva));
        lblTotal.setText(String.format("%.2f", total));
    }

    /**
     * Método para limpiar la factura.
     * Resetea el servicio, limpia la tabla y pone los totales en cero.
     */
    private void limpiarFactura() {
        facturaServicio.limpiarFactura();
        modeloTabla.setRowCount(0);
        lblSubtotal.setText("0.00");
        lblIVA.setText("0.00");
        lblTotal.setText("0.00");
    }

    /**
     * Método main para iniciar la aplicación.
     * Crea una instancia de la ventana y la hace visible en el hilo de eventos de Swing.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Factura de Contado");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(820, 720);
            frame.setLocationRelativeTo(null);

            JDesktopPane desktopPane = new JDesktopPane();
            FrmFacturaContado internalFactura = new FrmFacturaContado();
            desktopPane.add(internalFactura);
            internalFactura.setVisible(true);

            frame.add(desktopPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
