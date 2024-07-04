import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaLogistica sistemaLogistica = new SistemaLogistica();
        Scanner scanner = new Scanner(System.in);

        // Producto producto1 = new Producto(1, "Proteína Whey", "Proteína para aumento de masa muscular", 150.0, false, 50);
        Producto producto1 = new Proteina(1,"Proteína Whey", "Proteína para aumento de masa muscular", 150.0, false, 50,0.350, "Chocolate");
        Producto producto2 = new Proteina(2,"Proteína ISO XP", "Proteína para aumento de masa muscular", 160, false, 50,1.8, "Chocolate");
        Producto producto3 = new Proteina(3,"Dorian Yates - Proteina", "Proteína para aumento de masa muscular", 160, true, 50,1.0, "Vainilla");
        Producto producto4 = new Vitamina(4, "Max Marine Omega 3", "Suplemento de Omega 3 EPA & DHA", 130.0, false, 100);
        Producto producto5 = new Vitamina(5, "Cólageno", "Suplemento vitamínico Max Marine 3", 130.0, true, 100);
        Producto producto6 = new Vitamina(6, "Calcium &  Magnesium", "Suplemento vitamínico", 80.0, true, 100);
        Producto producto7 = new PreEntreno(7, "CLA Pre-Entreno", "Mejora el rendimiento durante el ejercicio", 120.0, false, 30);
        Producto producto8 = new PreEntreno(8, "C4 Ultimate", "Mejora el rendimiento durante el ejercicio", 240.0, true, 40);
        Producto producto9 = new PreEntreno(9, "Bloods & Guts", "Mejora el rendimiento durante el ejercicio", 130.0, false, 50);


        sistemaLogistica.registrarProducto(producto1);
        sistemaLogistica.registrarProducto(producto2);
        sistemaLogistica.registrarProducto(producto3);
        sistemaLogistica.registrarProducto(producto4);
        sistemaLogistica.registrarProducto(producto5);
        sistemaLogistica.registrarProducto(producto6);
        sistemaLogistica.registrarProducto(producto7);
        sistemaLogistica.registrarProducto(producto8);
        sistemaLogistica.registrarProducto(producto9);

        // System.out.println("listado products main");
        // sistemaLogistica.listarTodosProductos();

        Cliente cliente1 = new Cliente("Juan", "Perez", "Calle Falsa 123", "correo", "987654321");
        sistemaLogistica.registrarCliente(cliente1);

        Empleado empleado1 = new Empleado(1, "Maria Gomez", "Gerente de Ventas");
        sistemaLogistica.registrarEmpleado(empleado1);

        MenuPrincipal menuPrincipal = new MenuPrincipal(sistemaLogistica, scanner);
        menuPrincipal.mostrarMenu();

        scanner.close();
    }
}