// Archivo: src/SistemaLogistica.java
import java.util.ArrayList;
import java.util.List;

public class SistemaLogistica {
    private List<Producto> listaProductos = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Empleado> listaEmpleados = new ArrayList<>();
    private List<Pedido> listaPedidos = new ArrayList<>();
    private int codigoPedidoActual = 1;

    public void registrarProducto(Producto producto) { listaProductos.add(producto); }
    public List<Producto> consultarStock() { return listaProductos; }

    public void registrarCliente(Cliente cliente) { listaClientes.add(cliente); }
    public void registrarEmpleado(Empleado empleado) { listaEmpleados.add(empleado); }

    public void registrarPedido(Pedido pedido) { listaPedidos.add(pedido); }
    public List<Pedido> getListaPedidos() { return listaPedidos; }

    public Pedido obtenerPedidoPorCodigo(int codigo) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCodigo() == codigo) {
                return pedido;
            }
        }
        return null;
    }

    public Producto obtenerProductoPorCodigo(int codigo) {
        for (Producto producto : listaProductos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public int generarCodigoPedido() {
        return codigoPedidoActual++;
    }

    public boolean cancelarPedido(int codigoPedido) {
        Pedido pedido = obtenerPedidoPorCodigo(codigoPedido);
        if (pedido != null && pedido.getEstado().equals("Pendiente")) {
            pedido.setEstado("Cancelado");
            return true;
        }
        return false;
    }

    public void modificarEntrega(int idPedido, String nuevoTipo) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCodigo() == idPedido) {
                pedido.setTipoEntrega(nuevoTipo);
                break;
            }
        }
    }

    public Cliente buscarClientePorCorreo(String correo) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCorreo().equals(correo)) {
                return cliente;
            }
        }
        return null;
    }

    public Empleado buscarEmpleadoPorId(int id) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    public List<Pedido> obtenerPedidosPorCliente(Cliente cliente) {
        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCliente().equals(cliente)) {
                pedidosCliente.add(pedido);
            }
        }
        return pedidosCliente;
    }
}
