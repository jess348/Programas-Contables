package comprobantepago;

public class Transaccion {
    private String cod, cuenta;
    private double parcial, debe, haber;

    public Transaccion(String cod, String cuenta, double parcial, double debe, double haber) {
        this.cod = cod;
        this.cuenta = cuenta;
        this.parcial = parcial;
        this.debe = debe;
        this.haber = haber;
    }

    public String getCod() { return cod; }
    public String getCuenta() { return cuenta; }
    public double getParcial() { return parcial; }
    public double getDebe() { return debe; }
    public double getHaber() { return haber; }
}