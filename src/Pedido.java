import java.util.Date;
import java.util.List;

public class Pedido {
    private int codigo;
    private Cliente cliente;
    private List<Producto> productos;
    private Date fecha;
    private String estado;
    private String tipoEntrega;

    public Pedido(int codigo, Cliente cliente, List<Producto> productos, String estado, String tipoEntrega) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.productos = productos;
        this.fecha = new Date();
        this.estado = estado;
        this.tipoEntrega = tipoEntrega;
    }

    public int getCodigo() { return codigo; }
    public Cliente getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }
    public Date getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public String getTipoEntrega() { return tipoEntrega; }

    public void setEstado(String estado) { this.estado = estado; }
    public void setTipoEntrega(String tipoEntrega) { this.tipoEntrega = tipoEntrega; }

    public double calcularImporteTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecioBase();
        }
        return total;
    }

    public void generarReportePedido() {
        System.out.println("Reporte del Pedido " + codigo);
        System.out.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
        System.out.println("Fecha: " + fecha);
        System.out.println("Estado: " + estado);
        System.out.println("Tipo de Entrega: " + tipoEntrega);
        System.out.println("Productos:");
        for (Producto producto : productos) {
            System.out.println("- " + producto.getNombre() + " (Precio: " + producto.getPrecioBase() + ")");
        }
        System.out.println("Importe Total: " + calcularImporteTotal());
    }
}
