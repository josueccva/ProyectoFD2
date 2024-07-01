public class Proteina extends Producto{

    public double peso;
    public String sabor;

    public Proteina(int codigo, String nombre, String descripcion, Double precioBase, boolean oferta_sino, int stock, double peso, String sabor) {
        super(codigo, nombre, descripcion, precioBase, oferta_sino, stock);
        this.peso = peso;
        this.sabor = sabor;
    }

    @Override
    public String toString() {
        return "Proteina{" +super.toString()+
                "peso=" + peso +
                ", sabor='" + sabor + '\'' +
                '}';
    }


}
