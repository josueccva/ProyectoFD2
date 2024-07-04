public class PreEntreno extends Producto{

    public PreEntreno(int codigo, String nombre, String descripcion, double precioBase, boolean oferta, int stock) {
        super(codigo, nombre, descripcion, precioBase, oferta, stock);
    }

    @Override
    public String getCategoria() {
        return "Pre-Entreno";
    }

    @Override
    public double calcularPrecio() {
        if (isOferta()){
            return  getPrecioBase() * 0.92; // 8% descuento por oferta
        }else{
            return getPrecioBase();
        }
    }

    @Override
    public String toString() {
        return "Categoria: " + getCategoria() + ", " + super.toString();
    }
}
