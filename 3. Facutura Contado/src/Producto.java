public class Producto {
    private int cantidad;
    private String descripcion;
    private double precioUnitario;

    public Producto(int cantidad, String descripcion, double precioUnitario) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
    }

    public double getValorTotal() {
        return this.cantidad * this.precioUnitario;
    }

    // Getters para la tabla
    public int getCantidad() { return cantidad; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioUnitario() { return precioUnitario; }
}

