
import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Pantalla {

    public static void print(String texto){
        System.out.println(texto);
    }
    public static void mostrarError(String mensajeError){
        System.out.println(colorize("Error!!", Attribute.TEXT_COLOR(255,0,0)));
        System.out.println(colorize(mensajeError,Attribute.TEXT_COLOR(255,0,0)));
    }
    public static void mostrartableros(String[][] tablero1, String[][] tablero2,
                                       String[][] tablero3, String[][] tablero4,int hacks){
        borrarPantalla();
        if(hacks==1) {
            System.out.print("    Tablero PC");
            System.out.print("                 ");
            System.out.println("Tablero PC Disparos");
            for (int i = 0; i < tablero1.length; i++) {
                for (int j = 0; j < tablero1.length; j++) {
                    System.out.print(tablero3[i][j]);
                }
                System.out.print("        ");
                for (int j = 0; j < tablero1.length; j++) {
                    System.out.print(tablero4[i][j]);

                }
                System.out.println();
            }
            System.out.println();
            System.out.print("     Tablero Jugador");
            System.out.print("          ");
            System.out.println("Tablero Jugador Disparos");

            for (int i = 0; i < tablero1.length; i++) {
                for (int j = 0; j < tablero1.length; j++) {
                    System.out.print(tablero1[i][j]);
                }
                System.out.print("        ");
                for (int j = 0; j < tablero1.length; j++) {
                    System.out.print(tablero2[i][j]);

                }
                System.out.println();
            }
            System.out.println();
        }else{
            System.out.print("     Tablero Jugador");
            System.out.print("          ");
            System.out.println("Tablero Jugador Disparos");

            for (int i = 0; i < tablero1.length; i++) {
                for (int j = 0; j < tablero1.length; j++) {
                    System.out.print(tablero1[i][j]);
                }
                System.out.print("        ");
                for (int j = 0; j < tablero1.length; j++) {
                    System.out.print(tablero2[i][j]);

                }
                System.out.println();
            }
            System.out.println();
        }
    }
    public static String[][] creartablero(){

        String[][] tablero = new String[11][11];
        tablero[10][0] =colorize( "  ",Attribute.BACK_COLOR(198,166,100));
        for(int fil=0;fil<tablero.length-1;fil++){
            tablero[fil][0]=colorize((char)('A'+fil)+" ",Attribute.BACK_COLOR(198,166,100),
                    Attribute.TEXT_COLOR(0,0,0));
            for(int col=1;col<tablero.length;col++){
                tablero[fil][col]=(colorize("~ ",Attribute.BACK_COLOR(3, 167, 187),
                        Attribute.TEXT_COLOR(0,0,0)));
            }
        }
        for(int col = 1;col<tablero.length;col++){
            tablero[10][col]=colorize((col-1)+" ",Attribute.BACK_COLOR(198,166,100),
                    Attribute.TEXT_COLOR(0,0,0));
        }
        return tablero;
    }
    public static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static String[][] colocarBarcosJugador(String[][]tablero,int barco){
        int[] coordenadas = Entrada.obtenerCoordenadas("Vas a colocar 1 barco de "+barco+" celdas");
        int eleccion1;
        int eleccion2;
        if(barco>1) {
            System.out.println("Lo quieres en vertical o en horizontal?");
            eleccion1=Entrada.comprobarEntero(0,1,"Vertical: 0 Horizontal: 1");
            if (eleccion1 == 0) {
                System.out.println("Hacia arriba o hacia abajo?");
                eleccion2=Entrada.comprobarEntero(0,1,"Arriba:0 Abajo:1");
                if (eleccion2 == 0) {
                    if(nocabeBarco(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        mostrarError("Has introducido mal el barco, no cabe en el tablero," +
                                "introducelo de nuevo");
                        return colocarBarcosJugador(tablero,barco);
                    }
                    if(haycolision(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        mostrarError("Has introducido mal el barco, colisiona con otro barco ya introducido,"
                                + "introducelo de nuevo");
                        return colocarBarcosJugador(tablero,barco);
                    }
                    tablero[coordenadas[0]][coordenadas[1]] =colorize("B ",
                            Attribute.BACK_COLOR(0, 0, 255),
                            Attribute.TEXT_COLOR(0,0,0));
                    for (int i = 1; i < barco; i++) {
                        tablero[coordenadas[0] - i][coordenadas[1]] =colorize("B ",
                                Attribute.BACK_COLOR(0, 0, 255),
                                Attribute.TEXT_COLOR(0,0,0));
                    }
                } else if (eleccion2 == 1) {
                    if(nocabeBarco(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        mostrarError("Has introducido mal el barco, no cabe en el tablero," +
                                "introducelo de nuevo");
                        return colocarBarcosJugador(tablero,barco);
                    }
                    if(haycolision(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        mostrarError("Has introducido mal el barco, colisiona con otro barco ya introducido,"
                                + "introducelo de nuevo");
                        return colocarBarcosJugador(tablero,barco);
                    }
                    tablero[coordenadas[0]][coordenadas[1]] = colorize("B ",
                            Attribute.BACK_COLOR(0, 0, 255),
                            Attribute.TEXT_COLOR(0,0,0));
                    for(int i=1;i<barco;i++) {
                        tablero[coordenadas[0] + i][coordenadas[1]] = colorize("B ",
                                Attribute.BACK_COLOR(0, 0, 255),
                                Attribute.TEXT_COLOR(0,0,0));
                    }
                }
            } else if (eleccion1 == 1) {
                System.out.println("Hacia la izquierda o hacia la derecha?");
                eleccion2=Entrada.comprobarEntero(0,1,"Izquierda:0 Derecha:1");
                if (eleccion2 == 0) {
                    if(nocabeBarco(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        mostrarError("Has introducido mal el barco, no cabe en el tablero," +
                                "introducelo de nuevo");
                        return colocarBarcosJugador(tablero,barco);
                    }
                    if(haycolision(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        mostrarError("Has introducido mal el barco, colisiona con otro barco ya introducido,"
                                + "introducelo de nuevo");
                        return colocarBarcosJugador(tablero,barco);
                    }
                    tablero[coordenadas[0]][coordenadas[1]] = colorize("B ",
                            Attribute.BACK_COLOR(0, 0, 255),
                            Attribute.TEXT_COLOR(0,0,0));
                    for(int i=1;i<barco;i++) {
                        tablero[coordenadas[0]][coordenadas[1] - i] =colorize("B ",
                                Attribute.BACK_COLOR(0, 0, 255),
                                Attribute.TEXT_COLOR(0,0,0));
                    }
                } else if (eleccion2 == 1) {
                    if(nocabeBarco(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        mostrarError("Has introducido mal el barco, no cabe en el tablero,"
                                + "introducelo de nuevo");
                        return colocarBarcosJugador(tablero,barco);
                    }
                    if(haycolision(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        mostrarError("Has introducido mal el barco, colisiona con otro barco ya introducido,"
                                + "introducelo de nuevo");
                        return colocarBarcosJugador(tablero,barco);
                    }
                    tablero[coordenadas[0]][coordenadas[1]] = colorize("B ",
                            Attribute.BACK_COLOR(0, 0, 255),
                            Attribute.TEXT_COLOR(0,0,0));
                    for(int i=1;i<barco;i++) {
                        tablero[coordenadas[0]][coordenadas[1] + i] =colorize("B ",
                                Attribute.BACK_COLOR(0, 0, 255),
                                Attribute.TEXT_COLOR(0,0,0));
                    }
                }
            }
        }else{
            tablero[coordenadas[0]][coordenadas[1]] =colorize("B ",
                    Attribute.BACK_COLOR(0, 0, 255),
                    Attribute.TEXT_COLOR(0,0,0));
        }
        return tablero;
    }
    public static String[][] colocarBarcosPC(String[][] tablero,int barco){
        int[] coordenadas=new int[2];
        coordenadas[0] = (int)(Math.random() * 10);
        coordenadas[1] = ((int)(Math.random() * 10)) + 1;
        System.out.println(coordenadas[1]);
        int eleccion1;
        int eleccion2;
        if(barco>1) {
            eleccion1 = (int)(Math.random() * 2);
            if (eleccion1 == 0) {
                eleccion2 = (int)(Math.random() * 2);
                if (eleccion2 == 0) {
                    if(nocabeBarco(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        return colocarBarcosPC(tablero,barco);
                    }
                    if(haycolision(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        return colocarBarcosPC(tablero,barco);
                    }
                    tablero[coordenadas[0]][coordenadas[1]] = colorize("B ",
                            Attribute.BACK_COLOR(0, 0, 255),
                            Attribute.TEXT_COLOR(0,0,0));
                    for(int i=1;i<barco;i++) {
                        tablero[coordenadas[0] - i][coordenadas[1]] = colorize("B ",
                                Attribute.BACK_COLOR(0, 0, 255),
                                Attribute.TEXT_COLOR(0,0,0));
                    }
                } else if (eleccion2 == 1) {
                    if(nocabeBarco(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        return colocarBarcosPC(tablero,barco);
                    }
                    if(haycolision(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        return colocarBarcosPC(tablero,barco);
                    }
                    tablero[coordenadas[0]][coordenadas[1]] =colorize("B ",
                            Attribute.BACK_COLOR(0, 0, 255),
                            Attribute.TEXT_COLOR(0,0,0));
                    for(int i=1;i<barco;i++) {
                        tablero[coordenadas[0] + i][coordenadas[1]] = colorize("B ",
                                Attribute.BACK_COLOR(0, 0, 255),
                                Attribute.TEXT_COLOR(0,0,0));
                    }
                }
            } else if (eleccion1 == 1) {
                eleccion2 = (int)(Math.random() * 2);
                if (eleccion2 == 0) {
                    if(nocabeBarco(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        return colocarBarcosPC(tablero,barco);
                    }
                    if(haycolision(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        return colocarBarcosPC(tablero,barco);
                    }
                    tablero[coordenadas[0]][coordenadas[1]] = colorize("B ",
                            Attribute.BACK_COLOR(0, 0, 255),
                            Attribute.TEXT_COLOR(0,0,0));
                    for(int i=1;i<barco;i++) {
                        tablero[coordenadas[0]][coordenadas[1] - i] = colorize("B ",
                                Attribute.BACK_COLOR(0, 0, 255),
                                Attribute.TEXT_COLOR(0,0,0));
                    }
                } else if (eleccion2 == 1) {
                    if(nocabeBarco(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        return colocarBarcosPC(tablero,barco);
                    }
                    if(haycolision(tablero,barco,coordenadas,eleccion1,eleccion2)){
                        return colocarBarcosPC(tablero,barco);
                    }
                    tablero[coordenadas[0]][coordenadas[1]] = colorize("B ",
                            Attribute.BACK_COLOR(0, 0, 255),
                            Attribute.TEXT_COLOR(0,0,0));
                    for(int i=1;i<barco;i++) {
                        tablero[coordenadas[0]][coordenadas[1] + i] =colorize("B ",
                                Attribute.BACK_COLOR(0, 0, 255),
                                Attribute.TEXT_COLOR(0,0,0));
                    }
                }
            }
        }else{
            tablero[coordenadas[0]][coordenadas[1]] = colorize("B ",
                    Attribute.BACK_COLOR(0, 0, 255),
                    Attribute.TEXT_COLOR(0,0,0));
        }
        return tablero;
    }
    public static boolean nocabeBarco(String[][] tablero, int longitudBarco, int[] coordenadas,
                                      int eleccion1, int eleccion2){
        if(eleccion1==0){
            if(eleccion2==0){
                return (coordenadas[0] + 1) - longitudBarco < 0;
            }else{
                return (coordenadas[0] + 1) + longitudBarco > tablero.length;
            }
        }else{
            if(eleccion2==0){
                return (coordenadas[1]) - longitudBarco < 0;
            }else{
                return coordenadas[1] + longitudBarco > tablero.length;
            }
        }
    }
    public static boolean haycolision(String[][] tablero, int longitudBarco, int[] coordenadas,
                                      int eleccion1, int eleccion2){
        if(eleccion1==0){
            if(eleccion2==0){
                for(int i=0;i<longitudBarco;i++) {
                    if (quitarColores(tablero[coordenadas[0]-i][coordenadas[1]]).equals("B ")) {
                        return true;
                    }
                }
            }else{
                for(int i=0;i<longitudBarco;i++) {
                    if (quitarColores(tablero[coordenadas[0]+i][coordenadas[1]]).equals("B ")) {
                        return true;
                    }
                }
            }
        }else{
            if(eleccion2==0){
                for(int i=0;i<longitudBarco;i++) {
                    if (quitarColores(tablero[coordenadas[0]][coordenadas[1]-i]).equals("B ")) {
                        return true;
                    }
                }
            }else{
                for(int i=0;i<longitudBarco;i++) {
                    if (quitarColores(tablero[coordenadas[0]][coordenadas[1]+i]).equals("B ")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean disparosJugador(String[][] tableroPC,int []coordenadas){
        if(quitarColores(tableroPC[coordenadas[0]][coordenadas[1]]).equals("B ")){
            return true;
        } else if (quitarColores(tableroPC[coordenadas[0]][coordenadas[1]]).equals("T ")){
            return false;
        }else{
            return false;
        }
    }
    public static void mostrardisparo(String[][] tablero,int []coordenadas){
        if(quitarColores(tablero[coordenadas[0]][coordenadas[1]]).equals("B ")){
            System.out.println(colorize("Buen disparo!!",Attribute.TEXT_COLOR(0,255,0)));
        } else if (quitarColores(tablero[coordenadas[0]][coordenadas[1]]).equals("T ")){
            mostrarError("Ahí ya habias disparado!!, has perdido el tiro");
        }else{
            System.out.println(colorize("Has fallado",Attribute.TEXT_COLOR(255,0,0)));
        }
    }
    public static boolean disparosPC(String[][] tableroJugador,int[] coordenadas){
        if(quitarColores(tableroJugador[coordenadas[0]][coordenadas[1]]).equals("B ")){
            return true;
        } else if (quitarColores(tableroJugador[coordenadas[0]][coordenadas[1]]).equals("T ")){
            return false;
        }else{
            return false;
        }
    }
    public static String quitarColores(String texto) {
        return texto.replaceAll("\u001B\\[[;\\d]*m", "");
    }
    public static void pintarT (String[][] tablero, int[] coordenadas){
        tablero[coordenadas[0]][coordenadas[1]]=colorize("T ",
                Attribute.BACK_COLOR(0, 255, 0),
                Attribute.TEXT_COLOR(0,0,0));
    }
    public static void pintarfallo (String[][] tablero, int[] coordenadas) {
        tablero[coordenadas[0]][coordenadas[1]] = colorize("* ",
                Attribute.BACK_COLOR(255, 0, 0),
                Attribute.TEXT_COLOR(0, 0, 0));
    }
    public static String[][] copiarTablero(String[][] original) {
        String[][] copia = new String[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, original[i].length);
        }
        return copia;
    }
}