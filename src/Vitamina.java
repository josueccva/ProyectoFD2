public class Vitamina extends  Producto{

    public Vitamina(int codigo, String nombre, String descripcion, Double precioBase, boolean oferta_sino, int stock) {
        super(codigo, nombre, descripcion, precioBase, oferta_sino, stock);
    }

    @Override
    public String toString() {
        return "Vitamina{"+super.toString()+"}";
    }
}
