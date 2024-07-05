public abstract class Producto {
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private boolean oferta;
    private int stock;

    public Producto(int codigo, String nombre, String descripcion, double precioBase, boolean oferta, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.oferta = oferta;
        this.stock = stock;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecioBase() { return precioBase; }
    public boolean isOferta() { return oferta; }
    public int getStock() { return stock; }

    public void añadirStock(int cantidad) { this.stock += cantidad; }
    public void reducirStock(int cantidad) { this.stock -= cantidad; }

    public abstract String getCategoria();
    public abstract double calcularPrecio();

    public static void consultarStockProductos() {
    }

    public int obtenerSiguienteCodigo(){
        int nextCodigo = getCodigo() + 1;
        System.out.println("codigo asignado: " + nextCodigo);
        return nextCodigo;
    }

    @Override
    public String toString() {
        return "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", Precio Base=" + precioBase +
                ", oferta=" + oferta +
                ", Precio=" + calcularPrecio() +
                ", stock=" + stock;
    }
}
