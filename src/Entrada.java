
import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Entrada {

    private static int obtenerEntero(String texto){
        Scanner sc= new Scanner(System.in);
        Pantalla.print(texto);
        do {
            if(!sc.hasNextInt()) {
                Pantalla.mostrarError("Debes introducir un entero");
                sc.nextLine();
                Pantalla.print(texto);
            }
        }while(!sc.hasNextInt());
        return sc.nextInt();
    }
    public static int comprobarEntero(int min,int max,String texto){
        int num;
        do {
            num=obtenerEntero(texto);
            if (num < min) {
                System.out.println((colorize("El número debe ser mayor que "
                        + min,Attribute.TEXT_COLOR(255,0,0))));
            }
            if (num > max) {
                System.out.println(colorize("El número debe ser menor que "
                        + max,Attribute.TEXT_COLOR(255,0,0)));
            }
        }while(num<min || num>max);
        System.out.println(colorize("Correcto!!",Attribute.TEXT_COLOR(0,255,0)));
        return num;
    }
    private static boolean comprobarnum(int num){
        int min = 0;
        int max = 9;
        if(num<min || num>max) {
            if (num < min) {
                System.out.println((colorize("El número debe ser mayor que "
                        + min,Attribute.TEXT_COLOR(255,0,0))));
            }
            if (num > max) {
                System.out.println(colorize("El número debe ser menor que "
                        + max,Attribute.TEXT_COLOR(255,0,0)));
            }
            return true;
        }
        return false;
    }
    private static boolean comprobarLetras(char letra){
        char letra1 = 'A';
        char letra2 = 'J';
        if(letra<letra1 || letra>letra2) {
            if (letra < letra1) {
                System.out.println(colorize("La letra debe ser mayor que "
                        + letra1,Attribute.TEXT_COLOR(255,0,0)));
            }
            if (letra > letra2) {
                System.out.println(colorize("La letra debe ser menor que "
                        + letra2,Attribute.TEXT_COLOR(255,0,0)));
            }
            return true;
        }
        return false;
    }
    public static int[] obtenerCoordenadas(String texto) {
        Scanner sc = new Scanner(System.in);
        System.out.println(texto);
        int[] coordenadas = new int[2];
        String coord;
        char letra;
        int num;
        do {
            do {
                System.out.println("Dame las coordenadas como en estos ejemplos: A2, B5...");
                coord = sc.nextLine();
                if (coord.length()!=2){
                    Pantalla.mostrarError("Coordenadas mal introducidas!!");
                }
            }while (coord.length()!=2);
            letra=coord.charAt(0);
            String aux=letra+"";
            letra=aux.toUpperCase().charAt(0);
            num=coord.charAt(1)-'0';
        } while (comprobarLetras(letra) || comprobarnum(num));
        coordenadas[0]=letra-'A';
        coordenadas[1]=(coord.charAt(1)-'0')+1;
        return coordenadas;
    }
}