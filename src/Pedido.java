// Archivo: src/Pedido.java
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
    public String getEstado() { return estado; }
    public String getTipoEntrega() { return tipoEntrega; }

    public void setEstado(String estado) { this.estado = estado; }
    public void setTipoEntrega(String tipoEntrega) { this.tipoEntrega = tipoEntrega; }

    public double calcularImporteTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.calcularPrecio();
        }
        return total;
    }

    public double calcularImporteConDescuento() {
        double total = calcularImporteTotal();
        double descuento = 0;

        if (total > 200) {
            descuento = total * 0.05;
        } else if (productos.size() > 4) {
            descuento = total * 0.10;
        }

        return total - descuento;
    }

    public void generarReportePedido() {
        try{
            System.out.println("===================================");
            System.out.println("Reporte del Pedido " + codigo);
            System.out.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
            System.out.println("Fecha: " + fecha);
            System.out.println("Estado: " + estado);
            System.out.println("Tipo de Entrega: " + tipoEntrega);
            System.out.println("Productos:");

            Map<String, Integer> productoCantidad = new HashMap<>();
            Map<String, Double> productoPrecio = new HashMap<>();

            for (Producto producto : productos) {
                String nombre = producto.getNombre();
                if (productoCantidad.containsKey(nombre)) {
                    productoCantidad.put(nombre, productoCantidad.get(nombre) + 1);
                } else {
                    productoCantidad.put(nombre, 1);
                    productoPrecio.put(nombre, producto.calcularPrecio());
                }
            }

            for (Map.Entry<String, Integer> entry : productoCantidad.entrySet()) {
                String nombre = entry.getKey();
                int cantidad = entry.getValue();
                double precio = productoPrecio.get(nombre);
                System.out.println("- " + nombre + " - Precio Unitario: " + precio + " - Cantidad: " + cantidad);
            }

            System.out.println("Importe Total sin Descuento: " + calcularImporteTotal());
            System.out.println("Importe Total con Descuento: " + calcularImporteConDescuento());
            System.out.println("===================================");
        }catch (Exception e){
            System.out.println("Ocurrio un error al generar el reporte" + e.getMessage());
        }

    }
}
