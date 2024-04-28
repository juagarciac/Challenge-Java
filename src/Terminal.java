import com.ChallengeJava.Moneda.HistorialTransacciones;
import com.ChallengeJava.Moneda.Moneda;
import com.ChallengeJava.Moneda.Peticion;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
El flujo principal de print y entradas, estan los comentarios de terminal y se ejecutan la mayoria de los comandos en formato metodo
*/
public class Terminal {
    public static void ejecutar(){
        while (true) {
            try {
                HistorialTransacciones.inicializar();

                Scanner lectura = new Scanner(System.in);

                System.out.println("**********************************************************");
                System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
                System.out.println();
                System.out.println("En este programa debe escoger por la moneda que desea saber su cor");
                System.out.println("1) Dólar                     6) Dolar australiano");
                System.out.println("2) Peso argentino            7) Peso dominicano");
                System.out.println("3) Real brasileño            8) Euro");
                System.out.println("4) Peso colombiano           9) Sol peruano");
                System.out.println("5) Peso chileno              10) Peso uruguayo");
                System.out.println("");
                System.out.println("11) Salir                    12) Ver historial");
                System.out.println("elija una opción válida");
                System.out.println("**********************************************************");

                int moneda = lectura.nextInt();
                if (moneda == 11) {
                    break;
                }
                if (moneda == 12){
                    HistorialTransacciones.mostrar();
                    continue;
                }
                int monedaCambio = lectura.nextInt();
                if (moneda > 12 || moneda < 0 || monedaCambio > 12 || monedaCambio < 0) {
                    System.out.println("La posibilidad de las opciones es de maximo 11 y minimo 1");
                    continue;
                }
                if (monedaCambio == 11) {
                    break;
                }
                if (monedaCambio == 12){
                    HistorialTransacciones.mostrar();
                    continue;
                }
                System.out.println("Ingrese el valor que deseas convertir");
                double monto = lectura.nextDouble();
                Moneda monedero = Moneda.peticionMon(moneda , monedaCambio , monto);
                Peticion.cambioMoneda(monedero);
                HistorialTransacciones.guardarCambios();

                System.out.println("El valor "+monedero.getValor()+" ["+monedero.getMoneda()+"]"+" corresponde al valor final de =>>> "+monedero.getValorCambio()+" ["+monedero.getCambio()+"]");
                System.out.println("fecha de transacción "+monedero.getTiempoTomado());
            } catch (InputMismatchException e){
                System.err.println("No puedes usar palabras ni numeros decimales, solo numeros discretos");
            } catch (IOException | InterruptedException e) {
                System.out.println("El servidor no responde");
            }
        }
}
}
