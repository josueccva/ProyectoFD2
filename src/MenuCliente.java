// Archivo: src/MenuCliente.java
import java.util.Scanner;
import java.util.List;

public class MenuCliente {
    private SistemaLogistica sistemaLogistica;
    private Cliente cliente;
    private Scanner scanner;

    public MenuCliente(SistemaLogistica sistemaLogistica, Scanner scanner) {
        this.sistemaLogistica = sistemaLogistica;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        int opcion;

        System.out.println("===================================");
        System.out.println("|       Acceso de Cliente         |");
        System.out.println("===================================");
        System.out.println("| 1. Cliente registrado           |");
        System.out.println("| 2. Nuevo cliente                |");
        System.out.println("===================================");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                if (!clienteRegistrado()) {
                    return;
                }
                break;
            case 2:
                nuevoCliente();
                break;
            default:
                System.out.println("Opción no válida. Regresando al menú principal...");
                return;
        }

        int opcionMenu;
        Carrito carrito = new Carrito("");

        do {
            System.out.println("===================================");
            System.out.println("|           Menú Cliente          |");
            System.out.println("===================================");
            System.out.println("| 1. Consultar stock de productos |");
            System.out.println("| 2. Elegir productos             |");
            System.out.println("| 3. Validar y proceder con la    |");
            System.out.println("|    compra                       |");
            System.out.println("| 4. Consultar estado del pedido  |");
            System.out.println("| 5. Cancelar pedido              |");
            System.out.println("| 6. Regresar al menú principal   |");
            System.out.println("===================================");
            System.out.print("Seleccione una opción: ");
            opcionMenu = scanner.nextInt();
            scanner.nextLine();

            switch (opcionMenu) {
                case 1:
                    cliente.consultarStock(sistemaLogistica);
                    break;
                case 2:
                    cliente.elegirProductos(sistemaLogistica, carrito);
                    break;
                case 3:
                    cliente.validarCompra(sistemaLogistica, carrito);
                    break;
                case 4:
                    consultarEstadoPedido();
                    break;
                case 5:
                    cliente.cancelarPedido(sistemaLogistica);
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            if (opcionMenu != 6) {
                System.out.println("Presione enter para regresar al menú.");
                scanner.nextLine();  // Pausa
                scanner.nextLine();  // Espera por enter
            }
        } while (opcionMenu != 6);
    }

    private boolean clienteRegistrado() {
        scanner.nextLine();  // Consumir el salto de línea
        System.out.print("Ingrese su correo registrado: ");
        String correo = scanner.nextLine();
        cliente = sistemaLogistica.buscarClientePorCorreo(correo);

        if (cliente == null) {
            System.out.println("Cliente no encontrado. Regresando al menú principal...");
            return false;
        }

        System.out.println("Bienvenido de nuevo, " + cliente.getNombre() + " " + cliente.getApellido());
        return true;
    }

    private void nuevoCliente() {
        System.out.println("===================================");
        System.out.println("|        Registro de Cliente      |");
        System.out.println("===================================");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Apellido: ");
        String apellido = scanner.next();
        scanner.nextLine();  // Consumir el salto de línea
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        cliente = new Cliente(nombre, apellido, direccion, correo, telefono);
        sistemaLogistica.registrarCliente(cliente);

        System.out.println("Registro exitoso. Bienvenido, " + cliente.getNombre() + " " + cliente.getApellido());
    }

    private void consultarEstadoPedido() {
        List<Pedido> pedidosCliente = sistemaLogistica.obtenerPedidosPorCliente(cliente);

        if (pedidosCliente.isEmpty()) {
            System.out.println("No hay pedidos registrados aún. Regresando al menú anterior...");
            System.out.println("Presione enter para regresar al menú.");
            scanner.nextLine();  // Pausa
            scanner.nextLine();  // Espera por enter
            return;
        }

        for (Pedido pedido : pedidosCliente) {
            System.out.println("Pedido " + pedido.getCodigo() + " - Estado: " + pedido.getEstado());
        }
    }
}
