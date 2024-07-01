import java.util.Scanner;

public class Cliente {
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;

    public Cliente(String nombre, String direccion, String telefono, String correo) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {return nombre;}
    public String getDireccion() {return direccion;}
    public String getTelefono() {return telefono;}
    public String getCorreo() {return correo;}

    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public void setCorreo(String correo) {this.correo = correo;}

    public void consultarStock(SistemaLogistica sistemaLogistica){
        System.out.println("Stock de productos disponibles: ");
        for (Producto producto : sistemaLogistica.consultarStock()){
            System.out.println(producto.getNombre() + " - Stock:" + producto.getStock());
        }
    }

    public void elegirProducto(SistemaLogistica sistemaLogistica, Carrito carrito){
        Scanner sc = new Scanner(System.in);
        int codigoProducto;
        System.out.println("Ingrese el código del producto que desea agregar al carrito: ");
        codigoProducto = sc.nextInt();
        Producto productoElegido = sistemaLogistica.obtenerProductoPorCodigo(codigoProducto);
        if(productoElegido != null && productoElegido.getStock() > 0){
            carrito.agregarCarrito(productoElegido);
            productoElegido.reducirStock(1);
            System.out.println("Producto agregado correctamente: " + productoElegido.getNombre());

        }else {
            System.out.println("Producto no disponible o sin stock.");
        }
    }

    public void validarCompra(Carrito carrito){
        Scanner sc = new Scanner(System.in);
        System.out.println("Productos en el carrito: ");
        for (Producto producto :  carrito.getProductos()){
            System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecioBase());
        }
        System.out.println("Importe total: " + carrito.calcularImporteTotal());
        System.out.println("¿Desea proceder con la compra? (S/N): ");
        String respuesta = scanner.next();
        if (respuesta.equalsIgnoreCase("s")){
            carrito.vaciarCarrito();
            System.out.println("Compra realizada con éxito.");
        }else {
            System.out.println("Compra cancelada.");
        }
    }

    public void consultarEstadoPedido(SistemaLogistica sistemaLogistica){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el código de su pedido: ");
        int codigoPedido = sc.nextInt();
        Pedido pedido = sistemaLogistica.obtenerPedidoPorCodigo(codigoPedido);
        if(pedido != null){
            System.out.println("Estado del pedido: " + pedido.getEstado());
        }else{
            System.out.println("Pedido no encontrado.");
        }
    }

    public void cancelarPedido(SistemaLogistica sistemaLogistica){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el codigo de su pedido: ");
        int codigoPedido = sc.nextInt();
        boolean resultado = sistemaLogistica.cancelarPedido(codigoPedido);
        if(resultado){
            System.out.println("Pedido cancelado con éxito.");
        }else {
            System.out.println("No se pudo cancelar el pedido. Verifique el código e intente nuevamente.");
        }
    }
}
