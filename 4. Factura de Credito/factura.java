

public class factura {
    public static final double IVA_PORCENTAJE = 0.15; // IVA 15% según imagen

    public double calcularIVA(double subtotal) {
        return subtotal * IVA_PORCENTAJE;
    }

    public double calcularTotal(double subtotal) {
        return subtotal + calcularIVA(subtotal);
    }
}