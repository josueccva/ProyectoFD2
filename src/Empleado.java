public class Empleado {
    private int id;
    private String nombre;
    private String posicion;

    public Empleado(int id, String nombre, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getPosicion() {return posicion;}

    public void setId(int id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPosicion(String posicion) {this.posicion = posicion;}

    public void registrarProducto(SistemaLogistica sistemaLogistica, Producto producto){
        sistemaLogistica.registrarProducto(producto);
        System.out.println("Producto Registrada con exito" + producto.getNombre());
    }

    public void añadirStock(SistemaLogistica sistemaLogistica, int codigoProducto, int cantidad){
        Producto producto = sistemaLogistica.obtenerPedidoPorCodigo(codigoProducto);
        if (producto != null){
            producto.añadirStock(cantidad);
            System.out.println("Stock registrada con exito. Nuevo stock de: " + producto.getNombre() + ": " + producto.getStock());
        }else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void reducirStock(SistemaLogistica sistemaLogistica, int codigoProducto, int cantidad){
        Producto producto = sistemaLogistica.obtenerPedidoPorCodigo(codigoProducto);
        if (producto != null){
            if (producto.getStock) >= cantidad){
                producto.reducirStock(cantidad);
                System.out.println("Stock reducido con exito. Nuevo stock de " + producto.getNombre() + ": " + producto.getStock());
            } else{
                System.out.println("No hay suficiente stock para retirar esa cantidad.");
            }
        }else{
            System.out.println("Producto no encontrado.");
        }
    }

    public void consultarStock(SistemaLogistica sistemaLogistica){
        System.out.println("Stock de productos disponibles: ");
        for (Producto producto: sistemaLogistica.consultarStock()){
            System.out.println(producto.getNombre() + " - Stock: " + producto.getStock());
        }
    }

    public void consultarPedidos(SistemaLogistica sistemaLogistica){
        System.out.println("Pedidos realizados: ");
        for (Pedido pedido : listaPedidos){
            if (pedido.getCodigo() == idPedido){
                pedido.setTipoEntrega(nuevoTipo);
                break;
            }
        }
    }

}
