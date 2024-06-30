public class Empleado {
    private int id;
    private String nombre;
    private String posicion;

    public Empleado(int id, String nombre, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getPosicion() {return posicion;}


}
