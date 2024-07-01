public class Producto {
    private  int codigo;
    private  String nombre;
    private  String descripcion;
    private  Double precioBase;
    private  boolean oferta_sino;
    private  int stock;



    public Producto(int codigo, String nombre, String descripcion, Double precioBase, boolean oferta_sino, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
          this.oferta_sino = oferta_sino;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioBase=" + precioBase +
                ", oferta_sino=" + oferta_sino +
                ", stock=" + stock ;
    }

    public int consultarStockProductos(){

          return 0;

    }

    public int anadir_stock(){

        return 0;
    }

    public int generarOferta(){

        return 0;
    }


}
