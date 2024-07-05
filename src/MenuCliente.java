// Archivo: src/MenuCliente.java
import java.util.InputMismatchException;
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
        opcion = opcionAccesoSeleccionado();

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
        Carrito carrito = new Carrito();

        do {
            opcionMenu = opcionMenuClienteSeleccionada();
            switch (opcionMenu) {
                case 1:
                    cliente.consultarStock(sistemaLogistica);
                    break;
                case 2:
                    cliente.elegirProductos(sistemaLogistica, carrito);
                    break;
                case 3:
                    if (carrito.getProductos().isEmpty()) {
                        System.out.println("No hay productos en el carrito, por favor elige alguno.");
                    } else {
                        cliente.validarCompra(sistemaLogistica, carrito);
                    }
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

    // recursivo
    public int opcionAccesoSeleccionado(){
        System.out.println("===================================");
        System.out.println("|       Acceso de Cliente         |");
        System.out.println("===================================");
        System.out.println("| 1. Cliente registrado           |");
        System.out.println("| 2. Nuevo cliente                |");
        System.out.println("| 3. Volver al Menu Principal     |");
        System.out.println("===================================");
        System.out.print("Seleccione una opción: ");
        int opcion;
        try{
            opcion = scanner.nextInt();
            if (opcion < 1 || opcion > 3) {
                System.out.println("❌ Opción inválida. Por favor, ingrese una opción válida. ❌");
                opcion = opcionAccesoSeleccionado();
            }

        }catch (InputMismatchException e){
            System.out.println("❌ Por favor, ingrese un número entero. ❌");
            scanner.next();
            opcion = opcionAccesoSeleccionado();
        }
        return opcion;
    }

    // recursivo
    public int opcionMenuClienteSeleccionada(){
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
        int opcion;
        try{
            opcion = scanner.nextInt();
            if (opcion < 1 || opcion > 6) {
                System.out.println("❌ Opción inválida. Por favor, ingrese una opción válida. ❌");
                opcion = opcionMenuClienteSeleccionada();
            }

        }catch (InputMismatchException e){
            System.out.println("❌ Por favor, ingrese un número entero. ❌");
            scanner.next();
            opcion = opcionMenuClienteSeleccionada();
        }
        return opcion;
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
        try {
            System.out.println("===================================");
            System.out.println("|        Registro de Cliente      |");
            System.out.println("===================================");
            System.out.println("📑 Ingrese sus datos:");
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

            System.out.println("✔ Registro exitoso. Bienvenido, " + cliente.getNombre() + " " + cliente.getApellido());
        }catch (Exception e){
            System.out.println("⚠ Ocurrio un error en el registro.");
        }

    }

    private void consultarEstadoPedido() {
        List<Pedido> pedidosCliente = sistemaLogistica.obtenerPedidosPorCliente(cliente);

        if (pedidosCliente.isEmpty()) {
            System.out.println("No hay pedidos registrados aún. Regresando al menú anterior...");
            // System.out.println("Presione enter para regresar al menú.");
            // scanner.nextLine();  // Pausa
            // scanner.nextLine();  // Espera por enter
            return;
        }else{
            for (Pedido pedido : pedidosCliente) {
                System.out.println("Pedido " + pedido.getCodigo() + " - Estado: " + pedido.getEstado() + " - Tipo de Entrega: " + pedido.getTipoEntrega());
            }
        }
    }
}
