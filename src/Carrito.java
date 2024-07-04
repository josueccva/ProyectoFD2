import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private String codigoDescuento;
    private List<Producto> productos;

    public Carrito(String codigoDescuento) {
        this.codigoDescuento = codigoDescuento;
        this.productos = new ArrayList<>();
    }

    public String getCodigoDescuento() { return codigoDescuento; }

    public List<Producto> getProductos() { return productos; }

    public void agregarCarrito(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado al carrito: " + producto.getNombre());
    }

    public void vaciarCarrito() {
        productos.clear();
        System.out.println("El carrito ha sido vaciado.");
    }

    public double calcularImporteTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecioBase();
        }
        return total;
    }
}
