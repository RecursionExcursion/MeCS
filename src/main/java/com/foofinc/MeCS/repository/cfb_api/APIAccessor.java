package com.foofinc.MeCS.repository.cfb_api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

enum APIAccessor {

    INSTANCE;

    HttpResponse<String> accessAPI(String url, String bearerToken) throws InterruptedException, IOException {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                                                        .uri(URI.create(url))
                                                        .GET();

        //Set Headers
        requestBuilder.header("Content-Type", "application/json");
        if (bearerToken != null) {
            requestBuilder.header("Authorization", "Bearer " + bearerToken);
        }

        HttpRequest httpRequest = requestBuilder.build();

        HttpResponse<String> resp = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (resp.statusCode() != 200) {
            throw new IOException("Http Response Code- " + resp.statusCode());
        }

        return resp;
    }
}
