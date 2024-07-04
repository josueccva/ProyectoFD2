// Archivo: src/MenuEmpleado.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuEmpleado {
    private SistemaLogistica sistemaLogistica;
    private Empleado empleado;
    private Scanner scanner;

    private Producto producto;

    public MenuEmpleado(SistemaLogistica sistemaLogistica, Scanner scanner) {
        this.sistemaLogistica = sistemaLogistica;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        int opcion;
        opcion = opcionAccesoSeleccionado();

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
            opcionMenu = opcionMenuEmpSeleccionado();
            switch (opcionMenu) {
                case 1:
                    empleado.consultarStock(sistemaLogistica);
                    break;
                case 2:
                    // agregarStockProducto
                    // System.out.print("Ingrese el código del producto: ");
                    // int codigoProducto = scanner.nextInt();
                    int codigoProducto = soloNumero("📑 Ingrese el código del producto: ", "❌ El código debe ser un entero ❌");
                    // System.out.print("Ingrese la cantidad a añadir: ");
                    int cantidadAñadir = soloNumero("📑 Ingrese la cantidad a añadir: ","❌ La cantidad ha añadir debe ser un entero ❌");
                    System.out.println("-----------------------------------------------");
                    empleado.añadirStock(sistemaLogistica, codigoProducto, cantidadAñadir);
                    System.out.println("-----------------------------------------------");
                    break;
                case 3:

                    int codigoProductoRetirar = soloNumero("📑 Ingrese el código del producto: ", "❌ El código debe ser un entero ❌");
                    int cantidadRetirar = soloNumero("📑 Ingrese la cantidad a retirar: ","❌ La cantidad ha retirar debe ser un entero ❌");
                    System.out.println("---------------------------------------------------");
                    empleado.reducirStock(sistemaLogistica, codigoProductoRetirar, cantidadRetirar);
                    System.out.println("---------------------------------------------------");
                    break;
                case 4:
                    empleado.consultarPedidos(sistemaLogistica);
                    break;
                case 5:
                    sistemaLogistica.listarTodosProductos();
                    break;
                case 6:
                    // sistemaLogistica.registrarProducto(producto);
                    empleado.registrarProducto(sistemaLogistica);
                    break;
                case 7:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            if (opcionMenu != 7) {
                System.out.println("Presione enter para regresar al menú.");
                scanner.nextLine();
                scanner.nextLine();
            }
        } while (opcionMenu != 7);
    }

    public void registrarProducto(Scanner scanner){
        System.out.println("¿Qué tipo de producto desea registrar?");
        System.out.println("1. Proteina \n2. Vitamina");
        int tipoProducto = scanner.nextInt();
    }

    public int soloNumero(String mensaje, String errMensaje){
        System.out.print(mensaje);
        int opcion;
        try{
            opcion = scanner.nextInt();
            if (opcion <= 0) opcion = soloNumero(mensaje, errMensaje);
        }catch (InputMismatchException e){
            System.out.println(errMensaje);
            scanner.next();
            opcion = soloNumero(mensaje, errMensaje);
        }
        return opcion;
    }

    // recursivo
    private boolean empleadoRegistrado() {
        scanner.nextLine();
        System.out.print("📑 Ingrese su ID registrado: ");
        boolean retorno;

        try{
            int id = scanner.nextInt();
            empleado = sistemaLogistica.buscarEmpleadoPorId(id);

            if (empleado == null) {
                System.out.println("🙅‍♂️ Empleado no encontrado. Regresando al menú principal... 🙅‍♀️");
                return false;
            }
            System.out.println("🏋️‍♀️ Bienvenido de nuevo, " + empleado.getNombre() + " 🏋️‍♂️ (ID: " + empleado.getId() + ") 💪");
            retorno = true;
        }catch (InputMismatchException e){
            System.out.println("❌ El ID debe ser un número entero. ❌");
            scanner.next();
            retorno = empleadoRegistrado();
        }
        return retorno;
    }

    private void nuevoEmpleado() {
        System.out.println("===================================");
        System.out.println("|        Registro de Empleado     |");
        System.out.println("===================================");
        // System.out.print("ID: ");
        // int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Posición: ");
        String posicion = scanner.nextLine();

        empleado = new Empleado(nombre, posicion);// empleado = new Empleado(id, nombre, posicion);
        sistemaLogistica.registrarEmpleado(empleado);

        System.out.println("Registro exitoso. Bienvenido, " + empleado.getNombre() + " (ID: " + empleado.getId() + ")");
    }

    // recursivo
    public int opcionAccesoSeleccionado(){
        System.out.println("===================================");
        System.out.println("|       Acceso de Empleado        |");
        System.out.println("===================================");
        System.out.println("| 1. Empleado registrado          |");
        System.out.println("| 2. Nuevo empleado               |");
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
    public int opcionMenuEmpSeleccionado(){
        System.out.println("===================================");
        System.out.println("|           Menú Empleado         |");
        System.out.println("===================================");
        System.out.println("| 1. Consultar stock de productos |");
        System.out.println("| 2. Ingresar productos a stock   |");
        System.out.println("| 3. Retirar productos de stock   |");
        System.out.println("| 4. Consultar pedidos            |");
        System.out.println("| 5. Listar todos los productos   |");
        System.out.println("| 6. Registrar producto           |");
        System.out.println("| 7. Regresar al menú principal   |");
        System.out.println("===================================");
        System.out.print("Seleccione una opción: ");
        int opcion;
        try{
            opcion = scanner.nextInt();
            if (opcion < 1 || opcion > 7) {
                System.out.println("❌ Opción inválida. Por favor, ingrese una opción válida. ❌");
                opcion = opcionMenuEmpSeleccionado();
            }

        }catch (InputMismatchException e){
            System.out.println("❌ Por favor, ingrese un número entero. ❌");
            scanner.next();
            opcion = opcionMenuEmpSeleccionado();
        }
        return opcion;
    }
}