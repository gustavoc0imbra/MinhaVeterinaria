package org.uniara.AnimalService.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.uniara.AnimalService.DTO.AnimalPersonDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class PeopleConsumer {
    private final String AUTH_URL = "http://localhost:8081/api/v0/people/{personId}";
    private AnimalPersonDTO personDTO;

    public AnimalPersonDTO getById(String token, String personId) {
        String response = doRequest(token, personId);
        Map<String, String> json = convertResponse(response);

        personDTO = new AnimalPersonDTO(json.get("id"), json.get("name"));

        return personDTO;
    }

    private String doRequest(String token, String personId) {
        HttpClient client = HttpClient.newBuilder().build();
        String response = null;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL.replace("{personId}", personId)))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private Map<String, String> convertResponse(String response) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> json = null;

        try {
            json = mapper.readValue(response, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }
}
