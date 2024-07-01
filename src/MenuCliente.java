import java.util.Scanner;

public class MenuCliente {

    private SistemaLogistica sistemaLogistica;
    private Cliente cliente;

    public MenuCliente(SistemaLogistica sistemaLogistica) {
        this.sistemaLogistica = new SistemaLogistica();
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        Carrito carrito = new Carrito("");

        System.out.println("---REGISTRO DE CLIENTE---");
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Dirección: ");
        String direccion = sc.nextLine();
        System.out.println("Correo: ");
        String correo = sc.nextLine();
        System.out.println("Telefono: ");
        String telefono = sc.nextLine();

        cliente = new Cliente(nombre, direccion, correo, telefono);
        sistemaLogistica.registrarCliente(cliente);

        do {
            System.out.println("1. Consultar stock de productos");
            System.out.println("2. Elegir productos");
            System.out.println("3. Validar y proceder compra");
            System.out.println("4. Consultar estado del pedido");
            System.out.println("5. Cancelar pedido");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    cliente.consultarStock(sistemaLogistica);
                    break;

                case 2:
                    cliente.elegirProductos(sistemaLogistica, carrito);
                    break;

                case 3:
                    cliente.validarCompra(carrito)
                    break;

                case 4:
                    cliente.consultarEstadoPedido(sistemaLogistica);
                    break;

                case 5:
                    cliente.cancelarPedido(sistemaLogistica);
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no valida");
            }
        } while (opcion != 6);


    }
}
