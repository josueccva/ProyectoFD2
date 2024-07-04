// Archivo: src/Cliente.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String telefono;

    public Cliente(String nombre, String apellido, String direccion, String correo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDireccion() { return direccion; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public void consultarStock(SistemaLogistica sistemaLogistica) {
        System.out.println("Stock de productos disponibles:");
        for (Producto producto : sistemaLogistica.consultarStock()) {
            System.out.println(producto.getNombre() + " - Stock: " + producto.getStock());
        }
    }

    public void elegirProductos(SistemaLogistica sistemaLogistica, Carrito carrito) {
        Scanner scanner = new Scanner(System.in);
        int codigoProducto, cantidad;
        System.out.print("Ingrese el código del producto que desea agregar al carrito: ");
        codigoProducto = scanner.nextInt();
        Producto productoElegido = sistemaLogistica.obtenerProductoPorCodigo(codigoProducto);

        if (productoElegido != null && productoElegido.getStock() > 0) {
            System.out.print("Ingrese la cantidad que desea agregar al carrito: ");
            cantidad = scanner.nextInt();

            if (cantidad > 0 && cantidad <= productoElegido.getStock()) {
                for (int i = 0; i < cantidad; i++) {
                    carrito.agregarCarrito(productoElegido);
                }
                productoElegido.reducirStock(cantidad);
                System.out.println("Producto(s) agregado(s) al carrito: " + productoElegido.getNombre() + " - Cantidad: " + cantidad);
            } else {
                System.out.println("Cantidad no válida o fuera de stock.");
            }
        } else {
            System.out.println("Producto no disponible o sin stock.");
        }
    }

    public void validarCompra(SistemaLogistica sistemaLogistica, Carrito carrito) {
        if (carrito.getProductos().isEmpty()) {
            System.out.println("No hay productos en el carrito, por favor elige alguno.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Productos en el carrito:");
        for (Producto producto : carrito.getProductos()) {
            System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecioBase());
        }
        System.out.println("Importe total: " + carrito.calcularImporteTotal());
        System.out.print("¿Desea proceder con la compra? (s/n): ");
        String respuesta = scanner.next();
        if (respuesta.equalsIgnoreCase("s")) {
            Pedido pedido = new Pedido(sistemaLogistica.generarCodigoPedido(), this, carrito.getProductos(), "Pendiente", "Normal");
            sistemaLogistica.registrarPedido(pedido);
            System.out.println("Compra realizada con éxito. Su código de pedido es: " + pedido.getCodigo());
            pedido.generarReportePedido();
            carrito.vaciarCarrito();// Generar y mostrar el reporte del pedido con descuentos aplicados
        } else {
            System.out.println("Compra cancelada.");
        }
    }

    public void consultarEstadoPedido(SistemaLogistica sistemaLogistica) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código de su pedido: ");
        int codigoPedido = scanner.nextInt();
        Pedido pedido = sistemaLogistica.obtenerPedidoPorCodigo(codigoPedido);
        if (pedido != null) {
            System.out.println("Estado del pedido: " + pedido.getEstado());
            System.out.println("Tipo de entrega: " + pedido.getTipoEntrega());
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }

    public void cancelarPedido(SistemaLogistica sistemaLogistica) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código de su pedido: ");
        int codigoPedido = scanner.nextInt();
        boolean resultado = sistemaLogistica.cancelarPedido(codigoPedido);
        if (resultado) {
            System.out.println("Pedido cancelado con éxito.");
        } else {
            System.out.println("No se pudo cancelar el pedido. Verifique el código e intente nuevamente.");
        }
    }
}
