import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaLogistica sistemaLogistica = new SistemaLogistica();
        Scanner scanner = new Scanner(System.in);

        Producto producto1 = new Producto(1, "Proteína Whey", "Proteína para aumento de masa muscular", 150.0, false, 50);
        Producto producto2 = new Producto(2, "Pre-Entreno X", "Mejora el rendimiento durante el ejercicio", 120.0, false, 30);
        Producto producto3 = new Producto(3, "Vitamina C", "Suplemento vitamínico", 30.0, false, 100);

        sistemaLogistica.registrarProducto(producto1);
        sistemaLogistica.registrarProducto(producto2);
        sistemaLogistica.registrarProducto(producto3);

        Cliente cliente1 = new Cliente("Juan", "Perez", "Calle Falsa 123", "correo", "987654321");
        sistemaLogistica.registrarCliente(cliente1);

        Empleado empleado1 = new Empleado(1, "Maria Gomez", "Gerente de Ventas");
        sistemaLogistica.registrarEmpleado(empleado1);

        MenuPrincipal menuPrincipal = new MenuPrincipal(sistemaLogistica, scanner);
        menuPrincipal.mostrarMenu();

        scanner.close();
    }
}