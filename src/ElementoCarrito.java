public class ElementoCarrito {
    private Producto producto;
    // private Promocion promocion;
    private int cantidad;

    public ElementoCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double calcularSubtotal() {
        return producto.getPrecioBase() * cantidad;
    }



    @Override
    public String toString() {
        return "producto:" + producto.getNombre() + ", cantidad:" + cantidad + ", subtotal:" + calcularSubtotal();
    }
}