package com.ChallengeJava.Moneda;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/*
Clase principal del programa, tiene las monedas y valores dados por el usuario y por la transacción
*/

public class Moneda {
    private double valor = (double) 0;
    private String moneda;
    private double valorCambio;
    private String cambio;
    private String tiempoTomado;


    Moneda(MonedaCambiada moneda2){
        this.valor=moneda2.conversion_result();
        this.moneda=moneda2.target_code();
    }

    Moneda(double valor,String moneda){
        this.valor=valor;
        this.moneda = moneda;
    }

    Moneda(double valor,String moneda,String cambio){
        this.valor=valor;
        this.moneda = moneda;
        this.cambio = cambio;
    }

    /*
metodo para crear monedas con los valores predeterminados dados por la api
*/

    public static Moneda peticionMon(int moneda,int monedaCambio, double monto) {
        HashMap<Integer, String> monedaDisponibles = new HashMap<>();
        monedaDisponibles.put(1, "USD");
        monedaDisponibles.put(2, "ARS");
        monedaDisponibles.put(3, "BRL");
        monedaDisponibles.put(4, "COP");
        monedaDisponibles.put(5, "CLP");
        monedaDisponibles.put(6, "AUD");
        monedaDisponibles.put(7, "DOP");
        monedaDisponibles.put(8, "EUR");
        monedaDisponibles.put(9, "PEN");
        monedaDisponibles.put(10, "UYU");

        return new Moneda(monto,monedaDisponibles.get(moneda) ,monedaDisponibles.get(monedaCambio));
    }

    public void tomaDeTiempo(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatoCompleto = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.tiempoTomado = ahora.format(formatoCompleto);
    }


    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorCambio() {
        return valorCambio;
    }

    public void setValorCambio(double valorCambio) {
        this.valorCambio = valorCambio;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getTiempoTomado() {
        return tiempoTomado;
    }
}