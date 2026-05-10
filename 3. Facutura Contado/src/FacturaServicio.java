
import java.util.ArrayList;
import java.util.List;

public class FacturaServicio {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public double calcularSubtotal() {
        return productos.stream().mapToDouble(Producto::getValorTotal).sum();
    }

    public double calcularIVA() {
        return calcularSubtotal() * 0.15; // IVA del 15%
    }

    public double calcularTotalGeneral() {
        return calcularSubtotal() + calcularIVA();
    }
    
    public void limpiarFactura() {
        productos.clear();
    }
    
}

