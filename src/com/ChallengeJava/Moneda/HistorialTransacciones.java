package com.ChallengeJava.Moneda;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HistorialTransacciones {
    private static ArrayList<Moneda> historialTransaccion = new ArrayList<Moneda>();
    private static final String nombreArchivo = "Historial.json";

    private static void crear(){
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter(nombreArchivo);

            gson.toJson(historialTransaccion, writer);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean existencia(){
        File directorioFile = new File(nombreArchivo);
        if (!directorioFile.exists()) {
            return false;
        } else {
            return true;
        }
    }


    public static void inicializar() throws IOException {
        if(!HistorialTransacciones.existencia()){HistorialTransacciones.crear();}

        Gson gson = new Gson();
        FileReader reader = new FileReader(nombreArchivo);

        historialTransaccion = gson.fromJson(reader, new TypeToken<ArrayList<Moneda>>(){}.getType());

        reader.close();

    }

    public static void guardarCambios(){
        try {
            FileWriter writer = new FileWriter(nombreArchivo);

            Gson gson = new Gson();

            gson.toJson(historialTransaccion, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void incluirTransaccion(Moneda moneda){
        historialTransaccion.add(moneda);
    }

    public static int numeroIntervalo(){
        if(historialTransaccion.size()%3>0){
            return historialTransaccion.size()/3+1;
        }
        return historialTransaccion.size()/3;
    }

    public static List<Moneda> listaContieneRango(){
        return HistorialTransacciones.comprobacion(0,3);
    }

    private static List<Moneda> listaContieneRango(int rangoElegido){
        return HistorialTransacciones.comprobacion(rangoElegido*3-3,rangoElegido*3);
    }

    private static List<Moneda> comprobacion(int inicio,int fin){
        try{
        return historialTransaccion.subList(inicio,fin);
        } catch (IndexOutOfBoundsException e){
            return historialTransaccion.subList(inicio,historialTransaccion.size());
        }
    }

    public static void mostrar(){
        if(!HistorialTransacciones.existencia()){
            System.out.println("No hay transacciones guardadas");
            return;
        }
        System.out.println("Historial transacciones");
        var abertura = HistorialTransacciones.numeroIntervalo();
        List<Moneda> lista = HistorialTransacciones.listaContieneRango();
        System.out.println("Indice actual 1");
        for (Moneda elemento : lista){
            System.out.println("Valor original "+elemento.getValor()+" ["+elemento.getMoneda()+"]"+" Valor de transacción "+elemento.getValorCambio()+" ["+elemento.getCambio()+"]");
            System.out.println("fecha de transacción "+elemento.getTiempoTomado()+"\n");
        }
        Scanner lectura = new Scanner(System.in);
        while(true){
            if(abertura<=1){
                break;
            }
            System.out.println("Escoge en el siguiente rango de valores para ver mas transacciones");
            System.out.println("              1 - "+ abertura + "\nsalir del historial -1\n");
            int numero = lectura.nextInt();
            while(numero > abertura || numero<-1){
                System.out.println("No puedes usar numeros mayores al limite del historial\n");
                System.out.println("Escoge en el siguiente rango de valores para ver mas transacciones");
                System.out.println("              1 - "+ abertura + " salir del historial -1\n");
                numero= lectura.nextInt();
            }
            if(numero==-1){
                break;
            }
            System.out.println("Historial transacciones");
            lista = HistorialTransacciones.listaContieneRango(numero);
            System.out.println("Indice actual "+numero+"\n");
            for (Moneda elemento : lista){
                System.out.println("Valor original "+elemento.getValor()+" ["+elemento.getMoneda()+"]"+" Valor de transacción "+elemento.getValorCambio()+" ["+elemento.getCambio()+"]");
                System.out.println("fecha de transacción "+elemento.getTiempoTomado()+"\n");
            }
        }
    }
}
