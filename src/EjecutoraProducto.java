public class EjecutoraProducto {
    public static void main(String[] args) {

        Proteina proteina1 = new Proteina(123, "vegetal","vegetal",52.6,true,20,5.6, "fresa");
        Proteina proteina2 = new Proteina(123, "animal","animal",52.6,false,15,10.6, "chocolate");
        preEstreno prestreno1 = new preEstreno(123, "vegetal","vegetal",52.6,true,20,5.6, "fresa","polvo");
        preEstreno prestreno2 = new preEstreno(123, "vegetal","vegetal",52.6,true,20,5.6, "fresa","gomitas");
        vitamina vitamina1 = new vitamina(123, "vegetal","vegetal",52.6,true,20);

        System.out.println(proteina1);
        System.out.println(proteina2);
        System.out.println(prestreno1);
        System.out.println(prestreno2);
        System.out.println(vitamina1);

    }
}
