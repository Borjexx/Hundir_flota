import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Juego {
    public static void main(String[] args) {

        System.out.println("Quieres ver el tablero del PC también?");
        int hacks = Entrada.comprobarEntero(0,1,"0:NO ; 1:SI");

        String[][] tableroJugador = Pantalla.creartablero();
        String[][] tableroDisparosJugador= Pantalla.creartablero();
        String[][] tableroPC = Pantalla.creartablero();
        String[][] tableroDisparosPC = Pantalla.creartablero();

        Pantalla.mostrartableros(tableroJugador,tableroDisparosJugador,tableroPC,tableroDisparosPC,hacks);
        for(int i=1;i<=4;i++){
            Pantalla.colocarBarcosJugador(tableroJugador, i);
            Pantalla.colocarBarcosPC(tableroPC, i);
            Pantalla.mostrartableros(tableroJugador,tableroDisparosJugador,tableroPC,tableroDisparosPC,hacks);
        }
        int[] coordenadasJ;
        int[] coordenadasPC= new int[2];
        int puntuacionjugador = 0;
        int puntuacionPC = 0;
        String[][] tableroPCantiguo;
        while(puntuacionjugador<10 && puntuacionPC<10){
            tableroPCantiguo = Pantalla.copiarTablero(tableroPC);
            coordenadasJ = Entrada.obtenerCoordenadas("Vas a disparar, introduce las coordenadas:");
            if(Pantalla.disparosJugador(tableroPC,coordenadasJ)){
                Pantalla.pintarT(tableroPC,coordenadasJ);
                Pantalla.pintarT(tableroDisparosJugador,coordenadasJ);
                puntuacionjugador=puntuacionjugador+1;
            }else if(!Pantalla.quitarColores(tableroPC[coordenadasJ[0]][coordenadasJ[1]]).equals("T ")) {
                Pantalla.pintarfallo(tableroPC,coordenadasJ);
                Pantalla.pintarfallo(tableroDisparosJugador,coordenadasJ);
            }
            coordenadasPC[0] = (int)(Math.random() * 10);
            coordenadasPC[1] = ((int)(Math.random() * 10)) + 1;
            if(Pantalla.disparosPC(tableroJugador,coordenadasPC)){
                Pantalla.pintarT(tableroJugador,coordenadasPC);
                Pantalla.pintarT(tableroDisparosPC,coordenadasPC);
                puntuacionPC=puntuacionPC+1;
            }else if(!Pantalla.quitarColores(tableroPC[coordenadasPC[0]][coordenadasPC[1]]).equals("T ")){
                Pantalla.pintarfallo(tableroJugador,coordenadasPC);
                Pantalla.pintarfallo(tableroDisparosPC,coordenadasPC);
            }
            Pantalla.mostrartableros(tableroJugador,tableroDisparosJugador,tableroPC,tableroDisparosPC,hacks);
            Pantalla.mostrardisparo(tableroPCantiguo,coordenadasJ);
            System.out.println("Puntos del jugador: "+puntuacionjugador);
            System.out.println("Puntos del PC: "+puntuacionPC);
        }
        if(puntuacionjugador==10){
            System.out.println(colorize("Has ganado!!", Attribute.TEXT_COLOR(0,255,0)));
        }else if(puntuacionPC==10){
            System.out.println(colorize("Has perdido!!", Attribute.TEXT_COLOR(255,0,0)));
        }
    }
}