import java.util.Scanner;

public class MenuPrincipal {
    private SistemaLogistica sistemaLogistica;
    private Scanner scanner;

    public MenuPrincipal(SistemaLogistica sistemaLogistica, Scanner scanner) {
        this.sistemaLogistica = sistemaLogistica;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("===================================");
            System.out.println("|           Menú Principal        |");
            System.out.println("===================================");
            System.out.println("| 1. Menú Cliente                 |");
            System.out.println("| 2. Menú Empleado                |");
            System.out.println("| 3. Salir                        |");
            System.out.println("===================================");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    MenuCliente menuCliente = new MenuCliente(sistemaLogistica, scanner);
                    menuCliente.mostrarMenu();
                    break;
                case 2:
                    MenuEmpleado menuEmpleado = new MenuEmpleado(sistemaLogistica, scanner);
                    menuEmpleado.mostrarMenu();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 3);
    }
}
