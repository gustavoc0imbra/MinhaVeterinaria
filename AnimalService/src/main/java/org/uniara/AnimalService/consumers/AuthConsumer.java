package org.uniara.AnimalService.consumers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthConsumer {
    private final String AUTH_URL = "http://localhost:8080/api/v0/auth";
    private boolean isAuthenticated;

    public boolean isAuthenticated(String token) {
        String response = doRequest(token);
        isAuthenticated = convertResponse(response);

        return isAuthenticated;
    }

    private String doRequest(String token) {
        HttpClient client = HttpClient.newBuilder().build();
        String response = null;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL))
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private boolean convertResponse(String response) {
        return Boolean.parseBoolean(response);
    }
}
