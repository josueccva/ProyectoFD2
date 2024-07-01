public class vitamina extends  Producto{

    public vitamina(int codigo, String nombre, String descripcion, Double precioBase, boolean oferta_sino, int stock) {
        super(codigo, nombre, descripcion, precioBase, oferta_sino, stock);
    }

    @Override
    public String toString() {
        return "vitamina{"+super.toString()+"}";
    }
}
