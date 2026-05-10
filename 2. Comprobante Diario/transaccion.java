public class transaccion {
    private String cuenta;
    private double debe;
    private double haber;
    private String descripcion;

    public transaccion(String cuenta, double debe, double haber, String descripcion) {
        this.cuenta = cuenta;
        this.debe = debe;
        this.haber = haber;
        this.descripcion = descripcion;
    }

    // Métodos para obtener datos (Getters)
    public String getCuenta() { return cuenta; }
    public double getDebe() { return debe; }
    public double getHaber() { return haber; }
    public String getDescripcion() { return descripcion; }
}