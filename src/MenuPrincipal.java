import java.util.Scanner;

public class MenuPrincipal {
    private SistemaLogistica sistemaLogistica;

    public MenuPrincipal(SistemaLogistica sistemaLogistica) {
        this.sistemaLogistica = sistemaLogistica;
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Menú Cliente");
            System.out.println("2. Menú Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    MenuCliente menucliente = new MenuCliente(sistemaLogistica);
                    menucliente.mostrarMenu();
                    break;

                case 2:
                    MenuEmpleado menuEmpleado = new MenuEmpleado(sistemaLogistica);
                    menuEmpleado.mostrarMenu();
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no valida");
            }

        } while (opcion != 3);
    }
}
