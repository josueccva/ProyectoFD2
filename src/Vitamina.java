public class Vitamina extends Producto{

    public Vitamina(int codigo, String nombre, String descripcion, double precioBase, boolean oferta, int stock) {
        super(codigo, nombre, descripcion, precioBase, oferta, stock);
    }

    @Override
    public String getCategoria() {
        return "Vitamina";
    }

    @Override
    public double calcularPrecio() {
        if (isOferta()){
            return  getPrecioBase() * 0.90; // 10% descuento por oferta
        }else{
            return getPrecioBase();
        }
    }

    @Override
    public String toString() {
        return "Categoria: " + getCategoria() + ", " + super.toString();
    }
}
