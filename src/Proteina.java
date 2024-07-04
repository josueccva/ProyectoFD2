public class Proteina extends  Producto {
    private double peso;
    private String sabor;

    public Proteina(int codigo, String nombre, String descripcion, double precioBase, boolean oferta, int stock, double peso, String sabor) {
        super(codigo, nombre, descripcion, precioBase, oferta, stock);
        this.peso = peso;
        this.sabor = sabor;
    }

    @Override
    public String getCategoria() {
        return "Proteina";
    }

    @Override
    public double calcularPrecio() {
        if (isOferta()){
            return  getPrecioBase() * 0.95; // 5% descuento por oferta
        }else{
            return getPrecioBase();
        }
    }

    @Override
    public String toString() {
        return "Categoria: " + getCategoria() + ", " + super.toString() +
                ", peso=" + peso +
                ", sabor='" + sabor + "'";
    }

}
