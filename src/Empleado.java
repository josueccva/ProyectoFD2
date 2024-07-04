import java.util.ArrayList;
import java.util.List;

public class Empleado {
    private int id;
    private String nombre;
    private String posicion;
    private static List<Empleado> empleados = new ArrayList<>();

    public Empleado(int id, String nombre, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        empleados.add(this);
    }

    public Empleado(String nombre, String posicion) {
        this.id = generarCodigo();
        this.nombre = nombre;
        this.posicion = posicion;
        empleados.add(this);
    }

    private int generarCodigo(){
        int cod = getId()+1;
        System.out.println("gen cod:" + cod);
        if (empleados.isEmpty()){
            return 1;
        }else {
            return empleados.getLast().getId() + 1;
        }
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public void registrarProducto(SistemaLogistica sistemaLogistica, Producto producto) {
        sistemaLogistica.registrarProducto(producto);
        System.out.println("Producto registrado: " + producto.getNombre());
    }

    public void añadirStock(SistemaLogistica sistemaLogistica, int codigoProducto, int cantidad) {
        try {
            Producto producto = sistemaLogistica.obtenerProductoPorCodigo(codigoProducto);
            if (producto != null) {
                producto.añadirStock(cantidad);
                System.out.println("Stock añadido. Nuevo stock de " + producto.getNombre() + ": " + producto.getStock());
            } else {
                System.out.println("Producto no encontrado.");
            }
            // throw new NullPointerException("Forzando throw");
        } catch (Exception e) {
            System.out.println("Error al añadir stock: " + e.getMessage());
        }
    }

    public void reducirStock(SistemaLogistica sistemaLogistica, int codigoProducto, int cantidad) {
        try {
            Producto producto = sistemaLogistica.obtenerProductoPorCodigo(codigoProducto);
            if (producto != null) {
                if (producto.getStock() >= cantidad) {
                    producto.reducirStock(cantidad);
                    System.out.println("Stock reducido. Nuevo stock de " + producto.getNombre() + ": " + producto.getStock());
                } else {
                    System.out.println("No hay suficiente stock para retirar esa cantidad.");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al retirar stock: " + e.getMessage());
        }
    }

    public void consultarStock(SistemaLogistica sistemaLogistica) {
        try{
            System.out.println("Stock de productos disponibles:");
            for (Producto producto : sistemaLogistica.consultarStock()) {
                System.out.println(producto.getNombre() + " - Stock: " + producto.getStock());
            }
        }catch (Exception e) {
            System.out.println("Error al consultar stock: " + e.getMessage());
        }

    }

    public void consultarPedidos(SistemaLogistica sistemaLogistica) {
        try{
            List<Pedido> pedidos = sistemaLogistica.getListaPedidos();
            if (pedidos.isEmpty()) {
                System.out.println("No hay pedidos registrados.");
            } else {
                System.out.println("Pedidos registrados:");
                for (Pedido pedido : pedidos) {
                    System.out.println("Pedido " + pedido.getCodigo() + " - Cliente: " + pedido.getCliente().getNombre() + " - Estado: " + pedido.getEstado());
                }
            }
        }catch (Exception e){
            System.out.println("Error al consultar pedidos: " + e.getMessage());
        }
    }
}

