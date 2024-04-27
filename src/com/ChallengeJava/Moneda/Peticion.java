package com.ChallengeJava.Moneda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Peticion {
    private static final String api_key = "74408c402dd4cb65edaf3a64";
    private static final String direccionInicial = "https://v6.exchangerate-api.com/v6/";
    public static void cambioMoneda(Moneda moneda) throws IOException, InterruptedException {
        String direccion = Peticion.direccionInicial + Peticion.api_key+"/pair/" + moneda.getMoneda() + "/" + moneda.getCambio() + "/" + moneda.getValor() ;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
        String json = response.body();
        Gson gson = new GsonBuilder()
                .create();

        MonedaCambiada moneda2 = gson.fromJson(json, MonedaCambiada.class);
        moneda.setValorCambio(moneda2.conversion_result());


        moneda.tomaDeTiempo();
        HistorialTransacciones.incluirTransaccion(moneda);

    }
}
