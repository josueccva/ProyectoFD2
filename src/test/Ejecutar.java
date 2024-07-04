package test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejecutar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("escriba:");
            int opc = sc.nextInt();
            System.out.println(opc);
        }catch (InputMismatchException e){
            System.out.println("escriba2:");
            int opc = sc.nextInt();
            System.out.println(opc);
        }
    }
}
