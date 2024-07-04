// Archivo: src/Carrito.java
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;

    public Carrito() {
        this.productos = new ArrayList<>();
    }

    public void agregarCarrito(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void vaciarCarrito() {
        productos.clear();
    }

    public double calcularImporteTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecioBase();
        }
        return total;
    }
}
