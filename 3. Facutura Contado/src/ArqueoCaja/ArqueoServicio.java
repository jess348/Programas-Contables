package ArqueoCaja;

import java.util.ArrayList;
import java.util.List;

public class ArqueoServicio {
    private double efectivoInicial;
    private List<Double> ventas = new ArrayList<>();
    private double efectivoContado;

    public void setEfectivoInicial(double efectivoInicial) {
        this.efectivoInicial = efectivoInicial;
    }

    public void agregarVenta(double venta) {
        ventas.add(venta);
    }

    public void setEfectivoContado(double efectivoContado) {
        this.efectivoContado = efectivoContado;
    }

    public double calcularTotalVentas() {
        return ventas.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calcularEfectivoEsperado() {
        return efectivoInicial + calcularTotalVentas();
    }

    public double calcularDiferencia() {
        return efectivoContado - calcularEfectivoEsperado();
    }

    public void limpiarArqueo() {
        efectivoInicial = 0;
        ventas.clear();
        efectivoContado = 0;
    }

    // Getters para acceder desde otras clases
    public double getEfectivoInicial() {
        return efectivoInicial;
    }

    public List<Double> getVentas() {
        return new ArrayList<>(ventas);
    }

    public double getEfectivoContado() {
        return efectivoContado;
    }
}
