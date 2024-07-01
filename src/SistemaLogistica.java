import java.util.List;
import java.util.ArrayList;

public class SistemaLogistica {

    private List<Producto> listaProductos = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Empleado> listaEmpleados = new ArrayList<>();
    private List<Pedido> listaPedido = new ArrayList<>();


    public void registrarProducto(Producto producto) {listaProductos.add(producto);}
    public List<Producto> consultarStock() {return listaProductos;}

    public void registrarCliente(Cliente cliente) {listaClientes.add(cliente);}
    public void registrarEmpleado(Empleado empleado) {listaEmpleados.add(empleado);}

    public void registrarPedido(Pedido pedido) {listaPedido.add(pedido);}
    public Pedido obtenerPedidoPorCodigo(int codigo){
        for (Pedido pedido : listaPedidos){
            if (pedido.getCodigo() == codigo){
                return pedido;
            }
        }
        return null;
    }

    public int generarCodigoPedido(){
        return codigoPedidoActual++;
    }

    public boolean cancelarPedido(int codigo){
        Pedido pedido = obtenerPedidoPorCodigo(codigoPedido);
        if (pedido != null && pedido.getEstado().equals("Pendiente")){
            pedido.setEstado("Cancelado");
            return true;
        }
        return false;
    }

    public void modificarEntrega(int idPedido, String nuevoTipo){
        for (Pedido pedido : listaPedidos){
            if (pedido.getCodigo() == idPedido){
                pedido.setTipoEntrega(nuevoTipo);
                break;
            }
        }
    }
}
