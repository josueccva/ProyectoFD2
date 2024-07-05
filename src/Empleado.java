import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Empleado {
    private int id;
    private String nombre;
    private String posicion;
    private static List<Empleado> empleados = new ArrayList<>();

    public Empleado(int id, String nombre, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        empleados.add(this);
    }

    public Empleado(String nombre, String posicion) {
        this.id = generarCodigo();
        this.nombre = nombre;
        this.posicion = posicion;
        empleados.add(this);
    }

    private int generarCodigo(){
        int cod = getId()+1;
        System.out.println("gen cod:" + cod);
        if (empleados.isEmpty()){
            return 1;
        }else {
            return empleados.getLast().getId() + 1;
        }
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    public void registrarProducto(SistemaLogistica sistemaLogistica) {
        try{
            int codigo = sistemaLogistica.obtenerSiguienteCodigoProducto();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del producto:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese una descripción del producto:");
            String descripcion = scanner.nextLine();

            // System.out.println("Cual sera el precio del producto en soles?");
            double precio = soloNumeroDecimal("Cual sera el precio del producto en soles?", "El valor del precio debe ser en decimal");
            // scanner.nextLine();

            // System.out.println("Estará en oferta? Marque: \n1. Si \n2. No");
            int numOferta = obtenerOfertaValida();
            boolean oferta =  (numOferta == 1) ? true : false;

            int stock = soloNumero("Cual es la cantidad de stock disponible?", "Ingrese un valor entero");

            // System.out.println("¿Qué tipo de producto desea registrar?");
            // System.out.println("1. Proteina \n2. Vitamina \n3.Pre-Entreno");
            int tipoProducto = obtenerTipoProducto();
            switch (tipoProducto) {
                case 1:
                    // Registrar Proteina
                    // System.out.println("¿Cual es el peso del producto?");
                    double peso = soloNumeroDecimal("¿Cual es el peso del producto?","El peso debe ser un decimal");
                    // scanner.nextLine();

                    System.out.println("¿Que sabor tiene el producto?");
                    String sabor = scanner.nextLine();

                    Producto proteina = new Proteina(codigo, nombre, descripcion,precio, oferta, stock, peso, sabor);
                    sistemaLogistica.registrarProducto(proteina);
                    System.out.println("Producto registrado: " + proteina.getNombre());
                    break;
                case 2:
                    // Registrar Vitamina
                    Producto vitamina = new Vitamina(codigo, nombre, descripcion,precio, oferta, stock);
                    sistemaLogistica.registrarProducto(vitamina);
                    System.out.println("Producto registrado: " + vitamina.getNombre());
                    break;
                case 3:
                    // Registrar Vitamina
                    Producto preentreno = new PreEntreno(codigo, nombre, descripcion,precio, oferta, stock);
                    sistemaLogistica.registrarProducto(preentreno);
                    System.out.println("Producto registrado: " + preentreno.getNombre());
                    break;
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error en el registro: " + e.getMessage());
        }

    }

    private int obtenerOfertaValida(){
        Scanner scanner = new Scanner(System.in);
        try {

            int tipoProducto = soloNumero("Estará en oferta? Marque: \n1. Si \n2. No", "Por favor, ingrese un numero valido de las opciones");
            // scanner.nextLine(); // Consume newline

            if (tipoProducto < 1 || tipoProducto > 2) {
                System.out.println("Intente de nuevo, esta vez ingrese una opcion valida (1 o 2).");
                return obtenerTipoProducto();
            }
            return tipoProducto;

        } catch (InputMismatchException e) {
            System.out.println("Error no ingreso un numero, intente de nuevo.");
            scanner.nextLine();
            return obtenerTipoProducto();
        }
    }

    private int obtenerTipoProducto() {
        Scanner scanner = new Scanner(System.in);
        try {
            int tipoProducto = soloNumero("¿Qué tipo de producto desea registrar? \n1. Proteina \n2. Vitamina \n3. Pre-Entreno", "Por favor, ingrese un numero valido de las opciones");
            // scanner.nextLine(); // Consume newline

            if (tipoProducto < 1 || tipoProducto > 3) {
                System.out.println("Tipo de producto no válido. Intente de nuevo.");
                return obtenerTipoProducto();
            }
            return tipoProducto;

        } catch (InputMismatchException e) {
            System.out.println("Error no ingreso un numero, intente de nuevo.");
            scanner.nextLine();
            return obtenerTipoProducto();
        }
    }

    // recursivo
    public int soloNumero(String mensaje, String errMensaje){
        Scanner scanner = new Scanner(System.in);
        System.out.println(mensaje);
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
    public double soloNumeroDecimal(String mensaje, String errMensaje){
        Scanner scanner = new Scanner(System.in);
        System.out.println(mensaje);
        double opcion;
        try{
            opcion = scanner.nextDouble();
            if (opcion <= 0.0) opcion = soloNumeroDecimal(mensaje, errMensaje);
        }catch (InputMismatchException e){
            System.out.println(errMensaje);
            scanner.next();
            opcion = soloNumeroDecimal(mensaje, errMensaje);
        }
        return opcion;
    }

    public void añadirStock(SistemaLogistica sistemaLogistica, int codigoProducto, int cantidad) {
        try {
            Producto producto = sistemaLogistica.obtenerProductoPorCodigo(codigoProducto);
            if (producto != null) {
                producto.añadirStock(cantidad);
                System.out.println("Stock añadido. Nuevo stock de " + producto.getNombre() + ": " + producto.getStock());
            } else {
                System.out.println("Producto no encontrado.");
            }
            // throw new NullPointerException("Forzando throw");
        } catch (Exception e) {
            System.out.println("Error al añadir stock: " + e.getMessage());
        }
    }

    public void reducirStock(SistemaLogistica sistemaLogistica, int codigoProducto, int cantidad) {
        try {
            Producto producto = sistemaLogistica.obtenerProductoPorCodigo(codigoProducto);
            if (producto != null) {
                if (producto.getStock() >= cantidad) {
                    producto.reducirStock(cantidad);
                    System.out.println("Stock reducido. Nuevo stock de " + producto.getNombre() + ": " + producto.getStock());
                } else {
                    System.out.println("No hay suficiente stock para retirar esa cantidad.");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al retirar stock: " + e.getMessage());
        }
    }

    public void consultarStock(SistemaLogistica sistemaLogistica) {
        try{
            System.out.println("Stock de productos disponibles:");
            for (Producto producto : sistemaLogistica.consultarStock()) {
                System.out.println("Categoria: "+ producto.getCategoria() + " - Producto: " + producto.getNombre() + " - Stock: " + producto.getStock() + " - Precio B.:" + producto.getPrecioBase() + " - Oferta: " + producto.textoOferta() + " - Precio: " + producto.calcularPrecio());
            }
        }catch (Exception e) {
            System.out.println("Error al consultar stock: " + e.getMessage());
        }

    }

    public void consultarPedidos(SistemaLogistica sistemaLogistica) {
        try{
            List<Pedido> pedidos = sistemaLogistica.getListaPedidos();
            if (pedidos.isEmpty()) {
                System.out.println("No hay pedidos registrados.");
            } else {
                System.out.println("Pedidos registrados:");
                for (Pedido pedido : pedidos) {
                    System.out.println("Pedido " + pedido.getCodigo() + " - Cliente: " + pedido.getCliente().getNombre() + " - Estado: " + pedido.getEstado());
                }
            }
        }catch (Exception e){
            System.out.println("Error al consultar pedidos: " + e.getMessage());
        }
    }
}

