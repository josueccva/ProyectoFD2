// Archivo: src/MenuEmpleado.java
import java.util.Scanner;

public class MenuEmpleado {
    private SistemaLogistica sistemaLogistica;
    private Empleado empleado;
    private Scanner scanner;

    public MenuEmpleado(SistemaLogistica sistemaLogistica, Scanner scanner) {
        this.sistemaLogistica = sistemaLogistica;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        int opcion;

        System.out.println("===================================");
        System.out.println("|       Acceso de Empleado        |");
        System.out.println("===================================");
        System.out.println("| 1. Empleado registrado          |");
        System.out.println("| 2. Nuevo empleado               |");
        System.out.println("===================================");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                if (!empleadoRegistrado()) {
                    return;
                }
                break;
            case 2:
                nuevoEmpleado();
                break;
            default:
                System.out.println("Opción no válida. Regresando al menú principal...");
                return;
        }

        int opcionMenu;

        do {
            System.out.println("===================================");
            System.out.println("|           Menú Empleado         |");
            System.out.println("===================================");
            System.out.println("| 1. Consultar stock de productos |");
            System.out.println("| 2. Ingresar productos a stock   |");
            System.out.println("| 3. Retirar productos de stock   |");
            System.out.println("| 4. Consultar pedidos            |");
            System.out.println("| 5. Regresar al menú principal   |");
            System.out.println("===================================");
            System.out.print("Seleccione una opción: ");
            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 1:
                    empleado.consultarStock(sistemaLogistica);
                    break;
                case 2:
                    System.out.print("Ingrese el código del producto: ");
                    int codigoProducto = scanner.nextInt();
                    System.out.print("Ingrese la cantidad a añadir: ");
                    int cantidadAñadir = scanner.nextInt();
                    empleado.añadirStock(sistemaLogistica, codigoProducto, cantidadAñadir);
                    break;
                case 3:
                    System.out.print("Ingrese el código del producto: ");
                    int codigoProductoRetirar = scanner.nextInt();
                    System.out.print("Ingrese la cantidad a retirar: ");
                    int cantidadRetirar = scanner.nextInt();
                    empleado.reducirStock(sistemaLogistica, codigoProductoRetirar, cantidadRetirar);
                    break;
                case 4:
                    empleado.consultarPedidos(sistemaLogistica);
                    break;
                case 5:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            if (opcionMenu != 5) {
                System.out.println("Presione enter para regresar al menú.");
                scanner.nextLine();
                scanner.nextLine();
            }
        } while (opcionMenu != 5);
    }

    private boolean empleadoRegistrado() {
        scanner.nextLine();
        System.out.print("Ingrese su ID registrado: ");
        int id = scanner.nextInt();
        empleado = sistemaLogistica.buscarEmpleadoPorId(id);

        if (empleado == null) {
            System.out.println("Empleado no encontrado. Regresando al menú principal...");
            return false;
        }

        System.out.println("Bienvenido de nuevo, " + empleado.getNombre() + " (ID: " + empleado.getId() + ")");
        return true;
    }

    private void nuevoEmpleado() {
        System.out.println("===================================");
        System.out.println("|        Registro de Empleado     |");
        System.out.println("===================================");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Posición: ");
        String posicion = scanner.nextLine();

        empleado = new Empleado(id, nombre, posicion);
        sistemaLogistica.registrarEmpleado(empleado);

        System.out.println("Registro exitoso. Bienvenido, " + empleado.getNombre() + " (ID: " + empleado.getId() + ")");
    }
}