package ArqueoCaja;

public class ArqueoCaja {
    private ArqueoServicio arqueoServicio;

    public ArqueoCaja() {
        arqueoServicio = new ArqueoServicio();
    }

    public void realizarArqueo(double efectivoInicial, double efectivoContado) {
        arqueoServicio.setEfectivoInicial(efectivoInicial);
        arqueoServicio.setEfectivoContado(efectivoContado);
    }

    public double getTotalVentas() {
        return arqueoServicio.calcularTotalVentas();
    }

    public double getEfectivoEsperado() {
        return arqueoServicio.calcularEfectivoEsperado();
    }

    public double getDiferencia() {
        return arqueoServicio.calcularDiferencia();
    }

    public void agregarVenta(double venta) {
        arqueoServicio.agregarVenta(venta);
    }

    public void limpiarArqueo() {
        arqueoServicio.limpiarArqueo();
    }

    public ArqueoServicio getArqueoServicio() {
        return arqueoServicio;
    }
}