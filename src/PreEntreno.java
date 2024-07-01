public class PreEntreno extends Producto{

    private  double peso;
    private String sabor;
    private String modelo;

    public PreEntreno(int codigo, String nombre, String descripcion, Double precioBase, boolean oferta_sino, int stock, double peso, String sabor, String modelo) {
        super(codigo, nombre, descripcion, precioBase, oferta_sino, stock);
        this.peso = peso;
        this.sabor = sabor;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "PreEntreno{" + super.toString()+
                "peso=" + peso +
                ", sabor='" + sabor + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
